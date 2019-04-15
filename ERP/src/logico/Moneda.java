package logico;

public class Moneda {
	private float ValorMoneda;
	private String Moneda;
	
	public Moneda(float valorMoneda, String moneda) {
		super();
		this.ValorMoneda = valorMoneda;
		this.Moneda = moneda;
	}
	public float getValorMoneda() {
		return ValorMoneda;
	}
	public void setValorMoneda(float valorMoneda) {
		ValorMoneda = valorMoneda;
	}
	public String getMoneda() {
		return Moneda;
	}
	public void setMoneda(String moneda) {
		Moneda = moneda;
	}
	
	

}
