package persistencia;
import junit.framework.TestCase;
import junit.framework.Assert;
import logica.Comentario;
import org.junit.Test;

import javax.persistence.Persistence;

public class ComentarioJpaControllerTest extends TestCase {
    //Test para crear un comentario
    @Test
    public void testCreate() {
        ComentarioJpaController controller = new ComentarioJpaController(Persistence.createEntityManagerFactory("sigep"));
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setContenido("Buena foto amigo");
        nuevoComentario.setFecha(null);
        nuevoComentario.setPublicacion(null);
        nuevoComentario.setUsuario(null);
        try {
            controller.create(nuevoComentario);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
