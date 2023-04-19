package swing;

import java.awt.*;
import javax.swing.*;


public class GridLayoutDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	GridLayoutDemo(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(2, 0));
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(50, 70));
		p.setBackground(Color.BLACK);
		
		add(p);
		p = new JPanel();
		p.setPreferredSize(new Dimension(70, 50));
		p.setBackground(Color.BLUE);
		add(p);
		p = new JPanel();
		p.setPreferredSize(new Dimension(90, 110));
		p.setBackground(Color.CYAN);
		add(p);
		p = new JPanel();
		p.setPreferredSize(new Dimension(110, 90));
		p.setBackground(Color.GREEN);
		add(p);
		p = new JPanel();
		p.setPreferredSize(new Dimension(123, 76));
		p.setBackground(Color.MAGENTA);
		add(p);
		p = new JPanel();
		p.setPreferredSize(new Dimension(76, 123));
		p.setBackground(Color.ORANGE);
		add(p);
		p = new JPanel();
		p.setPreferredSize(new Dimension(76, 123));
		p.setBackground(Color.RED);
		add(p);
		

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new GridLayoutDemo(args).setVisible(true));
	}
}
