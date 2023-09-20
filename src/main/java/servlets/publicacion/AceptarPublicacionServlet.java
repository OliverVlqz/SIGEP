/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.publicacion;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Publicacion;
import persistencia.PublicacionJpaController;

/**
 *
 * @author olive
 */
@WebServlet("/AceptarPublicacionServlet")
public class AceptarPublicacionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la publicación desde el formulario
        Long publicacionId = Long.parseLong(request.getParameter("publicacionId"));

        // Crear una instancia del controlador JPA
        PublicacionJpaController publicacionController = new PublicacionJpaController();

        try {
            // Buscar la publicación por ID
            Publicacion publicacion = publicacionController.findPublicacion(publicacionId);
            
            if (publicacion != null) {
                // Cambiar el statusVerificacion a TRUE
                publicacion.setStatusVerificacion(true);
                
                // Actualizar la publicación
                publicacionController.edit(publicacion);
                
                // Redirigir a la misma página para actualizar la lista
                response.sendRedirect("VerPublicacionesVerificarServlet");
            } else {
                // Manejar el caso en el que la publicación no se encuentra
                response.getWriter().write("Publicación no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error al actualizar la publicación.");
        }
    }
}
