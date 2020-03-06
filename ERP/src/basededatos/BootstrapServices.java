package basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BootstrapServices {
	
	public void crearTablas() throws SQLException{
		Connection con = Conexion.getInstancia().conectar();
		aniofiscal(con); area(con); grupoatributo(con); atributos(con); cajachica(con);
		productos(con); kit(con); cantkitsutilizados(con); estandar(con); cantproductosutilizados(con);
		cargos(con); categoriaempleado(con); clientes(con); clientecredito(con); clientedeuda(con);
		combinaciones(con); combinacionesatributos(con); costodirecto(con); gastosgenerales(con); costoindirecto(con);
		descuentosutomaticos(con); descproducto(con); empleados(con); usuarios(con); facturas(con);
		facturacreditocliente(con); imagenproducto(con); infoempresa(con); kitproductos(con); kitsfacturados(con);
		longitud(con); manodeobra(con); manodeobraproducto(con); servicios(con); manodeobraservicio(con);
		masa(con); matriz(con); montocuentabancaria(con); rubros(con); proveedores(con);
		peticiones(con); peticionescredito(con); pagopeticionescredito(con); pagofacturacreditocliente(con); partida(con);
		partidaprodutil(con); precio(con); precioproducto(con); productopartida(con); productosfacturados(con);
		promediogananciaanual(con); promocion(con); promoproducto(con); proveedoressecproducto(con); proveedorprincipaproducto(con);
		rangonumerosvalorfiscal(con); rubroproducto(con); serviciomateriales(con); serviciosfacturado(con); transaccionescajachica(con);
		transaccionescuentabancaria(con); unidadmedidaproductoarea(con); unidadmedidaproductolongitud(con); unidadmedidaproductomasa(con);
		volumen(con); unidadmedidaproductovolumen(con); usuariocargo(con); usuariocontrasena(con); usuariorecordado(con);
		con.close();
		System.out.println("Tablas creadas!");
	}
	
	public void aniofiscal(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `aniofiscal` (\r\n" + 
				"  `idaniofiscal` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `fechainicio` date NOT NULL,\r\n" + 
				"  `fechaFinal` date NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idaniofiscal`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void area(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `area` (\r\n" + 
				"  `idarea` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `categoria` varchar(45) DEFAULT NULL,\r\n" + 
				"  `nombre` varchar(45) DEFAULT NULL,\r\n" + 
				"  `abreviatura` varchar(45) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`idarea`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void grupoatributo(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS `grupoatributo` (\r\n" + 
				"  `idgrupoatributo` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `borrrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idgrupoatributo`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void atributos(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS `atributos` (\r\n" + 
				"  `idatributos` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `grupoatributo` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idatributos`),\r\n" + 
				"  CONSTRAINT `atributgrupo` FOREIGN KEY (`grupoatributo`) REFERENCES `grupoatributo` (`idgrupoatributo`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);	
	}
	
	public void cajachica(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS `cajachica` (\r\n" + 
				"  `idcajachica` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `monto` float NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idcajachica`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void productos(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS`productos` (\r\n" + 
				"  `idproductos` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `codigo` varchar(45) NOT NULL,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `descripcion` varchar(45) DEFAULT NULL,\r\n" + 
				"  `tipoproducto` varchar(45) NOT NULL,\r\n" + 
				"  `observaciones` varchar(45) DEFAULT NULL,\r\n" + 
				"  `unidadmedida` varchar(45) DEFAULT NULL,\r\n" + 
				"  `costo` float NOT NULL,\r\n" + 
				"  `costoitbis` float DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idproductos`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}

	public void kit(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS `kit` (\r\n" + 
				"  `idkit` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `exisminima` float DEFAULT NULL,\r\n" + 
				"  `exismaxima` float DEFAULT NULL,\r\n" + 
				"  `exisactual` float DEFAULT NULL,\r\n" + 
				"  `exisinicial` float DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idkit`),\r\n" + 
				"  CONSTRAINT `productorefkit` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void cantkitsutilizados(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS `cantkitsutilizados` (\r\n" + 
				"  `idcantkitsutilizados` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `kit` int(11) NOT NULL,\r\n" + 
				"  `cantidad` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcantkitsutilizados`),\r\n" + 
				"  CONSTRAINT `kitutilizado` FOREIGN KEY (`kit`) REFERENCES `kit` (`idkit`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void estandar(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `estandar` (\r\n" + 
				"  `idestandar` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `existmin` float NOT NULL,\r\n" + 
				"  `existmax` float NOT NULL,\r\n" + 
				"  `fechavencimiento` date DEFAULT NULL,\r\n" + 
				"  `costocompra` float DEFAULT NULL,\r\n" + 
				"  `fabricado` tinyint(4) NOT NULL,\r\n" + 
				"  `existactual` float NOT NULL,\r\n" + 
				"  `existinicial` float NOT NULL,\r\n" + 
				"  `manodeobra` float DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idestandar`),\r\n" + 
				"  CONSTRAINT `productoreferenciado` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void cantproductosutilizados(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `cantproductosutilizados` (\r\n" + 
				"  `idcantproductosutilizados` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `estandar` int(11) NOT NULL,\r\n" + 
				"  `cantidad` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcantproductosutilizados`),\r\n" + 
				"  CONSTRAINT `productokit` FOREIGN KEY (`estandar`) REFERENCES `estandar` (`idestandar`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void cargos(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `cargos` (\r\n" + 
				"  `idcargos` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcargos`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void categoriaempleado(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `categoriaempleado` (\r\n" + 
				"  `idcategoriaempleado` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `sueldo` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcategoriaempleado`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void clientes(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `clientes` (\r\n" + 
				"  `idclientes` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `codigo` varchar(45) NOT NULL,\r\n" + 
				"  `telefono` varchar(45) DEFAULT NULL,\r\n" + 
				"  `cumpleanos` date DEFAULT NULL,\r\n" + 
				"  `rnc` varchar(45) NOT NULL,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idclientes`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void clientecredito(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `clientecredito` (\r\n" + 
				"  `idclientecredito` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `cliente` int(11) NOT NULL,\r\n" + 
				"  `credito` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idclientecredito`),\r\n" + 
				"  CONSTRAINT `clientecred` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`idclientes`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void clientedeuda(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `clientedeuda` (\r\n" + 
				"  `idclientedeuda` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `cliente` int(11) NOT NULL,\r\n" + 
				"  `deuda` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idclientedeuda`),\r\n" +  
				"  CONSTRAINT `clientedeud` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`idclientes`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void combinaciones(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `combinaciones` (\r\n" + 
				"  `idcombinaciones` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `numserie` varchar(45) DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcombinaciones`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void combinacionesatributos(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `combinacionesatributos` (\r\n" + 
				"  `idcombinacionesatributos` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `atributo` int(11) NOT NULL,\r\n" + 
				"  `combinacion` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcombinacionesatributos`),\r\n" + 
				"  CONSTRAINT `atributocomb` FOREIGN KEY (`atributo`) REFERENCES `atributos` (`idatributos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `combatri` FOREIGN KEY (`combinacion`) REFERENCES `combinaciones` (`idcombinaciones`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void costodirecto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `costodirecto` (\r\n" + 
				"  `idcostodirecto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `manodeobra` float NOT NULL,\r\n" + 
				"  `partida` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcostodirecto`),\r\n" + 
				"  CONSTRAINT `productocostodir` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void gastosgenerales(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `gastosgenerales` (\r\n" + 
				"  `idgastosgenerales` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `descripcion` varchar(45) DEFAULT NULL,\r\n" + 
				"  `modificado` date NOT NULL,\r\n" + 
				"  `gasto` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idgastosgenerales`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void costoindirecto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `costoindirecto` (\r\n" + 
				"  `idcostodirecto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `gastogeneral` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `costo` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idcostodirecto`),\r\n" + 
				"  CONSTRAINT `gastogenprod` FOREIGN KEY (`gastogeneral`) REFERENCES `gastosgenerales` (`idgastosgenerales`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `productocostoind` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void descuentosutomaticos(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `descuentosautomaticos` (\r\n" + 
				"  `iddescuentosAutomaticos` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `codigo` varchar(45) DEFAULT NULL,\r\n" + 
				"  `detalle` varchar(45) DEFAULT NULL,\r\n" + 
				"  `cantidadProducto` int(11) DEFAULT NULL,\r\n" + 
				"  `porcentajeDescuento` float DEFAULT NULL,\r\n" + 
				"  `activo` tinyint(4) DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`iddescuentosAutomaticos`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void descproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `descproducto` (\r\n" + 
				"  `iddescproducto` int(11) NOT NULL,\r\n" + 
				"  `descuento` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`iddescproducto`),\r\n" +  
				"  CONSTRAINT `descuento` FOREIGN KEY (`descuento`) REFERENCES `descuentosautomaticos` (`iddescuentosAutomaticos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `productodesc` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void empleados(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `empleados` (\r\n" + 
				"  `idempleados` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `telefono` varchar(45) DEFAULT NULL,\r\n" + 
				"  `domicilio` varchar(45) DEFAULT NULL,\r\n" + 
				"  `correo` varchar(45) DEFAULT NULL,\r\n" + 
				"  `rnc` varchar(45) NOT NULL,\r\n" + 
				"  `sueldo` float NOT NULL,\r\n" + 
				"  `categoria` int(11) NOT NULL,\r\n" + 
				"  `codigo` varchar(45) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idempleados`),\r\n" + 
				"  CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categoriaempleado` (`idcategoriaempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void usuarios(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `usuarios` (\r\n" + 
				"  `idusuarios` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `usuario` varchar(45) NOT NULL,\r\n" + 
				"  `empleado` int(11) NOT NULL,\r\n" + 
				"  `activo` tinyint(4) NOT NULL DEFAULT '1',\r\n" + 
				"  PRIMARY KEY (`idusuarios`),\r\n" + 
				"  CONSTRAINT `empleadousuario` FOREIGN KEY (`empleado`) REFERENCES `empleados` (`idempleados`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void facturas(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `facturas` (\r\n" + 
				"  `idfacturas` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `cliente` int(11) DEFAULT NULL,\r\n" + 
				"  `montototal` float NOT NULL,\r\n" + 
				"  `tipopago` varchar(45) NOT NULL,\r\n" + 
				"  `montorecibido` float NOT NULL,\r\n" + 
				"  `cambio` float NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  `hora` time NOT NULL,\r\n" + 
				"  `tipofactura` varchar(45) NOT NULL,\r\n" + 
				"  `cantcopias` int(11) NOT NULL,\r\n" + 
				"  `estado` varchar(45) NOT NULL,\r\n" + 
				"  `codigo` varchar(45) NOT NULL,\r\n" + 
				"  `usuariofacturador` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idfacturas`),\r\n" + 
				"  CONSTRAINT `clientefactura` FOREIGN KEY (`cliente`) REFERENCES `clientes` (`idclientes`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `usuariofacturador` FOREIGN KEY (`usuariofacturador`) REFERENCES `usuarios` (`idusuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void facturacreditocliente(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `facturacreditocliente` (\r\n" + 
				"  `idfacturacreditocliente` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `factura` int(11) NOT NULL,\r\n" + 
				"  `adeudado` float NOT NULL,\r\n" + 
				"  `plazopagodias` int(11) NOT NULL,\r\n" + 
				"  `porcientodescuento` float NOT NULL,\r\n" + 
				"  `fechalimitedescuento` date NOT NULL,\r\n" + 
				"  `porcientopenalizacion` float NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idfacturacreditocliente`),\r\n" + 
				"  CONSTRAINT `facturacredito` FOREIGN KEY (`factura`) REFERENCES `facturas` (`idfacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void imagenproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `imagenproducto` (\r\n" + 
				"  `idimagenproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `imagen` longblob NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idimagenproducto`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void infoempresa(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `infoempresa` (\r\n" + 
				"  `idinfoempresa` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `rnc` varchar(45) DEFAULT NULL,\r\n" + 
				"  `telefono` varchar(45) DEFAULT NULL,\r\n" + 
				"  `domicilio` varchar(45) DEFAULT NULL,\r\n" + 
				"  `itbis` int(11) NOT NULL DEFAULT '18',\r\n" + 
				"  `limitemontocajachica` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idinfoempresa`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void kitproductos(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `kitproductos` (\r\n" + 
				"  `idkitproductos` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `kit` int(11) NOT NULL,\r\n" + 
				"  `cantidadproducto` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idkitproductos`),\r\n" + 
				"  CONSTRAINT `cantproductokit` FOREIGN KEY (`cantidadproducto`) REFERENCES `cantproductosutilizados` (`idcantproductosutilizados`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `kitprodid` FOREIGN KEY (`kit`) REFERENCES `kit` (`idkit`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void kitsfacturados(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `kitsfacturados` (\r\n" + 
				"  `idkitsfacturados` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `idcantkitutil` int(11) NOT NULL,\r\n" + 
				"  `idfactura` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idkitsfacturados`),\r\n" + 
				"  CONSTRAINT `facturakits` FOREIGN KEY (`idfactura`) REFERENCES `facturas` (`idfacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `kitsfacturados` FOREIGN KEY (`idcantkitutil`) REFERENCES `cantkitsutilizados` (`idcantkitsutilizados`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void longitud(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `longitud` (\r\n" + 
				"  `idlongitud` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `categoria` varchar(45) DEFAULT NULL,\r\n" + 
				"  `nombre` varchar(45) DEFAULT NULL,\r\n" + 
				"  `abreviatura` varchar(45) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`idlongitud`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void manodeobra(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `manodeobra` (\r\n" + 
				"  `idmanodeobra` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `costo` float NOT NULL,\r\n" + 
				"  `cantidadhoras` float NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idmanodeobra`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void manodeobraproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `manodeobraproducto` (\r\n" + 
				"  `idmanodeobraproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `manodeobra` int(11) NOT NULL,\r\n" + 
				"  `estandar` int(11) NOT NULL,\r\n" + 
				"  `categoriaempleado` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idmanodeobraproducto`),\r\n" + 
				"  CONSTRAINT `manocategoria` FOREIGN KEY (`categoriaempleado`) REFERENCES `categoriaempleado` (`idcategoriaempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `manoestandar` FOREIGN KEY (`estandar`) REFERENCES `estandar` (`idestandar`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `manoobra` FOREIGN KEY (`manodeobra`) REFERENCES `manodeobra` (`idmanodeobra`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void servicios(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `servicios` (\r\n" + 
				"  `idservicios` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `categoriaempleado` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idservicios`),\r\n" + 
				"  CONSTRAINT `categoriaempleado` FOREIGN KEY (`categoriaempleado`) REFERENCES `categoriaempleado` (`idcategoriaempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `productoserv` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void manodeobraservicio(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `manodeobraservicio` (\r\n" + 
				"  `idmanodeobraservicio` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `manodeobra` int(11) NOT NULL,\r\n" + 
				"  `servicio` int(11) NOT NULL,\r\n" + 
				"  `categoriaempleado` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idmanodeobraservicio`),\r\n" + 
				"  CONSTRAINT `categoriaempmanoobraserv` FOREIGN KEY (`categoriaempleado`) REFERENCES `categoriaempleado` (`idcategoriaempleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `manodeobraserv` FOREIGN KEY (`manodeobra`) REFERENCES `manodeobra` (`idmanodeobra`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `serviciomanod` FOREIGN KEY (`servicio`) REFERENCES `servicios` (`idservicios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void masa(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `masa` (\r\n" + 
				"  `idmasa` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `categoria` varchar(45) DEFAULT NULL,\r\n" + 
				"  `nombre` varchar(45) DEFAULT NULL,\r\n" + 
				"  `abreviatura` varchar(45) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`idmasa`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void matriz(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `matriz` (\r\n" + 
				"  `idmatriz` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `prodestandar` int(11) NOT NULL,\r\n" + 
				"  `combinacion` int(11) NOT NULL,\r\n" + 
				"  `existactual` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idmatriz`),\r\n" + 
				"  CONSTRAINT `combinacionmatriz` FOREIGN KEY (`combinacion`) REFERENCES `combinaciones` (`idcombinaciones`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `prodestandarmatriz` FOREIGN KEY (`prodestandar`) REFERENCES `estandar` (`idestandar`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void montocuentabancaria(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `montocuentabancaria` (\r\n" + 
				"  `idmontocuentabancaria` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `monto` float NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idmontocuentabancaria`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void rubros(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `rubros` (\r\n" + 
				"  `idrubros` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `codigo` varchar(45) NOT NULL,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idrubros`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void proveedores(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `proveedores` (\r\n" + 
				"  `idproveedores` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `saldo` float NOT NULL,\r\n" + 
				"  `domicilio` varchar(45) DEFAULT NULL,\r\n" + 
				"  `correo` varchar(45) DEFAULT NULL,\r\n" + 
				"  `rnc` varchar(45) NOT NULL,\r\n" + 
				"  `rubro` int(11) DEFAULT NULL,\r\n" + 
				"  `sitioweb` varchar(45) DEFAULT NULL,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `telefono` varchar(45) DEFAULT NULL,\r\n" + 
				"  `codigo` varchar(45) DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idproveedores`),\r\n" + 
				"  CONSTRAINT `rubro` FOREIGN KEY (`rubro`) REFERENCES `rubros` (`idrubros`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void peticiones(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `peticiones` (\r\n" + 
				"  `idpeticiones` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `codigo` varchar(45) NOT NULL,\r\n" + 
				"  `idproveedor` int(11) NOT NULL,\r\n" + 
				"  `idproducto` int(11) NOT NULL,\r\n" + 
				"  `cantidad` float NOT NULL,\r\n" + 
				"  `monto` float NOT NULL,\r\n" + 
				"  `metodopago` varchar(45) NOT NULL,\r\n" + 
				"  `estado` varchar(45) NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idpeticiones`),\r\n" + 
				"  CONSTRAINT `idprod` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `idprov` FOREIGN KEY (`idproveedor`) REFERENCES `proveedores` (`idproveedores`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void peticionescredito(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `peticionescredito` (\r\n" + 
				"  `idpeticionescredito` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `peticion` int(11) NOT NULL,\r\n" + 
				"  `adeudado` float NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idpeticionescredito`),\r\n" + 
				"  CONSTRAINT `peticionadeudada` FOREIGN KEY (`peticion`) REFERENCES `peticiones` (`idpeticiones`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void pagopeticionescredito(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `pagopeticionescredito` (\r\n" + 
				"  `idpagopeticionescredito` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `peticion` int(11) NOT NULL,\r\n" + 
				"  `montopagado` float NOT NULL,\r\n" + 
				"  `fechadelpago` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idpagopeticionescredito`),\r\n" + 
				"  CONSTRAINT `peticionpagocredito` FOREIGN KEY (`peticion`) REFERENCES `peticionescredito` (`peticion`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void pagofacturacreditocliente(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `pagosfacturacreditocliente` (\r\n" + 
				"  `idpagosfacturacreditocliente` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `factura` int(11) NOT NULL,\r\n" + 
				"  `montopagado` float NOT NULL,\r\n" + 
				"  `fechadelpago` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idpagosfacturacreditocliente`),\r\n" + 
				"  CONSTRAINT `facturadeuda` FOREIGN KEY (`factura`) REFERENCES `facturacreditocliente` (`factura`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void partida(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `partida` (\r\n" + 
				"  `idpartida` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idpartida`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void partidaprodutil(Connection con) throws SQLException {
		String sql ="CREATE TABLE IF NOT EXISTS `partidaprodutil` (\r\n" + 
				"  `idpartidaprodutil` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `partida` int(11) NOT NULL,\r\n" + 
				"  `cantproductoutilizado` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idpartidaprodutil`),\r\n" + 
				"  CONSTRAINT `cantprodutilpartida` FOREIGN KEY (`cantproductoutilizado`) REFERENCES `cantproductosutilizados` (`idcantproductosutilizados`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `partidaprod` FOREIGN KEY (`partida`) REFERENCES `partida` (`idpartida`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void precio(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `precio` (\r\n" + 
				"  `idprecio` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `precio` float NOT NULL,\r\n" + 
				"  `descripcion` varchar(45) DEFAULT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idprecio`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void precioproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `precioproducto` (\r\n" + 
				"  `idprecioproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `precio` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `activo` tinyint(4) NOT NULL DEFAULT '1',\r\n" + 
				"  PRIMARY KEY (`idprecioproducto`),\r\n" + 
				"  CONSTRAINT `precioprod` FOREIGN KEY (`precio`) REFERENCES `precio` (`idprecio`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `productoprec` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void productopartida(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `productopartida` (\r\n" + 
				"  `idproductopartida` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `partida` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idproductopartida`),\r\n" + 
				"  CONSTRAINT `partidap` FOREIGN KEY (`partida`) REFERENCES `partida` (`idpartida`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `productop` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void productosfacturados(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `productosfacturados` (\r\n" + 
				"  `idproductosfacturados` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `idcantprodutil` int(11) NOT NULL,\r\n" + 
				"  `idfactura` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idproductosfacturados`),\r\n" +  
				"  CONSTRAINT `facturaprod` FOREIGN KEY (`idfactura`) REFERENCES `facturas` (`idfacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `produtilfactura` FOREIGN KEY (`idcantprodutil`) REFERENCES `cantproductosutilizados` (`idcantproductosutilizados`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void promediogananciaanual(Connection con) throws SQLException {
			String sql = "CREATE TABLE IF NOT EXISTS `promediogananciaanual` (\r\n" + 
					"  `idpromediogananciaanual` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
					"  `monto` float NOT NULL,\r\n" + 
					"  `fecha` date NOT NULL,\r\n" + 
					"  `activo` tinyint(4) NOT NULL DEFAULT '1',\r\n" + 
					"  PRIMARY KEY (`idpromediogananciaanual`)\r\n" + 
					");";
			Statement statement = con.createStatement();
			statement.execute(sql);
	}
	
	public void promocion(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `promocion` (\r\n" + 
				"  `idpromocion` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `porcentajeDescuento` int(11) DEFAULT NULL,\r\n" + 
				"  `nombre` varchar(45) NOT NULL,\r\n" + 
				"  `fechaInicio` date DEFAULT NULL,\r\n" + 
				"  `fechaFinal` date DEFAULT NULL,\r\n" + 
				"  `horaInicio` time DEFAULT NULL,\r\n" + 
				"  `horaFinal` time DEFAULT NULL,\r\n" + 
				"  `dia` varchar(45) DEFAULT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idpromocion`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void promoproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `promoproducto` (\r\n" + 
				"  `idpromoproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `promocion` int(11) NOT NULL,\r\n" + 
				"  `precioproducto` float NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idpromoproducto`),\r\n" + 
				"  CONSTRAINT `productopromo` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `promociondelprod` FOREIGN KEY (`promocion`) REFERENCES `promocion` (`idpromocion`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void proveedoressecproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `proveedoressecproducto` (\r\n" + 
				"  `idproveedoressecproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `proveedor` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idproveedoressecproducto`),\r\n" + 
				"  CONSTRAINT `productoprosec` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `proveedorsec` FOREIGN KEY (`proveedor`) REFERENCES `proveedores` (`idproveedores`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void proveedorprincipaproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `proveedorprincipaproducto` (\r\n" + 
				"  `idproveedorprincipaproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `proveedor` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idproveedorprincipaproducto`),\r\n" + 
				"  CONSTRAINT `productoprov` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `proveedorprin` FOREIGN KEY (`proveedor`) REFERENCES `proveedores` (`idproveedores`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void rangonumerosvalorfiscal(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `rangonumerosvalorfiscal` (\r\n" + 
				"  `idrangonumerosvalorfiscal` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `valorfiscalinferior` int(11) NOT NULL,\r\n" + 
				"  `valorfiscalsuperior` int(11) NOT NULL,\r\n" + 
				"  `fechasecsolicitada` date NOT NULL,\r\n" + 
				"  `fechasecvencimiento` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idrangonumerosvalorfiscal`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void rubroproducto(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `rubroproducto` (\r\n" + 
				"  `idrubroproducto` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `rubro` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idrubroproducto`),\r\n" +  
				"  CONSTRAINT `productorub` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `rubroprod` FOREIGN KEY (`rubro`) REFERENCES `rubros` (`idrubros`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void serviciomateriales(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `serviciomateriales` (\r\n" + 
				"  `idserviciomateriales` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `servicio` int(11) NOT NULL,\r\n" + 
				"  `cantproducto` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idserviciomateriales`),\r\n" + 
				"  CONSTRAINT `cantproductoser` FOREIGN KEY (`cantproducto`) REFERENCES `cantproductosutilizados` (`idcantproductosutilizados`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `serviciomat` FOREIGN KEY (`servicio`) REFERENCES `servicios` (`idservicios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void serviciosfacturado(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `serviciosfacturado` (\r\n" + 
				"  `idserviciosfacturado` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `idservicio` int(11) NOT NULL,\r\n" + 
				"  `idfactura` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idserviciosfacturado`),\r\n" + 
				"  CONSTRAINT `facturaservicio` FOREIGN KEY (`idfactura`) REFERENCES `facturas` (`idfacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `idserviciofact` FOREIGN KEY (`idservicio`) REFERENCES `servicios` (`idservicios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void transaccionescajachica(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `transaccionescajachica` (\r\n" + 
				"  `idtransaccionescajachica` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `montoactual` float NOT NULL,\r\n" + 
				"  `actualizacion` float NOT NULL,\r\n" + 
				"  `descripcion` varchar(45) NOT NULL,\r\n" + 
				"  `usuario` int(11) NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idtransaccionescajachica`),\r\n" + 
				"  CONSTRAINT `usucajachic` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`idusuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void transaccionescuentabancaria(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `transaccionescuentabancaria` (\r\n" + 
				"  `idtransaccionescuentabancaria` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `montoactual` float NOT NULL,\r\n" + 
				"  `actualizacion` float NOT NULL,\r\n" + 
				"  `descripcion` varchar(45) NOT NULL,\r\n" + 
				"  `fecha` date NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idtransaccionescuentabancaria`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void unidadmedidaproductoarea(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `unidadmedidaproductoarea` (\r\n" + 
				"  `idunidadmedidaproductoarea` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `unidad` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idunidadmedidaproductoarea`),\r\n" + 
				"  CONSTRAINT `productoarea` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `unidadmedidaparea` FOREIGN KEY (`unidad`) REFERENCES `area` (`idarea`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void unidadmedidaproductolongitud(Connection con) throws SQLException{
		String sql = "CREATE TABLE IF NOT EXISTS `unidadmedidaproductolongitud` (\r\n" + 
				"  `idunidadmedidaproductolongitud` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `unidad` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idunidadmedidaproductolongitud`),\r\n" + 
				"  CONSTRAINT `productolongitud` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `unidadplong` FOREIGN KEY (`unidad`) REFERENCES `longitud` (`idlongitud`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void unidadmedidaproductomasa(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `unidadmedidaproductomasa` (\r\n" + 
				"  `idunidadmedidaproductomasa` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `unidad` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idunidadmedidaproductomasa`),\r\n" + 
				"  CONSTRAINT `productomasa` FOREIGN KEY (`producto`) REFERENCES `productos` (`idproductos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `unidadmasaproducto` FOREIGN KEY (`unidad`) REFERENCES `masa` (`idmasa`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void volumen(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `volumen` (\r\n" + 
				"  `idvolumen` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `categoria` varchar(45) DEFAULT NULL,\r\n" + 
				"  `nombre` varchar(45) DEFAULT NULL,\r\n" + 
				"  `abreviatura` varchar(45) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`idvolumen`)\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void unidadmedidaproductovolumen(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `unidadmedidaproductovolumen` (\r\n" + 
				"  `idunidadmedidaproductovolumen` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `unidad` int(11) NOT NULL,\r\n" + 
				"  `producto` int(11) NOT NULL,\r\n" + 
				"  PRIMARY KEY (`idunidadmedidaproductovolumen`),\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void usuariocargo(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `usuariocargo` (\r\n" + 
				"  `idusuariocargo` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `usuario` int(11) NOT NULL,\r\n" + 
				"  `cargo` int(11) NOT NULL,\r\n" + 
				"  `borrado` tinyint(4) NOT NULL DEFAULT '0',\r\n" + 
				"  PRIMARY KEY (`idusuariocargo`),\r\n" + 
				"  CONSTRAINT `cargocargo` FOREIGN KEY (`cargo`) REFERENCES `cargos` (`idcargos`) ON DELETE NO ACTION ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `usuariocarg` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`idusuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void usuariocontrasena(Connection con) throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS `usuariocontrasena` (\r\n" + 
				"  `idusuariocontrasena` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `usuario` int(11) NOT NULL,\r\n" + 
				"  `contrasena` varchar(45) NOT NULL,\r\n" + 
				"  `activo` tinyint(4) NOT NULL DEFAULT '1',\r\n" + 
				"  PRIMARY KEY (`idusuariocontrasena`),\r\n" + 
				"  CONSTRAINT `usuariocontra` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`idusuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION\r\n" + 
				");";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	public void usuariorecordado(Connection con) throws SQLException {
		String sql = "create table IF NOT EXISTS usuariorecordado (idusuariorecordado INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, idusuario integer(10), foreign key (idusuario) references usuarios (idusuarios));";
		Statement statement = con.createStatement();
		statement.execute(sql);
	}
	
	
	
	
	
	
	
	
	
}
