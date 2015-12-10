package BLL;

public class Coche{
	private String matricula;
	private int kmsActuales;
	private Sucursal sucursal;
	private Categoria categoria;
	
	public Coche(String matricula, int kmsActuales, Sucursal sucursal,Categoria categoria) {
		super();
		this.matricula = matricula;
		this.kmsActuales = kmsActuales;
		this.sucursal = sucursal;
		this.categoria = categoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getKmsActuales() {
		return kmsActuales;
	}
	public void setKmsActuales(int kmsActuales) {
		this.kmsActuales = kmsActuales;
	}
}
