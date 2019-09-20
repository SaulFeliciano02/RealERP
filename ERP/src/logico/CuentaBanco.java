package logico;

import java.util.ArrayList;

public class CuentaBanco {
	private float montoActual;
	private ArrayList<TransaccionesCuentaBanco> transacciones;
	public CuentaBanco(float montoActual) {
		super();
		this.montoActual = montoActual;
		this.transacciones = new ArrayList<>();
	}
	public float getMontoActual() {
		return montoActual;
	}
	public void setMontoActual(float montoActual) {
		this.montoActual = montoActual;
	}
	public ArrayList<TransaccionesCuentaBanco> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(ArrayList<TransaccionesCuentaBanco> transacciones) {
		this.transacciones = transacciones;
	}
	
	
	
	

}
