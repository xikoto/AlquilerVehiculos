package UTIL;

import BLL.ControladorBLL;
import BLL.Reserva;

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
			for(int i = 1; i<4; i++)
				for (Reserva r : ControladorBLL.getControlador().listarReservasSucursal(i)) {
					System.out.println(r.getId() + " " + r.getSucursalRecogida().getId() + " "
							+ r.getSucursalDevolucion().getId());
				}
			
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
