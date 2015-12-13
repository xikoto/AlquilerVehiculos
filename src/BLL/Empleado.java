package BLL;

public class Empleado {
	private String dni;
	private String nombre;
	private boolean administrador;
	
	public Empleado(String nombre, boolean administrador) {
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
	public boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}	
}
