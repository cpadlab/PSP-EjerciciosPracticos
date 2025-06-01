
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
public class Servidor {
    
    private static final int PUERTO = 1014;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Semaforo semaforo = new Semaforo();
        ServerSocket servidor = new ServerSocket(PUERTO);
        
        while (true) {
            Socket cliente = servidor.accept();
            Thread hiloCliente = new HiloServidor(cliente, semaforo);
            hiloCliente.start();
        }
        
    }
    
}
