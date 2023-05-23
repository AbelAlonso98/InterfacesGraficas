package bouncingball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Stage implements MouseListener {
	private LinkedList<Ball> balls = new LinkedList<>();
	private LinkedList<Ball> deletedBalls = new LinkedList<>();
	private int numBalls;
	private boolean paused;
	private Thread t;
	private volatile boolean running;
	private JFrame frame;
	private long startTime;
	private static final long ONESECOND = 1000000000;

	public Stage(int numBalls) {
		this.numBalls = numBalls;
		startTime = System.nanoTime();
	}

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

	public void menu(JFrame frame) {
		this.frame = frame;

	}

	public void start(JFrame frame) {
		this.frame = frame;
		for (int i = 0; i < numBalls; i++)
			balls.add(Ball.random(this));
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
					if (running)
						t0 = System.nanoTime();
					else
						break;
				}
			}
			t1 = System.nanoTime();
			lapse = t1 - t0;
			t0 = t1;
			next(lapse);
			render();
		}
	}

	private synchronized void next(long lapse) {
		balls.forEach(b -> b.move(lapse));

		Iterator<Ball> it = deletedBalls.iterator();
		while (it.hasNext()) {
			Ball b = it.next();
			if (b.dissappear(lapse))
				it.remove();
		}
	}

	private void render() {
		BufferStrategy bufferStrategy = frame.getBufferStrategy();
		Graphics2D g = null;
		try {
			g = (Graphics2D) bufferStrategy.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			synchronized (this) {
				if (!balls.isEmpty() || !deletedBalls.isEmpty()) {
					for (Ball b : balls)
						b.paint(g);
					for (Ball b : deletedBalls)
						b.paint(g);
					g.setColor(Color.WHITE);
					g.setFont(new Font("SansSerif", Font.PLAIN, 25));
					g.drawString("Balls left: " + Integer.toString( balls.size()), 10, 40);
					String tiempo = Long.toString((System.nanoTime()-startTime)/ONESECOND)+" seconds";
					g.drawString(tiempo, 10, 60);

				} else {
					g.setColor(Color.WHITE);
					String string = "¡HAS GANADO!";
					Font stringFont = new Font("SansSerif", Font.PLAIN, 180);
					double altoFuente = stringFont.getStringBounds(string, g.getFontRenderContext()).getHeight();
					double anchoFuente = stringFont.getStringBounds(string, g.getFontRenderContext()).getWidth();
					g.setFont(stringFont);
					g.drawString(string, ((int)(frame.getWidth()-anchoFuente)/2), (int)(frame.getHeight()) / 2);

					string = "Presiona ESC para salir";
					stringFont = new Font("SansSerif", Font.PLAIN, 100);
					g.setFont(stringFont);
					anchoFuente = stringFont.getStringBounds(string, g.getFontRenderContext()).getWidth();
					altoFuente = stringFont.getStringBounds(string, g.getFontRenderContext()).getHeight();
					g.drawString(string, ((int)(frame.getWidth()-anchoFuente)/2), ((int)(frame.getHeight() / 2 + altoFuente)));
					
				}

			}
		} finally {
			if (g != null)
				g.dispose();
		}
		bufferStrategy.show();
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!balls.isEmpty()) {
			if (balls.getLast().in(e.getX(), e.getY())) {
				synchronized (this) {
					deletedBalls.add(balls.removeLast());
				}

			} else
				synchronized (this) {
					balls.add(Ball.random(this));
				}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
