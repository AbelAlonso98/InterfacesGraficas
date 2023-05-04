package figurasJulio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Stage {

	private Figura figura;
	private boolean paused;
	private Thread t;
	private volatile boolean running;
	private JFrame frame;
	private double width;
	private double height;
	private double floorY;
	private BasicStroke pincel;
	private double scale;
	private Figura figura2 = new Figura(this, 300, 300, 100, 5, 17.313, 0, Color.GREEN);
	


	public synchronized void togglePause() {
		if (paused) {
			paused = false;
			notify();
		} else
			paused = true;
	}

	public boolean isPaused() {
		return paused;
	}

	public void start(JFrame frame) {
		this.frame = frame;
		width = 57.931;
		scale = frame.getWidth()/width;
		pincel = new BasicStroke(1.0f / (float) scale);
		height = frame.getHeight()/scale;
		floorY = height/2;
		double radius = 3.579/2;
		double x = width/2;
		double y = floorY - radius;
		figura = new Figura(this, x, y, radius, 5, 17.313, 0, Color.CYAN);
		(t = new Thread(this::loop)).start();
	}

	public void stop() {
		running = false;
		synchronized (this) {
			if (paused) {
				paused = false;
				notify();
			}
		}
		try {
			t.join();
		} catch (InterruptedException e) {
		}
	}

	public void loop() {
		long t0;
		long t1;
		long lapse;
		t0 = System.nanoTime();
		running = true;
		while (running) {
			synchronized (this) {
				if (paused) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
					t0 = System.nanoTime();
				}
			}
			t1 = System.nanoTime();
			lapse = t1 - t0;
			t0 = t1;
			next(lapse);
			render();
		}
	}

	private void next(long lapse) {
		figura.move(lapse);
	}

	private void render() {
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		figura2.paint(g);
		g.setStroke(pincel);
		g.scale(scale, scale);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		figura.paint(g);
		g.setColor(Color.ORANGE);
		g.drawLine(0, (int)floorY, (int)width, (int)floorY);
		g.dispose();
		bufferStrategy.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}


}