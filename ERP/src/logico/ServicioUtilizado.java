package logico;

public class ServicioUtilizado{
	
	private Servicio servicio;
	private boolean borrado;
	private String nombre;
	private float precioUnitario;
	private float valor;
	
	public ServicioUtilizado(Servicio servicio) {
		super();
		this.servicio = servicio;
		this.borrado = false;
		this.nombre = servicio.getNombre();
		this.precioUnitario = servicio.getPrecio();
		this.valor = servicio.getPrecio();
	}

	public Servicio getServicio() {
		return servicio;
	}
	
	public String getNombre() {
		return nombre;
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
