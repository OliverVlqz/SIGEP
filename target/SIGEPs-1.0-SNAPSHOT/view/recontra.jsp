<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <title>Recuperar Contraseña</title>
     <jsp:include page="/layouts/RecuperarContraseñaEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
<h1><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png"></h1>
<h1>Recuperar tu cuenta</h1>
<p align="center">¿No tienes una cuenta?<a href="<%=request.getContextPath()%>/view/registro.jsp">Registrate</a></p>
<form action="" method="POST">
    <label for="username">Correo electronico:</label>
    <p>Ingresa tu correo electrónico para buscar tu cuenta. </p>
    <input type="text" id="username" name="username" required><br>
    <br>
    <div class="d-grid gap-2 col-6 mx-auto">
        <a href="codigoContra.jsp"><button class="btn btn-danger center" type="button">Nueva contraseña</button</a>
    </div>
</form>
<jsp:include page="/layouts/RecuperarContraseñaEstiloFooter.jsp"/>
</body>
</html>
