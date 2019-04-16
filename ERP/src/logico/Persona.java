package logico;

public class Persona {
	
	public String codigo;
	public String nombre;
	public String telefono;
	
	public Persona(String codigo, String nombre, String telefono) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
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
	
}
