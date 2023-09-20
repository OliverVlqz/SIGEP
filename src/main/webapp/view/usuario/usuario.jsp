<%@page import="logica.Usuario"%>
<%@page import="logica.Comentario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="logica.Publicacion" %>
<%@ page import="java.util.List" %>

<html>
    <% 
   
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
%>
<head>
    <title>Publicaciones</title>
    <jsp:include page="/layouts/UsuarioPublicacionEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
<% 
List<Publicacion> listaPublicaciones = (List<Publicacion>) session.getAttribute("listaPublicaciones");
%>
 <%
    
       ;

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
    %>


<!-- [ ... Aquí va tu código del navbar ... ] -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#" style="color: white; font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;"><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png" style="width: 40px; height: 40px;">SIGEP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarScroll">
            <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/VerPublicacionesServlet" style="color: white;"><i class="fas fa-home" ></i> Pagina Principal</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/ModificarUsuarioServlet" style="color: white;"><i class="fas fa-user" ></i> Perfil</a>
                </li>
                <a class="nav-link" href="<%=request.getContextPath()%>/VerCategoriasServlet" style="color: white;"><i class="fas fa-search"></i> Publicar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link reg" href="<%=request.getContextPath()%>/CerrarSesionServlet" style="color: white;"><i class="fas fa-sign-out-alt" ></i> Cerrar sesión</a>
                </li>
            </ul>
            <ul class="navbar me-4 my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px; align-items:center;">
                <li class="nav-item" style="display: block; justify-content: right; align-items: left;">
                    <a class="nav-link reg" href="<%=request.getContextPath()%>/VerPublicacionesServlet" style="color: white;"><i class="fas fa-arrow-left" ></i></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<% for(Publicacion publi : listaPublicaciones) { 
if(publi.isStatusVerificacion()) { 
%>

<div class="cosas">
    <!-- Iteramos sobre cada publicación en la lista -->
    
    <div class="publis">
        <div class="usu">
            <!-- Mostramos el nombre del usuario asociado a la publicación -->
            <i class="fas fa-user"> <%= publi.getUsuario().getNombre() %></i>
        </div>
        <div class="hr">
            <!-- Mostramos tipoPublicacion, categoría y fecha de la publicación -->
            <h6><%= publi.getTipoPublicacion() %></h6>
            <h6 class="lahr"><%= publi.getCategoria().getTitulo() %></h6>
            <h6 class="lahr"><%= publi.getFecha() %></h6>
        </div>
        <div class="lapubli">
            <!-- Mostramos el título y el contenido de la publicación -->
            <strong><%= publi.getTitulo() %></strong>
            <p><%= publi.getContenido() %></p>
            <input type="hidden" name="publicacionId" value="<%= publi.getId() %>">
            
            <% if(publi.getImagenData() != null && publi.getImagenData().length > 0) { %>
                <!-- Mostramos la imagen de la publicación si existe -->
                <div class="laimgpublicacion">
                    <img class="laimgpublicacion" src="data:image/jpeg;base64,<%= org.apache.commons.codec.binary.Base64.encodeBase64String(publi.getImagenData()) %>">
                </div>
            <% } %>
        </div> 
    </div>
        <!-- Sección de comentarios -->
      <div class="palcom">
    <div class="com">
        <div class="lscom" id="comments">
            <div class="title">
                <h3 style="text-align: center;">Deja un comentario:</h3>
            </div>

            <% 
            List<Comentario> comentariosDePublicacion = publi.getComentarios();
            if(comentariosDePublicacion != null && !comentariosDePublicacion.isEmpty()) {
                for (Comentario comentario : comentariosDePublicacion) {
            %>
            <div class="single-comment">
                <strong>Usuario:</strong> <%= comentario.getUsuario().getNombre() %> - <strong>Fecha:</strong> <%= comentario.getFecha() %> - <strong>Contenido:</strong> <%= comentario.getContenido() %>
                
                <!-- Formulario para responder al comentario -->
                <form action="../../ResponderComentarioServlet" method="POST">
                    <input type="hidden" name="idComentarioOriginal" value="<%= comentario.getId() %>">
                    <textarea name="contenidoRespuesta" class="responseTextarea" placeholder="Responder a este comentario..." style="height: 5%; width: 100%" required></textarea>
                    <br>
                    <input type="submit" value="Responder" style="">

                </form>

                <!-- Sección para mostrar las respuestas del comentario -->
                <div class="responses">
                    <% 
                    List<Comentario> respuestas = comentario.getRespuestas();
                    if(respuestas != null && !respuestas.isEmpty()) {
                        for(Comentario respuesta : respuestas) {
                    %>
                    <div class="single-response">
                        <strong>Usuario:</strong> <%= respuesta.getUsuario().getNombre() %> - <strong>Fecha:</strong> <%= respuesta.getFecha() %> - <strong>Contenido:</strong> <%= respuesta.getContenido() %>
                    </div>
                    <% 
                        }
                    }
                    %>
                </div>
                
            </div>
            <% 
                }
            } else {
            %>
            <div class="no-comments">No hay comentarios anteriores.</div>
            <% 
            }
            %>
        </div>
    </div>
    
    <!-- Aquí comienza el formulario de comentario -->
   <div class="brcom" style="height: 8%; padding-left: 0;">
    <form action="../../CrearComentarioServlet" method="POST" style="margin-left: 0; padding-left: 0; display: flex; ">
        <!-- Campo oculto para el ID de la publicación -->
        <input type="hidden" name="publicacionId" value="<%= publi.getId() %>">
        <textarea name="contenido" id="commentContent" style="  margin-left:1%; height:150%; width: 350px   " placeholder="Escribe tu comentario" required=""></textarea>
        <input type="submit" value="Comentar"style= "height: 150%; margin-right: 1%; align: right;" >
    </form>
</div>

    <!-- Aquí finaliza el formulario de comentario -->
</div>



                        <br>
              </div>     
    <% } %>
    <% } %>
    
    <jsp:include page="/layouts/UsuarioPublicacionEstiloFooter.jsp"/>
</body>
</html>
