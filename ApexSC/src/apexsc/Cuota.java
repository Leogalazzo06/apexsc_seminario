package apexsc;

import java.time.LocalDate;

// Esta clase representa una cuota social que pertenece a un socio del club.
public class Cuota {
    // Declaro los atributos privados de la clase.
    // Cada cuota tiene un socio asociado, un monto, una fecha de emisión, una fecha de vencimiento y un estado de pago.
    private Socio socio;
    private double monto;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private boolean pagada;

    // --- Constructor ---
    // Cuando creo una nueva cuota, debo pasar el socio, el monto y las fechas.
    // También agrego validaciones para asegurarme de que los datos sean correctos.
    public Cuota(Socio socio, double monto, LocalDate fechaEmision, LocalDate fechaVencimiento) throws Exception {
        if (socio == null) {
            throw new Exception("Debe asignarse un socio válido a la cuota."); // si el socio no existe, lanzo un error
        }
        if (monto <= 0) {
            throw new Exception("El monto de la cuota debe ser mayor que cero."); // el monto debe ser positivo
        }

        // Asigno los valores a los atributos
        this.socio = socio;
        this.monto = monto;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = false; // por defecto, toda cuota nueva empieza como no pagada
    }

    // --- Getters y Setters ---
    // Estos métodos me permiten acceder y modificar los valores privados desde fuera de la clase.

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    // Agrego este setter para poder cambiar la fecha de vencimiento cuando sea necesario.
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    // --- Métodos de comportamiento ---
    // Acá defino las acciones que una cuota puede realizar o comprobar.

    // Este método registra el pago de la cuota.
    public void registrarPago() {
        if (pagada) {
            // Si ya está pagada, informo que no se puede volver a pagar.
            System.out.println("La cuota ya se encuentra pagada.");
        } else {
            // Si no estaba pagada, cambio el estado y muestro un mensaje de confirmación.
            pagada = true;
            System.out.println("Cuota abonada correctamente por el socio: " + socio.getNombre());
        }
    }

    // Este método me permite saber si una cuota está vencida.
    public boolean estaVencida() {
        // Compruebo si la fecha actual es posterior a la fecha de vencimiento y además no está pagada.
        return LocalDate.now().isAfter(fechaVencimiento) && !pagada;
    }

    // Este método muestra por consola toda la información de la cuota.
    public void mostrarInfo() {
        System.out.println("\n--- Información de la Cuota ---");
        System.out.println("Socio: " + socio.getNombre());
        System.out.println("Monto: $" + monto);
        System.out.println("Emitida: " + fechaEmision);
        System.out.println("Vence: " + fechaVencimiento);

        // Según el estado, muestro si está pagada, vencida o pendiente.
        System.out.println("Estado: " + (pagada ? "Pagada" : (estaVencida() ? "Vencida" : "Pendiente")));
    }
}
