package logico;

public class CostoIndirecto {
	
	private String Nombre;
	private boolean borrado;
	
	

	public CostoIndirecto(String nombre) {
		super();
		this.Nombre = nombre;
		this.setBorrado(false);
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	

}
