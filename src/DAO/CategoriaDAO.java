package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import BLL.Categoria;
import BLL.Coche;
import DAO.dto.CategoriaDTO;
import UTIL.ConnectionManager;
import UTIL.DAOExcepcion;

public class CategoriaDAO implements ICategoriaDAO {

	protected ConnectionManager connManager;
	private Gson gson;

	public CategoriaDAO() throws DAOExcepcion {
		super();
		try {
			connManager = new ConnectionManager("alquilervehiculosBD");
			gson = new Gson();
		} catch (ClassNotFoundException e) {
			throw new DAOExcepcion(e);
		}
	}

	public CategoriaDTO buscarCategoria(String nombre) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CATEGORIA where NOMBRE= '"+nombre+"'");
			connManager.close();
		
			if (rs.next())
				return new CategoriaDTO(
							rs.getString("NOMBRE"),
							rs.getDouble("PRECIOMODILIMITADA"),
							rs.getDouble("PRECIOMODKMS"),
							rs.getDouble("PRECIOKMMODKMS"),
							rs.getDouble("PRECIOSEGUROTRIESGO"),
							rs.getDouble("PRECIOSEGUROTERCEROS"),
							rs.getString("CATEGORIASUPERIOR"));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}

	
	public String obtenerCategorias() throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CATEGORIA");						
			connManager.close();
	  	  
			ArrayList<CategoriaDTO> listaCatDTO = new ArrayList<CategoriaDTO>();
			HashMap<String, Categoria> mapCategorias = new HashMap<String, Categoria>();
			try{				
				while (rs.next()){

					CategoriaDTO cat = new CategoriaDTO(
							rs.getString("NOMBRE"),
							rs.getDouble("PRECIOMODILIMITADA"),
							rs.getDouble("PRECIOMODKMS"),
							rs.getDouble("PRECIOKMMODKMS"),
							rs.getDouble("PRECIOSEGUROTRIESGO"),
							rs.getDouble("PRECIOSEGUROTERCEROS"),
							rs.getString("CATEGORIASUPERIOR"));	 
					listaCatDTO.add(cat);
					mapCategorias.put(cat.getNombre(), new Categoria(cat, null));
				}
				
				//Vinculamos cada categoria con su categoria superior
				Categoria catAux;
				for(int i=0; i<listaCatDTO.size() ; i++){
					catAux = mapCategorias.get( listaCatDTO.get(i).getNombre() );
					catAux.setCategoriaSuperior( mapCategorias.get(listaCatDTO.get(i).getNombreCategoriaSuperior()) );
				}
				
				return gson.toJson( new ArrayList<Categoria>(mapCategorias.values()) );
				
			}catch (Exception e){	
				throw new DAOExcepcion(e);
			}
		}catch (SQLException e){	
			throw new DAOExcepcion(e);
		}	
		

	}

}
