package demo2d;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lienzo extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image fondo;

	public Lienzo() throws IOException {
		super(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		setPreferredSize(new Dimension(700, 700));
		setBackground(new Color(255, 255, 224));
		JButton button = new JButton("PULSA AQUI");
		add(button, constraints);
		fondo = ImageIO.read(getClass().getResource("/89045.jpg"));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, null);

		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}

	
}
