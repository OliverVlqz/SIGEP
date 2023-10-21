package persistencia;

import junit.framework.TestCase;
import junit.framework.Assert;
import logica.Usuario;
import org.junit.Test;

import javax.persistence.Persistence;

public class UsuarioJpaControllerTest extends TestCase{
    //Test Para crear un usuario normal
    @Test
public void testCreate() {
        UsuarioJpaController controller = new UsuarioJpaController(Persistence.createEntityManagerFactory("sigep"));
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Maximiliano");
        nuevoUsuario.setContrasenia("vegetta77");
        nuevoUsuario.setCorreo("max123@gmail.com");
        nuevoUsuario.setTipoUsuario("Usuario");
        nuevoUsuario.setGenero("Masculino");
        nuevoUsuario.setNumSanciones(0);
        try {
            controller.create(nuevoUsuario);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    //Test para crear un usuario administrador
    @Test
    public void testCreate2() {
UsuarioJpaController controller = new UsuarioJpaController(Persistence.createEntityManagerFactory("sigep"));
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Maxi");
        nuevoUsuario.setContrasenia("543");
        nuevoUsuario.setCorreo("maxc@gmail.com");
        nuevoUsuario.setTipoUsuario("Administrador");
        nuevoUsuario.setGenero("Masculino");
        nuevoUsuario.setNumSanciones(0);
        try {
            controller.create(nuevoUsuario);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    //Test para eliminar un usuario
    @Test
    public void testDestroy() {
UsuarioJpaController controller = new UsuarioJpaController(Persistence.createEntityManagerFactory("sigep"));
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("PruebaEliminar");
        nuevoUsuario.setContrasenia("eliminar");
        nuevoUsuario.setCorreo("pruebaeliminar@gmail.com");
        nuevoUsuario.setTipoUsuario("Usuario");
        nuevoUsuario.setGenero("Masculino");
        nuevoUsuario.setNumSanciones(0);
        try {
            controller.destroy(nuevoUsuario.getId());
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }


    }
}
