package servlets.categoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import logica.Categoria;
import logica.Controladora;
import logica.Usuario;

@WebServlet(name = "VerCategoriasServlet", urlPatterns = {"/VerCategoriasServlet"})
public class VerCategoriasServlet extends HttpServlet {
    
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controladora control = new Controladora();
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
        
        // Obtener la lista de todas las categorías
        List<Categoria> listaCategorias = control.obtenerCategorias();
        
        // Establecer la lista de categorías como un atributo de la sesión
        session.setAttribute("listaCategorias", listaCategorias);
        
        // Redireccionar al JSP que mostrará las categorías usando sendRedirect
        response.sendRedirect("/SIGEPs/view/usuario/usuarioPublicar.jsp");
    }
}
