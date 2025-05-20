
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Padilla
 */
public class SimuladorClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        int PUERTO = 1014;
        List<Cliente> listaClientes = new ArrayList<>();

        for (int i = 0; i <= 30; i++) {
            Cliente cliente = new Cliente(PUERTO);
            listaClientes.add(cliente);
            cliente.start();
        }

        for (Cliente cliente : listaClientes) {
            cliente.join();
        }

    }
    
}
