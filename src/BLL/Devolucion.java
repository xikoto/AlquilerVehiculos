package BLL;

import java.time.LocalDate;
import java.util.ArrayList;

public class Devolucion {
	private LocalDate fecha;
	private float totalACobrar;
	private boolean cobrado;
	private int kms;
	private int combustible;
	private Entrega entrega;
	private Empleado empleado;
	private ArrayList<Danyo> listaDanyos;
	public Devolucion(LocalDate fecha, float totalACobrar, boolean cobrado, int kms,
			int combustible, Entrega entrega, Empleado empleado,
			ArrayList<Danyo> listaDanyos) {
		super();
		this.fecha = fecha;
		this.totalACobrar = totalACobrar;
		this.cobrado = cobrado;
		this.kms = kms;
		this.combustible = combustible;
		this.entrega = entrega;
		this.empleado = empleado;
		this.listaDanyos = listaDanyos;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public float getTotalACobrar() {
		return totalACobrar;
	}
	public void setTotalACobrar(float totalACobrar) {
		this.totalACobrar = totalACobrar;
	}
	public boolean isCobrado() {
		return cobrado;
	}
	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
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
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public ArrayList<Danyo> getListaDanyos() {
		return listaDanyos;
	}
	public void setListaDanyos(ArrayList<Danyo> listaDanyos) {
		this.listaDanyos = listaDanyos;
	}
	
}
