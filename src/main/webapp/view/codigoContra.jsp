<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Codigo de verificacion</title>
    <jsp:include page="/layouts/RecuperarContraseñaEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
<h1><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png"></h1>
<h1>Codigo de Seguridad</h1>
<p align="center">¿No tienes una cuenta? <a href="<%=request.getContextPath()%>/view/registro.jsp">Registrate</a></p>
<form action="" method="POST">
    <label for="username">Ingresa codigo de seguridad :</label>
    <p>Comprueba si recibiste un correo electrónico con tu código de 8 dígitos.</p>
    <input type="text" id="username" name="username" required><br>
    <br>
    <a href="<%=request.getContextPath()%>/view/contraseñaNew.jsp"><button class="btn btn-danger center" type="button">Inserta codigo</button</a>
</form>
<jsp:include page="/layouts/RecuperarContraseñaEstiloFooter.jsp"/>
</body>
</html>
