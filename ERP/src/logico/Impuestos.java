package logico;

public class Impuestos {
	private String codigo;
	private String nombreImpuesto;
	private Float porcentaje;
	private Float montoAplicable;
	
	public Impuestos(String codigo, String nombreImpuesto, Float porcentaje, Float montoAplicable) {
		super();
		this.codigo = codigo;
		this.nombreImpuesto = nombreImpuesto;
		this.porcentaje = porcentaje;
		this.montoAplicable = montoAplicable;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreImpuesto() {
		return nombreImpuesto;
	}

	public void setNombreImpuesto(String nombreImpuesto) {
		this.nombreImpuesto = nombreImpuesto;
	}

	public Float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Float getMontoAplicable() {
		return montoAplicable;
	}

	public void setMontoAplicable(Float montoAplicable) {
		this.montoAplicable = montoAplicable;
	}
	
	

}
