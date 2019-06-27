package logico;

public class CantProductosUtilizados {
	
	private Producto producto;
	private float cantidad;
	private String unidad;
	private boolean borrado;
	
	public CantProductosUtilizados(Producto producto, float cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		setBorrado(false);
		if(producto.getUnidadMedida() != null) {
			this.unidad = producto.getUnidadMedida().getNombre();
		}
		else {
			this.unidad = "";
		}
		
	}

	
	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public String getProducto() {
		return producto.getNombre();
	}
	
	public Producto getProductoClass() {
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


	public boolean isBorrado() {
		return borrado;
	}


	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
}
