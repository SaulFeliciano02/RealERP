package logico;

import java.util.ArrayList;
import java.util.Date;

public class Estandar extends Producto{

	public float existenciaActual;
	public float existenciaMinima;
	public float existenciaMaxima;
	public Date fechaVencimiento;
	public float costoDeCompra;
	public ArrayList<Combinaciones> combinaciones;
	public boolean fabricado;
	public Partida partida;
	
	public Estandar(float existenciaActual, float existenciaMinima, float existenciaMaxima, Date fechaVencimiento, float costoDeCompra, boolean fabricado, 
			Partida partida, String codigo, String nombre, 
			String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, Moneda moneda, String observaciones,
			UnidadMedida unidadMedida, Precio precio, String comision, String codigoBarra, float costoManoDeObra,
			String descripcionFija, String descripcionVariable, float costo) {
		super(codigo, nombre, descripcion, rubroProducto, tipoProducto, proveedorPrin, proveedoresSec, observaciones,
				unidadMedida, precio, comision, codigoBarra, descripcionFija, costo);
		this.existenciaActual = existenciaActual;
		this.existenciaMinima = existenciaMinima;
		this.existenciaMaxima = existenciaMaxima;
		this.fechaVencimiento = fechaVencimiento;
		this.costoDeCompra = costoDeCompra;
		this.combinaciones = new ArrayList<>();
		this.fabricado = fabricado;
		this.partida = partida;
		// TODO Auto-generated constructor stub
	}
	

	
	public float getExistenciaActual() {
		return existenciaActual;
	}


	public void setExistenciaActual(float existenciaActual) {
		this.existenciaActual = existenciaActual;
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

	public void setCombinaciones(ArrayList<Combinaciones> combinaciones) {
		this.combinaciones = combinaciones;
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



	public Partida getPartida() {
		return partida;
	}
	
	
}
