package logico;

import java.sql.Date;

public class Cliente extends Persona{

	public String tipoCliente;
	public Date cumpleanos;
	public String rnc;

	public Cliente(String codigo, String nombre, String telefono, String tipoCliente, Date cumpleanos, String rnc) {
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
	
}
