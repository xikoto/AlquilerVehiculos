package DAO.dto;


import java.time.LocalDateTime;

public class EntregaDTO {

	private int id;
	private LocalDateTime fecha;
	private String tipoSeguro;
	private double kms;
	private double combustible;
	private String cocheAsignado;
	private String empleadoRealiza;
	
	public EntregaDTO(int id, LocalDateTime fecha, String tipoSeguro, double kms, double combustible, String coche,
			String empleado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.cocheAsignado = coche;
		this.empleadoRealiza = empleado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public double getKms() {
		return kms;
	}

	public void setKms(double kms) {
		this.kms = kms;
	}

	public double getCombustible() {
		return combustible;
	}

	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}

	public String getCoche() {
		return cocheAsignado;
	}

	public void setCoche(String coche) {
		this.cocheAsignado = coche;
	}

	public String getEmpleado() {
		return empleadoRealiza;
	}

	public void setEmpleado(String empleado) {
		this.empleadoRealiza = empleado;
	}

	
	
	
}
