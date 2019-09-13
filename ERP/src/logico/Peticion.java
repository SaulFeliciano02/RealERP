package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Peticion {
	private String codigo;
	private Proveedores proveedor;
	private Producto producto;
	private int cantidad;
	private float monto;
	private String metodoPago;
	private String estado;
	private LocalDate fecha;
	private ArrayList<Pago> pagosCredito;
	private String proveedorCodigo;
	private String productoNombre;
	private float adeudado;
	
	public Peticion(String codigo, Proveedores proveedor, Producto producto, int cantidad, float monto,
			String metodoPago, String estado, LocalDate fecha) {
		super();
		this.codigo = codigo;
		this.proveedor = proveedor;
		this.producto = producto;
		this.cantidad = cantidad;
		this.monto = monto;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.fecha = fecha;
		this.proveedorCodigo = proveedor.getCodigo();
		this.productoNombre = producto.getNombre();
		this.adeudado = 0;
		this.pagosCredito = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getProveedorCodigo() {
		return proveedorCodigo;
	}

	public void setProveedorCodigo(String proveedorCodigo) {
		this.proveedorCodigo = proveedorCodigo;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}

	public float getAdeudado() {
		return adeudado;
	}

	public void setAdeudado(float adeudado) {
		this.adeudado = adeudado;
	}

	public ArrayList<Pago> getPagosCredito() {
		return pagosCredito;
	}

	public void setPagosCredito(ArrayList<Pago> pagosCredito) {
		this.pagosCredito = pagosCredito;
	}
	
	public float calcularPagosPeticion()
	{
		float monto = 0;
		
		if(pagosCredito.size() > 0)
		{
			for (Pago pago : pagosCredito) {
				monto += pago.getMonto();
			}
		}
		
		return monto;
	}
	
}
