package logico;

import java.time.LocalDate;
import java.util.Calendar;

public class GastoGeneral {
	
	private String nombre;
	private float precioUnitario;
	private String descripcion;
	private LocalDate remodelado;
	
	public GastoGeneral(String nombre, float precioUnitario, String descripcion, LocalDate remodelado) {
		super();
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.descripcion = descripcion;
		this.remodelado = remodelado; 
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
	
	
	
}
