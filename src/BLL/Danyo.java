package BLL;

public class Danyo {
	private String zona;
	private String descripcion;
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Danyo(String zona, String descripcion) {
		super();
		this.zona = zona;
		this.descripcion = descripcion;
	}
	
}
