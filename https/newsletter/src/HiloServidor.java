
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class HiloServidor extends Thread {
    
    private Semaforo semaforo;
    private SSLSocket cliente;
    
    public HiloServidor(Semaforo semaforo, SSLSocket cliente) {
        this.semaforo = semaforo;
        this.cliente = cliente;
    }
    
    @Override
    public void run() {
        
        System.out.print("Cliente Conectado");
        
        try (
            BufferedReader entrada= new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida=new PrintWriter(cliente.getOutputStream(), true, StandardCharsets.UTF_8);
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
            if (ruta.equals("/")) { respuesta = manejarPost(cuerpo.toString()); }
            else { respuesta = construirRespuesta(404, Paginas.html404); }
            salida.println(respuesta);
            
        } catch (IOException e) { System.err.print(e.getMessage()); 
        } catch (Exception e) { System.err.print(e.getMessage()); } 
        
    }
    
    private String manejarPost(String cuerpo) throws Exception {
        
        if (!cuerpo.isEmpty()) {
            
            String[] pares = cuerpo.split("&");
            String nombre = pares[0].split("=")[1];
            String email = pares[1].split("=")[1];
            
            String email_utf8 = URLDecoder.decode(email, StandardCharsets.UTF_8);
            String nombre_utf8 = URLDecoder.decode(nombre, StandardCharsets.UTF_8);
            
            String[] archivo = semaforo.accesoLeer().split("\n");
            boolean emailExists = false;
            
            for ( String linea : archivo ) {
                try {
                    String[] partes = linea.split(":");
                    if (partes[1].equals(email_utf8)) {
                        emailExists = true;
                    }
                } catch (Exception e) {}
                
            }
            
            if (emailExists) {
                return construirRespuesta(200, Paginas.htmlIndex("<p style='color: red;'>El usuario ya está registrado</p>"));
            } else {
                
                semaforo.accesoEscribir(nombre_utf8 + ":" + email_utf8);
                return construirRespuesta(200, Paginas.htmlIndex("<p style='color: green;'>Usuario registrado correctamente</p>"));   
                
            }
        
        } else {
            return construirRespuesta(200, Paginas.htmlIndex(""));   
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
