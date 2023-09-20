package servlets.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;
import persistencia.UsuarioJpaController;

/**
 *
 * @author olive
 */
@WebServlet("/ModificarUsuarioAdminServlet")
public class ModificarUsuarioAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return; 
        }

        // Redireccionar al JSP para editar el usuario
        response.sendRedirect("/SIGEPs/view/admin/adminPerfil.jsp");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }

        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        String genero = request.getParameter("genero");

        // Actualizar el objeto usuario con los nuevos datos
        usuarioLogueado.setNombre(nombre);
        usuarioLogueado.setCorreo(correo);
        usuarioLogueado.setContrasenia(contrasenia);
        usuarioLogueado.setGenero(genero);

        // Usar el JPA controller para guardar los cambios en la base de datos
        UsuarioJpaController controller = new UsuarioJpaController();
        try {
            controller.edit(usuarioLogueado);
            session.setAttribute("usuarioLogueado", usuarioLogueado);  // Actualizar el usuario en la sesión
            response.sendRedirect("/SIGEPs/view/admin/adminPerfil.jsp");
        } catch (Exception e) {
            // Handle exception, por ejemplo, redireccionar a una página de error
            response.sendRedirect("/SIGEPs/view/error.jsp");
        }
    }
}
