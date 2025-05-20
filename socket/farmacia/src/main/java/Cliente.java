
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Padilla
 */
public class Cliente extends Thread {
    
    private Socket cliente;
    private BufferedReader lectura;
    private PrintWriter escritura;
    private Random random;
    
    private int PUERTO;
    
    public Cliente(int PUERTO) {
        this.PUERTO = PUERTO;
    }
    
    @Override
    public void run() {
        
        try {   
            
            cliente = new Socket("localhost", PUERTO);
            
            lectura = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escritura = new PrintWriter(cliente.getOutputStream(), true);
            
            solicitarProducto();
            
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
        
    }
    
    private void solicitarProducto() throws IOException {
        
        random = new Random();
        int cantidad = random.nextInt(2) + 1;
        int randomProducto = random.nextInt(3) + 1;
        
        String producto;
        if (randomProducto == 1) { producto = "paracetamol"; } 
        else if (randomProducto == 2) { producto = "ibuprofeno"; } 
        else { producto = "vitamina"; }
        
        escritura.println(producto + ":" + cantidad);
        System.out.println(lectura.readLine());

        
        
    }
    
}
