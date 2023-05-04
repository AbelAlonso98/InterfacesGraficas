package figurasJulio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.util.Random;

public class Figura extends Path2D.Double {
	private static final long serialVersionUID = 1L;

	private static final double ONESECOND = 1000000000d;

	Stage stage;
	Color color;
	double x;
	double y;
	double vx;
	double vy;
	double diameter;
	double angularSpeed;
	double alpha;

	public Figura(Stage stage, double x, double y, double radius, int n, double speed, double dir, Color color) {
		this.stage = stage;
		this.x = x;
		this.y = y;
		this.diameter = 2*radius;
		vx = speed * Math.cos(dir);
		vy = speed * Math.sin(dir);
		this.color = color;

		alpha = 0;
		double beta = 2 * Math.PI / n;
		Polygon p = new Polygon();

		for (int i = 0; i < n; i++) {
			p.addPoint((int) (radius * Math.cos(alpha + i * beta)), (int) (radius * Math.sin(alpha + i * beta)));
		}
		this.append(p, false);
		Arc2D.Double a = new Arc2D.Double(-radius, -radius, 2 * radius, 2 * radius, 0, 360, Arc2D.Double.CHORD);
		this.append(a, false);
	}

	public void rotate(long lapse) {
		alpha += lapse * angularSpeed / ONESECOND;
	}

//	public void move(long lapse) {
//		x += (float) lapse * vx / ONESECOND;
//		if (vx > 0) {
//			double d = x + diameter - stage.getWidth();
//			if (d >= 0) {
//				vx = -vx;
//				x -= 2 * d;
//			}
//		} else {
//			if (x < 0) {
//				vx = -vx;
//				x = diameter/2;
//			}
//		}
//		y += (float) lapse * vy / ONESECOND;
//		if (vy > 0) {
//			double d = y + diameter - stage.getHeight();
//			if (d >= 0) {
//				vy = -vy;
//				y -= 2 * d;
//			}
//		} else {
//			if (y < 0) {
//				vy = -vy;
//				y = -y;
//			}
//		}
//	}
	
	public void move(long lapse) {
		x += (float) lapse * vx / ONESECOND;
		if (vx > 0) {
			double d = x + diameter/2 - stage.getWidth();
			if (d >= 0) {
				vx = -vx;
				x -= 2 * d;
				angularSpeed= -angularSpeed;
			}
		} else {
			if (x - diameter/2 < 0) {
				vx = -vx;
				x = diameter/2;
				angularSpeed= -angularSpeed;

			}
		}
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
