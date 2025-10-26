package apexsc;

// Esta clase representa a un cajero del sistema.
// Hereda de la clase abstracta Usuario y define su propio comportamiento.
public class Cajero extends Usuario {

    // --- Constructor ---
    // Cuando creo un objeto Cajero, llamo al constructor de la clase Usuario con los datos necesarios.
    // Le paso el nombre de usuario, la contraseña y el rol que en este caso es "Cajero".
    public Cajero(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña, "Cajero");
    }

    // --- Implementación del método abstracto ---
    // Sobrescribo el método mostrarMenu() definido en la clase Usuario.
    // Este método muestra las opciones específicas que puede realizar un cajero dentro del sistema.
    @Override
    public void mostrarMenu() {
        System.out.println("\n--- Menú del Cajero ---");
        System.out.println("1. Cobrar Cuotas");
        System.out.println("2. Emitir Recibos");
        System.out.println("3. Consultar Pagos");
    }
}
