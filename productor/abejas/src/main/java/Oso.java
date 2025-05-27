
import java.util.Random;

/**
 *
 * @author Carlos Padilla
 */
public class Oso extends Thread {
    
    private Colmena colmena;
    private Random random;
    
    private int comilona = 0;
    private int miel = 0;
    
    public Oso(Colmena colmena) {
        this.colmena = colmena;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        
        while (Thread.currentThread().isAlive()) {
            
            if (comilona == 5) {
                Thread.currentThread().interrupt();
                break;
            }
            
            try {
                
                int nectar = colmena.comerMiel();
                miel += nectar;
                
                comilona++;
                
                System.out.println(">>> Comilona del oso "+ comilona + ". Miel en la panza: " + miel);
                
                int tiempoEspera = (random.nextInt(14) + 1) * 1000;
                Thread.sleep(tiempoEspera);
                
            } catch (InterruptedException e) { System.err.println(e.getMessage()); }
        }
        
        System.out.println(">>> Oso lleno hasta arriba se marcha a hibernar una larga temporada.");
            
    }
    
}
