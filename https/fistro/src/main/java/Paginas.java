/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Paginas {
    
    public static String errorPage = "<html><head><title>404 Error</title></head><body>"
        + "<h1>404 - Página no encontrada</h1>"
        + "<a href='/'>Volver al inicio</a>"
        + "</body></html>";
    
    public static String indexPage(String response) {
        return "<html><head><title>Fistro Servidor</title></head><body>"
            + "<h1>¡Bienvenidio a Chiquito Wiki!</h1>"
            + "<h2>Pulsa el botón para obtener una frase</h2>"
            + "<form action='/' method='post'>"
            + "<button type='submit'>¡Al ataquer!</button>"
            + "</form>"
            + response
            + "</body></html>";
    }
    
}
