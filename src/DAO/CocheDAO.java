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

}

