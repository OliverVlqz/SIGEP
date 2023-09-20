    package servlets.publicacion;

    import logica.Controladora;
    import logica.Publicacion;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;
    import java.io.IOException;
    import java.util.List;
import logica.Usuario;

  @WebServlet(name = "VerPublicacionesVerificarServlet", urlPatterns = {"/VerPublicacionesVerificarServlet"})
public class VerPublicacionesVerificarServlet extends HttpServlet {
    Controladora control = new Controladora();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
        List<Publicacion> listaPublicaciones = control.obtenerPublicaciones();
    
        // Guardar la lista de publicaciones en la sesión con el nombre "listaPublicacionesVerificar"
        session.setAttribute("listaPublicacionesVerificar", listaPublicaciones);

        // Redireccionar al JSP usando sendRedirect
        response.sendRedirect("view/admin/admin.jsp");
    }
}
    