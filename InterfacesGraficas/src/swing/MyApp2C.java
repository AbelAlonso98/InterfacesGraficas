package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MyApp2C extends JFrame {
	private static final long serialVersionUID = 1L;

	MyApp2C(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.add(new JButton("Pulsa aqui"), BorderLayout.NORTH);
		contentPane.add(new JTextField(), BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new MyApp2C(args).setVisible(true));
	}
}
