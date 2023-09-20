<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <title>SIGEP</title>
    <jsp:include page="layouts/IndexEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
<h1><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png"></h1>
<h1><%="Sistema de"%></h1><br>
<h1><%="Gestion de Publicaciones"%></h1>
<div class="container">
    <div class="button">
        <a href="<%=request.getContextPath()%>/view/registro.jsp" class="btn btn-1">Registro</a>
        <a href="<%=request.getContextPath()%>/view/iniciarSesion.jsp" class="btn btn-1">Iniciar Sesión</a>
    </div>
</div>
</body
<jsp:include page="layouts/IndexEstiloFooter.jsp"/>>
</html>