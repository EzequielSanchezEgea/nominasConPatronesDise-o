<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Buscar Empleados</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
   <h1>Buscar Empleados</h1>
<form name="buscarForm" action="empleado" method="post">
    <input type="hidden" name="opcion" value="buscarPorAtributo">
    <!-- Parámetro 'busquedaIniciada' para saber si se ha realizado una búsqueda -->
    <input type="hidden" name="busquedaIniciada" value="true">
    
    <table border="1">
        <tr>
            <td>Atributo:</td>
            <td>
                <select name="atributo" onchange="this.form.submit()">
                    <option value="nombre" ${param.atributo == 'nombre' ? 'selected' : ''}>Nombre</option>
                    <option value="dni" ${param.atributo == 'dni' ? 'selected' : ''}>DNI</option>
                    <option value="sexo" ${param.atributo == 'sexo' ? 'selected' : ''}>Sexo</option>
                    <option value="categoria" ${param.atributo == 'categoria' ? 'selected' : ''}>Categoria</option>
                    <option value="anyos" ${param.atributo == 'anyos' ? 'selected' : ''}>Años en la empresa</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Valor:</td>
            <td>
                <c:choose>
                    <c:when test="${param.atributo == 'nombre'}">
                        <input type="text" name="valor">
                    </c:when>
                    <c:when test="${param.atributo == 'dni'}">
                        <input type="text" name="valor" pattern="^\d{8}[A-Z]$" title="DNI debe tener 8 números seguidos de una letra mayúscula" required>
                    </c:when>
                    <c:when test="${param.atributo == 'sexo'}">
                        <input type="text" name="valor" pattern="[MF]" title="Sexo debe ser 'M' o 'F'" required>
                    </c:when>
                    <c:when test="${param.atributo == 'categoria'}">
                        <input type="number" name="valor" min="1" max="9" required>
                    </c:when>
                    <c:when test="${param.atributo == 'anyos'}">
                        <input type="number" name="valor" min="0" required>
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="valor" required>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <button type="submit">Buscar</button>
    <button type="button" onclick="window.location.href='index.jsp';">Volver</button>
</form>

<div>
    <h2>Resultados de la búsqueda</h2>

    <!-- Mostrar resultados solo si la búsqueda ha sido iniciada -->
    <c:if test="${param.busquedaIniciada == 'true'}">
        
        <!-- Si hay empleados en la lista -->
        <c:if test="${!empty listaEmpleados}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>DNI</th>
                        <th>Sexo</th>
                        <th>Categoria</th>
                        <th>Años en la empresa</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="empleado" items="${listaEmpleados}">
                        <tr>
                            <td>${empleado.nombre}</td>
                            <td>${empleado.dni}</td>
                            <td>${empleado.sexo}</td>
                            <td>${empleado.categoria}</td>
                            <td>${empleado.anyos}</td>
                            <td>
                                <!-- Botón para modificar -->
                                <form action="empleado" method="get" style="display:inline;">
                                    <input type="hidden" name="opcion" value="modificar">
                                    <input type="hidden" name="dni" value="${empleado.dni}">
                                    <input type="hidden" name="nombre" value="${empleado.nombre}">
                                    <input type="hidden" name="sexo" value="${empleado.sexo}">
                                    <input type="hidden" name="categoria" value="${empleado.categoria}">
                                    <input type="hidden" name="anyos" value="${empleado.anyos}">
                                    <input type="hidden" name="sueldoTotal" value="${empleado.sueldoTotal}">
                                    <button type="submit">Modificar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <!-- Si la lista está vacía y se realizó la búsqueda -->
        <c:if test="${empty listaEmpleados}">
            <p class="error">No se encontraron empleados.</p>
        </c:if>
    </c:if>
</div>
</body>
</html>
