package swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class MyApp extends JFrame{

	private static final long serialVersionUID = 1L;

	MyApp(String[] args) {
		super("MyApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPanel = getContentPane();
//    	contentPanel.setPreferredSize(new Dimension(500, 300));
		JButton button = new JButton("Pulsa aqui");
		JLabel texto = new JLabel("Hola Mundo");
		button.addActionListener(e -> texto.setText(texto.getText().isEmpty() ? "Hola Mundo!" : ""));
		
		contentPanel.add(button, BorderLayout.NORTH);
		contentPanel.add(texto, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);

	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new MyApp(args).setVisible(true));
	}


}
