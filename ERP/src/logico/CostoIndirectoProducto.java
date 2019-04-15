package logico;

public class CostoIndirectoProducto {
	
	private CostoIndirecto nombre;
	private float precioCosto;
	
	
	
	public CostoIndirectoProducto(CostoIndirecto nombre, float precioCosto) {
		super();
		this.nombre = nombre;
		this.precioCosto = precioCosto;
	}
	
	public CostoIndirecto getNombre() {
		return nombre;
	}
	public void setNombre(CostoIndirecto nombre) {
		this.nombre = nombre;
	}
	public float getPrecioCosto() {
		return precioCosto;
	}
	public void setPrecioCosto(float precioCosto) {
		this.precioCosto = precioCosto;
	}
	
	

}
