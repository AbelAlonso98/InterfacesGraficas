package figurasJulio;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements KeyListener, WindowListener {

	private static final long serialVersionUID = 1L;
	private Stage stage = new Stage();

	Main(String[] args) {
		super("MyApp");
		addKeyListener(this);
		addWindowListener(this);
		setIgnoreRepaint(true);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void start() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = ge.getScreenDevices();
		GraphicsDevice device = devices.length == 2 ? devices[1] : ge.getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		createBufferStrategy(2);
		stage.start(this);
	}



	public static void main(final String[] args) throws IOException {
		SwingUtilities.invokeLater(() -> new Main(args).start());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P:
			stage.togglePause();
			break;
		case KeyEvent.VK_ESCAPE:
			dispose();
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {


	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}