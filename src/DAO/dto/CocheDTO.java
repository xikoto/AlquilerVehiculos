package DAO.dto;

public class CocheDTO {
	private String matricula;
	private double kmsActuales;
	private int sucursal;
	private String categoria;
	
	public CocheDTO(String matricula, double kmsActuales, int sucursal, String nombre) {
		super();
		this.matricula = matricula;
		this.kmsActuales = kmsActuales;
		this.sucursal = sucursal;
		this.categoria = nombre;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double getKmsActuales() {
		return kmsActuales;
	}

	public void setKmsActuales(double kmsActuales) {
		this.kmsActuales = kmsActuales;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String nombre) {
		this.categoria = nombre;
	}
	
	
}
