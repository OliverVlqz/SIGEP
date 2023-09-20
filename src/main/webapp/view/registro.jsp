    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <jsp:include page="../layouts/RegistroEstiloHeader.jsp"/>
</head>
<body style="background-image: url(<%=request.getContextPath()%>/assets/img/Fondo.png)">
<h1><img src="<%=request.getContextPath()%>/assets/img/logoSIGEP.png"></h1>
<h1>Registro de Usuario</h1>
<form action="../SvUsuario" method="POST">
  <label for="nombre">Usuario:</label>
  <input type="text" id="nombre" name="nombre" required>
  <label for="email">Correo:</label>
  <input type="email" id="email" name="correo" required>
  <label for="contrasena">Contraseña:</label>
  <input type="password" id="contrasena" name="contrasenia" required>
  
  <label for="genero">Sexo:</label>
  <select id="genero" name="genero">
    <option value="Masculino">Masculino</option>
    <option value="Femenino">Femenino</option>
    <option value="Prefiero no decirlo">Prefiero no decirlo</option>
  </select>

  <!-- Campos ocultos con valores predeterminados -->
  <input type="hidden" name="tipoUsuario" value="Usuario">
  <input type="hidden" name="numSanciones" value="0">

  <input type="submit" value="Registrarse" class="mex">
</form>
<jsp:include page="../layouts/RegistroEstiloFooter.jsp"/>
</body>
</html>
