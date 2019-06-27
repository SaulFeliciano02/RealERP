package logico;

public class CostoIndirectoProducto {
	
	private String nombre;
	private float valor;
	private String descripcion;
	private boolean borrado;
	
	
	
	public CostoIndirectoProducto(String nombre, float valor, String descripcion) {
		super();
		this.nombre = nombre;
		this.valor = valor;
		this.descripcion = descripcion;
		this.setBorrado(false);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getValor() {
		return valor;
	}
	public void setPrecioCosto(float valor) {
		this.valor = valor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	

}
