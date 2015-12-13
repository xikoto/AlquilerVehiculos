package DAO;

import java.util.ArrayList;

import DAO.dto.CocheDTO;
import UTIL.DAOExcepcion;

public interface ICocheDAO {
	public ArrayList<CocheDTO> obtenerCochesSucursal(int idSucrursal) throws DAOExcepcion;

	public ArrayList<CocheDTO> obtenerCochesCategoria(String categoria) throws DAOExcepcion;
}
