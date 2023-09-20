<%@page import="logica.Publicacion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.Publicacion" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>

<!DOCTYPE html>
<html>
<head>
    <title>Ver Publicaciones</title>
</head>
<body>
    <h2>Publicaciones:</h2>

    <table border="1">
        <thead>
            <tr>
                <th>Título</th>
                <th>Contenido</th>
                <th>Imagen</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Publicacion> listaPublicaciones = (List<Publicacion>) request.getAttribute("listaPublicaciones");
                for(Publicacion pub : listaPublicaciones) {
            %>
            <tr>
                <td><%= pub.getTitulo() %></td>
                <td><%= pub.getContenido() %></td>
                <td>
                    <%
                        byte[] imagenData = pub.getImagenData();
                        if(imagenData != null) {
                            String encodedImage = Base64.encodeBase64String(imagenData);
                    %>
                            <img src="data:image/png;base64,<%= encodedImage %>" width="100" alt="Imagen de Publicación">
                    <%
                        }
                    %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

</body>
</html>
