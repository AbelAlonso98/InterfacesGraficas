package demoJList;


public class PaisDemo {

	private String nombre;
	private String capital;
	private String continente;
	
	public PaisDemo(String nombre, String capital, String continente) {
		this.nombre = nombre;
		this.capital = capital;
		this.continente = continente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCapital() {
		return capital;
	}
	
	public String getContinente() {
		return continente;
	}
}
