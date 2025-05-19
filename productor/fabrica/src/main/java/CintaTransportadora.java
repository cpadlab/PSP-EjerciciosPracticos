/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadl
 */
public class CintaTransportadora {
    
    private static final int CAPACIDAD_MAXIMA = 5;
    private int piezasFabricadas = 0;
    private int piezasEnCinta = 0;
    
    public CintaTransportadora() {
        
    }
    
    public synchronized void agregarPieza() throws InterruptedException {
        
        String nombre = Thread.currentThread().getName();
        
        while (piezasEnCinta >= 5) {
            System.out.print("\nLa máquina ["+ nombre + "] esperando para agregar pieza.");
            wait();
        }
        
        piezasEnCinta++;
        piezasFabricadas++;
        
        System.out.println("\nLa máquina [" + nombre + "]  ha colocado una pieza en la cinta, ahora hay " + piezasEnCinta + " piezas en la cinta.");
        
        notifyAll();
        
    }
    
    public synchronized void sacarPieza(int piezasEmpaquetadas) throws InterruptedException {
        
        while (piezasEnCinta <= 0 ) {
            wait();
        }
        
        this.piezasEnCinta--;
        System.out.print("\nEmpaquetadora ha retirado una pieza. Piezas en cinta: " + piezasEnCinta + " | Total empaquetadas: " + piezasEmpaquetadas);
        notifyAll();
        
    }
    
    public int getPiezasFabricadas(){
        return piezasFabricadas;   
    }
    
}

