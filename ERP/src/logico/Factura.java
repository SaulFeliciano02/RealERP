package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	
	private ArrayList<CantProductosUtilizados> prodFacturados;
	private Cliente miCliente;
	private float montoTotal;
	private String tipoPago;
	private float montoRecibido;
	private float cambio;
	private LocalDate fecha;
	
	public Factura(ArrayList<CantProductosUtilizados> prodFacturados, float montoTotal, String tipoPago,
			float montoRecibido, float cambio) {
		super();
		this.prodFacturados = prodFacturados;
		this.montoTotal = montoTotal;
		this.tipoPago = tipoPago;
		this.montoRecibido = montoRecibido;
		this.cambio = cambio;
		this.fecha = LocalDate.now();
	}

	public ArrayList<CantProductosUtilizados> getProdFacturados() {
		return prodFacturados;
	}

	public Cliente getMiCliente() {
		return miCliente;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public float getMontoRecibido() {
		return montoRecibido;
	}

	public float getCambio() {
		return cambio;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	
}
