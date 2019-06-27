package logico;
import java.util.*;

public class Promocion {
	private Float precioPromocion;
	private Date fechaInicio; // Cambiarlo a Calendar
	private Date fechaFinal; // Cambiarlo a Calendar
	private boolean borrado;
	
	
	public Promocion(Float precioPromocion, Date fechaInicio, Date fechaFinal) {
		super();
		this.precioPromocion = precioPromocion;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.setBorrado(false);
	}
	
	public Float getPrecioPromocion() {
		return precioPromocion;
	}
	public void setPrecioPromocion(Float precioPromocion) {
		this.precioPromocion = precioPromocion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

}
