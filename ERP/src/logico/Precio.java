package logico;

import java.time.LocalDate;

public class Precio {
	private float precio;
	private String descripcion;
	private boolean precioActivo;
	private LocalDate fecha;
	private int porc_ganancia;
	private int itbis;
	
	public Precio(float precio, String descripcion, boolean precioActivo, int porc_ganancia, int itbis) {
		super();
		this.precio = precio;
		this.descripcion = descripcion;
		this.precioActivo = precioActivo;
		this.fecha = LocalDate.now();
		this.setPorc_ganancia(porc_ganancia);
		this.setItbis(itbis);
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
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isPrecioActivo() {
		return precioActivo;
	}
	public void setPrecioActivo(boolean precioActivo) {
		this.precioActivo = precioActivo;
	}

	public int getPorc_ganancia() {
		return porc_ganancia;
	}

	public void setPorc_ganancia(int porc_ganancia) {
		this.porc_ganancia = porc_ganancia;
	}

	public int getItbis() {
		return itbis;
	}

	public void setItbis(int itbis) {
		this.itbis = itbis;
	}
	
	

}
