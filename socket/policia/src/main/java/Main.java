
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Main {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Scanner input = new Scanner(System.in);
        
        Thread[] hilos = new Thread[5];
        
        hilos[0] = new Juzgado();
        hilos[0].start();
        
        hilos[1] = new Servidor((Juzgado) hilos[0]);
        hilos[1].start();
        
        for (int i = 2; i < hilos.length; i++) {
            hilos[i] = new Patrulla();
            hilos[i].start();
        }
        
        while (true) {
            
            System.out.println("1. Seguir patrullando\n2. Terminar turno\n3. Ver estadísticas\n-----");
            int respuesta = input.nextInt();
            
            if (respuesta == 2) {
                for ( Thread hilo : hilos ) {
                    hilo.interrupt();
                }      
                break;
            } else if (respuesta == 3) {
                ((Juzgado) hilos[0]).verEstadisticas();
            }
            
        }
        
    }
    
}
