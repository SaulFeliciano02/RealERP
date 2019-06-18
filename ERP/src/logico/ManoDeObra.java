package logico;

import java.sql.Date;

public class ManoDeObra {
	private float costo;
	private float cantidadHoras;
	private Date date;
	private CategoriaEmpleado categoria;
	
	public ManoDeObra(float costo, float cantidadHoras, Date date, CategoriaEmpleado categoria) {
		super();
		this.costo = costo;
		this.cantidadHoras = cantidadHoras;
		this.date = date;
		this.categoria = categoria;
	}

	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public float getCantidadHoras() {
		return cantidadHoras;
	}
	public void setCantidadHoras(float cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}
	public CategoriaEmpleado getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaEmpleado categoria) {
		this.categoria = categoria;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
