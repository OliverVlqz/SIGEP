<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="logica.Comentario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Comentario</title>
    <!-- Puedes agregar estilos CSS o scripts JS si lo deseas -->
</head>
<body>
<% 
List<Comentario> listaComentarios = (List<Comentario>) session.getAttribute("listaComentarios");
%>

    <h2>Crear Comentario</h2>
    
    <form action="CrearComentarioServlet" method="POST">
        <label for="contenido">Comentario:</label><br>
        <textarea name="contenido" id="contenido" rows="4" cols="50" required></textarea><br><br>
        
        <input type="submit" value="Enviar Comentario">
    </form>

    <!-- Sección para mostrar los comentarios -->
    <h3>Comentarios anteriores:</h3>
    <ul>
        <% 
        if(listaComentarios != null && !listaComentarios.isEmpty()) {
            for (Comentario comentario : listaComentarios) {
        %>
                <li>
                    <strong>Usuario:</strong> <%= comentario.getUsuario().getNombre() %> - <strong>Fecha:</strong> <%= comentario.getFecha() %> - <strong>Contenido:</strong> <%= comentario.getContenido() %>
                    //Seccion para contestar comentarios
                    <form action="ResponderComentarioServlet" method="POST">
                        <input type="hidden" name="idComentarioOriginal" value="<%= comentario.getId() %>">
                        <label for="contenidoRespuesta">Responder:</label><br>
                        <textarea name="contenidoRespuesta" id="contenidoRespuesta" rows="2" cols="40" required></textarea><br>
                        <input type="submit" value="Responder">
                    </form>
                    <!-- Sección para mostrar las respuestas del comentario -->
                    <ul>
                        
                        <% 
                        List<Comentario> respuestas = comentario.getRespuestas();
                        if(respuestas != null && !respuestas.isEmpty()) {
                            for(Comentario respuesta : respuestas) {
                        %>
                            <li>
                                <strong>Usuario:</strong> <%= respuesta.getUsuario().getNombre() %> - <strong>Fecha:</strong> <%= respuesta.getFecha() %> - <strong>Contenido:</strong> <%= respuesta.getContenido() %>
                            </li>
                        <% 
                            }
                        }
                        %>
                    </ul>
                </li>
        <% 
            }
        } else {
        %>
            <li>No hay comentarios anteriores.</li>
        <% 
        }
        %>
    </ul>

</body>
</html>

