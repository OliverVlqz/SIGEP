<%-- 
    Document   : listaUsuarios
    Created on : 19 ago. 2023, 20:37:47
    Author     : olive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuarios</title>
</head>
<body>
    <h2>Usuarios</h2>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Género</th>
        </tr>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td>${usuario.nombre}</td>
                <td>${usuario.correo}</td>
                <td>${usuario.genero}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

