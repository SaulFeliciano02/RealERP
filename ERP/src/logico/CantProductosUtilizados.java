package logico;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CantProductosUtilizados{
	
	private Producto producto;
	private float cantidad;
	private String unidad;
	private boolean borrado;
	private String nombre;
	private float precioUnitario;
	private float valor;
	private String cantidadBig;
	
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
		this.nombre = producto.getNombre();
		this.precioUnitario = producto.getPrecio();
		this.valor = cantidad * producto.getPrecio();
		DecimalFormat formatter = new DecimalFormat("0.00000");
		this.cantidadBig = formatter.format(cantidad);
	}

	
	public String getUnidad() {
		return unidad;
	}


	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}


	public Producto getProducto() {
		return producto;
	}
	
	public String getNombre() {
		return nombre;
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


	public float getPrecioUnitario() {
		return precioUnitario;
	}


	public float getValor() {
		return valor;
	}


	public String getCantidadBig() {
		return cantidadBig;
	}


	public void setCantidadBig(String cantidadBig) {
		this.cantidadBig = cantidadBig;
	}
	
	
	
}
