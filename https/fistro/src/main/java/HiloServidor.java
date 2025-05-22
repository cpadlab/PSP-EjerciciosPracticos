
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
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
        
        System.out.print("Cliente Conectado");
        
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
            
            System.out.println("linea: vacía");
            String respuesta;
            
            if (peticion.startsWith("POST") && ruta.equals("/")) {
                respuesta = manejarFistro();
                
            } else {
                
                if (ruta.equals("/")) { respuesta = construirRespuesta(404, Paginas.indexPage("")); }
                else { respuesta = construirRespuesta(404, Paginas.errorPage); }
                
            }
            
            salida.println(respuesta);
            
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }        
        
    }
    
    private String manejarFistro() throws InterruptedException, IOException {
        
        int codigo = 200;
        String frase = "";
        Random random = new Random();
        
        String[] frases = semaforo.accesoLeer().split("\n");
        frase = frases[random.nextInt(frases.length)];
                
        return construirRespuesta(codigo, Paginas.indexPage("<p>" + frase + "</p>"));
        
    }
    
    private String construirRespuesta(int codigo, String contenido) {
        return (codigo == 200 ? "HTTP/1.1 200 OK" : "HTTP/1.1 400 Bad Request") + "\n"
            + "Content-Type: text/html; charset=UTF-8"+ "\n"
            + "Content-Length: " + contenido.length() + "\n"
            + "\n"
            + contenido;
    }
    
}
