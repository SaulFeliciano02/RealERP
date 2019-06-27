package logico;

public class Rubro {
	private String codigo;
	private String nombreRubro;
	private boolean borrado;
	

	public Rubro(String codigo, String nombreRubro) {
		super();
		this.codigo = codigo;
		this.nombreRubro = nombreRubro;
		this.setBorrado(false);
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	

}
