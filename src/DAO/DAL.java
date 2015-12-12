package DAO;


import java.util.ArrayList;
import java.util.List;

import DAO.dto.CocheDTO;
import DAO.dto.ClienteDTO;
import DAO.dto.RegListaResSucDTO;
import DAO.dto.ReservaDTO;
import UTIL.*;

public class DAL {
	
	private static DAL controlador = new DAL();

	private ICategoriaDAO categoriaDAO;
	private IReservaDAO reservaDAO;
	private IClienteDAO clienteDAO;
	private ISucursalDAO sucursalDAO;
	private ICocheDAO cocheDAO;
	
	public DAL(){
		try {
			this.categoriaDAO = new CategoriaDAO();
			this.reservaDAO = new ReservaDAO();
			this.clienteDAO = new ClienteDAO();
			this.sucursalDAO = new SucursalDAO();
			this.cocheDAO = new CocheDAO();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void setDAL(DAL controlador) {
		DAL.controlador = controlador;
	}

	public static DAL getControlador(){
		return controlador;
	}
	
	public ReservaDTO crearReserva(ReservaDTO reserva) throws DAOExcepcion{
		return reservaDAO.crearReserva(reserva);
	}
	
	public List<RegListaResSucDTO> obtenerReservasPorSucursalOrigen(int sucursalID) throws DAOExcepcion{
		return reservaDAO.obtenerReservasPorSucursalOrigen(sucursalID);
	}
	
	public ClienteDTO buscarCliente(String dni) throws DAOExcepcion{
		return clienteDAO.buscarCliente(dni);
	}
	
	public void crearCliente(ClienteDTO clienteDTO) throws DAOExcepcion{
		clienteDAO.crearCliente(clienteDTO);
	}
	
	public String listarClientes() throws DAOExcepcion{
		return clienteDAO.listarClientes();
	}
	
	public String obtenerCategorias() throws DAOExcepcion{
		return categoriaDAO.obtenerCategorias();
	}
	
	public String obtenerSucursales() throws DAOExcepcion{
		return sucursalDAO.obtenerSucursales();
	}

	public ArrayList<CocheDTO> obtenerCochesSucursal(int idSucursal) throws DAOExcepcion{
		return cocheDAO.obtenerCochesSucursal(idSucursal);
	}
	
}
