
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
public class Servidor extends Thread {
    
    private static final int PUERTO = 1014;
    private Juzgado juzgado;
    
    public Servidor(Juzgado juzgado) {
        this.juzgado = juzgado;
    }
    
    @Override
    public void run() {
        
        try {
        
            ServerSocket servidor = new ServerSocket(PUERTO);

            while (true) {
                    Socket cliente = servidor.accept();
                    Thread hiloCliente = new HiloServidor(cliente, juzgado);
                    hiloCliente.start();
            }
            
        } catch (IOException e) { System.out.println(e.getMessage()); }
        
        
    }
            
    
    
}
