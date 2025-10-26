package apexsc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Esta clase representa la ventana de gestión de cuotas del sistema.
// Permite agregar, modificar, eliminar y buscar cuotas asociadas a socios.
public class VentanaCuotas extends JFrame {

    // --- Atributos principales de la ventana ---
    private final JTextField txtSocio, txtMonto, txtBuscar; // campos de texto
    private final DefaultTableModel modeloTabla;            // modelo de datos de la tabla
    private final JTable tablaCuotas;                       // tabla que muestra las cuotas
    private final List<Cuota> listaCuotas;                  // lista interna donde guardo las cuotas
    private int filaSeleccionada = -1;                      // índice de la fila seleccionada

    // --- Constructor ---
    public VentanaCuotas() {
        setTitle("Gestión de Cuotas");
        setSize(800, 540);
        setLocationRelativeTo(null); // centro la ventana en pantalla
        setResizable(false);
        setLayout(new BorderLayout(10, 10)); // estructura principal de la interfaz

        // Defino colores de la interfaz
        Color azul = new Color(0, 51, 102);
        Color dorado = new Color(218, 165, 32);
        Color fondo = new Color(245, 247, 255);

        listaCuotas = new ArrayList<>();

        // --- Panel superior con formulario y botones ---
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(fondo);
        top.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Formulario para ingresar los datos de la cuota
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(fondo);
        form.setBorder(BorderFactory.createTitledBorder("Registrar / Editar Cuota"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtSocio = new JTextField(16);
        txtMonto = new JTextField(10);

        // Fila 1 del formulario
        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Socio:"), gbc);
        gbc.gridx = 1; form.add(txtSocio, gbc);
        gbc.gridx = 2; form.add(new JLabel("Monto:"), gbc);
        gbc.gridx = 3; form.add(txtMonto, gbc);

        // --- Campos de fecha de vencimiento (día, mes, año) ---
        JComboBox<Integer> cbDia = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cbDia.addItem(i);
        JComboBox<Integer> cbMes = new JComboBox<>();
        for (int i = 1; i <= 12; i++) cbMes.addItem(i);
        JComboBox<Integer> cbAnio = new JComboBox<>();
        int anioActual = LocalDate.now().getYear();
        for (int i = anioActual; i <= anioActual + 5; i++) cbAnio.addItem(i);

        // Fila 2 del formulario
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Fecha de Vencimiento:"), gbc);
        gbc.gridx = 1; form.add(cbDia, gbc);
        gbc.gridx = 2; form.add(cbMes, gbc);
        gbc.gridx = 3; form.add(cbAnio, gbc);

        // Panel con botones de acción
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setBackground(fondo);
        JButton btnAgregar = crearBoton("Agregar", azul, dorado);
        JButton btnActualizar = crearBoton("Actualizar", azul, dorado);
        JButton btnEliminar = crearBoton("Eliminar", azul, dorado);
        JButton btnLimpiar = crearBoton("Limpiar", azul, dorado);
        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        top.add(form, BorderLayout.CENTER);
        top.add(botones, BorderLayout.SOUTH);

        // --- Panel central con búsqueda y tabla ---
        JPanel center = new JPanel(new BorderLayout(0, 8));
        center.setBackground(fondo);
        center.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        busqueda.setBackground(fondo);
        busqueda.add(new JLabel("Buscar cuota (socio o estado):"));
        txtBuscar = new JTextField(24);
        JButton btnBuscar = crearBoton("Buscar", azul, dorado);
        busqueda.add(txtBuscar);
        busqueda.add(btnBuscar);

        // Configuro la tabla con sus columnas
        String[] columnas = {"Socio", "Monto", "Fecha Vencimiento", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCuotas = new JTable(modeloTabla);
        tablaCuotas.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tablaCuotas);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de Cuotas"));

        center.add(busqueda, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        // --- Asocio los eventos a los botones y la tabla ---
        btnAgregar.addActionListener(e -> agregarCuota(cbDia, cbMes, cbAnio));
        btnActualizar.addActionListener(e -> actualizarCuota(cbDia, cbMes, cbAnio));
        btnEliminar.addActionListener(e -> eliminarCuota());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnBuscar.addActionListener(e -> buscarCuota());
        tablaCuotas.getSelectionModel().addListSelectionListener(e -> cargarSeleccion(cbDia, cbMes, cbAnio));

        setVisible(true);
    }

    // --- Método auxiliar para crear botones con estilo uniforme ---
    private JButton crearBoton(String texto, Color fondo, Color borde) {
        JButton b = new JButton(texto);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setBackground(fondo);
        b.setForeground(Color.WHITE);
        b.setBorder(BorderFactory.createLineBorder(borde, 2));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    // --- CRUD: Crear, Leer, Actualizar, Eliminar ---

    // Agrego una nueva cuota a la lista y a la tabla
    private void agregarCuota(JComboBox<Integer> cbDia, JComboBox<Integer> cbMes, JComboBox<Integer> cbAnio) {
        try {
            String socio = txtSocio.getText().trim();
            double monto = Double.parseDouble(txtMonto.getText().trim());
            LocalDate fecha = LocalDate.of(
                    (Integer) cbAnio.getSelectedItem(),
                    (Integer) cbMes.getSelectedItem(),
                    (Integer) cbDia.getSelectedItem()
            );

            if (socio.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del socio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cuota c = new Cuota(new Socio(socio, "N/A", "General"), monto, LocalDate.now(), fecha);
            listaCuotas.add(c);
            modeloTabla.addRow(new Object[]{
                    c.getSocio().getNombre(),
                    c.getMonto(),
                    c.getFechaVencimiento(),
                    estadoCuota(c)
            });

            limpiarCamposSoloInputs();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar cuota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga los datos de la fila seleccionada en los campos de texto
    private void cargarSeleccion(JComboBox<Integer> cbDia, JComboBox<Integer> cbMes, JComboBox<Integer> cbAnio) {
        filaSeleccionada = tablaCuotas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idx = tablaCuotas.convertRowIndexToModel(filaSeleccionada);
            Cuota c = listaCuotas.get(idx);
            txtSocio.setText(c.getSocio().getNombre());
            txtMonto.setText(String.valueOf(c.getMonto()));
            cbDia.setSelectedItem(c.getFechaVencimiento().getDayOfMonth());
            cbMes.setSelectedItem(c.getFechaVencimiento().getMonthValue());
            cbAnio.setSelectedItem(c.getFechaVencimiento().getYear());
        }
    }

    // Actualiza la información de una cuota seleccionada
    private void actualizarCuota(JComboBox<Integer> cbDia, JComboBox<Integer> cbMes, JComboBox<Integer> cbAnio) {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cuota para actualizar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String socio = txtSocio.getText().trim();
            double monto = Double.parseDouble(txtMonto.getText().trim());
            LocalDate nuevaFecha = LocalDate.of(
                    (Integer) cbAnio.getSelectedItem(),
                    (Integer) cbMes.getSelectedItem(),
                    (Integer) cbDia.getSelectedItem()
            );

            Cuota cuota = listaCuotas.get(tablaCuotas.convertRowIndexToModel(filaSeleccionada));
            cuota.setSocio(new Socio(socio, "N/A", "General"));
            cuota.setMonto(monto);
            cuota.setFechaVencimiento(nuevaFecha);

            modeloTabla.setValueAt(socio, filaSeleccionada, 0);
            modeloTabla.setValueAt(monto, filaSeleccionada, 1);
            modeloTabla.setValueAt(nuevaFecha, filaSeleccionada, 2);
            modeloTabla.setValueAt(estadoCuota(cuota), filaSeleccionada, 3);

            JOptionPane.showMessageDialog(this, "Cuota actualizada correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposSoloInputs();
            filaSeleccionada = -1;

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El monto debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar cuota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina una cuota seleccionada de la lista y la tabla
    private void eliminarCuota() {
        int fila = tablaCuotas.getSelectedRow();
        if (fila >= 0) {
            int idx = tablaCuotas.convertRowIndexToModel(fila);
            listaCuotas.remove(idx);
            modeloTabla.removeRow(idx);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una cuota para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    // --- Métodos auxiliares ---

    // Limpia solo los campos de texto principales
    private void limpiarCamposSoloInputs() {
        txtSocio.setText("");
        txtMonto.setText("");
        txtSocio.requestFocus();
    }

    // Limpia todos los campos incluyendo búsqueda y selección
    private void limpiarCampos() {
        limpiarCamposSoloInputs();
        txtBuscar.setText("");
        actualizarTabla(listaCuotas);
        filaSeleccionada = -1;
    }

    // Busca cuotas por nombre del socio o estado
    private void buscarCuota() {
        String q = txtBuscar.getText().trim().toLowerCase();
        if (q.isEmpty()) {
            actualizarTabla(listaCuotas);
            return;
        }
        List<Cuota> filtradas = new ArrayList<>();
        for (Cuota c : listaCuotas) {
            String estado = estadoCuota(c).toLowerCase();
            if (c.getSocio().getNombre().toLowerCase().contains(q) || estado.contains(q)) {
                filtradas.add(c);
            }
        }
        actualizarTabla(filtradas);
    }

    // Actualiza los datos mostrados en la tabla según la lista recibida
    private void actualizarTabla(List<Cuota> lista) {
        modeloTabla.setRowCount(0);
        for (Cuota c : lista) {
            modeloTabla.addRow(new Object[]{
                    c.getSocio().getNombre(),
                    c.getMonto(),
                    c.getFechaVencimiento(),
                    estadoCuota(c)
            });
        }
    }

    // Devuelve una cadena que representa el estado actual de la cuota
    private String estadoCuota(Cuota c) {
        if (c.isPagada()) return "Pagada";
        if (c.estaVencida()) return "Vencida";
        return "Pendiente";
    }
}
