package logico;

public class Atributos {
	
	public String nombre;
	public GrupoAtributo grupo;
	
	public Atributos(String nombre, GrupoAtributo grupo) {
		super();
		this.nombre = nombre;
		this.grupo = grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GrupoAtributo getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoAtributo grupo) {
		this.grupo = grupo;
	}
	
}
