package logico;

public abstract class UnidadMedida {
	private String categoria;
	private String nombre;
	private String abreviatura;
	
	public UnidadMedida(String categoria, String nombre, String abreviatura) {
		super();
		this.categoria = categoria;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float Conversion(String comparador, float valorTransformar)
	{
		float valorConvertido = 0;
		return valorConvertido;
	}
}
