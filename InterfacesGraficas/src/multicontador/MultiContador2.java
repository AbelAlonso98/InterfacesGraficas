package multicontador;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MultiContador2 extends JFrame {

	private static final long serialVersionUID = 1L;

	MultiContador2(String[] args) {
		super("MultiContador2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		Contador[] parciales = new Contador[3];
		Contador global = new Contador(parciales, Color.RED);
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				constraints.gridx = 0;
				constraints.gridy = 1;
				contentPane.add(parciales[i] = new Contador(global, Color.GREEN), constraints);
			} else if (i == 1) {
				constraints.gridx = 1;
				constraints.gridy = 1;
				contentPane.add(parciales[i] = new Contador(global, Color.YELLOW), constraints);
			} else {
				constraints.gridx = 2;
				constraints.gridy = 1;
				contentPane.add(parciales[i] = new Contador(global, Color.BLUE), constraints);
			}

		}
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(global, constraints);
		pack();
		setLocationRelativeTo(null);

	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new MultiContador2(args).setVisible(true));
	}
}
