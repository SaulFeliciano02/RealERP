package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Controladora implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Cliente> misClientes;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Proveedores> misProveedores;
	
	private ArrayList<DescuentosAutomaticos> misDescuentos;
	private ArrayList<Promocion> misPromociones;
	
	private ArrayList<Rubro> misRubros;
	
	private ArrayList<Producto> misProductos;
	
	public Controladora() {
		this.misClientes = new ArrayList<>();
		this.misEmpleados = new ArrayList<>();
		this.misProveedores = new ArrayList<>();
		this.misDescuentos = new ArrayList<>();
		this.misPromociones = new ArrayList<>();
		this.misRubros = new ArrayList<>();
		this.misProductos = new ArrayList<>();	
	}
	
	private void addCliente(Cliente c) {
		misClientes.add(c);
	}
	private void addEmpleado(Empleado e) {
		misEmpleados.add(e);
	}
	private void addProveedor(Proveedores p) {
		misProveedores.add(p);
	}
	private void addDescuento(DescuentosAutomaticos d) {
		misDescuentos.add(d);
	}
	private void addPromocion(Promocion p) {
		misPromociones.add(p);
	}
	private void addRubro(Rubro r) {
		misRubros.add(r);
	}
	private void addProducto(Producto p) {
		misProductos.add(p);
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}

	public ArrayList<Proveedores> getMisProveedores() {
		return misProveedores;
	}

	public ArrayList<DescuentosAutomaticos> getMisDescuentos() {
		return misDescuentos;
	}

	public ArrayList<Promocion> getMisPromociones() {
		return misPromociones;
	}

	public ArrayList<Rubro> getMisRubros() {
		return misRubros;
	}

	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}
	
	

}
