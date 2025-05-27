
/**
 *
 * @author Carlos Padilla
 */
public class Colmena {
    
    private static final int CAPACIDAD_MAXIMA = 30;
    private int nectar = 0;
    
    public int getNectar() {
        return nectar;
    }
    
    public synchronized void depositarMiel() throws InterruptedException {
        
        String name = Thread.currentThread().getName();
        
        if (nectar >= CAPACIDAD_MAXIMA) {
            wait();
        }
        
        nectar++;
        System.out.println("--- " + name + "trajo néctar. Miel en el panal: " + nectar);
        
        notifyAll();
    }
    
    public synchronized int comerMiel() throws InterruptedException {
        
        if (nectar <= 3) {
            wait();
        }
        
        int miel = nectar;
        nectar = 0;
        notifyAll();
        
        return miel;
        
    }
    
}
