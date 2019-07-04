package logico;

public class CantKitsUtilizados {
	
	private Kit kit;
	private float cantidad;
	private boolean borrado;
	
	public CantKitsUtilizados(Kit kit, float cantidad) {
		super();
		this.kit = kit;
		this.cantidad = cantidad;
		this.borrado = false;
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
	
}
