
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author cpadl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Lavanderia lavanderia = new Lavanderia();
        
        Thread[] clientes = new Thread[6];
        for ( int i = 0; i < clientes.length ; i++ ) {
            clientes[i] = new Cliente(lavanderia);
            clientes[i].start();
        }
        
        for ( Thread cliente : clientes ) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                System.err.print(e);
            }
        }
        
        System.out.print("\nLavanderia Cerrada");
        
    }
    
}
