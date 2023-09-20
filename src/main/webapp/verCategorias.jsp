<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ver y Seleccionar Categorías</title>
    <style>
        /* Estilo para ocultar la tabla */
        #tablaCategorias {
            display: none;
        }
    </style>
</head>
<body>

<h2>Categorías existentes</h2>

<!-- Tabla ahora con un ID y con estilo para ocultarla -->
<table id="tablaCategorias" border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listaCategorias}" var="categoria">
            <tr>
                <td>${categoria.id}</td>
                <td>${categoria.titulo}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h3>Seleccionar una Categoría</h3>

<form action="TuServletDeProcesamiento" method="post">
    <label for="categoriaSeleccionada">Elegir Categoría:</label>
    <select id="categoriaSeleccionada" name="categoriaSeleccionada">
        <c:forEach items="${listaCategorias}" var="categoria">
            <option value="${categoria.id}">${categoria.titulo}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Seleccionar">
</form>

</body>
</html>
