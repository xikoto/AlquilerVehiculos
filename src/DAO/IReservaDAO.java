//IReservaDAO
package DAO;

import java.util.ArrayList;
import java.util.List;

import DAO.dto.RegListaResSucDTO;
import DAO.dto.ReservaDTO;
import UTIL.DAOExcepcion;


public interface IReservaDAO {
	public ReservaDTO buscarReserva(int identificador)throws DAOExcepcion;

	public List<RegListaResSucDTO> obtenerReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion;
 
	public ReservaDTO crearReserva(ReservaDTO reserva) throws DAOExcepcion;

	public ArrayList<RegListaResSucDTO> obtenerReservasPendientesEntrega(int idSucursal) throws DAOExcepcion;
}
