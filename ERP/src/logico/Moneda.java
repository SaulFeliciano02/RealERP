package logico;

public class Moneda {
	private float valorMoneda;
	private String moneda;
	private boolean borrado;
	
	public Moneda(float valorMoneda, String moneda) {
		super();
		this.valorMoneda = valorMoneda;
		this.moneda = moneda;
		this.setBorrado(false);
	}
	public float getvalorMoneda() {
		return valorMoneda;
	}
	public void setvalorMoneda(float valorMoneda) {
		this.valorMoneda = valorMoneda;
	}
	public String getmoneda() {
		return moneda;
	}
	public void setmoneda(String moneda) {
		this.moneda = moneda;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	

}
