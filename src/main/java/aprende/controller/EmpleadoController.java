package aprende.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aprende.model.Empleado;
import aprende.model.EmpleadoService;

/**
 * Controlador que maneja las solicitudes HTTP relacionadas con los empleados.
 * Realiza las operaciones de listado, búsqueda, modificación, eliminación y cálculo de salarios de los empleados.
 * Este controlador se comunica con la capa de servicios (EmpleadoService) para llevar a cabo la lógica de negocio.
 */
@WebServlet("/empleado")
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmpleadoService empleadoService;

    /**
     * Constructor del controlador.
     * Se inicializa el servicio de empleados para manejar las operaciones.
     */
    public EmpleadoController() {
        super();
        empleadoService = new EmpleadoService(); // Iniciar servicio
    }

    /**
     * Maneja las solicitudes GET.
     * Dependiendo de la opción seleccionada, se llama a un método específico para realizar la operación.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        try {
            // Según la opción solicitada, se ejecuta una acción específica.
            if ("listar".equals(opcion)) {
                listarEmpleados(request, response);
            } else if ("salarios".equals(opcion)) {
                mostrarFormularioSalarios(request, response);
            } else if ("obtenerSalarios".equals(opcion)) {
                obtenerSalarioPorDni(request, response);
            } else if ("mostrar".equals(opcion)) {
                request.getRequestDispatcher("/views/mostrar.jsp").forward(request, response);
            } else if ("modificar".equals(opcion)) {
                request.getRequestDispatcher("/views/modificar.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, se pasa el mensaje de error a la vista.
            request.setAttribute("error", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }

    /**
     * Maneja las solicitudes POST.
     * Dependiendo de la acción solicitada, se llama a un método específico para realizar la operación.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        try {
            // Según la opción solicitada, se ejecuta una acción específica.
            if ("obtenerSalarios".equals(opcion)) {
                obtenerSalarioPorDni(request, response);
            } else if ("buscarPorAtributo".equals(opcion)) {
                buscarEmpleadosPorAtributo(request, response);
            } else if ("guardar".equals(opcion)) {
                guardarEmpleado(request, response);
            } else if ("editar".equals(opcion)) {
                editarEmpleado(request, response);
            } else if ("eliminar".equals(opcion)) {
                eliminarEmpleado(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error, se pasa el mensaje de error a la vista.
            request.setAttribute("error", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }

    /**
     * Listar todos los empleados.
     * Se obtiene la lista de empleados desde el servicio y se pasa a la vista correspondiente.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Empleado> lista = empleadoService.obtenerTodosLosEmpleados();
        request.setAttribute("lista", lista); // Se pasa la lista de empleados a la vista
        request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
    }

    /**
     * Muestra el formulario para ingresar los salarios.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void mostrarFormularioSalarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
    }

    /**
     * Obtiene el salario de un empleado dado su DNI.
     * Si se encuentra el salario, se muestra en la vista. Si ocurre un error, se muestra el mensaje de error.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void obtenerSalarioPorDni(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String dni = request.getParameter("dni");
        try {
            // Llamada al servicio para obtener el salario del empleado por su DNI
            Double salario = empleadoService.obtenerNominaPorDni(dni);
            
            // Se pasa el salario y el DNI a la vista para su presentación.
            request.setAttribute("sueldo", salario);
            request.setAttribute("dni", dni);
            request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
        } catch (SQLException e) {
            // En caso de error, se pasa el mensaje de error a la vista.
            request.setAttribute("mensaje", "Error: " + e.getMessage());
            request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
        }
    }

    /**
     * Busca empleados por un atributo específico.
     * Se calcula el sueldo de cada empleado y se pasa la lista resultante a la vista.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void buscarEmpleadosPorAtributo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String atributo = request.getParameter("atributo");
        String valor = request.getParameter("valor");
        List<Empleado> listaEmpleados = empleadoService.buscarEmpleadosPorAtributo(atributo, valor);

        // Se calcula el sueldo de cada empleado en la lista y se agrega al atributo sueldoTotal.
        for (Empleado empleado : listaEmpleados) {
            int sueldo = empleadoService.calcularSueldo(empleado);
            empleado.setSueldoTotal(sueldo);
        }

        // Se pasa la lista de empleados a la vista para su presentación.
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.getRequestDispatcher("/views/mostrar.jsp").forward(request, response);
    }

    /**
     * Guarda un nuevo empleado en la base de datos.
     * Los datos del empleado se extraen del formulario y se pasan al servicio para ser guardados.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws SQLException Si ocurre un error al guardar el empleado.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void guardarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Empleado empleado = new Empleado();
        empleado.setDni(request.getParameter("dni"));
        empleado.setNombre(request.getParameter("nombre"));
        empleado.setSexo(request.getParameter("sexo").charAt(0));
        empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
        empleado.setAnyos(Integer.parseInt(request.getParameter("anyos")));

        boolean guardado = empleadoService.guardarEmpleado(empleado);

        if (guardado) {
            request.setAttribute("mensajeExito", "Empleado guardado exitosamente.");
        } else {
            request.setAttribute("mensajeError", "Error al guardar el empleado.");
        }

        // Se vuelve a listar los empleados después de la operación.
        listarEmpleados(request, response);
    }

    /**
     * Edita los datos de un empleado existente.
     * El empleado se busca por su DNI original y se actualizan sus datos con los nuevos valores del formulario.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws SQLException Si ocurre un error al actualizar el empleado.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String dniOriginal = request.getParameter("dniOriginal");

        Empleado empleado = new Empleado();
        empleado.setDni(request.getParameter("dni"));
        empleado.setNombre(request.getParameter("nombre"));
        empleado.setSexo(request.getParameter("sexo").charAt(0));
        empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
        empleado.setAnyos(Integer.parseInt(request.getParameter("anyos")));

        // Se calcula el nuevo sueldo del empleado.
        int sueldoActualizado = empleadoService.calcularSueldo(empleado);
        empleado.setSueldoTotal(sueldoActualizado);

        // Se actualiza el empleado y se muestra el mensaje de éxito o error.
        String mensaje = empleadoService.editarEmpleado(empleado, dniOriginal);
        request.setAttribute("mensajeExito", mensaje);

        request.setAttribute("empleado", empleado);
        request.getRequestDispatcher("/views/modificar.jsp").forward(request, response);
    }

    /**
     * Elimina un empleado de la base de datos.
     * 
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @throws SQLException Si ocurre un error al eliminar el empleado.
     * @throws ServletException Si ocurre un error en la ejecución del servlet.
     * @throws IOException Si ocurre un error en la entrada/salida de datos.
     */
    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String dni = request.getParameter("dni");

        boolean eliminado = empleadoService.eliminarEmpleado(dni);
        if (eliminado) {
            request.setAttribute("mensajeExito", "Empleado eliminado exitosamente.");
        } else {
            request.setAttribute("mensajeError", "Error al eliminar el empleado.");
        }

        // Se vuelve a listar los empleados después de la eliminación.
        listarEmpleados(request, response);
    }
}
