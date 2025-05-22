
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Carlos Padilla
 */
public class HiloServidor extends Thread {
    
    private SSLSocket cliente;
    private Semaforo semaforo;
    
    public HiloServidor(SSLSocket cliente, Semaforo semaforo) {
        this.cliente = cliente;
        this.semaforo = semaforo;
    }
    
    @Override
    public void run() {
        
        System.out.print("Cliente Conectado");
        
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true, StandardCharsets.UTF_8)
        ) {
            
            String peticion = entrada.readLine();
            if (peticion == null || (!peticion.startsWith("GET") && !peticion.startsWith("POST"))) {
                return; 
            }
            
            System.out.println("peticion: " + peticion);
            String ruta = peticion.split(" ")[1];
            String linea;

            int contentLength = 0;
            while (!(linea = entrada.readLine()).isBlank()) {
                System.out.println("linea: "+ linea);
                if (linea.startsWith("Content-Length: ")) {
                    contentLength = Integer.parseInt(linea.substring(16));
                }
            }
            System.out.println("linea: vacía");

            StringBuilder cuerpo = new StringBuilder();
            if (peticion.startsWith("POST") && contentLength > 0) {
                char[] buffer = new char[contentLength];
                entrada.read(buffer, 0, contentLength);
                cuerpo.append(buffer);
            }

            String respuesta;
            if (ruta.equals("/")) { respuesta = manejarFarmacia(cuerpo.toString()); }
            else { respuesta = construirRespuesta(404, Paginas.errorPage); }
            
            salida.println(respuesta);
            
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
        
    }
    
    private String manejarFarmacia(String cuerpo) throws InterruptedException, IOException {
        
        int codigo = 200;
        System.out.println("Cuerpo: " + cuerpo);
        
        if (!cuerpo.isEmpty()) {
            
            String[] partes = cuerpo.split("&");
            
            String producto = partes[0].split("=")[1];
            producto = URLDecoder.decode(producto, StandardCharsets.UTF_8);
            
            int cantidad = 0;
            cantidad = Integer.parseInt(partes[1].split("=")[1]);
            
            String[] stockActual = semaforo.accesoLeer().split("\n");
            int indiceProducto = -1;
            
            int stockProducto = 0;
            boolean productoEncontrado = false;
            
            for (int i = 0; i < stockActual.length; i++) {
                String linea = stockActual[i];
                if (linea.split(":")[0].equals(producto)) {
                    productoEncontrado = true;
                    stockProducto = Integer.parseInt(linea.split(":")[1]);
                    indiceProducto = i;
                    break;
                }
            }

            
            if (!productoEncontrado) {
                return construirRespuesta(codigo, Paginas.indexPage("<p style='color: red;'>Producto no encontrado.</p>"));
            } 
            
            if (stockProducto - cantidad < 0) {
                return construirRespuesta(codigo, Paginas.indexPage("<p style='color: red;'>Stock insuficiente.</p>"));
            }
            
            if (indiceProducto != -1) {
                stockActual[indiceProducto] = producto + ":" + (stockProducto - cantidad);
            }
            
            semaforo.accesoEscribir(stockActual);
            
            return construirRespuesta(codigo, Paginas.indexPage("<p style='color: green;'>Stock actualizado correctamente.</p>"));
            
        } else {
            return construirRespuesta(codigo, Paginas.indexPage(""));
        }
        
    }
    
    private String construirRespuesta(int codigo, String contenido) {
        return (codigo == 200 ? "HTTP/1.1 200 OK" : "HTTP/1.1 400 Bad Request") + "\n"
            + "Content-Type: text/html; charset=UTF-8"+ "\n"
            + "Content-Length: " + contenido.length() + "\n"
            + "\n"
            + contenido;
    }
    
}
