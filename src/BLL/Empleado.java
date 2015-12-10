package BLL;

public class Empleado {
	private String nombre;
	private String administrador;
	
	public Empleado(String nombre, String administrador) {
		super();
		this.nombre = nombre;
		this.administrador = administrador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAdministrador() {
		return administrador;
	}
	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}	
}
