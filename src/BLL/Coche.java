package BLL;

public class Coche{
	private String matricula;
	private double kmsActuales;
	private Sucursal sucursal;
	private Categoria categoria;
	
	public Coche(String matricula, double kmsActuales, Sucursal sucursal,Categoria categoria) {
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
	public double getKmsActuales() {
		return kmsActuales;
	}
	public void setKmsActuales(double kmsActuales) {
		this.kmsActuales = kmsActuales;
	}
}
