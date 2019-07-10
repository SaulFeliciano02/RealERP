package logico;

public class ServicioUtilizado extends CantBienesYServiciosUtilizados{
	
	private Servicio servicio;
	private boolean borrado;
	
	public ServicioUtilizado(Servicio servicio, boolean borrado) {
		super();
		this.servicio = servicio;
		this.borrado = borrado;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
	
	
	
}
