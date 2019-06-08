package logico;

public class CantProductosUtilizados {
	
	private Producto producto;
	private float cantidad;
	
	public CantProductosUtilizados(Producto producto, float cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
}
