<%-- 
    Document   : agregarUsuario
    Created on : 19 ago. 2023, 20:38:29
    Author     : olive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Usuario</title>
</head>
<body>
    <h2>Agregar Usuario</h2>
    <form action="UsuarioServlet" method="post">
        Nombre: <input type="text" name="nombre"><br>
        Correo: <input type="text" name="correo"><br>
        Contraseña: <input type="password" name="contrasena"><br>
        Genero: <input type="text" name="genero"><br>
        <input type="submit" value="Agregar Usuario">
    </form>
</body>
</html>

