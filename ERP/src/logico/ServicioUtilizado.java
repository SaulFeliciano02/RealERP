package logico;

public class ServicioUtilizado extends CantBienesYServiciosUtilizados{
	
	private Servicio servicio;
	private boolean borrado;
	private float precioUnitario;
	private float valor;
	private String unidad;
	
	public ServicioUtilizado(Servicio servicio, boolean borrado) {
		super();
		this.servicio = servicio;
		this.borrado = borrado;
		this.precioUnitario = servicio.getPrecio();
		this.valor = servicio.getPrecio();
		if(servicio.getUnidadMedida() != null) {
			this.unidad = servicio.getUnidadMedida().getNombre();
		}
		else {
			this.unidad = "";
		}
	}

	public String getProducto() {
		return servicio.getNombre();
	}
	
	public Servicio getServicioClass() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getUnidad() {
		return unidad;
	}
	
	
	
	
	
	
}
