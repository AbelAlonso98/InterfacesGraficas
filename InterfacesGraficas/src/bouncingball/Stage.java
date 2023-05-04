package bouncingball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class Stage {

	private ArrayList<Ball> balls = new ArrayList<>();
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

	public void start(JFrame frame, int numeroBolas) {
		this.frame = frame;
		int radio;
		double direccion = 0;

		Color colorRandom;
		for (int i = 0; i < numeroBolas; i++) {
			radio = frame.getWidth() < frame.getHeight() ? frame.getWidth() * generarAleatorio(5, 25) / 100
					: frame.getHeight() * generarAleatorio(5, 25) / 100;
			direccion = generarAleatorio(20, 70) + 90 * generarAleatorio(0, 3);
			colorRandom = new Color(generarAleatorio(0, 255),
					generarAleatorio(0, 255),
					generarAleatorio(0, 255));
			balls.add(new Ball(this, frame.getWidth() / 2d, frame.getHeight() / 2d, radio, generarAleatorio(50, 250) * 1d,
					direccion, colorRandom));

		}
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
		balls.forEach(b -> b.move(lapse));
	}

	private void render() {
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		balls.forEach(b -> b.paint(g));
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

	private int generarAleatorio(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

}