<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="logica.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesion</title>
    <jsp:include page="../layouts/IniciarSesionEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
<h1><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png"></h1>
<h1>Inicio de sesion</h1>
<p>¿No tienes una cuenta? <a href="<%=request.getContextPath()%>/view/registro.jsp">Registrate</a></p>

<form action="../SvLogin" method="post">
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required>
    <label for="contrasenia">Contraseña:</label>
    <input type="password" id="contrasenia" name="contrasenia" required><br><br>
    <input type="submit" value="Iniciar sesión"><br>
</form>



</body>
<jsp:include page="../layouts/IniciarSesionEstiloFooter.jsp"/>
</html>
