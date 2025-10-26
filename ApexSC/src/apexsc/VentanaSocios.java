package apexsc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Esta clase representa la ventana de gestión de socios del sistema.
// Desde aquí puedo registrar, editar, eliminar y buscar socios.
public class VentanaSocios extends JFrame {

    // --- Atributos principales ---
    private final JTextField txtNombre, txtDni, txtCategoria, txtBuscar;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaSocios;
    private final List<Socio> listaSocios;
    private int filaSeleccionada = -1; // almacena el índice de la fila seleccionada

    // --- Constructor ---
    public VentanaSocios() {
        setTitle("Gestión de Socios");
        setSize(800, 540);
        setLocationRelativeTo(null); // centro la ventana
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        // Defino los colores principales de la interfaz
        Color azul = new Color(0, 51, 102);
        Color dorado = new Color(218, 165, 32);
        Color fondo = new Color(245, 247, 255);

        listaSocios = new ArrayList<>();

        // --- Panel superior: formulario y botones ---
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(fondo);
        top.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(fondo);
        form.setBorder(BorderFactory.createTitledBorder("Registrar / Editar Socio"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de texto
        txtNombre = new JTextField(16);
        txtDni = new JTextField(16);
        txtCategoria = new JTextField(16);

        // Primera fila: nombre y DNI
        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; form.add(txtNombre, gbc);
        gbc.gridx = 2; form.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 3; form.add(txtDni, gbc);

        // Segunda fila: categoría
        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; form.add(txtCategoria, gbc);
        gbc.gridwidth = 1;

        // Panel de botones principales
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

        // --- Panel central: búsqueda y tabla ---
        JPanel center = new JPanel(new BorderLayout(0, 8));
        center.setBackground(fondo);
        center.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        busqueda.setBackground(fondo);
        busqueda.add(new JLabel("Buscar socio (nombre o DNI):"));
        txtBuscar = new JTextField(24);
        JButton btnBuscar = crearBoton("Buscar", azul, dorado);
        busqueda.add(txtBuscar);
        busqueda.add(btnBuscar);

        // Configuro la tabla
        String[] columnas = {"Nombre", "DNI", "Categoría"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaSocios = new JTable(modeloTabla);
        tablaSocios.setFillsViewportHeight(true);
        tablaSocios.setAutoCreateRowSorter(true);
        JScrollPane scroll = new JScrollPane(tablaSocios);
        scroll.setBorder(BorderFactory.createTitledBorder("Listado de Socios"));

        center.add(busqueda, BorderLayout.NORTH);
        center.add(scroll, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        // --- Asocio los eventos a los botones ---
        btnAgregar.addActionListener(e -> agregarSocio());
        btnEliminar.addActionListener(e -> eliminarSocio());
        btnActualizar.addActionListener(e -> actualizarSocio());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnBuscar.addActionListener(e -> buscarSocio());
        tablaSocios.getSelectionModel().addListSelectionListener(e -> cargarSeleccion());

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

    // Agrega un nuevo socio a la lista y a la tabla
    private void agregarSocio() {
        String nombre = txtNombre.getText().trim();
        String dni = txtDni.getText().trim();
        String categoria = txtCategoria.getText().trim();

        if (categoria.isEmpty()) categoria = "General";

        try {
            if (nombre.isEmpty() || dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar el nombre y el DNI.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Socio socio = new Socio(nombre, dni, categoria);
            listaSocios.add(socio);
            modeloTabla.addRow(new Object[]{socio.getNombre(), socio.getDni(), socio.getCategoria()});
            limpiarCamposSoloInputs();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar socio:\n" + ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga los datos del socio seleccionado en los campos
    private void cargarSeleccion() {
        filaSeleccionada = tablaSocios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int modelIndex = tablaSocios.convertRowIndexToModel(filaSeleccionada);
            Socio socio = listaSocios.get(modelIndex);
            txtNombre.setText(socio.getNombre());
            txtDni.setText(socio.getDni());
            txtCategoria.setText(socio.getCategoria());
        }
    }

    // Actualiza la información de un socio existente
    private void actualizarSocio() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un socio para editar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText().trim();
        String dni = txtDni.getText().trim();
        String categoria = txtCategoria.getText().trim();
        if (categoria.isEmpty()) categoria = "General";

        try {
            Socio socio = listaSocios.get(tablaSocios.convertRowIndexToModel(filaSeleccionada));
            socio.setNombre(nombre);
            socio.setDni(dni);
            socio.setCategoria(categoria);

            // Actualizo la tabla visualmente
            modeloTabla.setValueAt(nombre, filaSeleccionada, 0);
            modeloTabla.setValueAt(dni, filaSeleccionada, 1);
            modeloTabla.setValueAt(categoria, filaSeleccionada, 2);

            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
            limpiarCamposSoloInputs();
            filaSeleccionada = -1;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar socio:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Elimina un socio de la lista y la tabla
    private void eliminarSocio() {
        int fila = tablaSocios.getSelectedRow();
        if (fila >= 0) {
            int modelIndex = tablaSocios.convertRowIndexToModel(fila);
            listaSocios.remove(modelIndex);
            modeloTabla.removeRow(modelIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un socio para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Limpia los campos del formulario
    private void limpiarCamposSoloInputs() {
        txtNombre.setText("");
        txtDni.setText("");
        txtCategoria.setText("");
        txtNombre.requestFocus();
    }

    // Limpia todos los campos y restaura la tabla
    private void limpiarCampos() {
        limpiarCamposSoloInputs();
        txtBuscar.setText("");
        actualizarTabla(listaSocios);
        filaSeleccionada = -1;
    }

    // Busca socios por nombre o DNI
    private void buscarSocio() {
        String q = txtBuscar.getText().trim().toLowerCase();
        if (q.isEmpty()) {
            actualizarTabla(listaSocios);
            return;
        }
        List<Socio> filtrados = new ArrayList<>();
        for (Socio s : listaSocios) {
            if (s.getNombre().toLowerCase().contains(q) || s.getDni().toLowerCase().contains(q)) {
                filtrados.add(s);
            }
        }
        actualizarTabla(filtrados);
    }

    // Actualiza los datos de la tabla con una lista específica de socios
    private void actualizarTabla(List<Socio> lista) {
        modeloTabla.setRowCount(0);
        for (Socio s : lista) {
            modeloTabla.addRow(new Object[]{s.getNombre(), s.getDni(), s.getCategoria()});
        }
    }
}
