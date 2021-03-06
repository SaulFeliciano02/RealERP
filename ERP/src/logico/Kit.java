package logico;

import java.util.ArrayList;
import java.util.Date;

public class Kit extends Producto{
	
	
	private ArrayList<CantProductosUtilizados> productosContenidos; //Preguntarle a Alonso despues
	private float existenciaActual;
	private float existenciaMinima;
	private float existenciaMaxima;
	private float existenciaInicial;
	private Date fechaVencimiento;
	
	public Kit(ArrayList<CantProductosUtilizados> productosContenidos, float existenciaActual, float existenciaMinima, float existenciaMaxima, float existenciaInicial, Date fechaVencimiento, String codigo, String nombre, String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, Moneda moneda, String observaciones,
			UnidadMedida unidadMedida, Precio precio, String comision, String codigoBarra, float costoManoDeObra,
			String descripcionFija, String descripcionVariable, float costo, float costoitbis) {
		super(codigo, nombre, descripcion, rubroProducto, tipoProducto, proveedorPrin, proveedoresSec, observaciones,
				unidadMedida, precio, comision, codigoBarra, descripcionFija, costo, costoitbis);
		this.existenciaActual = existenciaActual;
		this.existenciaMinima = existenciaMinima;
		this.existenciaMaxima = existenciaMaxima;
		this.existenciaInicial = existenciaInicial;
		this.fechaVencimiento = fechaVencimiento;
		this.productosContenidos = productosContenidos;
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
	public ArrayList<CantProductosUtilizados> getProductosContenidos() {
		return productosContenidos;
	}
	public float getExistenciaInicial() {
		return existenciaInicial;
	}
	public void setExistenciaInicial(float existenciaInicial) {
		this.existenciaInicial = existenciaInicial;
	}
}
