package BLL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DAO.DAL;
import DAO.dto.ClienteDTO;
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
	 * 	MÉTODOS GET
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
	public ArrayList<Reserva> listarReservasSucursal(int sucursalID){
		
		ArrayList<RegListaResSucDTO> listaDTO=null;
		//tratamos las posibles excepciones para avisar a la vista (con el try/catch)
		try {
			listaDTO = (ArrayList<RegListaResSucDTO>) dal.obtenerReservasPorSucursalOrigen(sucursalID);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			//A toda la sucursal que cogemos del hashMap le añadimos la Reserva
			listaSucursales.get( dto.getIdSucursalRecogida() ).addRecogidaReserva(auxReserva);
			listaSucursales.get( dto.getIdSucursalDevolucion() ).addDevolucionReserva(auxReserva);
			
			listaReservas.put(auxReserva.getId(), auxReserva);
		}
		
		return new ArrayList<Reserva>( listaReservas.values());
		
	}
	
	public List<Sucursal> listarSucursales() {
		 return new ArrayList<Sucursal>(listaSucursales.values());
	}
	
	public void crearReserva(ReservaDTO reservaDTO) throws DAOExcepcion{
		reservaDTO = dal.crearReserva(reservaDTO);
		
		//Creo una nueva Reserva pasandole las sucursales correspondientes obteniendolas de 
		//los hasmap basandome en su id. Con el cliente hago lo mismo, este existirá en el hash map
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
		//Añadimos la RELACION para poder hacer de 0 a muchos
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
	
	private void cargarSistema() throws DAOExcepcion{
		/*Sucursal suc1  = new Sucursal(1,"Camino de Vera s/n");
		Sucursal suc2  = new Sucursal(2,"Archiduque Carlos, 3");
		listaSucursales.put(new Integer(suc1.getId()), suc1);
		listaSucursales.put(new Integer(suc2.getId()), suc2);
		
		Categoria cat1 = new Categoria("sedán",(double) 45, (double)23, (double)0.75,(double) 50.25, (double) 43.23, null);
		Categoria cat2 = new Categoria("economy", (double)48, (double)27, (double)0.85, (double) 75.25, (double) 55.23, cat1);
		listaCategorias.put(cat1.getNombre(), cat1);
		listaCategorias.put(cat2.getNombre(), cat2);*/
		
		//Carga de la DB
		//cargarClientes();//Revisar		
		cargarCategorias();
		cargarSucursales();
		//Cargar Reservas
		
	}
	
	@SuppressWarnings("unused")
	private void cargarClientes() throws DAOExcepcion{
		String listaEnJson = dal.listarClientes();
		ArrayList<Cliente> clientesDAO = gson.fromJson(listaEnJson, new TypeToken<ArrayList<Cliente>>(){}.getType());
		
		for(int i=0 ; i<clientesDAO.size() ; i++){
			listaClientes.put( clientesDAO.get(i).getDni() , clientesDAO.get(i));
		}
	}
	
	//Me falta añadir la lista de coches a cada categoria
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
