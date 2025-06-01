
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Cliente extends Thread {
    
    private static final int PUERTO = 1014;
    private static final String HOST = "localhost";
    
    private Random random;
    
    private int numComensales;
    private String dia;
    
    private Socket cliente;
    private BufferedReader lectura;
    private PrintWriter escritura;
    
    public Cliente() {
        this.random = new Random();
    }
    
    @Override
    public void run() {
        
        try {
            
            numComensales = random.nextInt(24) + 1;
            int numDia = random.nextInt(2);
            
            if (numDia == 1) { dia = "Sábado"; }
            else { dia = "Domingo"; }
            
            cliente = new Socket(HOST, PUERTO);
            lectura = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escritura = new PrintWriter(cliente.getOutputStream(), true);
            
            reservarMesa();
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    private void reservarMesa() throws IOException {
        
        lectura.readLine();
        escritura.println(dia);
        
        lectura.readLine();
        escritura.println(numComensales);
        
        System.out.print("Soy " + Thread.currentThread().getName() + " y he solicitado una mesa para " + numComensales + " comensales el " + dia + "\n");
        System.out.print(lectura.readLine());
        
    }
    
}
