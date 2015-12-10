package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import BLL.Categoria;
import BLL.Sucursal;
import DAO.dto.CategoriaDTO;
import DAO.dto.SucursalDTO;
import UTIL.*;

public class SucursalDAO implements ISucursalDAO{

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

					direccion = rs.getString("direccion");
					if( direccion == null) direccion = "";
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

}
