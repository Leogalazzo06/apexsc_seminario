package apexsc;

// Subclase que hereda de Usuario
public class Administrador extends Usuario {

    public Administrador(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña, "Administrador");
    }

    // Implementación del método abstracto
    @Override
    public void mostrarMenu() {
        System.out.println("\n--- Menú del Administrador ---");
        System.out.println("1. Gestionar Socios");
        System.out.println("2. Gestionar Eventos");
        System.out.println("3. Reportes Generales");
        System.out.println("4. Configuración del Sistema");
    }
}
