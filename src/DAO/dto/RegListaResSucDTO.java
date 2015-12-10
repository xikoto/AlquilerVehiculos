package DAO.dto;

import java.time.LocalDateTime;

public class RegListaResSucDTO extends ReservaDTO{

	private String nombreyApellidos;
	private String direccion;
	private String poblacion;
	private String codPostal;
	private LocalDateTime fechaCarnetConducir;
	private String digitosTC;
	private int mesTC;
	private int añoTC;
	private int cvcTC;
	private String tipoTC;
	
	
	
	
	public RegListaResSucDTO(int id, LocalDateTime fechaRecogida, LocalDateTime fechaDevolucion,
			String modalidadAlquiler, String nombreCategoria, String dniCliente, int idSucursalRecogida,
			int idSucursalDevolucion, String nombreyApellidos, String direccion, String poblacion, String codPostal,
			LocalDateTime fechaCarnetConducir, String digitosTC, int mesTC, int añoTC, int cvcTC, String tipoTC) {
		super(id, fechaRecogida, fechaDevolucion, modalidadAlquiler, nombreCategoria, dniCliente, idSucursalRecogida,
				idSucursalDevolucion);
		this.nombreyApellidos = nombreyApellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
		this.fechaCarnetConducir = fechaCarnetConducir;
		this.digitosTC = digitosTC;
		this.mesTC = mesTC;
		this.añoTC = añoTC;
		this.cvcTC = cvcTC;
		this.tipoTC = tipoTC;
	}




	public String getNombreyApellidos() {
		return nombreyApellidos;
	}




	public void setNombreyApellidos(String nombreyApellidos) {
		this.nombreyApellidos = nombreyApellidos;
	}




	public String getDireccion() {
		return direccion;
	}




	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}




	public String getPoblacion() {
		return poblacion;
	}




	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}




	public String getCodPostal() {
		return codPostal;
	}




	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}




	public LocalDateTime getFechaCarnetConducir() {
		return fechaCarnetConducir;
	}




	public void setFechaCarnetConducir(LocalDateTime fechaCarnetConducir) {
		this.fechaCarnetConducir = fechaCarnetConducir;
	}




	public String getDigitosTC() {
		return digitosTC;
	}




	public void setDigitosTC(String digitosTC) {
		this.digitosTC = digitosTC;
	}




	public int getMesTC() {
		return mesTC;
	}




	public void setMesTC(int mesTC) {
		this.mesTC = mesTC;
	}




	public int getAñoTC() {
		return añoTC;
	}




	public void setAñoTC(int añoTC) {
		this.añoTC = añoTC;
	}




	public int getCvcTC() {
		return cvcTC;
	}




	public void setCvcTC(int cvcTC) {
		this.cvcTC = cvcTC;
	}




	public String getTipoTC() {
		return tipoTC;
	}




	public void setTipoTC(String tipoTC) {
		this.tipoTC = tipoTC;
	}
	
	

	
	
	
	
}
