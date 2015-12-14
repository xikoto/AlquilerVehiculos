package BLL;

import java.util.ArrayList;
import java.util.HashMap;

import DAO.dto.SucursalDTO;

public class Sucursal {
	private int id;
	private String direccion;
	private ArrayList<Reserva> devolucionesReserva;
	private ArrayList<Reserva> recogidasReserva;
	private ArrayList<Empleado> listaEmpleados;
	private HashMap<String,Coche> listaCoches;
	
	public Sucursal(int id,String direccion) {
		super();
		this.id=id;
		this.direccion = direccion;
		this.devolucionesReserva = new ArrayList<Reserva>();
		this.recogidasReserva = new ArrayList<Reserva>();
		this.listaEmpleados = new ArrayList<Empleado>();
		this.listaCoches = new HashMap<String,Coche>();
	}
	
	public Sucursal(SucursalDTO sucursalDTO){
		this( sucursalDTO.getId(), sucursalDTO.getDirección());
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ArrayList<Reserva> getDevolucionesReserva() {
		return devolucionesReserva;
	}
	public void addDevolucionReserva(Reserva reserva){
		devolucionesReserva.add(reserva);
	}
	public ArrayList<Reserva> getRecogidasReserva() {
		return recogidasReserva;
	}
	public void addRecogidaReserva(Reserva reserva){
		recogidasReserva.add(reserva);
	}
	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	public HashMap<String,Coche> getListaCoches() {
		return listaCoches;
	}
	public void addCoche(Coche c){
		listaCoches.put(c.getMatricula(),c);
	}
	
	public void addEmpleado(Empleado e){
		listaEmpleados.add(e);
	}
}
