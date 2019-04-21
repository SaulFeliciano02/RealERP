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
	
	private static Controladora controladora;
	
	public Controladora() {
		super();
		this.misClientes = new ArrayList<>();
		this.misEmpleados = new ArrayList<>();
		this.misProveedores = new ArrayList<>();
		this.misDescuentos = new ArrayList<>();
		this.misPromociones = new ArrayList<>();
		this.misRubros = new ArrayList<>();
		this.misProductos = new ArrayList<>();	
	}
	
	public static Controladora getInstance() {
		if (controladora == null) {
			controladora = new Controladora();
		}
		return controladora;
	}
	
	public void addCliente(Cliente c) {
		misClientes.add(c);
	}
	public void addEmpleado(Empleado e) {
		misEmpleados.add(e);
	}
	public void addProveedor(Proveedores p) {
		misProveedores.add(p);
	}
	public void addDescuento(DescuentosAutomaticos d) {
		misDescuentos.add(d);
	}
	public void addPromocion(Promocion p) {
		misPromociones.add(p);
	}
	public void addRubro(Rubro r) {
		misRubros.add(r);
	}
	public void addProducto(Producto p) {
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
	
	/**FUNCION PARA BUSCAR UN PRODUCTO**/
	
	public Producto searchProducts(String buscador, String tipoBusqueda) {
		Producto searchProducto = null;
		for(int i = 0; i < misProductos.size(); i++) {
			int boolCount = 0;
			/**Con motivo de no repetir la misma funcion varias veces, se penso hacerlo un switch**/
			for(int j = 0; j < buscador.length(); j++) {
				switch(tipoBusqueda) {
				case "Codigo":
					if(misProductos.get(i).getCodigo().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Nombre":
					if(misProductos.get(i).getNombre().toLowerCase().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Descripcion":
					if(misProductos.get(i).getDescripcion().toLowerCase().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Proveedor":
					if(misProductos.get(i).getProveedorPrin().getCodigo().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Rubro":
					if(misProductos.get(i).getRubroProducto().getNombreRubro().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				}
				
			}
			if(boolCount == buscador.length()) {
				searchProducto = misProductos.get(i);
			}
		}
		return searchProducto;
	}
	

/**FUNCION PARA BUSCAR PROVEEDOR**/
	
	public Proveedores searchProveedores(String buscador, String tipoBusqueda){
		Proveedores searchProveedor = null;
		for(int i = 0; i < misProveedores.size(); i++) {
			int boolCount = 0;
			for(int j = 0; j < tipoBusqueda.length(); j++) {
				switch(tipoBusqueda) {
				case "Codigo":
					if(misProveedores.get(i).getCodigo().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				
				case "Nombre":
					if(misProveedores.get(i).getNombre().toLowerCase().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Rubro":
					if(misProveedores.get(i).getRubro().getNombreRubro().toLowerCase().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				}
			}
			if(boolCount == buscador.length()) {
				searchProveedor = misProveedores.get(i);
			}
		}
		return searchProveedor;
	}
	
/**FUNCION PARA BUSCAR CLIENTES**/
	
	public Cliente searchClientes(String buscador, String tipoBusqueda){
		Cliente searchCliente = null;
		for(int i = 0; i < misClientes.size(); i++) {
			int boolCount = 0;
			for(int j = 0; j < buscador.length(); j++) {
				switch(tipoBusqueda) {
				case "Codigo":
					if(misClientes.get(i).getCodigo().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Nombre":
					if(misClientes.get(i).getNombre().toLowerCase().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				}
			}
			if(boolCount == buscador.length()) {
				searchCliente = misClientes.get(i);
			}
		}
		return searchCliente;
	}
	
/**FUNCION PARA BUSCAR EMPLEADOS**/
	
	public Empleado searchEmpleados(String buscador, String tipoBusqueda){
		Empleado searchEmpleado = null;
		for(int i = 0; i < misEmpleados.size(); i++) {
			int boolCount = 0;
			for(int j = 0; j < buscador.length(); j++) {
				switch(tipoBusqueda) {
				case "Codigo":
					if(misEmpleados.get(i).getCodigo().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				case "Nombre":
					if(misEmpleados.get(i).getNombre().toLowerCase().charAt(j) == buscador.charAt(j)) {
						boolCount++;
					}
					break;
				}
			}
			if(boolCount == buscador.length()) {
				searchEmpleado = misEmpleados.get(i);
			}
		}
		return searchEmpleado;
	}
}
