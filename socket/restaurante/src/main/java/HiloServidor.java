
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cpadlab
 */
public class HiloServidor extends Thread {
    
    private Socket cliente;
    private Semaforo semaforo;
    
    private BufferedReader lectura;
    private PrintWriter escritura;
    
    public HiloServidor(Socket cliente, Semaforo semaforo) {
        this.cliente = cliente;
        this.semaforo = semaforo;
    }
    
    @Override
    public void run() {
        
        System.out.println("Cliente conectado");
        
        try {
            
            lectura = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escritura = new PrintWriter(cliente.getOutputStream(), true);
            
            escritura.println("¿Para qué día quiere reservar?");
            String dia = lectura.readLine();
            if (!"Sábado".equals(dia) && !"Domingo".equals(dia)) {
                System.out.println("El dia es incorrecto");
                cerrarConexion(); Thread.currentThread().interrupt();
                return;
            }
            
            escritura.println("¿Para cuantos comensales?");
            int numComensales = Integer.parseInt(lectura.readLine());
            
            if ("Sábado".equals(dia)) {
                
                int reservasSabado = semaforo.getReservaSabado();
                System.out.print("reservasSabado: " + reservasSabado);
                
                if (reservasSabado + numComensales <= semaforo.AFORO_MAXIMO) {
                    
                    semaforo.reservar(dia, numComensales);
                    System.out.println("\n-----\nReserva Confirmada.\nCliente: " + Thread.currentThread().getName() + "\nDía: " + dia + "\nNúmero de comensales: " + numComensales);
                    escritura.println("Reserva Confirmada.");
                    
                } else {
                    
                    escritura.println("Reserva cancelada por falta de aforo.");
                    System.out.println("\n-----\nReserva cancelada por falta de aforo.\nCliente: " + Thread.currentThread().getName() + "\nDía: " + dia + "\nNúmero de comensales: " + numComensales);
                    
                }
                
            } else {
                
                int reservasDomingo = semaforo.getReservaDomingo();
                System.out.print("reservasDomingo: " + reservasDomingo);
                        
                if (reservasDomingo + numComensales <= semaforo.AFORO_MAXIMO) {
                    
                    semaforo.reservar(dia, numComensales);
                    System.out.println("\n-----\nReserva Confirmada.\nCliente: " + Thread.currentThread().getName() + "\nDía: " + dia + "\nNúmero de comensales: " + numComensales);
                    escritura.println("Reserva Confirmada.");
                    
                } else {
                    
                    escritura.println("Reserva cancelada por falta de aforo.");
                    System.out.println("\n-----\nReserva cancelada por falta de aforo.\nCliente: " + Thread.currentThread().getName() + "\nDía: " + dia + "\nNúmero de comensales: " + numComensales);
                    
                }

            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try { cerrarConexion(); Thread.currentThread().interrupt(); }
            catch (IOException e) { System.err.println(e.getMessage()); }
            
        }
        
    }
    
    private void cerrarConexion() throws IOException {
        cliente.close();
        System.out.println("Conexión con el cliente cerrada");
    }
    
}
