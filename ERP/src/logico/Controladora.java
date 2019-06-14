package logico;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import basededatos.Conexion;

public class Controladora implements Serializable{
	private static final long serialVersionUID = 1L;
	
	Date date = null;
	Rubro armas = new Rubro("00001", "Armas");
	Cliente cliente1 = new Cliente("000001", "Marcos", "312312", "Activo", null, "2312313");
	Cliente cliente2 = new Cliente("000002", "Saul", "312312", "Activo", null, "2312313");
	Cliente cliente3 = new Cliente("000003", "Yorman", "312312", "Activo", null, "2312313");
	Cliente cliente4 = new Cliente("000004", "Sarah", "312312", "Activo", null, "2312313");
	Cliente cliente5 = new Cliente("000005", "Michael", "312312", "Activo", null, "2312313");
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
	Volumen cucharaTe = new Volumen("Volumen", "Cuchara de té", "tsp");
	Volumen cucharaMadera = new Volumen("Volumen", "Cuchara de madera", "tbsp");
	Volumen onzafluida = new Volumen("Volumen", "Onza fluida", "foz");
	Volumen taza = new Volumen("Volumen", "Taza", "cp");
	Volumen medioLitro = new Volumen("Volumen", "Medio litro", "L/2");
	Volumen cuartogalon = new Volumen("Volumen", "Cuarto de galón", "gl/4");
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
	Area sq_centimetros = new Area("Area", "Sq Centimetros", "sqcm");
	Area sq_metros = new Area("Area", "Sq Metros", "sqm");
	ArrayList<Proveedores> provsec = new ArrayList<>();
	Moneda dola = new Moneda(50, "dolar");
	Precio pre = new Precio(1500f, "caro", true);
	//UnidadMedida lb = new UnidadMedida("Libra", "Lb");
	//Estandar pro = new Estandar(10f, 5f, 20f, date, 150f, false, "01", "pitola", "Esa vaina mata", armas, "no se", prov1, provsec, dola, "Eso no silve", lb, pre, "0f", "pis-0101", 0f, "Mata", "Puesde que no mate");
	
	private ArrayList<Cliente> misClientes;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Proveedores> misProveedores;
	
	private ArrayList<DescuentosAutomaticos> misDescuentos;
	private ArrayList<Promocion> misPromociones;
	
	private ArrayList<Rubro> misRubros;
	
	private ArrayList<Producto> misProductos;
	private ArrayList<Estandar> misProductosEstandar;
	private ArrayList<Kit> misProductosKit;
	private ArrayList<Servicio> misProductosServicio;
	private ArrayList<GrupoAtributo> misGrupoAtributo;
	private ArrayList<Atributos> misAtributos;
	private ArrayList<GastoGeneral> misGastosGenerales;
	private ArrayList<UnidadMedida> misUnidadMedida;
	private ArrayList<CategoriaEmpleado> misCategoriasEmpleado;
	private ArrayList<CantProductosUtilizados> misCantProductosUtilizados;
	private ArrayList<Precio> misPrecios;
	private ArrayList<Partida> misPartidas;
	private ArrayList<Combinaciones> misCombinaciones;
	
	private ArrayList<Area> misAreas;
	private ArrayList<Longitud> misLongitudes;
	private ArrayList<Masa> misMasas;
	private ArrayList<Volumen> misVolumenes;
	
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
		this.misUnidadMedida = new ArrayList<>();
		this.misAreas = new ArrayList<>();
		this.misLongitudes = new ArrayList<>();
		this.misMasas = new ArrayList<>();
		this.misVolumenes = new ArrayList<>();
		this.misPartidas = new ArrayList<>();
		this.misCantProductosUtilizados = new ArrayList<>();
		
		misClientes.add(cliente1);
		misClientes.add(cliente2);
		misClientes.add(cliente3);
		misClientes.add(cliente4);
		misClientes.add(cliente5);
		misProveedores.add(proveedor1);
		misProveedores.add(proveedor2);
		misProveedores.add(proveedor3);
		misProveedores.add(proveedor4);
		misProveedores.add(proveedor5);
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
		misRubros.add(armas);
		//misProductos.add(pro);
		//misProductosEstandar.add(pro);
		this.misAtributos = new ArrayList<>();
		this.misGrupoAtributo = new ArrayList<>();
		this.misGastosGenerales = new ArrayList<>();
		this.misCategoriasEmpleado = new ArrayList<>();
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
	
