package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

import BLL.Sucursal;
import DAO.dto.EmpleadoDTO;
import UTIL.*;

public class SucursalDAO extends UtilDAO implements ISucursalDAO{

	private ConnectionManager connManager;
	private Gson gson;
	
	public SucursalDAO() throws DAOExcepcion{
		try{
			connManager= new ConnectionManager("alquilervehiculosBD");
			gson= new Gson();
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
		}
	}
	
	@Override
	public String obtenerSucursales() throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from SUCURSAL");						
			connManager.close();
	  	  
			ArrayList<Sucursal> listaSucursales = new ArrayList<Sucursal>();
			try{	
				String direccion = "";
				while (rs.next()){

					direccion = limpiarString( rs.getString("direccion"));
					listaSucursales.add(new Sucursal( rs.getInt("id"), direccion
										));
					
				}
				
				return gson.toJson( listaSucursales );
				
			}catch (Exception e){	
				throw new DAOExcepcion(e);
			}
		}catch (SQLException e){	
			throw new DAOExcepcion(e);
		}	
	}

	@Override
	public ArrayList<EmpleadoDTO> obtenerEmpleados(int id) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from EMPLEADO where sucursal='"+id+"'");						
			connManager.close();
	  	  
			ArrayList<EmpleadoDTO> listaEmpleados = new ArrayList<EmpleadoDTO>();
			String dni,nombre,sucursal;
			boolean administrador;
			while (rs.next()){
				dni=rs.getString("DNI");
				nombre=rs.getString("NOMBRE");
				sucursal=rs.getString("SUCURSAL");
				administrador=Boolean.parseBoolean(rs.getString("ADMINISTRADOR"));
				listaEmpleados.add(new EmpleadoDTO(dni,
												   nombre,
												   administrador,
												   sucursal));
				
			}
			if(listaEmpleados.isEmpty())throw new DAOExcepcion("No existen registros de empleado para la sucursal: "+id);
			return listaEmpleados;
		}catch (SQLException e){	
			throw new DAOExcepcion(e);
		}
	}

}
