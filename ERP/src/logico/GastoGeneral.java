package logico;

import java.time.LocalDate;

public class GastoGeneral {
	
	private String nombre;
	private float precioUnitario;
	private String descripcion;
	private LocalDate remodelado;
	private boolean borrado;
	
	public GastoGeneral(String nombre, float precioUnitario, String descripcion, LocalDate remodelado) {
		super();
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		this.remodelado = remodelado; 
		this.setBorrado(false);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public LocalDate getRemodelado() {
		return remodelado;
	}

	public void setRemodelado(LocalDate remodelado) {
		this.remodelado = remodelado;
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
