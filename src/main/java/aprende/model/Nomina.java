package aprende.model;

public class Nomina {
    
    // Instancia única de la clase (Singleton)
    private static Nomina instance;
    
    private static final int SUELDO_BASE[] = {
        50000, 70000, 90000, 110000, 130000,
        150000, 170000, 190000, 210000, 230000
    };

    // Constructor privado para evitar instanciación directa desde fuera
    private Nomina() {}

    // Método estático para obtener la única instancia de Nomina
    public static Nomina getInstance() {
        if (instance == null) {
            synchronized (Nomina.class) {
                if (instance == null) {
                    instance = new Nomina();
                }
            }
        }
        return instance;
    }

    /**
     * Calcula el sueldo total de un empleado basado en su categoría y años trabajados.
     * 
     * @param empleado El empleado cuyo sueldo se va a calcular.
     * @return El sueldo total del empleado.
     */
    public int sueldo(Empleado empleado) {
        int categoriaEmpleado = empleado.getCategoria();
        int anyosTrabajados = empleado.getAnyos();

        // Validar que la categoría sea válida
        if (categoriaEmpleado < 1 || categoriaEmpleado > SUELDO_BASE.length) {
            throw new IllegalArgumentException("Categoría inválida: " + categoriaEmpleado);
        }

        int sueldoBase = SUELDO_BASE[categoriaEmpleado - 1];
        int sueldoTotal = sueldoBase + (5000 * anyosTrabajados);
        return sueldoTotal;
    }
}
