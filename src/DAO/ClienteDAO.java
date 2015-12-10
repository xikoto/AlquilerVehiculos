package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;

import BLL.Cliente;
import DAO.dto.CategoriaDTO;
import DAO.dto.ClienteDTO;
import UTIL.ConnectionManager;
import UTIL.DAOExcepcion;

public class ClienteDAO implements IClienteDAO{

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
			
			String nombre, direccion, poblacion, codPostal, digitosTC, tipoTC;
			if( rs.next()){
				
				//En prevencion de los null
				nombre = rs.getString("nombreApellidos");
				if(nombre == null) nombre = "";
				
				direccion = rs.getString("direccion");
				if(direccion == null) direccion = "";
				
				poblacion = rs.getString("poblacion");
				if(poblacion == null) poblacion = "";
				
				codPostal = rs.getString("codPostal");
				if(codPostal == null) codPostal = "";
				
				digitosTC = rs.getString("digitosTC");
				if(digitosTC == null) digitosTC = "";
				
				tipoTC = rs.getString("tipoTC");
				if(tipoTC == null) tipoTC = "";
				
				return new ClienteDTO(	rs.getString("dni"),
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
			
			String nombre, direccion, poblacion, codPostal, digitosTC, tipoTC;
			
			ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
			while(rs.next()){
				
				nombre = rs.getString("nombreApellidos");
				if(nombre == null) nombre = "";
				
				direccion = rs.getString("direccion");
				if(direccion == null) direccion = "";
				
				poblacion = rs.getString("poblacion");
				if(poblacion == null) poblacion = "";
				
				codPostal = rs.getString("codPostal");
				if(codPostal == null) codPostal = "";
				
				digitosTC = rs.getString("digitosTC");
				if(digitosTC == null) digitosTC = "";
				
				tipoTC = rs.getString("tipoTC");
				if(tipoTC == null) tipoTC = "";
				
				listaClientes.add( new Cliente(	rs.getString("dni"),
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
