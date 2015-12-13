package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.dto.CocheDTO;
import UTIL.ConnectionManager;
import UTIL.DAOExcepcion;

public class CocheDAO extends UtilDAO implements ICocheDAO{
    protected ConnectionManager connManager;
    
    public CocheDAO() throws DAOExcepcion {
        super();
        try {
            connManager = new ConnectionManager("alquilervehiculosBD");
        } catch (ClassNotFoundException e) {
            throw new DAOExcepcion(e);
        }
    }

    @Override
    public ArrayList<CocheDTO> obtenerCochesSucursal(int idSucrursal) throws DAOExcepcion{
        try{
            connManager.connect();
            ResultSet rs=connManager.queryDB("select * from COCHE where sucursal="+idSucrursal);
            connManager.close();
            
            String matricula, nombre;
            ArrayList<CocheDTO> listac=new ArrayList<CocheDTO>();
            while(rs.next()){
            	
            	matricula = limpiarString(rs.getString("matricula"));
            	nombre = limpiarString(rs.getString("nombre"));
            	
                listac.add(new CocheDTO(matricula, 
                                        rs.getDouble("kmsActuales"), 
                                        rs.getInt("sucursal"),
                                        nombre));
            }
            
            if(listac.isEmpty())throw new DAOExcepcion("No existen registros de coches para la sucursal: "+ idSucrursal);
            return listac;
        }
        catch (SQLException e){    throw new DAOExcepcion(e);}    
    }

	@Override
	public ArrayList<CocheDTO> obtenerCochesCategoria(String categoria) throws DAOExcepcion {
		try{
            /*connManager.connect();
            ResultSet rs=connManager.queryDB("select * from COCHE where sucursal="+idSucrursal);
            connManager.close();
            
            String matricula, nombre;
            ArrayList<CocheDTO> listac=new ArrayList<CocheDTO>();
            while(rs.next()){
            	
            	matricula = limpiarString(rs.getString("matricula"));
            	nombre = limpiarString(rs.getString("nombre"));
            	
                listac.add(new CocheDTO(matricula, 
                                        rs.getDouble("kmsActuales"), 
                                        rs.getInt("sucursal"),
                                        nombre));
            }
            
            if(listac.isEmpty())throw new DAOExcepcion("No existen registros de coches para la sucursal: "+ idSucrursal);
            return listac;*/
			ResultSet rs;
			boolean bandera;
			do{
				bandera = false;
				connManager.connect();
	            rs=connManager.queryDB("select * from COCHE where categoria='"+ categoria + "'");
	            connManager.close();
	            
	            String matricula, nombre;
	            ArrayList<CocheDTO> listaCocheDTO=new ArrayList<CocheDTO>();
	            while(rs.next()){
	            	
	            	matricula = limpiarString(rs.getString("matricula"));
	            	nombre = limpiarString(rs.getString("nombre"));
	            	
	            	listaCocheDTO.add(new CocheDTO(matricula, 
	                                        rs.getDouble("kmsActuales"), 
	                                        rs.getInt("sucursal"),
	                                        nombre));
	            }
	            
	            if( listaCocheDTO.isEmpty() ){
	            	categoria = subirCategoria(categoria);
	            	if()
	            }
	            	
	            
			}while( bandera );
			
        }catch (SQLException e){
        	throw new DAOExcepcion(e);
        } 
	}
	
	private String subirCategoria(String catActual) throws DAOExcepcion{
		try{
			
			connManager.connect();
			String sql = "select categoriaSuperior from CATEGORIA where nombre='" + catActual +"'";
            ResultSet rs=connManager.queryDB(sql);
            connManager.close();
			
            String catSup = null;
            if(rs.next()){
            	catSup = limpiarString( rs.getString("categoriaSuperior"));
            }
            return catSup;
		}catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}

}

