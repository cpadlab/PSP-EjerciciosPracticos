
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class HiloServidor extends Thread {
    
    private Socket cliente;
    private Juzgado juzgado;
    
    private BufferedReader lectura;
    
    public HiloServidor(Socket cliente, Juzgado juzgado) {
        this.cliente = cliente;
        this.juzgado = juzgado;
    }
    
    @Override
    public void run() {
        
        try {
            
            lectura = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            String detenido = lectura.readLine();
            juzgado.añadirDetenido(detenido);
            
            int detenidosActuales = juzgado.getEntradaDetenidos() + 1;
            juzgado.setEntradaDetenidos(detenidosActuales);
            
        } catch (IOException e) { System.err.println(e.getMessage()); 
        } finally { 
            try { cliente.close(); } 
            catch (IOException e) { System.err.println(e.getMessage()); }
        }
        
    }
    
}
