
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Carlos Padilla
 */
public class ServidorHTTPS {
    
    private static final int PUERTO = 1014;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException, KeyManagementException {
        
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
        System.out.print("Servidor iniciado en: https://localhost:" + PUERTO);
        
        while (true) {
            SSLSocket cliente = (SSLSocket) server.accept();
            Thread hiloServidor = new HiloServidor(cliente, semaforo);
            hiloServidor.start();
        }
        
    }
    
}
