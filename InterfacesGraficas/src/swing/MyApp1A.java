package swing;

import java.awt.BorderLayout;
import java.awt.Container;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MyApp1A implements Runnable{
    public void run() {
    	// Creo el objeto JFrame
    	JFrame frame = new JFrame("MyApp");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Añado lo que quiera
    	Container contentPanel = frame.getContentPane();
//    	contentPanel.setPreferredSize(new Dimension(500, 300));
    	contentPanel.add(new JButton("Pulsa aqui"), BorderLayout.NORTH);
    	contentPanel.add(new JTextField(), BorderLayout.CENTER);
    	
    	// Llamo a pack y lo hago visible
    	frame.pack();
    	frame.setLocationRelativeTo(null);		// Hace que la ventana salga en el centro de la pantalla
    	frame.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MyApp1A());
    }
}