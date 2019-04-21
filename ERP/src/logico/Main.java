package logico;

import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		Date date = null;
		Rubro armas = new Rubro("Armas");
		Proveedores prov1 = new Proveedores("Prov-01", "Juan", "777-777-7777", "Wareve", null, "011", null, null);
		Proveedores prov2 = new Proveedores("Prov-02", "Pedro", "777-777-7777", "Wareve", null, "012", null, null);
		Proveedores prov3 = new Proveedores("Prov-03", "Edualdo", "777-777-7777", "Wareve", null, "013", null, null);
		ArrayList<Proveedores> provsec = new ArrayList<>();
		Moneda dola = new Moneda(50, "dolar");
		Precio pre = new Precio(1500f, "caro", true);
		UnidadMedida lb = new UnidadMedida("Libra", "Lb");
		provsec.add(prov2);
		provsec.add(prov3);
		
		Producto pro = new Estandar(5f, 20f, date, 150f, false, "01", "pitola", "Esa vaina mata", armas, "no se", prov1, provsec, dola, "Eso no silve", lb, pre, "0f", "pis-0101", 0f, "Mata", "Puesde que no mate");
		Controladora.getInstance().addProducto(pro);
		
		System.out.println(Controladora.getInstance().searchProducts("pitola", "Nombre").getNombre());
	}

}
