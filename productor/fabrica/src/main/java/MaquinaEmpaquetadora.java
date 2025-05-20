
/**
 *
 * @author Carlos Padilla
 */
public class MaquinaEmpaquetadora extends Thread {
    
    private CintaTransportadora cintaTransportadora;
    private int piezasEmpaquetadas = 0;
            
    public MaquinaEmpaquetadora(CintaTransportadora cintaTransportadora) {
        this.cintaTransportadora = cintaTransportadora;
    }
    
    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            
            if (piezasEmpaquetadas >= 50) {
                interrupt();break;
            }
            
            try {
                sacarPieza();
            } catch (InterruptedException e) {
                System.err.print(e);
            }
            
        }
    }
    
    private void sacarPieza() throws InterruptedException {
        
        piezasEmpaquetadas++;
        cintaTransportadora.sacarPieza(piezasEmpaquetadas);
        Thread.sleep(2000);
        
    }
    
}
