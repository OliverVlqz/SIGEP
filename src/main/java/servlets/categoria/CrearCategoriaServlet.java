package servlets.categoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import logica.Categoria;
import logica.Controladora;
import persistencia.CategoriaJpaController;

@WebServlet(name = "CrearCategoriaServlet", urlPatterns = {"/CrearCategoriaServlet"})
public class CrearCategoriaServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controladora control = new Controladora();
        String nombreCategoria = request.getParameter("nombreCategoria");
         control.crearCategoria(nombreCategoria);
       
       response.sendRedirect("VerCategoriasServlet");
        
        
    }   
}
