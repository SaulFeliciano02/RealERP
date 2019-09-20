package logico;

import java.time.LocalDate;

public class TransaccionesCuentaBanco {
	private float actualizacion;
	private String descripcion;
	private LocalDate fecha;
	public TransaccionesCuentaBanco(float actualizacion, String descripcion, LocalDate fecha) {
		super();
		this.actualizacion = actualizacion;
		this.descripcion = descripcion;;
		this.fecha = fecha;
	}
	public float getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(float actualizacion) {
		this.actualizacion = actualizacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	

}
