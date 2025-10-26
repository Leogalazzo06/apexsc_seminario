package apexsc;

import javax.swing.*;
import java.awt.*;

public class ApexSC extends JFrame {

    public ApexSC() {
        // Establezco las configuraciones básicas de la ventana principal
        setTitle("Apex SC - Sistema de Gestión");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centro la ventana en la pantalla
        setResizable(false); // no permito redimensionar

        // --- Defino los colores que voy a usar en toda la interfaz ---
        Color azulPrincipal = new Color(0, 51, 102); // azul oscuro principal
        Color dorado = new Color(218, 165, 32);      // dorado para bordes y efectos
        Color fondo = new Color(245, 247, 255);      // color claro de fondo

        // --- Creo el panel principal que contendrá todos los elementos ---
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 12, 12)); // 8 filas, una para el título y 7 para botones
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60)); // margen interno

        // --- Agrego el título principal ---
        JLabel titulo = new JLabel("APEX SC", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(azulPrincipal);
        panel.add(titulo);

        // --- Creo los botones del menú principal ---
        JButton btnSocios = crearBoton("Gestión de Socios", azulPrincipal, dorado);
        JButton btnCuotas = crearBoton("Gestión de Cuotas", azulPrincipal, dorado);
        JButton btnEventos = crearBoton("Gestión de Eventos", azulPrincipal, dorado);
        JButton btnPileta = crearBoton("Abonos de Pileta", azulPrincipal, dorado);
        JButton btnUsuarios = crearBoton("Usuarios del Sistema", azulPrincipal, dorado);
        JButton btnInfo = crearBoton("Acerca del Sistema", azulPrincipal, dorado);
        JButton btnSalir = crearBoton("Salir", azulPrincipal, dorado);

        // --- Agrego los botones al panel ---
        panel.add(btnSocios);
        panel.add(btnCuotas);
        panel.add(btnEventos);
        panel.add(btnPileta);
        panel.add(btnUsuarios);
        panel.add(btnInfo);
        panel.add(btnSalir);

        // --- Defino las acciones que realiza cada botón ---

        // Abre la ventana de gestión de socios
        btnSocios.addActionListener(e -> new VentanaSocios());

        // Abre la ventana de gestión de cuotas
        btnCuotas.addActionListener(e -> new VentanaCuotas());

        // Abre la ventana de eventos
        btnEventos.addActionListener(e -> new VentanaEventos());

        // Abre una ventana genérica (aún no implementada) para abonos de pileta
        btnPileta.addActionListener(e -> new VentanaGenerica("Gestión de Abonos de Pileta"));

        // Abre una ventana genérica para la sección de usuarios
        btnUsuarios.addActionListener(e -> new VentanaGenerica("Usuarios del Sistema"));

        // Muestra información sobre el sistema en un cuadro de diálogo
        btnInfo.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                Sistema de Gestión - Apex SC
                Versión 1.0
                Desarrollado por Leonel Agustín Galazzo
                """,
                "Información del Sistema", JOptionPane.INFORMATION_MESSAGE));

        // Muestra un cuadro de confirmación antes de salir del sistema
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Desea salir del sistema?",
                    "Salir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        // Agrego el panel completo a la ventana
        add(panel);
    }

    // --- Método auxiliar para crear botones con el mismo estilo ---
    private JButton crearBoton(String texto, Color fondo, Color borde) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false); // elimino el borde de enfoque
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setBackground(fondo);
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createLineBorder(borde, 2)); // borde dorado
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // cambio el cursor al pasar por encima

        // --- Agrego un efecto visual al pasar el mouse (hover) ---
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(borde);   // cambia el color de fondo al dorado
                boton.setForeground(fondo);   // cambia el texto al color azul
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(fondo);   // vuelve al color original
                boton.setForeground(Color.WHITE);
            }
        });
        return boton;
    }

    // --- Método principal que inicia el programa ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Creo una instancia de la ventana y la muestro
            new ApexSC().setVisible(true);
        });
    }
}
