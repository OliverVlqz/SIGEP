package servlets.comentario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Controladora;
import logica.Publicacion;
import logica.Usuario;

@WebServlet(name = "ResponderComentarioServlet", urlPatterns = {"/ResponderComentarioServlet"})
public class ResponderComentarioServlet extends HttpServlet {
    Controladora control = new Controladora();

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del comentario original y el contenido de la respuesta
       HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
        Long idComentarioOriginal = Long.parseLong(request.getParameter("idComentarioOriginal"));
        String contenidoRespuesta = request.getParameter("contenidoRespuesta");
        

        // Usar la controladora para crear la respuesta y vincularla al comentario original
        control.responderComentario(idComentarioOriginal, contenidoRespuesta,usuarioLogueado);

        // Redirigir a donde desees, por ejemplo, a una página de confirmación o de regreso al comentario original
        response.sendRedirect("VerPublicacionesServlet");
    }
}
