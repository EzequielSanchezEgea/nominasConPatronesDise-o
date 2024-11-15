<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Modificar Empleado</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
    <h1>Modificar Empleado</h1>
    
    <!-- Sección para mostrar mensajes -->
    <c:if test="${not empty mensajeExito}">
        <p class="success">${mensajeExito}</p>
    </c:if>
    <c:if test="${not empty mensajeError}">
        <p class="error">${mensajeError}</p>
    </c:if>

  <form name="modificarForm" action="empleado" method="post">
    <input type="hidden" name="opcion" value="editar">
    <!-- Campo oculto para guardar el DNI original -->
    <input type="hidden" name="dniOriginal" value="${param.dni}">
    
    <table border="1">
        <tr>
            <td>Nombre:</td>
            <td><input type="text" name="nombre" value="${param.nombre}" required></td>
        </tr>
        <tr>
            <td>Dni:</td>
            <td><input type="text" name="dni" value="${param.dni}" pattern="^\d{8}[A-Z]$" title="DNI debe tener 8 números seguidos de una letra Mayúscula" required></td>
        </tr>
        <tr>
            <td>Sexo:</td>
            <td><input type="text" name="sexo" value="${param.sexo}" pattern="[MF]" title="Sexo debe ser 'M' o 'F'" required></td>
        </tr>
        <tr>
            <td>Categoria:</td>
            <td><input type="number" name="categoria" value="${param.categoria}" min="1" max="9" required></td>
        </tr>
        <tr>
            <td>Años en la empresa:</td>
            <td><input type="number" name="anyos" value="${param.anyos}" min="0" required></td>
        </tr>
        <tr>
            <td>Nómina:</td>
            <td>
                <c:choose>
                    <c:when test="${not empty empleado.sueldoTotal}">
                        <input type="text" name="sueldoTotal" value="${empleado.sueldoTotal}" readonly>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="sueldoTotal" value="${param.sueldoTotal}" readonly>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <button type="submit">Guardar Cambios</button>
   <button type="button" onclick="window.history.go(-1);">Volver</button>
</form>

</body>
</html>
