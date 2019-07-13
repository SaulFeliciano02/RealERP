package logico;

public class CantKitsUtilizados{
	
	private Kit kit;
	private float cantidad;
	private boolean borrado;
	private String nombre;
	private float precioUnitario;
	private float valor;
	private String unidad;
	
	public CantKitsUtilizados(Kit kit, float cantidad) {
		super();
		this.kit = kit;
		this.cantidad = cantidad;
		this.borrado = false;
		this.nombre = kit.getNombre();
		this.precioUnitario = kit.getPrecio();
		this.valor = cantidad * kit.getPrecio();
		if(kit.getUnidadMedida() != null) {
			this.unidad = kit.getUnidadMedida().getNombre();
		}
		else {
			this.unidad = "";
		}
	}

	public Kit getKit() {
		return kit;
	}

	public void setKit(Kit kit) {
		this.kit = kit;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public String getNombre() {
		return nombre;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public float getValor() {
		return valor;
	}

	public String getUnidad() {
		return unidad;
	}
	
	
	
}
