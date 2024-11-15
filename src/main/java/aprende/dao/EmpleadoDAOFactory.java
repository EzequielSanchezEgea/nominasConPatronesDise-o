package aprende.dao;

/**
 * Interfaz para la fábrica de DAOs de empleados.
 * Esta interfaz define el método para crear una instancia de un objeto 
 * que implementa la interfaz {@link EmpleadoDAOInterface}.
 * La implementación concreta de esta fábrica se encargará de devolver la
 * implementación específica del DAO correspondiente a la base de datos.
 */
public interface EmpleadoDAOFactory {

    /**
     * Método para crear una instancia de un {@link EmpleadoDAOInterface}.
     * Este método devuelve un objeto que implementa la interfaz {@link EmpleadoDAOInterface},
     * que se utiliza para realizar las operaciones de acceso a datos de empleados en la base de datos.
     * 
     * @return Una instancia de un objeto que implementa {@link EmpleadoDAOInterface}.
     */
    EmpleadoDAOInterface crearEmpleadoDAO();
}