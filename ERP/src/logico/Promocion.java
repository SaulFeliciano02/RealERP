package logico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Promocion {
	private int porcentajeDescuento;
	private LocalDate fechaInicio; 
	private LocalTime horaInicio;
	private LocalDate fechaFinal; 
	private LocalTime horaFinal;
	private String dia;
	private ArrayList<Producto> productos;
	private boolean borrado;
	
	
	public Promocion(int porcentajeDescuento, LocalDate fechaInicio, LocalDate fechaFinal, LocalTime horaInicio, LocalTime horaFinal) {
		super();
		this.setPorcentajeDescuento(porcentajeDescuento);
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.fechaFinal = fechaFinal;
		this.horaFinal = horaFinal;
		this.productos = new ArrayList<>();
		this.setBorrado(false);
	}
	
	public Promocion(int porcentajeDescuento, String dia)
	{
		super();
		this.setPorcentajeDescuento(porcentajeDescuento);
		this.dia = dia;
		this.productos = new ArrayList<>();
		this.setBorrado(false);
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

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

}
