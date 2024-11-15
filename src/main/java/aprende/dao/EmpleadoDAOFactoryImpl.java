package aprende.dao;

/**
 * Implementación concreta de la fábrica de DAOs de empleados.
 * Esta clase implementa la interfaz {@link EmpleadoDAOFactory} y es responsable de
 * crear una instancia de la clase {@link EmpleadoDAO}, que implementa la interfaz {@link EmpleadoDAOInterface}.
 */
public class EmpleadoDAOFactoryImpl implements EmpleadoDAOFactory {

    /**
     * Crea y devuelve una instancia de {@link EmpleadoDAO}.
     * Este método implementa la interfaz {@link EmpleadoDAOFactory} y se encarga de devolver una nueva
     * instancia de la clase {@link EmpleadoDAO}, que implementa las operaciones de acceso a datos para los empleados.
     * 
     * @return Una nueva instancia de {@link EmpleadoDAO}, que implementa {@link EmpleadoDAOInterface}.
     */
    @Override
    public EmpleadoDAOInterface crearEmpleadoDAO() {
        // Devuelve una nueva instancia de EmpleadoDAO
        return new EmpleadoDAO();
    }
}
