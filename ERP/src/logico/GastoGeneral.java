package logico;

import java.util.Calendar;

public class GastoGeneral {
	
	private String nombre;
	private float precioUnitario;
	private Calendar remodelado;
	
	public GastoGeneral(String nombre, float precioUnitario, Calendar remodelado) {
		super();
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
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

	public Calendar getRemodelado() {
		return remodelado;
	}

	public void setRemodelado(Calendar remodelado) {
		this.remodelado = remodelado;
	}
	
	
	
}
