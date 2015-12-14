package DAO;


import java.sql.SQLException;
import java.sql.Timestamp;

import DAO.dto.EntregaDTO;
import UTIL.ConnectionManager;
import UTIL.DAOExcepcion;

public class EntregaDAO implements IEntregaDAO {

	protected ConnectionManager connManager;
	
	public EntregaDAO() throws DAOExcepcion{
		super();
		try {
			connManager = new ConnectionManager("alquilervehiculosBD");
		} catch (ClassNotFoundException e) {
			throw new DAOExcepcion(e);
		}
	}
	
	@Override
	public void entregarVehiculoReservado(EntregaDTO entregaDTO) throws DAOExcepcion {
		try{
			connManager.connect();
			//La fecha hay que guardarla como Timestamp -> Hacer un casting de LocalDateTime a Timestamp
			String sql = 	"INSERT INTO ENTREGA VALUES (" +
													entregaDTO.getId() + ", " +
													"'" + Timestamp.valueOf( entregaDTO.getFecha()) + "', " +
													"'" + entregaDTO.getTipoSeguro() + "', " +
													entregaDTO.getKms() + ", " +
													entregaDTO.getCombustible() + ", " +
													"'" + entregaDTO.getCoche() + "', " +
													"'" + entregaDTO.getEmpleado() + "')"
								
								;
			
			connManager.updateDB(sql);
			connManager.close();
		
				
		}catch (SQLException e){
			throw new DAOExcepcion(e);
		}

	}
	
	

}
