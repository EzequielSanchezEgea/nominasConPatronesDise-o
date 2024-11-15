package aprende.dao;

import java.sql.SQLException;
import java.util.List;

import aprende.model.Empleado;

/**
 * Interfaz que define las operaciones de acceso a datos para los empleados.
 * Las implementaciones de esta interfaz se encargarán de realizar las operaciones CRUD
 * (Crear, Leer, Actualizar y Eliminar) sobre la base de datos para gestionar los empleados.
 */
public interface EmpleadoDAOInterface {

    /**
     * Obtiene todos los empleados de la base de datos.
     * 
     * @return Una lista con todos los empleados registrados.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     */
    List<Empleado> obtenerTodosLosEmpleados() throws SQLException;

    /**
     * Obtiene la nómina (salario) de un empleado dado su DNI.
     * 
     * @param dni El DNI del empleado cuyo salario se desea obtener.
     * @return El salario del empleado, o {@code null} si no se encuentra el empleado.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     */
    Double obtenerNominaPorDni(String dni) throws SQLException;

    /**
     * Busca empleados según un atributo específico.
     * 
     * @param atributo El nombre del atributo por el que se desea realizar la búsqueda.
     * @param valor El valor del atributo por el que se realiza la búsqueda.
     * @return Una lista de empleados que coinciden con el atributo y valor proporcionados.
     * @throws SQLException Si ocurre un error al consultar la base de datos.
     */
    List<Empleado> buscarEmpleadosPorAtributo(String atributo, String valor) throws SQLException;

    /**
     * Guarda un nuevo empleado en la base de datos.
     * 
     * @param empleado El objeto {@link Empleado} con los datos del nuevo empleado.
     * @return {@code true} si el empleado fue guardado correctamente, {@code false} en caso contrario.
     * @throws SQLException Si ocurre un error al guardar el empleado en la base de datos.
     */
    boolean guardarEmpleado(Empleado empleado) throws SQLException;

    /**
     * Actualiza los datos de un empleado en la base de datos.
     * 
     * @param empleado El objeto {@link Empleado} con los datos actualizados del empleado.
     * @param dniOriginal El DNI original del empleado que se desea editar.
     * @return Un mensaje que indica si la operación fue exitosa o si ocurrió algún error.
     * @throws SQLException Si ocurre un error al actualizar los datos del empleado en la base de datos.
     */
    String editar(Empleado empleado, String dniOriginal) throws SQLException;

    /**
     * Elimina un empleado de la base de datos.
     * 
     * @param dni El DNI del empleado que se desea eliminar.
     * @return {@code true} si el empleado fue eliminado correctamente, {@code false} en caso contrario.
     * @throws SQLException Si ocurre un error al eliminar el empleado de la base de datos.
     */
    boolean eliminar(String dni) throws SQLException;
}
