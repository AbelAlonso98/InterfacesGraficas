package figurasMovimientoJulio;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

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
	double radius;
	double angularSpeed;
	double alpha;
	double dir = 1;
	int n;

	   public Figura(Stage stage, double x, double y, double radius, int n, double speed, double dir, Color color) {
		this.stage = stage;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.diameter = 2 * radius;
		this.n = n;
		// calcular velocidades
		vx = speed * Math.cos(dir);
		vy = speed * Math.sin(dir);
		angularSpeed = speed / radius;

		this.color = color;

		crearFigura();

	}

	public void move(long lapse) {
		alpha += angularSpeed * lapse / ONESECOND * dir;
		x += (float) lapse * vx / ONESECOND;
		if (vx > 0) {
			double d = x + radius - stage.getWidth();
			if (d >= 0) {
				vx = -vx;
				dir = -dir;
				x -= 2 * d;
			}
		} else {
			if (x < radius) {
				vx = -vx;
				dir = -dir;
				x += 2 * (radius - x);
			}
		}
		y += (float) lapse * vy / ONESECOND;
		if (vy > 0) {
			double d = y + radius - stage.getHeight();
			if (d >= 0) {
				vy = -vy;
				y -= 2 * d;
			}
		} else {
			if (y < radius) {
				vy = -vy;
				y += 2 * (radius - y);
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

	public void crearFigura() {

		double beta = 2 * Math.PI / n;
		moveTo(radius * Math.cos(alpha), radius * Math.sin(alpha));
		for (int i = 0; i <= n; i++) {
			lineTo(radius * Math.cos(alpha + i * beta), radius * Math.sin(alpha + i * beta));
		}
		Ellipse2D.Double a = new Ellipse2D.Double(-radius, -radius, 2 * radius, 2 * radius);
		this.append(a, false);

	}

	public void incLados() {

		if (n < 20) {
			n++;
			reset();
			crearFigura();
		}
	}
	
	public void decLados() {

		if (n >3) {
			n--;
			reset();
			crearFigura();
		}
	}

}
