package BLL;

import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.dto.EntregaDTO;

public class Entrega {
	private int id;
	private LocalDateTime fecha;
	private String tipoSeguro;
	private double kms;
	private double combustible;
	private Coche coche;
	private Empleado empleado;
	private Reserva reserva;
	private ArrayList<Danyo> listaDanyos;
	private Devolucion devolucion;
	
	public Entrega(int id, LocalDateTime fecha, String tipoSeguro, double kms, double combustible,
			Coche coche, Empleado empleado, Reserva reserva, Devolucion devolucion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.coche = coche;
		this.empleado = empleado;
		this.reserva = reserva;
		this.listaDanyos = new ArrayList<Danyo>();
		this.devolucion = devolucion;
	}
	
	public Entrega(EntregaDTO dto,Coche coche, Empleado empleado, Reserva reserva){
		this(	dto.getId(),
				dto.getFecha(),
				dto.getTipoSeguro(),
				dto.getKms(),
				dto.getCombustible(),
				coche,
				empleado,
				reserva,
				null
				);
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
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
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public ArrayList<Danyo> getListaDanyos() {
		return listaDanyos;
	}
	public void addDanyo(Danyo danyo) {
		this.listaDanyos.add(danyo);
	}
	public Devolucion getDevolucion() {
		return devolucion;
	}
	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}
	
}
