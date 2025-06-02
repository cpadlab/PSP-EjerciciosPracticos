
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author cpadlab
 */
public class Patrulla extends Thread {
    
    private final String[] detenidos = { "El Gasofa", "La Loli", "El Chispas" };
    private static final int PUERTO = 1014;
    private Random random;
    
    public Patrulla() {
        this.random = new Random();        
    }
    
    @Override()
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                
                Socket cliente = new Socket("localhost", PUERTO);
                PrintWriter escritura = new PrintWriter(cliente.getOutputStream(), true);
                
                int detenidoAleatorio = random.nextInt(detenidos.length);
                String detenido = detenidos[detenidoAleatorio];
                
                escritura.println(detenido);
                
                System.out.println("[" + Thread.currentThread().getName() + "] Detenido '" + detenido + "' detenido y puesto a disposición judicial.");
                
                cliente.close();
                
                try {
                    int tiempoEspera = random.nextInt(4) + 1;
                    Thread.sleep(tiempoEspera*1000);
                } catch (InterruptedException e) { System.err.println(e.getMessage()); }
                
            } catch (IOException e) { System.err.println(e.getMessage()); }
        }
    }
    
}
