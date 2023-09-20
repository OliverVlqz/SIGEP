<%-- 
    Document   : registro
    Created on : 19 ago. 2023, 14:34:08
    Author     : olive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Formulario de Registro</h2>
    <form action="../SvUsuario" method="post">
        Nombre: <input type="text" name="nombre" required><br><br>
        Contraseña: <input type="password" name="contrasenia" required><br><br>
        Correo: <input type="email" name="correo" required><br><br>
        genero <input type="text" name="genero" required><br><br>
        Tipo de Usuario: <input type="text" name="tipoUsuario"><br><br>
        Número de Sanciones: <input type="number" name="numSanciones" value="0"><br><br>
        <input type="submit" value="Registrar">
    </form>
</body>
</html>
