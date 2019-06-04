package logico;

public class Empleado extends Persona {
	
	public String domicilio;
	public String correo;
	public String rnc;
	public String tipo;
	public float sueldo;
	
	public Empleado(String codigo, String nombre, String telefono, String domicilio, String correo, String rnc, String tipo, float sueldo) {
		super(codigo, nombre, telefono);
		this.domicilio = domicilio;
		this.correo = correo;
		this.sueldo = sueldo;
		this.rnc = rnc;
		this.tipo = tipo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
