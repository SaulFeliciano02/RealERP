package logico;

import java.util.ArrayList;

public class Servicio extends Persona {
	
	private Empleado empleado;
	private ArrayList<CantProductosUtilizados> materialesUtilizados;

	public Servicio(Empleado empleado, String codigo, String nombre, String telefono) {
		super(codigo, nombre, telefono);
		this.empleado = empleado;
		this.materialesUtilizados = new ArrayList<>();
		// TODO Auto-generated constructor stub
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
