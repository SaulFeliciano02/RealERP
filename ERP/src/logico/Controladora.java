package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Controladora implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Cliente cliente1 = new Cliente("000001", "Marcos", "312312", "Activo", null, "2312313");
	Cliente cliente2 = new Cliente("000002", "Saul", "312312", "Activo", null, "2312313");
	Cliente cliente3 = new Cliente("000003", "Yorman", "312312", "Activo", null, "2312313");
	Cliente cliente4 = new Cliente("000004", "Sarah", "312312", "Activo", null, "2312313");
	Cliente cliente5 = new Cliente("000005", "Michael", "312312", "Activo", null, "2312313");
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
		misClientes.add(cliente1);
		misClientes.add(cliente2);
		misClientes.add(cliente3);
		misClientes.add(cliente4);
		misClientes.add(cliente5);
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
	
	public ArrayList<Producto> searchProducts(String buscador, String tipoBusqueda) {
		int j = 0;
		ArrayList<Producto> searchProducto = new ArrayList<>();
		for(int i = 0; i < misProductos.size(); i++) {
			int boolCount = 0;
			j = 0;
			/**Con motivo de no repetir la misma funcion varias veces, se penso hacerlo un switch**/
			switch(tipoBusqueda) {
				case "Codigo":
					for(int k = 0; k < misProductos.get(i).getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Nombre":
					for(int k = 0; k < misProductos.get(i).getNombre().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
				case "Descripcion":
					for(int k = 0; k < misProductos.get(i).getDescripcion().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getDescripcion().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Proveedor":
					for(int k = 0; k < misProductos.get(i).getProveedorPrin().getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getProveedorPrin().getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Rubro":
					for(int k = 0; k < misProductos.get(i).getRubroProducto().getNombreRubro().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getRubroProducto().getNombreRubro().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
			}
			if(boolCount == buscador.length()) {
				searchProducto.add(misProductos.get(i));
			}
		}
		return searchProducto;
	}
	

/**FUNCION PARA BUSCAR PROVEEDOR**/
	
	public ArrayList<Proveedores> searchProveedores(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Proveedores> searchProveedor = new ArrayList<>();
		for(int i = 0; i < misProveedores.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
			case "Codigo":
				for(int k = 0; k < misProveedores.get(i).getCodigo().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getCodigo().charAt(j) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misProveedores.get(i).getNombre().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getNombre().toLowerCase().charAt(j) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Rubro":
				for(int k = 0; k < misProveedores.get(i).getRubro().getNombreRubro().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getRubro().getNombreRubro().toLowerCase().charAt(j) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			}
			if(boolCount == buscador.length()) {
				searchProveedor.add(misProveedores.get(i));
			}
		}
		return searchProveedor;
	}
	
/**FUNCION PARA BUSCAR CLIENTES**/
	
	public ArrayList<Cliente> searchClientes(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Cliente> searchCliente = new ArrayList<>();
		for(int i = 0; i < misClientes.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
				case "Codigo":
					for(int k = 0; k < misClientes.get(i).getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misClientes.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}				
					break;
				case "Nombre":
					for(int k = 0; k < misClientes.get(i).getNombre().length(); k++) {
						if(j < buscador.length()) {
							if(misClientes.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				}
			if(boolCount == buscador.length()) {
				searchCliente.add(misClientes.get(i));
			}
		}
		return searchCliente;
	}
	
/**FUNCION PARA BUSCAR EMPLEADOS**/
	
	public ArrayList<Empleado> searchEmpleados(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Empleado> searchEmpleado = new ArrayList<>();
		for(int i = 0; i < misEmpleados.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
			case "Codigo":
				for(int k = 0; k < misEmpleados.get(i).getCodigo().length(); k++) {
					if(j < buscador.length()) {
					if(misEmpleados.get(i).getCodigo().charAt(j) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misEmpleados.get(i).getNombre().length(); k++) {
					if(j < buscador.length()) {
						if(misEmpleados.get(i).getNombre().toLowerCase().charAt(j) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			}		
			if(boolCount == buscador.length()) {
				searchEmpleado.add(misEmpleados.get(i));
			}
		}
		return searchEmpleado;
	}
	/**FUNCION PARA VERIFICAR SI UN STRING DADO ES UN NUMERO**/
	
	public boolean isNumber(String string) {
		boolean numeric = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
       return numeric;
	}
	
	public double calcularPrecio(double costo, double porcientoGanancia, double impuesto) {
		double precio = (costo * (porcientoGanancia/100)) + (costo * (impuesto/100));
		double precioTotal = costo+precio;		
		return precioTotal;
	}

}




