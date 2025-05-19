
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadl
 */
public class Cliente extends Thread {
    
    private Lavanderia lavanderia;
    
    public Cliente(Lavanderia lavanderia) {
        this.lavanderia = lavanderia;
    }
    
    @Override
    public void run() {
        
        try {
            
            lavanderia.lavar();
            int tiempoEspera = (new Random().nextInt(6) + 5) * 1000;
            Thread.sleep(tiempoEspera);
            lavanderia.terminar();
            
            interrupt();
            
        } catch (InterruptedException e) {
            System.err.print(e);
        }
        
    }
    
}
