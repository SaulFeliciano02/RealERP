package logico;

public class DescuentosAutomaticos {
	private String codigo;
	private String detalle;
	private Integer cantidadProducto;
	private Float porcentajeDescuento;
	private boolean activo;
	
	public DescuentosAutomaticos(String codigo, String detalle, Integer cantidadProducto, Float porcentajeDescuento,
			boolean activo) {
		super();
		this.codigo = codigo;
		this.detalle = detalle;
		this.cantidadProducto = cantidadProducto;
		this.porcentajeDescuento = porcentajeDescuento;
		this.activo = activo;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	
	

}
