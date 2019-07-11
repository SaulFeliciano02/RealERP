package logico;

import java.sql.Date;
import java.time.LocalDate;

public class Cliente extends Persona{

	private String tipoCliente;
	private Date cumpleanos;
	private String rnc;
	private float credito;
	private float deuda;
	private LocalDate ultimaActualizacionCredito;
	private LocalDate ultimaActualizacionDeuda;
	
	public Cliente(String codigo, String nombre, String telefono, String tipoCliente, Date cumpleanos, String rnc) {
		super(codigo, nombre, telefono);
		this.tipoCliente = tipoCliente;
		this.cumpleanos = cumpleanos;
		this.rnc = rnc;
		this.credito = 0;
		this.deuda = 0;
	}

	public float getCredito() {
		return credito;
	}

	public void setCredito(float credito) {
		this.credito = credito;
	}

	public float getDeuda() {
		return deuda;
	}

	public void setDeuda(float deuda) {
		this.deuda = deuda;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Date getCumpleanos() {
		return cumpleanos;
	}

	public void setCumpleanos(Date cumpleanos) {
		this.cumpleanos = cumpleanos;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public LocalDate getUltimaActualizacionCredito() {
		return ultimaActualizacionCredito;
	}

	public void setUltimaActualizacionCredito(LocalDate ultimaActualizacionCredito) {
		this.ultimaActualizacionCredito = ultimaActualizacionCredito;
	}

	public LocalDate getUltimaActualizacionDeuda() {
		return ultimaActualizacionDeuda;
	}

	public void setUltimaActualizacionDeuda(LocalDate ultimaActualizacionDeuda) {
		this.ultimaActualizacionDeuda = ultimaActualizacionDeuda;
	}
	
}
