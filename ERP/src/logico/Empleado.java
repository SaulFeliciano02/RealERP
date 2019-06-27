package logico;

public class Empleado extends Persona {
	
	public String domicilio;
	public String correo;
	public String rnc;
	public float sueldo;
	public CategoriaEmpleado categoria;
	private boolean borrado;
	
	public Empleado(String codigo, String nombre, String telefono, String domicilio, String correo, String rnc, float sueldo, CategoriaEmpleado categoria) {
		super(codigo, nombre, telefono);
		this.domicilio = domicilio;
		this.correo = correo;
		this.sueldo = sueldo;
		this.rnc = rnc;
		this.categoria = categoria;
		this.setBorrado(false);
	}

	public CategoriaEmpleado getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEmpleado categoria) {
		this.categoria = categoria;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
}
