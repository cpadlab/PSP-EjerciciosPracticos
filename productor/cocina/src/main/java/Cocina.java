
import java.util.Random;

/**
 *
 * @author Carlos Padilla
 */
public class Cocina {
    
    private static final int CUCHILLOS_MAXIMOS = 2;
    private int cuchillosEnUso = 0;
    
    public synchronized void cogerCuchillo() throws InterruptedException {
        
        String nombre = Thread.currentThread().getName();
        if (cuchillosEnUso >= CUCHILLOS_MAXIMOS) {
            System.out.println(nombre + " está esperando un cuchillo…");
            wait();
        }
        
        cuchillosEnUso++;
        
    }
    
    public synchronized void soltarCuchillo() {
        cuchillosEnUso--;
        String nombre = Thread.currentThread().getName();
        System.out.println(nombre + " ha soltado un cuchillo. Cuchillos disponibles: " + cuchillosEnUso);
        notifyAll();
    }
    
    public int getCuchillosEnUso() {
        return cuchillosEnUso - CUCHILLOS_MAXIMOS;
    }
    
}
