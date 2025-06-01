
import java.util.Random;

/**
 *
 * @author Carlos Padilla
 */
public class Cocinero extends Thread {
    
    private int cuchillosUsados = 0;
    private Cocina cocina;
    
    public Cocinero(Cocina cocina) {
        this.cocina = cocina;
    }
    
    @Override
    public void run() {
        
        String nombre = Thread.currentThread().getName();
        
        Random random = new Random();
        
        for (int i = 0; i < 5; i++) {
            
            cuchillosUsados++;
            
            try { 
                cocina.cogerCuchillo();
                System.out.println(nombre + " ha tomado un cuchillo por " + cuchillosUsados + " vez. Cuchillos disponibles: " + String.valueOf(cocina.getCuchillosEnUso()));
                Thread.sleep((random.nextInt(10) + 10) * 1000);
            } 
            catch (InterruptedException e) { System.err.println(e.getMessage()); }
            finally { 
                
                try { Thread.sleep((random.nextInt(5) + 5) * 1000); }
                catch (InterruptedException e) { System.err.println(e.getMessage()); }
                
                cocina.soltarCuchillo(); 
                
            }
            
            
            
        }
        
        System.out.println(nombre + " ha terminado su trabajo.");
        
    }
    
}
