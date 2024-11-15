<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listar Empleados</title>
 
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>

    <!-- T�tulo de la p�gina -->
    <h1>Lista de Empleados</h1>

    <!-- Mostrar mensajes de �xito o error -->
    <c:if test="${not empty mensajeExito}">
        <div class="success">
            <p><c:out value="${mensajeExito}" /></p>
        </div>
    </c:if>

    <c:if test="${not empty mensajeError}">
        <div class="error">
            <p><c:out value="${mensajeError}" /></p>
        </div>
    </c:if>

    <!-- Tabla para listar los empleados -->
    <table border="1">
        <tr>
            <td>Nombre</td>
            <td>DNI</td>
            <td>Sexo</td>
            <td>Categor�a</td>
            <td>A�os Trabajados</td>
             <td>Acci�n</td>
        </tr>
        <!-- Iterar sobre la lista de empleados-->
        <c:forEach var="empleado" items="${lista}">
            <tr>
                <td><c:out value="${empleado.nombre}" /></td>
                <td><c:out value="${empleado.dni}" /></td>
                <td><c:out value="${empleado.sexo}" /></td>
                <td><c:out value="${empleado.categoria}" /></td>
                <td><c:out value="${empleado.anyos}" /></td>
                 <td>
            <form action="empleado" method="post">
                <input type="hidden" name="dni" value="${empleado.dni}"/>
                <input type="hidden" name="opcion" value="eliminar"/>
                <button type="submit">Eliminar</button>
            </form>
        </td>
            </tr>
        </c:forEach>
    </table>

    <!-- Bot�n para volver a la p�gina principal -->
    <button onclick="window.location.href='index.jsp';">Volver</button>
    
    <!-- Bot�n para ir a la p�gina de crear empleados -->
    <button type="button" onclick="window.location.href='views/crear.jsp';">Crear Empleados</button>

</body>
</html>
