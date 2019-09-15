package logico;

import java.util.ArrayList;

public class CajaChica {
	private float montoActual;
	private ArrayList<TransaccionesCajaChica> transacciones;
	
	
	
	public CajaChica(float montoActual) {
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
	public ArrayList<TransaccionesCajaChica> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(ArrayList<TransaccionesCajaChica> transacciones) {
		this.transacciones = transacciones;
	}
	
	

}
