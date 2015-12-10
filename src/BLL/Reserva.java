package BLL;

import java.time.LocalDateTime;

import DAO.dto.RegListaResSucDTO;
import DAO.dto.ReservaDTO;

public class Reserva {
	private int id;
	private LocalDateTime fechaRecogida;
	private LocalDateTime fechaDevolucion;
	private String modalidadAlquiler;
	private Sucursal sucursalDevolucion;
	private Sucursal sucursalRecogida;
	private Categoria categoria;
	private Cliente cliente;
	private Entrega entrega;
	
	public Reserva(int id,LocalDateTime fechaRecogida, LocalDateTime fechaDevolucion,
			String modalidadAlquiler,Sucursal sucursalRecogida,
			Sucursal sucursalDevolucion,Categoria categoria, Cliente cliente,
			Entrega entrega) {
		super();
		this.id =id;
		this.fechaRecogida = fechaRecogida;
		this.fechaDevolucion = fechaDevolucion;
		this.modalidadAlquiler = modalidadAlquiler;
		this.sucursalDevolucion = sucursalDevolucion;
		this.sucursalRecogida = sucursalRecogida;
		this.categoria = categoria;
		this.cliente = cliente;
		this.entrega = entrega;
	}
	
	public Reserva(	ReservaDTO reservaDTO, 
					Sucursal sucursalRecogida, Sucursal sucursalDevolucion, 
					Categoria categoria, Cliente cliente){
		this(	reservaDTO.getId(),
				reservaDTO.getFechaRecogida(),
				reservaDTO.getFechaDevolucion(),
				reservaDTO.getModalidadAlquiler(),
				sucursalRecogida,
				sucursalDevolucion,
				categoria,
				cliente,
				null
				);
	}
	
	public Reserva(	RegListaResSucDTO dto, 
			Sucursal sucursalRecogida, Sucursal sucursalDevolucion, 
			Categoria categoria, Cliente cliente){
		this(	dto.getId(),
				dto.getFechaRecogida(),
				dto.getFechaDevolucion(),
				dto.getModalidadAlquiler(),
				sucursalRecogida,
				sucursalDevolucion,
				categoria,
				cliente,
				null
				);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getFechaRecogida() {
		return fechaRecogida;
	}
	public void setFechaRecogida(LocalDateTime fechaRecogida) {
		this.fechaRecogida = fechaRecogida;
	}
	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public String getModalidadAlquiler() {
		return modalidadAlquiler;
	}
	public void setModalidadAlquiler(String modalidadAlquiler) {
		this.modalidadAlquiler = modalidadAlquiler;
	}
	public Sucursal getSucursalDevolucion() {
		return sucursalDevolucion;
	}
	public void setSucursalDevolucion(Sucursal sucursalDevolucion) {
		this.sucursalDevolucion = sucursalDevolucion;
	}
	public Sucursal getSucursalRecogida() {
		return sucursalRecogida;
	}
	public void setSucursalRecogida(Sucursal sucursalRecogida) {
		this.sucursalRecogida = sucursalRecogida;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	
	
}
