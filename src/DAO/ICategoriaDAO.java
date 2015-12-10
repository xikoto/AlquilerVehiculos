//ICategoriaDAO
package DAO;


import DAO.dto.CategoriaDTO;
import UTIL.DAOExcepcion;

public interface ICategoriaDAO {
 public CategoriaDTO buscarCategoria(String nombre)throws DAOExcepcion;

 public String obtenerCategorias() throws DAOExcepcion;
}
