package logico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;


import basededatos.Conexion;

public class Controladora implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Date date = null;
	Rubro armas = new Rubro("00001", "Armas");
	/*Cliente cliente1 = new Cliente("000001", "Marcos", "312312", "Activo", null, "2312313");
	Cliente cliente2 = new Cliente("000002", "Saul", "312312", "Activo", null, "2312313");
	Cliente cliente3 = new Cliente("000003", "Yorman", "312312", "Activo", null, "2312313");
	Cliente cliente4 = new Cliente("000004", "Sarah", "312312", "Activo", null, "2312313");
	Cliente cliente5 = new Cliente("000005", "Michael", "312312", "Activo", null, "2312313");*/
	Proveedores proveedor1 = new Proveedores("000001", "Jose", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor2 = new Proveedores("000002", "Marcos", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor3 = new Proveedores("000003", "Maria", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor4 = new Proveedores("000004", "Juan", "21312313", "Su casa", "", "", null, "");
	Proveedores proveedor5 = new Proveedores("000005", "Luis", "21312313", "Su casa", "", "", null, "");
	Proveedores prov1 = new Proveedores("Prov-01", "Juan", "777-777-7777", "Wareve", null, "011", null, null);
	Proveedores prov2 = new Proveedores("Prov-02", "Pedro", "777-777-7777", "Wareve", null, "012", null, null);
	Proveedores prov3 = new Proveedores("Prov-03", "Edualdo", "777-777-7777", "Wareve", null, "013", null, null);
	Longitud pulgada = new Longitud("Longitud", "Pulgadas", "inch");
	Longitud pies = new Longitud("Longitud", "Pies", "ft");
	Longitud yardas = new Longitud("Longitud", "Yardas", "yd");
	Longitud milimetros = new Longitud("Longitud", "Milimetros", "mm");
	Longitud centimetros = new Longitud("Longitud", "Centimetros", "cm");
	Longitud metros = new Longitud("Longitud", "Metros", "m");
	Volumen pulgada_cb = new Volumen("Volumen", "Pulgadas cb", "ci");
	Volumen pies_cb = new Volumen("Volumen", "Pies cb", "cft");
	Volumen cucharaTe = new Volumen("Volumen", "Cuchara de t�", "tsp");
	Volumen cucharaMadera = new Volumen("Volumen", "Cuchara de madera", "tbsp");
	Volumen onzafluida = new Volumen("Volumen", "Onza fluida", "foz");
	Volumen taza = new Volumen("Volumen", "Taza", "cp");
	Volumen medioLitro = new Volumen("Volumen", "Medio litro", "L/2");
	Volumen cuartogalon = new Volumen("Volumen", "Cuarto de galón", "gl/4");
	Volumen mediogalon = new Volumen("Volumen", "Medio galón", "gl/2");
	Volumen galon = new Volumen("Volumen", "Galón", "gl");
	Volumen barril = new Volumen("Volumen", "Barril", "brl");
	Volumen milimetrocb = new Volumen("Volumen", "Milímetros cb", "cmm");
	Volumen centimetrocb = new Volumen("Volumen", "Centímetros cb", "ccm");
	Volumen metrocb = new Volumen("Volumen", "Metros cb", "cmt");
	Volumen mililitros = new Volumen("Volumen", "Mililitros", "ml");
	Volumen litros = new Volumen("Volumen", "Litros", "L");
	Masa grano = new Masa("Masa", "Grano", "gn");
	Masa onza = new Masa("Masa", "Onza", "oz");
	Masa libra = new Masa("Masa", "Libra", "lb");
	Masa miligramo = new Masa("Masa", "Miligramo", "mg");
	Masa gramo = new Masa("Masa", "Gramo", "g");
	Masa kilogramo = new Masa("Masa", "Kilogramo", "kg");
	Area sq_pulgadas = new Area("Area", "Sq Pulgadas", "sqinch");
	Area sq_pies = new Area("Area", "Sq Pies", "sqft");
	Area sq_yardas = new Area("Area", "Sq Yardas", "sqyd");
	Area sq_milimetros = new Area("Area", "Sq Milimetros", "sqmm");
	Area sq_centimetros = new Area("Area", "Sq Centímetros", "sqcm");
	Area sq_metros = new Area("Area", "Sq Metros", "sqm");
	ArrayList<Proveedores> provsec = new ArrayList<>();
	Moneda dola = new Moneda(50, "dolar");
	Precio pre = new Precio(1500f, "caro", true, 0, 0);
	Cargo cargo1 = new Cargo("Administrador", true, true, true, true, true, true, true, true, true, true);
	Cargo cargo2 = new Cargo("Cajero", false, false, true, false, true, false, false, false, false, false);
	Proveedores provdefault;
	Usuario usuarioLogueado = null;
	//UnidadMedida lb = new UnidadMedida("Libra", "Lb");
	//Estandar pro = new Estandar(10f, 5f, 20f, date, 150f, false, "01", "pitola", "Esa vaina mata", armas, "no se", prov1, provsec, dola, "Eso no silve", lb, pre, "0f", "pis-0101", 0f, "Mata", "Puesde que no mate");
	
	private ArrayList<Cliente> misClientes;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Proveedores> misProveedores;
	private ArrayList<Cargo> misCargos;
	
	private ArrayList<DescuentosAutomaticos> misDescuentos;
	private ArrayList<Promocion> misPromociones;
	
	private ArrayList<Rubro> misRubros;
	
	private ArrayList<Producto> misProductos;
	private ArrayList<Estandar> misProductosEstandar;
	private ArrayList<Kit> misProductosKit;
	private ArrayList<Servicio> misProductosServicio;
	private ArrayList<Estandar> misProductosMatriz;
	private ArrayList<GrupoAtributo> misGrupoAtributo;
	private ArrayList<Atributos> misAtributos;
	private ArrayList<GastoGeneral> misGastosGenerales;
	private ArrayList<UnidadMedida> misUnidadMedida;
	private ArrayList<CategoriaEmpleado> misCategoriasEmpleado;
	private ArrayList<CantProductosUtilizados> misCantProductosUtilizados;
	private ArrayList<CantKitsUtilizados> misCantKitsUtilizados;
	private ArrayList<Precio> misPrecios;
	private ArrayList<Partida> misPartidas;
	private ArrayList<Combinaciones> misCombinaciones;
	private ArrayList<ManoDeObra> misManosDeObras;
	private ArrayList<CostoIndirectoProducto> misCostosIndirectos;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Factura> misFacturasValorFiscal;
	private ArrayList<Area> misAreas;
	private ArrayList<Longitud> misLongitudes;
	private ArrayList<Masa> misMasas;
	private ArrayList<Volumen> misVolumenes;
	private ArrayList<Peticion> misPeticiones;
	private ArrayList<Usuario> misUsuarios;
	private float deudaTotal;
	private float gananciaVentasPagadas;
	private float ingresosVentasPagadas;
	private float ingresosVentasPorPagar;
	private float ingresoTotal;
	private float gananciasTotal;
	private float pagosDeudasClientesTotal;
	private float egresosPagos;
	private float egresosPorPagar;
	private float montoPagoPeticionesPorPagarTotal;
	private float deudaPeticionesTotal;
	
	public float getPagosDeudasClientesTotal() {
		return pagosDeudasClientesTotal;
	}

	public void setPagosDeudasClientesTotal(float pagosDeudasClientesTotal) {
		this.pagosDeudasClientesTotal = pagosDeudasClientesTotal;
	}
	
	public float getGananciasTotal() {
		return gananciasTotal;
	}

	public void setGananciasTotal(float gananciasTotal) {
		this.gananciasTotal = gananciasTotal;
	}

	private Empresa miEmpresa;
	private CajaChica miCajaChica;
	private CuentaBanco miCuentaBanco;
	
	private float ventaPromedioMensual;
	
	private static Controladora controladora;
	
	public Controladora() {
		super();
		this.misClientes = new ArrayList<>();
		this.misEmpleados = new ArrayList<>();
		this.misProveedores = new ArrayList<>();
		this.misDescuentos = new ArrayList<>();
		this.misPromociones = new ArrayList<>();
		this.misRubros = new ArrayList<>();
		this.misProductos = new ArrayList<>();
		this.misProductosEstandar = new ArrayList<>();
		this.misProductosKit = new ArrayList<>();
		this.misProductosServicio = new ArrayList<>();
		this.misProductosMatriz = new ArrayList<>();
		this.misUnidadMedida = new ArrayList<>();
		this.misAreas = new ArrayList<>();
		this.misLongitudes = new ArrayList<>();
		this.misMasas = new ArrayList<>();
		this.misVolumenes = new ArrayList<>();
		this.misPartidas = new ArrayList<>();
		this.misCantProductosUtilizados = new ArrayList<>();
		this.misPrecios = new ArrayList<>();
		this.misCombinaciones = new ArrayList<>();
		this.misCostosIndirectos = new ArrayList<>();
		this.misFacturas = new ArrayList<>();
		this.misCantKitsUtilizados = new ArrayList<>();
		this.misPeticiones = new ArrayList<>();
		this.setMisFacturasValorFiscal(new ArrayList<>());
		this.misCargos = new ArrayList<>();
		this.misUsuarios = new ArrayList<>();
		
		/*misClientes.add(cliente1);
		misClientes.add(cliente2);
		misClientes.add(cliente3);
		misClientes.add(cliente4);
		misClientes.add(cliente5);*/
		
		/**misProveedores.add(proveedor1);
		misProveedores.add(proveedor2);
		misProveedores.add(proveedor3);
		misProveedores.add(proveedor4);
		misProveedores.add(proveedor5);**/
		
		misUnidadMedida.add(pulgada);
		misUnidadMedida.add(pies);
		misUnidadMedida.add(yardas);
		misUnidadMedida.add(milimetros);
		misUnidadMedida.add(centimetros);
		misUnidadMedida.add(metros);
		misUnidadMedida.add(pulgada_cb);
		misUnidadMedida.add(pies_cb);
		misUnidadMedida.add(cucharaTe);
		misUnidadMedida.add(cucharaMadera);
		misUnidadMedida.add(onzafluida);
		misUnidadMedida.add(taza);
		misUnidadMedida.add(medioLitro);
		misUnidadMedida.add(cuartogalon);
		misUnidadMedida.add(mediogalon);
		misUnidadMedida.add(galon);
		misUnidadMedida.add(barril);
		misUnidadMedida.add(milimetrocb);
		misUnidadMedida.add(centimetrocb);
		misUnidadMedida.add(metrocb);
		misUnidadMedida.add(mililitros);
		misUnidadMedida.add(litros);
		misUnidadMedida.add(grano);
		misUnidadMedida.add(onza);
		misUnidadMedida.add(libra);
		misUnidadMedida.add(miligramo);
		misUnidadMedida.add(gramo);
		misUnidadMedida.add(kilogramo);
		misUnidadMedida.add(sq_pulgadas);
		misUnidadMedida.add(sq_pies);
		misUnidadMedida.add(sq_yardas);
		misUnidadMedida.add(sq_milimetros);
		misUnidadMedida.add(sq_centimetros);
		misUnidadMedida.add(sq_metros);
		
		
		misCargos.add(cargo1);
		misCargos.add(cargo2);
		
		if(activarLoadCargosUsuariosDefaultSQL())
		{
			guardarCargosUsuariosSQL(cargo1);
			guardarCargosUsuariosSQL(cargo2);
		}
		
		createProveedorDefault();
		
		//misRubros.add(armas);
		//misProductos.add(pro);
		//misProductosEstandar.add(pro);
		this.misAtributos = new ArrayList<>();
		this.misGrupoAtributo = new ArrayList<>();
		this.misGastosGenerales = new ArrayList<>();
		this.misCategoriasEmpleado = new ArrayList<>();
		this.misManosDeObras = new ArrayList<>();
		this.usuarioLogueado = null;
		
		this.miEmpresa = null;
		this.miCajaChica = null;
		this.miCuentaBanco = null;
	}
	
	public CajaChica getMiCajaChica() {
		return miCajaChica;
	}
	
	public void setCajaChica(CajaChica cajaChica) {
		miCajaChica = cajaChica;
	}
	
	public CuentaBanco getMiCuentaBanco() {
		return miCuentaBanco;
	}
	
	public void setCuentaBanco(CuentaBanco cuentaBanco) {
		miCuentaBanco = cuentaBanco;
	}
	
	public Usuario getUsuarioLogueado() {
		return usuarioLogueado;
	}



	public void setUsuarioLogueado(Usuario usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}



	public ArrayList<Peticion> getMisPeticiones(){
		return misPeticiones;
	}
	
	public void setMiEmpresa(Empresa miEmpresa) {
		this.miEmpresa = miEmpresa;
	}

	public Empresa getMiEmpresa() {
		return miEmpresa;
	}
	
	
	public ArrayList<CostoIndirectoProducto> getMisCostosIndirectos(){
		return misCostosIndirectos;
	}
	
	public ArrayList<Estandar> getMisProductosMatriz(){
		return misProductosMatriz;
	}
	
	public ArrayList<ManoDeObra> getMisManosDeObras(){
		return misManosDeObras;
	}
	
	public ArrayList<Combinaciones> getMisCombinaciones() {
		return misCombinaciones;
	}




	public ArrayList<Partida> getMisPartidas() {
		return misPartidas;
	}




	public ArrayList<Precio> getMisPrecios() {
		return misPrecios;
	}




	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public ArrayList<CantProductosUtilizados> getMisCantProductosUtilizados() {
		return misCantProductosUtilizados;
	}



	public ArrayList<Area> getMisAreas() {
		return misAreas;
	}


	public ArrayList<Longitud> getMisLongitudes() {
		return misLongitudes;
	}


	public ArrayList<Masa> getMisMasas() {
		return misMasas;
	}


	public ArrayList<Volumen> getMisVolumenes() {
		return misVolumenes;
	}


	public ArrayList<CategoriaEmpleado> getMisCategoriasEmpleado() {
		return misCategoriasEmpleado;
	}

	public static Controladora getInstance() {
		if (controladora == null) {
			controladora = new Controladora();
		}
		return controladora;
	}
	
	public void addCliente(Cliente c) {
		misClientes.add(c);
		
		guardarClienteSQL(c);
	}
	
	public void createProveedorDefault()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		String nombre = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT nombre FROM infoempresa WHERE borrado = 0");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				nombre = r.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		if(emptyProveedores())
		{
			if(nombre != null)
			{
				provdefault = new Proveedores("00", nombre, "", "", "", "", null, null);
			}
			else
			{
				provdefault = new Proveedores("00", "Mi Empresa", "", "", "", "", null, null);
			}
			
			getMisProveedores().add(provdefault);
			guardarProveedorSQL(provdefault);
		}
	}
	
	public float getDeudaPeticionesTotal() {
		return deudaPeticionesTotal;
	}

	public void setDeudaPeticionesTotal(float deudaPeticionesTotal) {
		this.deudaPeticionesTotal = deudaPeticionesTotal;
	}

	public void guardarPromedioGananciaAnualSQL(float monto)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		/*LocalDate localDate = LocalDate.now();
		Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());*/
		//Date date1 = (java.sql.Date.valueOf(LocalDate.now()));
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO promediogananciaanual (monto, fecha) VALUES (?, ?)");
			p.setFloat(1, monto);
			p.setDate(2, (java.sql.Date.valueOf(LocalDate.now())));
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarClienteSQL(Cliente cliente) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO clientes (codigo, telefono, cumpleanos, rnc, nombre) VALUES (?, ?, ?, ?, ?)");
			p.setString(1, cliente.getCodigo());
			p.setString(2, cliente.getTelefono());
			p.setDate(3, cliente.getCumpleanos());
			p.setString(4, cliente.getRnc());
			p.setString(5, cliente.getNombre());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}
	
	public void guardarCajaChicaSQL(CajaChica caja)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO cajachica (monto, fecha) VALUES (?, ?)");
			p.setFloat(1, caja.getMontoActual());
			p.setDate(2, (java.sql.Date.valueOf(LocalDate.now())));
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			c.close();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarTransaccionCaja(TransaccionesCajaChica transaccion) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO transaccionescajachica (montoactual, actualizacion, descripcion, usuario, fecha) VALUES (?, ?, ?, ?, ?)");
			p.setFloat(1, getMiCajaChica().getMontoActual());
			p.setFloat(2, transaccion.getActualizacion());
			p.setString(3, transaccion.getDescripcion());
			p.setInt(4, getMisUsuarios().indexOf(transaccion.getUsuario())+1);
			p.setDate(5, (java.sql.Date.valueOf(transaccion.getFecha())));
			
			p.executeUpdate();
			
			//ejecutar el preparedStatement
			p = (PreparedStatement)
					c.prepareStatement("UPDATE cajachica SET monto = '"+Controladora.getInstance().getMiCajaChica().getMontoActual()+"' WHERE idcajachica = 1");
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarCuentaBancoSQL(CuentaBanco cuentaBanco)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO montocuentabancaria (monto, fecha) VALUES (?, ?)");
			p.setFloat(1, cuentaBanco.getMontoActual());
			p.setDate(2, (java.sql.Date.valueOf(LocalDate.now())));
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			c.close();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarTransaccionCuenta(TransaccionesCuentaBanco transaccion) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO transaccionescuentabancaria (montoactual, actualizacion, descripcion, fecha) VALUES (?, ?, ?, ?)");
			p.setFloat(1, getMiCajaChica().getMontoActual());
			p.setFloat(2, transaccion.getActualizacion());
			p.setString(3, transaccion.getDescripcion());
			p.setDate(4, (java.sql.Date.valueOf(transaccion.getFecha())));
			
			p.executeUpdate();
			
			//ejecutar el preparedStatement
			p = (PreparedStatement)
					c.prepareStatement("UPDATE montocuentabancaria SET monto = '"+Controladora.getInstance().getMiCuentaBanco().getMontoActual()+"' WHERE idmontocuentabancaria = 1");
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarPeticionSQL(Peticion pet)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO peticiones (codigo, idproveedor, idproducto, cantidad, monto, metodopago, estado, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, pet.getCodigo());
			p.setInt(2, getMisProveedores().indexOf(pet.getProveedor())+1);
			p.setInt(3, getMisProductos().indexOf(pet.getProducto())+1);
			p.setFloat(4, pet.getCantidad());
			p.setFloat(5, pet.getMonto());
			p.setString(6, pet.getMetodoPago());
			p.setString(7, pet.getEstado());
			p.setDate(8, (java.sql.Date.valueOf(pet.getFecha())));
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarPeticionesCreditoSQL(Peticion pet)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO peticionescredito (peticion, adeudado) VALUES (?, ?)");
			p.setInt(1, misPeticiones.indexOf(pet)+1);
			p.setFloat(2, pet.getAdeudado());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarPagoPeticionesCreditoSQL(Peticion pet, float monto, String tipoPago)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO pagopeticionescredito (peticion, montopagado, fechadelpago, tipodepago) VALUES (?, ?, ?, ?)");
			p.setInt(1, misPeticiones.indexOf(pet)+1);
			p.setFloat(2, monto);
			p.setDate(3, (java.sql.Date.valueOf(LocalDate.now())));
			p.setString(4, tipoPago);
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void guardarCargosUsuariosSQL(Cargo defaul)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO cargos (nombre) VALUES (?)");
			p.setString(1, defaul.getNombre());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			p.close();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO permisoscargos (cargo, manejodeproductos, infoproductos, facturarcompra, infofactura, facturaporcredito, manejopromociones, accesomodulorrhh, accesomodulogastos, accesomodulohistorial, accesomoduloconfiguracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			p.setInt(1, misCargos.indexOf(defaul)+1);
			p.setBoolean(2, defaul.isManejodeproductos());
			p.setBoolean(3, defaul.isInfoproductos());
			p.setBoolean(4, defaul.isFacturarcompra());
			p.setBoolean(5, defaul.isInfofactura());
			p.setBoolean(6, defaul.isFacturaporcredito());
			p.setBoolean(7, defaul.isManejopromociones());
			p.setBoolean(8, defaul.isAccesomodulorrhh());
			p.setBoolean(9, defaul.isAccesomodulogastos());
			p.setBoolean(10, defaul.isAccesomodulohistorial());
			p.setBoolean(11, defaul.isAccesomoduloconfiguracion());
			
			p.executeUpdate();
			
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}
	
	public void guardarNuevoPagoDeuda(Factura fac)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		float nuevaDeuda = 0;
		int index = misFacturas.indexOf(fac)+1;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO pagosfacturacreditocliente (factura, montopagado, fechadelpago) VALUES (?, ?, ?)");
			p.setInt(1, misFacturas.indexOf(fac)+1);
			p.setFloat(2, fac.getMontoDelUltimoPago());
			p.setDate(3, (java.sql.Date.valueOf(fac.getFechaDelUltimoPago())));
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			p.close();
			
			nuevaDeuda = fac.getAdeudado();
			
			p = (PreparedStatement) c.prepareStatement("UPDATE facturacreditocliente SET adeudado = '"+nuevaDeuda+"' WHERE factura = '"+index+"'");
			
			p.executeUpdate();
			
			p.close();
			
			if(nuevaDeuda <= 0)
			{
				String estado = "Saldada";
				
				p = (PreparedStatement) c.prepareStatement("UPDATE facturas SET estado = '"+estado+"' WHERE idfacturas = '"+index+"'");
				
				p.executeUpdate();
			}
			
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}
	
	public void guardarUsuarioSQL(Usuario usu)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO usuarios (usuario, empleado) VALUES (?, ?)");
			p.setString(1, usu.getUsuario());
			System.out.println(Controladora.getInstance().getMisEmpleados().indexOf(usu.getEmpleado())+1);
			p.setInt(2, Controladora.getInstance().getMisEmpleados().indexOf(usu.getEmpleado())+1);
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			p.close();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO usuariocontrasena (usuario, contrasena) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisUsuarios().indexOf(usu)+1);
			p.setString(2, usu.getContrasena());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			p.close();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO usuariocargo (usuario, cargo) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisUsuarios().indexOf(usu)+1);
			p.setInt(2, misCargos.indexOf(usu.getCargo())+1);
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}
	
	public void guardarImagenProductoSQL(byte[] foto, Producto prod) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO imagenproducto (producto, imagen) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(prod)+1);
			p.setBytes(2, foto);
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}
	
	public void guardarClienteCreditoSQL(Cliente cliente, float credito) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO clientecredito (cliente, credito, fecha) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisClientes().indexOf(cliente)+1);
			p.setFloat(2, credito);
			p.setDate(3, java.sql.Date.valueOf(LocalDate.now())); 
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}
	
	public void guardarClienteDeudaSQL(Cliente cliente) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO clientedeuda (cliente, deuda, fecha) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisClientes().indexOf(cliente)+1);
			p.setFloat(2, cliente.getDeuda());
			p.setDate(3, java.sql.Date.valueOf(LocalDate.now())); 
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}

	public void addEmpleado(Empleado e) {
		misEmpleados.add(e);
		
		guardarEmpleadoSQL(e);
	}
	public void guardarEmpleadoSQL(Empleado e) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO empleados (nombre, telefono, domicilio, correo, rnc, sueldo, categoria, codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			s = (Statement) c.createStatement();	
			r = s.executeQuery("SELECT idcategoriaempleado FROM categoriaempleado WHERE nombre = '"+e.getCategoria().getNombre()+"'");
			System.out.println(e.getCategoria().getNombre());
			int categoriaid = 0;
			while(r.next())
			{
				categoriaid = r.getInt(1);
			}
			System.out.println(categoriaid);
			p.setString(1, e.getNombre());
			p.setString(2, e.getTelefono());
			p.setString(3, e.getDomicilio());
			p.setString(4, e.getCorreo());
			p.setString(5, e.getRnc());
			p.setFloat(6, e.getSueldo());
			p.setInt(7, categoriaid);
			p.setString(8, e.getCodigo());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void addProveedor(Proveedores p) {
		misProveedores.add(p);
		
		guardarProveedorSQL(p);
	}
	public void addDescuento(DescuentosAutomaticos d) {
		misDescuentos.add(d);
		
		guardarDescuentosAutomaticsSQL(d);
	}
	public void addPromocion(Promocion p) {
		misPromociones.add(p);
		
		guardarPromocionSQL(p);
	}
	public void addRubro(Rubro r) {
		misRubros.add(r);
		
		guardarRubroSQL(r);
	}
	
	public void addCategoriaEmpleado(CategoriaEmpleado c)
	{
		misCategoriasEmpleado.add(c);
		try {
			guardarCategoriaEmpleadoSQL(c);
		}catch(Exception e) {
			Controladora.getInstance().print_exception(e);
		}
	}
	
	public void addGastoGeneral(GastoGeneral g)
	{
		misGastosGenerales.add(g);
		
		guardarGastoGeneralSQL(g);
	}
	

	public void guardarGastoGeneralSQL(GastoGeneral g) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO gastosgenerales (nombre, descripcion, modificado, gasto) VALUES (?, ?, ?, ?)");
			p.setString(1, g.getNombre());
			p.setString(2, g.getDescripcion());
			p.setDate(3, (java.sql.Date.valueOf(g.getRemodelado())));
			p.setFloat(4, g.getPrecioUnitario());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
		
	}

	public void guardarCategoriaEmpleadoSQL(CategoriaEmpleado categoria) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO categoriaempleado (nombre, sueldo) VALUES (?, ?)");
			p.setString(1, categoria.getNombre());
			p.setFloat(2, categoria.getSueldo());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}

	public void guardarRubroSQL(Rubro rubro) {
		
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO rubros (codigo, nombre) VALUES (?, ?)");
			p.setString(1, rubro.getCodigo());
			p.setString(2, rubro.getNombreRubro());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}

	public void guardarProveedorSQL(Proveedores proveedor)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO proveedores (saldo, domicilio, correo, rnc, rubro, sitioweb, nombre, telefono, codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			p.setFloat(1, proveedor.getSaldo());
			p.setString(2, proveedor.getDomicilio());
			p.setString(3, proveedor.getCorreo());
			p.setString(4, proveedor.getRnc());
			if(proveedor.getRubro() != null)
			{
				s = (Statement) c.createStatement();
				r = s.executeQuery("SELECT idrubros FROM rubros WHERE nombre '"+proveedor.getRubro().getNombreRubro()+"'");
				while(r.next())
				{
					int rubroid = r.getInt(1);
					p.setInt(5, rubroid);
				}
				
			}
			else
			{
				p.setNull(5, java.sql.Types.BIGINT);
			}
			p.setString(6, proveedor.getSitioWeb());
			p.setString(7, proveedor.getNombre());
			p.setString(8, proveedor.getTelefono());
			p.setString(9, proveedor.getCodigo());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
	}

	public void guardarPrecioSQL(Precio precio) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO precio (precio, descripcion, fecha, porc_ganancia, itbis) VALUES (?, ?, ?, ?, ?)");
			p.setFloat(1, precio.getPrecio());
			p.setString(2, precio.getDescripcion());
			p.setDate(3, (java.sql.Date.valueOf(precio.getFecha())));
			p.setInt(4, precio.getPorc_ganancia());
			p.setInt(5, precio.getItbis());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
				Controladora.getInstance().print_exception(e2);
			}
		}
	}

	public void guardarAreaSQL(Area area) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO area (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
			p.setString(1, area.getCategoria());
			p.setString(2, area.getNombre());
			p.setString(3, area.getAbreviatura());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
				Controladora.getInstance().print_exception(e2);
			}
		}
	}

	public void guardarVolumenSQL(Volumen volumen) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO volumen (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
			p.setString(1, volumen.getCategoria());
			p.setString(2, volumen.getNombre());
			p.setString(3, volumen.getAbreviatura());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void guardarMasaSQL(Masa masa) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO masa (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
			p.setString(1, masa.getCategoria());
			p.setString(2, masa.getNombre());
			p.setString(3, masa.getAbreviatura());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarLongitudSQL(Longitud longitud) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO longitud (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
			p.setString(1, longitud.getCategoria());
			p.setString(2, longitud.getNombre());
			p.setString(3, longitud.getAbreviatura());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarPromocionSQL(Promocion promocion) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			if(promocion.getDia() != null)
			{
				p = (PreparedStatement)
						c.prepareStatement("INSERT INTO promocion (porcentajeDescuento, nombre, dia) VALUES (?, ?, ?)");
				p.setInt(1, promocion.getPorcentajeDescuento());
				p.setString(2, promocion.getNombre());
				p.setString(3, promocion.getDia());
			}
			else {
				p = (PreparedStatement)
						c.prepareStatement("INSERT INTO promocion (porcentajeDescuento, nombre, fechaInicio, fechaFinal, horaInicio, horaFinal) VALUES (?, ?, ?, ?, ?, ?)");
				p.setInt(1, promocion.getPorcentajeDescuento());
				p.setString(2, promocion.getNombre());
				p.setDate(3, java.sql.Date.valueOf(promocion.getFechaInicio()));
				p.setDate(4, java.sql.Date.valueOf(promocion.getFechaFinal()));
				p.setTime(5, java.sql.Time.valueOf(promocion.getHoraInicio()));
				p.setTime(6, java.sql.Time.valueOf(promocion.getHoraFinal()));
			}
			
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void setPromocionBorrado(int index) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE promocion SET borrado = 1 WHERE idpromocion = '"+index+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void guardarFacturaSQL(Factura factura, String tipoFactura) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			if(factura.getMiCliente() != null) {
				p =(PreparedStatement)
						c.prepareStatement("INSERT INTO facturas (cliente, montototal, tipopago, montorecibido, cambio, fecha, hora, tipofactura, cantcopias, estado, codigo, usuariofacturador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				p.setInt(1, Controladora.getInstance().getMisClientes().indexOf(factura.getMiCliente())+1);
				p.setFloat(2, factura.getMontoTotal());
				p.setString(3, factura.getTipoPago());
				p.setFloat(4, factura.getMontoRecibido());
				p.setFloat(5, factura.getCambio());
				p.setDate(6, java.sql.Date.valueOf(factura.getFecha()));
				p.setTime(7, java.sql.Time.valueOf(factura.getHora()));
				p.setString(8, tipoFactura);
				p.setInt(9, factura.getCantcopias());
				p.setString(10, factura.getEstado());
				p.setString(11, factura.getCodigo());
				p.setInt(12, getMisUsuarios().indexOf(factura.getUsuarioFacturador())+1);
			}
			else {
				p = (PreparedStatement)
					c.prepareStatement("INSERT INTO facturas (montototal, tipopago, montorecibido, cambio, fecha, hora, tipofactura, cantcopias, estado, codigo, usuariofacturador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				p.setFloat(1, factura.getMontoTotal());
				p.setString(2, factura.getTipoPago());
				p.setFloat(3, factura.getMontoRecibido());
				p.setFloat(4, factura.getCambio());
				p.setDate(5,  java.sql.Date.valueOf(factura.getFecha()));
				p.setTime(6, java.sql.Time.valueOf(factura.getHora()));
				p.setString(7, tipoFactura);
				p.setInt(8, factura.getCantcopias());
				p.setString(9, factura.getEstado());
				p.setString(10, factura.getCodigo());
				p.setInt(11, getMisUsuarios().indexOf(factura.getUsuarioFacturador())+1);
			}
			
			
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarProductosFacturadosSQL(CantProductosUtilizados cantproductosutilizados, Factura factura) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO productosfacturados (idcantprodutil, idfactura) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisCantProductosUtilizados().indexOf(cantproductosutilizados)+1);
			p.setInt(2, Controladora.getInstance().getMisFacturas().indexOf(factura)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarKitsUtilizadosSQL(CantKitsUtilizados cantkitutilizados) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO cantkitsutilizados (kit, cantidad) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosKit().indexOf(cantkitutilizados.getKit())+1);
			p.setInt(2, Math.round(cantkitutilizados.getCantidad()));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarKitsFacturadosSQL(CantKitsUtilizados cantkitutilizados, Factura factura) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO kitsfacturados (idcantkitutil, idfactura) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosKit().indexOf(cantkitutilizados.getKit())+1);
			p.setInt(2, Controladora.getInstance().getMisFacturas().indexOf(factura)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarServiciosFacturadosSQL(Servicio servicio, Factura factura) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO serviciosfacturado (idservicio, idfactura) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosServicio().indexOf(servicio)+1);
			p.setInt(2, Controladora.getInstance().getMisFacturas().indexOf(factura)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarDescuentosAutomaticsSQL(DescuentosAutomaticos descuentosAutomaticos) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO descuentosAutomaticos (codigo, detalle, cantidadProducto, porcentajeDescuento, activo) VALUES (?, ?, ?, ?, ?)");
			p.setString(1, descuentosAutomaticos.getCodigo());
			p.setString(2, descuentosAutomaticos.getDetalle());
			p.setInt(3, descuentosAutomaticos.getCantidadProducto());
			p.setFloat(4, descuentosAutomaticos.getPorcentajeDescuento());
			p.setBoolean(5, descuentosAutomaticos.isActivo());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarUnidadMedidaAreaSQL(Producto producto, Area area) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO unidadmedidaarea (unidad, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisAreas().indexOf(area)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarUnidadMedidaLongitudSQL(Producto producto, Longitud longitud) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO unidadmedidaarea (unidad, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisLongitudes().indexOf(longitud)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarUnidadMedidaMasaSQL(Producto producto, Masa masa) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO unidadmedidaarea (unidad, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisMasas().indexOf(masa)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarUnidadMedidaVolumenSQL(Producto producto, Volumen volumen) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO unidadmedidaarea (unidad, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisVolumenes().indexOf(volumen)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarServiciosSQL(Producto producto, CategoriaEmpleado cat) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO servicios (producto, categoriaempleado) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.setInt(2, Controladora.getInstance().getMisCategoriasEmpleado().indexOf(cat)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarServiciosMaterialesSQL(Servicio servicio, CantProductosUtilizados cantproductosutilizados) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO serviciomateriales (servicio, cantproducto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosServicio().indexOf(servicio)+1);
			p.setInt(2, Controladora.getInstance().getMisCantProductosUtilizados().indexOf(cantproductosutilizados)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarRubroProductoSQL(Producto producto, Rubro rubro) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO rubroproducto (rubro, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisRubros().indexOf(rubro)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarProveedorPrincipalProductoSQL(Producto producto, Proveedores proveedor) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO proveedorprincipaproducto (proveedor, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProveedores().indexOf(proveedor)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarProveedoresSecProductoSQL(Producto producto, Proveedores proveedor) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO proveedoressecproducto (proveedor, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProveedores().indexOf(proveedor)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarPromoProductoSQL(Producto producto, Promocion promocion) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO promoproducto (producto, promocion, precioproducto) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.setInt(2, Controladora.getInstance().getMisPromociones().indexOf(promocion)+1);
			p.setFloat(3, ((100-promocion.getPorcentajeDescuento())*producto.getPrecioClass().getPrecio()) / 100);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarProductosSQL(Producto producto) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO productos (codigo, nombre, descripcion, tipoproducto, observaciones, unidadmedida, costo, costoitbis) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, producto.getCodigo());
			p.setString(2, producto.getNombre());
			p.setString(3, producto.getDescripcion());
			p.setString(4, producto.getTipoProducto());
			p.setString(5, producto.getObservaciones());
			if(producto.getUnidadMedida() == null) {
				p.setString(6, "");
			}
			else {
				p.setString(6, producto.getUnidadMedida().getNombre()); 
			}
			p.setFloat(7, producto.getCosto());
			p.setFloat(8, producto.getCostoitbis());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarPartidaSQL() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO partida () VALUES ()");
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarPrecioProductoSQL(Producto producto, Precio precio) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO precioproducto (precio, producto, activo) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisPrecios().indexOf(precio)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.setBoolean(3, precio.isPrecioActivo());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarPartidaProdutilSQL(Partida partida, CantProductosUtilizados cantproductosutilizados) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO partidaprodutil (partida, cantproductoutilizado) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisPartidas().indexOf(partida)+1);
			p.setInt(2, Controladora.getInstance().getMisCantProductosUtilizados().indexOf(cantproductosutilizados)+1);
			p.executeUpdate();
			System.out.println("Size de MisCantProductosUtilizados: " + getMisCantProductosUtilizados().size());
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarMatrizSQL(Estandar estandar, Combinaciones combinacion) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO matriz (prodestandar, combinacion, existactual) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosEstandar().indexOf(estandar)+1);
			p.setInt(2, Controladora.getInstance().getMisCombinaciones().indexOf(combinacion)+1);
			p.setFloat(3, combinacion.getExistenciaActual());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarKitProductosSQL(Kit kit, CantProductosUtilizados cantproductosutilizados) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO kitproductos (kit, cantidadproducto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosKit().indexOf(kit)+1);
			p.setInt(2, Controladora.getInstance().getMisCantProductosUtilizados().indexOf(cantproductosutilizados)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarKitSQL(Kit kit) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO kit (producto, exisminima, exismaxima, exisactual, exisinicial) VALUES (?, ?, ?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(kit)+1);
			p.setFloat(2, kit.getExistenciaMinima());
			p.setFloat(3, kit.getExistenciaMaxima());
			p.setFloat(4, kit.getExistenciaActual());
			p.setFloat(5, kit.getExistenciaInicial());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarGrupoAtributoSQL(GrupoAtributo grupoatributo) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO grupoatributo (nombre) VALUES (?)");
			p.setString(1, grupoatributo.getNombre());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarEstandarSQL(Estandar estandar) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO estandar (producto, existmin, existmax, fechavencimiento, costocompra, fabricado, existactual, existinicial, manodeobra) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(estandar)+1);
			p.setInt(2, Math.round(estandar.getExistenciaMinima()));
			p.setInt(3, Math.round(estandar.getExistenciaMaxima()));
			p.setDate(4, (java.sql.Date) estandar.getFechaVencimiento());
			p.setFloat(5, estandar.getCostoDeCompra());
			p.setBoolean(6, estandar.isFabricado());
			p.setInt(7, Math.round(estandar.getExistenciaActual()));
			p.setInt(8, Math.round(estandar.getExistenciaInicial()));
			p.setFloat(9, estandar.getManodeobra());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarDescProductoSQL(Producto producto, DescuentosAutomaticos descuentoautomatico) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO descproducto (descuento, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisDescuentos().indexOf(descuentoautomatico)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarCostoIndirectoSQL(Producto producto, CostoIndirectoProducto costoIndirecto) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO costoindirecto (gastogeneral, producto, costo) VALUES (?, ?, ?)");
			GastoGeneral gast = buscarGasto(costoIndirecto.getNombre());
			p.setInt(1, Controladora.getInstance().getMisGastosGenerales().indexOf(gast)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.setFloat(3, costoIndirecto.getValor());
				p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarProductoPartida(Producto producto, Partida partida) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO productopartida (producto, partida) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.setInt(2, Controladora.getInstance().getMisPartidas().indexOf(partida)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		
	public void guardarCombinacionesAtributosSQL(Atributos atributo, Combinaciones combinaciones) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO combinacionesatributos (atributo, combinacion) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisAtributos().indexOf(atributo)+1);
			p.setInt(2, Controladora.getInstance().getMisCombinaciones().indexOf(combinaciones)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarCombinacionesSQL(Combinaciones comb) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO combinaciones (numserie) VALUES (?)");
			
			p.setString(1, comb.getNumeroSerie());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarCantProductosUtilizadosSQL(Estandar estandar, CantProductosUtilizados cantproductosutilizados) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO cantproductosutilizados (estandar, cantidad) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosEstandar().indexOf(estandar)+1);
			p.setFloat(2, cantproductosutilizados.getCantidad());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarAtributoSQL(Atributos atributo) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO atributos (nombre, grupoatributo) VALUES (?, ?)");
			p.setString(1, atributo.getNombre());
			p.setInt(2, Controladora.getInstance().getMisGrupoAtributo().indexOf(atributo.getGrupoAtributo())+1);
			System.out.println("El indice es: " + Controladora.getInstance().getMisGrupoAtributo().indexOf(atributo.getGrupoAtributo()));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarManoDeObraSQL(ManoDeObra manodeobra) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO manodeobra (costo, cantidadhoras, fecha) VALUES (?, ?, ?)");
			p.setFloat(1, manodeobra.getCosto());
			p.setFloat(2, manodeobra.getCantidadHoras());
			p.setDate(3, manodeobra.getDate());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarManoDeObraProductoSQL(Estandar estandar, ManoDeObra manodeobra, CategoriaEmpleado categoriaempleado) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO manodeobraproducto (manodeobra, estandar, categoriaempleado) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisManosDeObras().indexOf(manodeobra)+1);
			p.setInt(2, Controladora.getInstance().getMisProductosEstandar().indexOf(estandar)+1);
			p.setInt(3, Controladora.getInstance().getMisCategoriasEmpleado().indexOf(categoriaempleado)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public void guardarManoDeObraServicioSQL(Servicio servicio, ManoDeObra manodeobra, CategoriaEmpleado categoriaempleado) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO manodeobraservicio (manodeobra, servicio, categoriaempleado) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisManosDeObras().indexOf(manodeobra)+1);
			p.setInt(2, Controladora.getInstance().getMisProductosServicio().indexOf(servicio)+1);
			p.setInt(3, Controladora.getInstance().getMisCategoriasEmpleado().indexOf(categoriaempleado)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarInfoEmpresaSQL(Empresa empresa) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			if(empresa.getITBIS() != 18) {
				p = (PreparedStatement)
					c.prepareStatement("INSERT INTO infoempresa (nombre, rnc, telefono, domicilio, itbis, limitemontocajachica) VALUES (?, ?, ?, ?, ?, ?)");
				p.setInt(6, empresa.getITBIS());
			}else {
				p = (PreparedStatement)
						c.prepareStatement("INSERT INTO infoempresa (nombre, rnc, telefono, domicilio, limitemontocajachica) VALUES (?, ?, ?, ?, ?)");
			}
			p.setString(1, empresa.getNombre());
			p.setString(2, empresa.getRnc());
			p.setString(3, empresa.getTelefono());
			p.setString(4, empresa.getDomicilio());
			p.setFloat(5, empresa.getCajaMaximo());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarRangoNumerosValorFiscal(Empresa empresa) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
				c.prepareStatement("INSERT INTO rangonumerosvalorfiscal (valorfiscalinferior, valorfiscalsuperior, fechasecsolicitada, fechasecvencimiento) VALUES (?, ?, ?, ?)");
		
			p.setInt(1, empresa.getValorFiscalInferior());
			p.setInt(2, empresa.getValorFiscalMayor());
			p.setDate(3, java.sql.Date.valueOf(empresa.getFechaSecSolicitada()));
			p.setDate(4, java.sql.Date.valueOf(empresa.getFechasecvencimiento()));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void guardarAnioFiscal(Empresa empresa) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
				c.prepareStatement("INSERT INTO aniofiscal (fechainicio, fechafinal) VALUES (?, ?)");
		
			p.setDate(1, java.sql.Date.valueOf(empresa.getFechaInicio()));
			p.setDate(2, java.sql.Date.valueOf(empresa.getFechaFinal()));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	

	public void addProducto(Producto p) {
		misProductos.add(p);
	}
	public void addProductoEstandar(Estandar e) {
		misProductosEstandar.add(e);
		
		guardarEstandarSQL(e);
	}
	public void addProductoKit(Kit k) {
		misProductosKit.add(k);
	}
	public void addProductoServicio(Servicio s) {
		misProductosServicio.add(s);
	}
	
	public void addGrupoAtributo(GrupoAtributo g) {
		misGrupoAtributo.add(g);
	}
	public void addAtributo(Atributos a) {
		misAtributos.add(a);
	}
	public void addUnidad(UnidadMedida u) {
		misUnidadMedida.add(u);
	}

	public void addPrecio(Precio p)
	{
		/*misPre.add(p);
		
		guardarCategoriaEmpleadoSQL(p);*/
	}
	
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}

	public ArrayList<Proveedores> getMisProveedores() {
		return misProveedores;
	}

	public ArrayList<DescuentosAutomaticos> getMisDescuentos() {
		return misDescuentos;
	}

	public ArrayList<Promocion> getMisPromociones() {
		return misPromociones;
	}

	public ArrayList<Rubro> getMisRubros() {
		return misRubros;
	}

	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}
	
	public ArrayList<GrupoAtributo> getMisGrupoAtributo() {
		return misGrupoAtributo;
	}
	
	public ArrayList<Atributos> getMisAtributos() {
		return misAtributos;
	}
	
	public ArrayList<Estandar> getMisProductosEstandar() {
		return misProductosEstandar;
	}

	public ArrayList<Kit> getMisProductosKit() {
		return misProductosKit;
	}

	public ArrayList<Servicio> getMisProductosServicio() {
		return misProductosServicio;
	}
	public ArrayList<UnidadMedida> getMisUnidadMedida() {
		return misUnidadMedida;
	}

	/**FUNCION PARA VERIFICAR UNA FAMILIA DE ATRIBUTO**/
	
	public boolean verificarFamiliaAtributo(String nombre) {
		boolean existe = false;
		if(!getMisGrupoAtributo().isEmpty()) {
			int i;
			for(i=0;i<getMisGrupoAtributo().size();i++)
			{
				if(nombre.equalsIgnoreCase(getMisGrupoAtributo().get(i).getNombre()) && existe == false)
				{
					existe = true;
				}
			}
		}
		
		return existe;
	}
	
	public GrupoAtributo buscarGrupoAtributo(String nombre) {
		GrupoAtributo grupoatributo = null;
		for(GrupoAtributo g : Controladora.getInstance().getMisGrupoAtributo()) {
			if(g.getNombre().equalsIgnoreCase(nombre)) {
				grupoatributo = g;
			}
		}
		return grupoatributo;
	}
	
	/**FUNCION PARA BUSCAR UN PRODUCTO**/
	
	public ArrayList<Producto> searchProducts(String buscador, String tipoBusqueda) {
		ArrayList<Producto> searchProducto = new ArrayList<>();
		switch(tipoBusqueda) {
			case "Codigo":
				for(Producto producto : Controladora.getInstance().getMisProductos()) {
					if(producto.getCodigo().toLowerCase().contains(buscador.toLowerCase())) {
						searchProducto.add(producto);
					}		
				}
				break;
			case "Nombre":
				for(Producto producto : Controladora.getInstance().getMisProductos()) {
					if(producto.getNombre().toLowerCase().contains(buscador.toLowerCase())) {
						searchProducto.add(producto);
					}
				}
				break;
			case "Descripcion":
				for(Producto producto : Controladora.getInstance().getMisProductos()) {
					if(producto.getDescripcion().toLowerCase().contains(buscador.toLowerCase())) {
						searchProducto.add(producto);
					}
				}					
				break;
			case "Proveedor":
				for(Producto producto : Controladora.getInstance().getMisProductos()) {
					if(producto.getProveedorPrinClass().getCodigo().toLowerCase().contains(buscador.toLowerCase())) {
						searchProducto.add(producto);
					}
				}
				break;
			case "Rubro":
				for(Producto producto : Controladora.getInstance().getMisProductos()) {
					if(producto.getRubroProductoClass().getCodigo().toLowerCase().contains(buscador.toLowerCase())) {
						searchProducto.add(producto);
					}
					
				}
				break;
		}
		
		return searchProducto;
	}
	
	public CategoriaEmpleado buscarCategoria(String cat)
	{
		CategoriaEmpleado c = null;
		int i;
		
		for(i = 0; i < getMisCategoriasEmpleado().size(); i++)
		{
			if(getMisCategoriasEmpleado().get(i).getNombre().equalsIgnoreCase(cat))
			{
				c = getMisCategoriasEmpleado().get(i);
				break;
			}
		}
		
		return c;
	}
	
	public boolean isCategoria(String nombre) {
		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
			if(c.getNombre().equalsIgnoreCase(nombre)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Estandar> searchProductsEstandar(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Estandar> searchProductoEstandar = new ArrayList<>();
		for(int i = 0; i < misProductosEstandar.size(); i++) {
			int boolCount = 0;
			j = 0;
			/**Con motivo de no repetir la misma funcion varias veces, se penso hacerlo un switch**/
			switch(tipoBusqueda) {
				case "Codigo":
					for(int k = 0; k < misProductosEstandar.get(i).getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Nombre":
					for(int k = 0; k < misProductosEstandar.get(i).getNombre().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
				case "Descripcion":
					for(int k = 0; k < misProductosEstandar.get(i).getDescripcion().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getDescripcion().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Proveedor":
					for(int k = 0; k < misProductosEstandar.get(i).getProveedorPrinClass().getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getProveedorPrinClass().getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Rubro":
					for(int k = 0; k < misProductosEstandar.get(i).getRubroProductoClass().getNombreRubro().length(); k++) {
						if(j < buscador.length()) {
							if(misProductosEstandar.get(i).getRubroProductoClass().getNombreRubro().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
			}
			if(boolCount == buscador.length()) {
				searchProductoEstandar.add(misProductosEstandar.get(i));
			}
		}
		return searchProductoEstandar;
	}
	

/**FUNCION PARA BUSCAR PROVEEDOR**/
	
	public ArrayList<Proveedores> searchProveedores(String buscador, String tipoBusqueda){
		ArrayList<Proveedores> searchProveedor = new ArrayList<>();
		switch(tipoBusqueda) {
			case "Codigo":
				for(Proveedores proveedor : Controladora.getInstance().getMisProveedores()) {
					if(proveedor.getCodigo().toLowerCase().contains(buscador)) {
						searchProveedor.add(proveedor);
					}				
				}
				break;
			case "Nombre":
				for(Proveedores proveedor : Controladora.getInstance().getMisProveedores()) {
					if(proveedor.getNombre().toLowerCase().contains(buscador)) {
						searchProveedor.add(proveedor);
					}
				}
				break;
			case "Rubro":
				for(Proveedores proveedor : Controladora.getInstance().getMisProveedores()) {
					if(proveedor.getRubro().getNombreRubro().toLowerCase().contains(buscador)) {
						searchProveedor.add(proveedor);
					}
				}
				break;
		}
		return searchProveedor;
	}
	
/**FUNCION PARA BUSCAR CLIENTES**/
	
	public ArrayList<Cliente> searchClientes(String buscador, String tipoBusqueda){
		ArrayList<Cliente> searchCliente = new ArrayList<>();
		switch(tipoBusqueda) {
			case "Codigo":
				for(Cliente cliente : Controladora.getInstance().getMisClientes()) {
					if(cliente.getCodigo().toLowerCase().contains(buscador)) {
						searchCliente.add(cliente);
					}
				}			
				break;
			case "Nombre":
				for(Cliente cliente : Controladora.getInstance().getMisClientes()) {
					if(cliente.getNombre().toLowerCase().contains(buscador)) {
						searchCliente.add(cliente);
					}
				}
				break;
		}
		return searchCliente;
	}
	
/**FUNCION PARA BUSCAR EMPLEADOS**/
	
	public ArrayList<Empleado> searchEmpleados(String buscador, String tipoBusqueda){
		ArrayList<Empleado> searchEmpleado = new ArrayList<>();
		switch(tipoBusqueda) {
			case "Codigo":
				for(Empleado empleado : Controladora.getInstance().getMisEmpleados()) {
					if(empleado.getCodigo().toLowerCase().contains(buscador)) {
						searchEmpleado.add(empleado);
					}
				}
				break;
			case "Nombre":
				for(Empleado empleado : Controladora.getInstance().getMisEmpleados()) {
					if(empleado.getNombre().toLowerCase().contains(buscador)) {
						searchEmpleado.add(empleado);
					}
				}
				break;
		}		
		return searchEmpleado;
	}
	
	public Rubro buscarRubro(String nombre)
	{
		Rubro r = null;
		int i = 0;
		boolean encontrado = false;
		
		while(i<getMisRubros().size() && !encontrado)
		{
			if(getMisRubros().get(i).getNombreRubro().equalsIgnoreCase(nombre))
			{
				r = getMisRubros().get(i);
				encontrado = true;
			}
			
			i++;
		}
		
		return r;
	}
	
	/**FUNCION PARA BUSCAR RUBROS**/
	public ArrayList<Rubro> searchRubro(String buscador, String tipoBusqueda){
		ArrayList<Rubro> searchRubro = new ArrayList<>();
		switch(tipoBusqueda) {
			case "Codigo":
				for(Rubro rubro : Controladora.getInstance().getMisRubros()) {
					if(rubro.getCodigo().toLowerCase().contains(buscador)) {
						searchRubro.add(rubro);
					}
				}
				break;
			case "Nombre":
				for(Rubro rubro : Controladora.getInstance().getMisRubros()) {
					if(rubro.getNombreRubro().toLowerCase().contains(buscador)) {
						searchRubro.add(rubro);
					}
				}
				break;
			}		
		return searchRubro;
	}
	
	public ArrayList<Usuario> searchUsuarios(String username){
		ArrayList<Usuario> searchUsuario = new ArrayList<>();
		System.out.println(username);
		for(Usuario usuario : Controladora.getInstance().getMisUsuarios()) {
			if(usuario.getUsuario().toLowerCase().contains(username)) {
				searchUsuario.add(usuario);
			}
		}
		return searchUsuario;
	}
	
	public Empleado buscarEmpleado(String codigo)
	{
		Empleado emp = null;
		
		for (Empleado empleado : misEmpleados) {
			if(empleado.getCodigo().equalsIgnoreCase(codigo))
			{
				emp = empleado;
			}
		}
		
		return emp;
	}
	
	public Cargo buscarCargo(String nombre)
	{
		Cargo cargo = null;
				
		for (Cargo car : misCargos) {
			if(car.getNombre().equalsIgnoreCase(nombre))
			{
				cargo = car;
			}
		}
		
		return cargo;
	}
	
	public void sendUnidadesIntoDatabase() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			for(UnidadMedida a : Controladora.getInstance().getMisUnidadMedida()) {
				if(a.getCategoria().equalsIgnoreCase("area")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO area (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
				if(a.getCategoria().equalsIgnoreCase("masa")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO masa (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
				if(a.getCategoria().equalsIgnoreCase("volumen")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO volumen (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
				if(a.getCategoria().equalsIgnoreCase("longitud")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO longitud (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
			
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void sendMasaIntoDatabase() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			for(UnidadMedida a : Controladora.getInstance().getMisUnidadMedida()) {
				if(a.getCategoria().equalsIgnoreCase("masa")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO masa (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				if(c!=null) {
					c.close();
				}
					
				if(s!=null) {
					s.close();
				}
					
				if(r!=null) {
					r.close();
				}
					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void sendLongitudIntoDatabase() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			for(UnidadMedida a : Controladora.getInstance().getMisUnidadMedida()) {
				if(a.getCategoria().equalsIgnoreCase("longitud")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO longitud (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				if(c!=null) {
					c.close();
				}
					
				if(s!=null) {
					s.close();
				}
					
				if(r!=null) {
					r.close();
				}
					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void sendAreaIntoDatabase() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			for(UnidadMedida a : Controladora.getInstance().getMisUnidadMedida()) {
				if(a.getCategoria().equalsIgnoreCase("area")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO area (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				if(c!=null) {
					c.close();
				}
					
				if(s!=null) {
					s.close();
				}
					
				if(r!=null) {
					r.close();
				}
					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void sendVolumenIntoDatabase() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			for(UnidadMedida a : Controladora.getInstance().getMisUnidadMedida()) {
				if(a.getCategoria().equalsIgnoreCase("volumen")) {
					p = (PreparedStatement)
							c.prepareStatement("INSERT INTO volumen (categoria, nombre, abreviatura) VALUES (?, ?, ?)");
					p.setString(1, a.getCategoria());
					p.setString(2, a.getNombre());
					p.setString(3, a.getAbreviatura());
					p.executeUpdate();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				if(c!=null) {
					c.close();
				}
					
				if(s!=null) {
					s.close();
				}
					
				if(r!=null) {
					r.close();
				}
					
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**FUNCION PARA VERIFICAR SI UN STRING DADO ES UN NUMERO**/
	
	public boolean isNumber(String string) {
		boolean numeric = true;
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
        	numeric = false;   
        }
       return numeric;
	}
	
	public boolean isFloat(String fullString, String character) {
		boolean numeric = true;
		int count = 0;
        try {
            Double.parseDouble(character);
        } catch (NumberFormatException e) {
        	numeric = false;
        	if(character.charAt(0) == '.') {
        		for(int i = 0; i < fullString.length(); i++) {
        			if(fullString.charAt(i) == '.') {
        				count++;
        			}	
        		}
        	}
        	if(count == 0 && character.equalsIgnoreCase(".")) {
        		numeric = true;
        	}
        	    
        }
       return numeric;
	}
	
	public double calcularPrecio(double costo, double porcientoGanancia, double impuesto) {
		double precio = (costo * (porcientoGanancia/100)) + (costo * (impuesto/100));
		double precioTotal = costo+precio;		
		return precioTotal;
	}
	
	
	public String findPartidaNombre(String string) {
		String nombre = "";
		int i = 0;
		while(string.charAt(i) != '[') {
    		nombre += string.charAt(i);
    		i++;
    	} 
		return nombre;
	}
	
	public String findPartidaCantidad(String string) {
		int i = 0;
		int check = 0;
		String cantidad = "";
		while(i < string.length()) {
    		if(string.charAt(i) == ':') {
    			check++;
    		}
    		if(string.charAt(i) == '(') {
    			check++;
    		}
    		if(check == 2 && string.charAt(i) != ' ' && string.charAt(i) != ':' && string.charAt(i) != ']') {
    			cantidad += string.charAt(i);
    		}
    		i++;
    	}
		System.out.println(cantidad);
		return cantidad;
	}
	
	public String findPartidaCosto(String string) {
		String partida = "";
		int i = 0;
		int check = 0;
		while(i < string.length()) {
			 if(Character.isDigit(string.charAt(i)) && check == 0) {
				 check++;
				 partida += string.charAt(i);
			 }
			 else if((Character.isDigit(string.charAt(i)) || string.charAt(i) == '.') && check == 1) {
				 partida += string.charAt(i);
			 }
			 else if(Character.isLetter(string.charAt(i)) && check == 1) {
				 check++;
			 }
			 i++;
		}
		return partida;
	}
	
	public String findEncargadoNombre(String string) {
		try {
			if(string.contains(":")) {
				String nombre = "";
				int i = 0;
				while(string.charAt(i) != ':') {
					nombre += string.charAt(i);
					i++;
				}
				return nombre;
			}
			else {
				return string;
			}
		}catch(NullPointerException e) {
			return "";
		}
		
		
	}
	
	public String findFacturaNombre(String string) {
		String nombre = "";
		int i = 0;
		boolean check = true;
		while(check) {
			if(string.charAt(i) == ':' || string.charAt(i) == ',') {
				check = false;
			}
			if(check) {
				nombre += string.charAt(i);
			}
			
			i++;
		}
		return nombre;
	}
	
	public String findFacturaCosto(String string) {
		String costo = "";
		int i = 0;
		boolean check = false;
		while(i < string.length()) {
			if(check && (Character.isDigit(string.charAt(i)) || string.charAt(i) == '.' )) {
				costo += string.charAt(i);
			}
			if(string.charAt(i) == ':') {
				check = true;
			}
			if(check && string.charAt(i) == '(') {
				check = false;
			}
			i++;
		}
		return costo;
	}
	
	public String findFacturaCostoConvertido(String string) {
		String costoConvertido = "";
		int i = 0;
		boolean check = false;
		while(i < string.length()) {
			if(check && (Character.isDigit(string.charAt(i)) || string.charAt(i) == '.')) {
				costoConvertido += string.charAt(i);
			}
			if(string.charAt(i) == '(') {
				check = true;
			}
			i++;
		}
		return costoConvertido;
	}
	
	public String findFacturaNumeroSerie(String string) {
		int i = 0;
		String numeroSerie = "";
		boolean check = true;
		try {
			while(string.charAt(i) != ')') {
				if(string.charAt(i) == '(') {
					check = false;
				}
				if(!check && string.charAt(i) != ')' && string.charAt(i) != '(') {
					//System.out.println(numeroSerie);
					numeroSerie += string.charAt(i);
				}
				i++;
			}
		}catch(NullPointerException e) {
			return "";
		}
		
		//System.out.println("El numero de serie es: " + numeroSerie);
		return numeroSerie;
	}
	
	public GastoGeneral buscarGasto(String g)
	{
		GastoGeneral encontrado = null;
		int i = 0;
		
		while(i < misGastosGenerales.size() || encontrado == null)
		{
			if(getMisGastosGenerales().get(i).getNombre().equals(g) && !getMisGastosGenerales().get(i).isBorrado()) 
			{
				return encontrado;
			}
			
			i++;
		}
		
		return null;
	}
	
	public Producto buscarProducto(String p)
	{
		Producto encontrado = null;
		int i = 0;
		
		while(i < getMisProductos().size() || encontrado == null)
		{
			System.out.println("El indice es: " + i);
			System.out.println("El size de los productos es " + getMisProductos().size());
			if(!Controladora.getInstance().getMisProductos().get(i).isBorrado()) {
				if(p.equalsIgnoreCase(Controladora.getInstance().getMisProductos().get(i).getNombre()))
				{
					encontrado = getMisProductos().get(i);
				}
			}
			
			i++;
		}
		
		return encontrado;
	}
	
	public int getProductoIndice(Producto producto) {
		for(int i = 0; i < Controladora.getInstance().getMisProductos().size(); i++) {
			Producto p = Controladora.getInstance().getMisProductos().get(i);
			if(p.getCodigo().equalsIgnoreCase(producto.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getProductoEstandarIndice(Producto producto) {
		for(int i = 0; i < Controladora.getInstance().getMisProductosEstandar().size(); i++) {
			Producto p = Controladora.getInstance().getMisProductosEstandar().get(i);
			if(p.getCodigo().equalsIgnoreCase(producto.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getProductoKitIndice(Producto producto) {
		for(int i = 0; i < Controladora.getInstance().getMisProductosKit().size(); i++) {
			Producto p = Controladora.getInstance().getMisProductosKit().get(i);
			if(p.getCodigo().equalsIgnoreCase(producto.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getProductoMatrizIndice(Producto producto) {
		for(int i = 0; i < Controladora.getInstance().getMisProductosMatriz().size(); i++) {
			Producto p = Controladora.getInstance().getMisProductosMatriz().get(i);
			if(p.getCodigo().equalsIgnoreCase(producto.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getProductoServicioIndice(Producto producto) {
		for(int i = 0; i < Controladora.getInstance().getMisProductosServicio().size(); i++) {
			Producto p = Controladora.getInstance().getMisProductosServicio().get(i);
			if(p.getCodigo().equalsIgnoreCase(producto.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public Cliente buscarCliente(String codigo) {
		Cliente encontrado = null;
		int i = 0;
		
		while(i < Controladora.getInstance().getMisClientes().size() || encontrado == null)
		{
			if(!Controladora.getInstance().getMisClientes().get(i).isBorrado()) {
				if(codigo.equalsIgnoreCase(Controladora.getInstance().getMisClientes().get(i).getCodigo()))
				{
					encontrado = Controladora.getInstance().getMisClientes().get(i);
				}
			}
			
			i++;
		}
		
		return encontrado;
	}
	
	public int getClienteIndice(Cliente cliente) {
		for(int i = 0; i < Controladora.getInstance().getMisClientes().size(); i++) {
			Cliente c = Controladora.getInstance().getMisClientes().get(i);
			if(c.getCodigo().equalsIgnoreCase(cliente.getCodigo()) && !c.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getProveedorIndice(Proveedores proveedor) {
		for(int i = 0; i < Controladora.getInstance().getMisProveedores().size(); i++) {
			Proveedores p = Controladora.getInstance().getMisProveedores().get(i);
			if(p.getCodigo().equalsIgnoreCase(proveedor.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getEmpleadoIndice(Empleado empleado) {
		for(int i = 0; i < Controladora.getInstance().getMisEmpleados().size(); i++) {
			Empleado e = Controladora.getInstance().getMisEmpleados().get(i);
			if(e.getCodigo().equalsIgnoreCase(empleado.getCodigo()) && !e.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getPromocionIndice(Promocion promocion) {
		/**for(int i = 0; i < Controladora.getInstance().getMisPromociones().size(); i++) {
			Promocion p = Controladora.getInstance().getMisPromociones().get(i);
			if(p.getCodigo().equalsIgnoreCase(promocion.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}**/
		return -1;
	}
	
	public int getRubroIndice(Rubro rubro) {
		for(int i = 0; i < Controladora.getInstance().getMisRubros().size(); i++) {
			Rubro r = Controladora.getInstance().getMisRubros().get(i);
			if(r.getCodigo().equalsIgnoreCase(rubro.getCodigo()) && !r.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getAtributoIndice(Atributos atributo) {
		for(int i = 0; i < Controladora.getInstance().getMisAtributos().size(); i++) {
			Atributos a = Controladora.getInstance().getMisAtributos().get(i);
			if(a.getNombre().equalsIgnoreCase(atributo.getNombre()) && !a.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getGrupoAtributoIndice(GrupoAtributo grupoAtributo) {
		for(int i = 0; i < Controladora.getInstance().getMisGrupoAtributo().size(); i++) {
			GrupoAtributo g = Controladora.getInstance().getMisGrupoAtributo().get(i);
			if(g.getNombre().equalsIgnoreCase(grupoAtributo.getNombre()) && !g.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getCategoriaEmpleadoIndice(CategoriaEmpleado categoriaEmpleado) {
		for(int i = 0; i < Controladora.getInstance().getMisCategoriasEmpleado().size(); i++) {
			CategoriaEmpleado c = Controladora.getInstance().getMisCategoriasEmpleado().get(i);
			if(c.getNombre().equalsIgnoreCase(categoriaEmpleado.getNombre()) && !c.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getManoDeObraIndice(ManoDeObra manoDeObra) {
		/**for(int i = 0; i < Controladora.getInstance().getMisManosDeObras().size(); i++) {
			ManoDeObra m = Controladora.getInstance().getMisManosDeObras().get(i);
			if(c.getNombre().equalsIgnoreCase(categoriaEmpleado.getNombre()) && !c.isBorrado()) {
				return i;
			}
		}**/
		return -1;
	}
	
	public int getPeticionIndice(Peticion peticion) {
		/**for(int i = 0; i < Controladora.getInstance().getMisPeticiones().size(); i++) {
			Peticion p = Controladora.getInstance().getMisPeticiones().get(i);
			if(p.getCodigo().equalsIgnoreCase(peticion.getCodigo()) && !p.isBorrado()) {
				return i;
			}
		}**/
		return -1;
	}
	
	public int getGastoGeneralIndice(GastoGeneral gastoGeneral) {
		for(int i = 0; i < Controladora.getInstance().getMisGastosGenerales().size(); i++) {
			GastoGeneral g = Controladora.getInstance().getMisGastosGenerales().get(i);
			if(g.getNombre().equalsIgnoreCase(gastoGeneral.getNombre()) && !g.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public int getCombinacionesIndice(Combinaciones combinacion) {
		for(int i = 0; i < Controladora.getInstance().getMisCombinaciones().size(); i++) {
			Combinaciones c = Controladora.getInstance().getMisCombinaciones().get(i);
			if(c.getNumeroSerie().equalsIgnoreCase(combinacion.getNumeroSerie()) && !c.isBorrado()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isRubroInProduct(Rubro rubro) {
		for(Producto p : Controladora.getInstance().getMisProductos()) {
			if(!p.isBorrado() && (p.getRubroProductoClass().equals(rubro))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean rubroCodeExists(Rubro rubro) {
		for(Rubro r : Controladora.getInstance().getMisRubros()) {
			if(r.getCodigo().equalsIgnoreCase(rubro.getCodigo())) {
				System.out.println(r.getCodigo());
				System.out.println(rubro.getCodigo());
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<GastoGeneral> getMisGastosGenerales() {
		return misGastosGenerales;
	}

	public void setMisGastosGenerales(ArrayList<GastoGeneral> misGastosGenerales) {
		this.misGastosGenerales = misGastosGenerales;
	}
	
	public ArrayList<GastoGeneral> searchGastoGeneral (String buscador)
	{
		int j = 0;
		ArrayList<GastoGeneral> searchGastoGeneral = new ArrayList<>();
		for(int i = 0; i < misGastosGenerales.size(); i++) {
			int boolCount = 0;
			j = 0;
			
			for(int k = 0; k < misGastosGenerales.get(i).getNombre().length(); k++) {
				if(j < buscador.length()) {
					if(misGastosGenerales.get(i).getNombre().charAt(k) == buscador.charAt(j)) {
						boolCount++;
					}
				}
				j++;
			}
			if(boolCount == buscador.length()) {
				searchGastoGeneral.add(misGastosGenerales.get(i));
			}
		}
		return searchGastoGeneral;
	}
	
	public float calcularCostos(GastoGeneral g, float precioEstimadoProducto)
	{
		float atribucion = 0;
		System.out.println(ventaPromedioMensual);
		if(precioEstimadoProducto > 0 && getVentaPromedioMensual()> 0)
		{
			float factor = precioEstimadoProducto/getVentaPromedioMensual();
			
			atribucion = g.getPrecioUnitario() * factor;
		}
		
		return atribucion;
	}

	public float getVentaPromedioMensual() {
		return ventaPromedioMensual;
	}

	public void setVentaPromedioMensual(float ventaPromedioAnual) {
		this.ventaPromedioMensual = ventaPromedioAnual;
	}
	
	public float calcularManoDeObra(String nombreCategoria, String tiempoMedida, float tiempoCantidad) {
		float costoManoObra = 0;
		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
			if(c.getNombre().equals(nombreCategoria)) {
				if(tiempoMedida.equalsIgnoreCase("Minutos")) {
					tiempoCantidad = tiempoCantidad / 60;
				}
				else if(tiempoMedida.equalsIgnoreCase("Segundos")) {
					tiempoCantidad = tiempoCantidad / 3600; 
				}
				costoManoObra = c.getSueldo() * tiempoCantidad;
			}
		}
		return costoManoObra;
	}
	
	public Peticion buscarPeticion(String codigo) {
		Peticion result = null;
		for(Peticion peticion : Controladora.getInstance().getMisPeticiones()) {
			if(peticion.getCodigo().equalsIgnoreCase(codigo)) {
				result = peticion;
			}
		}
		return result;
	}
	
	public boolean isAtributoInProduct(String atributo) {
		boolean result = false;
		for(Estandar estandar : Controladora.getInstance().getMisProductosEstandar()) {
			for(Combinaciones combinacion : estandar.getCombinaciones()) {
				for(Atributos a : combinacion.getListaAtributos()) {
					if(a.getNombre().equalsIgnoreCase(atributo) && !estandar.isBorrado()) {
						return true;
					}
				}
			}
		}
		return result;
	}
	
	public boolean isFamiliaInProduct(String familia) {
		boolean result = false;
		for(Estandar estandar : Controladora.getInstance().getMisProductosEstandar()) {
			for(Combinaciones combinacion : estandar.getCombinaciones()) {
				for(Atributos a : combinacion.getListaAtributos()) {
					if(a.getGrupoAtributo().getNombre().equalsIgnoreCase(familia) && !estandar.isBorrado()) {
						return true;
					}
				}
			}
		}
		return result;
	}
	
	public boolean isEmpleadoInUsuario(String codigo) {
		for(Usuario usuario : Controladora.getInstance().getMisUsuarios()) {
			if(usuario.getEmpleado().getCodigo().equalsIgnoreCase(codigo)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean empleadoRNCExists(Empleado empleado) {
		for(Empleado e : Controladora.getInstance().getMisEmpleados()) {
			if(e.getRnc().equals(empleado.getRnc()) && !e.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isClienteInFactura(Cliente cliente) {
		for(Factura factura : Controladora.getInstance().getMisFacturas()) {
			if(factura.getClienteCodigo().equalsIgnoreCase(cliente.getCodigo())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean clienteCodigoExists(Cliente cliente) {
		for(Cliente c : Controladora.getInstance().getMisClientes()) {
			if(c.getCodigo().equalsIgnoreCase(cliente.getCodigo()) && !c.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean clienteRNCExists(Cliente cliente) {
		for(Cliente c : Controladora.getInstance().getMisClientes()) {
			if(c.getRnc().equalsIgnoreCase(cliente.getRnc()) && !c.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isProveedorInProducto(Proveedores proveedor) {
		for(Producto producto : Controladora.getInstance().getMisProductos()) {
			if(producto.getProveedorPrinClass().equals(proveedor) && !producto.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean proveedorRNCExists(Proveedores proveedor) {
		for(Proveedores p : Controladora.getInstance().getMisProveedores()) {
			if(p.getRnc().equalsIgnoreCase(proveedor.getRnc()) && !p.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isCategoriaInProducto(CategoriaEmpleado categoria) {
		for(Estandar estandar : Controladora.getInstance().getMisProductosEstandar()) {
			if(estandar.getInfoManoDeObra().getCategoria().equals(categoria) && !estandar.isBorrado()) {
				return true;
			}
		}
		for(Estandar matriz : Controladora.getInstance().getMisProductosMatriz()) {
			if(matriz.getInfoManoDeObra().getCategoria().equals(categoria) && !matriz.isBorrado()) {
				return true;
			}
		}
		for(Servicio servicio : Controladora.getInstance().getMisProductosServicio()) {
			if(servicio.getInfoManoDeObra().getCategoria().equals(categoria) && !servicio.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Estandar> getProductsEstandarWithCategory(CategoriaEmpleado categoria){
		ArrayList<Estandar> productosEstandar = new ArrayList<>();
		for(Estandar estandar : Controladora.getInstance().getMisProductosEstandar()) {
			if(estandar.getInfoManoDeObra().getCategoria().equals(categoria) && !estandar.isBorrado()) {
				productosEstandar.add(estandar);
			}
		}
		for(Estandar matriz : Controladora.getInstance().getMisProductosMatriz()) {
			if(matriz.getInfoManoDeObra().getCategoria().equals(categoria) && !matriz.isBorrado()) {
				productosEstandar.add(matriz);
			}
		}
		return productosEstandar;
	}
	
	public ArrayList<Servicio> getProductsServicioWithCategory(CategoriaEmpleado categoria){
		ArrayList<Servicio> productosServicio = new ArrayList<>();
		for(Servicio servicio : Controladora.getInstance().getMisProductosServicio()) {
			if(servicio.getInfoManoDeObra().getCategoria().equals(categoria) && !servicio.isBorrado()) {
				productosServicio.add(servicio);
			}
		}
		return productosServicio;
	}
	
	public boolean isCategoryInEmpleado(CategoriaEmpleado categoria) {
		for(Empleado empleado : Controladora.getInstance().getMisEmpleados()) {
			if(empleado.getCategoria().equals(categoria) && !empleado.isBorrado()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Empleado> getEmpleadoWithCategory(CategoriaEmpleado categoria){
		ArrayList<Empleado> empleados = new ArrayList<>();
		for(Empleado empleado : Controladora.getInstance().getMisEmpleados()) {
			if(empleado.getCategoria().equals(categoria) && !empleado.isBorrado()) {
				empleados.add(empleado);
			}
		}
		return empleados;
	}
	
	public boolean validarNumeroSerie(String numeroSerie) {
		for(Estandar estandar : Controladora.getInstance().getMisProductosEstandar()) {
			for(Combinaciones c : estandar.getCombinaciones()) {
				if(c.getNumeroSerie().equalsIgnoreCase(numeroSerie) && !estandar.isBorrado()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean validarUsuario(String usuario, String password) {
		boolean check = false;
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		int cuenta = 0;
		int cuenta2 = 0;
		int idUsuario = 0;
		String passHash = DigestUtils.md5Hex(password);
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM usuarios WHERE usuario = '"+usuario+"' AND activo = 1");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
				
				c3 = con.conectar();
				
				s3 = (Statement) c3.createStatement();
				r3 = s3.executeQuery("SELECT * FROM usuarios WHERE usuario = '"+usuario+"' AND activo = 1");
				
				while(r3.next())
				{
					idUsuario = r3.getInt(1);
				}
			}
			
			c2 = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT COUNT(*) AS TOTAL FROM usuariocontrasena WHERE usuario = '"+idUsuario+"' AND contrasena = '"+passHash+"' AND activo = 1");
			
			while(r2.next())
			{
				cuenta2 = r2.getInt(1);
			}
			
			if(cuenta > 0 && cuenta2 > 0)
			{
				check = true;
			}
			
			if(!activarLoadUsuarios() && (usuario.equalsIgnoreCase("administrador") && password.equalsIgnoreCase("administrador")))
			{
				check = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		/*for(Usuario u : Controladora.getInstance().getMisUsuarios()) {
			if(u.getUsuario() == usuario && u.getContrasena() == password) {
				check = true;
			}
		}*/
		return check;
	}
	
	public Usuario buscarUsuario(String usuario, String password) {
		Usuario result = null;
		
		if(usuario.equals("root") && password.equals("root")) {
			System.out.println(usuario + ": " + password);
			result = new Usuario("root", null, true, "root", true, null);
		}
		else {
			for(Usuario u : Controladora.getInstance().getMisUsuarios()) {
				if(u.getUsuario().equalsIgnoreCase(usuario) && u.getContrasena().equals(password)) {
					result = u;
				}
			}
		}
		
		return result;
	}
	
	public Usuario buscarUsuario(String usuario) {
		for(Usuario u : Controladora.getInstance().getMisUsuarios()) {
			if(u.getUsuario().equalsIgnoreCase(usuario)) {
				return u;
			}
		}
		return null;
	}
	
	public boolean emptyProveedores()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM proveedores");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta == 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarloadMatriz()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		java.sql.Connection c2 = null;
		Statement s2 = null;
		ResultSet r2 = null;
		boolean activar = false;
		int cuenta = 0;
		int cuenta2 = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM productos");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			//Recuperar precios
			c2 = con.conectar();
					
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT COUNT(*) AS TOTAL FROM matriz");
					
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r2.next())
			{
				cuenta2 = r2.getInt(1);
			}
			
			if(cuenta > 0 && cuenta2 > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadMatriz()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		int id = 0;
		int idcombinacionAtrib = 0;
		int idatributo = 0;
		int idCombinacion = 0;
		ArrayList<Atributos> listaAtributos = null;
		int idMatriz = 0;
		int idEstandar = 0;
		int idCombinacion2 = 0;
		float existencia = 0;
		String numserie = null;
		Combinaciones comb = null;
		int idEstandarAnterior = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM combinaciones");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				listaAtributos = new ArrayList<>();
				id = r.getInt(1);
				numserie = r.getString(2);
				
				c2 = con.conectar();
				
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT * FROM combinacionesatributos WHERE combinacion = '"+id+"'");
				
				while(r2.next())
				{
					idcombinacionAtrib = r2.getInt(1);
					idatributo = r2.getInt(2);
					idCombinacion = r2.getInt(3);
					
					listaAtributos.add(getMisAtributos().get(idatributo-1));
				}
				c2.close();
				
				int i;
				
				for(i=0; i<listaAtributos.size(); i++)
				{
					System.out.println("Los atributos son: " + listaAtributos.get(i).getNombre());
				}
				
				c3 = con.conectar();
				
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s3 = (Statement) c3.createStatement();
				r3 = s3.executeQuery("SELECT * FROM matriz WHERE combinacion = '"+id+"'");
				
				while(r3.next())
				{
					idMatriz = r3.getInt(1);
					idEstandar = r3.getInt(2);
					idCombinacion2 = r3.getInt(3);
					existencia = r3.getFloat(4);
				}
				c3.close();
				
				comb = new Combinaciones(numserie, existencia, listaAtributos);
				
				getMisCombinaciones().add(comb);
				
				getMisProductosEstandar().get(idEstandar-1).agregarCombinacion(comb);
				
				listaAtributos = null;
				
				if(idEstandarAnterior != idEstandar)
				{
					getMisProductosMatriz().add(getMisProductosEstandar().get(idEstandar-1));
				}
				
				idEstandarAnterior = idEstandar;
				
			}
			
			int i;
			
			for (Estandar matriz : getMisProductosMatriz()) {
				System.out.println(matriz.getNombre());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadPrecio()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM precio");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadPrecio()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		int id = 0;
		float precio = 0;
		String descripcion = null;
		Date fecha = null;
		boolean activo = false;
		int porc_ganancia = 0;
		int itbis = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM precio");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				id = r.getInt(1);
				precio = r.getFloat(2);
				descripcion = r.getString(3);
				fecha = r.getDate(4);
				porc_ganancia = r.getInt(6);
				itbis = r.getInt(7);
				
				c2 = con.conectar();
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT activo FROM precioproducto WHERE precio = '"+id+"'");
				
				//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
				while(r2.next())
				{
					activo = r2.getBoolean(1);
				}
				
				Precio pre = new Precio(precio, descripcion, activo, porc_ganancia, itbis);
				pre.setFecha(LocalDate.parse(fecha.toString()));
				
				Controladora.getInstance().getMisPrecios().add(pre);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(c2!=null) {
					c2.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(s2!=null) {
					s2.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
				if(r2!=null) {
					r2.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadPromedioGananciaAnual()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM promediogananciaanual");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadCargosUsuariosDefaultSQL()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM cargos");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta == 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void desactivarPromedioGananciaAnualActual()
	{
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE promediogananciaanual SET activo = 0 WHERE activo = 1");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loadPromedioGananciaAnual()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int id = 0;
		float monto = 0;
		Date fecha = null;
		boolean activo = false;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM promediogananciaanual WHERE activo = 1");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				id = r.getInt(1);
				monto = r.getFloat(2);
				fecha = r.getDate(3);
				activo = r.getBoolean(4);
				
				Controladora.getInstance().setVentaPromedioMensual(monto);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadInfoEmpresa()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM infoempresa");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarUnidadMedidaMasa() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM masa");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarUnidadMedidaLongitud() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM longitud");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarUnidadMedidaArea() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM area");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarUnidadMedidaVolumen() {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM volumen");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadCliente()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM clientes");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadCajaChica()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM cajachica");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadCajaChica()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		float monto = 0;
		float montoAnteriorCaja = 0;
		float actualizacion = 0;
		String descripcion = null;
		int idUsu = 0;
		Usuario usu = null;
		Date fecha = null;
		ArrayList<TransaccionesCajaChica> trs = new ArrayList<>();
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM transaccionescajachica");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				montoAnteriorCaja = r.getFloat(2);
				actualizacion = r.getFloat(3);
				descripcion = r.getString(4);
				idUsu = r.getInt(5);
				fecha = r.getDate(6);
				
				usu = getMisUsuarios().get(idUsu-1);
				TransaccionesCajaChica tr = new TransaccionesCajaChica(actualizacion, descripcion, usu, LocalDate.parse(fecha.toString()));
				trs.add(tr);
				
			}
			
			c.close();
			
			c2 = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT * FROM cajachica");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r2.next())
			{
				monto = r2.getFloat(2);
			}
			CajaChica cj = new CajaChica(monto);
			cj.setTransacciones(trs);
			
			setCajaChica(cj);

			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadPeticiones()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM peticiones");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadCostoIndirecto()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM costoindirecto");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadCostoIndirecto()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		int id = 0;
		int idGastoGeneral = 0;
		int idProducto = 0;
		float costo = 0;
		String nombreprod = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM costoindirecto");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				id = r.getInt(1);
				idGastoGeneral = r.getInt(2);
				idProducto = r.getInt(3);
				costo = r.getFloat(4);
				
				c2 = con.conectar();
				
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT nombre FROM productos WHERE idproductos = '"+idProducto+"'");
				
				while(r2.next())
				{
					nombreprod = r2.getString(1);
				}
				
				CostoIndirectoProducto costoInd = new CostoIndirectoProducto(getMisGastosGenerales().get(idGastoGeneral-1).getNombre(), costo, null);
				
				getMisCostosIndirectos().add(costoInd);
				
				buscarProducto(nombreprod).getCostosIndirectos().add(costoInd);
				
				//getMisProductos().get(idProducto-1).getCostosIndirectos().add(costoInd);
				
				c2.close();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loadPromocion()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		int idpromocion = 0;
		int porcientoDescuento = 0;
		String nombre = null;
		Date fechaInicial = null;
		Date fechaFinal = null;
		Time horaInicio = null;
		Time horaFinal = null;
		String dia = null;
		boolean borrado = false;
		int idPromoProducto = 0;
		int idProducto = 0;
		int promocion = 0;
		float precioPromocion = 0;
		boolean borrado2 = false;
		ArrayList<Producto> productos = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM promocion");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				productos = new ArrayList<>();
				idpromocion = r.getInt(1);
				porcientoDescuento = r.getInt(2);
				nombre = r.getString(3);
				fechaInicial = r.getDate(4);
				fechaFinal = r.getDate(5);
				horaInicio = r.getTime(6);
				horaFinal = r.getTime(7);
				dia = r.getString(8);
				borrado = r.getBoolean(9);
				c2 = con.conectar();
				
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT * FROM promoproducto where promocion = '"+idpromocion+"'");
				
				while(r2.next())
				{
					idPromoProducto = r2.getInt(1);
					idProducto = r2.getInt(2);
					promocion = r2.getInt(3);
					precioPromocion = r2.getFloat(4);
					borrado2 = r2.getBoolean(5);
					
					System.out.println("Vuelta promocion con: " + idProducto + " Size de productos: " + Controladora.getInstance().getMisProductos().size());
					
					productos.add(Controladora.getInstance().getMisProductos().get(idProducto-1));
					System.out.println("Cruce la promo");
				}
				
				
				if(dia == null)
				{
					Promocion promo = new Promocion(porcientoDescuento, nombre, LocalDate.parse(fechaInicial.toString()), LocalDate.parse(fechaFinal.toString()), LocalTime.parse(horaInicio.toString()), LocalTime.parse(horaFinal.toString()));
					promo.setProductos(productos);
					promo.setBorrado(borrado);
					Controladora.getInstance().getMisPromociones().add(promo);
				}
				else
				{
					Promocion promo = new Promocion(porcientoDescuento, nombre, dia);
					promo.setProductos(productos);
					promo.setBorrado(borrado);
					System.out.println("El valor de borrado de esta promocion es: " + promo.isBorrado());
					Controladora.getInstance().getMisPromociones().add(promo);
				}
				
				productos = null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadClienteCredito()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM clientecredito");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadClienteCredito()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int id = 0;
		int idCliente = 0;
		float credito = 0;
		Date fecha = null;
		boolean borrado = false;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM clientecredito");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				id = r.getInt(1);
				idCliente = r.getInt(2);
				credito = r.getFloat(3);
				fecha = r.getDate(4);
				borrado = r.getBoolean(5);
				
				Cliente cli = getMisClientes().get(idCliente-1);
				
				cli.setCredito(credito);
				cli.setUltimaActualizacionCredito(LocalDate.parse(fecha.toString()));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadClienteDeuda()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM clientedeuda");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadClienteDeuda()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int id = 0;
		int idCliente = 0;
		float deuda = 0;
		Date fecha = null;
		boolean borrado = false;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM clientedeuda");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				id = r.getInt(1);
				idCliente = r.getInt(2);
				deuda = r.getFloat(3);
				fecha = r.getDate(4);
				borrado = r.getBoolean(5);
				
				Cliente cli = getMisClientes().get(idCliente-1);
				
				cli.setDeuda(deuda);
				cli.setUltimaActualizacionDeuda(LocalDate.parse(fecha.toString()));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loadInfoEmpresa()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		int idInfoEmpresa = 0;
		String nombre = null;
		String rnc = null;
		String telefono = null;
		String domicilio = null;
		int itbis = 0;
		boolean borrado = false;
		int valorfiscalinferior = 0;
		int valorfiscalsuperior = 0;
		Date fechasolicitada = null;
		Date fechaVencimiento = null;
		Date fechainicio = null;
		Date fechaFinal = null;
		boolean borradoAnioFiscal = false;
		float limiteMontoCajaChica = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM infoempresa");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				idInfoEmpresa = r.getInt(1);
				nombre = r.getString(2);
				rnc = r.getString(3);
				telefono = r.getString(4);
				domicilio = r.getString(5);
				itbis = r.getInt(6);
				limiteMontoCajaChica = r.getFloat(7);
				borrado = r.getBoolean(8);
				
			}
			
			if(activarLoadRangoNumeroValorFiscal())
			{
				c2 = con.conectar();
			
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT * FROM rangonumerosvalorfiscal");
				while(r2.next())
				{
					valorfiscalinferior = r2.getInt(2);
					valorfiscalsuperior = r2.getInt(3);
					fechasolicitada = r2.getDate(4);
					fechaVencimiento = r2.getDate(5);
				}
			}
			
			if(activarLoadAnioFiscal())
			{
				c3 = con.conectar();
				
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s3 = (Statement) c3.createStatement();
				r3 = s3.executeQuery("SELECT * FROM aniofiscal");
				while(r3.next())
				{
					fechainicio = r3.getDate(2);
					fechaFinal = r3.getDate(3);
					borradoAnioFiscal = r3.getBoolean(4);
				}
			}
			
			Empresa emp = null;
			
			if(activarLoadAnioFiscal() && activarLoadRangoNumeroValorFiscal())
			{
				emp = new Empresa(nombre, rnc, telefono, domicilio, valorfiscalinferior, valorfiscalsuperior, LocalDate.parse(fechasolicitada.toString()), LocalDate.parse(fechaVencimiento.toString()), LocalDate.parse(fechainicio.toString()), LocalDate.parse(fechaFinal.toString()), limiteMontoCajaChica);
			}
			else
			{
				emp = new Empresa(nombre, rnc, telefono, domicilio, limiteMontoCajaChica);
			}
			
			
			Controladora.getInstance().setMiEmpresa(emp);
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(c2!=null) {
					c2.close();
				}
				
				if(c3!=null) {
					c3.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(s2!=null) {
					s2.close();
				}
				
				if(s3!=null) {
					s3.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
				if(r2!=null) {
					r2.close();
				}
				
				if(r3!=null) {
					r3.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loadCliente()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM clientes");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String codigo = r.getString(2);
				String telefono = r.getString(3);
				Date cumpleanos = r.getDate(4);
				String rnc = r.getString(5);
				String nombre = r.getString(6);
				boolean borrado = r.getBoolean(7);
				
				Cliente cli = new Cliente(codigo, nombre, telefono, null, (java.sql.Date) cumpleanos, rnc);
				cli.setBorrado(borrado);
				
				Controladora.getInstance().getMisClientes().add(cli);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadCuentaBancaria()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM montocuentabancaria");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadRangoNumeroValorFiscal()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM rangonumerosvalorfiscal");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadAnioFiscal()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM aniofiscal");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadCuentaBancaria()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		float monto = 0;
		float montoAnteriorCuenta = 0;
		float actualizacion = 0;
		String descripcion = null;
		Date fecha = null;
		ArrayList<TransaccionesCuentaBanco> trs = new ArrayList<>();
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM transaccionescuentabancaria");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				montoAnteriorCuenta = r.getFloat(2);
				actualizacion = r.getFloat(3);
				descripcion = r.getString(4);
				fecha = r.getDate(5);
				
				TransaccionesCuentaBanco tr = new TransaccionesCuentaBanco(actualizacion, descripcion, LocalDate.parse(fecha.toString()));
				trs.add(tr);
				
			}
			
			c.close();
			
			c2 = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT * FROM montocuentabancaria");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r2.next())
			{
				monto = r2.getFloat(2);
			}
			CuentaBanco cj = new CuentaBanco(monto);
			cj.setTransacciones(trs);
			
			setCuentaBanco(cj);

			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loadPeticiones()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		String codigo = null;
		int idProv = 0;
		Proveedores prov = null;
		int idProducto = 0;
		Producto prod = null;
		int cantidad = 0;
		float monto = 0;
		String metodoP = null;
		String estado = null;
		Date fecha = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM peticiones");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				codigo = r.getString(2);
				idProv = r.getInt(3);
				idProducto = r.getInt(4);
				cantidad = r.getInt(5);
				monto = r.getFloat(6);
				metodoP = r.getString(7);
				estado = r.getString(8);
				fecha = r.getDate(9);
				
				prov = getMisProveedores().get(idProv-1);
				prod = getMisProductos().get(idProducto-1);
				Peticion pet = new Peticion(codigo, prov, prod, cantidad, monto, metodoP, estado, LocalDate.parse(fecha.toString()));
				
				Controladora.getInstance().getMisPeticiones().add(pet);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadPeticionesCredito()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM peticionescredito");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadPeticionesCredito()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int idPeticion = 0;
		float montoAdeudado = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM peticionescredito");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				idPeticion = r.getInt(2);
				montoAdeudado = r.getFloat(3);
				
				Controladora.getInstance().getMisPeticiones().get(idPeticion-1).setAdeudado(montoAdeudado);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadPagoPeticionesCredito()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM pagopeticionescredito");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadPagoPeticionesCredito()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int idPeticion = 0;
		float montoPagado = 0;
		Date fecha = null;
		String tipoPago = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM pagopeticionescredito");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				idPeticion = r.getInt(2);
				montoPagado = r.getFloat(3);
				fecha = r.getDate(4);
				tipoPago = r.getString(5);
				
				Pago pag = new Pago(montoPagado, tipoPago, LocalDate.parse(fecha.toString()));
				
				Controladora.getInstance().getMisPeticiones().get(idPeticion-1).getPagosCredito().add(pag);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadUsuarios()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM usuarios");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadUsuarios()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		int idUsuario = 0;
		String nombreUsuario = null;
		int empleadoID = 0;
		boolean usuarioActivo = false;
		String passwordHash = null;
		int idCargo = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM usuarios");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				idUsuario = r.getInt(1);
				nombreUsuario = r.getString(2);
				empleadoID = r.getInt(3);
				usuarioActivo = r.getBoolean(4);
				
				c2 = con.conectar();
				
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT * FROM usuariocontrasena WHERE usuario = '"+idUsuario+"' AND activo = 1");
				
				while(r2.next())
				{
					passwordHash = r2.getString(3);
				}
				
				c2.close();
				
				c3 = con.conectar();
				
				s3 = (Statement) c3.createStatement();
				r3 = s3.executeQuery("SELECT * FROM usuariocargo WHERE usuario = '"+idUsuario+"' AND borrado = 0");
				
				while(r3.next())
				{
					idCargo = r3.getInt(3);
				}
				
				c3.close();
				
				Empleado emp = Controladora.getInstance().getMisEmpleados().get(empleadoID-1);
				Cargo cg = Controladora.getInstance().getMisCargos().get(idCargo-1);
				
				Usuario usu = new Usuario(nombreUsuario, emp, usuarioActivo, passwordHash, true, cg);
				
				Controladora.getInstance().getMisUsuarios().add(usu);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void setMisEmpleadosToNull() {
		this.misEmpleados.clear();
	}
	
	public void setMisUsuariosToNull() {
		this.misUsuarios.clear();
	}

	public boolean activarLoadKit()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		java.sql.Connection c2 = null;
		Statement s2 = null;
		ResultSet r2 = null;
		boolean activar = false;
		int cuenta = 0;
		int cuenta2 = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM productos");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			//Recuperar precios
			c2 = con.conectar();
					
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT COUNT(*) AS TOTAL FROM kit");
					
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r2.next())
			{
				cuenta2 = r2.getInt(1);
			}
			
			if(cuenta > 0 && cuenta2 > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
public void loadKit()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c2 = null;
	java.sql.Connection c3 = null;
	java.sql.Connection c4 = null;
	java.sql.Connection c5 = null;
	java.sql.Connection c6 = null;
	java.sql.Connection c7 = null;
	java.sql.Connection c8 = null;
	java.sql.Connection c9 = null;
	java.sql.Connection c10 = null;
	Statement s = null;
	Statement s2 = null;
	Statement s3 = null;
	Statement s4 = null;
	Statement s5 = null;
	Statement s6 = null;
	Statement s7 = null;
	Statement s8 = null;
	Statement s9 = null;
	Statement s10 = null;
	ResultSet r = null;
	ResultSet r2 = null;
	ResultSet r3 = null;
	ResultSet r4 = null;
	ResultSet r5 = null;
	ResultSet r6 = null;
	ResultSet r7 = null;
	ResultSet r8 = null;
	ResultSet r9 = null;
	ResultSet r10 = null;
	//Variables para producto
	String codigo = null;
	String nombre = null;
	String descripcion = null;
	String tipoProducto = null;
	String observ = null;
	float costo = 0;
	float costoitbis = 0;
	boolean borrado = false;
	//Variables para kit
	int id = 0;
	int idproducto = 0;
	float exisminima = 0;
	float exismaxima = 0;
	float exisactual = 0;
	float exisinicial = 0;
	//Variables para kitproductos
	int idkitproducto = 0;
	int idCantProd = 0;
	//Variables para cantproductosutilizados
	int idcantprodutil = 0;
	int idestandar = 0;
	float cantidad = 0;
	ArrayList<CantProductosUtilizados> listado = null;
	//Variables para precioproducto
	int idprecioprod = 0;
	int precioidp = 0;
	int productoidp = 0;
	boolean activo = true;
	//Variables para precio
	int idprecio = 0;
	float montoprecio = 0;
	String descripcionprecio = null;
	Date fechaprecio = null;
	int porc_ganancia = 0;
	int itbis = 0;
	//Variables para rubroproducto
	int idrubroproducto = 0;
	int rubroid = 0;
	int productorubroid = 0;
	//Variables para rubro
	int idrubros = 0;
	String codigorubro = null;
	String nombrerubro = null;
	//RECUPERAR PROVEEDOR
	int idproveedorprincipal = 0;
	int idproveedor = 0;
	int idprodproveedor = 0;
		
	//CONTINUACION DE RECUPERAR PROVEEDOR
	String nombreproveedor = null;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM productos");
		
		while(r.next())
		{
			idproducto = r.getInt(1);
			codigo = r.getString(2);
			nombre = r.getString(3);
			descripcion = r.getString(4);
			tipoProducto = r.getString(5);
			observ = r.getString(6);
			costo = r.getFloat(8);
			costoitbis = r.getFloat(9);
			borrado = r.getBoolean(10);
			
			//Recuperar kit
			c2 = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT * FROM kit WHERE producto = '"+idproducto+"'");
			
			while(r2.next())
			{
				listado = new ArrayList<>();
				id = r2.getInt(1);
				exisminima = r2.getFloat(3);
				exismaxima = r2.getFloat(4);
				exisactual = r2.getFloat(5);
				exisinicial = r2.getFloat(6);
				
				//Recuperar kit
				c3 = con.conectar();
				
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s3 = (Statement) c3.createStatement();
				r3 = s3.executeQuery("SELECT * FROM kitproductos WHERE kit = '"+id+"'");
				
				while(r3.next())
				{
					idkitproducto = r3.getInt(1);
					idCantProd = r3.getInt(3);
					
					//Recuperar kit
					c4 = con.conectar();
					
					//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
					s4 = (Statement) c4.createStatement();
					r4 = s4.executeQuery("SELECT * FROM cantproductosutilizados WHERE idcantproductosutilizados = '"+idCantProd+"'");
					
					while(r4.next())
					{
						idcantprodutil = r4.getInt(1);
						idestandar = r4.getInt(2);
						cantidad = r4.getFloat(3);
					}
					
					Estandar prod = getMisProductosEstandar().get(idestandar-1);
					
					CantProductosUtilizados cant = new CantProductosUtilizados(prod, cantidad);
					
					listado.add(cant);
					
				}
				
				c5 = con.conectar();
				s5 = (Statement) c5.createStatement();
				r5 = s5.executeQuery("SELECT * FROM precioproducto WHERE producto = '"+idproducto+"'");
				while(r5.next())
				{
					idprecioprod = r5.getInt(1);
					precioidp = r5.getInt(2);
					productoidp = r5.getInt(3);
					activo = r5.getBoolean(4);
				}
				
				c6 = con.conectar();
				s6 = (Statement) c6.createStatement();
				r6 = s6.executeQuery("SELECT * FROM precio WHERE idprecio = '"+precioidp+"'");
				while(r6.next())
				{
					idprecio = r6.getInt(1);
					montoprecio = r6.getFloat(2);
					descripcionprecio = r6.getString(3);
					fechaprecio = r6.getDate(4);
					porc_ganancia = r6.getInt(6);
					itbis = r6.getInt(7);
				}
				
				c7 = con.conectar();
				s7 = (Statement) c7.createStatement();
				r7 = s7.executeQuery("SELECT * FROM rubroproducto WHERE producto = '"+idproducto+"'");
				while(r7.next())
				{
					idrubroproducto = r7.getInt(1);
					rubroid = r7.getInt(2);
					productorubroid = r7.getInt(3);
				}
				
				c8 = con.conectar();
				s8 = (Statement) c8.createStatement();
				r8 = s8.executeQuery("SELECT * FROM rubros WHERE idrubros = '"+rubroid+"'");
				while(r8.next())
				{
					idrubros = r8.getInt(1);
					codigorubro = r8.getString(2);
					nombrerubro = r8.getString(3);
				}
				
				c9 = con.conectar();
				s9 = (Statement) c9.createStatement();
				r9 = s9.executeQuery("SELECT * FROM proveedorprincipaproducto WHERE producto = '"+idproducto+"'");
				while(r9.next())
				{
					idproveedorprincipal = r9.getInt(1);
					idproveedor = r9.getInt(2);
					idprodproveedor = r9.getInt(3);
				}
				
				c10 = con.conectar();
				s10 = (Statement) c10.createStatement();
				r10 = s10.executeQuery("SELECT nombre FROM proveedores WHERE idproveedores = '"+idproveedor+"'");
				while(r10.next())
				{
					nombreproveedor = r10.getString(1);
				}
				
				Rubro ru = buscarRubro(nombrerubro);
				Precio pre = new Precio(montoprecio, descripcionprecio, activo, porc_ganancia, itbis);
				Proveedores pro = buscarProveedor(nombreproveedor);
				
				Kit recuperado = new Kit(listado, exisactual, exisminima, exismaxima, exisinicial, null, codigo, nombre, descripcion, ru, tipoProducto, pro, null, null, observ, null, pre, null, null, 0, descripcion, null, costo, costoitbis);
				recuperado.setBorrado(borrado);
				
				Controladora.getInstance().getMisProductos().add(recuperado);
				Controladora.getInstance().getMisProductosKit().add(recuperado);
				listado = null;
				
				System.out.println(recuperado.getNombre());
				System.out.println(recuperado.getRubroProducto());
			}
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(c2!=null) {
				c2.close();
			}
			
			if(c3!=null) {
				c3.close();
			}
			
			if(c4!=null) {
				c4.close();
			}
			
			if(c5!=null) {
				c5.close();
			}
			
			if(c6!=null) {
				c6.close();
			}
			
			if(c7!=null) {
				c7.close();
			}
			
			if(c8!=null) {
				c8.close();
			}
			
			if(c9!=null) {
				c9.close();
			}
			
			if(c10!=null) {
				c10.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(s2!=null) {
				s2.close();
			}
			
			if(s3!=null) {
				s3.close();
			}
			
			if(s4!=null) {
				s4.close();
			}
			
			if(s5!=null) {
				s5.close();
			}
			
			if(s6!=null) {
				s6.close();
			}
			
			if(s7!=null) {
				s7.close();
			}
			
			if(s8!=null) {
				s8.close();
			}
			
			if(s9!=null) {
				s9.close();
			}
			
			if(s10!=null) {
				s10.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			if(r2!=null) {
				r2.close();
			}
			
			if(r3!=null) {
				r3.close();
			}
			
			if(r4!=null) {
				r4.close();
			}
			
			if(r5!=null) {
				r5.close();
			}
			
			if(r6!=null) {
				r6.close();
			}
			
			if(r7!=null) {
				r7.close();
			}
			
			if(r8!=null) {
				r8.close();
			}
			
			if(r9!=null) {
				r9.close();
			}
			
			if(r10!=null) {
				r10.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
	
public boolean activarLoadServicios()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	java.sql.Connection c2 = null;
	Statement s2 = null;
	ResultSet r2 = null;
	boolean activar = false;
	int cuenta = 0;
	int cuenta2 = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM productos");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		//Recuperar precios
		c2 = con.conectar();
				
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s2 = (Statement) c2.createStatement();
		r2 = s2.executeQuery("SELECT COUNT(*) AS TOTAL FROM servicios");
				
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r2.next())
		{
			cuenta2 = r2.getInt(1);
		}
		
		if(cuenta > 0 && cuenta2 > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

	public void loadServicios()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		java.sql.Connection c4 = null;
		java.sql.Connection c5 = null;
		java.sql.Connection c6 = null;
		java.sql.Connection c7 = null;
		java.sql.Connection c8 = null;
		java.sql.Connection c9 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		Statement s4 = null;
		Statement s5 = null;
		Statement s6 = null;
		Statement s7 = null;
		Statement s8 = null;
		Statement s9 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		ResultSet r4 = null;
		ResultSet r5 = null;
		ResultSet r6 = null;
		ResultSet r7 = null;
		ResultSet r8 = null;
		ResultSet r9 = null;
		int id = 0;
		int idproducto2 = 0;
		int idServicioMateriales = 0;
		int idServicio = 0;
		int idcantProd = 0;
		int idcantprodutil = 0;
		int idestandar = 0;
		float cantidad = 0;
		int idproducto = 0;
		String codigo = null;
		String nombre = null;
		String descripcion = null;
		String tipoProducto = null;
		String observ = null;
		float costo = 0;
		float costoitbis = 0;
		int idprecioprod = 0;
		int precioidp = 0;
		int productoidp = 0;
		boolean activo = true;
		int idprecio = 0;
		float montoprecio = 0;
		String descripcionprecio = null;
		Date fechaprecio = null;
		int porc_ganancia = 0;
		int itbis = 0;
		int idrubroproducto = 0;
		int rubroid = 0;
		int productorubroid = 0;
		int idrubros = 0;
		String codigorubro = null;
		String nombrerubro = null;
		int categoriaempleadoid = 0;
		String nombreCat = null;
		ArrayList<CantProductosUtilizados> listado = new ArrayList<>();
		boolean borrado = false;
	
		try {
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM productos");
		
			while(r.next())
			{
				idproducto = r.getInt(1);
				codigo = r.getString(2);
				nombre = r.getString(3);
				descripcion = r.getString(4);
				tipoProducto = r.getString(5);
				observ = r.getString(6);
				costo = r.getFloat(8);
				costoitbis = r.getFloat(9);
				borrado = r.getBoolean(10);
			
				//Recuperar precios
				c2 = con.conectar();
			
				//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT * FROM servicios WHERE producto = '"+idproducto+"'");
			
				//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
				while(r2.next())
				{
					id = r2.getInt(1);
					idproducto2 = r2.getInt(2);
					categoriaempleadoid = r2.getInt(3);
				
					c9 = con.conectar();
				
					//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
					s9 = (Statement) c9.createStatement();
					r9 = s9.executeQuery("SELECT * FROM categoriaempleado WHERE idcategoriaempleado = '"+categoriaempleadoid+"'");
				
					while(r9.next())
					{
						nombreCat = r9.getString(2);
					}
				
					c9.close();
					c3 = con.conectar();
				
					//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
					s3 = (Statement) c3.createStatement();
					r3 = s3.executeQuery("SELECT * FROM serviciomateriales WHERE servicio = '"+id+"'");
				
					while(r3.next())
					{
						idServicioMateriales = r3.getInt(1);
						idServicio = r3.getInt(2);
						idcantProd = r3.getInt(3);
					
						c4 = con.conectar();
					
						//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
						s4 = (Statement) c4.createStatement();
						r4 = s4.executeQuery("SELECT * FROM cantproductosutilizados WHERE idcantproductosutilizados = '"+idcantProd+"'");
					
						while(r4.next())
						{
							idcantprodutil = r4.getInt(1);
							idestandar = r4.getInt(2);
							cantidad = r4.getFloat(3);
						
						}
						
						c4.close();
					
						Estandar prod = getMisProductosEstandar().get(idestandar-1);
					
						CantProductosUtilizados cant = new CantProductosUtilizados(prod, cantidad);
					
						listado.add(cant);
					}
				
					c5 = con.conectar();
					s5 = (Statement) c5.createStatement();
					r5 = s5.executeQuery("SELECT * FROM precioproducto WHERE producto = '"+idproducto+"'");
					while(r5.next())
					{
						idprecioprod = r5.getInt(1);
						precioidp = r5.getInt(2);
						productoidp = r5.getInt(3);
						activo = r5.getBoolean(4);
					}
					
					c5.close();
					c6 = con.conectar();
					s6 = (Statement) c6.createStatement();
					r6 = s6.executeQuery("SELECT * FROM precio WHERE idprecio = '"+precioidp+"'");
					while(r6.next())
					{
						idprecio = r6.getInt(1);
						montoprecio = r6.getFloat(2);
						descripcionprecio = r6.getString(3);
						fechaprecio = r6.getDate(4);
						porc_ganancia = r6.getInt(6);
						itbis = r6.getInt(7);
					}
					
					c6.close();
					c7 = con.conectar();
					s7 = (Statement) c7.createStatement();
					r7 = s7.executeQuery("SELECT * FROM rubroproducto WHERE producto = '"+idproducto+"'");
					while(r7.next())
					{
						idrubroproducto = r7.getInt(1);
						rubroid = r7.getInt(2);
						productorubroid = r7.getInt(3);
					}
					
					c7.close();
					c8 = con.conectar();
					s8 = (Statement) c8.createStatement();
					r8 = s8.executeQuery("SELECT * FROM rubros WHERE idrubros = '"+rubroid+"'");
					while(r8.next())
					{
						idrubros = r8.getInt(1);
						codigorubro = r8.getString(2);
						nombrerubro = r8.getString(3);
					}
					c8.close();
					
					Rubro ru = buscarRubro(nombrerubro);
					Precio pre = new Precio(montoprecio, descripcionprecio, activo, porc_ganancia, itbis);
					CategoriaEmpleado cat = buscarCategoria(nombreCat);
				
					Servicio serv = new Servicio(codigo, nombre, descripcion, ru, tipoProducto, null, null, observ, null, pre, null, null, descripcion, cat, listado, costo, costoitbis);
					serv.setBorrado(borrado);
					Controladora.getInstance().getMisProductos().add(serv);
					Controladora.getInstance().getMisProductosServicio().add(serv);
				
					System.out.println(serv.getRubroProducto());
					listado = null;
				}	
				
				c2.close();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

public void loadUnidadesMedida()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM area");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String categoria = r.getString(2);
				String nombre = r.getString(3);
				String abreviatura = r.getString(4);
				
				Area cli = new Area(categoria, nombre, abreviatura);
				
				Controladora.getInstance().getMisUnidadMedida().add(cli);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM masa");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String categoria = r.getString(2);
				String nombre = r.getString(3);
				String abreviatura = r.getString(4);
				
				Masa cli = new Masa(categoria, nombre, abreviatura);
				
				Controladora.getInstance().getMisUnidadMedida().add(cli);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM longitud");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String categoria = r.getString(2);
				String nombre = r.getString(3);
				String abreviatura = r.getString(4);
				
				Longitud cli = new Longitud(categoria, nombre, abreviatura);
				
				Controladora.getInstance().getMisUnidadMedida().add(cli);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM volumen");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String categoria = r.getString(2);
				String nombre = r.getString(3);
				String abreviatura = r.getString(4);
				
				Volumen cli = new Volumen(categoria, nombre, abreviatura);
				
				Controladora.getInstance().getMisUnidadMedida().add(cli);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

public boolean activarLoadProveedores()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM proveedores");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

public void loadProveedores()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet q = null;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			s2 = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM proveedores");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				Float saldo = r.getFloat(2);
				String domicilio = r.getString(3);
				String correo = r.getString(4);
				String rnc = r.getString(5);
				int rubroid = r.getInt(6);
				String sitioWeb = r.getString(7);
				String nombre = r.getString(8);
				String telefono = r.getString(9);
				String codigo = r.getString(10);
				boolean borrado = r.getBoolean(11);
				
				if(rubroid > 0)
				{
					q = s2.executeQuery("SELECT * FROM rubros WHERE id = '"+rubroid+"'");
					while(q.next())
					{
						int idr = q.getInt(1);
						String codrubro = q.getString(2);
						String nombrerubro = q.getString(3);
						
						Rubro rubroproveedor = new Rubro(codrubro, nombrerubro);
						
						Proveedores cli = new Proveedores(codigo, nombre, telefono, domicilio, correo, rnc, rubroproveedor, sitioWeb);
						cli.setBorrado(borrado);
						
						Controladora.getInstance().getMisProveedores().add(cli);
					}
					
				}
				else
				{
					Proveedores cli = new Proveedores(codigo, nombre, telefono, domicilio, correo, rnc, null, sitioWeb);
					cli.setBorrado(borrado);
					
					Controladora.getInstance().getMisProveedores().add(cli);
				}
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
				if(q!=null) {
					q.close();
				}
				
				if(s2!=null) {
					s2.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

public boolean activarRecuperarRubros()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM rubros");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

public void recuperarRubros()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	
	try {
		
		//Recuperar rubros
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		if(Controladora.getInstance().getMisRubros().isEmpty())
		{
			System.out.println("Mis rubros est� vac�o");
		}
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM rubros");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			int id = r.getInt(1);
			String codigo = r.getString(2);
			String nombreRubro = r.getString(3);				
			boolean borrado = r.getBoolean(4);
			
			Rubro pre = new Rubro(codigo, nombreRubro);
			pre.setBorrado(borrado);
			
			Controladora.getInstance().getMisRubros().add(pre);
			
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

public boolean activarLoadManoDeObra()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM manodeobra");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

public void loadManoDeObra()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c2 = null;
	java.sql.Connection c3 = null;
	Statement s = null;
	Statement s2 = null;
	Statement s3 = null;
	ResultSet r = null;
	ResultSet r2 = null;
	ResultSet r3 = null;
	int id =0;
	float costo = 0;
	float cantHoras = 0;
	Date fecha = null;
	int id2 = 0;
	int manoobraID = 0;
	int estandarID = 0;
	int servicioID = 0;
	int categoriaID = 0;
	
	try {
		
		//Recuperar rubros
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM manodeobra");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			id = r.getInt(1);
			costo = r.getFloat(2);
			cantHoras = r.getFloat(3);
			fecha = r.getDate(4);
			
			c2 = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT * FROM manodeobraproducto WHERE manodeobra = '"+id+"'");
			
			while(r2.next())
			{
				id2 = r2.getInt(1);
				manoobraID = r2.getInt(2);
				estandarID = r2.getInt(3);
				categoriaID = r2.getInt(4);
				
				System.out.println("CategoriaID = " + categoriaID);
				CategoriaEmpleado cat = Controladora.getInstance().getMisCategoriasEmpleado().get(categoriaID-1);
				
				ManoDeObra mano = new ManoDeObra(costo, cantHoras, (java.sql.Date) fecha, cat);
				
				Controladora.getInstance().getMisManosDeObras().add(mano);
				
				Controladora.getInstance().getMisProductosEstandar().get(estandarID-1).setInfoManoDeObra(mano);
			}
			c2.close();
			c3 = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s3 = (Statement) c3.createStatement();
			r3 = s3.executeQuery("SELECT * FROM manodeobraservicio WHERE manodeobra = '"+id+"'");
			
			while(r3.next())
			{
				id2 = r3.getInt(1);
				manoobraID = r3.getInt(2);
				servicioID = r3.getInt(3);
				categoriaID = r3.getInt(4);
				
				System.out.println("CategoriaID = " + categoriaID);
				CategoriaEmpleado cat = Controladora.getInstance().getMisCategoriasEmpleado().get(categoriaID-1);
				
				ManoDeObra mano = new ManoDeObra(costo, cantHoras, (java.sql.Date) fecha, cat);
				
				Controladora.getInstance().getMisManosDeObras().add(mano);
				
				Controladora.getInstance().getMisProductosServicio().get(servicioID-1).setInfoManoDeObra(mano);
			}
			
			c3.close();
		}		
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(c2!=null) {
				c2.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(s2!=null) {
				s2.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			if(r2!=null) {
				r2.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

public boolean activarLoadProductos()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM productos");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

public void loadProductos()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c2 = null;
	java.sql.Connection c3 = null;
	java.sql.Connection c4 = null;
	java.sql.Connection c5 = null;
	java.sql.Connection c6 = null;
	java.sql.Connection c7 = null;
	java.sql.Connection c8 = null;
	Statement s = null;
	Statement s2 = null;
	Statement s3 = null;
	Statement s4 = null;
	Statement s5 = null;
	Statement s6 = null;
	Statement s7 = null;
	Statement s8 = null;
	ResultSet r = null;
	ResultSet r2 = null;
	ResultSet r3 = null;
	ResultSet r4 = null;
	ResultSet r5 = null;
	ResultSet r6 = null;
	ResultSet r7 = null;
	ResultSet r8 = null;
	
	//RECUPERACION PRODUCTO
	int id = 0;
	String codigo = null;
	String nombre=null;				
	String descripcion = null;
	String tipoproducto = null;
	String observaciones = null;
	String unidadmedida = null;
	Float costo = null;
	float costoitbis = 0;
	UnidadMedida unidad1 = null;
	boolean borrado = false;
	
	//RECUPERACION PRODUCTO ESTANDAR
	int idestandar;
	int producto;
	Float exitmin = null;
	Float exitmax = null;				
	Date fechavencimiento = null;
	Float costocompra = null;
	boolean fabricado = false;
	Float exitact = null;
	Float exitinit = null;
	Float manodeobra = null;
	
	//RECUPERAR PRECIO
	int idprecioprod;
	int precioidp = 0;
	int productoidp;
	boolean activo = false;
	
	//SEGUNDA PARTE DEL PRECIO
	int idprecio;
	float montoprecio = 0;
	String descripcionprecio = null;
	Date fechaprecio;
	int porc_ganancia = 0;
	int itbis = 0;
	
	//RECUPERAR RUBRO
	int idrubroproducto = 0;
	int rubroid = 0;
	int productorubroid = 0;
	
	//CONTINUACION DE RECUPERAR RUBRO
	int idrubros = 0;
	String codigorubro = null;
	String nombrerubro = null;
	
	//RECUPERAR PROVEEDOR
	int idproveedorprincipal = 0;
	int idproveedor = 0;
	int idprodproveedor = 0;
	
	//CONTINUACION DE RECUPERAR PROVEEDOR
	String nombreproveedor = null;
	
	try {
		
		c = con.conectar();
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM productos");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			id = r.getInt(1);
			System.out.println("El id de este producto es " + id);
			codigo = r.getString(2);
			nombre = r.getString(3);				
			descripcion = r.getString(4);
			tipoproducto = r.getString(5);
			observaciones = r.getString(6);
			unidadmedida = r.getString(7);
			costo = r.getFloat(8);
			costoitbis = r.getFloat(9);
			borrado = r.getBoolean(10);
			
			c2 = con.conectar();
			s2 = (Statement) c2.createStatement();
			r2 = s2.executeQuery("SELECT * FROM estandar WHERE producto = '"+id+"'");
			while(r2.next())
			{
				System.out.println("Ciclo r2");
				idestandar = r2.getInt(1);
				producto = r2.getInt(2);
				exitmin = r2.getFloat(3);
				exitmax = r2.getFloat(4);
				fechavencimiento = r2.getDate(5);
				costocompra = r2.getFloat(6);
				fabricado = r2.getBoolean(7);
				exitact = r2.getFloat(8);
				exitinit = r2.getFloat(9);
				manodeobra = r2.getFloat(10);
				
				c3 = con.conectar();
				s3 = (Statement) c3.createStatement();
				r3 = s3.executeQuery("SELECT * FROM precioproducto WHERE producto = '"+id+"'");
				while(r3.next())
				{
					System.out.println("Ciclo r3");
					idprecioprod = r3.getInt(1);
					precioidp = r3.getInt(2);
					productoidp = r3.getInt(3);
					activo = r3.getBoolean(4);
				}
				
				c4 = con.conectar();
				s4 = (Statement) c4.createStatement();
				r4 = s4.executeQuery("SELECT * FROM precio WHERE idprecio = '"+precioidp+"'");
				while(r4.next())
				{
					System.out.println("Ciclo r4");
					idprecio = r4.getInt(1);
					montoprecio = r4.getFloat(2);
					descripcionprecio = r4.getString(3);
					fechaprecio = r4.getDate(4);
					porc_ganancia = r4.getInt(6);
					itbis = r4.getInt(7);
				}
				
				c5 = con.conectar();
				s5 = (Statement) c5.createStatement();
				r5 = s5.executeQuery("SELECT * FROM rubroproducto WHERE producto = '"+id+"'");
				while(r5.next())
				{
					System.out.println("Ciclo r5");
					idrubroproducto = r5.getInt(1);
					rubroid = r5.getInt(2);
					productorubroid = r5.getInt(3);
				}
				
				c6 = con.conectar();
				s6 = (Statement) c6.createStatement();
				r6 = s6.executeQuery("SELECT * FROM rubros WHERE idrubros = '"+rubroid+"'");
				while(r6.next())
				{
					System.out.println("Ciclo r6");
					idrubros = r6.getInt(1);
					codigorubro = r6.getString(2);
					nombrerubro = r6.getString(3);
				}
				
				c7 = con.conectar();
				s7 = (Statement) c7.createStatement();
				r7 = s7.executeQuery("SELECT * FROM proveedorprincipaproducto WHERE producto = '"+id+"'");
				while(r7.next())
				{
					System.out.println("Ciclo r7");
					idproveedorprincipal = r7.getInt(1);
					idproveedor = r7.getInt(2);
					idprodproveedor = r7.getInt(3);
				}
				
				c8 = con.conectar();
				s8 = (Statement) c8.createStatement();
				r8 = s8.executeQuery("SELECT nombre FROM proveedores WHERE idproveedores = '"+idproveedor+"'");
				while(r8.next())
				{
					System.out.println("Ciclo r8");
					nombreproveedor = r8.getString(1);
				}
				
				//RECUPERAR UNIDAD DE MEDIDA
				System.out.println("Seccion de recuperar medida");
				unidad1 = buscarUnidadMedida(unidadmedida);
				
				//CREAR EL ESTANDAR
				System.out.println("Seccion de crear estandar");
				Rubro ru = buscarRubro(nombrerubro);
				Proveedores pro = buscarProveedor(nombreproveedor);
				Precio pre = new Precio(montoprecio, descripcionprecio, activo, porc_ganancia, itbis);
				Estandar estandar = new Estandar(exitact, exitmin, exitmax, exitinit, fechavencimiento, costocompra, fabricado, null, codigo, nombre, descripcionprecio, ru, tipoproducto, pro, null, null, observaciones, unidad1, pre, null, null, manodeobra, descripcion, null, costo, costoitbis);
				estandar.setBorrado(borrado);
				
				System.out.println(unidadmedida);
				if(estandar.getUnidadMedida() == null)
				{
					System.out.println("unidad de medida null");
				}
				else
				{
					System.out.println(estandar.getUnidadMedida().getAbreviatura());
				}
				Controladora.getInstance().getMisProductos().add(estandar);
				System.out.println("La cantidad de productos es " + Controladora.getInstance().getMisProductos().size());
				Controladora.getInstance().getMisProductosEstandar().add(estandar);
			}
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(c2!=null) {
				c2.close();
			}
			
			if(c3!=null) {
				c3.close();
			}
			
			if(c4!=null) {
				c4.close();
			}
			
			if(c5!=null) {
				c5.close();
			}
			
			if(c6!=null) {
				c6.close();
			}
			
			if(c7!=null) {
				c7.close();
			}
			
			if(c8!=null) {
				c8.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(s2!=null) {
				s2.close();
			}
			
			if(s3!=null) {
				s3.close();
			}
			
			if(s4!=null) {
				s4.close();
			}
			
			if(s5!=null) {
				s5.close();
			}
			
			if(s6!=null) {
				s6.close();
			}
			
			if(s7!=null) {
				s7.close();
			}
			
			if(s8!=null) {
				s8.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			if(r2!=null) {
				r2.close();
			}
			
			if(r3!=null) {
				r3.close();
			}
			
			if(r4!=null) {
				r4.close();
			}
			
			if(r5!=null) {
				r5.close();
			}
			
			if(r6!=null) {
				r6.close();
			}
			
			if(r7!=null) {
				r7.close();
			}
			
			if(r8!=null) {
				r8.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

public boolean activarLoadPartida()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM productopartida");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

	public void loadPartida()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		java.sql.Connection c4 = null;
		java.sql.Connection c5 = null;
		java.sql.Connection c6 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		Statement s4 = null;
		Statement s5 = null;
		Statement s6 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		ResultSet r4 = null;
		ResultSet r5 = null;
		ResultSet r6 = null;
		int i;
		int idProducto = 0;
		int idprodpart = 0;
		int productoid = 0;
		int partidaid = 0;
		int idpartidaprodutil = 0;
		int partidaid2 = 0;
		int cantproductoutilizadoid = 0;
		int cantproductoutilizadoid2 = 0;
		int idestandarrelacionado = 0;
		float cantidad = 0;
		String nombre = null;
		Estandar est = null;
		ArrayList<Producto> productosPartida = new ArrayList<>();
		Partida partidaRecuperada = new Partida();
		int idProductorelacionado = 0;
		//BigDecimal cantidadLong = 0;
	
		for (i = 0; i < Controladora.getInstance().getMisProductosEstandar().size(); i++)
		{
			try {	
				//Recuperar rubros
				c = con.conectar();
				s = (Statement) c.createStatement();
		
					if(Controladora.getInstance().getMisProductosEstandar().get(i).isFabricado())
					{
						partidaRecuperada = new Partida();
						System.out.println(getMisProductosEstandar().get(i).isFabricado());
						r = s.executeQuery("SELECT idproductos FROM productos WHERE codigo = '"+getMisProductosEstandar().get(i).getCodigo()+"'");
						while(r.next())
						{
							idProducto = r.getInt(1); 
						}
					
						c4 = con.conectar();
						s4 = (Statement) c4.createStatement();
						r4 = s4.executeQuery("SELECT * FROM productopartida WHERE producto = '"+idProducto+"'");
						while(r4.next())
						{
							idprodpart = r4.getInt(1);
							productoid = r4.getInt(2);
							partidaid = r4.getInt(3); 
						}
					
						c5 = con.conectar();
						s5 = (Statement) c5.createStatement();
						r5 = s5.executeQuery("SELECT * FROM partidaprodutil WHERE partida = '"+partidaid+"'");
						while(r5.next())
						{
							idpartidaprodutil = r5.getInt(1);
							partidaid2 = r5.getInt(2);
							cantproductoutilizadoid = r5.getInt(3);
						
							c2 = con.conectar();
							s2 = (Statement) c2.createStatement();
							r2 = s2.executeQuery("SELECT * FROM cantproductosutilizados WHERE idcantproductosutilizados = '"+cantproductoutilizadoid+"'");
							while(r2.next())
							{
								cantproductoutilizadoid2 = r2.getInt(1);
								idestandarrelacionado = r2.getInt(2);
								cantidad = r2.getFloat(3);
								
								c6 = con.conectar();
								s6 = (Statement) c6.createStatement();
								r6 = s6.executeQuery("SELECT producto FROM estandar WHERE idestandar = '"+idestandarrelacionado+"'");
								while(r6.next())
								{
									idProductorelacionado = r6.getInt(1);
								}
								
								c3 = con.conectar();
								s3 = (Statement) c3.createStatement();
								r3 = s3.executeQuery("SELECT nombre FROM productos WHERE idproductos = '"+idProductorelacionado+"'");
								while(r3.next())
								{
									nombre = r3.getString(1);
									est = (Estandar) buscarProducto(nombre);
									productosPartida.add(est);
								}
								//cantidadLong = BigDecimal.valueOf(cantidad);
								CantProductosUtilizados cpu = new CantProductosUtilizados(est, cantidad);
								Controladora.getInstance().getMisCantProductosUtilizados().add(cpu);
								partidaRecuperada.agregarProductoUtilizado(cpu);
								System.out.println("La clase de cantproductosutilizados: " + cpu.getProducto().getNombre() + " " + cpu.getCantidad());
							
							}
					
						
						}
						Controladora.getInstance().getMisPartidas().add(partidaRecuperada);	
						Controladora.getInstance().getMisProductosEstandar().get(i).setPartida(partidaRecuperada);
						System.out.println(Controladora.getInstance().getMisProductosEstandar().get(i).getPartida().getListaMateriales().size());
						partidaRecuperada = null;
					
					}
			
			} catch (SQLException e) {
				e.printStackTrace();
				Controladora.getInstance().print_exception(e);
			}
		
			//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
				
					if(c!=null) {
						c.close();
					}
				
					if(c2!=null) {
						c2.close();
					}
				
					if(c3!=null) {
						c3.close();
					}
				
					if(c4!=null) {
						c4.close();
					}
				
					if(c5!=null) {
						c5.close();
					}
					
					if(c6!=null) {
						c6.close();
					}
				
					if(s!=null) {
						s.close();
					}
				
					if(s2!=null) {
						s2.close();
					}
				
					if(s3!=null) {
						s3.close();
					}
				
					if(s4!=null) {
						s4.close();
					}
				
					if(s5!=null) {
						s5.close();
					}
					
					if(s6!=null) {
						s6.close();
					}
				
					if(r!=null) {
						r.close();
					}
				
					if(r2!=null) {
						r2.close();
					}
				
					if(r3!=null) {
						r3.close();
					}
				
					if(r4!=null) {
						r4.close();
					}
				
					if(r5!=null) {
						r5.close();
					}
					
					if(r6!=null) {
						r6.close();
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public Proveedores buscarProveedor(String nombre)
	{
		Proveedores p = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<getMisProveedores().size() && !encontrado)
		{
			if(getMisProveedores().get(i).getNombre().equalsIgnoreCase(nombre))
			{
				p = getMisProveedores().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return p;
	}
	
	public Proveedores buscarProveedorCodigo(String codigo) {
		Proveedores p = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<Controladora.getInstance().getMisProveedores().size() && !encontrado)
		{
			if(Controladora.getInstance().getMisProveedores().get(i).getCodigo().equalsIgnoreCase(codigo) && !Controladora.getInstance().getMisProveedores().get(i).isBorrado())
			{
				p = getMisProveedores().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return p;
	}

	public UnidadMedida buscarUnidadMedida(String u)
	{
		UnidadMedida a = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<getMisUnidadMedida().size() && !encontrado)
		{
			if(getMisUnidadMedida().get(i).getNombre().equalsIgnoreCase(u))
			{
				a = getMisUnidadMedida().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return a;
	}

	public Area isArea(String u)
	{
		Area a = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<getMisAreas().size() && !encontrado)
		{
			if(getMisAreas().get(i).getNombre().equalsIgnoreCase(u))
			{
				a = getMisAreas().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return a;
	}

	public Longitud isLongitud(String u)
	{
		Longitud a = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<getMisLongitudes().size() && !encontrado)
		{
			if(getMisLongitudes().get(i).getNombre().equalsIgnoreCase(u))
			{
				a = getMisLongitudes().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return a;
	}

	public Masa isMasa(String u)
	{
		Masa a = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<getMisMasas().size() && !encontrado)
		{
			if(getMisMasas().get(i).getNombre().equalsIgnoreCase(u))
			{
				a = getMisMasas().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return a;
	}

	public Volumen isVolumen(String u)
	{
		Volumen a = null;
		int i = 0;
		boolean encontrado = false;
	
		while(i<getMisVolumenes().size() && !encontrado)
		{
			if(getMisVolumenes().get(i).getNombre().equalsIgnoreCase(u))
			{
				a = getMisVolumenes().get(i);
				encontrado = true;
			}
		
			i++;
		}
	
		return a;
	}

	public boolean activarLoadEmpleados()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
	
		try {
		
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM empleados");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
		
			if(cuenta > 0)
			{
				activar = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return activar;
	}

public void loadEmpleados()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	Statement s2 = null;
	ResultSet r = null;
	ResultSet q = null;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		s2 = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM empleados");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			int id = r.getInt(1);
			String codigo = r.getString(9);
			String nombre = r.getString(2);
			String telefono = r.getString(3);	
			String domicilio = r.getString(4);
			String correo = r.getString(5);
			String rnc = r.getString(6);
			float sueldo = r.getFloat(7);
			int categoriaid = r.getInt(8);
			boolean borrado = r.getBoolean(10);
			
			q = s2.executeQuery("SELECT * FROM categoriaempleado WHERE idcategoriaempleado = '"+categoriaid+"'");
			while(q.next())
			{
				int idr = q.getInt(1);
				String nombrecategoria = q.getString(2);
				float sueldocategoria = q.getFloat(3);
				
				CategoriaEmpleado categoriaEmp = new CategoriaEmpleado(nombrecategoria, sueldocategoria);
				
				Empleado emp = new Empleado(codigo, nombre, telefono, domicilio, correo, rnc, sueldocategoria, categoriaEmp);
				emp.setBorrado(borrado);
				
				Controladora.getInstance().getMisEmpleados().add(emp);
			}
			
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			if(q!=null) {
				q.close();
			}
			
			if(s2!=null) {
				s2.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

public boolean activarLoadCategoriaEmpleado()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM categoriaempleado");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

public void loadCategoriaEmpleado()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	
	try {
		
		//Recuperar rubros
		c = con.conectar();
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM categoriaempleado");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			int id = r.getInt(1);
			String nombre = r.getString(2);
			Float sueldo = r.getFloat(3);
			boolean borrado = r.getBoolean(4);
			
			CategoriaEmpleado pre = new CategoriaEmpleado(nombre, sueldo);
			pre.setBorrado(borrado);
			
			Controladora.getInstance().getMisCategoriasEmpleado().add(pre);
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

	public boolean activarLoadAtributos()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
	
		try {
		
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM atributos");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
		
			if(cuenta > 0)
			{
				activar = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return activar;
	}

	public void loadAtributos()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet q = null;
	
		try {
		
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM atributos");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String nombre = r.getString(2);
				int idgrupoatributo = r.getInt(3);
				boolean borrado = r.getBoolean("borrado");
			
				c2 = con.conectar();
				s2 = (Statement) c2.createStatement();
				q = s2.executeQuery("SELECT * FROM grupoatributo WHERE idgrupoatributo = '"+idgrupoatributo+"'");
				while(q.next())
				{
					int idr = q.getInt(1);
				
					String nombrecategoria = q.getString(2);
				
					GrupoAtributo pre = buscarGrupoAtributo(nombrecategoria);
				
					Atributos atr = new Atributos(nombre, pre);
					atr.setBorrado(borrado);
					
					Controladora.getInstance().getMisAtributos().add(atr);
				}
			
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
			
				if(c!=null) {
					c.close();
				}
			
				if(c2!=null) {
					c2.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
				if(q!=null) {
					q.close();
				}
			
				if(s2!=null) {
					s2.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean activarLoadGrupoAtributo()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
	
		try {
		
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM grupoatributo");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
		
			if(cuenta > 0)
			{
				activar = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return activar;
	}
	
	public void loadGrupoAtributo()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
	
		try {
		
			//Recuperar rubros
			c = con.conectar();
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM grupoatributo");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String nombre = r.getString(2);
			
				GrupoAtributo pre = new GrupoAtributo(nombre);
			
				Controladora.getInstance().getMisGrupoAtributo().add(pre);
			}
		
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
			
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public boolean activarLoadGastosGenerales()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
	
		try {
			
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM gastosgenerales");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
		
			if(cuenta > 0)
			{
				activar = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
			
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return activar;
	}

	public void loadGastosGenerales()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
	
		try {
		
			//Recuperar rubros
			c = con.conectar();
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM gastosgenerales");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				String nombre = r.getString(2);
				float precioUnitario = r.getFloat(5);
				String descripcion = r.getString(3);
				Date remodelado = r.getDate(4);
				boolean borrado = r.getBoolean(6);
				GastoGeneral pre = new GastoGeneral(nombre, precioUnitario, descripcion, LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(remodelado)));
				pre.setBorrado(borrado);
				
				Controladora.getInstance().getMisGastosGenerales().add(pre);
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean activarLoadImagenProducto()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
	
		try {
			
			//Recuperar precios
			c = con.conectar();
		
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM imagenproducto");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
		
			if(cuenta > 0)
			{
				activar = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
			
				if(c!=null) {
					c.close();
				}
			
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return activar;
	}

	public void loadImagenProducto()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
	
		try {
		
			//Recuperar rubros
			c = con.conectar();
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM imagenproducto");
		
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				int producto = r.getInt(2);
				byte[] foto = r.getBytes(3);
				boolean borrado = r.getBoolean(4);
				try {
					Controladora.getInstance().getMisProductos().get(producto-1).setFoto(foto);
				}catch(IndexOutOfBoundsException e) {
					
				}
				
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
	
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
			
				if(r!=null) {
					r.close();
				}
			
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que se encarga de restar la existencia actual de un producto estandar cuando se le hace una modificacion a este.
	public void restarExistenciaActual(float cantidadRestar, int indiceProducto) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE estandar SET existactual = '"+cantidadRestar+"' WHERE idestandar = '"+indiceProducto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que se encarga de restar la existencia actual de una matriz cuando se le hace una modificacion a este.
	public void restarExistenciaActualMatriz(float cantidadRestar, int indiceProducto) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE matriz SET existactual = '"+cantidadRestar+"' WHERE combinacion = '"+indiceProducto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public Factura buscarFactura(String codigo)
	{
		Factura f = null;
		
		for (Factura fac : misFacturas) {
			if(fac.getCodigo().equalsIgnoreCase(codigo))
			{
				f = fac;
			}
		}
		
		return f;
	}
	
	//Funcion que se encarga de restar la existencia actual de un producto kit cuando se le hace una modificacion a este.
	public void restarExistenciaActualKit(float cantidadRestar, int indiceProducto) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE kit SET exisactual = '"+cantidadRestar+"' WHERE idkit = '"+indiceProducto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que se encarga de sumar la existencia actual de un producto kit cuando se le hace una modificacion a este.
		public void sumarExistenciaActualKit(float cantidadSumar, int indiceProducto) {
			Conexion con = new Conexion();
			java.sql.Connection cSQL = null;
			Statement sSQL = null;
			ResultSet r = null;
			PreparedStatement p = null;
			try {
				cSQL = con.conectar();
				sSQL = (Statement) cSQL.createStatement();
				p = (PreparedStatement)
						cSQL.prepareStatement("UPDATE kit SET exisactual = '"+cantidadSumar+"' WHERE idkit = '"+indiceProducto+"'");
				p.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				Controladora.getInstance().print_exception(e);
			}
			finally {
				try {
					if(cSQL!=null) {
						cSQL.close();
					}
					
					if(sSQL!=null) {
						sSQL.close();
					}
					
					if(r!=null) {
						r.close();
					}
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	
	//Funcion que se encarga de sumar la existencia actual de un producto cuando se le hace una modificacion a este.
	public void sumarExistenciaActual(float cantidadSumar, int indiceProducto) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE estandar SET existactual = '"+cantidadSumar+"' WHERE idestandar = '"+indiceProducto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que se encarga de modificar datos de una petici�n.
	public void modificarEstadoPeticion(String estado, String metodo, String codigo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE peticiones SET estado = '"+estado+"' WHERE codigo = '"+codigo+"'");
			p.executeUpdate();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE peticiones SET metodopago = '"+metodo+"' WHERE codigo = '"+codigo+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
						sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que se encarga de sumar la existencia actual de un producto cuando se le hace una modificacion a este.
		public void modificarProveedorSaldo(float saldo, int indiceProveedor) {
			Conexion con = new Conexion();
			java.sql.Connection cSQL = null;
			Statement sSQL = null;
			ResultSet r = null;
			PreparedStatement p = null;
			try {
				cSQL = con.conectar();
				sSQL = (Statement) cSQL.createStatement();
				p = (PreparedStatement)
						cSQL.prepareStatement("UPDATE proveedores SET saldo = '"+saldo+"' WHERE idproveedores = '"+indiceProveedor+"'");
				p.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				Controladora.getInstance().print_exception(e);
			}
			finally {
				try {
					if(cSQL!=null) {
						cSQL.close();
					}
					
					if(sSQL!=null) {
							sSQL.close();
					}
					
					if(r!=null) {
						r.close();
					}
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	
	//Funcion que le asigna true al valor de borrado al producto
	public void borrarProducto(int indiceProducto) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE productos SET borrado = 1 WHERE idproductos = '"+indiceProducto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que le asigna true al valor de borrado del cliente
	public void borrarCliente(int indiceCliente) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE clientes SET borrado = 1 WHERE idclientes = '"+indiceCliente+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que le asigna true al valor de borrado del proveedor
	public void borrarProveedor(int indiceProveedor) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE proveedores SET borrado = 1 WHERE idproveedores = '"+indiceProveedor+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que le asigna true al valor de borrado del empleado
	public void borrarEmpleado(int indiceEmpleado) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE empleados SET borrado = 1 WHERE idempleados = '"+indiceEmpleado+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que le asigna true al valor de borrado del rubro
	public void borrarRubro(int indiceRubro) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE rubros SET borrado = 1 WHERE idrubros = '"+indiceRubro+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void borrarCategoriaEmpleado(int indiceCategoria) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE categoriaempleado SET borrado = 1 WHERE idcategoriaempleado = '"+indiceCategoria+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//Funcion que le asigna true al valor de borrado del gasto general
	public void borrarGastoGeneral(int indiceGasto) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE gastosgenerales SET borrado = 1 WHERE idgastosgenerales = '"+indiceGasto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void borrarAtributo(int indiceAtributo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE atributos SET borrado = 1 WHERE idatributos = '"+indiceAtributo+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void borrarGrupoAtributo(int indiceGrupoAtributo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			//p = (PreparedStatement)
			//		cSQL.prepareStatement("UPDATE atributos SET borrado = 1 WHERE grupoatributo = '"+indiceGrupoAtributo+"'");
			//p.executeUpdate();
			
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE grupoatributo SET borrado = 1 WHERE idgrupoatributo = '"+indiceGrupoAtributo+"'");
			p.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void loadFactura()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		java.sql.Connection c2 = null;
		java.sql.Connection c3 = null;
		java.sql.Connection c4 = null;
		java.sql.Connection c5 = null;
		java.sql.Connection c6 = null;
		java.sql.Connection c7 = null;
		java.sql.Connection c8 = null;
		java.sql.Connection c9 = null;
		java.sql.Connection c10 = null;
		java.sql.Connection c11 = null;
		java.sql.Connection c12 = null;
		Statement s = null;
		Statement s2 = null;
		Statement s3 = null;
		Statement s4 = null;
		Statement s5 = null;
		Statement s6 = null;
		Statement s7 = null;
		Statement s8 = null;
		Statement s9 = null;
		Statement s10 = null;
		Statement s11 = null;
		Statement s12 = null;
		ResultSet r = null;
		ResultSet r2 = null;
		ResultSet r3 = null;
		ResultSet r4 = null;
		ResultSet r5 = null;
		ResultSet r6 = null;
		ResultSet r7 = null;
		ResultSet r8 = null;
		ResultSet r9 = null;
		ResultSet r10 = null;
		ResultSet r11 = null;
		ResultSet r12 = null;
		int idfactura = 0;
		int idcliente = 0;
		Cliente cli = null;
		float montoTotal = 0;
		String tipoPago = null;
		float montoRecibido = 0;
		float cambio = 0;
		Date fecha = null;
		Time hora = null;
		String tipoFactura = null;
		int idProdFacturado = 0;
		int idCantProdUtil = 0;
		int cantproductoutilizadoid2 = 0;
		int idestandarrelacionado = 0;
		float cantidad = 0;
		String nombre = null;
		Producto est = null;
		ArrayList<CantProductosUtilizados> cantProdFact = new ArrayList<>();
		int idKitFacturado = 0;
		int idCantKitUtil = 0;
		int cantkitutilizadoid2 = 0;
		int idkitrelacionado = 0;
		int cantidadkit = 0;
		int idkit = 0;
		String nombrekit = null;
		Kit kit = null;
		ArrayList<CantKitsUtilizados> cantKitFact = new ArrayList<>();
		int idServ = 0;
		int idProducto = 0;
		String nombreServ = null;
		Servicio serv = null;
		ArrayList<ServicioUtilizado> serviciosFact = new ArrayList<>();
		int cantcopias = 0;
		String estado = null;
		String codigo = null;
		float adeudado = 0;
		int plazopagodias = 0;
		float porcientodescuento = 0;
		Date fechalimitedescuento = null;
		float porcientopenalizacion = 0;
		float montopagado = 0;
		Date fechaDelPago = null;
		int idProductoDelEstandar = 0;
		int idUsuario = 0;
		ArrayList<Float> pagosDeuda = new ArrayList<>();
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM facturas");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cantProdFact = new ArrayList<>();
				cantKitFact = new ArrayList<>();
				serviciosFact = new ArrayList<>();
				pagosDeuda = new ArrayList<>();
				adeudado = 0;
				idfactura = r.getInt(1);
				idcliente = r.getInt(2);
				montoTotal = r.getFloat(3);
				tipoPago = r.getString(4);
				montoRecibido = r.getFloat(5);
				cambio = r.getFloat(6);
				fecha = r.getDate(7);
				hora = r.getTime(8);
				tipoFactura = r.getString(9);
				cantcopias = r.getInt(10);
				estado = r.getString(11);
				codigo = r.getString(12);
				idUsuario = r.getInt(13);
				if(idcliente>0)
				{
					cli = Controladora.getInstance().getMisClientes().get(idcliente-1);
				}
				
				c2 = con.conectar();
				s2 = (Statement) c2.createStatement();
				r2 = s2.executeQuery("SELECT * FROM productosfacturados WHERE idfactura = '"+idfactura+"'");
				
				while(r2.next())
				{
					idProdFacturado = r2.getInt(1);
					idCantProdUtil = r2.getInt(2);
					
					c3 = con.conectar();
					s3 = (Statement) c3.createStatement();
					r3 = s3.executeQuery("SELECT * FROM cantproductosutilizados WHERE idcantproductosutilizados = '"+idCantProdUtil+"'");
					while(r3.next())
					{
						cantproductoutilizadoid2 = r3.getInt(1);
						idestandarrelacionado = r3.getInt(2);
						cantidad = r3.getFloat(3);
						
						c12 = con.conectar();
						s12 = (Statement) c12.createStatement();
						r12 = s12.executeQuery("SELECT producto FROM estandar WHERE idestandar = '"+idestandarrelacionado+"'");
						while(r12.next())
						{
							idProductoDelEstandar = r12.getInt(1);
						}

						c12.close();

						
						c4 = con.conectar();
						s4 = (Statement) c4.createStatement();
						r4 = s4.executeQuery("SELECT nombre FROM productos WHERE idproductos = '"+idProductoDelEstandar+"'");
						while(r4.next())
						{
							nombre = r4.getString(1);
							est = buscarProducto(nombre);
						}
						c4.close();
						s4.close();
						r4.close();
						CantProductosUtilizados cpu = new CantProductosUtilizados(est, cantidad);
						System.out.println("CantProductosUtilizados: " + cpu.getNombre() + " " + cpu.getCantidad());
						cantProdFact.add(cpu);
						Controladora.getInstance().getMisCantProductosUtilizados().add(cpu); 
					}
				}
				
				c5 = con.conectar();
				s5 = (Statement) c5.createStatement();
				r5 = s5.executeQuery("SELECT * FROM kitsfacturados WHERE idfactura = '"+idfactura+"'");
				
				while(r5.next())
				{
					idKitFacturado = r5.getInt(1);
					idCantKitUtil = r5.getInt(2);
					
					c6 = con.conectar();
					s6 = (Statement) c6.createStatement();
					r6 = s6.executeQuery("SELECT * FROM cantkitsutilizados WHERE idcantkitsutilizados = '"+idCantKitUtil+"'");
					while(r6.next())
					{
						cantkitutilizadoid2 = r6.getInt(1);
						idkitrelacionado = r6.getInt(2);
						cantidadkit = r6.getInt(3);
						
						c9 = con.conectar();
						s9 = (Statement) c9.createStatement();
						r9 = s9.executeQuery("SELECT producto FROM kit WHERE idkit = '"+idkitrelacionado+"'");
						while(r9.next())
						{
							idkit = r.getInt(1);
						}
						
						c7 = con.conectar();
						s7 = (Statement) c7.createStatement();
						r7 = s7.executeQuery("SELECT nombre FROM productos WHERE idproductos = '"+idkit+"'");
						while(r7.next())
						{
							nombrekit = r7.getString(1);
							System.out.println(nombrekit);
							kit = (Kit) buscarProducto(nombrekit);
							CantKitsUtilizados cku = new CantKitsUtilizados(kit, cantidad);
							System.out.println("CantKitsUtilizados: " + cku.getNombre() + " " + cku.getCantidad());
							cantKitFact.add(cku);
							Controladora.getInstance().getMisCantKitsUtilizados().add(cku);
							c7.close();
							s7.close();
							r7.close();
							c9.close();
							s9.close();
							r9.close();
						}
					}
				}
				
				c8 = con.conectar();
				s8 = (Statement) c8.createStatement();
				r8 = s8.executeQuery("SELECT * FROM serviciosfacturado WHERE idfactura = '"+idfactura+"'");
				
				while(r8.next())
				{
					idServ = r8.getInt(2);
						
					serv = Controladora.getInstance().getMisProductosServicio().get(idServ-1);
					ServicioUtilizado servutil = new ServicioUtilizado(serv); 
					System.out.println("ServicioUtilizado: " + servutil.getNombre());
					serviciosFact.add(servutil);
				}
				
				c10 = con.conectar();
				s10 = (Statement) c10.createStatement();
				r10 = s10.executeQuery("SELECT * FROM facturacreditocliente WHERE factura = '"+idfactura+"'");
				
				while(r10.next())
				{
					adeudado = r10.getFloat(3);
					plazopagodias = r10.getInt(4);
					porcientodescuento = r10.getFloat(5);
					fechalimitedescuento = r10.getDate(6);
					porcientopenalizacion = r10.getFloat(7);
						
				}
				
				c10.close();
				
				c11 = con.conectar();
				s11 = (Statement) c11.createStatement();
				r11 = s11.executeQuery("SELECT * FROM pagosfacturacreditocliente WHERE factura = '"+idfactura+"'");
				
				while(r11.next())
				{
					montopagado = r11.getFloat(3);
					fechaDelPago = r11.getDate(4);
					pagosDeuda.add(montopagado);
				}
				
				Factura fact = new Factura(cantProdFact, cantKitFact, serviciosFact, montoTotal, tipoPago, montoRecibido, cambio, cli, tipoFactura, cantcopias, estado);
				System.out.println("cantProdFact size: " + cantProdFact.size());
				System.out.println("cantKitFact size: " + cantKitFact.size());
				System.out.println("serviciosFact size: " + serviciosFact.size());
				//LocalDateTime fh = LocalDateTime.of(LocalDate.parse(fecha.toString()), LocalTime.parse(hora.toString())); 
				fact.setFecha(LocalDate.parse(fecha.toString()));
				fact.setHora(LocalTime.parse(hora.toString()));
				fact.setCodigo(codigo);
				fact.setAdeudado(adeudado);
				System.out.println("adeudado: " + fact.getAdeudado());
				fact.setPlazoPagoDias(plazopagodias);
				fact.setPorcientoDescuento(porcientodescuento);
				if(pagosDeuda.size() == 0)
				{
					System.out.println("pagos deuda est� vacio");
				}
				for (Float f : pagosDeuda) {
					System.out.println("Valor del pago deuda: " + f);
				}
				fact.setPagosDeuda(pagosDeuda);
				Usuario usu = Controladora.getInstance().getMisUsuarios().get(idUsuario-1);
				fact.setUsuarioFacturador(usu);

				if(fechalimitedescuento != null)
				{
					fact.setFechaLimiteDescuento(LocalDate.parse(fechalimitedescuento.toString()));
				}
				fact.setPorcientoPenalizacion(porcientopenalizacion);
				fact.setMontoDelUltimoPago(montopagado);
				if(fechaDelPago != null)
				{
					fact.setFechaDelUltimoPago(LocalDate.parse(fechaDelPago.toString()));
				}
				getMisFacturas().add(fact);
				/*cantProdFact.clear();
				cantKitFact.clear();
				serviciosFact.clear();*/
				for(CantProductosUtilizados cant : fact.getProdFacturados()) {
					System.out.println("El nombre del producto es: " + cant.getNombre());
				}
				
				if(c2!=null) {
					c2.close();
				}
				
				if(c3!=null) {
					c3.close();
				}
				
				if(c4!=null) {
					c4.close();
				}
				
				if(c5!=null) {
					c5.close();
				}
				
				if(c6!=null) {
					c6.close();
				}
				
				if(c7!=null) {
					c7.close();
				}
				
				if(c8!=null) {
					c8.close();
				}
				
				if(c9!=null) {
					c9.close();
				}
				
				if(s2!=null) {
					s2.close();
				}
				
				if(s3!=null) {
					s3.close();
				}
				
				if(s4!=null) {
					s4.close();
				}
				
				if(s5!=null) {
					s5.close();
				}
				
				if(s6!=null) {
					s6.close();
				}
				
				if(s7!=null) {
					s7.close();
				}
				
				if(s8!=null) {
					s8.close();
				}
				
				if(s9!=null) {
					s9.close();
				}
				
				if(r2!=null) {
					r2.close();
				}
				
				if(r3!=null) {
					r3.close();
				}
				
				if(r4!=null) {
					r4.close();
				}
				
				if(r5!=null) {
					r5.close();
				}
				
				if(r6!=null) {
					r6.close();
				}
				
				if(r7!=null) {
					r7.close();
				}
				
				if(r8!=null) {
					r8.close();
				}
				
				if(r9!=null) {
					r9.close();
				}
			}
			
			for (Factura fac : misFacturas) {
				
				System.out.println("cantProdFact size2: " + fac.getProdFacturados().size());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(c2!=null) {
					c2.close();
				}
				
				if(c3!=null) {
					c3.close();
				}
				
				if(c4!=null) {
					c4.close();
				}
				
				if(c5!=null) {
					c5.close();
				}
				
				if(c6!=null) {
					c6.close();
				}
				
				if(c7!=null) {
					c7.close();
				}
				
				if(c8!=null) {
					c8.close();
				}
				
				if(c9!=null) {
					c9.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(s2!=null) {
					s2.close();
				}
				
				if(s3!=null) {
					s3.close();
				}
				
				if(s4!=null) {
					s4.close();
				}
				
				if(s5!=null) {
					s5.close();
				}
				
				if(s6!=null) {
					s6.close();
				}
				
				if(s7!=null) {
					s7.close();
				}
				
				if(s8!=null) {
					s8.close();
				}
				
				if(s9!=null) {
					s9.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
				if(r2!=null) {
					r2.close();
				}
				
				if(r3!=null) {
					r3.close();
				}
				
				if(r4!=null) {
					r4.close();
				}
				
				if(r5!=null) {
					r5.close();
				}
				
				if(r6!=null) {
					r6.close();
				}
				
				if(r7!=null) {
					r7.close();
				}
				
				if(r8!=null) {
					r8.close();
				}
				
				if(r9!=null) {
					r9.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<CantKitsUtilizados> getMisCantKitsUtilizados() {
		return misCantKitsUtilizados;
	}

	public void setMisCantKitsUtilizados(ArrayList<CantKitsUtilizados> misCantKitsUtilizados) {
		this.misCantKitsUtilizados = misCantKitsUtilizados;
	}


	public ArrayList<Factura> getMisFacturasValorFiscal() {
		return misFacturasValorFiscal;
	}


	public void setMisFacturasValorFiscal(ArrayList<Factura> misFacturasValorFiscal) {
		this.misFacturasValorFiscal = misFacturasValorFiscal;
	}

	public void calcularDeudafacturas(LocalDate inicio, LocalDate fin)
	{
		deudaTotal = 0;
		
		if(inicio != null && fin != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin)))
					{
						deudaTotal += fac.getAdeudado();
					}
				}
			}
		}
		
		else
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					deudaTotal += fac.getAdeudado();
				}
			}
		}
	}


	public float getDeudaTotal() {
		return deudaTotal;
	}


	public void setDeudaTotal(float deudaTotal) {
		this.deudaTotal = deudaTotal;
	}


	public float getGananciaVentasPagadas() {
		return gananciaVentasPagadas;
	}


	public void setGananciaVentasPagadas(float gananciaVentasPagadas) {
		this.gananciaVentasPagadas = gananciaVentasPagadas;
	}

	public void calcularGananciaVentasPagadas(LocalDate inicio, LocalDate fin)
	{
		gananciaVentasPagadas = 0;
		
		if(inicio != null && fin != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin)))
					{
						gananciaVentasPagadas += fac.calcularGanancia();
					}
				}
			}
		}
		
		else
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					gananciaVentasPagadas += fac.calcularGanancia();
				}
			}
		}
	}
	
	public int calcularCantidadVentasPagadas(LocalDate inicio, LocalDate fin)
	{
		int cantidad = 0;
		
		if(inicio != null && fin != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if(fac.getAdeudado() == 0 && ((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin))))
					{
						cantidad++;
					}
				}
			}
		}
		
		else {
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if(fac.getAdeudado() == 0)
					{
						cantidad++;
					}
				}
			}
		}
		
		return cantidad;
	}
	
	public int calcularCantidadPeticionesPagadas(LocalDate inicio, LocalDate fin)
	{
		int cantidad = 0;
		
		if(inicio != null && fin != null)
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion p : misPeticiones) {
					if(p.getAdeudado() == 0 && ((p.getFecha().isAfter(inicio) && p.getFecha().isBefore(fin)) || (p.getFecha().isEqual(inicio) || p.getFecha().isEqual(fin))))
					{
						cantidad++;
					}
				}
			}
		}
		
		else
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion pet : misPeticiones) {
					if(pet.getAdeudado() == 0)
					{
						cantidad++;
					}
				}
			}
		}
		
		return cantidad;
	}
	
	public void calcularIngresosVentasPagadas(LocalDate inicio, LocalDate fin)
	{
		ingresosVentasPagadas = 0;
		
		if(inicio != null && fin != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin)))
					{
						//setIngresosVentasPagadas(getIngresosVentasPagadas() + fac.calcularIngreso());
						setIngresosVentasPagadas(fac.calcularIngreso());
					}
				}
			}
		}
		else
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					//setIngresosVentasPagadas(getIngresosVentasPagadas() + fac.calcularIngreso());
					setIngresosVentasPagadas(fac.calcularIngreso());
				}
			}
		}	
	}
	
	public float calcularVentasPagadasPorDia(LocalDate dia)
	{
		float ventas = 0;
		
		if(dia != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if(fac.getFecha().isEqual(dia))
					{
						ventas += fac.calcularIngreso();
					}
				}
			}
		}
		
		return ventas;
	}
	
	public void calcularIngresosVentasPorPagar(LocalDate inicio, LocalDate fin)
	{
		ingresosVentasPorPagar = 0;
		
		if(inicio != null && fin != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin)))
					{
						ingresosVentasPorPagar += fac.calcularIngresoPorPagar();
					}
				}
			}
		}
		
		else
		{
			if(misFacturas.size() > 0)
			{
				System.out.println("Entre el if de calcular los ingresos por pagar");
				for (Factura fac : misFacturas) {
					ingresosVentasPorPagar += fac.calcularIngresoPorPagar();
				}
			}
		}
		
	}


	public float getIngresosVentasPagadas() {
		return ingresosVentasPagadas;
	}


	public void setIngresosVentasPagadas(float ingresosVentasPagadas) {
		this.ingresosVentasPagadas = ingresosVentasPagadas;
	}


	public float getIngresosVentasPorPagar() {
		return ingresosVentasPorPagar;
	}


	public void setIngresosVentasPorPagar(float ingresosVentasPorPagar) {
		this.ingresosVentasPorPagar = ingresosVentasPorPagar;
	}
	
	public void calcularIngresoTotal()
	{
		ingresoTotal = ingresosVentasPagadas + ingresosVentasPorPagar;
		//ingresoTotal = ingresosVentasPagadas + pagosDeudasClientesTotal;
	}
	
	public void calcularGanaciaTotal(LocalDate inicio, LocalDate fin)
	{
		gananciasTotal = 0;
		
		if(inicio != null && fin != null)
		{
			for (Factura fac : misFacturas) {
				if((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin)))
				{
					gananciasTotal += fac.calcularGananciaIncluyendoDeuda();
				}
			}
		}
		
		else
		{
			for (Factura fac : misFacturas) {
				gananciasTotal += fac.calcularGananciaIncluyendoDeuda();
			}
		}
	}
	
	public void calcularPagosDeudasClientesTotal(LocalDate inicio, LocalDate fin)
	{
		pagosDeudasClientesTotal = 0;
		
		if(inicio != null && fin != null)
		{
			if(!misFacturas.isEmpty())
			{
				for (Factura fac : misFacturas) {
					if((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin)))
					{
						pagosDeudasClientesTotal += fac.calcularPagos();
					}
				}
			}
		}
		
		else
		{
			if(!misFacturas.isEmpty())
			{
				for (Factura fac : misFacturas) {
					pagosDeudasClientesTotal += fac.calcularPagos();
				}
			}
		}
	}

	public float calcularPagosDeVentasPorDia(LocalDate dia)
	{
		float pagos = 0;
		
		if(dia != null)
		{
			if(!misFacturas.isEmpty())
			{
				for (Factura fac : misFacturas) {
					if(fac.getFecha().isEqual(dia))
					{
						pagos += fac.calcularPagos();
					}
				}
			}
		}
		
		return pagos;
	}
	
	public float getIngresoTotal() {
		return ingresoTotal;
	}


	public void setIngresoTotal(float ingresoTotal) {
		this.ingresoTotal = ingresoTotal;
	}
	
	public int cantidadVentasPorCobrar(LocalDate inicio, LocalDate fin)
	{
		int cantidad = 0;
		
		if(inicio != null && fin != null)
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if(fac.getAdeudado() > 0 && ((fac.getFecha().isAfter(inicio) && fac.getFecha().isBefore(fin)) || (fac.getFecha().isEqual(inicio) || fac.getFecha().isEqual(fin))))
					{
						cantidad++;
					}
				}
			}
		}
		
		else
		{
			if(misFacturas.size() > 0)
			{
				for (Factura fac : misFacturas) {
					if(fac.getAdeudado() > 0)
					{
						cantidad++;
					}
				}
			}
		}
		
		
		return cantidad;
	}

	public void guardarFacturaCreditoClienteSQL(Factura factura) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO facturacreditocliente (factura, adeudado, plazopagodias, porcientodescuento, fechalimitedescuento, porcientopenalizacion) VALUES (?, ?, ?, ?, ?, ?)");
			p.setInt(1, getMisFacturas().indexOf(factura)+1);
			p.setFloat(2, factura.getAdeudado());
			p.setInt(3, factura.getPlazoPagoDias());
			p.setFloat(4, factura.getPorcientoDescuento());
			if(!(factura.getFechaLimiteDescuento() == null))
			{
				p.setDate(5, (java.sql.Date.valueOf(factura.getFechaLimiteDescuento())));
			}
			else
			{
				p.setDate(5, null);
			}
			p.setFloat(6, factura.getPorcientoPenalizacion());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
		
	}
	
	
	public boolean activarLoadFacturaCreditoClienteSQL()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM facturacreditocliente");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadFacturas()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM facturas");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public boolean activarLoadPromocion()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		boolean activar = false;
		int cuenta = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM PROMOCION");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				cuenta = r.getInt(1);
			}
			
			if(cuenta > 0)
			{
				activar = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return activar;
	}
	
	public void loadFacturaCreditoCliente()
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int id = 0;
		int idFactura = 0;
		float adeudado = 0;
		int plazopagodias = 0;
		float porcientodescuento = 0;
		Date fechalimitedescuento = null;
		float porcientopenalizacion = 0;
		
		try {
			
			//Recuperar precios
			c = con.conectar();
			
			//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT * FROM facturacreditocliente");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				id = r.getInt(1);
				idFactura = r.getInt(2);
				adeudado = r.getFloat(3);
				plazopagodias = r.getInt(4);
				porcientodescuento = r.getFloat(5);
				fechalimitedescuento = r.getDate(6);
				porcientopenalizacion = r.getFloat(7);
				
				Factura fac = getMisFacturas().get(idFactura-1);
				fac.setAdeudado(adeudado);
				fac.setPlazoPagoDias(plazopagodias);
				fac.setPorcientoDescuento(porcientodescuento);
				fac.setFechaLimiteDescuento(LocalDate.parse(fechalimitedescuento.toString()));
				fac.setPorcientoPenalizacion(porcientopenalizacion);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
		finally {
			try {
				
				if(c!=null) {
					c.close();
				}
				
				if(s!=null) {
					s.close();
				}
				
				if(r!=null) {
					r.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<Cargo> getMisCargos() {
		return misCargos;
	}

	public void setMisCargos(ArrayList<Cargo> misCargos) {
		this.misCargos = misCargos;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}



	public void actualizarProveedorPorDefecto(String nombre) {
		
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("UPDATE proveedores SET nombre = '"+nombre+"' WHERE idproveedores = 1");
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
		
	}
	
public void pagarDeudaPeticion(Peticion peticion, float monto) {
		
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("UPDATE peticionescredito SET adeudado = '"+peticion.getAdeudado()+"' WHERE peticion = '"+(Controladora.getInstance().getMisPeticiones().indexOf(peticion)+1)+"'");
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
		
	}
	
	public float calculoGananciasTotalReporte()
	{
		gananciasTotal = 0;
		
		if(misFacturas.size() > 0)
		{
			for (Factura fac : misFacturas) {
				gananciasTotal += fac.calcularGananciaIncluyendoDeuda();
			}
		}
		
		return gananciasTotal;
	}
	
	public float calculoEgresosPagos(LocalDate inicio, LocalDate fin)
	{
		egresosPagos = 0;
		
		if(inicio != null && fin != null)
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion p : misPeticiones) {
					if(p.getAdeudado() == 0 && ((p.getFecha().isAfter(inicio) && p.getFecha().isBefore(fin)) || (p.getFecha().isEqual(inicio) || p.getFecha().isEqual(fin))))
					{
						egresosPagos += p.getMonto();
					}
				}
			}
		}
		else
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion p : misPeticiones) {
					if(p.getAdeudado() == 0)
					{
						egresosPagos += p.getMonto();
					}
				}
			}
		}
		
		return egresosPagos;
	}
	
	public float calculoComprasPagadasPorDia(LocalDate dia)
	{
		float compras = 0;
		
		if(dia != null)
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion p : misPeticiones) {
					if(p.getFecha().isEqual(dia))
					{
						compras += p.getMonto();
					}
				}
			}
		}
		
		return compras;
	}

	public float getEgresosPagos() {
		return egresosPagos;
	}

	public void setEgresosPagos(float egresosPagos) {
		this.egresosPagos = egresosPagos;
	}
	
	public int calcularCantidadPeticionesPorPagar(LocalDate inicio, LocalDate fin)
    {
    	int cantidad = 0;
    	
    	if(inicio != null && fin != null)
    	{
    		if(getMisPeticiones().size() > 0)
        	{
        		for (Peticion pet : getMisPeticiones()) {
    				if(pet.getAdeudado() > 0 && ((pet.getFecha().isAfter(inicio) && pet.getFecha().isBefore(fin)) || (pet.getFecha().isEqual(inicio) || pet.getFecha().isEqual(fin))))
    				{
    					cantidad++;
    				}
    			}
        	}
    	}
    	else
    	{
    		if(getMisPeticiones().size() > 0)
        	{
        		for (Peticion pet : getMisPeticiones()) {
    				if(pet.getAdeudado() > 0)
    				{
    					cantidad++;
    				}
    			}
        	}
    	}
    	
    	return cantidad;
    }
	
	public float calcularEgresosPeticionesPorPagar(LocalDate inicio, LocalDate fin)
	{
		setEgresosPorPagar(0);
		
		if(inicio != null && fin != null)
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion p : misPeticiones) {
					if(p.getAdeudado() > 0 && ((p.getFecha().isAfter(inicio) && p.getFecha().isBefore(fin)) || (p.getFecha().isEqual(inicio) || p.getFecha().isEqual(fin))))
					{
						egresosPorPagar += p.getMonto();
					}
				}
			}
		}
		
		else {
			if(misPeticiones.size() > 0)
			{
				for (Peticion p : misPeticiones) {
					if(p.getAdeudado() > 0)
					{
						egresosPorPagar += p.getMonto();
					}
				}
			}
		}
		
		return egresosPorPagar;
	}

	public float getEgresosPorPagar() {
		return egresosPorPagar;
	}

	public void setEgresosPorPagar(float egresosPorPagar) {
		this.egresosPorPagar = egresosPorPagar;
	}
	
	public float calcularMontoPagosPeticionesPorPagarTotal(LocalDate inicio, LocalDate fin)
	{
		setMontoPagoPeticionesPorPagarTotal(0);
		if(inicio != null && fin != null)
		{
			if(getMisPeticiones().size() > 0)
			{
				for (Peticion pet : misPeticiones) {
					if((pet.getFecha().isAfter(inicio) && pet.getFecha().isBefore(fin)) || (pet.getFecha().isEqual(inicio) || pet.getFecha().isEqual(fin)))
					{
						montoPagoPeticionesPorPagarTotal += pet.calcularPagosPeticion();
					}
				}
			}
		}
		
		else {
			if(getMisPeticiones().size() > 0)
			{
				for (Peticion pet : misPeticiones) {
					montoPagoPeticionesPorPagarTotal += pet.calcularPagosPeticion();
				}
			}
		}
		
		return montoPagoPeticionesPorPagarTotal;
	}

	public float calcularPagosDeComprasPorDia(LocalDate dia)
	{
		float pagos = 0;
		
		if(dia != null)
		{
			if(getMisPeticiones().size() > 0)
			{
				for (Peticion pet : misPeticiones) {
					if(pet.getFecha().isEqual(dia))
					{
						pagos += pet.calcularPagosPeticion();
					}
				}
			}
		}
		
		return pagos;
	}
	
	public float calcularPagoEmpleadosPorDia()
	{
		float pago = 0;
		
		if(!misEmpleados.isEmpty())
		{
			for (Empleado e : misEmpleados) {
				pago += e.getSueldo();
			}
		}
		
		return pago;
	}
	
	public float getMontoPagoPeticionesPorPagarTotal() {
		return montoPagoPeticionesPorPagarTotal;
	}

	public void setMontoPagoPeticionesPorPagarTotal(float montoPagoPeticionesPorPagarTotal) {
		this.montoPagoPeticionesPorPagarTotal = montoPagoPeticionesPorPagarTotal;
	}
	
	public float calcularDeudaPeticionesTotal(LocalDate inicio, LocalDate fin)
	{
		deudaPeticionesTotal = 0;
		
		if(inicio != null && fin != null)
		{
			if(misPeticiones.size() > 0)
			{
				for (Peticion pet : misPeticiones) {
					if(pet.getAdeudado() > 0 && ((pet.getFecha().isAfter(inicio) && pet.getFecha().isBefore(fin)) || (pet.getFecha().isEqual(inicio) || pet.getFecha().isEqual(fin))))
					{
						deudaPeticionesTotal += pet.getAdeudado();
					}
				}
			}
		}
		
		else {
			if(misPeticiones.size() > 0)
			{
				for (Peticion pet : misPeticiones) {
					if(pet.getAdeudado() > 0)
					{
						deudaPeticionesTotal += pet.getAdeudado();
					}
				}
			}
		}
		
		return deudaPeticionesTotal;
	}
	
	public void editarPrecio(int indexPrecio, float precio) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE precio SET precio = '"+precio+"' WHERE idprecio = '"+indexPrecio+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void editarProductoCosto(int indexProducto, float costo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE productos SET costo = '"+costo+"' WHERE idproductos = '"+indexProducto+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void editarManoDeObraCosto(int indexManoDeObra, float costo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE manodeobra SET costo = '"+costo+"' WHERE idmanodeobra = '"+indexManoDeObra+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void editarEstandarCostoManoObra(int indexEstandar, float costo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE estandar SET manodeobra = '"+costo+"' WHERE idestandar = '"+indexEstandar+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void editarSueldoCategoriaEmpleado(int indexEmpleado, int indexNewCategoria, float sueldo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE empleados SET sueldo = '"+sueldo+"' WHERE idempleados = '"+indexEmpleado+"'");
			p.executeUpdate();
			
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE empleados SET categoria = '"+indexNewCategoria+"' WHERE idempleados = '"+indexEmpleado+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void editarCategoriaManoObraEstandar(int indexNewCategoria, int indexManoObra) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE manodeobraproducto SET categoriaempleado = '"+indexNewCategoria+"' WHERE manodeobra = '"+indexManoObra+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void editarCategoriaManoObraServicio(int indexNewCategoria, int indexManoObra) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE manodeobraservicio SET categoriaempleado = '"+indexNewCategoria+"' WHERE manodeobra = '"+indexManoObra+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			Controladora.getInstance().print_exception(e);
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**public void editarServicioCostoManoObra(int indexServicio, float costo) {
		Conexion con = new Conexion();
		java.sql.Connection cSQL = null;
		Statement sSQL = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			cSQL = con.conectar();
			sSQL = (Statement) cSQL.createStatement();
			p = (PreparedStatement)
					cSQL.prepareStatement("UPDATE servicios SET manodeobra = '"+costo+"' WHERE idproductos = '"+indexServicio+"'");
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(cSQL!=null) {
					cSQL.close();
				}
				
				if(sSQL!=null) {
					sSQL.close();
				}
				
				if(r!=null) {
					r.close();
				}
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}**/
	
	
public void reiniciarInfoEmpresa() {
		
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("ALTER TABLE infoempresa ALTER COLUMN IDINFOEMPRESA RESTART WITH 1");
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			p = (PreparedStatement) c.prepareStatement("ALTER TABLE rangonumerosvalorfiscal ALTER COLUMN IDRANGONUMEROSVALORFISCAL RESTART WITH 1");
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			p = (PreparedStatement) c.prepareStatement("ALTER TABLE aniofiscal ALTER COLUMN IDANIOFISCAL RESTART WITH 1");
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			
			System.out.println("AutoIncrement modified!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
		
		
	}

public void reiniciarClientes() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE clientes ALTER COLUMN IDCLIENTES RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}
	
public void reiniciarPreguntasRecuperacion()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE PREGUNTASRECUPERACION ALTER COLUMN IDPREGUNTASRECUPERACION RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
}

public void reiniciarRubros() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE rubros ALTER COLUMN IDRUBROS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarGastosGenerales() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE gastosgenerales ALTER COLUMN IDGASTOSGENERALES RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarProveedores() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE proveedores ALTER COLUMN IDPROVEEDORES RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarCategoriaEmpleado() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE categoriaempleado ALTER COLUMN IDCATEGORIAEMPLEADO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarEmpleados() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE empleados ALTER COLUMN IDEMPLEADOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarPrecios() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE precio ALTER COLUMN IDPRECIO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarProducto() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE productos ALTER COLUMN IDPRODUCTOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE estandar ALTER COLUMN IDESTANDAR RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE precioproducto ALTER COLUMN IDPRECIOPRODUCTO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}


public void reiniciarPartida() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE partida ALTER COLUMN IDPARTIDA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		if(!activarLoadProductos())
		{
			p = (PreparedStatement) c.prepareStatement("ALTER TABLE estandar ALTER COLUMN IDESTANDAR RESTART WITH 1");
			
			//ejecutar el preparedStatement
			p.executeUpdate();
		}
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE productopartida ALTER COLUMN IDPRODUCTOPARTIDA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE partidaprodutil ALTER COLUMN IDPARTIDAPRODUTIL RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		if(!Controladora.getInstance().activarLoadFacturas() && !Controladora.getInstance().activarLoadFacturaCreditoClienteSQL())
		{
			System.out.println("If para reiniciar cantidad producto utilizado");
			
			p = (PreparedStatement) c.prepareStatement("ALTER TABLE cantproductosutilizados ALTER COLUMN IDCANTPRODUCTOSUTILIZADOS RESTART WITH 1");
		
			//ejecutar el preparedStatement
			p.executeUpdate();
		}
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarGrupoAtributos() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE grupoatributo ALTER COLUMN IDGRUPOATRIBUTO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarAtributos() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE atributos ALTER COLUMN IDATRIBUTOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarMatriz() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE combinaciones ALTER COLUMN IDCOMBINACIONES RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE combinacionesatributos ALTER COLUMN IDCOMBINACIONESATRIBUTOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE matriz ALTER COLUMN IDMATRIZ RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarKit() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE kit ALTER COLUMN IDKIT RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE kitproductos ALTER COLUMN IDKITPRODUCTOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		if(!Controladora.getInstance().activarLoadFacturas() && !Controladora.getInstance().activarLoadFacturaCreditoClienteSQL())
		{
			System.out.println("If para reiniciar cantidad producto utilizado");
			
			p = (PreparedStatement) c.prepareStatement("ALTER TABLE cantproductosutilizados ALTER COLUMN IDCANTPRODUCTOSUTILIZADOS RESTART WITH 1");
		
			//ejecutar el preparedStatement
			p.executeUpdate();
		}
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarServicio() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE servicios ALTER COLUMN IDSERVICIOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE serviciomateriales ALTER COLUMN IDSERVICIOMATERIALES RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarManoDeObra() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE manodeobra ALTER COLUMN IDMANODEOBRA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE manodeobraproducto ALTER COLUMN IDMANODEOBRAPRODUCTO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE manodeobraservicio ALTER COLUMN IDMANODEOBRASERVICIO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarCostoIndirecto() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE costoindirecto ALTER COLUMN IDCOSTODIRECTO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarPromedioGananciaAnual() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE promediogananciaanual ALTER COLUMN IDPROMEDIOGANANCIAANUAL RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarUsuarios() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE usuarios ALTER COLUMN IDUSUARIOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE usuariocontrasena ALTER COLUMN IDUSUARIOCONTRASENA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE usuariocargo ALTER COLUMN IDUSUARIOCARGO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarFactura() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE facturas ALTER COLUMN IDFACTURAS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE productosfacturados ALTER COLUMN IDPRODUCTOSFACTURADOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE kitsfacturados ALTER COLUMN IDKITSFACTURADOS RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE serviciosfacturado ALTER COLUMN IDSERVICIOSFACTURADO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE facturacreditocliente ALTER COLUMN IDFACTURACREDITOCLIENTE RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE pagosfacturacreditocliente ALTER COLUMN IDPAGOSFACTURACREDITOCLIENTE RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarPromocion() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE promocion ALTER COLUMN IDPROMOCION RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE promoproducto ALTER COLUMN IDPROMOPRODUCTO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarImagenProducto() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE imagenproducto ALTER COLUMN IDIMAGENPRODUCTO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarFacturaCreditoCliente() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c2 = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	PreparedStatement p2 = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE facturacreditocliente ALTER COLUMN IDFACTURACREDITOCLIENTE RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		c2 = con.conectar();
		
		p2 = (PreparedStatement) c2.prepareStatement("ALTER TABLE PAGOSFACTURACREDITOCLIENTE ALTER COLUMN IDPAGOSFACTURACREDITOCLIENTE RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p2.executeUpdate(); 
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(c2!=null) {
						c2.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarPeticiones() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE peticiones ALTER COLUMN IDPETICIONES RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarPeticionesCredito() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE peticionescredito ALTER COLUMN IDPETICIONESCREDITO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarPagoPeticionesCredito() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE pagopeticionescredito ALTER COLUMN IDPAGOPETICIONESCREDITO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarCajaChica() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE transaccionescajachica ALTER COLUMN IDTRANSACCIONESCAJACHICA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE cajachica ALTER COLUMN IDCAJACHICA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void reiniciarCuentaBancaria() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE transaccionescuentabancaria ALTER COLUMN IDTRANSACCIONESCUENTABANCARIA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE montocuentabancaria ALTER COLUMN IDMONTOCUENTABANCARIA RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public void RecordarContrasena(String usuario)
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c3 = null;
	Statement s = null;
	Statement s3 = null;
	ResultSet r = null;
	ResultSet t = null;
	PreparedStatement p = null;
	int idUsuario = 0;
	
	try {
		c3 = con.conectar();
		
		s3 = (Statement) c3.createStatement();
		t = s3.executeQuery("SELECT * FROM usuarios where usuario = " + "'" + usuario + "'" + ";");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(t.next())
		{
			idUsuario = t.getInt(1);
			
		}
		
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("INSERT INTO usuariorecordado (idusuario) VALUES (?)");
		p.setInt(1, idUsuario);
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("Datos guardados!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(c3!=null) {
						c3.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(s3!=null) {
						s3.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
					if(t!=null) {
						t.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
}

public void OlvidarContrasena(String usuario)
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c3 = null;
	Statement s = null;
	Statement s3 = null;
	ResultSet r = null;
	ResultSet t = null;
	PreparedStatement p = null;
	int idUsuario = 0;
	
	try {
		//c3 = con.conectar();
		
		//s3 = (Statement) c3.createStatement();
		//t = s3.executeQuery("SELECT * FROM usuarios where usuario = " + "'" + usuario + "'" + ";");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		//while(t.next())
		//{
			//idUsuario = t.getInt(1);
			
		//}
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("delete from usuariorecordado where idusuariorecordado = 1");
		//p.setInt(1, idUsuario);
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		System.out.println("Datos guardados!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
}

public void reiniciarUsuarioRecordado() {
	
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		c = con.conectar();
		
		p = (PreparedStatement) c.prepareStatement("ALTER TABLE USUARIORECORDADO ALTER COLUMN IDUSUARIORECORDADO RESTART WITH 1");
		
		//ejecutar el preparedStatement
		p.executeUpdate();
		
		System.out.println("AutoIncrement modified!");
		
	} catch (Exception e2) {
		e2.printStackTrace();
		Controladora.getInstance().print_exception(e2);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
			finally {
				try {
					
					if(c!=null) {
						c.close();
					}
					
					if(s!=null) {
						s.close();
					}
					
					if(r!=null) {
						r.close();
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	}
	
	
}

public boolean activarUsuarioRecordado()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	Statement s = null;
	ResultSet r = null;
	boolean activar = false;
	int cuenta = 0;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT COUNT(*) AS TOTAL FROM usuariorecordado");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			cuenta = r.getInt(1);
		}
		
		if(cuenta > 0)
		{
			activar = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return activar;
}

public boolean verificarUsuarioRecordado(String usuario, String contrasena)
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c2 = null;
	java.sql.Connection c3 = null;
	Statement s = null;
	Statement s2 = null;
	Statement s3 = null;
	ResultSet r = null;
	ResultSet q = null;
	ResultSet t = null;
	PreparedStatement p = null;
	int idUsuario = 0;
	int idUsuario2 = 0;
	String password = null;
	boolean bandera = false;
	String nomUsu = null;
	Usuario usu = buscarUsuario(usuario, contrasena);
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM usuariorecordado");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			idUsuario = r.getInt(2);
			
		}
		
		c2 = con.conectar();
		
		s2 = (Statement) c2.createStatement();
		q = s2.executeQuery("SELECT * FROM USUARIOCONTRASENA where usuario = " + idUsuario);
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(q.next())
		{
			idUsuario2 = q.getInt(2);
			password = q.getString(3);
			
		}
		
		c3 = con.conectar();
		
		s3 = (Statement) c3.createStatement();
		t = s3.executeQuery("SELECT * FROM usuarios where idusuarios = " + idUsuario);
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(t.next())
		{
			nomUsu = t.getString(2);
			
		}
		
		System.out.println("Usu1: " + idUsuario + " Usu2: " + idUsuario2 + " contrasena: " + contrasena + " password: " + password);
		
		if(idUsuario != 0)
		{
			if(idUsuario == idUsuario2 && password.equals(contrasena) && nomUsu.equals(usuario))
			{
				System.out.println("Entre a el if de la bandera de verificacion");
				bandera = true;
				System.out.println("Valor de la bandera: " + bandera);
			}
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(c2!=null) {
				c2.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(s2!=null) {
				s2.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			if(q!=null) {
				q.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return bandera;
}

public String datosUsuarioRecordado()
{
	Conexion con = new Conexion();
	java.sql.Connection c = null;
	java.sql.Connection c2 = null;
	java.sql.Connection c3 = null;
	Statement s = null;
	Statement s2 = null;
	Statement s3 = null;
	ResultSet r = null;
	ResultSet q = null;
	ResultSet t = null;
	int idUsuario = 0;
	int idUsuario2 = 0;
	String password = null;
	String username = null;
	
	try {
		
		//Recuperar precios
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM usuariorecordado");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			idUsuario = r.getInt(2);
			
		}
		
		c2 = con.conectar();
		
		s2 = (Statement) c2.createStatement();
		q = s2.executeQuery("SELECT * FROM USUARIOCONTRASENA where usuario = " + idUsuario);
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(q.next())
		{
			idUsuario2 = q.getInt(2);
			password = q.getString(3);
			
		}
		
		c3 = con.conectar();
		
		s3 = (Statement) c3.createStatement();
		t = s3.executeQuery("SELECT * FROM usuarios where idusuarios = " + idUsuario);
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(t.next())
		{
			username = t.getString(2);
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		Controladora.getInstance().print_exception(e);
	}
	
	//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
	finally {
		try {
			
			if(c!=null) {
				c.close();
			}
			
			if(c2!=null) {
				c2.close();
			}
			
			if(s!=null) {
				s.close();
			}
			
			if(s2!=null) {
				s2.close();
			}
			
			if(r!=null) {
				r.close();
			}
			
			if(q!=null) {
				q.close();
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	return username + " " + password;
}

	public void guardarPreguntaSeguridad(String pregunta1, String respuesta1, String pregunta2, String respuesta2, int usuarioID) {
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			System.out.println("Estoy guardando las preguntas de seguridad");
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO preguntasrecuperacion(primerapregunta, segundapregunta, primerarespuesta, segundarespuesta, usuario) VALUES (?, ?, ?, ?, ?)");
			p.setString(1, pregunta1);
			p.setString(2, pregunta2);
			p.setString(3, respuesta1);
			p.setString(4, respuesta2);
			p.setInt(5, usuarioID);
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public PreguntaSeguridad buscarPreguntaSeguridad(int usuarioID) throws SQLException {
		Conexion con = new Conexion();
		java.sql.Connection c = con.conectar();
		Statement s = null;
		ResultSet r = null;
		String query = "SELECT * FROM preguntasrecuperacion where usuario = ?";
		PreparedStatement p = c.prepareStatement(query);
		p.setInt(1, usuarioID);
		
		r = p.executeQuery();
		String pregunta1 = "";
		String pregunta2 = "";
		String respuesta1 = "";
		String respuesta2 = "";
		while(r.next()) {
			pregunta1 = r.getString("primerapregunta");
			pregunta2 = r.getString("segundapregunta");
			respuesta1 = r.getString("primerarespuesta");
			respuesta2 = r.getString("segundarespuesta");
		}
		PreguntaSeguridad ps = new PreguntaSeguridad(pregunta1, respuesta1, pregunta2, respuesta2);
		return ps;
	}

	public void nuevaContrasena(String password, Usuario usu)
	{
		Conexion con = new Conexion();
		java.sql.Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		String passHash = DigestUtils.md5Hex(password); 
		int id = Controladora.getInstance().getMisUsuarios().indexOf(usu)+1;
		
		try {
			System.out.println("Estoy guardando las preguntas de seguridad");
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("UPDATE USUARIOCONTRASENA SET contrasena = ? WHERE USUARIO = ?");
			p.setString(1, passHash);
			p.setInt(2, id);

			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Actualizacion de password realizada correctamente!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
			Controladora.getInstance().print_exception(e2);
		}
		
		//Bloque que se ejecuta obligatoriamente para cerrar todos los canales abiertos
				finally {
					try {
						
						if(c!=null) {
							c.close();
						}
						
						if(s!=null) {
							s.close();
						}
						
						if(r!=null) {
							r.close();
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		}
	}
	
	public void print_exception(Exception exception) {
		String error = "Error " + LocalDate.now() + " " + LocalTime.now().getHour() + "-" + LocalTime.now().getMinute() + "-" + LocalTime.now().getSecond() + ".txt";
		String fileName = "C:\\Users\\" + "\\" + System.getProperty("user.name") + "\\ERP Logs";
		//File file = new File(fileName);
		//file.mkdirs();
		File printFile = new File(fileName, error);
		PrintStream ps = null;
		try {
			ps = new PrintStream(printFile);
		} catch (FileNotFoundException e1) {
			printFile = new File(error);
			e1.printStackTrace();
			try {		
				ps = new PrintStream(printFile);
			} catch (FileNotFoundException e) {
			}
		}
		exception.printStackTrace(ps);
	}
}

	


