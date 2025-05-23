
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class ServidorHTTP {
    
    private static final int PUERTO = 1015;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Semaforo semaforo = new Semaforo();
        
        ServerSocket serverSocket = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado en: http://localhost:" + PUERTO);

        while (true) {
            Socket cliente = serverSocket.accept();
            Thread hiloServidor = new HiloServidor(cliente, semaforo);
            hiloServidor.start();
        }
        
    }
    
}
