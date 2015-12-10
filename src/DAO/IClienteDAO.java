//IClienteDAO
package DAO;

import DAO.dto.ClienteDTO;
import UTIL.DAOExcepcion;


public interface IClienteDAO {
	public ClienteDTO buscarCliente(String dni)throws DAOExcepcion;

 	public void crearCliente(ClienteDTO cliente) throws DAOExcepcion;
 
 	public String listarClientes() throws DAOExcepcion;
 
}
