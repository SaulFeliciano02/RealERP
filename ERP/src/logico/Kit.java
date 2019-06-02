package logico;

import java.util.ArrayList;
import java.util.Date;

public class Kit extends Producto{
	
	
	private ArrayList<Producto> productosContenidos; //Preguntarle a Alonso despues
	private int existencia;
	private int existencia_minima;
	private int existencia_maxima;
	private Date fechaVencimiento;
	
	public Kit(ArrayList<Producto> productosContenidos, int existencia, int existencia_minima, int existencia_maxima, Date fechaVencimiento, String codigo, String nombre, String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, Moneda moneda, String observaciones,
			UnidadMedida unidadMedida, Precio precio, String comision, String codigoBarra, float costoManoDeObra,
			String descripcionFija, String descripcionVariable) {
		super(codigo, nombre, descripcion, rubroProducto, tipoProducto, proveedorPrin, proveedoresSec, observaciones,
				unidadMedida, precio, comision, codigoBarra, descripcionFija);
		this.existencia = existencia;
		this.existencia_minima = existencia_minima;
		this.existencia_maxima = existencia_maxima;
		this.fechaVencimiento = fechaVencimiento;
		this.productosContenidos = productosContenidos;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public int getExistencia_minima() {
		return existencia_minima;
	}

	public void setExistencia_minima(int existencia_minima) {
		this.existencia_minima = existencia_minima;
	}

	public int getExistencia_maxima() {
		return existencia_maxima;
	}

	public void setExistencia_maxima(int existencia_maxima) {
		this.existencia_maxima = existencia_maxima;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public ArrayList<Producto> getProductosContenidos() {
		return productosContenidos;
	}
	
	
	
	
	

}
