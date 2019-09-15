package logico;

import java.time.LocalDate;

public class TransaccionesCajaChica {
	private float actualizacion;
	private String descripcion;
	private Usuario usuario;
	private LocalDate fecha;
	private String usuarioNombre;
	
	public TransaccionesCajaChica(float actualizacion, String descripcion, Usuario usuario, LocalDate fecha) {
		super();
		this.actualizacion = actualizacion;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.fecha = fecha;
		this.usuarioNombre = usuario.getUsuario();
	}
	
	
	public String getUsuarioNombre() {
		return usuarioNombre;
	}


	public void setUsuarioNombre(String usuarioNombre) {
		this.usuarioNombre = usuarioNombre;
	}


	public float getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(float actualizacion) {
		this.actualizacion = actualizacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	

}
