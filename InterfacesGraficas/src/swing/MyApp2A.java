package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MyApp2A {
	
	JFrame frame;
	
	MyApp2A(String[] args) {
		// Creo el objeto JFrame
    	frame = new JFrame("MyApp");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Añado lo que quiera
    	Container contentPanel = frame.getContentPane();
//    	contentPanel.setPreferredSize(new Dimension(500, 300));
    	contentPanel.add(new JButton("Pulsa aqui"), BorderLayout.NORTH);
    	contentPanel.add(new JTextField(), BorderLayout.CENTER);
    	
    	// Llamo a pack y lo hago visible
    	frame.pack();
    	frame.setLocationRelativeTo(null);		// Hace que la ventana salga en el centro de la pantalla
    	
    }

    public void show() {
    	frame.setVisible(true);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyApp2A(args).show();
            }
        });
    }
}
