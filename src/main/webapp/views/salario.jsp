<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Buscador Salarios</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
    <!-- Título de la página -->
    <h1>Consulta Salario</h1>
    <!-- Formulario para buscar salario por DNI -->
    <form name="salarioForm" action="empleado" method="post">
        <input type="hidden" name="opcion" value="obtenerSalarios">
        <table border="1">
            <tr>
                <td>Introduce DNI Empleado:</td>
                <td>
                    <input type="text" name="dni" size="50"
                           value="${dni != null ? dni : ''}" pattern="^\d{8}[A-Za-z]$" 
                           title="DNI debe tener 8 números seguidos de una letra" required>
                </td>
            </tr>
        </table>
        <button type="submit">Buscar Salario</button>
        <button type="button" onclick="window.location.href='index.jsp';">Volver</button>
    </form>
    
    <!-- Sección para mostrar resultados -->
    <div>
        <h2>Resultados de la búsqueda</h2>
        <!-- Si hay un mensaje de error, lo mostramos -->
        <c:if test="${not empty mensaje}">
            <p class="error">${mensaje}</p>
        </c:if>
        
        <!-- Si hay un salario encontrado, lo mostramos -->
        <c:if test="${sueldo != null}">
            <p class="success">Empleado encontrado:</p>
            <p>DNI del empleado: ${dni}</p>
            <p>Salario: ${sueldo}</p>
        </c:if>
    </div>
</body>
</html>
