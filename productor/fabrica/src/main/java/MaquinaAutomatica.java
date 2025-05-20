
import java.util.Random;


/**
 *
 * @author Carlos Padilla
 */
public class MaquinaAutomatica extends Thread {
    
    private CintaTransportadora cintaTransportadora;
            
    public MaquinaAutomatica(CintaTransportadora cintaTransportadora) {
        this.cintaTransportadora = cintaTransportadora;
    }
    
    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            
            if (cintaTransportadora.getPiezasFabricadas() >= 50) {
                interrupt();break;
            }
            
            try {
                generarPieza();
            } catch (InterruptedException e) {
                System.err.print(e);
            }
            
        }
    }
    
    private void generarPieza() throws InterruptedException {
        
        cintaTransportadora.agregarPieza();
        int tiempoEspera = (new Random().nextInt(2)) + 1;
        Thread.sleep(tiempoEspera*1000);
        
    }
    
}
