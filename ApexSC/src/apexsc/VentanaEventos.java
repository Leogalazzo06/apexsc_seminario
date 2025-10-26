package apexsc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Esta clase representa la ventana de gestión de eventos del sistema.
// Permite agregar, editar, eliminar y buscar eventos del club.
public class VentanaEventos extends JFrame {

    // --- Atributos principales ---
    private final JTextField txtNombre, txtCapacidad, txtPrecio, txtBuscar;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaEventos;
    private final List<Evento> listaEventos;
    private int filaSeleccionada = -1;

    // --- Constructor ---
    public VentanaEventos() {
        setTitle("Gestión de Eventos");
        setSize(800, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        // Defino los colores de la interfaz
        Color azul = new Color(0, 51, 102);
        Color dorado = new Color(218, 165, 32);
        Color fondo = new Color(245, 247, 255);

        listaEventos = new ArrayList<>();

        // --- Panel superior (formulario y botones) ---
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(fondo);
        top.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Formulario para ingresar los datos de un evento
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(fondo);
        form.setBorder(BorderFactory.createTitledBorder("Registrar / Editar Evento"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(18);
        txtCapacidad = new JTextField(8);
        txtPrecio = new JTextField(8);

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtNombre, gbc);
        gbc.gridwidth = 1;

        // Campos para seleccionar la fecha (día, mes, año)
        JComboBox<Integer> cbDia = new JComboBox<>();
        for (int i = 1; i <= 31; i++) cbDia.addItem(i);
        JComboBox<Integer> cbMes = new JComboBox<>();
        for (int i = 1; i <= 12; i++) cbMes.addItem(i);
        JComboBox<Integer> cbAnio = new JComboBox<>();
        int anioActual = LocalDate.now().getYear();
        for (int i = anioActual; i <= anioActual + 3; i++) cbAnio.addItem(i);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Fecha del Evento:"), gbc);
        gbc.gridx = 1; form.add(cbDia, gbc);
        gbc.gridx = 2; form.add(cbMes, gbc);
        gbc.gridx = 3; form.add(cbAnio, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Capacidad:"), gbc);
        gbc.gridx = 1; form.add(txtCapacidad, gbc);
        gbc.gridx = 2; form.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 3; form.add(txtPrecio, gbc);

        // Panel con los botones principales
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

        // --- Panel central (buscador y tabla) ---
        JPanel center = new JPanel(new BorderLayout(0, 8));
        center.setBackground(fondo);
        center.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        busqueda.setBackground(fondo);
        busqueda.add(new JLabel("Buscar evento (nombre o fecha):"));
        txtBuscar = new JTextField(24);
        JButton btnBuscar = crearBoton("Buscar", azul, dorado);
        busqueda.add(txtBuscar);
        busqueda.add(btnBuscar);

        // Configuración de la tabla
        String[] columnas = {"Nombre", "Fecha", "Capacidad", "Precio"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaEventos = new JTable(modeloTabla);
        tablaEventos.setFillsViewportHeight(true);
        tablaEventos.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(tablaEventos);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de Eventos"));

        center.add(busqueda, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        // --- Asocio los eventos a los botones y tabla ---
        btnAgregar.addActionListener(e -> agregarEvento(cbDia, cbMes, cbAnio));
        btnActualizar.addActionListener(e -> actualizarEvento(cbDia, cbMes, cbAnio));
        btnEliminar.addActionListener(e -> eliminarEvento());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnBuscar.addActionListener(e -> buscarEvento());
        tablaEventos.getSelectionModel().addListSelectionListener(e -> cargarSeleccion(cbDia, cbMes, cbAnio));

        setVisible(true);
    }

    // --- Método para crear botones con estilo uniforme ---
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

    // --- CRUD: Crear, Leer, Actualizar y Eliminar ---

    // Agrega un nuevo evento a la lista y a la tabla
    private void agregarEvento(JComboBox<Integer> cbDia, JComboBox<Integer> cbMes, JComboBox<Integer> cbAnio) {
        try {
            String nombre = txtNombre.getText().trim();
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            double precio = Double.parseDouble(txtPrecio.getText().trim());

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate fecha = LocalDate.of(
                    (Integer) cbAnio.getSelectedItem(),
                    (Integer) cbMes.getSelectedItem(),
                    (Integer) cbDia.getSelectedItem()
            );

            Evento evento = new Evento(nombre, fecha, capacidad, precio);
            listaEventos.add(evento);
            modeloTabla.addRow(new Object[]{
                    evento.getNombre(),
                    evento.getFecha(),
                    evento.getCapacidad(),
                    evento.getPrecio()
            });
            limpiarCamposSoloInputs();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Capacidad y precio deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga los datos del evento seleccionado en los campos de texto
    private void cargarSeleccion(JComboBox<Integer> cbDia, JComboBox<Integer> cbMes, JComboBox<Integer> cbAnio) {
        filaSeleccionada = tablaEventos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int modelIndex = tablaEventos.convertRowIndexToModel(filaSeleccionada);
            Evento evento = listaEventos.get(modelIndex);
            txtNombre.setText(evento.getNombre());
            txtCapacidad.setText(String.valueOf(evento.getCapacidad()));
            txtPrecio.setText(String.valueOf(evento.getPrecio()));
            cbDia.setSelectedItem(evento.getFecha().getDayOfMonth());
            cbMes.setSelectedItem(evento.getFecha().getMonthValue());
            cbAnio.setSelectedItem(evento.getFecha().getYear());
        }
    }

    // Actualiza un evento seleccionado
    private void actualizarEvento(JComboBox<Integer> cbDia, JComboBox<Integer> cbMes, JComboBox<Integer> cbAnio) {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un evento para actualizar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String nombre = txtNombre.getText().trim();
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            LocalDate fecha = LocalDate.of(
                    (Integer) cbAnio.getSelectedItem(),
                    (Integer) cbMes.getSelectedItem(),
                    (Integer) cbDia.getSelectedItem()
            );

            Evento evento = new Evento(nombre, fecha, capacidad, precio);
            listaEventos.set(tablaEventos.convertRowIndexToModel(filaSeleccionada), evento);

            modeloTabla.setValueAt(nombre, filaSeleccionada, 0);
            modeloTabla.setValueAt(fecha, filaSeleccionada, 1);
            modeloTabla.setValueAt(capacidad, filaSeleccionada, 2);
            modeloTabla.setValueAt(precio, filaSeleccionada, 3);

            JOptionPane.showMessageDialog(this, "Evento actualizado correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposSoloInputs();
            filaSeleccionada = -1;

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Capacidad y precio deben ser numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina un evento seleccionado de la lista y la tabla
    private void eliminarEvento() {
        int fila = tablaEventos.getSelectedRow();
        if (fila >= 0) {
            int idx = tablaEventos.convertRowIndexToModel(fila);
            listaEventos.remove(idx);
            modeloTabla.removeRow(idx);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un evento.", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    // --- Métodos auxiliares ---

    // Limpia los campos de texto del formulario
    private void limpiarCamposSoloInputs() {
        txtNombre.setText("");
        txtCapacidad.setText("");
        txtPrecio.setText("");
        txtNombre.requestFocus();
    }

    // Limpia todos los campos incluyendo la búsqueda
    private void limpiarCampos() {
        limpiarCamposSoloInputs();
        txtBuscar.setText("");
        actualizarTabla(listaEventos);
        filaSeleccionada = -1;
    }

    // Busca eventos por nombre o fecha
    private void buscarEvento() {
        String q = txtBuscar.getText().trim().toLowerCase();
        if (q.isEmpty()) {
            actualizarTabla(listaEventos);
            return;
        }
        List<Evento> filtrados = new ArrayList<>();
        for (Evento e : listaEventos) {
            if (e.getNombre().toLowerCase().contains(q) ||
                e.getFecha().toString().contains(q)) {
                filtrados.add(e);
            }
        }
        actualizarTabla(filtrados);
    }

    // Actualiza la tabla con una lista específica de eventos
    private void actualizarTabla(List<Evento> lista) {
        modeloTabla.setRowCount(0);
        for (Evento e : lista) {
            modeloTabla.addRow(new Object[]{
                    e.getNombre(),
                    e.getFecha(),
                    e.getCapacidad(),
                    e.getPrecio()
            });
        }
    }
}
