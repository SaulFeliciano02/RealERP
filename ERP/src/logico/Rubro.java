package logico;

public class Rubro {
	private String codigo;
	private String nombreRubro;
	

	public Rubro(String codigo, String nombreRubro) {
		super();
		this.codigo = codigo;
		this.nombreRubro = nombreRubro;
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
	
	

}
