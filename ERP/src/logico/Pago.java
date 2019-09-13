package logico;

import java.time.LocalDate;

public class Pago {

	private float monto;
	private String tipoDePago;
	private LocalDate fecha;
	
	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getTipoDePago() {
		return tipoDePago;
	}

	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Pago(float monto, String tipoDePago, LocalDate fecha) {
		super();
		this.monto = monto;
		this.tipoDePago = tipoDePago;
		this.fecha = fecha;
	}
	
}
