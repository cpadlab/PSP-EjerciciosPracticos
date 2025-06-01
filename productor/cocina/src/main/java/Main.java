
/**
 *
 * @author Carlos Padilla
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Cocina cocina = new Cocina();
        
        Thread[] hilos = new Thread[4];
        
        for (int i = 0; i < hilos.length; i++ ) {
            hilos[i] = new Cocinero(cocina);
            hilos[i].start();
        }
        
        for ( Thread hilo : hilos ) {
            try { hilo.join(); } 
            catch (InterruptedException e) { System.err.println(e.getMessage()); }
        }
        
        System.out.println("Todos los cocineros han terminado.");
        
    }
    
}
