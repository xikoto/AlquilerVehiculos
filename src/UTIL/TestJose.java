package UTIL;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.*;

import BLL.*;
import DAO.ReservaDAO;

public class TestJose {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		/*Gson gson = new Gson();
		
		ClasePruebaDos cp2 = new ClasePruebaDos("miguel");
		
		ClasePruebaUno cp1 = new ClasePruebaUno("JOse" , cp2);
		
		String json = gson.toJson(cp1);
		
		System.out.println(json);
		
		ClasePruebaUno cp3 = gson.fromJson(json, ClasePruebaUno.class);
		
		ArrayList<ClasePruebaUno> lista = new ArrayList<ClasePruebaUno>();
		lista.add(cp3);
		lista.add(cp1);
		
		String listaEnString = gson.toJson(lista);
		System.out.println(listaEnString);
		
		
		ArrayList<ClasePruebaUno> lista2 = gson.fromJson(listaEnString, new TypeToken<ArrayList<ClasePruebaUno>>(){}.getType());
		
		for(int i= 0 ; i< lista2.size(); i++){
			System.out.println(lista2.get(i).nombre +" "+ lista2.get(i).cpd.apellido);
		}*/
		
		try {
			ReservaDAO rDAO = new ReservaDAO();
			System.out.println("Va a hacer consulta");
			rDAO.pruebaConsulta( 2 );
			System.out.println("Ha hecho consulta");
			
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
