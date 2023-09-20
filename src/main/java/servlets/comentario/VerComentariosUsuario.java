/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.comentario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Comentario;
import logica.Controladora;
import logica.Usuario;

/**
 *
 * @author olive
 */
@WebServlet(name = "VerComentariosUsuario", urlPatterns = {"/VerComentariosUsuario"})
public class VerComentariosUsuario extends HttpServlet {
    Controladora control = new Controladora();

    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
      
        
  
        // Obtener la lista de comentarios desde la controladora
        List<Comentario> listaComentarios = control.obtenerComentarios();

        // Establecer la lista de comentarios como un atributo de la sesión
        HttpSession sessionC = request.getSession();
        sessionC.setAttribute("listaComentarios", listaComentarios);
        
        // Redireccionar al JSP que mostrará los comentarios usando sendRedirect
        response.sendRedirect("view/admin/PublicacionesUsuario.jsp");
    }
}
