package logico;

import java.util.ArrayList;

public class Partida {
	
	public ArrayList<CantProductosUtilizados> listaMateriales;

	public Partida() {
		super();
		this.listaMateriales = new ArrayList<>();
	}

	public ArrayList<CantProductosUtilizados> getListaMateriales() {
		return listaMateriales;
	}

	public void agregarProductoUtilizado(CantProductosUtilizados p) {
		listaMateriales.add(p);
	}
	
}
