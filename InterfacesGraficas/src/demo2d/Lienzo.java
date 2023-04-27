package demo2d;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Lienzo extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image fondo;

	public Lienzo() throws IOException {
		super(new BorderLayout());
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
