package logico;

import java.util.ArrayList;

public class Servicio extends Producto{
	
	private CategoriaEmpleado categoria;
	private ArrayList<CantProductosUtilizados> materialesUtilizados;
	private ManoDeObra infoManoDeObra;
	private float manodeobra;
	private float costoPartida;

	public Servicio(String codigo, String nombre, String descripcion, Rubro rubroProducto, String tipoProducto,
			Proveedores proveedorPrin, ArrayList<Proveedores> proveedoresSec, String observaciones,
			UnidadMedida unidadMedida, Precio precio, String comision, String codigoBarra, String descripcionFija,
			CategoriaEmpleado categoria, ArrayList<CantProductosUtilizados> materialesUtilizados, float costo) {
		super(codigo, nombre, descripcion, rubroProducto, tipoProducto, proveedorPrin, proveedoresSec, observaciones,
				unidadMedida, precio, comision, codigoBarra, descripcionFija, costo);
		this.setCategoria(categoria);
		this.materialesUtilizados = materialesUtilizados;
		this.manodeobra = 0;
		this.costoPartida = 0;
	}
	
	

	public float getManodeobra() {
		manodeobra = infoManoDeObra.getCosto();
		return manodeobra;
	}
	
	public float getCostoPartida() {
		costoPartida = 0;
		for(CantProductosUtilizados c : getMaterialesUtilizados()) {
			costoPartida += c.getProductoClass().getPrecio();
		}
		return costoPartida;
	}



	public void setManodeobra(float manodeobra) {
		this.manodeobra = manodeobra;
	}



	public CategoriaEmpleado getCategoria() {
		return categoria;
	}



	public void setCategoria(CategoriaEmpleado categoria) {
		this.categoria = categoria;
	}



	public ArrayList<CantProductosUtilizados> getMaterialesUtilizados() {
		return materialesUtilizados;
	}
	
	public void agregarMaterialDeServicio(CantProductosUtilizados p) {
		materialesUtilizados.add(p);
	}
	
	public ManoDeObra getInfoManoDeObra() {
		return infoManoDeObra;
	}

	public void setInfoManoDeObra(ManoDeObra infoManoDeObra) {
		this.infoManoDeObra = infoManoDeObra;
	}

}
