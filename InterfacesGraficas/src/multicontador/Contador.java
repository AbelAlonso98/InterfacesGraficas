package multicontador;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contador extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Contador global;
	private Contador[] parciales;
	private JTextField texto;
	private Color color;

	public Contador(Contador global, Color color) {
		this.global = global;
		this.color = color;
		inicializar();
	}

	public Contador(Contador[] parciales, Color color) {
		this.parciales = parciales;
		this.color = color;
		inicializar();
	}

	private void inicializar() {
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(crearBoton("RESET", Recursos.resetIcon, color), constraints);
		texto = new JTextField("0", 5);
		texto.setHorizontalAlignment(JTextField.CENTER);
		texto.setEditable(false);
		texto.setFont(Recursos.font);
		texto.setBackground(Color.BLACK);
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(texto, constraints);
		if (global != null) {
			constraints.gridx = 0;
			constraints.gridy = 3;
			add(crearBoton("+", Recursos.addIcon, color), constraints);
		}
	}

	private JButton crearBoton(String actionCommand, ImageIcon icono, Color color) {
		JButton boton = new JButton(icono);
		boton.setActionCommand(actionCommand);
		boton.addActionListener(this);
		boton.setBackground(color);
		boton.setHideActionText(false);
		return boton;
	}

	public void inc() {
		int n = Integer.parseInt(texto.getText()) + 1;
		texto.setText(String.valueOf(n));
	}

	public void reset() {
		texto.setText("0");
	}

	public void dec(int n) {
		int nuevo = Integer.parseInt(texto.getText()) - n;
		texto.setText(String.valueOf(nuevo));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("+")) {
			inc();
			global.inc();
		} else {
			if (global == null) {
				reset();
				for (int i = 0; i < parciales.length; i++)
					parciales[i].reset();
			} else {
				int n = Integer.parseInt(texto.getText());
				reset();
				global.dec(n);
			}

		}
	}

}
