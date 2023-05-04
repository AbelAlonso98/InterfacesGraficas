package passiveRendering;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements KeyListener, WindowListener  {
	
	private static final long serialVersionUID = 1L;
	private Stage stage;

	Main(String[] args) {
    	super("MyApp");
    	addKeyListener(this);
    	addWindowListener(this);
    	setIgnoreRepaint(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }
	
	private void start() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice [] devices = ge.getScreenDevices();
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
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P:
			stage.togglePause();
			break;
		case KeyEvent.VK_ESCAPE:
			windowClosing(null);
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
		boolean paused = stage.isPaused();
		if(!paused)
			stage.togglePause();
		if(JOptionPane.showConfirmDialog(
				this,
				"¿Cerrar la aplicacion?",
				"Bouncing Ball",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			stage.stop();
			dispose();
			System.exit(0);
		}
		else if(!paused)
			stage.togglePause();
		
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
	}
	

}