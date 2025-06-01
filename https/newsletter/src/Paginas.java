/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cpadlab
 */
public class Paginas {
    
    public static final String html404 = "<html><head><title>Error 404</title></head><body>"
        + "<h1>404 Página No Encontrada</h1>"
        + "<p>La página solicitada no existe.</p>"
        + "</body></html>";
    
    public static String htmlIndex(String respuesta) {
        return "<html><head><title>NewsLetter</title></head>"
            + "<body>"
            + "<h1>NewsLetter</h1>"
            + "<form action='/' method='post'>"
            + "<div>"
            + "<label for='#nombre'>Nombre: </label>"
            + "<input type='text' id='nombre' name='nombre'>"
            + "</div>"
            + "<div>"
            + "<label for='#email'>E-mail: </label>"
            + "<input type='email' id='email' name='email'>"
            + "</div>"
            + "<button type='submit'>Subscribirse</button>"
            + "</form>"
            + respuesta
            + "</body></html>";
    }
    
}
