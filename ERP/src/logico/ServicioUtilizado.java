package logico;

public class ServicioUtilizado extends CantBienesYServiciosUtilizados{
	
	private Servicio servicio;
	private boolean borrado;
	private float precioUnitario;
	private float valor;
	
	public ServicioUtilizado(Servicio servicio) {
		super();
		this.servicio = servicio;
		this.borrado = false;
		this.precioUnitario = servicio.getPrecio();
		this.valor = servicio.getPrecio();
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

	
	
	
	
	
	
	
}
