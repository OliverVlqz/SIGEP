    <%@page import="java.util.List"%>
    <%@page import="logica.Usuario"%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Visualizacion de usuarios</title>
        <jsp:include page="/layouts/VerUsuariosEstiloHeader.jsp"/>
    </head>
  
     <% 
   
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

       
%>
    
    <% 
// Capturar el tipo de usuario logueado desde la sesión




if (usuarioLogueado == null) {
    response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
    return;
}
else{
String tipoUsuario = usuarioLogueado.getTipoUsuario();
if (!"Administrador".equals(tipoUsuario) && !"SuperAdministrador".equals(tipoUsuario)) {

    response.sendRedirect("../../CerrarSesionServlet"); // Puedes redirigir a una página de permiso denegado o cualquier otra página que desees.
    return;

    
        }
  
%>
    <body>
    <!-- ... (El resto del código HTML previo a la tabla permanece igual) ... -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid" >
            <a class="navbar-brand" href="#" style="color: white; font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;"><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png" style="width: 40px; height: 40px;">SIGEP</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <!-- ... (Otros enlaces) ... -->
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/VerPublicacionesVerificarServlet " style="color: white;"><i class="fas fa-home" ></i> Pagina Principal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/ModificarUsuarioAdminServlet" style="color: white;"><i class="fas fa-user" ></i> Perfil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/SvUsuario" style="color: white;"><i class="fas fa-search"></i> Ver usuarios</a>
                    </li>

                   <% if("SuperAdministrador".equals(tipoUsuario)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/SIGEPs/view/admin/superAdminAnadirAdmin.jsp"  style="color: white;"><i class="fas fa-search"></i> Añadir Administrador</a>
                </li>
                <% } %>

                    <li class="nav-item">
                        <a class="nav-link reg" href="<%=request.getContextPath()%>/CerrarSesionServlet" style="color: white;"><i class="fas fa-sign-out-alt" ></i> Cerrar sesión</a>
                    </li>
                </ul>

                <ul class="navbar me-4 my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px; align-items:center;">
                    <li class="nav-item" style="display: block; justify-content: right; align-items: left;">
                        <a class="nav-link reg" href="" style="color: white;"><i class="fas fa-arrow-left" ></i></a>
                    </li>
                </ul>    
            </div>
        </div>
    </nav>  

    <div class="cosas">
        <div class="latabla">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Usuario</th>
                    <th scope="col">Correo</th>
                    <th scope="col">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <% 
                    // Obtener la lista de usuarios desde la sesión usando el nombre "listaUsuarios"

                    List<Usuario> usuarios = (List<Usuario>) session.getAttribute("listaUsuarios");

                    for (Usuario usuario : usuarios) {
                %>
                <tr>
                    <td><%= usuario.getNombre() %></td>
                    <td><%= usuario.getCorreo() %></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/EliminarUsuarioServlet" method="post">
                     <input type="hidden" name="usuarioId" value="<%= usuario.getId() %>">
                    <button type="submit" class="btns"><i class="fas fa-ban"></i></button>
</form>
                    <form action="<%=request.getContextPath()%>/VerPublicacionesUsuario" method="GET">
    <input type="hidden" name="usuarioId" value="<%= usuario.getId() %>">
    <button type="submit" class="btns"><i class="fas fa-eye"></i></button>
</form>


                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <!-- ... (El resto del código para la búsqueda y demás elementos sigue igual) ... -->
    </div>
    <jsp:include page="/layouts/VerUsuariosEstiloHeader.jsp"/>
    </body>
    <% } %>
    </html>
