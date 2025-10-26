package apexsc;

// Esta es una clase abstracta que representa a un usuario dentro del sistema.
// No se puede instanciar directamente, sino que sirve como base para otras clases como Cajero o Administrador.
public abstract class Usuario {
    // --- Atributos privados ---
    // Uso encapsulamiento para proteger la información de los usuarios.
    private String nombreUsuario;  // nombre de usuario con el que se inicia sesión
    private String contraseña;     // contraseña del usuario
    private String rol;            // rol dentro del sistema (por ejemplo: Cajero, Administrador, etc.)

    // --- Constructor ---
    // Cuando creo un usuario (a través de una subclase), asigno los valores básicos.
    public Usuario(String nombreUsuario, String contraseña, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    // --- Getters y Setters ---
    // Permiten acceder y modificar los atributos privados cuando sea necesario.
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // --- Método abstracto ---
    // Este método no tiene implementación aquí, ya que cada tipo de usuario
    // (como Cajero o Administrador) debe definir su propio menú.
    public abstract void mostrarMenu();

    // --- Método común para todos los usuarios ---
    // Este método muestra información general del usuario, sin importar el tipo que sea.
    public void mostrarInfo() {
        System.out.println("\n--- Información de Usuario ---");
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Rol: " + rol);
    }
}
