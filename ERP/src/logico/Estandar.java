package logico;

import java.util.ArrayList;
import java.util.Date;

public class Estandar {
	
	public float existenciaMinima;
	public float existenciaMaxima;
	public Date fechaVencimiento;
	public float costoDeCompra;
	public ArrayList<Combinaciones> combinaciones;
	public boolean fabricado;
	
	public Estandar(float existenciaMinima, float existenciaMaxima, Date fechaVencimiento, float costoDeCompra,
			ArrayList<Combinaciones> combinaciones, boolean fabricado) {
		super();
		this.existenciaMinima = existenciaMinima;
		this.existenciaMaxima = existenciaMaxima;
		this.fechaVencimiento = fechaVencimiento;
		this.costoDeCompra = costoDeCompra;
		this.combinaciones = combinaciones;
		this.fabricado = fabricado;
	}

	public float getExistenciaMinima() {
		return existenciaMinima;
	}

	public void setExistenciaMinima(float existenciaMinima) {
		this.existenciaMinima = existenciaMinima;
	}

	public float getExistenciaMaxima() {
		return existenciaMaxima;
	}

	public void setExistenciaMaxima(float existenciaMaxima) {
		this.existenciaMaxima = existenciaMaxima;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public float getCostoDeCompra() {
		return costoDeCompra;
	}

	public void setCostoDeCompra(float costoDeCompra) {
		this.costoDeCompra = costoDeCompra;
	}

	public ArrayList<Combinaciones> getCombinaciones() {
		return combinaciones;
	}

	public boolean isFabricado() {
		return fabricado;
	}

	public void setFabricado(boolean fabricado) {
		this.fabricado = fabricado;
	}
	
	public void agregarCombinacion(Combinaciones c) {
		this.combinaciones.add(c);
	}
	
}
