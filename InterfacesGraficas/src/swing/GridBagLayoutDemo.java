package swing;

import java.awt.*;
import javax.swing.*;


public class GridBagLayoutDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	GridBagLayoutDemo(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(100, 90));
		p.setBackground(Color.BLACK);
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(p, constraints);
		p = new JPanel();
		p.setPreferredSize(new Dimension(100, 60));
		p.setBackground(Color.BLUE);
		constraints.gridx = 0;
		constraints.gridy = 1;
		add(p, constraints);
		p = new JPanel();
		p.setPreferredSize(new Dimension(300, 60));
		p.setBackground(Color.CYAN);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		add(p, constraints);
		p = new JPanel();
		p.setPreferredSize(new Dimension(100, 60));
		p.setBackground(Color.GREEN);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		add(p, constraints);
		p = new JPanel();
		p.setPreferredSize(new Dimension(90, 50));
		p.setBackground(Color.MAGENTA);
		constraints.gridx = 2;
		constraints.gridy = 1;
		add(p, constraints);
		p = new JPanel();
		p.setPreferredSize(new Dimension(50, 50));
		p.setBackground(Color.ORANGE);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		add(p, constraints);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new GridBagLayoutDemo(args).setVisible(true));
	}
}
