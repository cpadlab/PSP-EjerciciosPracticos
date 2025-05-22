
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Semaforo {
    
    private static final int LECTORES_MAXIMOS = 5;
    private static final Path rutaArchivo = Paths.get("frases.txt");
    private int numLectores = 0;
    
    public synchronized String accesoLeer() throws InterruptedException, IOException {
        
        String nombre = Thread.currentThread().getName();
        if (numLectores >= LECTORES_MAXIMOS) {
            System.out.print("[" + nombre + "] esperando a leer.");
            wait();
        }
        
        numLectores++;
        System.out.print("[" + nombre + "] leyendo.");
        
        try {
            
            if (!Files.exists(rutaArchivo)) {
                System.out.print("El archivo frases.txt no existe.");
                return null;
            }
            
            return new String(Files.readAllBytes(rutaArchivo));
            
        } finally {
            numLectores--;
            System.out.print("[" + nombre + "] ha terminado de leer.");    
            notifyAll();
        }
        
    }
    
}
