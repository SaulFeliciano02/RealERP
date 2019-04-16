package logico;

public class DescuentosAutomaticos {
	private String codigo;
	private String detalle;
	private Integer cantidadProducto;
	private Float porcentajeDescuento;
	private Integer nivel;
	
	public DescuentosAutomaticos(String codigo, String detalle, Integer cantidadProducto, Float porcentajeDescuento,
			Integer nivel) {
		super();
		this.codigo = codigo;
		this.detalle = detalle;
		this.cantidadProducto = cantidadProducto;
		this.porcentajeDescuento = porcentajeDescuento;
		this.nivel = nivel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Float porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	

}
