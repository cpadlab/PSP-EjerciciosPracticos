
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Carlos Padilla
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
