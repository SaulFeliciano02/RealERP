package logico;

import java.sql.Date;
import java.time.LocalDate;

public class Cliente extends Persona{

	public String tipoCliente;
	public LocalDate cumpleanos;
	public String rnc;

	public Cliente(String codigo, String nombre, String telefono, String tipoCliente, LocalDate cumpleanos, String rnc) {
		super(codigo, nombre, telefono);
		this.tipoCliente = tipoCliente;
		this.cumpleanos = cumpleanos;
		this.rnc = rnc;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public LocalDate getCumpleanos() {
		return cumpleanos;
	}

	public void setCumpleanos(LocalDate cumpleanos) {
		this.cumpleanos = cumpleanos;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}
	
}
