package fasesLuna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	private Luna luna = new Luna();

	Main(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.BLACK);
		contentPane.add(luna, BorderLayout.CENTER);
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
		JButton boton1 = new JButton(new ImageIcon(getClass().getResource("/fasesLuna/anterior.png")));
		boton1.addActionListener(e -> luna.anterior());
		panel.add(boton1);
		
		JButton boton2 = new JButton(new ImageIcon(getClass().getResource("/fasesLuna/siguiente.png")));
		boton2.addActionListener(e -> luna.siguiente());
		panel.add(boton2);
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null); 

	}



	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new Main(args).setVisible(true));
	}
}
