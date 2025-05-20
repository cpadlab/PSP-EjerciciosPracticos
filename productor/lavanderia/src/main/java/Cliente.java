
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Padilla
 */
public class Cliente extends Thread {
    
    private Lavanderia lavanderia;
    
    public Cliente(Lavanderia lavanderia) {
        this.lavanderia = lavanderia;
    }
    
    @Override
    public void run() {
        
        try {
            
            lavanderia.lavar();
            int tiempoEspera = (new Random().nextInt(6) + 5) * 1000;
            Thread.sleep(tiempoEspera);
            lavanderia.terminar();
            
            interrupt();
            
        } catch (InterruptedException e) {
            System.err.print(e);
        }
        
    }
    
}
