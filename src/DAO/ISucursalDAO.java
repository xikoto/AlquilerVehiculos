//ISucursalDAO
package DAO;

import java.util.ArrayList;

import DAO.dto.EmpleadoDTO;
import UTIL.DAOExcepcion;


public interface ISucursalDAO {
	public String obtenerSucursales() throws DAOExcepcion;
	
	public ArrayList<EmpleadoDTO> obtenerEmpleados(int id)throws DAOExcepcion;
}
