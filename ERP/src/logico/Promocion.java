package logico;
import java.util.*;

public class Promocion {
	private Float precioPromocion;
	private Date fechaInicio;
	private Date fechaFinal;
	
	
	public Promocion(Float precioPromocion, Date fechaInicio, Date fechaFinal) {
		super();
		this.precioPromocion = precioPromocion;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
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

}
