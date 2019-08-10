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
	private String tipoFactura;
	private int cantcopias;
	
	public Factura(ArrayList<CantProductosUtilizados> prodFacturados, ArrayList<CantKitsUtilizados> kitFacturados, ArrayList<ServicioUtilizado> serviciosFacturados, float montoTotal, String tipoPago,
			float montoRecibido, float cambio, Cliente cliente, String tipoFactura, int cantcopias) {
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
		this.cantcopias = cantcopias;
		this.setTipoFactura(tipoFactura);
		if(cliente != null) {
			this.clienteCodigo = cliente.getCodigo();
		}
		else {
			this.clienteCodigo = "No Cliente";
		}
		this.facturados = new ArrayList<>();
		for(CantProductosUtilizados c : prodFacturados) {
			CantBienesYServiciosUtilizados all = new CantBienesYServiciosUtilizados(c.getUnidad(), c.getCantidad(), c.getNombre(), c.getPrecioUnitario(), c.getValor());
			facturados.add(all);
		}
		for(CantKitsUtilizados k : kitFacturados) {
			CantBienesYServiciosUtilizados all = new CantBienesYServiciosUtilizados(k.getUnidad(), k.getCantidad(), k.getNombre(), k.getPrecioUnitario(), k.getValor());
			facturados.add(all);
		}
		for(ServicioUtilizado s : serviciosFacturados) {
			CantBienesYServiciosUtilizados all = new CantBienesYServiciosUtilizados("", 1, s.getNombre(), s.getPrecioUnitario(), s.getValor());
			facturados.add(all);
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

	public ArrayList<ServicioUtilizado> getServiciosFacturados() {
		return serviciosFacturados;
	}

	public void setServiciosFacturados(ArrayList<ServicioUtilizado> serviciosFacturados) {
		this.serviciosFacturados = serviciosFacturados;
	}
	
	public ArrayList<CantBienesYServiciosUtilizados> getFacturados(){
		
		return facturados;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public int getCantcopias() {
		return cantcopias;
	}

	public void setCantcopias(int cantcopias) {
		this.cantcopias = cantcopias;
	}
	
	
	
}
