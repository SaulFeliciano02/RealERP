package logico;

import java.util.ArrayList;
//hey
abstract public class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private Rubro rubroProducto;
	private Proveedores ProveedorPrin;
	private ArrayList<Proveedores> ProveedoresSec;
	private Impuestos impuesto;
	private String observaciones;
	private ArrayList<Producto> sustitutos;
	private ArrayList<CostoIndirectoProducto> costosIndirectos; //Preguntar a Alonso si esto va con todos los tipos de productos.
	private UnidadMedida unidadMedida;
	private Precio precio;
	private ArrayList<Precio> precios;
	private ArrayList<DescuentosAutomaticos> descuentos;
	private String comision;
	private Promocion promocion;
	private String descripcionFija;
	private ArrayList<CostoDirecto> costosDirectos;
	

	public Producto(String codigo, String nombre, String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, 
			String observaciones,
			UnidadMedida unidadMedida, Precio precio,
			String comision, String codigoBarra, String descripcionFija) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.rubroProducto = rubroProducto;
		ProveedorPrin = proveedorPrin;
		ProveedoresSec = proveedoresSec;
		this.observaciones = observaciones;
		this.costosIndirectos = new ArrayList<CostoIndirectoProducto>();
		this.sustitutos = new ArrayList<Producto>();
		this.precios = new ArrayList<Precio>();
		this.descuentos = new ArrayList<DescuentosAutomaticos>();
		this.unidadMedida = unidadMedida;
		this.precio = precio;
		this.comision = comision;
		this.descripcionFija = descripcionFija;
		this.costosDirectos = new ArrayList<CostoDirecto>();
	}
	
	
	public Precio getPrecioClass() {
		return precio;
	}
	
	public float getPrecio() {
		return precio.getPrecio();
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public String getDescripcionFija() {
		return descripcionFija;
	}

	public void setDescripcionFija(String descripcionFija) {
		this.descripcionFija = descripcionFija;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Rubro getRubroProductoClass() {
		return rubroProducto;
	}
	public String getRubroProducto() {
		System.out.println(rubroProducto.getCodigo());
		return rubroProducto.getCodigo();
	}
	public void setRubroProducto(Rubro rubroProducto) {
		this.rubroProducto = rubroProducto;
	}
	public Proveedores getProveedorPrinClass() {
		return ProveedorPrin;
	}
	public String getProveedorPrin() {
		return ProveedorPrin.getCodigo();
	}
	public void setProveedorPrin(Proveedores proveedorPrin) {
		ProveedorPrin = proveedorPrin;
	}
	public ArrayList<Proveedores> getProveedoresSec() {
		return ProveedoresSec;
	}
	public void setProveedoresSec(ArrayList<Proveedores> proveedoresSec) {
		ProveedoresSec = proveedoresSec;
	}
	public Impuestos getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(Impuestos impuesto) {
		this.impuesto = impuesto;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public ArrayList<Producto> getSustitutos() {
		return sustitutos;
	}
	public void setSustitutos(ArrayList<Producto> sustitutos) {
		this.sustitutos = sustitutos;
	}
	public ArrayList<CostoIndirectoProducto> getCostosIndirectos() {
		return costosIndirectos;
	}
	public void setCostosIndirectos(ArrayList<CostoIndirectoProducto> costosIndirectos) {
		this.costosIndirectos = costosIndirectos;
	}
	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public ArrayList<Precio> getPrecios() {
		return precios;
	}
	public void setPrecios(ArrayList<Precio> precios) {
		this.precios = precios;
	}
	public ArrayList<DescuentosAutomaticos> getDescuentos() {
		return descuentos;
	}
	public void setDescuentos(ArrayList<DescuentosAutomaticos> descuentos) {
		this.descuentos = descuentos;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public Promocion getPromocion() {
		return promocion;
	}
	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}


	public ArrayList<CostoDirecto> getCostosDirectos() {
		return costosDirectos;
	}


	public void setCostosDirectos(ArrayList<CostoDirecto> costosDirectos) {
		this.costosDirectos = costosDirectos;
	}
}
