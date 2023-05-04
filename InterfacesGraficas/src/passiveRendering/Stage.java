package passiveRendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Stage{

	private Ball ball;
	private boolean paused;
	private Thread t;
	private volatile boolean running;
	private JFrame frame; 
	
	
	public synchronized void togglePause() {
		if (paused) {
			paused = false;
			notify();
		}
		else
			paused = true;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void start(JFrame frame) {
		this.frame = frame;
		ball = new Ball(this, frame.getWidth() / 2d, frame.getHeight() / 2d, 50, 100d, 45d, Color.MAGENTA);
		(t = new Thread(this::loop)).start();
	}

	
	public void stop() {
		running = false;
		synchronized (this) {
			if(paused) {
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
			synchronized(this) {
				if (paused) {
					try {
						wait();
						if (!running)
							break;
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
		ball.move(lapse);
	}
	
	private void render() {
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g);
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
