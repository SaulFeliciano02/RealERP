package logico;

public class Atributos {
	
	public String nombre;
	public GrupoAtributo grupo;
	private boolean borrado;
	
	public Atributos(String nombre, GrupoAtributo grupo) {
		super();
		this.nombre = nombre;
		this.grupo = grupo;
		this.setBorrado(false);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGrupo() {
		return grupo.getNombre();
	}
	
	public GrupoAtributo getGrupoAtributo() {
		return grupo;
	}

	public void setGrupo(GrupoAtributo grupo) {
		this.grupo = grupo;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
}
