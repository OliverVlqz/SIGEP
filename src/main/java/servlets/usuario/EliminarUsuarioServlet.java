package servlets.usuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.UsuarioJpaController;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author olive
 */
@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
UsuarioJpaController usuarioController = new UsuarioJpaController();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el ID del usuario a eliminar del formulario
        Long usuarioId = Long.parseLong(request.getParameter("usuarioId"));

 
    try {
        // Instanciamos el JPA Controller
        
        
        // Eliminamos el usuario
        usuarioController.destroy(usuarioId);
         // Redirigimos de vuelta a la página de visualización de usuarios con un mensaje de éxito.
                     request.getSession().setAttribute("mensaje", "Usuario eliminado exitosamente.");
                    response.sendRedirect(request.getContextPath() + "/SvUsuario");
    } catch (logica.exceptions.NonexistentEntityException ex) {
             // En caso de que el usuario no exista, podemos manejar el error aquí.
            request.getSession().setAttribute("error", "Error al eliminar usuario.");
            response.sendRedirect(request.getContextPath() + "/SvUsuario");
    }
                 
           
          
          
      
          
        }
    }

