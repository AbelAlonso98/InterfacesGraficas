package tarea1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Contador extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Contador global;
	private Contador[] parciales;
	private JTextField texto;

	public Contador(Contador global) {
		this.global = global;
		inicializar();
	}
	
	public Contador(Contador[] parciales) {
		this.parciales = parciales;
		inicializar();
	}

	private void inicializar() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(10, 10, 10, 10),
				BorderFactory.createLineBorder(Color.BLACK)));
		add(crearBoton("", Recursos.resetIcon));
		texto = new JTextField("0", 5);
		texto.setHorizontalAlignment(JTextField.CENTER);
		texto.setEditable(false);
		texto.setFont(Recursos.font);
		add(texto);
		if(global !=null)
				add(crearBoton("", Recursos.addIcon));
	}

	private JButton crearBoton(String actionCommand, ImageIcon icono) {
		JButton boton = new JButton(actionCommand, icono);
		boton.setActionCommand(actionCommand);
		boton.addActionListener(this);
		return boton;
	}
	
	public void inc() {
		int n = Integer.parseInt(texto.getText())+1;
		texto.setText(String.valueOf(n));
	}

	public void reset() {
		texto.setText("0");
	}
	
	public void dec(int n) {
		int nuevo = Integer.parseInt(texto.getText())-n;
		texto.setText(String.valueOf(nuevo));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("+")) {
			inc();
			global.inc();
		}
		else {
			if(global==null) {
				reset();
				for(int i = 0; i < parciales.length; i++)
					parciales[i].reset();
			}
			else {
				int n = Integer.parseInt(texto.getText());
				reset();
				global.dec(n);
			}
				
		}
	}
}
