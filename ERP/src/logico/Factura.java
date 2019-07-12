package logico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Factura {
	
	private ArrayList<CantProductosUtilizados> prodFacturados;
	private ArrayList<CantKitsUtilizados> kitFacturados;
	private ArrayList<ServicioUtilizado> serviciosFacturados;
	private Cliente miCliente;
	private float montoTotal;
	private String tipoPago;
	private float montoRecibido;
	private float cambio;
	private LocalDate fecha;
	private LocalTime hora;
	private String clienteCodigo;
	private ArrayList<CantBienesYServiciosUtilizados> facturados;
	
	public Factura(ArrayList<CantProductosUtilizados> prodFacturados, ArrayList<CantKitsUtilizados> kitFacturados, ArrayList<ServicioUtilizado> serviciosFacturados, float montoTotal, String tipoPago,
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
		this.facturados = new ArrayList<>();
		
		
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

	public ArrayList<ServicioUtilizado> getServiciosFacturados() {
		return serviciosFacturados;
	}

	public void setServiciosFacturados(ArrayList<ServicioUtilizado> serviciosFacturados) {
		this.serviciosFacturados = serviciosFacturados;
	}
	
	public ArrayList<CantBienesYServiciosUtilizados> getFacturados(){
		facturados = null;
		for(CantProductosUtilizados c : prodFacturados) {
			facturados.add(c);
		}
		for(CantKitsUtilizados k : kitFacturados) {
			facturados.add(k);
		}
		for(ServicioUtilizado s : serviciosFacturados) {
			facturados.add(s);
		}
		return facturados;
	}
	
	
	
}
