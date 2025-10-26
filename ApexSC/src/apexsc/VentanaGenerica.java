package apexsc;

import javax.swing.*;
import java.awt.*;

// Esta clase representa una ventana genérica que puedo reutilizar
// para mostrar secciones simples del sistema que todavía no tienen
// una interfaz específica desarrollada.
public class VentanaGenerica extends JFrame {

    // --- Constructor ---
    // Recibe el título de la ventana como parámetro para poder personalizarlo.
    public VentanaGenerica(String tituloVentana) {
        // Configuro las propiedades básicas de la ventana
        setTitle(tituloVentana);
        setSize(400, 300);
        setLocationRelativeTo(null); // centro la ventana en la pantalla
        setResizable(false);         // impido que se cambie el tamaño

        // Creo un JLabel que muestra el título centrado en la ventana
        JLabel titulo = new JLabel(tituloVentana, JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18)); // defino la fuente y el tamaño
        titulo.setForeground(new Color(0, 51, 102));         // color azul oscuro para el texto

        // Agrego el título al contenido de la ventana
        add(titulo);

        // Hago visible la ventana
        setVisible(true);
    }
}
