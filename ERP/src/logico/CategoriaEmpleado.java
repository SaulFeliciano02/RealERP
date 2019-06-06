package logico;

public class CategoriaEmpleado {
	
	String nombre;
	float sueldo;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public CategoriaEmpleado(String nombre, float sueldo) {
		super();
		this.nombre = nombre;
		this.sueldo = sueldo;
	}
	
	
}
