package aprende.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aprende.conexion.Conexion;
import aprende.model.Empleado;
import aprende.model.Nomina;

public class EmpleadoDAO implements EmpleadoDAOInterface {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	/**
	 * Obtiene todos los empleados de la base de datos.
	 * 
	 * @return Una lista de objetos Empleado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
		ResultSet resultSet = null;
		List<Empleado> listaEmpleados = new ArrayList<>();
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			// Definir la consulta SQL
			sql = "SELECT * FROM empleados";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			// Procesar el resultado de la consulta
			while (resultSet.next()) {
				Empleado empleado = new Empleado();

				// Asignar los valores del resultSet a los atributos del objeto Empleado
				empleado.setNombre(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setSexo(resultSet.getString(3).charAt(0));
				empleado.setCategoria(resultSet.getInt(4));
				empleado.setAnyos(resultSet.getInt(5));

				// Añadir el empleado a la lista de empleados
				listaEmpleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return listaEmpleados; // Devolver la lista de empleados
	}

	/**
	 * Obtiene el salario de un empleado según su DNI.
	 * 
	 * @param dni El DNI del empleado.
	 * @return El salario del empleado.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Double obtenerNominaPorDni(String dni) throws SQLException {
		ResultSet resultSet = null;
		Double sueldo = null;
		String sql = null;
		Connection connection = obtenerConexion();

		try {
			// Consulta a la tabla nominas usando el DNI.
			sql = "SELECT sueldo FROM nominas WHERE empleado_dni = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, dni); // Se pasa el DNI como parámetro.

			resultSet = statement.executeQuery();

			// Si se encuentra un registro, se setea al sueldo el nuevo valor.
			if (resultSet.next()) {
				sueldo = resultSet.getDouble("sueldo");
			} else {
				throw new SQLException("Empleado con DNI " + dni + " no encontrado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e; // Lanza la excepción para que sea manejada por el llamador
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return sueldo;
	}

	/**
	 * Busca empleados por un atributo específico.
	 * 
	 * @param atributo El nombre del atributo a buscar.
	 * @param valor    El valor del atributo a buscar.
	 * @return Una lista de empleados que coinciden con el criterio de búsqueda.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public List<Empleado> buscarEmpleadosPorAtributo(String atributo, String valor) throws SQLException {
		ResultSet resultSet = null;
		List<Empleado> listaEmpleados = new ArrayList<>();

		String sql = "SELECT * FROM empleados WHERE " + atributo + " = ?";
		Connection connection = obtenerConexion();
		if (atributo == "") {
			listaEmpleados = obtenerTodosLosEmpleados();
		}
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, valor); // Se pasa el valor del atributo como parámetro.
			resultSet = statement.executeQuery();

			// Procesar el resultado de la consulta
			while (resultSet.next()) {
				Empleado empleado = new Empleado();
				// Asignar los valores del resultSet a los atributos del objeto Empleado
				empleado.setNombre(resultSet.getString(1));
				empleado.setDni(resultSet.getString(2));
				empleado.setSexo(resultSet.getString(3).charAt(0));
				empleado.setCategoria(resultSet.getInt(4));
				empleado.setAnyos(resultSet.getInt(5));
				// Añadir el empleado a la lista de empleados
				listaEmpleados.add(empleado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return listaEmpleados; // Devolver la lista de empleados
	}

	/**
	 * Guarda un nuevo empleado en la base de datos.
	 * 
	 * @param empleado El empleado a guardar.
	 * @return true si se guarda correctamente, false de lo contrario.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public boolean guardarEmpleado(Empleado empleado) throws SQLException {
		String sql = null;
		String sqlNominas = null;
		boolean estadoOperacion = false;
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement verificarStmt = null;
		ResultSet rs = null;

		try {
			connection = obtenerConexion();
			connection.setAutoCommit(false);

			// Verificar si el empleado ya existe basado en el DNI
			String sqlVerificar = "SELECT COUNT(*) FROM empleados WHERE dni = ?";
			verificarStmt = connection.prepareStatement(sqlVerificar);
			verificarStmt.setString(1, empleado.getDni());

			rs = verificarStmt.executeQuery();
			rs.next();
			int count = rs.getInt(1); // Obtenemos el número de coincidencias por DNI

			if (count > 0) {
				System.out.println("El empleado con DNI '" + empleado.getDni() + "' ya existe.");
				estadoOperacion = false;
			} else {
				// Insertar el nuevo empleado
				sql = "INSERT INTO empleados (nombre, dni, sexo, categoria, anyos) VALUES(?,?,?,?,?)";
				statement = connection.prepareStatement(sql);
				statement.setString(1, empleado.getNombre());
				statement.setString(2, empleado.getDni());
				statement.setString(3, String.valueOf(empleado.getSexo()).substring(0, 1));
				statement.setInt(4, empleado.getCategoria());
				statement.setInt(5, empleado.getAnyos());

				estadoOperacion = statement.executeUpdate() > 0;

				// Calcular el salario usando la instancia Singleton de Nomina
				Nomina nomina = Nomina.getInstance(); // Obtener la instancia de Nomina
				int sueldoTotal = nomina.sueldo(empleado); // Llamar al método de instancia

				// Actualizar la tabla de nóminas
				sqlNominas = "INSERT INTO nominas(empleado_dni, sueldo) VALUES(?,?)";
				statement = connection.prepareStatement(sqlNominas);
				statement.setString(1, empleado.getDni());
				statement.setInt(2, sueldoTotal);
				estadoOperacion = statement.executeUpdate() > 0 && estadoOperacion;

				if (estadoOperacion) {
					System.out.println("Nómina insertada correctamente.");
				}
			}

			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				connection.rollback();
			}
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (verificarStmt != null) {
				verificarStmt.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return estadoOperacion;
	}

	/**
	 * Edita la información de un empleado existente.
	 * 
	 * @param empleado El empleado con la información actualizada.
	 * @return true si se actualiza correctamente, false de lo contrario.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public String editar(Empleado empleado, String dniOriginal) throws SQLException {
		String sqlEmpleados = null;
		String sqlNominas = null;
		String mensaje = "";
		boolean estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);

			// Verificar si ya existe otro empleado con el nuevo DNI
			String sqlCheckDni = "SELECT COUNT(*) FROM empleados WHERE dni = ? AND dni != ?";
			statement = connection.prepareStatement(sqlCheckDni);
			statement.setString(1, empleado.getDni());
			statement.setString(2, dniOriginal); // Usamos dniOriginal aquí para la comparación
			ResultSet rs = statement.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				mensaje = "Error: El DNI ya está en uso por otro empleado.";
				throw new SQLException(mensaje);
			}

			// Actualizar la tabla de empleados
			sqlEmpleados = "UPDATE empleados SET nombre=?, dni=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
			statement = connection.prepareStatement(sqlEmpleados);
			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getDni());
			statement.setString(3, String.valueOf(empleado.getSexo()).substring(0, 1));
			statement.setInt(4, empleado.getCategoria());
			statement.setInt(5, empleado.getAnyos());
			statement.setString(6, dniOriginal); // Usamos dniOriginal en el WHERE
			estadoOperacion = statement.executeUpdate() > 0;

			// Calcular el salario usando la instancia Singleton de Nomina
			Nomina nomina = Nomina.getInstance(); // Obtener la instancia de Nomina
			int sueldoTotal = nomina.sueldo(empleado); // Llamar al método de instancia

			// Actualizar la tabla nominas
			sqlNominas = "UPDATE nominas SET sueldo=? WHERE empleado_dni=?";
			statement = connection.prepareStatement(sqlNominas);
			statement.setInt(1, sueldoTotal);
			statement.setString(2, empleado.getDni());
			estadoOperacion = statement.executeUpdate() > 0 && estadoOperacion;

			// Mensaje de éxito
			if (estadoOperacion) {
				mensaje = "Éxito: Empleado actualizado correctamente.";
			} else {
				mensaje = "Error: No se pudo actualizar la información del empleado.";
			}

			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			mensaje = e.getMessage();
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return mensaje;
	}

	public boolean eliminar(String dni) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM empleados WHERE dni=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dni);

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
}
