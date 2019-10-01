package logico;

public class CantBienesYServiciosUtilizados {
	private String unidad;
	private float cantidad;
	private String nombre;
	private float precioUnitario;
	private float valor;
	
	
	
	public CantBienesYServiciosUtilizados(String unidad, float cantidad, String nombre, float precioUnitario,
			float valor) {
		super();
		this.unidad = unidad;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.valor = valor;
	}
	
	public String getUnidad() {
		return unidad;
	}
	public float getCantidad() {
		return cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public float getValor() {
		return valor;
	}
	
	
	
}
