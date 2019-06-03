package logico;

import java.util.ArrayList;

public class Servicio extends Producto{
	
	private Empleado empleado;
	private ArrayList<CantProductosUtilizados> materialesUtilizados;

	public Servicio(String codigo, String nombre, String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, String observaciones,
			UnidadMedida unidadMedida, Precio precio, String comision, String codigoBarra, String descripcionFija,
			Empleado empleado, ArrayList<CantProductosUtilizados> materialesUtilizados) {
		super(codigo, nombre, descripcion, rubroProducto, tipoProducto, proveedorPrin, proveedoresSec, observaciones,
				unidadMedida, precio, comision, codigoBarra, descripcionFija);
		this.empleado = empleado;
		this.materialesUtilizados = materialesUtilizados;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public ArrayList<CantProductosUtilizados> getMaterialesUtilizados() {
		return materialesUtilizados;
	}
	
	public void agregarMaterialDeServicio(CantProductosUtilizados p) {
		materialesUtilizados.add(p);
	}
	
	

}
