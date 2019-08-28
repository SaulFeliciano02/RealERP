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
	private float adeudado;
	private String estado;
	private int plazoPagoDias;
	private float porcientoDescuento;
	private LocalDate fechaLimiteDescuento;
	private float porcientoPenalizacion;
	private String codigo;
	private float montoDelUltimoPago;
	private LocalDate fechaDelUltimoPago;
	
	public float getMontoDelUltimoPago() {
		return montoDelUltimoPago;
	}

	public void setMontoDelUltimoPago(float montoDelUltimoPago) {
		this.montoDelUltimoPago = montoDelUltimoPago;
	}

	public LocalDate getFechaDelUltimoPago() {
		return fechaDelUltimoPago;
	}

	public void setFechaDelUltimoPago(LocalDate fechaDelUltimoPago) {
		this.fechaDelUltimoPago = fechaDelUltimoPago;
	}

	public Factura(ArrayList<CantProductosUtilizados> prodFacturados, ArrayList<CantKitsUtilizados> kitFacturados, ArrayList<ServicioUtilizado> serviciosFacturados, float montoTotal, String tipoPago,
			float montoRecibido, float cambio, Cliente cliente, String tipoFactura, int cantcopias, String estado) {
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
		this.setEstado(estado);
		if (String.valueOf(Controladora.getInstance().getMisFacturas().size()).length() < 8)
		{
			int digitos = String.valueOf(Controladora.getInstance().getMisFacturas().size()).length();
			int i;
			String ceros = "";
			for(i = digitos; i < 8; i++)
			{
				ceros += "0";
			}
			
			setCodigo("B" + tipoFactura + ceros + Controladora.getInstance().getMisFacturas().size());
		}
		else
		{
			setCodigo("B" + tipoFactura + Controladora.getInstance().getMisFacturas().size());
		}
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
	
	public Factura(ArrayList<CantProductosUtilizados> prodFacturados, ArrayList<CantKitsUtilizados> kitFacturados, ArrayList<ServicioUtilizado> serviciosFacturados, float montoTotal, String tipoPago,
			float montoRecibido, float cambio, Cliente cliente, String tipoFactura, int cantcopias, String estado, float adeudado, int plazoPagoDias, float porcientoDescuento, LocalDate fechaLimiteDescuento, float porcientoPenalizacion) {
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
		this.setEstado(estado);
		this.adeudado = adeudado;
		this.plazoPagoDias = plazoPagoDias;
		this.setPorcientoDescuento(porcientoDescuento);
		this.setFechaLimiteDescuento(fechaLimiteDescuento);
		this.porcientoPenalizacion = porcientoPenalizacion;
		this.montoDelUltimoPago = montoRecibido;
		this.fechaDelUltimoPago = fecha;
		if (String.valueOf(Controladora.getInstance().getMisFacturas().size()).length() < 8)
		{
			int digitos = String.valueOf(Controladora.getInstance().getMisFacturas().size()).length();
			int i;
			String ceros = "";
			for(i = digitos; i < 8; i++)
			{
				ceros += "0";
			}
			
			setCodigo("B" + tipoFactura + ceros + Controladora.getInstance().getMisFacturas().size());
		}
		else
		{
			setCodigo("B" + tipoFactura + Controladora.getInstance().getMisFacturas().size());
		}
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

	public float getAdeudado() {
		return adeudado;
	}

	public void setAdeudado(float adeudado) {
		this.adeudado = adeudado;
	}

	public float calcularGanancia() {
		float ganancia = 0;
		
		if(adeudado == 0)
		{
			for (CantProductosUtilizados fac : prodFacturados) {
				ganancia += fac.getProducto().getPrecio() - fac.getProducto().getCosto();
			}
			
			for (CantKitsUtilizados fac : kitFacturados) {
				ganancia += fac.getKit().getPrecio() - fac.getKit().getCosto();
			}
			
			for (ServicioUtilizado fac : serviciosFacturados) {
				ganancia += fac.getServicio().getPrecio() - fac.getServicio().getCosto();
			}
		}
		
		return ganancia;
	}

	public float calcularIngreso() {
		float ingreso = 0;
		
		if(adeudado == 0)
		{
			ingreso = montoTotal;
		}
		
		return ingreso;
	}

	public float calcularIngresoPorPagar() {
		float ingreso = 0;
		
		if(adeudado > 0)
		{
			ingreso = montoTotal;
		}
		
		return ingreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPlazoPagoDias() {
		return plazoPagoDias;
	}

	public void setPlazoPagoDias(int plazoPagoDias) {
		this.plazoPagoDias = plazoPagoDias;
	}

	public float getPorcientoDescuento() {
		return porcientoDescuento;
	}

	public void setPorcientoDescuento(float porcientoDescuento) {
		this.porcientoDescuento = porcientoDescuento;
	}

	public LocalDate getFechaLimiteDescuento() {
		return fechaLimiteDescuento;
	}

	public void setFechaLimiteDescuento(LocalDate fechaLimiteDescuento) {
		this.fechaLimiteDescuento = fechaLimiteDescuento;
	}

	public float getPorcientoPenalizacion() {
		return porcientoPenalizacion;
	}

	public void setPorcientoPenalizacion(float porcientoPenalizacion) {
		this.porcientoPenalizacion = porcientoPenalizacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
