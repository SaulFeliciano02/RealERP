package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Controladora implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Date date = null;
	Rubro armas = new Rubro("00001", "Armas");
	Cliente cliente1 = new Cliente("000001", "Marcos", "312312", "Activo", null, "2312313");
	Cliente cliente2 = new Cliente("000002", "Saul", "312312", "Activo", null, "2312313");
	Cliente cliente3 = new Cliente("000003", "Yorman", "312312", "Activo", null, "2312313");
	Cliente cliente4 = new Cliente("000004", "Sarah", "312312", "Activo", null, "2312313");
	Cliente cliente5 = new Cliente("000005", "Michael", "312312", "Activo", null, "2312313");
	Proveedores proveedor1 = new Proveedores("000001", "Jose", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor2 = new Proveedores("000002", "Marcos", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor3 = new Proveedores("000003", "Maria", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor4 = new Proveedores("000004", "Juan", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor5 = new Proveedores("000005", "Luis", "21312313", "Su casa", "", "", null, "");
	Proveedores prov1 = new Proveedores("Prov-01", "Juan", "777-777-7777", "Wareve", null, "011", null, null);
	Proveedores prov2 = new Proveedores("Prov-02", "Pedro", "777-777-7777", "Wareve", null, "012", null, null);
	Proveedores prov3 = new Proveedores("Prov-03", "Edualdo", "777-777-7777", "Wareve", null, "013", null, null);
	ArrayList<Proveedores> provsec = new ArrayList<>();
	Moneda dola = new Moneda(50, "dolar");
	Precio pre = new Precio(1500f, "caro", true);
	UnidadMedida lb = new UnidadMedida("Libra", "Lb");
	Estandar pro = new Estandar(10f, 5f, 20f, date, 150f, false, "01", "pitola", "Esa vaina mata", armas, "no se", prov1, provsec, dola, "Eso no silve", lb, pre, "0f", "pis-0101", 0f, "Mata", "Puesde que no mate");
	
	private ArrayList<Cliente> misClientes;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Proveedores> misProveedores;
	
	private ArrayList<DescuentosAutomaticos> misDescuentos;
	private ArrayList<Promocion> misPromociones;
	
	private ArrayList<Rubro> misRubros;
	
	private ArrayList<Producto> misProductos;
	private ArrayList<Estandar> misProductosEstandar;
	private ArrayList<Kit> misProductosKit;
	private ArrayList<Servicio> misProductosServicio;
	private ArrayList<GrupoAtributo> misGrupoAtributo;
	private ArrayList<Atributos> misAtributos;
	private ArrayList<GastoGeneral> misGastosGenerales;
	
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
		this.misProductosEstandar = new ArrayList<>();
		this.misProductosKit = new ArrayList<>();
		this.misProductosServicio = new ArrayList<>();
		misClientes.add(cliente1);
		misClientes.add(cliente2);
		misClientes.add(cliente3);
		misClientes.add(cliente4);
		misClientes.add(cliente5);
		misProveedores.add(proveedor1);
		misProveedores.add(proveedor2);
		misProveedores.add(proveedor3);
		misProveedores.add(proveedor4);
		misProveedores.add(proveedor5);
		misProductos.add(pro);
		misProductosEstandar.add(pro);
		this.misAtributos = new ArrayList<>();
		this.misGrupoAtributo = new ArrayList<>();
		this.misGastosGenerales = new ArrayList<>();
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
	public void addProductoEstandar(Estandar e) {
		misProductosEstandar.add(e);
	}
	public void addProductoKit(Kit k) {
		misProductosKit.add(k);
	}
	public void addProductoServicio(Servicio s) {
		misProductosServicio.add(s);
	}
	
	public void addGrupoAtributo(GrupoAtributo g) {
		misGrupoAtributo.add(g);
	}
	public void addAtributo(Atributos a) {
		misAtributos.add(a);
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
	
	public ArrayList<GrupoAtributo> getMisGrupoAtributo() {
		return misGrupoAtributo;
	}
	
	public ArrayList<Atributos> getMisAtributos() {
		return misAtributos;
	}
	
	public ArrayList<Estandar> getMisProductosEstandar() {
		return misProductosEstandar;
	}

	public ArrayList<Kit> getMisProductosKit() {
		return misProductosKit;
	}

	public ArrayList<Servicio> getMisProductosServicio() {
		return misProductosServicio;
	}

	/**FUNCION PARA VERIFICAR UNA FAMILIA DE ATRIBUTO**/
	
	public boolean verificarFamiliaAtributo(String nombre) {
		boolean existe = false;
		if(!getMisGrupoAtributo().isEmpty()) {
			int i;
			for(i=0;i<getMisGrupoAtributo().size();i++)
			{
				if(nombre.equalsIgnoreCase(getMisGrupoAtributo().get(i).getNombre()) && existe == false)
				{
					existe = true;
				}
			}
		}
		
		return existe;
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
	
	public ArrayList<Estandar> searchProductsEstandar(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Estandar> searchProductoEstandar = new ArrayList<>();
		for(int i = 0; i < misProductosEstandar.size(); i++) {
			int boolCount = 0;
			j = 0;
			/**Con motivo de no repetir la misma funcion varias veces, se penso hacerlo un switch**/
			switch(tipoBusqueda) {
				case "Codigo":
					for(int k = 0; k < misProductosEstandar.get(i).getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Nombre":
					for(int k = 0; k < misProductosEstandar.get(i).getNombre().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
				case "Descripcion":
					for(int k = 0; k < misProductosEstandar.get(i).getDescripcion().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getDescripcion().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Proveedor":
					for(int k = 0; k < misProductosEstandar.get(i).getProveedorPrin().getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getProveedorPrin().getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Rubro":
					for(int k = 0; k < misProductosEstandar.get(i).getRubroProducto().getNombreRubro().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getRubroProducto().getNombreRubro().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
			}
			if(boolCount == buscador.length()) {
				searchProductoEstandar.add(misProductosEstandar.get(i));
			}
		}
		return searchProductoEstandar;
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
						if(misProveedores.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misProveedores.get(i).getNombre().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Rubro":
				for(int k = 0; k < misProveedores.get(i).getRubro().getNombreRubro().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getRubro().getNombreRubro().toLowerCase().charAt(k) == buscador.charAt(j)) {
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
					if(misEmpleados.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misEmpleados.get(i).getNombre().length(); k++) {
					if(j < buscador.length()) {
						if(misEmpleados.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
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
	
	/**FUNCION PARA BUSCAR RUBROS**/
	public ArrayList<Rubro> searchRubro(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Rubro> searchRubro = new ArrayList<>();
		for(int i = 0; i < misRubros.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
			case "Codigo":
				for(int k = 0; k < misRubros.get(i).getCodigo().length(); k++) {
					if(j < buscador.length()) {
					if(misRubros.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misRubros.get(i).getNombreRubro().length(); k++) {
					if(j < buscador.length()) {
						if(misRubros.get(i).getNombreRubro().toLowerCase().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			}		
			if(boolCount == buscador.length()) {
				searchRubro.add(misRubros.get(i));
			}
		}
		return searchRubro;
	}
	
	
	
	/**FUNCION PARA VERIFICAR SI UN STRING DADO ES UN NUMERO**/
	
	public boolean isNumber(String string) {
		boolean numeric = true;
		int count = 0;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
        	numeric = false;   
        }
       return numeric;
	}
	
	public boolean isFloat(String fullString, String character) {
		boolean numeric = true;
		int count = 0;
        try {
            Double.parseDouble(character);
        } catch (NumberFormatException e) {
        	numeric = false;
        	if(character.charAt(0) == '.') {
        		for(int i = 0; i < fullString.length(); i++) {
        			if(fullString.charAt(i) == '.') {
        				count++;
        			}	
        		}
        	}
        	if(count == 0) {
        		numeric = true;
        	}
        	    
        }
       return numeric;
	}
	
	public double calcularPrecio(double costo, double porcientoGanancia, double impuesto) {
		double precio = (costo * (porcientoGanancia/100)) + (costo * (impuesto/100));
		double precioTotal = costo+precio;		
		return precioTotal;
	}
	
	
	public String findPartidaNombre(String string) {
		String nombre = "";
		int i = 0;
		while(string.charAt(i) != ':') {
    		nombre += string.charAt(i);
    		i++;
    	} 
		return nombre;
	}
	
	public String findPartidaCantidad(String string) {
		int i = 0;
		int check = 0;
		String cantidad = "";
		while(i < string.length()) {
    		if(string.charAt(i) == ':') {
    			check++;
    		}
    		if(check == 2 && string.charAt(i) != ' ' && string.charAt(i) != ':') {
    			cantidad += string.charAt(i);
    		}
    		i++;
    	}
		return cantidad;
	}
	
	public String findPartidaCosto(String string) {
		String partida = "";
		int i = 0;
		int check = 0;
		while(i < string.length()) {
			 if(Character.isDigit(string.charAt(i)) && check == 0) {
				 check++;
				 partida += string.charAt(i);
			 }
			 else if((Character.isDigit(string.charAt(i)) || string.charAt(i) == '.') && check == 1) {
				 partida += string.charAt(i);
			 }
			 else if(Character.isLetter(string.charAt(i)) && check == 1) {
				 check++;
			 }
			 i++;
		}
		return partida;
	}

	public ArrayList<GastoGeneral> getMisGastosGenerales() {
		return misGastosGenerales;
	}

	public void setMisGastosGenerales(ArrayList<GastoGeneral> misGastosGenerales) {
		this.misGastosGenerales = misGastosGenerales;
	}
	
	public ArrayList<GastoGeneral> searchGastoGeneral (String buscador)
	{
		int j = 0;
		ArrayList<GastoGeneral> searchGastoGeneral = new ArrayList<>();
		for(int i = 0; i < misGastosGenerales.size(); i++) {
			int boolCount = 0;
			j = 0;
			
			for(int k = 0; k < misGastosGenerales.get(i).getNombre().length(); k++) {
				if(j < buscador.length()) {
					if(misGastosGenerales.get(i).getNombre().charAt(k) == buscador.charAt(j)) {
						boolCount++;
					}
				}
				j++;
			}
			if(boolCount == buscador.length()) {
				searchGastoGeneral.add(misGastosGenerales.get(i));
			}
		}
		return searchGastoGeneral;
	}
}




