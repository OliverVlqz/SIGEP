<%@page import="java.util.List"%>
<%@page import="logica.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Publicación y Categoría</title>
</head>
<body>
<% 
List<Categoria> listaCategorias = (List<Categoria>) session.getAttribute("listaCategorias");
%>

    <!-- Formulario para Crear Publicación -->
    <h2>Crear nueva publicación</h2>
    <form action="CrearPublicacionServlet" method="POST" enctype="multipart/form-data">

        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br><br>
        
        <label for="contenido">Contenido:</label>
        <textarea id="contenido" name="contenido" rows="5" cols="30" required></textarea><br><br>
        
        <label for="imagen">Imagen:</label>
        <input type="file" id="imagen" name="imagen"><br><br>
        
        <label for="tipoPublicacion">Tipo de Publicación:</label>
        <select id="tipoPublicacion" name="tipoPublicacion">
            <option value="formal">Formal</option>
            <option value="informal">-Informal</option>
        </select><br><br>
        
        <label for="reaccion">Reacción (valor numérico):</label>
        <input type="number" id="reaccion" name="reaccion" required><br><br>

        <!-- Lista desplegable para seleccionar una Categoría -->
        <label for="categoriaSeleccionada">Elegir Categoría:</label>
        <select id="categoriaSeleccionada" name="categoriaSeleccionada">
            <c:forEach items="${listaCategorias}" var="categoria">
                <option value="${categoria.id}">${categoria.titulo}</option>
            </c:forEach>
        </select><br><br>
        
        <input type="submit" value="Crear Publicación">
    </form>
    
    <hr> <!-- Separador -->

    <!-- Formulario para Crear Categoría -->
    <h2>Crear nueva categoría</h2>
    <form action="CrearCategoriaServlet" method="post">
        <label for="nombreCategoria">Nombre de la Categoría:</label>
        <input type="text" id="nombreCategoria" name="nombreCategoria" required><br><br>
        
        <input type="submit" value="Crear Categoría">
    </form>
    <% session.removeAttribute("listaCategorias");%>
</body>
</html>

