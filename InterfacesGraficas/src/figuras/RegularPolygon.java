package figuras;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class RegularPolygon extends Polygon {
	private static final long serialVersionUID = 1L;

	private static final double ONESECOND = 1000000000d;
	
	Stage stage;
	Color color;
	double x;
	double y;
	double angularSpeed;
	double alpha;

	public RegularPolygon(Stage stage, double x, double y, int radius, int n, double angularSpeed, Color color) {
		this.stage = stage;
		this.x = x;
		this.y = y;
		this.color = color;
		this.angularSpeed = angularSpeed;
		alpha = 0;
		double beta = 2 * Math.PI / n;

		for (int i = 0; i < n; i++) {
			addPoint((int) (radius * Math.cos(alpha + i * beta)), (int) (radius * Math.sin(alpha + i * beta)));
		}

	}
	
	public void rotate(long lapse) {
		alpha += lapse * angularSpeed / ONESECOND;
	}

	private static Random r = new Random();

	static RegularPolygon random(Stage stage) {
		int min = Math.max(stage.getWidth(), stage.getHeight());
		int radius = r.nextInt((int) (min * 0.05f), min / 8);
		int lados = r.nextInt(3, 20);
		Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		int rx = r.nextInt(radius, stage.getWidth() - radius);
		int ry = r.nextInt(radius, stage.getHeight() - radius);
		double angularSpeed = r.nextDouble(Math.PI / 10, Math.PI / 8);
		return new RegularPolygon(stage, rx, ry, radius, lados, angularSpeed, color);
	}

	public void paint(Graphics2D g) {
		AffineTransform af = g.getTransform();
		g.rotate(alpha, x, y);
		g.translate(x, y);
		g.setColor(color);
		g.draw(this);
		g.setTransform(af);



	}

}
