
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Carlos Padilla
 */
public class Semaforo {
    
    private boolean isLibre = true;
    private int lectoresActivos = 0;
    private static final int LECTORES_MAXIMOS = 5;
    
    private static final String ALGORITMO = "AES";
    private static final byte[] CLAVE_AES = "1234567890123456".getBytes();
    
    public synchronized int accesoLeer(String dia) throws InterruptedException, IOException, Exception {
        
        String name = Thread.currentThread().getName();
        if (lectoresActivos >= LECTORES_MAXIMOS) {
            System.out.println("[" + name + "] esperando a leer.\n");
            wait();
        }
        
        lectoresActivos++;
        System.out.println("[" + name + "] leyendo. Lectores activos: " + lectoresActivos + "\n");
        
        int reservasActuales = 0;
        
        try {
            
            Path rutaArchivo = Paths.get(dia.toLowerCase() + ".txt");
            if (!Files.exists(rutaArchivo)) { System.out.println("El archivo " + dia + ".txt no existe.");
            } else {
                
                String archivoDescifrado = descifrar(Files.readAllBytes(rutaArchivo));
                reservasActuales = Integer.parseInt(archivoDescifrado);
                
            }
            
        } finally {
            System.out.println("[" + name + "] ha terminado de leer.\n");
            lectoresActivos--; notifyAll();
        }
        
        return reservasActuales;
    }
    
    public synchronized void accesoEscribir(String dia, int numeroReservas) throws InterruptedException, IOException, Exception {
        
        String name = Thread.currentThread().getName();
        if (!isLibre) {
            System.out.println("[" + name + "] esperando a escribir.\n");
            wait();
        }
        
        isLibre = false;
        System.out.println("[" + name + "] escribiendo.\n");
        
        try {
            
            Path rutaArchivo = Paths.get(dia.toLowerCase() + ".txt");
            if (!Files.exists(rutaArchivo)) { 
                Files.createFile(rutaArchivo);  
            } 
            
            String contenido = String.join(System.lineSeparator(), String.valueOf(numeroReservas));
            Files.write(rutaArchivo, cifrar(contenido));
            
        } finally {
            System.out.println("[" + name + "] ha terminado de escribir.\n");
            isLibre = true; notifyAll();
        }
        
    }
    
    private static byte[] cifrar(String datos) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        SecretKey clave = new SecretKeySpec(CLAVE_AES, ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        return cipher.doFinal(datos.getBytes());
    }
    
    private static String descifrar(byte[] datos) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        SecretKey clave = new SecretKeySpec(CLAVE_AES, ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return new String(cipher.doFinal(datos));
    }
    
}
