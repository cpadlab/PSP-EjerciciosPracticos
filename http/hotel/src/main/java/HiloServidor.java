
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Padilla
 */
public class HiloServidor extends Thread {
    
    private Socket cliente;
    private Semaforo semaforo;
    
    public HiloServidor(Socket cliente, Semaforo semaforo) {
        this.cliente = cliente;
        this.semaforo = semaforo;
    }
    
    @Override
    public void run() {
        System.out.println("Cliente Conectado");   
        
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            var salida = new PrintWriter(cliente.getOutputStream(), true, StandardCharsets.UTF_8)
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
            
            String respuesta;
            
            System.out.println("linea: vacía");
            StringBuilder cuerpo = new StringBuilder();
            if (peticion.startsWith("POST") && contentLength > 0) {
                
                char[] buffer = new char[contentLength];
                entrada.read(buffer, 0, contentLength);
                cuerpo.append(buffer);
                
                if (ruta.equals("/")) { respuesta = manejarPost(cuerpo.toString());
                } else { respuesta = construirRespuesta(404, Paginas.html_noEncontrado); }
                
            } else {
                if (ruta.equals("/")) { respuesta = construirRespuesta(200, Paginas.html_reservas);
               } else { respuesta = construirRespuesta(404, Paginas.html_noEncontrado); }   
            }
            
            salida.println(respuesta);
            
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String manejarPost(String cuerpo) throws InterruptedException, IOException, Exception {
        
        int codigo = 200;
        System.out.print(cuerpo);
        
        if (!cuerpo.isEmpty()) {
            
            String[] partes = cuerpo.split("&");
            
            String dia = partes[0].split("=")[1];
            dia = URLDecoder.decode(dia, StandardCharsets.UTF_8);
            System.out.println("Dia: " + dia);
            
            int cantidad = Integer.parseInt(partes[1].split("=")[1]);
            
            int reservasActuales = semaforo.accesoLeer(dia);
            semaforo.accesoEscribir(dia, reservasActuales+cantidad);
                    
        }
        
        return construirRespuesta(codigo, Paginas.html_reservas);
    }
    
    private String construirRespuesta(int codigo, String contenido) {
        return (codigo == 200 ? "HTTP/1.1 200 OK" : "HTTP/1.1 400 Bad Request") + "\n"
            + "Content-Type: text/html; charset=UTF-8"+ "\n"
            + "Content-Length: " + contenido.length() + "\n"
            + "\n"
            + contenido;
    }
    
}
