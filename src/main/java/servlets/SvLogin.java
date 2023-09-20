package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    Controladora control = new Controladora();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usuario = control.checkLogin(nombre, contrasenia);

        if (usuario != null) {
            // Credenciales correctas
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);
            
            // Redirige según el tipo de usuario
            String tipo = usuario.getTipoUsuario();
            switch (tipo) {
                case "Usuario":
                    response.sendRedirect("VerPublicacionesServlet");
                    break;
                case "Administrador":
                    response.sendRedirect("VerPublicacionesVerificarServlet");
                    break;
                case "SuperAdministrador":
                    response.sendRedirect("VerPublicacionesVerificarServlet");
                    break;
                default:
                    // Por seguridad, si no hay coincidencia, redirige al inicio de sesión
                    response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
                    break;
            }

        } else {
            // Credenciales incorrectas
            request.setAttribute("errorMessage", "Nombre de usuario o contraseña incorrecta.");
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
           }
    }
}
