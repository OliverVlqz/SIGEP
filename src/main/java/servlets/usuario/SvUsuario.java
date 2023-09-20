/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuario;

/**
 *
 * @author olive
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

   Controladora control = new Controladora();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         List<Usuario> usuarios = new ArrayList <Usuario>();
        usuarios= control.getUsuarios();
        
        HttpSession misession = request.getSession();
        misession.setAttribute("listaUsuarios", usuarios);
        
        response.sendRedirect("view/admin/adminVerUsuario.jsp");
        
        
    }

    
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
         response.sendRedirect("view/iniciarSesion.jsp");
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
