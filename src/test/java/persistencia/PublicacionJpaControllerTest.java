package persistencia;
import junit.framework.TestCase;
import junit.framework.Assert;
import logica.Publicacion;

import org.junit.Test;

import javax.persistence.Persistence;

public class PublicacionJpaControllerTest extends TestCase {
    //Pruebas para crear una publicacion sin imagen
    @Test
    public void testCreate() {
        PublicacionJpaController controller = new PublicacionJpaController(Persistence.createEntityManagerFactory("sigep"));
        Publicacion nuevaPublicacion = new Publicacion();
        nuevaPublicacion.setTitulo("Soy Maximiliano");
        nuevaPublicacion.setContenido("soy el maximiliano");
        nuevaPublicacion.setFecha(null);
        nuevaPublicacion.setTipoPublicacion("Informal");
        nuevaPublicacion.setCategoria(null);
        nuevaPublicacion.setImagenData(null);

        try {
            controller.create(nuevaPublicacion);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
