package logico;

public class Usuario {
	
	private Empleado empleado;
	private boolean usuarioActivo;
	private String contrasena;
	private boolean contrasenaActiva;
	private Cargo cargo;
	private String usuario;
	private String empleadoNombre;
	private String cargoNombre;
	
	public Usuario(String usuario, Empleado empleado, boolean usuarioActivo, String contrasena, boolean contrasenaActiva,
			Cargo cargo) {
		super();
		this.setUsuario(usuario);
		this.empleado = empleado;
		this.usuarioActivo = usuarioActivo;
		this.contrasena = contrasena;
		this.contrasenaActiva = contrasenaActiva;
		this.cargo = cargo;
		this.empleadoNombre = empleado.getNombre();
		this.cargoNombre = cargo.getNombre();
	}

	
	
	public String getCargoNombre() {
		return cargoNombre;
	}



	public void setCargoNombre(String cargoNombre) {
		this.cargoNombre = cargoNombre;
	}



	public String getEmpleadoNombre() {
		return empleadoNombre;
	}


	public void setEmpleadoNombre(String empleadoNombre) {
		this.empleadoNombre = empleadoNombre;
	}


	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public boolean isUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(boolean usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isContrasenaActiva() {
		return contrasenaActiva;
	}

	public void setContrasenaActiva(boolean contrasenaActiva) {
		this.contrasenaActiva = contrasenaActiva;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
