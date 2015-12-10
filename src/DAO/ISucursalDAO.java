//ISucursalDAO
package DAO;

import java.util.List;

import DAO.dto.SucursalDTO;
import UTIL.DAOExcepcion;


public interface ISucursalDAO {
	public String obtenerSucursales() throws DAOExcepcion;
}
