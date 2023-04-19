package fasesLuna;

import javax.swing.ImageIcon;

public class FaseLunar {

	private String nombre;
	private ImageIcon imagen;
	
	public FaseLunar(String nombre) {
		this.nombre = nombre;
		imagen = new ImageIcon(getClass().getResource(String.format("/fasesLuna/%s.png", nombre)));

	}

	public String getNombre() {
		return nombre;
	}

	public ImageIcon getImagen() {
		return imagen;
	}
	
	
}
