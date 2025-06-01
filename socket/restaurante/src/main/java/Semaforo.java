/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Semaforo {
    
    public static final int AFORO_MAXIMO = 25;
    
    private int reservaSabado = 0;
    private int reservaDomingo = 0;
    
    public int getReservaDomingo() {
        return this.reservaDomingo;
    }
    
    public int getReservaSabado() {
        return this.reservaSabado;
    }
    
    public synchronized void reservar(String dia, int numero) {
        if ("Sábado".equals(dia)) { reservaSabado += numero; } 
        else { reservaDomingo += numero; }
    }
    
}
