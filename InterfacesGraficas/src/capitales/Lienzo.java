package capitales;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JScrollPane;

public class Lienzo extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private Image  fondo;

	public Lienzo() throws IOException {
		super();
		  fondo = ImageIO.read(getClass().getResource("/capitales/img/europa.png"));
	}
	
	public Lienzo(Component view) throws IOException{
		super(view);
		  fondo = ImageIO.read(getClass().getResource("/capitales/img/europa.png"));
		  
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		double sx = (double) getWidth() / (double) fondo.getWidth(this);
		double sy = (double) getHeight() / (double) fondo.getHeight(this);
		AffineTransform t = g2d.getTransform();
		
		g2d.scale(sx, sy);
		g2d.drawImage(fondo, 0, 0, this);
		g2d.setTransform(t);
	}
	

	public void setFondo(String continente) throws IOException {
		fondo = ImageIO.read(getClass().getResource("/capitales/img/" + continente.toLowerCase() + ".png"));
	}
	
}
