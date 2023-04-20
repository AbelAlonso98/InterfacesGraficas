package multicontador;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MultiContador2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Color[] colores = {Color.GREEN, Color.YELLOW, Color.BLUE};

	MultiContador2(String[] args) {
		super("MultiContador2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Contador[] parciales = new Contador[3];
		Contador global = new Contador(parciales, Color.RED);
		for (int i = 0; i < 3; i++) {
			constraints.weightx = 1.0;
			Contador c = new Contador(global, colores[i]);
		    c.setMinimumSize(new Dimension(100, c.getMinimumSize().height)); // Agregar esta línea
		    parciales[i] = c;
			if (i == 0) {
				constraints.weightx = 1.0;
				constraints.gridx = 0;
				constraints.gridy = 1;
				contentPane.add(parciales[i] = c, constraints);
			} else if (i == 1) {
				constraints.gridx = 1;
				constraints.gridy = 1;
				contentPane.add(parciales[i] = c, constraints);
			} else {
				constraints.gridx = 2;
				constraints.gridy = 1;
				contentPane.add(parciales[i] = c, constraints);

			}

		}
		Arrays.stream(parciales).forEach(c -> c.setMinimumSize(new Dimension(0, c.getPreferredSize().height)));

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;

	
		contentPane.add(global, constraints);


		pack();
		setLocationRelativeTo(null);

	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new MultiContador2(args).setVisible(true));
	}
}
