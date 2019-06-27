package logico;

import java.util.ArrayList;

public class Partida {
	
	public ArrayList<CantProductosUtilizados> listaMateriales;
	private boolean borrado;

	public Partida() {
		super();
		this.listaMateriales = new ArrayList<>();
		this.setBorrado(false);
	}

	public ArrayList<CantProductosUtilizados> getListaMateriales() {
		return listaMateriales;
	}

	public void agregarProductoUtilizado(CantProductosUtilizados p) {
		listaMateriales.add(p);
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
}
