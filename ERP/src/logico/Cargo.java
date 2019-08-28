package logico;

public class Cargo {
	
	private String nombre;
	private boolean activo;
	private boolean manejodeproductos;
	private boolean infoproductos;
	private boolean facturarcompra;
	private boolean infofactura;
	private boolean facturaporcredito;
	private boolean manejopromociones;
	private boolean accesomodulorrhh;
	private boolean accesomodulogastos;
	private boolean accesomodulohistorial;
	private boolean accesomoduloconfiguracion;
	
	public Cargo(String nombre, boolean manejodeproductos, boolean infoproductos, boolean facturarcompra,
			boolean infofactura, boolean facturaporcredito, boolean manejopromociones, boolean accesomodulorrhh,
			boolean accesomodulogastos, boolean accesomodulohistorial, boolean accesomoduloconfiguracion) {
		super();
		this.nombre = nombre;
		this.activo = true;
		this.manejodeproductos = manejodeproductos;
		this.infoproductos = infoproductos;
		this.facturarcompra = facturarcompra;
		this.infofactura = infofactura;
		this.facturaporcredito = facturaporcredito;
		this.manejopromociones = manejopromociones;
		this.accesomodulorrhh = accesomodulorrhh;
		this.accesomodulogastos = accesomodulogastos;
		this.accesomodulohistorial = accesomodulohistorial;
		this.accesomoduloconfiguracion = accesomoduloconfiguracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isManejodeproductos() {
		return manejodeproductos;
	}

	public void setManejodeproductos(boolean manejodeproductos) {
		this.manejodeproductos = manejodeproductos;
	}

	public boolean isInfoproductos() {
		return infoproductos;
	}

	public void setInfoproductos(boolean infoproductos) {
		this.infoproductos = infoproductos;
	}

	public boolean isFacturarcompra() {
		return facturarcompra;
	}

	public void setFacturarcompra(boolean facturarcompra) {
		this.facturarcompra = facturarcompra;
	}

	public boolean isInfofactura() {
		return infofactura;
	}

	public void setInfofactura(boolean infofactura) {
		this.infofactura = infofactura;
	}

	public boolean isFacturaporcredito() {
		return facturaporcredito;
	}

	public void setFacturaporcredito(boolean facturaporcredito) {
		this.facturaporcredito = facturaporcredito;
	}

	public boolean isManejopromociones() {
		return manejopromociones;
	}

	public void setManejopromociones(boolean manejopromociones) {
		this.manejopromociones = manejopromociones;
	}

	public boolean isAccesomodulorrhh() {
		return accesomodulorrhh;
	}

	public void setAccesomodulorrhh(boolean accesomodulorrhh) {
		this.accesomodulorrhh = accesomodulorrhh;
	}

	public boolean isAccesomodulogastos() {
		return accesomodulogastos;
	}

	public void setAccesomodulogastos(boolean accesomodulogastos) {
		this.accesomodulogastos = accesomodulogastos;
	}

	public boolean isAccesomodulohistorial() {
		return accesomodulohistorial;
	}

	public void setAccesomodulohistorial(boolean accesomodulohistorial) {
		this.accesomodulohistorial = accesomodulohistorial;
	}

	public boolean isAccesomoduloconfiguracion() {
		return accesomoduloconfiguracion;
	}

	public void setAccesomoduloconfiguracion(boolean accesomoduloconfiguracion) {
		this.accesomoduloconfiguracion = accesomoduloconfiguracion;
	}
	
}
