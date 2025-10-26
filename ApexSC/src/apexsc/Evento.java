package apexsc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Esta clase representa un evento del club, que puede ser deportivo o social.
public class Evento {
    // --- Atributos privados ---
    // Uso encapsulamiento para proteger los datos del evento.
    private String nombre;              // nombre del evento
    private LocalDate fecha;            // fecha en que se realiza
    private int capacidadMaxima;        // cantidad máxima de personas permitidas
    private int entradasVendidas;       // contador de entradas vendidas
    private double precioEntrada;       // precio por entrada
    private List<Socio> asistentes;     // lista de socios que compraron entrada

    // --- Constructor ---
    // Cuando creo un nuevo evento, valido los datos para asegurar que sean correctos.
    public Evento(String nombre, LocalDate fecha, int capacidadMaxima, double precioEntrada) throws Exception {
        // Valido que el nombre no sea nulo ni esté vacío.
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre del evento no puede estar vacío.");
        }
        // Valido que la capacidad sea mayor a cero.
        if (capacidadMaxima <= 0) {
            throw new Exception("La capacidad del evento debe ser mayor que cero.");
        }
        // Valido que el precio no sea negativo.
        if (precioEntrada < 0) {
            throw new Exception("El precio de la entrada no puede ser negativo.");
        }

        // Si todo está correcto, asigno los valores a los atributos.
        this.nombre = nombre;
        this.fecha = fecha;
        this.capacidadMaxima = capacidadMaxima;
        this.precioEntrada = precioEntrada;
        this.entradasVendidas = 0;       // al crear el evento, aún no hay entradas vendidas
        this.asistentes = new ArrayList<>(); // inicio la lista vacía de asistentes
    }

    // --- Getters y Setters ---
    // Me permiten acceder a la información del evento desde otras clases.
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public List<Socio> getAsistentes() {
        return asistentes;
    }

    // --- Método para vender una entrada ---
    // Este método permite registrar la compra de una entrada por parte de un socio.
    public void venderEntrada(Socio socio) {
        // Verifico que el socio sea válido.
        if (socio == null) {
            System.out.println("Error: el socio no es válido.");
            return;
        }
        // Si ya se alcanzó la capacidad máxima, no se pueden vender más entradas.
        if (entradasVendidas >= capacidadMaxima) {
            System.out.println("No se pueden vender más entradas. Capacidad alcanzada.");
            return;
        }

        // Si todo está bien, agrego el socio a la lista de asistentes.
        asistentes.add(socio);
        entradasVendidas++; // incremento el contador de entradas vendidas
        System.out.println("Entrada vendida a: " + socio.getNombre());
    }

    // --- Mostrar información del evento ---
    // Este método muestra en consola todos los datos importantes del evento.
    public void mostrarInfo() {
        System.out.println("\n--- Información del Evento ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Capacidad: " + capacidadMaxima);
        System.out.println("Entradas vendidas: " + entradasVendidas);
        System.out.println("Entradas disponibles: " + (capacidadMaxima - entradasVendidas));
        System.out.println("Precio de entrada: $" + precioEntrada);

        // Si hay asistentes, los muestro uno por uno.
        if (!asistentes.isEmpty()) {
            System.out.println("\nLista de asistentes:");
            for (Socio s : asistentes) {
                System.out.println("- " + s.getNombre() + " (" + s.getCategoria() + ")");
            }
        } else {
            // Si no hay asistentes, informo que aún no se vendieron entradas.
            System.out.println("Aún no hay asistentes registrados.");
        }
    }

    // --- Cancelar evento ---
    // Este método permite cancelar un evento y realizar el "reembolso" simbólico.
    public void cancelarEvento() {
        System.out.println("\n Evento '" + nombre + "' cancelado. Devolviendo entradas...");
        // Recorro la lista de asistentes y simulo el reembolso.
        for (Socio s : asistentes) {
            System.out.println("Reembolso emitido para: " + s.getNombre());
        }
        // Limpio la lista de asistentes y reinicio el contador de entradas.
        asistentes.clear();
        entradasVendidas = 0;
        System.out.println("Evento cancelado correctamente.");
    }

    // --- Getters adicionales usados por la interfaz gráfica ---
    // Estos métodos devuelven los mismos datos con nombres distintos,
    // pensados para que sean compatibles con otras partes del programa.
    public int getCapacidad() {
        return capacidadMaxima;
    }

    public double getPrecio() {
        return precioEntrada;
    }
}
