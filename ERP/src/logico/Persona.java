package logico;

abstract public class Persona {
	
	public String codigo;
	public String nombre;
	public String telefono;
	private boolean borrado;
	
	public Persona(String codigo, String nombre, String telefono) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.setBorrado(false);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
}
