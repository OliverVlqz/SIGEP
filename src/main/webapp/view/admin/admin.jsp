<%@page import="logica.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="logica.Publicacion" %>
<%@ page import="java.util.List" %>
<html>
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

<head>
    <title>Publicaciones y Listado de verificacion</title>
    <jsp:include page="/layouts/AdministradorPublicacionListadoHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
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
<div class="cate">
<br>
    <div class="cosas">
        <div class="latabla" align="center">
            <table class="table table-hover" style="width: 35%;">
                <h1 align="center">Listado de verficación</h1>
                <thead>
                <title>Publicaciones Formales</title>
                <tr>
                    <th scope="col">Usuario</th>
                    <th scope="col">Publicacion</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Acciones</th>
                </tr>
                </thead>
                <tbody>
                <% 
                
                List<Publicacion> listaPublicaciones = (List<Publicacion>) session.getAttribute("listaPublicacionesVerificar");

                for (Publicacion publi : listaPublicaciones) {
                    if (!publi.isStatusVerificacion()) {
                %>
                <tr>
                    <td><%= publi.getUsuario().getNombre() %></td>
                    <td><%= publi.getTitulo() %></td>
                    <td><%= publi.getFecha() %></td>
                    <td>
                        <form action="../../AceptarPublicacionServlet" method="post">
                            <input type="hidden" name="publicacionId" value="<%= publi.getId() %>">
                            <button class="btns" type="submit" id="aceptar"><i class="fas fa-check"></i></button>
                        </form>
                        <form action="../../RechazarPublicacionServlet" method="post">
                            <input type="hidden" name="publicacionId" value="<%= publi.getId() %>">
                            <button class="btns" type="submit" id="rechazar"><i class="fas fa-times"></i></button>
                        </form>
                    </td>
                </tr>
                <% 
                    }
                } 
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/layouts/AdministradorPublicacionListadoFooter.jsp"/>
</body>
<%   }%>
</html>
