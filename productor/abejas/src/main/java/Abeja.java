
import java.util.Random;

/**
 *
 * @author Carlos Padilla
 */
public class Abeja extends Thread {
    
    private Colmena colmena;
    private Random random;
    private Thread oso;
    
    public Abeja(Colmena colmena, Thread oso) {
        this.oso = oso;
        this.colmena = colmena;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        
        while (Thread.currentThread().isAlive()) {
            
            if (oso.isAlive() || colmena.getNectar() < 30) {
                
                try {
                
                    colmena.depositarMiel();

                    int tiempoEspera = (random.nextInt(4) + 1) * 1000;
                    Thread.sleep(tiempoEspera);

                } catch (InterruptedException e) { System.err.println(e.getMessage()); }
                
            
            } else {
                Thread.currentThread().interrupt();
                break;
            }
            
        }
        
        System.out.println(Thread.currentThread().getName() + "ve que el panal esta cerrado y se marcha.");
        
    }
    
}
