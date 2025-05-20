import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Carlos Padilla
 */
public class Servidor extends Thread {
    
    private ServerSocket servidor;
    
    private int PUERTO;
    private Producto paracetamol, ibuprofeno, vitamina;
    
    public Servidor(int PUERTO, Producto paracetamol, Producto ibuprofeno, Producto vitamina) {
        
        this.PUERTO = PUERTO;
        
        this.paracetamol = paracetamol;
        this.ibuprofeno = ibuprofeno;
        this.vitamina = vitamina;
    
    }

    @Override
    public void run() {
        
        try {
            
            servidor = new ServerSocket(PUERTO);
            System.out.print("Servidor abierto en el puerto: " + PUERTO);
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.print("Cliente conectado.");
                Thread hiloCliente = new HiloServidor(cliente, paracetamol, ibuprofeno, vitamina);
                hiloCliente.start();
            }
            
        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
        
    }
    
}
