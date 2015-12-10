package BLL;

import java.time.LocalDate;
import java.util.ArrayList;

public class Entrega {
	private LocalDate fecha;
	private String tipoSeguro;
	private int kms;
	private int combustible;
	private Coche coche;
	private Empleado empleado;
	private Reserva reserva;
	private ArrayList<Danyo> listaDanyos;
	private Devolucion devolucion;
	public Entrega(LocalDate fecha, String tipoSeguro, int kms, int combustible,
			Coche coche, Empleado empleado, Reserva reserva,
			ArrayList<Danyo> listaDanyos, Devolucion devolucion) {
		super();
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.coche = coche;
		this.empleado = empleado;
		this.reserva = reserva;
		this.listaDanyos = listaDanyos;
		this.devolucion = devolucion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public int getKms() {
		return kms;
	}
	public void setKms(int kms) {
		this.kms = kms;
	}
	public int getCombustible() {
		return combustible;
	}
	public void setCombustible(int combustible) {
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
	public void setListaDanyos(ArrayList<Danyo> listaDanyos) {
		this.listaDanyos = listaDanyos;
	}
	public Devolucion getDevolucion() {
		return devolucion;
	}
	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}
	
}
