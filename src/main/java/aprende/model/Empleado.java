package aprende.model;

/**
 * Clase que representa un empleado con sus atributos y métodos relacionados.
 * 
 * <p>
 * Contiene información como nombre, DNI, sexo, categoría y años de experiencia.
 * Además, proporciona validaciones para los datos y un campo temporal para
 * almacenar el sueldo calculado.
 * </p>
 */
public class Empleado {
    private int categoria;
    public String nombre, dni;
    public char sexo;
    public int anyos;
    private transient int sueldoTotal; // Campo temporal para el salario calculado

    /**
     * Constructor por defecto de la clase Empleado.
     */
    public Empleado() {
        super();
    }

    /**
     * Constructor que inicializa un empleado con los parámetros especificados.
     * 
     * @param nombre   Nombre del empleado.
     * @param dni      DNI del empleado (debe tener 8 dígitos seguidos de una
     *                 letra).
     * @param sexo     Sexo del empleado ('M' o 'F').
     * @param categoria Categoría del empleado (debe ser mayor que 0).
     * @param anyos    Años de experiencia del empleado (debe ser 0 o mayor).
     */
    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) {
        setCategoria(categoria);
        setNombre(nombre);
        setDni(dni);
        setSexo(sexo);
        setAnyos(anyos);
    }

    // Getters y Setters con validaciones

    /**
     * Obtiene la categoría del empleado.
     * 
     * @return La categoría del empleado.
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del empleado. Debe ser mayor que 0.
     * 
     * @param categoria La categoría a establecer.
     * @throws IllegalArgumentException Si la categoría es menor o igual a 0.
     */
    public void setCategoria(int categoria) {
        if (categoria <= 0) {
            throw new IllegalArgumentException("La categoría debe ser mayor que 0.");
        }
        this.categoria = categoria;
    }

    /**
     * Obtiene el nombre del empleado.
     * 
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado. No puede ser nulo ni estar vacío.
     * 
     * @param nombre El nombre a establecer.
     * @throws IllegalArgumentException Si el nombre es nulo o vacío.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    /**
     * Obtiene el DNI del empleado.
     * 
     * @return El DNI del empleado.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del empleado. Debe tener 8 dígitos seguidos de una letra
     * mayúscula.
     * 
     * @param dni El DNI a establecer.
     * @throws IllegalArgumentException Si el formato del DNI no es válido.
     */
    public void setDni(String dni) {
        if (dni == null || !dni.matches("[0-9]{8}[A-Z]")) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos seguidos de una letra mayúscula.");
        }
        this.dni = dni;
    }

    /**
     * Obtiene el sexo del empleado.
     * 
     * @return El sexo del empleado ('M' o 'F').
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del empleado. Debe ser 'M' o 'F'.
     * 
     * @param sexo El sexo a establecer.
     * @throws IllegalArgumentException Si el sexo no es 'M' ni 'F'.
     */
    public void setSexo(char sexo) {
        if (sexo != 'M' && sexo != 'F') {
            throw new IllegalArgumentException("El sexo debe ser 'M' o 'F'.");
        }
        this.sexo = sexo;
    }

    /**
     * Obtiene los años de experiencia del empleado.
     * 
     * @return Los años de experiencia.
     */
    public int getAnyos() {
        return anyos;
    }

    /**
     * Establece los años de experiencia del empleado. No puede ser un número
     * negativo.
     * 
     * @param anyos Los años de experiencia a establecer.
     * @throws IllegalArgumentException Si los años son negativos.
     */
    public void setAnyos(int anyos) {
        if (anyos < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        this.anyos = anyos;
    }

    /**
     * Obtiene el sueldo total del empleado.
     * 
     * @return El sueldo total.
     */
    public int getSueldoTotal() {
        return sueldoTotal;
    }

    /**
     * Establece el sueldo total del empleado.
     * 
     * @param sueldoTotal El sueldo total calculado.
     */
    public void setSueldoTotal(int sueldoTotal) {
        this.sueldoTotal = sueldoTotal;
    }

    /**
     * Retorna una representación en cadena de los datos del empleado.
     * 
     * @return Una cadena con la información del empleado.
     */
    @Override
    public String toString() {
        return "Empleado [categoria=" + categoria + ", nombre=" + nombre + ", dni=" + dni + ", sexo=" + sexo
                + ", anyos=" + anyos + "]";
    }
}
