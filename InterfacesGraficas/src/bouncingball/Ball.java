package bouncingball;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball {

	private static final double ONESECOND = 1000000000d;
	private float timeToOpaque = (float) (3 * ONESECOND);
	private float timeToDisappear = (float) (2 * ONESECOND);
	private float big;

	Stage stage;

	Color color;
	double x;
	double y;
	int radius;
	int diameter;
	double vx;
	double vy;
	float alpha;

	public Ball(Stage stage, double x, double y, int radius, double speed, double direction, Color color) {
		this.stage = stage;
		this.x = x - radius;
		this.y = y - radius;
		this.radius = radius;
		diameter = 2 * radius;
		vx = speed * Math.cos(direction);
		vy = speed * Math.sin(direction);
		this.color = color;
		big = diameter;
	}

	public void move(long lapse) {
		if (alpha < 1) {
			alpha += lapse / timeToOpaque;
			if (alpha > 1)
				alpha = 1;
		}
		x += (float) lapse * vx / ONESECOND;
		if (vx > 0) {
			double d = x + diameter - stage.getWidth();
			if (d >= 0) {
				vx = -vx;
				x -= 2 * d;
			}
		} else {
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
		} else {
			if (y < 0) {
				vy = -vy;
				y = -y;
			}
		}

	}

	public boolean dissappear(long lapse) {
		// radius
		float n = lapse*big/timeToDisappear;
		x -= n;
		y -= n;
		diameter += 2*n;
		// alpha
		alpha -= lapse / timeToDisappear;

		return alpha <= 0;
	}

	public void paint(Graphics2D g) {

		Composite ant = null; 
		if (alpha < 1) {
			ant = g.getComposite();
			AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(alcom);
		}
		g.setColor(color);
		g.fillOval((int) x, (int) y, diameter, diameter);
		if (alpha < 1) {
			g.setComposite(ant);
		}
	}

	private static Random r = new Random();

	static Ball random(Stage stage) {
		int min = Math.max(stage.getWidth(), stage.getHeight());
		int radius = nextInt((int) (min * 0.05f), min / 8);
		int speed = nextInt(50, 250);
		int dir = nextInt(20, 70) + (90 * r.nextInt(4));
		Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		int rx = stage.getWidth() / 2;
		int ry = stage.getHeight() / 2;
		return new Ball(stage, rx, ry, radius, speed, dir, color);
	}

	private static int nextInt(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}

	public boolean in(double x, double y) {
		return Math.sqrt(Math.pow(x - (this.x + radius), 2) + Math.pow(y - (this.y + radius), 2)) <= radius;
	}

}
