package servlets.publicacion;

import logica.Controladora;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.servlet.http.HttpSession;
import logica.Categoria;
import logica.Usuario;

@WebServlet(name = "CrearPublicacionServlet", urlPatterns = {"/CrearPublicacionServlet"})
@MultipartConfig(maxFileSize = 16177215)  // Carga de archivos hasta 16MB
public class CrearPublicacionServlet extends HttpServlet {
    Controladora control = new Controladora();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }


        
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        Date fecha = new Date(); // Suponemos que la fecha se establece como la fecha actual
        String tipoPublicacion = request.getParameter("tipoPublicacion");
        int reaccion = Integer.parseInt(request.getParameter("reaccion"));
        Long idCategoria = Long.parseLong(request.getParameter("categoriaSeleccionada"));
        Categoria categoriaSeleccionada = control.obtenerCategoriaPorId(idCategoria);


        boolean statusVerificacion = "informal".equals(tipoPublicacion);
        Part filePart = request.getPart("imagen");
        InputStream imagenInput = null;
        
        if (filePart != null && filePart.getSize() > 0) {
            imagenInput = filePart.getInputStream();
        }
        // Crear el objeto Publicacion usando la Controladora
        
        control.crearPublicacion(titulo, contenido,fecha,tipoPublicacion,reaccion,statusVerificacion, imagenInput, usuarioLogueado, categoriaSeleccionada);

        response.sendRedirect("VerPublicacionesServlet"); // Redirige al usuario a la página donde puede ver sus publicaciones
    }
}
