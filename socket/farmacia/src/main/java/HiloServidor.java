
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Padilla
 */
public class HiloServidor extends Thread {
    
    private Socket cliente;
    private BufferedReader lectura;
    private PrintWriter escritura;
    private Producto paracetamol, ibuprofeno, vitamina;
    
    public HiloServidor(Socket cliente, Producto paracetamol, Producto ibuprofeno, Producto vitamina) {
        this.cliente = cliente;
        this.paracetamol = paracetamol;
        this.ibuprofeno = ibuprofeno;
        this.vitamina = vitamina;
    }
    
    @Override
    public void run() {
        
        try {
            
            lectura = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escritura = new PrintWriter(cliente.getOutputStream(), true);
            
            String[] partes = lectura.readLine().split(":");
            String producto = partes[0];
            int cantidad = Integer.parseInt(partes[1]);
            
            if ("paracetamol".equals(producto)) {
                
                int stock = paracetamol.getStock();
                if (stock - cantidad < 0) {
                    escritura.println("Stock insuficiente");
                    return;
                }
                
                paracetamol.setStock(stock-cantidad);
                escritura.println("Éxito. Quedan: " + (stock-cantidad) + " paracetamol");
                
            } else if ("ibuprofeno".equals(producto)) {
                
                int stock = ibuprofeno.getStock();
                if (stock - cantidad < 0) {
                    escritura.println("Stock insuficiente");
                    return;
                }
                
                ibuprofeno.setStock(stock-cantidad);
                escritura.println("Éxito. Quedan: " + (stock-cantidad) + " ibuprofeno");
                
            } else if ("vitamina".equals(producto)) {
                
                int stock = vitamina.getStock();
                if (stock - cantidad < 0) {
                    escritura.println("Stock insuficiente");
                    return;
                }
                
                vitamina.setStock(stock-cantidad);
                escritura.println("Éxito. Quedan: " + (stock-cantidad) + " vitaminas");
                
            } else {
                escritura.println("Producto no encontrado");
            }
            
            cliente.close();
            
        } catch (IOException e) {
            System.err.print(e.getMessage());
        } 
        
    }
    
}
