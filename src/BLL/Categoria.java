package BLL;

import java.util.HashMap;

import DAO.dto.CategoriaDTO;

public class Categoria {
	private String nombre;
	private double precioModIlimitada;
	private double precioModKms;
	private double precioKmModKms;
	private double precioSeguroTRiesgo;
	private double precioSeguroTerceros;
	private Categoria categoriaSuperior;
	private HashMap<String,Coche> listaCoches;
	
	public Categoria(String nombre, double precioModIlimitada,
			double precioModKms, double precioKmModKms,
			double precioSeguroTRiesgo, double precioSeguroTerceros,
			Categoria categoriaSuperior) {
		super();
		this.nombre = nombre;
		this.precioModIlimitada = precioModIlimitada;
		this.precioModKms = precioModKms;
		this.precioKmModKms = precioKmModKms;
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
		this.precioSeguroTerceros = precioSeguroTerceros;
		this.categoriaSuperior = categoriaSuperior;
		this.listaCoches = new HashMap<String,Coche>();
	}
	
	public Categoria(CategoriaDTO catDTO, Categoria catSup){
		this(catDTO.getNombre(), catDTO.getPrecioModIlimitada(), catDTO.getPrecioModKms(),
			 catDTO.getPrecioKMModKms(), catDTO.getPrecioSeguroTRiesgo(), catDTO.getPrecioSeguroTerceros(), 
			 catSup );
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecioModIlimitada() {
		return precioModIlimitada;
	}
	public void setPrecioModIlimitada(int precioModIlimitada) {
		this.precioModIlimitada = precioModIlimitada;
	}
	public double getPrecioModKms() {
		return precioModKms;
	}
	public void setPrecioModKms(int precioModKms) {
		this.precioModKms = precioModKms;
	}
	public double getPrecioKmModKms() {
		return precioKmModKms;
	}
	public void setPrecioKmModKms(int precioKmModKms) {
		this.precioKmModKms = precioKmModKms;
	}
	public double getPrecioSeguroTRiesgo() {
		return precioSeguroTRiesgo;
	}
	public void setPrecioSeguroTRiesgo(int precioSeguroTRiesgo) {
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
	}
	public double getPrecioSeguroTerceros() {
		return precioSeguroTerceros;
	}
	public void setPrecioSeguroTerceros(int precioSeguroTerceros) {
		this.precioSeguroTerceros = precioSeguroTerceros;
	}
	public Categoria getCategoriaSuperior() {
		return categoriaSuperior;
	}
	public void setCategoriaSuperior(Categoria categoriaSuperior) {
		this.categoriaSuperior = categoriaSuperior;
	}
	public HashMap<String,Coche> getListaCoches() {
		return listaCoches;
	}
	public void addCoche(Coche c){
		listaCoches.put(c.getMatricula(),c);
	}
}
