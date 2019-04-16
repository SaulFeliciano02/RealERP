package logico;

public class Empleado extends Persona {
	
	public String domicilio;
	public String correo;
	public float sueldo;
	
	public Empleado(String codigo, String nombre, String telefono, String domicilio, String correo, float sueldo) {
		super(codigo, nombre, telefono);
		this.domicilio = domicilio;
		this.correo = correo;
		this.sueldo = sueldo;
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
	
}
