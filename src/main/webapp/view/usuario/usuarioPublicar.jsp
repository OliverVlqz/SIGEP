    <%@page import="logica.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Publicar</title>
    <jsp:include page="../../layouts/PublicarEstiloHeader.jsp"/>
</head>
<body>
    <%
        
         
   
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
%>
    
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect("/SIGEPs/view/iniciarSesion.jsp");
            return;
        }
    %>
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
  
  
<div class="cosas">
    
   
    <div class="publi"  >
        <h1>Crear Publicación</h1>
        <form action="../../CrearPublicacionServlet" method="post" enctype="multipart/form-data"> 
            <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" style="width: 100%" required ><br>
        <label for="titulo">Contenido:</label> <br>         
            <textarea id="contenido" name="contenido" placeholder="Escribe tu texto aquí..." rows="5" cols="30" required></textarea><br><br>
                
        <input type="file" id="imageInput" name="imagen" accept="image/*">
        
           
          <input type="hidden" id="reaccion" name="reaccion" value="0">
            <label for="titulo">Tipo de publicacion:</label>  
          <select name="tipoPublicacion" id="styleSelect">
            <option value="formal">Formal</option>
            <option value="informal">Informal</option>
        </select>
         <label for="categorias">Selecciona una categoría:</label>
                <select name="categoriaSeleccionada" id="categorias">
                    <c:forEach items="${listaCategorias}" var="categoria">
                        <option value="${categoria.id}">${categoria.titulo}</option>
                    </c:forEach>
                </select><br>
                  
         <input type="submit" id="publishButton" value="Publicar">
          
           </form>
    </div>
   
    <div class="cate">
        
        

       <form action="../../CrearCategoriaServlet" method="post">
            <label for="nuevaCategoria">Crear tu propia categoria:</label>
            <input type="text" id="nuevaCategoria" name="nombreCategoria" required><br><br>
            <button type="submit">Agregar</button>
        </form>
    </div>
</div>
<jsp:include page="../../layouts/PublicarEstiloFooter.jsp"/>
</body>
</html>
