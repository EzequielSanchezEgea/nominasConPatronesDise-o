package aprende.model;

import java.sql.SQLException;
import java.util.List;

import aprende.dao.EmpleadoDAOInterface;
import aprende.dao.EmpleadoDAOFactory;
import aprende.dao.EmpleadoDAOFactoryImpl;

/**
 * Servicio que proporciona operaciones relacionadas con los empleados.
 * Esta clase interactúa con la capa de acceso a datos (DAO) para realizar 
 * operaciones CRUD y otras funciones relacionadas con los empleados.
 */
public class EmpleadoService {

    private EmpleadoDAOInterface empleadoDAO;

    /**
     * Constructor de la clase EmpleadoService.
     * Se utiliza la fábrica de DAOs para obtener una instancia del DAO
     * que se utilizará para interactuar con la base de datos.
     */
    public EmpleadoService() {
        // Usamos la fábrica para obtener la instancia del DAO
        EmpleadoDAOFactory factory = new EmpleadoDAOFactoryImpl();
        this.empleadoDAO = factory.crearEmpleadoDAO();
    }

    /**
     * Obtiene todos los empleados almacenados en la base de datos.
     * 
     * @return Lista de empleados.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
        return empleadoDAO.obtenerTodosLosEmpleados();
    }

    /**
     * Obtiene la nómina de un empleado dada su identificación (DNI).
     * 
     * @param dni El DNI del empleado.
     * @return El monto de la nómina del empleado.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public Double obtenerNominaPorDni(String dni) throws SQLException {
        return empleadoDAO.obtenerNominaPorDni(dni);
    }

    /**
     * Busca empleados según un atributo específico y su valor.
     * 
     * @param atributo El nombre del atributo de búsqueda (por ejemplo, "nombre", "departamento").
     * @param valor El valor que se desea buscar para el atributo indicado.
     * @return Lista de empleados que coinciden con el atributo y valor dados.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<Empleado> buscarEmpleadosPorAtributo(String atributo, String valor) throws SQLException {
        return empleadoDAO.buscarEmpleadosPorAtributo(atributo, valor);
    }

    /**
     * Guarda un nuevo empleado en la base de datos.
     * 
     * @param empleado El empleado que se desea guardar.
     * @return true si el empleado se guardó correctamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean guardarEmpleado(Empleado empleado) throws SQLException {
        return empleadoDAO.guardarEmpleado(empleado);
    }

    /**
     * Edita la información de un empleado en la base de datos.
     * 
     * @param empleado El empleado con los nuevos datos a guardar.
     * @param dniOriginal El DNI del empleado antes de ser editado.
     * @return Un mensaje indicando el resultado de la operación (por ejemplo, "Empleado editado con éxito").
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public String editarEmpleado(Empleado empleado, String dniOriginal) throws SQLException {
        return empleadoDAO.editar(empleado, dniOriginal);
    }

    /**
     * Elimina un empleado de la base de datos utilizando su DNI.
     * 
     * @param dni El DNI del empleado que se desea eliminar.
     * @return true si el empleado se eliminó correctamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean eliminarEmpleado(String dni) throws SQLException {
        return empleadoDAO.eliminar(dni);
    }

    /**
     * Calcula el sueldo de un empleado. Este cálculo puede involucrar varios factores
     * dependiendo de la implementación de la clase Nomina.
     * 
     * @param empleado El empleado para el cual se desea calcular el sueldo.
     * @return El monto calculado del sueldo del empleado.
     */
    public int calcularSueldo(Empleado empleado) {
        // Se obtiene una instancia de la clase Nomina (que se asume tiene lógica de cálculo de sueldo)
        Nomina nomina = Nomina.getInstance();
        return nomina.sueldo(empleado);
    }
}
