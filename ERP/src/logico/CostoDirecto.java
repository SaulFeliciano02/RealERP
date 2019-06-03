package logico;

public class CostoDirecto {
	private String nombre;
	private double valor;
	private String descripcion;
	
	public CostoDirecto(String nombre, double valor, String descripcion) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
