
/**
 *
 * @author Carlos Padilla
 */
public class Paginas {
    
    public static String errorPage = "<html><head><title>404 Error</title></head><body>"
        + "<h1>404 - Página no encontrada</h1>"
        + "<a href='/'>Volver al inicio</a>"
        + "</body></html>";
    
    public static String indexPage(String respuesta) {
        return "<html><head><title>Farmacia</title></head><body>"
        + "<h1>¡Bienvenidos a nuestra Farmacia!</h1>"
        + "<form action='/' method='post'>"
        + "<div>"
        + "<label for='producto'>Producto:</label>"
        + "<select name='producto' id='producto'>"
        + "<option value='Paracetamol'>Paracetamol</option>"
        + "<option value='Ibuprofeno'>Ibuprofeno</option>"
        + "<option value='Vitamina C'>Vitamina C</option>"
        + "</select>"
        + "</div>"
        + "<div>"
        + "<label for='cantidad'>Cantidad:</label>"
        + "<input type='number' name='cantidad' id='cantidad'>"
        + "</div>"
        + "<button type='submit'>Comprar</button>"
        + "</form>"
        + respuesta
        + "</body></html>";
    }
    
}
