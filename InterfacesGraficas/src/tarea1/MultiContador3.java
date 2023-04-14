package tarea1;

import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MultiContador3 extends JFrame {

	private static final long serialVersionUID = 1L;

	MultiContador3(String[] args) {
		super("MultiContador2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		Contador[] parciales = new Contador[3];
		Contador global = new Contador(parciales);
		for (int i = 0; i < 3; i++)
			contentPane.add(parciales[i] = new Contador(global));
		contentPane.add(global);
		pack();
		setLocationRelativeTo(null);

	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new MultiContador3(args).setVisible(true));
	}
}