	public void guardarClienteSQL(Cliente cliente) {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO empleados (codigo, telefono, cumpleanos, rnc, nombre) VALUES (?, ?, ?, ?, ?, ?)");
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO empleados (nombre, telefono, domicilio, correo, rnc, sueldo, categoria, codigo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			s = (Statement) c.createStatement();	
			r = s.executeQuery("SELECT idcategoriaempleado FROM categoriaempleado WHERE nombre = '"+e.getCategoria().getNombre()+"'");
			
			while(r.next())
			{
				p.setString(1, e.getNombre());
				p.setString(2, e.getTelefono());
				p.setString(3, e.getDomicilio());
				p.setString(4, e.getCorreo());
				p.setString(5, e.getRnc());
				p.setFloat(6, e.getSueldo());
				int categoriaid = r.getInt(1);
				p.setInt(7, categoriaid);
				p.setString(8, e.getCodigo());
			}
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
		} catch (Exception e2) {
			e2.printStackTrace();
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
		
		guardarCategoriaEmpleadoSQL(c);
	}
	
	public void addGastoGeneral(GastoGeneral g)
	{
		misGastosGenerales.add(g);
		
		guardarGastoGeneralSQL(g);
	}
	
	/*public void guardarProductoEstandarSQL(Estandar estandar) {
		
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		PreparedStatement p2 = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO productos (codigo, nombre, descripcion, tipoproducto, observaciones, unidadmedida, costo) VALUES (?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, estandar.getCodigo());
			p.setString(2, estandar.getNombre());
			p.setString(3, estandar.getDescripcion());
			p.setString(4, estandar.getTipoProducto());
			p.setString(5, estandar.getObservaciones());
			p.setString(6, estandar.getUnidadMedida().getNombre());
			p.setFloat(7, estandar.getCosto());
			
			//ejecutar el preparedStatement
			p.executeUpdate();
			System.out.println("Datos guardados!");
			
			String codigoprod = estandar.getCodigo();
			
			s = (Statement) c.createStatement();
			r = s.executeQuery("SELECT idproductos FROM productos WHERE codigo = '"+codigoprod+"'");
			
			//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
			while(r.next())
			{
				int id = r.getInt(1);
				
				p2 = (PreparedStatement) c.prepareStatement("INSERT INTO estandar (producto, existmin, existmax, fechavencimiento, costocompra, fabricado, existactual, existinicial) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				p2.setInt(1, id);
				p2.setFloat(2, estandar.getExistenciaMinima());
				p2.setFloat(3, estandar.getExistenciaMaxima());
				p2.setDate(4, (java.sql.Date) estandar.getFechaVencimiento());
				p2.setFloat(5, estandar.getCostoDeCompra());
				p2.setBoolean(6, estandar.isFabricado());
				p2.setFloat(7, estandar.getExistenciaActual());
				p2.setFloat(8, estandar.getExistenciaActual());
				
			}
			
			
		} catch (Exception e) {
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
		
	}*/

	public void guardarGastoGeneralSQL(GastoGeneral g) {
		Conexion con = new Conexion();
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO precio (precio, descripcion, precioActivo) VALUES (?, ?, ?)");
			p.setFloat(1, precio.getPrecio());
			p.setString(2, precio.getDescripcion());
			p.setBoolean(3, precio.isPrecioActivo());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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

	public void guardarAreaSQL(Area area) {
		Conexion con = new Conexion();
		Connection c = null;
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

	public void guardarVolumenSQL(Volumen volumen) {
		Conexion con = new Conexion();
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO promocion (fechaInicio, fechaFinal) VALUES (?, ?)");
			p.setDate(1, (java.sql.Date) promocion.getFechaInicio());
			p.setDate(2, (java.sql.Date) promocion.getFechaFinal());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
	
	public void guardarServiciosSQL(Producto producto) {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO servicios (producto) VALUES (?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO unidadmedidaarea (servicio, cantproducto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductosServicio().indexOf(servicio)+1);
			p.setInt(2, Controladora.getInstance().getMisCantProductosUtilizados().indexOf(cantproductosutilizados));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO proveedorprincipalproducto (proveedor, producto) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProveedores().indexOf(proveedor)+1);
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO promoproducto (producto, promocion, precioproducto) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.setInt(2, Controladora.getInstance().getMisPromociones().indexOf(promocion)+1);
			p.setFloat(3, producto.getPrecio());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO productos (codigo, nombre, descripcion, tipoproducto, observaciones, unidadmedida, costo) VALUES (?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, producto.getCodigo());
			p.setString(2, producto.getNombre());
			p.setString(3, producto.getDescripcion());
			p.setString(4, producto.getTipoProducto());
			p.setString(5, producto.getObservaciones());
			p.setString(6, producto.getUnidadMedida().getNombre());
			p.setFloat(7, producto.getCosto());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
		Connection c = null;
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
		Connection c = null;
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
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
		Connection c = null;
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
	
	public void guardarKitSQL(Producto producto) {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO kit (producto) VALUES (?, ?, ?, ?, ?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto)+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO grupoatributo (nombre) VALUES (?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, grupoatributo.getNombre());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO estandar (producto, existmin, existmax, fechavencimiento, costocompra, fabricado, existactual, existinicial) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(estandar)+1);
			p.setInt(2, Math.round(estandar.getExistenciaMinima()));
			p.setInt(3, Math.round(estandar.getExistenciaMaxima()));
			p.setDate(4, (java.sql.Date) estandar.getFechaVencimiento());
			p.setFloat(5, estandar.getCostoDeCompra());
			p.setBoolean(6, estandar.isFabricado());
			p.setInt(7, Math.round(estandar.getExistenciaActual()));
			p.setInt(8, Math.round(estandar.getExistenciaActual()));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
	
	public void guardarCostoIndirectoSQL(Producto producto, GastoGeneral gastogeneral) {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO producto (gastogeneral, producto, costo) VALUES (?, ?, ?)");
			p.setInt(1, Controladora.getInstance().getMisGastosGenerales().indexOf(gastogeneral));
			p.setInt(2, Controladora.getInstance().getMisProductos().indexOf(producto));
			p.setFloat(3, producto.getCosto());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO productopartida (producto, partida) VALUES (?, ?)");
			p.setInt(1, Controladora.getInstance().getMisProductos().indexOf(producto));
			p.setInt(2, Controladora.getInstance().getMisPartidas().indexOf(partida));
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
	
	/**public void guardarCostoDirectoSQL(Producto producto, Float partida, Float manoDeObra) {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO costodirecto (producto, manodeobra, partida) VALUES (?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, Controladora.getInstance().getMisProductos());
			p.setString(2, producto.getNombre());
			p.setString(3, producto.getDescripcion());
			p.setString(4, producto.getTipoProducto());
			p.setString(5, producto.getObservaciones());
			p.setString(6, producto.getUnidadMedida().getNombre());
			p.setFloat(7, producto.getCosto());
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
	}**/
	
	public void guardarCombinacionesAtributosSQL(Atributos atributo, Combinaciones combinaciones) {
		Conexion con = new Conexion();
		Connection c = null;
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
	
	public void guardarCombinacionesSQL() {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO combinaciones () VALUES ()");
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		Connection c = null;
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
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		try {
			c = con.conectar();
			
			p = (PreparedStatement)
					c.prepareStatement("INSERT INTO producto (nombre, grupoatributo) VALUES (?, ?)");
			p.setString(1, atributo.getNombre());
			p.setInt(2, Controladora.getInstance().getMisGrupoAtributo().indexOf(atributo.getGrupoAtributo())+1);
			p.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
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
	
	/**FUNCION PARA BUSCAR UN PRODUCTO**/
	
	public ArrayList<Producto> searchProducts(String buscador, String tipoBusqueda) {
		int j = 0;
		ArrayList<Producto> searchProducto = new ArrayList<>();
		for(int i = 0; i < misProductos.size(); i++) {
			int boolCount = 0;
			j = 0;
			/**Con motivo de no repetir la misma funcion varias veces, se penso hacerlo un switch**/
			switch(tipoBusqueda) {
				case "Codigo":
					for(int k = 0; k < misProductos.get(i).getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Nombre":
					for(int k = 0; k < misProductos.get(i).getNombre().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
				case "Descripcion":
					for(int k = 0; k < misProductos.get(i).getDescripcion().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getDescripcion().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				case "Proveedor":
					for(int k = 0; k < misProductos.get(i).getProveedorPrinClass().getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getProveedorPrinClass().getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					
					break;
				case "Rubro":
					for(int k = 0; k < misProductos.get(i).getRubroProductoClass().getNombreRubro().length(); k++) {
						if(j < buscador.length()) {
							if(misProductos.get(i).getRubroProductoClass().getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}	
					break;
			}
			if(boolCount == buscador.length()) {
				searchProducto.add(misProductos.get(i));
			}
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
		int j = 0;
		ArrayList<Proveedores> searchProveedor = new ArrayList<>();
		for(int i = 0; i < misProveedores.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
			case "Codigo":
				for(int k = 0; k < misProveedores.get(i).getCodigo().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misProveedores.get(i).getNombre().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Rubro":
				for(int k = 0; k < misProveedores.get(i).getRubro().getNombreRubro().length(); k++) {
					if(j < buscador.length()) {
						if(misProveedores.get(i).getRubro().getNombreRubro().toLowerCase().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			}
			if(boolCount == buscador.length()) {
				searchProveedor.add(misProveedores.get(i));
			}
		}
		return searchProveedor;
	}
	
/**FUNCION PARA BUSCAR CLIENTES**/
	
	public ArrayList<Cliente> searchClientes(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Cliente> searchCliente = new ArrayList<>();
		for(int i = 0; i < misClientes.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
				case "Codigo":
					for(int k = 0; k < misClientes.get(i).getCodigo().length(); k++) {
						if(j < buscador.length()) {
							if(misClientes.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}				
					break;
				case "Nombre":
					for(int k = 0; k < misClientes.get(i).getNombre().length(); k++) {
						if(j < buscador.length()) {
							if(misClientes.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
								boolCount++;
							}
						}
						j++;
					}
					break;
				}
			if(boolCount == buscador.length()) {
				searchCliente.add(misClientes.get(i));
			}
		}
		return searchCliente;
	}
	
/**FUNCION PARA BUSCAR EMPLEADOS**/
	
	public ArrayList<Empleado> searchEmpleados(String buscador, String tipoBusqueda){
		int j = 0;
		ArrayList<Empleado> searchEmpleado = new ArrayList<>();
		for(int i = 0; i < misEmpleados.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
			case "Codigo":
				for(int k = 0; k < misEmpleados.get(i).getCodigo().length(); k++) {
					if(j < buscador.length()) {
					if(misEmpleados.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misEmpleados.get(i).getNombre().length(); k++) {
					if(j < buscador.length()) {
						if(misEmpleados.get(i).getNombre().toLowerCase().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			}		
			if(boolCount == buscador.length()) {
				searchEmpleado.add(misEmpleados.get(i));
			}
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
		int j = 0;
		ArrayList<Rubro> searchRubro = new ArrayList<>();
		for(int i = 0; i < misRubros.size(); i++) {
			int boolCount = 0;
			j = 0;
			switch(tipoBusqueda) {
			case "Codigo":
				for(int k = 0; k < misRubros.get(i).getCodigo().length(); k++) {
					if(j < buscador.length()) {
					if(misRubros.get(i).getCodigo().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			case "Nombre":
				for(int k = 0; k < misRubros.get(i).getNombreRubro().length(); k++) {
					if(j < buscador.length()) {
						if(misRubros.get(i).getNombreRubro().toLowerCase().charAt(k) == buscador.charAt(j)) {
							boolCount++;
						}
					}
					j++;
				}
				break;
			}		
			if(boolCount == buscador.length()) {
				searchRubro.add(misRubros.get(i));
			}
		}
		return searchRubro;
	}
	
	public void sendUnidadesIntoDatabase() {
		Conexion con = new Conexion();
		Connection c = null;
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
		int count = 0;
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
		String nombre = "";
		int i = 0;
		while(string.charAt(i) != ':') {
			nombre += string.charAt(i);
			i++;
		}
		return nombre;
	}
	
	public GastoGeneral buscarGasto(String g)
	{
		GastoGeneral encontrado = null;
		int i = 0;
		
		while(i < misGastosGenerales.size() || encontrado == null)
		{
			if(getMisGastosGenerales().get(i).getNombre().equals(g))
			{
				encontrado = getMisGastosGenerales().get(i);
			}
			
			i++;
		}
		
		return encontrado;
	}
	
	public Estandar buscarProducto(String p)
	{
		Estandar encontrado = null;
		int i = 0;
		
		while(i < getMisProductosEstandar().size() || encontrado == null)
		{
			if(p.equalsIgnoreCase(getMisProductosEstandar().get(i).getNombre()))
			{
				encontrado = getMisProductosEstandar().get(i);
			}
			
			i++;
		}
		
		return encontrado;
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
	
	public void loadCliente()
	{
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
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
				
				Cliente cli = new Cliente(codigo, nombre, telefono, null, (java.sql.Date) cumpleanos, rnc);
				
				Controladora.getInstance().getMisClientes().add(cli);
				
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



public void loadUnidadesMedida()
	{
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
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



public void loadProveedores()
	{
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		Statement s2 = null;
		ResultSet r = null;
		ResultSet q = null;
		PreparedStatement p = null;
		
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
						
						Controladora.getInstance().getMisProveedores().add(cli);
					}
					
				}
				else
				{
					Proveedores cli = new Proveedores(codigo, nombre, telefono, domicilio, correo, rnc, null, sitioWeb);
					
					Controladora.getInstance().getMisProveedores().add(cli);
				}
				
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

public void recuperarRubros()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	try {
		
		//Recuperar rubros
		c = con.conectar();
		
		//Para recibir datos desde la base de datos, se utiliza ResultSet y el Statement
		if(Controladora.getInstance().getMisRubros().isEmpty())
		{
			System.out.println("Mis rubros está vacío");
		}
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM rubros");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			int id = r.getInt(1);
			String codigo = r.getString(2);
			String nombreRubro = r.getString(3);				
			
			
			Rubro pre = new Rubro(codigo, nombreRubro);
			
			Controladora.getInstance().getMisRubros().add(pre);
			
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



public void loadProductos()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
	//RECUPERACION PRODUCTO
	int id = 0;
	String codigo = null;
	String nombre=null;				
	String descripcion = null;
	String tipoproducto = null;
	String observaciones = null;
	String unidadmedida = null;
	Float costo = null;
	UnidadMedida unidad1 = null;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM productos");
		
		//Bucle para recibir cada valor de las columnas, fila por fila, e imprimirlos en consola
		while(r.next())
		{
			id = r.getInt(1);
			codigo = r.getString(2);
			nombre = r.getString(3);				
			descripcion = r.getString(4);
			tipoproducto = r.getString(5);
			observaciones = r.getString(6);
			unidadmedida = r.getString(7);
			costo = r.getFloat(8);
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
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM estardar WHERE producto = '"+id+"'");
		while(r.next())
		{
			idestandar = r.getInt(1);
			producto = r.getInt(2);
			exitmin = r.getFloat(3);
			exitmax = r.getFloat(4);
			fechavencimiento = r.getDate(5);
			costocompra = r.getFloat(6);
			fabricado = r.getBoolean(7);
			exitact = r.getFloat(8);
			exitinit = r.getFloat(9);
			manodeobra = r.getFloat(10);
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
	
	//RECUPERAR PRECIO
	int idprecioprod;
	int precioidp = 0;
	int productoidp;
	boolean activo = false;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM precioproducto WHERE producto = '"+id+"'");
		while(r.next())
		{
			idprecioprod = r.getInt(1);
			precioidp = r.getInt(2);
			productoidp = r.getInt(3);
			activo = r.getBoolean(4);
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
	
	//SEGUNDA PARTE DEL PRECIO
	int idprecio;
	float montoprecio = 0;
	String descripcionprecio = null;
	Date fechaprecio;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM precio WHERE idprecio = '"+precioidp+"'");
		while(r.next())
		{
			idprecio = r.getInt(1);
			montoprecio = r.getFloat(2);
			descripcionprecio = r.getString(3);
			fechaprecio = r.getDate(4);
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
	
	//RECUPERAR UNIDAD DE MEDIDA
	
	if(isArea(unidadmedida) != null)
	{
		unidad1 = isArea(unidadmedida);
	}
	else if(isLongitud(unidadmedida) != null)
	{
		unidad1 = isLongitud(unidadmedida);
	}
	else if(isMasa(unidadmedida) != null)
	{
		unidad1 = isMasa(unidadmedida);
	}
	else if(isVolumen(unidadmedida) != null)
	{
		unidad1 = isVolumen(unidadmedida);
	}
	
	//RECUPERAR RUBRO
	int idrubroproducto = 0;
	int rubroid = 0;
	int productorubroid = 0;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM rubroproducto WHERE producto = '"+id+"'");
		while(r.next())
		{
			idrubroproducto = r.getInt(1);
			rubroid = r.getInt(2);
			productorubroid = r.getInt(3);
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
	
	//CONTINUACION DE RECUPERAR RUBRO
	int idrubros = 0;
	String codigorubro = null;
	String nombrerubro = null;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM rubros WHERE idrubros = '"+rubroid+"'");
		while(r.next())
		{
			idrubros = r.getInt(1);
			codigorubro = r.getString(2);
			nombrerubro = r.getString(3);
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
	
	//RECUPERAR PROVEEDOR
	int idproveedorprincipal = 0;
	int idproveedor = 0;
	int idprodproveedor = 0;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT * FROM proveedorprincipaproducto WHERE producto = '"+id+"'");
		while(r.next())
		{
			idproveedorprincipal = r.getInt(1);
			idproveedor = r.getInt(2);
			idprodproveedor = r.getInt(3);
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
	
	//CONTINUACION DE RECUPERAR PROVEEDOR
	String nombreproveedor = null;
	
	try {
		
		c = con.conectar();
		
		s = (Statement) c.createStatement();
		r = s.executeQuery("SELECT nombre FROM proveedores WHERE idproveedores = '"+idproveedor+"'");
		while(r.next())
		{
			nombreproveedor = r.getString(1);
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
	
	//CREAR EL ESTANDAR
	Rubro ru = buscarRubro(nombrerubro);
	Proveedores pro = buscarProveedor(nombreproveedor);
	Precio pre = new Precio(montoprecio, descripcionprecio, activo);
	Estandar estandar = new Estandar(exitact, exitmin, exitmax, exitinit, fechavencimiento, costocompra, fabricado, null, codigo, nombre, descripcionprecio, ru, tipoproducto, pro, null, null, observaciones, unidad1, pre, null, null, manodeobra, descripcion, null, costo);
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

public void loadEmpleados()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	Statement s2 = null;
	ResultSet r = null;
	ResultSet q = null;
	PreparedStatement p = null;
	
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
			
			q = s2.executeQuery("SELECT * FROM categoriaempleado WHERE idcategoriaempleado = '"+categoriaid+"'");
			while(q.next())
			{
				int idr = q.getInt(1);
				String nombrecategoria = q.getString(2);
				float sueldocategoria = q.getFloat(3);
				
				CategoriaEmpleado categoriaEmp = new CategoriaEmpleado(nombrecategoria, sueldocategoria);
				
				Empleado emp = new Empleado(codigo, nombrecategoria, telefono, domicilio, correo, rnc, sueldocategoria, categoriaEmp);
				
				Controladora.getInstance().getMisEmpleados().add(emp);
			}
			
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

public void loadCategoriaEmpleado()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
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
			
			CategoriaEmpleado pre = new CategoriaEmpleado(nombre, sueldo);
			
			Controladora.getInstance().getMisCategoriasEmpleado().add(pre);
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

	public void loadAtributos()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	Statement s2 = null;
	ResultSet r = null;
	ResultSet q = null;
	PreparedStatement p = null;
	
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

			q = s2.executeQuery("SELECT * FROM grupoatributo WHERE idgrupoatributo = '"+idgrupoatributo+"'");
			while(q.next())
			{
				int idr = q.getInt(1);
				String nombrecategoria = q.getString(2);
				
				GrupoAtributo pre = new GrupoAtributo(nombrecategoria);
				
				Atributos atr = new Atributos(nombrecategoria, pre);
				
				Controladora.getInstance().getMisAtributos().add(atr);
			}
			
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

public void loadGrupoAtributo()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
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

public void loadGastosGenerales()
{
	Conexion con = new Conexion();
	Connection c = null;
	Statement s = null;
	ResultSet r = null;
	PreparedStatement p = null;
	
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
			
			GastoGeneral pre = new GastoGeneral(nombre, precioUnitario, descripcion, LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(remodelado)));
			
			Controladora.getInstance().getMisGastosGenerales().add(pre);
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



	
	
}




