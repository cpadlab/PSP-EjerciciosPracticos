
import java.util.ArrayList;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Juzgado extends Thread {
    
    private int entradaDetenidos = 0;
    private int detenidosLibertad = 0;
    private int detenidosPrision = 0;
    
    private final String[] destinos = { "LIBERTAD con cargos.", "PRISIÓN preventiva." };

    private ArrayList<String> detenidos;
    private Random random;
    
    public Juzgado() {
        this.detenidos = new ArrayList<>();
        this.random = new Random();
    }
    
    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            
            if (!detenidos.isEmpty()) {
                
                int detenidoAleatorio = random.nextInt(detenidos.size());
                String detenido = detenidos.get(detenidoAleatorio);
                
                int destinoAleatorio = random.nextInt(destinos.length);
                String destino = destinos[destinoAleatorio];
                
                if (destinoAleatorio == 0) { detenidosLibertad++; } 
                else { detenidosPrision++; }
                
                detenidos.remove(detenidoAleatorio);
                System.out.println(">>> Juzgando a '" + detenido + "'... Resultado: " + destino);
                
            } else {
                System.out.println(">>> No hay detenidos para juzgar.");
            }
             
            try {
                int tiempoEspera = random.nextInt(4) + 1;
                Thread.sleep(tiempoEspera*1000);
            } catch (InterruptedException e) { System.err.println(e.getMessage()); }
            
        }
    }
    
    public void añadirDetenido(String detenido) {
        this.detenidos.add(detenido);
    }
    
    public void verEstadisticas() {
        System.out.println("Han entrado: " + getEntradaDetenidos() + " detenidos.\nDetenido puestos en libertad: " + detenidosLibertad + "\nDetenidos puestos en prisión preventiva: " + detenidosPrision + "\n-----W");
    }

    public int getEntradaDetenidos() {
        return entradaDetenidos;
    }

    public void setEntradaDetenidos(int entradaDetenidos) {
        this.entradaDetenidos = entradaDetenidos;
    }
    
}
