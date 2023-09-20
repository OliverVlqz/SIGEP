package servlets.usuario;

import logica.Usuario;
import persistencia.UsuarioJpaController;
import java.io.IOException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistroUsuarioServlet", urlPatterns = {"/registroUsuario"})
public class RegistroUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Recogemos los datos del formulario
        String nombre = request.getParameter("nombre");
        String contrasenia = request.getParameter("contrasenia");
        String correo = request.getParameter("correo");
        String tipoUsuario = request.getParameter("tipoUsuario");
        String numSanciones = request.getParameter("numSanciones");
        
        // Creamos el objeto usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setContrasenia(contrasenia); // Considera encriptar la contraseña antes de guardarla
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setTipoUsuario(tipoUsuario);
        nuevoUsuario.setNumSanciones(Integer.parseInt(numSanciones));
        
        try {
            // Creamos una instancia del JPA controller
            UsuarioJpaController controller = new UsuarioJpaController(Persistence.createEntityManagerFactory("sigep"));
            
            // Guardamos el nuevo usuario en la base de datos
            controller.create(nuevoUsuario);
            
            // Redireccionamos a la página de éxito o inicio de sesión
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Redireccionamos a una página de error o mostramos el error al usuario
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("registro.jsp"); // Redirige al formulario de registro si se intenta acceder con GET
    }
}
