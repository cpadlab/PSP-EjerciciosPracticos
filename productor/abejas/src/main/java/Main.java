
/**
 *
 * @author Carlos Padilla
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Colmena colmena = new Colmena();
        
        Thread[] hilos = new Thread[3];
        
        hilos[0] = new Oso(colmena);
        hilos[0].start();
        
        for (int i = 1; i < hilos.length ; i++) {
            hilos[i] = new Abeja(colmena, hilos[0]);
            hilos[i].start();
        }
        
        for ( Thread hilo : hilos ) {
            try { hilo.join(); } 
            catch (InterruptedException e) { System.err.println(e.getMessage()); }
        }
        
        System.out.println("Panal de abejas cerrado. Fin del dia.");
                
    }
    
}
