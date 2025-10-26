package apexsc;

import java.time.LocalDate;

// Esta clase representa un abono a la pileta del club.
// Hereda de la clase Socio, por lo que reutiliza sus atributos y métodos.
public class AbonoPileta extends Socio {
    // --- Atributos propios del abono ---
    private LocalDate fechaInicio;  // fecha en que comienza el abono
    private LocalDate fechaFin;     // fecha en que termina el abono
    private double precioAbono;     // precio total del abono

    // --- Constructor ---
    // Creo un abono de pileta asociado a un socio.
    // Uso super() para aprovechar el constructor de la clase Socio.
    public AbonoPileta(String nombre, String dni, String categoria,
                       LocalDate fechaInicio, LocalDate fechaFin, double precioAbono) throws Exception {
        super(nombre, dni, categoria); // llamo al constructor de la clase Socio

        // Verifico que la fecha de fin no sea anterior a la de inicio.
        if (fechaFin.isBefore(fechaInicio)) {
            throw new Exception("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        // Verifico que el precio del abono sea válido.
        if (precioAbono <= 0) {
            throw new Exception("El precio del abono debe ser mayor a cero.");
        }

        // Asigno los valores a los atributos.
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioAbono = precioAbono;
    }

    // --- Getters ---
    // Devuelven los datos del abono cuando se los necesita en otras partes del programa.
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getPrecioAbono() {
        return precioAbono;
    }

    // --- Método para verificar si el abono está vigente ---
    // Devuelve true si la fecha actual está dentro del período de validez del abono.
    public boolean estaVigente() {
        LocalDate hoy = LocalDate.now();
        return (hoy.isAfter(fechaInicio) || hoy.isEqual(fechaInicio)) && hoy.isBefore(fechaFin);
    }

    // --- Sobrescritura de método (polimorfismo) ---
    // Modifico el comportamiento del método calcularCuotaMensual() de la clase Socio.
    @Override
    public double calcularCuotaMensual() {
        // Los socios con abono de pileta tienen una cuota base más una parte proporcional del abono.
        // Divido el precio total del abono en 4 semanas para simular un cobro mensual.
        return super.calcularCuotaMensual() + (precioAbono / 4);
    }

    // --- Método para mostrar la información del abono ---
    // Imprime en consola todos los datos relevantes del abono de pileta.
    public void mostrarAbono() {
        System.out.println("\n--- Abono de Pileta ---");
        System.out.println("Socio: " + getNombre() + " (" + getCategoria() + ")");
        System.out.println("Período: " + fechaInicio + " al " + fechaFin);
        System.out.println("Precio total del abono: $" + precioAbono);
        System.out.println("Estado: " + (estaVigente() ? "Vigente" : "Vencido"));
        System.out.println("Cuota mensual con abono: $" + calcularCuotaMensual());
    }
}
