/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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


/**
 *
 * @author olive
 */
@WebServlet(name = "CrearComentarioServlet", urlPatterns = {"/CrearComentarioServlet"})
public class CrearComentarioServlet extends HttpServlet {
    Controladora control = new Controladora();
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
        Long publicacionId = Long.parseLong(request.getParameter("publicacionId"));
        Publicacion publicacion = control.buscarPublicacionPorId(publicacionId);

        // Obtener el contenido del comentario desde el formulario
        String contenido = request.getParameter("contenido");

        // Usar la controladora para crear el objeto comentario
        
        control.crearComentario(contenido, usuarioLogueado, publicacion);



        // Usar la controladora de persistencia para guardar el comentario en la base de datos
        

        // Redirigir a donde desees, por ejemplo, a una página de confirmación
        response.sendRedirect("VerPublicacionesServlet");
    }
}
