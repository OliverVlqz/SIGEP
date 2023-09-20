<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar sesión</title>
</head>
<body>

<h2>Iniciar sesión</h2>

<% 
    // Mostramos el mensaje de error si hay alguno
    if(request.getAttribute("errorMessage") != null) {
%>
    <div style="color: red;">
        <%= request.getAttribute("errorMessage") %>
    </div>
<%
    }
%>

<form action="SvLogin" method="post">
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
    </div>
    <div>
        <label for="contrasenia">Contraseña:</label>
        <input type="password" id="contrasenia" name="contrasenia" required>
    </div>
    <div>
        <input type="submit" value="Iniciar sesión">
    </div>
</form>

</body>
</html>
