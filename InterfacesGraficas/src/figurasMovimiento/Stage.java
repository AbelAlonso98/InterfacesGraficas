package figurasMovimiento;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Stage {

	private ArrayList<RegularPolygon> figuras = new ArrayList<>();
	private boolean paused;
	private Thread t;
	private volatile boolean running;
	private JFrame frame;

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
		figuras.add(new RegularPolygon(this, getWidth()/2d, getHeight()/2d, 250, 8, 550, Math.PI/2, Color.MAGENTA));
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
		figuras.forEach(p -> p.rotate(lapse));
		figuras.forEach(p -> p.move(lapse));
	}

	private void render() {
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		figuras.forEach(f -> f.paint(g));
		g.dispose();
		bufferStrategy.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}


}