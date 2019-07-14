package logico;

import java.time.LocalDate;

public class Empresa {
	private String nombre;
	private String rnc;
	private String telefono;
	private String domicilio;
	private int ITBIS;
	private int valorFiscalInferior;
	private int valorFiscalMayor;
	private LocalDate fechaSecSolicitada;
	private LocalDate fechasecvencimiento;
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;
	private boolean borrado;
	public Empresa(String nombre, String rnc, String telefono, String domicilio, int valorFiscalInferior,
			int valorFiscalMayor, LocalDate fechaSecSolicitada, LocalDate fechasecvencimiento, LocalDate fechaInicio,
			LocalDate fechaFinal) {
		super();
		this.nombre = nombre;
		this.rnc = rnc;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.valorFiscalInferior = valorFiscalInferior;
		this.valorFiscalMayor = valorFiscalMayor;
		this.fechaSecSolicitada = fechaSecSolicitada;
		this.fechasecvencimiento = fechasecvencimiento;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.ITBIS = 18;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRnc() {
		return rnc;
	}
	public void setRnc(String rnc) {
		this.rnc = rnc;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public int getITBIS() {
		return ITBIS;
	}
	public void setITBIS(int iTBIS) {
		ITBIS = iTBIS;
	}
	public int getValorFiscalInferior() {
		return valorFiscalInferior;
	}
	public void setValorFiscalInferior(int valorFiscalInferior) {
		this.valorFiscalInferior = valorFiscalInferior;
	}
	public int getValorFiscalMayor() {
		return valorFiscalMayor;
	}
	public void setValorFiscalMayor(int valorFiscalMayor) {
		this.valorFiscalMayor = valorFiscalMayor;
	}
	public LocalDate getFechaSecSolicitada() {
		return fechaSecSolicitada;
	}
	public void setFechaSecSolicitada(LocalDate fechaSecSolicitada) {
		this.fechaSecSolicitada = fechaSecSolicitada;
	}
	public LocalDate getFechasecvencimiento() {
		return fechasecvencimiento;
	}
	public void setFechasecvencimiento(LocalDate fechasecvencimiento) {
		this.fechasecvencimiento = fechasecvencimiento;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public boolean isBorrado() {
		return borrado;
	}
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	
	
}
