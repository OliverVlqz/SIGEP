<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nueva Contraseña</title>
    <jsp:include page="/layouts/RecuperarContraseñaEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
    <h1><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png"></h1>
<h1>Ingresa contraseña</h1>
<p align="center">¿No tienes una cuenta?<a href="<%=request.getContextPath()%>/view/registro.jsp">Registrate</a></p>
<form action="" method="POST">
    <label for="username">Ingresa nueva contraseña:</label>
    <p>Una contraseña segura tiene una combinación de letras, números y simbolos.</p>
    <input type="text" id="username" name="username" required><br><br>
</form>
</body>
<jsp:include page="/layouts/RecuperarContraseñaEstiloFooter.jsp"/>
</html>
