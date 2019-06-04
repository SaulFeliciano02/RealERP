package logico;

public abstract class UnidadMedida {
	private String nombre;
	private String abreviatura;
	
	public UnidadMedida(String nombre, String abreviatura) {
		super();
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
	public float Conversion(String comparador, float valorTransformar)
	{
		float valorConvertido = 0;
		return valorConvertido;
	}
}
