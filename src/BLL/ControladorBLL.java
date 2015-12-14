package BLL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DAO.DAL;
import DAO.dto.ClienteDTO;
import DAO.dto.CocheDTO;
import DAO.dto.EmpleadoDTO;
import DAO.dto.EntregaDTO;
import DAO.dto.RegListaResSucDTO;
import DAO.dto.ReservaDTO;
import UTIL.BLLExcepcion;
import UTIL.DAOExcepcion;

public class ControladorBLL {
	
	private static ControladorBLL controlador = new ControladorBLL();
	
	private Gson gson;
	
	private HashMap<Integer, Sucursal> listaSucursales;
	private HashMap<String, Categoria> listaCategorias;
	private HashMap<Integer, Reserva> listaReservas;
	private HashMap<String, Cliente> listaClientes;
	
	private DAL dal;
	
	private ControladorBLL(){
		//Aqui cargamos los datos predefinidos <Por Hacer>
		this.dal = DAL.getControlador();
		this.gson = new Gson();
		listaSucursales = new HashMap<Integer, Sucursal>();
		listaCategorias = new HashMap<String, Categoria>();
		listaReservas = new HashMap<Integer, Reserva>();
		listaClientes = new HashMap<String, Cliente>();
		try {
			cargarSistema();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * 	M�TODOS GET
	 * */
	public static ControladorBLL getControlador(){
		return controlador;
	}
	
	public Cliente getCliente(String dni){
		return listaClientes.get(dni);
	}
	
	public Categoria getCategoria(String nombre){
		return listaCategorias.get(nombre);
	}
	
	public Sucursal getSucursal(int id){
		return listaSucursales.get(id);
	}
	
	public Reserva getReserva(int id){
		return listaReservas.get(id);
	}
	
	
	
	public HashMap<Integer,Reserva> getListaReservas(){
		return listaReservas;
	}
	
	public ArrayList<Categoria> listarCategorias() {
		return new ArrayList<Categoria>(listaCategorias.values());
	}
	
	
	/*public Entrega getEntrega(String tipoSeguro, LocalDate fechaEntrega,LocalDate fechaDevolucion){
		Devolucion dev = new Devolucion(fechaDevolucion, 0, false, 0, 0, null, null, null);
		Entrega e=new Entrega(fechaEntrega, tipoSeguro, 0, 0, null, null, null, null, dev);
		dev.setEntrega(e);
		return e;
	}*/
	
	
	// Metodo para cambiar, metodo de apoyo para la creacion de vista
	public ArrayList<Reserva> listarReservasSucursal(int sucursalID)throws DAOExcepcion{

		ArrayList<RegListaResSucDTO> listaDTO = (ArrayList<RegListaResSucDTO>) dal.obtenerReservasPorSucursalOrigen(sucursalID);
		ArrayList<Reserva> lr = new ArrayList<Reserva>();
		Cliente auxCliente;
		Reserva auxReserva;
		for( RegListaResSucDTO dto : listaDTO ){
			auxCliente = new Cliente(dto);
			listaClientes.put(auxCliente.getDni(), auxCliente);
			
			auxReserva = new Reserva(	dto, //elemento de la lista
										listaSucursales.get(dto.getIdSucursalRecogida()) ,
										listaSucursales.get(dto.getIdSucursalDevolucion()),
										listaCategorias.get(dto.getNombreCategoria()),
										auxCliente
									);
			//A toda la sucursal que cogemos del hashMap le a�adimos la Reserva
			listaSucursales.get( dto.getIdSucursalRecogida() ).addRecogidaReserva(auxReserva);
			listaSucursales.get( dto.getIdSucursalDevolucion() ).addDevolucionReserva(auxReserva);
			lr.add(auxReserva);
			listaReservas.put(auxReserva.getId(), auxReserva);
		}
		
		return lr;
		
	}
	
	public List<Sucursal> listarSucursales() {
		 return new ArrayList<Sucursal>(listaSucursales.values());
	}
	
	public void crearReserva(ReservaDTO reservaDTO) throws DAOExcepcion{
		reservaDTO = dal.crearReserva(reservaDTO);
		
		//Creo una nueva Reserva pasandole las sucursales correspondientes obteniendolas de 
		//los hasmap basandome en su id. Con el cliente hago lo mismo, este existir� en el hash map
		//ya que lo cargaremos/crearemos cuando creamos la reserva
		Sucursal sucRec, sucDev;
		Categoria cat;
		Cliente cli;
		
		sucRec = listaSucursales.get(reservaDTO.getIdSucursalRecogida());
		sucDev = listaSucursales.get(reservaDTO.getIdSucursalDevolucion());
		cat = listaCategorias.get(reservaDTO.getNombreCategoria());
		cli = listaClientes.get(reservaDTO.getDniCliente());
		
		Reserva auxRes = new Reserva(	reservaDTO,
										sucRec,
										sucDev,
										cat,
										cli
									);
		//A�adimos la RELACION para poder hacer de 0 a muchos
		Sucursal sucAux = listaSucursales.get(reservaDTO.getIdSucursalRecogida());
		sucAux.addRecogidaReserva(auxRes);
		listaSucursales.get(reservaDTO.getIdSucursalDevolucion()).addDevolucionReserva(auxRes);
		//la insertamos en el hashMap
		listaReservas.put(auxRes.getId(), auxRes);
		
	}
	
	
	public boolean buscarCliente(String dni){
			try{
				ClienteDTO clienteDTO = dal.buscarCliente(dni);
				listaClientes.put(clienteDTO.getDni(), new Cliente(clienteDTO));
				return true;
			}catch(DAOExcepcion e){
				return false;
			}
	}
	
	public void crearCliente(ClienteDTO cliente) throws BLLExcepcion, DAOExcepcion{
		if( !listaClientes.containsKey(cliente.getDni()) ){
			dal.crearCliente(cliente);
			listaClientes.put(cliente.getDni(), new Cliente(cliente));
		}else{
			throw new BLLExcepcion("El cliente ya existe");
		}
	}
	
	public ArrayList<Coche> listarVehiculosDisponibles(int idSucursal) throws DAOExcepcion{
		ArrayList<CocheDTO> listaCochesDTO=dal.obtenerCochesSucursal(idSucursal);
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		Categoria cat;
		Sucursal suc;
		Coche c;
		for(CocheDTO cocheDTO : listaCochesDTO){
			cat = listaCategorias.get(cocheDTO.getCategoria());
			suc = listaSucursales.get(cocheDTO.getSucursal());
			c = new Coche(cocheDTO.getMatricula(), 
						  cocheDTO.getKmsActuales(), 
						  suc, 
						  cat);
			listaCoches.add(c);
			cat.addCoche(c);
			suc.addCoche(c);
		}
		return new ArrayList<Coche>(listaSucursales.get(idSucursal).getListaCoches().values());
	}
	
	
	
	/*****************************************************************************
	 * 						ENTREGAR VEHICULO RESERVADO      					 
	 * @throws DAOExcepcion *
	 *****************************************************************************/
	
	/**
	 * 1.- Listar reservas pendientes de entrega
	 * Listara las reservas de la sucursa indicada que esten faltas de entrega
	 */
	public ArrayList<Reserva> listarReservasPendientesEntrega(int idSucursal) throws DAOExcepcion{
		ArrayList<RegListaResSucDTO> listaResPEDTO = dal.obtenerReservasPendientesEntrega(idSucursal);
		
		Cliente auxCliente;
		Reserva auxReserva;
		ArrayList<Reserva> listaResPendEntrega = new ArrayList<Reserva>();
		for(RegListaResSucDTO dto : listaResPEDTO){
			auxCliente = new Cliente(dto);
			listaClientes.put(auxCliente.getDni(), auxCliente);
			
			auxReserva = new Reserva(	dto, //elemento de la lista
										listaSucursales.get(dto.getIdSucursalRecogida()) ,
										listaSucursales.get(dto.getIdSucursalDevolucion()),
										listaCategorias.get(dto.getNombreCategoria()),
										auxCliente
									);
			//A toda la sucursal que cogemos del hashMap le a�adimos la Reserva
			listaSucursales.get( dto.getIdSucursalRecogida() ).addRecogidaReserva(auxReserva);
			listaSucursales.get( dto.getIdSucursalDevolucion() ).addDevolucionReserva(auxReserva);
			listaResPendEntrega.add(auxReserva);
			listaReservas.put(auxReserva.getId(), auxReserva);
		}
		
		return listaResPendEntrega;
	}
	
	/**
	 * 2.- Listar Coches de la categor�a de la reserva que ha realizado el cliente.
	 * @throws DAOExcepcion 
	 * 
	 */
	public ArrayList<Coche> listarCochesPorCategoria(String categoria) throws DAOExcepcion{
		ArrayList<CocheDTO> listaCochesDTO = dal.obtenerCochesCategoria(categoria);
		
		Coche cocheAux;
		Sucursal sucAux;
		Categoria catAux;
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		for(CocheDTO dto : listaCochesDTO){
			sucAux = listaSucursales.get( dto.getSucursal());
			catAux = listaCategorias.get( dto.getCategoria());
			cocheAux = new Coche( 	dto.getMatricula(),
									dto.getKmsActuales(),
									sucAux,
									catAux
								);
			sucAux.addCoche(cocheAux);
			catAux.addCoche(cocheAux);
			listaCoches.add(cocheAux);
		}
		return listaCoches;
	}
	
	/**
	 * 3.- Listar Empleados de la sucursal.
	 * @throws DAOExcepcion 
	 * 
	 */

	public ArrayList<Empleado> obtenerEmpleados(int idSuc) throws DAOExcepcion{
		ArrayList<EmpleadoDTO> listaEmpleadosDTO = dal.obtenerEmpleados(idSuc);
		
		Empleado empleadoAux;
		Sucursal sucAux;
		ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();
		for(EmpleadoDTO dto : listaEmpleadosDTO){
			sucAux = listaSucursales.get( Integer.parseInt(dto.getSucursal()));
			empleadoAux = new Empleado(dto.getDni(),dto.getNombre(),dto.isAdministrador());
			sucAux.addEmpleado(empleadoAux);
			listaEmpleado.add(empleadoAux);
		}
		return listaEmpleado;
	}
	
	/**
	 * 4.- A�adir entrega en DB
	 * @throws DAOExcepcion 
	 */
	public void entregarVehiculoReservado(EntregaDTO entregaDTO, int idSucursal) throws DAOExcepcion{
		dal.entregarVehiculoReservado(entregaDTO);
		
		Coche cocheAux = listaSucursales.get(idSucursal).getCoche(entregaDTO.getCoche());
		Empleado empAux = listaSucursales.get(idSucursal).getEmpleado(entregaDTO.getEmpleado());
		Reserva resAux = listaReservas.get(entregaDTO.getId());
		
		Entrega entregaAux = new Entrega( entregaDTO,cocheAux,empAux, resAux);
		
		resAux.setEntrega(entregaAux);
		
	}
	
	
	
	/************************************************************************************
	 * 						CARGAR SISTEMA												*
	 ************************************************************************************/
	
	private void cargarSistema() throws DAOExcepcion{
		cargarCategorias();
		cargarSucursales();
	}
	
	@SuppressWarnings("unused")
	private void cargarClientes() throws DAOExcepcion{
		String listaEnJson = dal.listarClientes();
		ArrayList<Cliente> clientesDAO = gson.fromJson(listaEnJson, new TypeToken<ArrayList<Cliente>>(){}.getType());
		
		for(int i=0 ; i<clientesDAO.size() ; i++){
			listaClientes.put( clientesDAO.get(i).getDni() , clientesDAO.get(i));
		}
	}
	
	private void cargarCategorias()throws DAOExcepcion{
		String listaEnJson = dal.obtenerCategorias();
		ArrayList<Categoria> categoriasDAO = gson.fromJson(listaEnJson, new TypeToken<ArrayList<Categoria>>(){}.getType());
		
		for(int i=0; i<categoriasDAO.size() ; i++){
			listaCategorias.put(categoriasDAO.get(i).getNombre(), categoriasDAO.get(i));
		}
	}
	
	private void cargarSucursales() throws DAOExcepcion{
		String listaEnJson = dal.obtenerSucursales();
		ArrayList<Sucursal> sucursalesDAO = gson.fromJson(listaEnJson, new TypeToken<ArrayList<Sucursal>>(){}.getType());
		
		for(int i=0; i<sucursalesDAO.size() ; i++){
			listaSucursales.put( sucursalesDAO.get(i).getId(), sucursalesDAO.get(i));
		}
	}
	
}
