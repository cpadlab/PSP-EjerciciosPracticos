
/**
 *
 * @author Carlos Padilla
 */
public class Lavanderia {
    
    private int clientesAtendidos = 0;
    private int lavadorasDisponibles = 4;
    private int gananciaTotal = 0;
    
    public Lavanderia(){
        
    }
    
    public synchronized void lavar() throws InterruptedException {
        
        String nombre = Thread.currentThread().getName();
        
        while (lavadorasDisponibles <= 0) {
            System.out.print("El cliente [" + nombre + "] está esperando para lavar.\n");
            wait();
        }
        
        clientesAtendidos++;
        System.out.print("Se ha atendido a " + clientesAtendidos + " clientes.\n");
        
        gananciaTotal += 3;
        System.out.print("Se ha recaudado " + gananciaTotal + "€.\n");
        
        lavadorasDisponibles--;
        System.out.print("Hay " + lavadorasDisponibles + " lavadoras disponibles.\n");
        System.out.print("Hay " + (4-lavadorasDisponibles) + " lavadoras en uso.\n");
        
    }
    
    public synchronized void terminar() throws InterruptedException {
        lavadorasDisponibles++;   
        notifyAll();
    }
    
}
