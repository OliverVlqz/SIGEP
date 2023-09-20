package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CerrarSesionServlet", urlPatterns = {"/CerrarSesionServlet"})
public class CerrarSesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la sesión actual
        HttpSession session = request.getSession(false); // pasar 'false' para no crear una nueva sesión si no hay una existente

        if (session != null) {
            // Invalidar la sesión
            session.invalidate();
        }

        // Redirigir al usuario a la página de inicio o página de inicio de sesión
        response.sendRedirect(request.getContextPath() + "/index.jsp"); 
    }
}
