<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Crear Empleado</title>
     <link rel="stylesheet" type="text/css" href="../styles/main.css">
</head>
<body>

<h1>Crear Empleado</h1>

<!-- Sección para mostrar mensajes -->
<c:if test="${not empty mensajeExito}">
    <p class="exito">${mensajeExito}</p>
</c:if>
<c:if test="${not empty mensajeError}">
    <p class="error">${mensajeError}</p>
</c:if>

<form name="crearForm" action="/nominas/empleado" method="post">
  <input type="hidden" name="opcion" value="guardar">

  <table border="1">
    <tr>
      <td>Nombre:</td>
      <td><input type="text" name="nombre" size="50" required></td>
    </tr>
    <tr>
      <td>DNI:</td>
      <td><input type="text" name="dni" size="50" pattern="^\d{8}[A-Z]$" title="DNI debe tener 8 números seguidos de una letra mayúscula" required></td>
    </tr>
    <tr>
      <td>Sexo:</td>
      <td>
        <select name="sexo" required>
          <option value="M">Masculino</option>
          <option value="F">Femenino</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Categoría:</td>
      <td><input type="number" name="categoria" min="1" max="9" required></td>
    </tr>
    <tr>
      <td>Años en la empresa:</td>
      <td><input type="number" name="anyos" min="0" required></td>
    </tr>
  </table>

  <input type="submit" value="Guardar">
  <button type="button" onclick="window.history.go(-1);">Volver</button>
</form>
</body>
</html>
