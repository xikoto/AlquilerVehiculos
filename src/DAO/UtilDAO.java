package DAO;

public class UtilDAO {
	
	protected String limpiarString(String str){
		
		if( str != null )
			str = str.trim();
		else
			str = "";
		
		return str;
		
	}
	
}
