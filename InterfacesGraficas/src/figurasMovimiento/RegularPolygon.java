package figurasMovimiento;

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
	double speed;
	double angularSpeed;
	double alpha;
	int radius;

	public RegularPolygon(Stage stage, double x, double y, int radius, int n, double speed, double angularSpeed,
			Color color) {
		this.stage = stage;
		this.x = x;
		this.y = y;
		this.color = color;
		this.speed = speed;
		this.angularSpeed = angularSpeed;
		this.radius = radius;
		alpha = 0;
		double beta = 2 * Math.PI / n;

		for (int i = 0; i < n; i++) {
			addPoint((int) (radius * Math.cos(alpha + i * beta)), (int) (radius * Math.sin(alpha + i * beta)));
		}

	}

	public void rotate(long lapse) {
		alpha += lapse * angularSpeed / ONESECOND;

	}

	public void move(long lapse) {
		x += (float) lapse * speed / ONESECOND;
		if (speed > 0) {
			double d = x + radius - stage.getWidth();
			if (d >= 0) {
				speed = -speed;
				x -= 2 * d;
				angularSpeed= -angularSpeed;
			}
		} else {
			if (x - radius < 0) {
				speed = -speed;
				x = radius;
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
