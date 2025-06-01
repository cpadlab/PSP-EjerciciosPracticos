
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class SimuladorCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Scanner input = new Scanner(System.in);
        
        while (true) {
            
            int respuesta;
            
            while (true) {
                System.out.println("¿Que desea hacer?\n[1] Simular peticiones clientes.\n[2] Terminar programa.");
                respuesta = input.nextInt();
                if (respuesta <= 2 & respuesta >= 1) {
                    break;
                }
            }
            
            if (respuesta == 1) { 
                
                int numHilos;
                
                while (true) {
                    System.out.println("\n¿Cuántos clientes desea simular?: ");
                    numHilos = input.nextInt();
                    if (numHilos > 0 ) {
                        break;
                    }
                }
                
                Thread[] hilos = new Thread[numHilos];
                
                for (int i = 0; i < hilos.length; i++) {
                    hilos[i] = new Cliente();
                    hilos[i].start();
                }
                
                for ( Thread hilo : hilos ) {
                    hilo.join();
                }
                
                
            } else { break; }
            
        }
        
    }
    
}
