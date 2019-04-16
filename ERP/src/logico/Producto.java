package logico;

import java.util.ArrayList;

public class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private Rubro rubroProducto;
	private String tipoProducto;
	private Proveedores ProveedorPrin;
	private ArrayList<Proveedores> ProveedoresSec;
	private Moneda moneda;
	private Impuestos impuesto;
	private String observaciones;
	private ArrayList<Producto> sustitutos;
	private ArrayList<CostoIndirectoProducto> costosIndirectos;
	private UnidadMedida unidadMedida;
	private ArrayList<Precio> precios;
	private ArrayList<DescuentosAutomaticos> descuentos;
	private String comision;
	private Promocion promocion;
	private String codigoBarra;
	private float costoManoDeObra;
	private boolean tipo_descripcion; //Variable o Fija
	
		
	public Producto(String codigo, String nombre, String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, Moneda moneda, Impuestos impuesto,
			String observaciones, ArrayList<Producto> sustitutos, ArrayList<CostoIndirectoProducto> costosIndirectos,
			UnidadMedida unidadMedida, ArrayList<Precio> precios, ArrayList<DescuentosAutomaticos> descuentos,
			String comision, Promocion promocion, String codigoBarra, float costoManoDeObra, boolean tipo_descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.rubroProducto = rubroProducto;
		this.tipoProducto = tipoProducto;
		ProveedorPrin = proveedorPrin;
		ProveedoresSec = proveedoresSec;
		this.moneda = moneda;
		this.impuesto = impuesto;
		this.observaciones = observaciones;
		this.sustitutos = sustitutos;
		this.costosIndirectos = costosIndirectos;
		this.unidadMedida = unidadMedida;
		this.precios = precios;
		this.descuentos = descuentos;
		this.comision = comision;
		this.promocion = promocion;
		this.codigoBarra = codigoBarra;
		this.costoManoDeObra = costoManoDeObra;
		this.tipo_descripcion = tipo_descripcion;
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
	public Rubro getRubroProducto() {
		return rubroProducto;
	}
	public void setRubroProducto(Rubro rubroProducto) {
		this.rubroProducto = rubroProducto;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public Proveedores getProveedorPrin() {
		return ProveedorPrin;
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
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
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
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public float getCostoManoDeObra() {
		return costoManoDeObra;
	}
	public void setCostoManoDeObra(float costoManoDeObra) {
		this.costoManoDeObra = costoManoDeObra;
	}
	public boolean isTipo_descripcion() {
		return tipo_descripcion;
	}
	public void setTipo_descripcion(boolean tipo_descripcion) {
		this.tipo_descripcion = tipo_descripcion;
	}
	
	
	
	
	

}
