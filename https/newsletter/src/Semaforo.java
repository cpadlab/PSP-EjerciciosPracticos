
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
    private int lectoresActivos = 0;
    private boolean isLibre = true;
    
    private static final String ALGORITMO = "AES";
    private static final byte[] CLAVE_AES = "1234567890123456".getBytes();
    
    private static final Path rutaArchivo = Paths.get("newsletter.txt");
    
    public synchronized String accesoLeer() throws Exception {
        
        String archivo = "";
        
        if (lectoresActivos >= LECTORES_MAXIMOS) { wait(); }
        lectoresActivos++;
        
        try {
            
            if (!Files.exists(rutaArchivo)) { 
                System.err.print("Archivo no existe");
                archivo = "";
            } else {
                archivo = descifrarArchivo(Files.readAllBytes(rutaArchivo));   
            }
        
        } finally {
            lectoresActivos--;
            notify(); 
        }
        
        return archivo;
        
    }
    
    public synchronized void accesoEscribir(String linea) throws Exception {
        
        if (!isLibre) { wait(); }
        isLibre = false;
        
        try {
            
            if (!Files.exists(rutaArchivo)) { 
                Files.createFile(rutaArchivo);
            }
            
            String archivo = accesoLeer();
            archivo += linea + "\n";
            
            System.out.print(archivo);
            
            Files.write(rutaArchivo, cifrarArchivo(archivo));
            
        } finally {
            isLibre = true;
            notify();
        }
    }
    
    private static byte[] cifrarArchivo(String datos) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        SecretKey clave = new SecretKeySpec(CLAVE_AES, ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        return cipher.doFinal(datos.getBytes());
    }
    
    private static String descifrarArchivo(byte[] datos) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        SecretKey clave = new SecretKeySpec(CLAVE_AES, ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return new String(cipher.doFinal(datos));
    }
    
}
