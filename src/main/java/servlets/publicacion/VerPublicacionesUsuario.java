/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.publicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Publicacion;

/**
 *
 * @author olive
 */
@WebServlet(name = "VerPublicacionesUsuario", urlPatterns = {"/VerPublicacionesUsuario"})
public class VerPublicacionesUsuario extends HttpServlet {

        Controladora control = new Controladora();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
            
               String usuarioIdParam = request.getParameter("usuarioId");
    
    if (usuarioIdParam != null && !usuarioIdParam.isEmpty()) {
        HttpSession session = request.getSession();
        session.setAttribute("usuarioParaPublicacionesId", usuarioIdParam);
    }
            
            List<Publicacion> listaPublicaciones = control.obtenerPublicaciones();
            

            // Guardar la lista de publicaciones en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("listaPublicaciones", listaPublicaciones);

            // Redireccionar al JSP usando sendRedirect
            response.sendRedirect("VerComentariosUsuario");
        }
    }


