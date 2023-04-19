package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


//No deberiamos hacerlo asi

public class MyApp3D extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyApp3D() {
		super("MyApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Añado lo que quiera
		Container contentPanel = getContentPane();
//    	contentPanel.setPreferredSize(new Dimension(500, 300));
		contentPanel.add(new JButton("Pulsa aqui"), BorderLayout.NORTH);
		contentPanel.add(new JTextField(), BorderLayout.CENTER);

		// Llamo a pack y lo hago visible
		pack();
		setLocationRelativeTo(null); // Hace que la ventana salga en el centro de la pantalla
	}

	public static void main(final String[] args) {
		new MyApp3D().setVisible(true);
	}
}
