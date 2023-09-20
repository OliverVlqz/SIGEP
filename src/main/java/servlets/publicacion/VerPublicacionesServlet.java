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

    @WebServlet(name = "VerPublicacionesServlet", urlPatterns = {"/VerPublicacionesServlet"})
    public class VerPublicacionesServlet extends HttpServlet {
        Controladora control = new Controladora();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Publicacion> listaPublicaciones = control.obtenerPublicaciones();
            

            // Guardar la lista de publicaciones en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("listaPublicaciones", listaPublicaciones);

            // Redireccionar al JSP usando sendRedirect
            response.sendRedirect("VerComentariosServlet");
        }
    }
