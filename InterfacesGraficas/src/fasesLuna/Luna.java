package fasesLuna;

import java.awt.Color;


import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class Luna extends JLabel {

	private static final long serialVersionUID = 1L;

	private static final FaseLunar[] fases = { new FaseLunar("Luna Nueva"), new FaseLunar("Luna Creciente"),
			new FaseLunar("Cuarto Creciente"), new FaseLunar("Quinto Octante"), new FaseLunar("Luna Llena"),
			new FaseLunar("Tercer Octante"), new FaseLunar("Cuarto Menguante"), new FaseLunar("Luna Menguante") };

	private int fase;

	public Luna() {
		super(fases[0].getNombre(), fases[0].getImagen(), JLabel.CENTER);
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
	}

	public void siguiente() {
		if (++fase == fases.length)
			fase = 0;
		setText(fases[fase].getNombre());
		setIcon(fases[fase].getImagen());
	}
	
	public void anterior() {
		if (--fase < 0)
			fase = fases.length-1;
		setText(fases[fase].getNombre());
		setIcon(fases[fase].getImagen());
	}
}
