package capitales;


public class Pais {
	private String nombre;
	private String capital;
	private String codigo;

	public Pais(String nombre, String capital, String codigo) {
		this.nombre = nombre;
		this.capital = capital;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCapital() {
		return capital;
	}

	public String getCodigo() {
		return codigo;
	}

}
