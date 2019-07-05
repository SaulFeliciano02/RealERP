package logico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Factura {
	
	private ArrayList<CantProductosUtilizados> prodFacturados;
	private ArrayList<CantKitsUtilizados> kitFacturados;
	private ArrayList<Servicio> serviciosFacturados;
	private Cliente miCliente;
	private float montoTotal;
	private String tipoPago;
	private float montoRecibido;
	private float cambio;
	private LocalDate fecha;
	private LocalTime hora;
	private String clienteCodigo;
	
	public Factura(ArrayList<CantProductosUtilizados> prodFacturados, ArrayList<CantKitsUtilizados> kitFacturados, ArrayList<Servicio> serviciosFacturados, float montoTotal, String tipoPago,
			float montoRecibido, float cambio, Cliente cliente) {
		super();
		this.prodFacturados = prodFacturados;
		this.kitFacturados = kitFacturados;
		this.serviciosFacturados = serviciosFacturados;
		this.montoTotal = montoTotal;
		this.tipoPago = tipoPago;
		this.montoRecibido = montoRecibido;
		this.cambio = cambio;
		this.miCliente = cliente;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		if(cliente != null) {
			this.clienteCodigo = cliente.getCodigo();
		}
		else {
			this.clienteCodigo = "No Cliente";
		}
		
		
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
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getClienteCodigo() {
		return clienteCodigo;
	}

	public ArrayList<CantKitsUtilizados> getKitFacturados() {
		return kitFacturados;
	}

	public void setKitFacturados(ArrayList<CantKitsUtilizados> kitFacturados) {
		this.kitFacturados = kitFacturados;
	}

	public ArrayList<Servicio> getServiciosFacturados() {
		return serviciosFacturados;
	}

	public void setServiciosFacturados(ArrayList<Servicio> serviciosFacturados) {
		this.serviciosFacturados = serviciosFacturados;
	}
	
	
	
}
