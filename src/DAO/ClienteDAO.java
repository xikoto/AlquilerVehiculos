package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;

import BLL.Cliente;
import DAO.dto.ClienteDTO;
import UTIL.ConnectionManager;
import UTIL.DAOExcepcion;

public class ClienteDAO extends UtilDAO implements IClienteDAO{

	protected ConnectionManager connManager;
	
	private Gson gson;
	
	public ClienteDAO()throws DAOExcepcion {
		super();
		try{
			connManager= new ConnectionManager("alquilervehiculosBD");
			gson = new Gson();
		}catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
		}
		
	}

	@Override
	public ClienteDTO buscarCliente(String dni) throws DAOExcepcion {
		try{
			connManager.connect();
			String sql = "SELECT * FROM Cliente WHERE dni='" + dni + "'";
			ResultSet rs=connManager.queryDB(sql);
			connManager.close();
			
			String dniRS, nombre, direccion, poblacion, codPostal, digitosTC, tipoTC;
			if( rs.next()){
				
				//En prevencion de los null
				dniRS =		limpiarString( rs.getString("dni"));
				nombre = 	limpiarString( rs.getString("nombreApellidos"));
				direccion = limpiarString( rs.getString("direccion"));
				poblacion = limpiarString( rs.getString("poblacion"));				
				codPostal = limpiarString( rs.getString("codPostal"));				
				digitosTC = limpiarString( rs.getString("digitosTC"));
				tipoTC = 	limpiarString( rs.getString("tipoTC"));
				
				return new ClienteDTO(	dniRS,
										nombre,
										direccion,
										poblacion,
										codPostal,
										rs.getTimestamp("fechaCarnetConducir").toLocalDateTime(),
										digitosTC,
										rs.getInt("mesTC"),
										rs.getInt("añoTC"),
										rs.getInt("cvcTC"),
										tipoTC
									);
			}else{
				throw new DAOExcepcion("No existe ese cliente con DNI " + dni);
			}
			
		}catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}

	@Override
	public void crearCliente(ClienteDTO cliente) throws DAOExcepcion {
		
		try{
			connManager.connect();
			//La fecha hay que guardarla como Timestamp -> Hacer un casting de LocalDateTime a Timestamp
			String sql = 	"INSERT INTO Cliente " +
								"VALUES ('" + cliente.getDni() + "'," +
										"'" + cliente.getNombreyApellidos() + "'," +
										"'" + cliente.getDireccion() + "', " +
										"'" + cliente.getPoblacion() + "'," +
										"'" + cliente.getCodPostal() + "'," +
										"'" + Timestamp.valueOf(cliente.getFechaCarnetConducir()) + "'," +
										"'" + cliente.getDigitosTC() + "'," +
										cliente.getMesTC() + "," +
										cliente.getAñoTC() + "," +
										cliente.getCvcTC() + "," +
										"'" + cliente.getTipoTC() + "')"
								
								;
			
			connManager.updateDB(sql);
			connManager.close();
		
				
		}catch (SQLException e){
			throw new DAOExcepcion(e);
		}	
		
		
	}

	@Override
	public String listarClientes() throws DAOExcepcion {
		
		try{
			
			connManager.connect();
			String sql = "SELECT * FROM Cliente";
			ResultSet rs=connManager.queryDB(sql);
			connManager.close();
			
			String dni, nombre, direccion, poblacion, codPostal, digitosTC, tipoTC;
			
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			while(rs.next()){
				
				dni =		limpiarString( rs.getString("dni"));
				nombre = 	limpiarString( rs.getString("nombreApellidos"));
				direccion = limpiarString( rs.getString("direccion"));				
				poblacion = limpiarString( rs.getString("poblacion"));				
				codPostal = limpiarString( rs.getString("codPostal"));				
				digitosTC = limpiarString( rs.getString("digitosTC"));
				tipoTC = 	limpiarString( rs.getString("tipoTC"));
				
				listaClientes.add( new Cliente(	dni,
												nombre,
												direccion,
												poblacion,
												codPostal,
												rs.getTimestamp("fechaCarnetConducir").toLocalDateTime(),
												digitosTC,
												rs.getInt("mesTC"),
												rs.getInt("añoTC"),
												rs.getInt("cvcTC"),
												tipoTC
											  	));
			}
			
			if( listaClientes.isEmpty() ){
				throw new DAOExcepcion("No hay registros de clientes");
			}else{
				
				String listaEnJson = gson.toJson(listaClientes);
				return listaEnJson;
			}
				
		}catch(SQLException e){
			throw new DAOExcepcion(e);
		}
		
	}
	
	

}
