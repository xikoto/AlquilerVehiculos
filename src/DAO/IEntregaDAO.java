package DAO;

import DAO.dto.EntregaDTO;
import UTIL.DAOExcepcion;

public interface IEntregaDAO {

	public void entregarVehiculoReservado(EntregaDTO entregaDTO) throws DAOExcepcion;
	
}
