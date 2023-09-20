<%-- 
    Document   : UsuarioNav
    Created on : 22 ago. 2023, 14:59:29
    Author     : olive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <a class="nav-link" href="<%=request.getContextPath()%>/view/usuario/usuarioPublicar.jsp" style="color: white;"><i class="fas fa-search"></i> Publicar</a>
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
