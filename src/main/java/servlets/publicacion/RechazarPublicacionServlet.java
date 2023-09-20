/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.publicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.PublicacionJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author olive
 */
@WebServlet("/RechazarPublicacionServlet")
public class RechazarPublicacionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la publicación desde el formulario
        Long publicacionId = Long.parseLong(request.getParameter("publicacionId"));

        // Crear una instancia del controlador JPA
        PublicacionJpaController publicacionController = new PublicacionJpaController();

      
        try {
            // Eliminar la publicación por ID
            publicacionController.destroy(publicacionId);
              response.sendRedirect("VerPublicacionesVerificarServlet");
       
        } catch (logica.exceptions.NonexistentEntityException ex) {
            Logger.getLogger(RechazarPublicacionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            // Redirigir a la misma página para actualizar la lista
          
    }
}
