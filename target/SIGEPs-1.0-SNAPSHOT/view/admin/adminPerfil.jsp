
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@page import="logica.Usuario"%>
<%@page import="logica.Comentario"%>
<%@page import="java.util.List"%>
<%@page import="logica.Publicacion"%>
<%@ page import="logica.Usuario" %>


<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");







%> 
 <% 
   
       

       
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
<html>
<head>
    <title>PerfilUsuario</title>
     
    <jsp:include page="../../layouts/PerfilUsuarioEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">>
   
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
           
<br>
<div class="row g-3">
   <form class="row g-3" action="/SIGEPs/ModificarUsuarioServlet" method="post">
       
    <div class="col-md-6">
        
     
        <h1><img src="<%=request.getContextPath()%>/assets/img/user.png"></h1>
        <h1 style="color: white;"><%= usuario.getNombre() %></h1>
    </div>
    <div class="col-md-6">
        <label for="nombre">Nombre:</label><br>
        <input type="text" id="nombre" name="nombre" value="<%= usuario.getNombre() %>" placeholder="Ingresa el nuevo nombre"><br>
        
        <label for="email">Correo electrónico:</label><br>
        <input type="email" id="email" name="correo" value="<%= usuario.getCorreo() %>" placeholder="Ingresa el nuevo correo electronico">
        
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasenia" value="<%= usuario.getContrasenia() %>" placeholder="Ingresa la nueva contraseña"><br>
        
        <label for="genero">Sexo:</label><br>
        <select id="genero" name="genero">
            <option value="masculino" <%= "masculino".equals(usuario.getGenero()) ? "selected" : "" %>>Masculino</option>
            <option value="femenino" <%= "femenino".equals(usuario.getGenero()) ? "selected" : "" %>>Femenino</option>
            <option value="otro" <%= "otro".equals(usuario.getGenero()) ? "selected" : "" %>>Prefiero no decirlo</option>
        </select>
    </div>
        <div  class="row g-3">
    <button type="submit" class="btn btn-primary">Actualizar</button>
</div>

  </form>
          </div>  



        </div>  
        
        <br>    
 <jsp:include page="/layouts/PerfilUsuarioEstiloFooter.jsp"/>

      
        


</body>
<% }%>
</html>
