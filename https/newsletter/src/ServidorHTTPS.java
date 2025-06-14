
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class ServidorHTTPS {
    
    private static final int PUERTO = 1014;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Semaforo semaforo = new Semaforo();
        
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyFile = new FileInputStream("AlmacenSSL")) {
            keyStore.load(keyFile, "123456".toCharArray());
        }
        
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, "123456".toCharArray());
        
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, null);
        
        SSLServerSocketFactory factory = sslContext.getServerSocketFactory();
        SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PUERTO);
        
        while (true) {
            SSLSocket cliente = (SSLSocket) server.accept();
            Thread hiloServidor = new HiloServidor(semaforo, cliente);
            hiloServidor.start();
        }

        
    }
    
}
