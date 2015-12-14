package DAO.dto;

public class EmpleadoDTO {
	private String dni;
	private String nombre;
	private boolean administrador;
	private String sucursal;
	
	public EmpleadoDTO(String dni, String nombre, boolean administrador, String sucursal) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.administrador = administrador;
		this.sucursal = sucursal;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
}
