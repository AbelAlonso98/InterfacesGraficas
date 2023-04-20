package demo2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	Main() throws IOException {
		super("Demo 2D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setBackground(Color.CYAN);
		add(new Lienzo(), BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				new Main().setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
