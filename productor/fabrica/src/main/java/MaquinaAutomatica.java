
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadl
 */
public class MaquinaAutomatica extends Thread {
    
    private CintaTransportadora cintaTransportadora;
            
    public MaquinaAutomatica(CintaTransportadora cintaTransportadora) {
        this.cintaTransportadora = cintaTransportadora;
    }
    
    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            
            if (cintaTransportadora.getPiezasFabricadas() >= 50) {
                interrupt();break;
            }
            
            try {
                generarPieza();
            } catch (InterruptedException e) {
                System.err.print(e);
            }
            
        }
    }
    
    private void generarPieza() throws InterruptedException {
        
        cintaTransportadora.agregarPieza();
        int tiempoEspera = (new Random().nextInt(2)) + 1;
        Thread.sleep(tiempoEspera*1000);
        
    }
    
}
