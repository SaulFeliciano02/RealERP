package logico;

public class GrupoAtributo {
	
	public String nombre;
	private boolean borrado;

	public GrupoAtributo(String nombre) {
		super();
		this.nombre = nombre;
		this.setBorrado(false);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
}
