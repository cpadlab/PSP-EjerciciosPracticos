/**
 *
 * @author Carlos Padilla
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        int STOCK_INICIAL = 10;
        int PUERTO = 1014;
        
        Producto paracetamol = new Producto(STOCK_INICIAL);
        Producto ibuprofeno = new Producto(STOCK_INICIAL);
        Producto vitamina = new Producto(STOCK_INICIAL);
        
        Thread servidor = new Servidor(PUERTO, paracetamol, ibuprofeno, vitamina);
        servidor.start();
        
        
    }
    
}
