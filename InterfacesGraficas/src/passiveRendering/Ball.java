package passiveRendering;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {

	private static final double ONESECOND = 1000000000d;
	
	Stage stage;
	
	Color color;
	double x;
	double y;
	int diameter;
	double vx;
	double vy;
	
	
	public Ball(Stage stage, double x, double y, int radius, double speed, double direction, Color color) {
		this.stage = stage;
		this.x = x - radius;
		this.y = y - radius;
		diameter = 2 * radius;
		vx = speed * Math.cos(direction);
		vy = speed * Math.sin(direction);
		this.color = color;
	}
	
	public void move(long lapse) {
		x += (float) lapse * vx / ONESECOND;
		if (vx > 0) {
			double d = x + diameter - stage.getWidth();
			if (d >= 0) {
				vx = -vx;
				x -= 2 * d;
			}
		}
		else {
			if (x < 0) {
				vx = -vx;
				x = -x;
			}
		}
		y += (float) lapse * vy / ONESECOND;
		if (vy > 0) {
			double d = y + diameter - stage.getHeight();
			if (d >= 0) {
				vy = -vy;
				y -= 2 * d;
			}
		}
		else {
			if (y < 0) {
				vy = -vy;
				y = -y;
			}
		}
	}
	
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diameter, diameter);
	}
	
}
