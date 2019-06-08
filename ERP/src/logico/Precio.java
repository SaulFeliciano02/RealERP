package logico;

import java.time.LocalDate;

public class Precio {
	private float precio;
	private String descripcion;
	private boolean precioActivo;
	private LocalDate fecha;
	
	public Precio(float precio, String descripcion, boolean precioActivo) {
		super();
		this.precio = precio;
		this.descripcion = descripcion;
		this.precioActivo = precioActivo;
		this.fecha = LocalDate.now();
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isPrecioActivo() {
		return precioActivo;
	}
	public void setPrecioActivo(boolean precioActivo) {
		this.precioActivo = precioActivo;
	}
	
	

}
