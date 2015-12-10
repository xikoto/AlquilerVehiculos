package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import DAO.dto.RegListaResSucDTO;
import DAO.dto.ReservaDTO;
import UTIL.ConnectionManager;
import UTIL.DAOExcepcion;

public class ReservaDAO implements IReservaDAO{
	
	protected ConnectionManager connManager;
	
	public ReservaDAO() throws DAOExcepcion{
		try {
			connManager = new ConnectionManager("alquilervehiculosBD");
		} catch (ClassNotFoundException e) {
			throw new DAOExcepcion(e);
		}
	}

	@Override
	public ReservaDTO buscarReserva(int identificador) throws DAOExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegListaResSucDTO> obtenerReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion {
		try{
			connManager.connect();
			String sql = "SELECT * " +
					 "FROM Reserva " +
					 "LEFT JOIN Cliente " +
					 "ON Reserva.clienteRealiza=Cliente.dni " +
					 "WHERE Reserva.sucursalRecogida= "+ idSucursal 
					 ;
		
			ResultSet rs=connManager.queryDB(sql);						
			connManager.close();
	  	  
			String alquiler, nombre, direccion, poblacion, codPostal, digitosTC, tipoTC;
			ArrayList<RegListaResSucDTO> listaReservasDTO = new ArrayList<RegListaResSucDTO>();
			try{			
				while (rs.next()){
					
					//En prevencion de los null
					alquiler = rs.getString("modalidadAlquiler");
					if(alquiler == null) alquiler = "";
					
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
					
					listaReservasDTO.add( new RegListaResSucDTO(	rs.getInt("id"), 
																	rs.getTimestamp("fechaRecogida").toLocalDateTime(), 
																	rs.getTimestamp("fechaDevolucion").toLocalDateTime(), 
																	alquiler, 
																	rs.getString("categoria"), 
																	rs.getString("clienteRealiza"), 
																	rs.getInt("sucursalRecogida"), 
																	rs.getInt("sucursalDevolucion"), 
																	nombre, 
																	direccion, 
																	poblacion, 
																	codPostal, 
																	rs.getTimestamp("fechaCarnetConducir").toLocalDateTime(), 
																	digitosTC, 
																	rs.getInt("mesTC"), 
																	rs.getInt("añoTC"), 
																	rs.getInt("cvcTC"), 
																	rs.getString("tipoTC")
										));
					
				}
				
				if( listaReservasDTO.isEmpty() ){
					throw new DAOExcepcion("No existen registros de reservas para la sucursal " + idSucursal);
				}else{
					return listaReservasDTO;
				}

				
				
			}catch (Exception e){	
				throw new DAOExcepcion(e);
			}
		}catch (SQLException e){	
			throw new DAOExcepcion(e);
		}	
	}

	@Override
	public ReservaDTO crearReserva(ReservaDTO reservaDTO) throws DAOExcepcion {
		
		try{
			connManager.connect();
			
			//Autoincrement
			String sql = "SELECT MAX(id) FROM Reserva";
			ResultSet rs=connManager.queryDB(sql);
			
			int idAInsertar = 1;
			if( rs.next() ){
				idAInsertar = rs.getInt(1); //coge el int de la 1ªcolumna
				idAInsertar++;
			}
			
			sql = "INSERT INTO Reserva " +
							"VALUES (" + idAInsertar + "," +
									"'"+ Timestamp.valueOf(reservaDTO.getFechaRecogida()) + "'," +
									"'"+ Timestamp.valueOf(reservaDTO.getFechaDevolucion()) + "'," +
									"'"+ reservaDTO.getModalidadAlquiler() + "'," +
									"'"+ reservaDTO.getNombreCategoria() + "'," +
									"'"+ reservaDTO.getDniCliente() + "'," +
									reservaDTO.getIdSucursalRecogida() + "," +
									reservaDTO.getIdSucursalDevolucion() + ")"
						;
			
			connManager.updateDB(sql);
			connManager.close();
			
			reservaDTO.setId(idAInsertar);
			return reservaDTO;
			
		}catch(SQLException e){
			throw new DAOExcepcion(e);
		}
		
	}
	
	public void pruebaConsulta(int idSucursal){
		try{
			connManager.connect();
			
			String sql = "SELECT * " +
						 "FROM Reserva " +
						 "LEFT JOIN Cliente " +
						 "ON Reserva.clienteRealiza=Cliente.dni " +
						 "WHERE Reserva.sucursalRecogida= "+ idSucursal 
						 ;
			
			//String sql = "select * from Reserva ";
			
			ResultSet rs=connManager.queryDB(sql);
			
			/*if(rs.next()){
				System.out.println( "Reserva: " + rs.getInt("id") + " Cliente: " + rs.getString("clienteRealiza"));
			}else{
				System.out.println("No hay registros");
			}*/
			
			while(rs.next()){
				System.out.println( "Reserva: " + rs.getInt("id") + " Cliente: " + rs.getString("dni") + " " + rs.getString("nombreApellidos"));
				//System.out.println( "Reserva: " + rs.getInt("id") + " Cliente: " + rs.getString("clienteRealiza"));
			}
			
			/*String sql = "INSERT INTO Reserva VALUES(101,'" + Timestamp.valueOf(LocalDateTime.now()) +"' ,'" + Timestamp.valueOf(LocalDateTime.now()) +"' , 'modali2', 'sedán', '11111111A', 2, 2)";
			connManager.updateDB(sql);*/
			
			connManager.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
