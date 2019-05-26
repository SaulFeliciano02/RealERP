package logico;

import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
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
		Controladora.getInstance().getMisClientes().add(cliente1);
		Controladora.getInstance().getMisClientes().add(cliente2);
		Controladora.getInstance().getMisClientes().add(cliente3);
		Controladora.getInstance().getMisClientes().add(cliente4);
		Controladora.getInstance().getMisClientes().add(cliente5);
		Controladora.getInstance().getMisProveedores().add(proveedor1);
		Controladora.getInstance().getMisProveedores().add(proveedor2);
		Controladora.getInstance().getMisProveedores().add(proveedor3);
		Controladora.getInstance().getMisProveedores().add(proveedor4);
		Controladora.getInstance().getMisProveedores().add(proveedor5);
		Controladora.getInstance().getMisProveedores().add(prov1);
		Controladora.getInstance().getMisProveedores().add(prov2);
		Controladora.getInstance().getMisProveedores().add(prov3);
		ArrayList<Proveedores> provsec = new ArrayList<>();
		Moneda dola = new Moneda(50, "dolar");
		Precio pre = new Precio(1500f, "caro", true);
		UnidadMedida lb = new UnidadMedida("Libra", "Lb");
		provsec.add(prov2);
		provsec.add(prov3);
		
		Estandar pro = new Estandar(10f, 5f, 20f, date, 150f, false, "01", "pitola", "Esa vaina mata", armas, "no se", prov1, provsec, dola, "Eso no silve", lb, pre, "0f", "pis-0101", 0f, "Mata", "Puesde que no mate");
		Controladora.getInstance().addProducto(pro);
		Controladora.getInstance().addProductoEstandar(pro);
		
		//System.out.println(Controladora.getInstance().searchProducts("pitola", "Nombre").getNombre());
	}

}
