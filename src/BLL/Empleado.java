package BLL;

public class Empleado {
	private String dni;
	private String nombre;
	private boolean administrador;
	
	public Empleado(String dni,String nombre, boolean administrador) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.administrador = administrador;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
