<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.Publicacion" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Verificación de Publicaciones</title>
    <!-- Puedes agregar estilos o scripts aquí si es necesario -->
</head>
<body>
<% 
List<Publicacion> listaPublicaciones = (List<Publicacion>) session.getAttribute("listaPublicaciones");
%>

<h2>Verificación de Publicaciones</h2>

<table border="1">
    <thead>
        <tr>
            <th>Nombre de Usuario</th>
            <th>Título de la Publicación</th>
            <th>Fecha</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <% 

        for (Publicacion publi : listaPublicaciones) {
            if (!publi.isStatusVerificacion()) {
        %>
        <tr>
            <td><%= publi.getUsuario().getNombre() %></td>
            <td><%= publi.getTitulo() %></td>
            <td><%= publi.getFecha() %></td>
            <td>
                <form action="RutaDelServletParaAceptar" method="post">
                    <input type="hidden" name="publicacionId" value="<%= publi.getId() %>">
                    <input type="submit" value="Aceptar">
                </form>
                <form action="RutaDelServletParaRechazar" method="post">
                    <input type="hidden" name="publicacionId" value="<%= publi.getId() %>">
                    <input type="submit" value="Rechazar">
                </form>
            </td>
        </tr>
        <% 
            }
        } 
        %>
    </tbody>
</table>

</body>
</html>
