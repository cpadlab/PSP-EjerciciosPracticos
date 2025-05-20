
/**
 *
 * @author Carlos Padilla
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CintaTransportadora cintaTransportadora = new CintaTransportadora();
        
        Thread[] maquinas = new Thread[4];
        for (int i = 0; i < maquinas.length - 1 ; i++ ) {
            maquinas[i] = new MaquinaAutomatica(cintaTransportadora);
            maquinas[i].start();
        }
        
        maquinas[3] = new MaquinaEmpaquetadora(cintaTransportadora);
        maquinas[3].start();
        
        for ( Thread maquina : maquinas ) {
            try {
                maquina.join();
            } catch (Exception e) {
                System.err.print(e.getMessage());
            }
        }
        
        System.out.print("Fábrica Cerrada");
        
    }
    
}
