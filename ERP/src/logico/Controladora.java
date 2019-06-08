package logico;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDate;
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
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO empleados (nombre, telefono, domicilio, correo, rnc, sueldo, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)");
			p.setString(1, e.getNombre());
			p.setString(2, e.getTelefono());
			p.setString(3, e.getDomicilio());
			p.setString(4, e.getCorreo());
			p.setString(5, e.getRnc());
			p.setFloat(6, e.getSueldo());
			p.setInt(7, Controladora.getInstance().getMisCategoriasEmpleado().indexOf(e.getCategoria())+1);
			
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
	
	public void guardarProductoEstandarSQL(Estandar e) {
		
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		/*try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO gastosgenerales (nombre, descripcion, modificado) VALUES (?, ?, ?)");
			p.setString(1, g.getNombre());
			p.setString(2, g.getDescripcion());
			p.setDate(3, (java.sql.Date.valueOf(g.getRemodelado())));
			
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
		*/
		
	}

	public void guardarGastoGeneralSQL(GastoGeneral g) {
		Conexion con = new Conexion();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		PreparedStatement p = null;
		
		try {
			c = con.conectar();
			
			p = (PreparedStatement) c.prepareStatement("INSERT INTO gastosgenerales (nombre, descripcion, modificado) VALUES (?, ?, ?)");
			p.setString(1, g.getNombre());
			p.setString(2, g.getDescripcion());
			p.setDate(3, (java.sql.Date.valueOf(g.getRemodelado())));
			
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
			p.setInt(5, Controladora.getInstance().getMisRubros().indexOf(proveedor.getRubro())+1);
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
					c.prepareStatement("INSERT INTO promocion (precioPromocion, fechaInicio, fechaFinal) VALUES (?, ?, ?)");
			p.setFloat(1, promocion.getPrecioPromocion());
			p.setDate(2, (java.sql.Date) promocion.getFechaInicio());
			p.setDate(3, (java.sql.Date) promocion.getFechaFinal());
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

	public void addProducto(Producto p) {
		misProductos.add(p);
	}
	public void addProductoEstandar(Estandar e) {
		misProductosEstandar.add(e);
		
		guardarProductoEstandarSQL(e);
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
							if(misProductos.get(i).getRubroProductoClass().getNombreRubro().charAt(k) == buscador.charAt(j)) {
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
	
	
}




