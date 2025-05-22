
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Carlos Padilla
 */
public class Semaforo {
    
    private static final int LECTORES_MAXIMOS = 5;
    
    private boolean isLibre = true;
    private int lectoresActivos = 0;
    private static final Path rutaArchivo = Paths.get("stock.txt");
    
    public synchronized String accesoLeer() throws InterruptedException, IOException {
        
        String nombre = Thread.currentThread().getName();
        if (lectoresActivos >= LECTORES_MAXIMOS) {
            System.out.print("[" + nombre + "] en cola lectura.");
            wait();
        }
        
        lectoresActivos++;
        System.out.println("[" + nombre + "] leyendo. Lectores activos: " + lectoresActivos);
        
        try {
            
            if (!Files.exists(rutaArchivo)) {
                System.err.println("El archivo stock.txt no existe.");
                return "";
            }

            return new String(Files.readAllBytes(rutaArchivo));
            
        } finally {
            System.out.println(Thread.currentThread().getName() + ": Ya ha leido");
            lectoresActivos--;
            notify();
        }
        
    }
    
    public synchronized void accesoEscribir(String[] stock) throws InterruptedException, IOException {
        
        String nombre = Thread.currentThread().getName();
        if (!isLibre) {
            System.out.print("[" + nombre + "] en cola escritura.");
            wait();
        }
        
        isLibre = false;
        System.out.println("[" + nombre + "] escribiendo.");
        
        try {
            
            if (!Files.exists(rutaArchivo)) {
                Files.createFile(rutaArchivo);
            }

            String contenido = String.join(System.lineSeparator(), stock);
            Files.write(rutaArchivo, contenido.getBytes());
            
        } finally {
            System.out.println(Thread.currentThread().getName() + ": Ya ha escrito");
            isLibre = true;
            notify();
        }
        
    }
    
    
    
}
