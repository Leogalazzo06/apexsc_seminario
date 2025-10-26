package apexsc;

// Esta clase representa a un socio del club Apex SC.
// Es la clase base que servirá también para extender o heredar en caso de tener diferentes tipos de socios.
public class Socio {
    // --- Atributos privados ---
    // Aplico encapsulamiento para proteger los datos personales de cada socio.
    private String nombre;     // nombre completo del socio
    private String dni;        // documento de identidad
    private String categoria;  // categoría del socio (por ejemplo: común, familiar, vitalicio)
    private boolean activo;    // indica si el socio está activo o no

    // --- Constructor ---
    // Cuando creo un socio, valido que los campos obligatorios no estén vacíos.
    public Socio(String nombre, String dni, String categoria) throws Exception {
        // Verifico que el nombre no sea nulo ni vacío.
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre del socio no puede estar vacío.");
        }
        // Verifico que el DNI no sea nulo ni vacío.
        if (dni == null || dni.isEmpty()) {
            throw new Exception("El DNI no puede estar vacío.");
        }

        // Asigno los valores a los atributos.
        this.nombre = nombre;
        this.dni = dni;
        this.categoria = categoria;
        this.activo = true; // Todo socio nuevo se registra como activo por defecto.
    }

    // --- Getters y Setters ---
    // Estos métodos me permiten acceder y modificar los valores privados desde fuera de la clase.

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // --- Método para mostrar la información del socio ---
    // Este método imprime por consola todos los datos del socio de forma ordenada.
    public void mostrarDatos() {
        System.out.println("\n--- Datos del Socio ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Categoría: " + categoria);
        System.out.println("Estado: " + (activo ? "Activo" : "Inactivo"));
    }

    // --- Método de comportamiento ---
    // Este método calcula el monto de la cuota mensual según la categoría del socio.
    // También permite demostrar polimorfismo si en el futuro existen subclases con diferentes reglas.
    public double calcularCuotaMensual() {
        // Uso un switch para devolver el valor según la categoría del socio.
        switch (categoria.toLowerCase()) {
            case "familiar":
                return 4500.0; // los socios familiares pagan una cuota mayor
            case "vitalicio":
                return 0.0;    // los socios vitalicios no pagan cuota
            default:
                return 3000.0; // el resto de las categorías pagan la cuota base
        }
    }
}
