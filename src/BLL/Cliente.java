package BLL;

import java.time.LocalDateTime;

import DAO.dto.ClienteDTO;
import DAO.dto.RegListaResSucDTO;

public class Cliente {
	private String dni;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String cp;
	private LocalDateTime fechaCarnetConducir;
	private String digitosTC;
	private int mesTC;
	private int anyoTC;
	private int cvcTC;
	private String tipoTC;

	
	public Cliente(
			String dni,String nombre,String direccion, String poblacion, String cp,
			LocalDateTime fechaCarnetConducir, String digitosTC, int mesTC, int anyoTC,
			int cvcTC, String tipoTC) {
		super();
		this.dni = dni;
		this.nombre=nombre;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.cp = cp;
		this.fechaCarnetConducir = fechaCarnetConducir;
		this.digitosTC = digitosTC;
		this.mesTC = mesTC;
		this.anyoTC = anyoTC;
		this.cvcTC = cvcTC;
		this.tipoTC = tipoTC;
	}
	
	public Cliente(ClienteDTO clienteDTO){
		this( 	clienteDTO.getDni(), clienteDTO.getNombreyApellidos(), clienteDTO.getDireccion(),
				clienteDTO.getPoblacion(), clienteDTO.getCodPostal(), clienteDTO.getFechaCarnetConducir(),
				clienteDTO.getDigitosTC(), clienteDTO.getMesTC(), clienteDTO.getAñoTC(),
				clienteDTO.getCvcTC(), clienteDTO.getTipoTC()
			);
	}
	
	public Cliente(RegListaResSucDTO dto){
		this( 	dto.getDniCliente(), dto.getNombreyApellidos(), dto.getDireccion(), dto.getPoblacion(), dto.getCodPostal(),
				dto.getFechaCarnetConducir(), dto.getDigitosTC(), dto.getMesTC(), dto.getAñoTC(), dto.getCvcTC(),
				dto.getTipoTC()
				);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
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
	public int getAnyoTC() {
		return anyoTC;
	}
	public void setAnyoTC(int anyoTC) {
		this.anyoTC = anyoTC;
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
