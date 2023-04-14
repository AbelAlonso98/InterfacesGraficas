package tarea1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class MultiContador extends JFrame {

	private static final long serialVersionUID = 1L;
	Dimension dimensionTexto = new Dimension(115, 20);
	Dimension dimensionFilas = new Dimension(250, 40);
	Border borde = BorderFactory.createLineBorder(Color.black, 1);
	FlowLayout layoutCentrado = new FlowLayout(FlowLayout.LEFT);

	MultiContador(String[] args) {
		super("Multi-Contador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPanel = getContentPane();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(270, 235));

		// Primera fila
		JTextField l1 = new JTextField("0");
		l1.setPreferredSize(dimensionTexto);
		JButton r1 = new JButton("RESET");
		JButton s1 = new JButton("+");
		JPanel fila1 = new JPanel();
		fila1.setBorder(borde);
		fila1.setPreferredSize(dimensionFilas);
		fila1.setLayout(layoutCentrado);
		fila1.add(r1);
		fila1.add(l1);
		fila1.add(s1);

		// Segunda fila
		JTextField l2 = new JTextField("0");
		l2.setPreferredSize(dimensionTexto);
		JButton r2 = new JButton("RESET");
		JButton s2 = new JButton("+");
		JPanel fila2 = new JPanel();
		fila2.setBorder(borde);
		fila2.setPreferredSize(dimensionFilas);
		fila2.setLayout(layoutCentrado);
		fila2.add(r2);
		fila2.add(l2);
		fila2.add(s2);

		// Tercera fila
		JTextField l3 = new JTextField("0");
		l3.setPreferredSize(dimensionTexto);
		JButton r3 = new JButton("RESET");
		JButton s3 = new JButton("+");
		JPanel fila3 = new JPanel();
		fila3.setBorder(borde);
		fila3.setPreferredSize(dimensionFilas);
		fila3.setLayout(layoutCentrado);
		fila3.add(r3);
		fila3.add(l3);
		fila3.add(s3);

		// Cuarta fila
		JTextField l4 = new JTextField("0");
		l4.setPreferredSize(dimensionTexto);
		JButton r4 = new JButton("RESET");
		JPanel fila4 = new JPanel();
		fila4.setBorder(borde);
		fila4.setPreferredSize(dimensionFilas);
		fila4.setLayout(layoutCentrado);
		fila4.add(r4);
		fila4.add(l4);

		// Creo todos los Listeners
		ActionListener reset1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l1.setText("0");
			}
		};
		ActionListener increase1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l1.setText(Integer.toString(Integer.parseInt(l1.getText()) + 1));
				l4.setText(Integer.toString(Integer.parseInt(l1.getText()) + Integer.parseInt(l2.getText())
						+ Integer.parseInt(l3.getText())));
			}
		};
		ActionListener reset2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l2.setText("0");
				l4.setText(Integer.toString(Integer.parseInt(l1.getText()) + Integer.parseInt(l2.getText())
						+ Integer.parseInt(l3.getText())));
			}
		};
		ActionListener increase2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l2.setText(Integer.toString(Integer.parseInt(l2.getText()) + 1));
				l4.setText(Integer.toString(Integer.parseInt(l1.getText()) + Integer.parseInt(l2.getText())
						+ Integer.parseInt(l3.getText())));
			}
		};
		ActionListener reset3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l3.setText("0");
				l4.setText(Integer.toString(Integer.parseInt(l1.getText()) + Integer.parseInt(l2.getText())
						+ Integer.parseInt(l3.getText())));
			}
		};
		ActionListener increase3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l3.setText(Integer.toString(Integer.parseInt(l3.getText()) + 1));
				l4.setText(Integer.toString(Integer.parseInt(l1.getText()) + Integer.parseInt(l2.getText())
						+ Integer.parseInt(l3.getText())));
			}
		};

		// Asigno todos los listener
		r1.addActionListener(reset1);
		s1.addActionListener(increase1);
		r2.addActionListener(reset2);
		s2.addActionListener(increase2);
		r3.addActionListener(reset3);
		s3.addActionListener(increase3);
		r4.addActionListener(e -> l4.setText("0"));
		r4.addActionListener(reset1);
		r4.addActionListener(reset2);
		r4.addActionListener(reset3);

		// Creo un panel envoltorio para juntar las 4 filas y que no se muevan al redimensionar la ventana
		JPanel envoltorio = new JPanel();
		envoltorio.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		envoltorio.add(fila1);
		envoltorio.add(fila2);
		envoltorio.add(fila3);
		envoltorio.add(fila4);

		contentPanel.add(envoltorio, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);

	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new MultiContador(args).setVisible(true));
	}
}
