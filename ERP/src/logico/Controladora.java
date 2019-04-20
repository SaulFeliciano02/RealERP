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
	
	/**FUNCION PARA BUSCAR UN PRODUCTO**/
	
	public ArrayList<Producto> searchProducts(String buscador, String tipoBusqueda) {
		ArrayList<Producto> searchProducto = new ArrayList<>();
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
				searchProducto.add(misProductos.get(i));
			}
		}
		return searchProducto;
	}
	

/**FUNCION PARA BUSCAR PROVEEDOR**/
	
	public ArrayList<Proveedores> searchProveedores(String buscador, String tipoBusqueda){
		ArrayList<Proveedores> searchProveedor = new ArrayList<>();
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
				searchProveedor.add(misProveedores.get(i));
			}
		}
		return searchProveedor;
	}
	
/**FUNCION PARA BUSCAR CLIENTES**/
	
	public ArrayList<Cliente> searchClientes(String buscador, String tipoBusqueda){
		ArrayList<Cliente> searchCliente = new ArrayList<>();
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
				searchCliente.add(misClientes.get(i));
			}
		}
		return searchCliente;
	}
	
/**FUNCION PARA BUSCAR EMPLEADOS**/
	
	public ArrayList<Empleado> searchEmpleados(String buscador, String tipoBusqueda){
		ArrayList<Empleado> searchEmpleado = new ArrayList<>();
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
				searchEmpleado.add(misEmpleados.get(i));
			}
		}
		return searchEmpleado;
	}
}
