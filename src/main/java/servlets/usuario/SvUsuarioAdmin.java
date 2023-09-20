/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;

/**
 *
 * @author olive
 */
@WebServlet(name = "SvUsuarioAdmin", urlPatterns = {"/SvUsuarioAdmin"})
public class SvUsuarioAdmin extends HttpServlet {
 Controladora control = new Controladora();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String nombre = request.getParameter("nombre");
        String contrasenia = request.getParameter("contrasenia");
        String correo = request.getParameter("correo");
        String genero = request.getParameter("genero");
        String tipoUsuario = request.getParameter("tipoUsuario");
        String numSanciones = request.getParameter("numSanciones");
         try{
        control.crearUsuario(nombre,contrasenia,correo,genero,tipoUsuario,numSanciones );
         response.sendRedirect("view/admin/admin.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Redireccionamos a una página de error o mostramos el error al usuario
            response.sendRedirect("error.jsp");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}