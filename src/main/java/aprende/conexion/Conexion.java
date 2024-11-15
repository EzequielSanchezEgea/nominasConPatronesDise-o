package aprende.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * La clase Conexion proporciona un método para obtener una conexión a la base de datos
 * utilizando un pool de conexiones administrado por Apache DBCP.
 * Esta implementación asegura que las conexiones se gestionen de manera eficiente,
 * evitando la sobrecarga de crear y destruir conexiones repetidamente.
 */
public class Conexion {
    private static BasicDataSource dataSource = null;

    /**
     * Obtiene el DataSource que se utilizará para las conexiones a la base de datos.
     * 
     * Si el DataSource no ha sido inicializado, se crea uno nuevo y se configura
     * con los parámetros necesarios para conectarse a la base de datos MySQL.
     * 
     * @return Un objeto DataSource configurado para la conexión a la base de datos.
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            // Configuración del controlador JDBC
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            // Configuración de las credenciales de la base de datos
            dataSource.setUsername("root");
            dataSource.setPassword("2222");
            // URL de la base de datos con opciones de conexión
            dataSource.setUrl("jdbc:mysql://localhost:3306/laboral?useTimezone=true&serverTimezone=UTC");
            // Tamaños del pool de conexiones
            dataSource.setInitialSize(20);   // Número inicial de conexiones en el pool
            dataSource.setMaxIdle(15);       // Máximo número de conexiones inactivas
            dataSource.setMaxTotal(20);      // Máximo total de conexiones en el pool
            dataSource.setMaxWaitMillis(5000); // Tiempo máximo de espera para una conexión
        }
        return dataSource;
    }

    /**
     * Obtiene una conexión a la base de datos.
     * 
     * Este método utiliza el DataSource configurado para obtener una conexión
     * que puede ser utilizada para realizar operaciones en la base de datos.
     * 
     * @return Una conexión a la base de datos.
     * @throws SQLException Si ocurre un error al obtener la conexión, lo que puede deberse
     *                      a problemas de red, credenciales incorrectas o configuración de la base de datos.
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
