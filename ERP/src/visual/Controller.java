package visual;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

import archivos.Archivos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
//import jdk.nashorn.internal.ir.SetSplitState;
import logico.Atributos;
import logico.CajaChica;
import logico.CantBienesYServiciosUtilizados;
import logico.CantProductosUtilizados;
import logico.Cargo;
import logico.CategoriaEmpleado;
import logico.Cliente;
import logico.Combinaciones;
import logico.Controladora;
import logico.CostoIndirectoProducto;
import logico.CuentaBanco;
import logico.Empleado;
import logico.Empresa;
import logico.Estandar;
import logico.Factura;
import logico.GastoGeneral;
import logico.GrupoAtributo;
import logico.Kit;
import logico.ManoDeObra;
import logico.Peticion;
import logico.Producto;
import logico.Promocion;
import logico.Proveedores;
import logico.Rubro;
import logico.Servicio;
import logico.TransaccionesCajaChica;
import logico.TransaccionesCuentaBanco;
import logico.Usuario;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.*;

public class Controller implements Initializable{
	
	
	/**VARIABLES DE LA PANTALLA PRINCIPAL**/
	@FXML private Pane mainPane;
    @FXML private HBox box_Principal;
    @FXML private Label label_bienvenido;

    @FXML private Button selected_Gastos;
   // @FXML private Button selected_Principal;
    @FXML private Button selected_ventas;
    @FXML private Button selected_productos;
    @FXML private Button selected_help;
    @FXML private Button selected_rh;
    @FXML private Button selected_historial;
    @FXML private Button selected_admin;
    @FXML private Button selected_config;

    @FXML private Button button_gastos;
  //  @FXML private Button button_principal;
    @FXML private Button button_ventas;
    @FXML private Button button_productos;
    @FXML private Button button_help;
    @FXML private Button button_rh;
    @FXML private Button button_historial;
    @FXML private Button button_admin;
    @FXML private Button button_config;
    
    @FXML private Label text_negocioName;
    
    //VARIABLES DE RECURSOS HUMANOS
    @FXML private TabPane tabpane_recursosHumanos;
    @FXML private Tab tab_proveedores;
    @FXML private Tab tab_clientes;
    @FXML private Tab tab_empleados;
    @FXML private Tab tab_CategoriaEmpleado;
    @FXML private TextField textfield_nombreCategoriaEmp;
    @FXML private TextField textfield_salarioCategoriaEmp;
    @FXML private Button button_guardarCategoriaEmp;
    @FXML private Button button_editarCategoria;
    @FXML private TableView<CategoriaEmpleado> tableview_CategoriaEmp;
    @FXML private TableColumn<CategoriaEmpleado, String> tablecolumn_NombreCategoria;
    @FXML private TableColumn<CategoriaEmpleado, Float> tablecolumn_SueldoCategoria;
    @FXML private RadioButton radiobutton_PorHora;
    @FXML private RadioButton radiobutton_PorDia;
    
    //DESPLIEGUE DE PRODUCTOS
    @FXML private TableColumn<Producto, Float> tablecolumn_productCodigo;
    @FXML private TableColumn<Producto, String> tablecolumn_productNombre;
    @FXML private TableColumn<Producto, Float> tablecolumn_productExistenciaInicial;
    @FXML private TableColumn<Producto, Float> tablecolumn_productExistenciaActual;
    @FXML private TableColumn<Producto, Float> tablecolumn_productExistenciaMinima;
    @FXML private TableColumn<Producto, Float> tablecolumn_productExistenciaMaxima;
    @FXML private TableColumn<Producto, String> tablecolumn_productTipo;
    @FXML private TableColumn<Producto, Rubro> tablecolumn_productRubro;
    @FXML private TableColumn<Producto, Proveedores> tablecolumn_productProveedor;
    @FXML private TableColumn<Producto, Float> tablecolumn_productPrecio;
    @FXML private TableColumn<Producto, Float> tablecolumn_productCosto;
    @FXML private TableColumn<Producto, String> tablecolumn_productDescripcion;
    @FXML private TableView<Producto> tableview_productList;
    @FXML private Button button_abrirInfoAdicional;
    @FXML private ComboBox<String> combobox_productBuscar;
    @FXML private TextField textfield_productBuscar;
    @FXML private Button button_nuevoProducto;
    @FXML private Button button_modificarProducto;
    @FXML private Button button_eliminarProducto;
    @FXML private AnchorPane pane_InfoAdicionalProducto;
    @FXML private TitledPane titledpane_productoInformacionAdicional;
    @FXML private TableColumn<CantProductosUtilizados, String> tablecolumn_productoPartidaUtilizado;
    @FXML private TableColumn<CantProductosUtilizados, Float> tablecolumn_productoPartidaCantidad;
    @FXML private TableColumn<CantProductosUtilizados, String> tablecolumn_productoPartidaUnidad;
    @FXML private TableView<CantProductosUtilizados> tableview_productoPartidaList;
    
    @FXML private TableColumn<Producto, Float> tablecolumn_productoCostoPartida;
    @FXML private TableColumn<Producto, Float> tablecolumn_productoCostoManoObra;
    @FXML private TableColumn<Producto, Float> tablecolumn_productoCostoCompra;
    @FXML private TableView<Producto> tableview_productoCostosList;
    
    @FXML private TableColumn<Combinaciones, String> tablecolumn_atributo1;
    @FXML private TableColumn<Combinaciones, String> tablecolumn_atributo2;
    @FXML private TableColumn<Combinaciones, String> tablecolumn_atributo3;
    @FXML private TableColumn<Combinaciones, Float> tablecolumn_existenciaActualCombinacion;
    @FXML private TableColumn<Combinaciones, String> tablecolumn_numSerieCombinacion;
    @FXML private TableView<Combinaciones> tableview_atributosList;
    
    @FXML private TableColumn<Factura, Date> tablecolumn_facturaDate;
    @FXML private TableColumn<Factura, LocalDate> tablecolumn_facturaHora;
    @FXML private TableColumn<Factura, Float> tablecolumn_facturaTotalPagar;
    @FXML private TableColumn<Factura, Float> tablecolumn_facturaRecibido;
    @FXML private TableColumn<Factura, Float> tablecolumn_facturaCambio;
    @FXML private TableColumn<Factura, String> tablecolumn_facturaCliente;
    @FXML private TableColumn<Factura, String> tablecolumn_facturaEmpleado;
    @FXML private TableView<Factura> tableview_facturaList;
    
    @FXML private Button button_facturaInfoAdicional;
    
    @FXML private TextField textfield_facturaClienteCodigo;
    @FXML private TextField textfield_facturaClienteNombre;
    @FXML private TextField textfield_facturaTotalPagado;
    
    @FXML private TableColumn<CantBienesYServiciosUtilizados, String> tablecolumn_facturaProductoList;
    @FXML private TableColumn<CantBienesYServiciosUtilizados, String> tablecolumn_facturaProductoUnidad;
    @FXML private TableColumn<CantBienesYServiciosUtilizados, Float> tablecolumn_facturaProductoCantidad;
    @FXML private TableColumn<CantBienesYServiciosUtilizados, Float> tablecolumn_facturaProductoPrecioUnitario;
    @FXML private TableColumn<CantBienesYServiciosUtilizados, Float> tablecolumn_facturaProductoValor;
    @FXML private TableView<CantBienesYServiciosUtilizados> tableview_facturaProductoList;
    
    @FXML private TableColumn<Promocion, String> tablecolumn_promocionCodigo;
    @FXML private TableColumn<Promocion, Integer> tablecolumn_promocionPorcentaje;
    @FXML private TableColumn<Promocion, LocalDate> tablecolumn_promocionFechaInicial;
    @FXML private TableColumn<Promocion, LocalDate> tablecolumn_promocionFechaFinal;
    @FXML private TableColumn<Promocion, LocalTime> tablecolumn_promocionHoraInicial;
    @FXML private TableColumn<Promocion, LocalTime> tablecolumn_promocionHoraFinal;
    @FXML private TableColumn<Promocion, String> tablecolumn_promocionDia;
    @FXML private TableView<Promocion> tableview_promocionList;
    
    
    @FXML private TableColumn<CostoIndirectoProducto, String> tablecolumn_productoCostoIndirectoNombre;
    @FXML private TableColumn<CostoIndirectoProducto, Float> tablecolumn_productoCostoIndirectoMonto;
    @FXML private TableView<CostoIndirectoProducto> tableview_productoCostoIndirecto;
    
    @FXML private Button button_exportarInventario;
    @FXML private ImageView imageview_image;
    
    @FXML private AnchorPane titledPane_nuevaPeticion;
    @FXML private TitledPane titledpane_busquedaProductosPeticiones;
    
    @FXML private Tab tab_rubros;
    @FXML private Tab tab_atributos;
    
    //PETICIONES
    
    @FXML private TableColumn<Peticion, String> tablecolumn_peticionCodigo;
    @FXML private TableColumn<Peticion, String> tablecolumn_peticionProveedor;
    @FXML private TableColumn<Peticion, String> tablecolumn_peticionProducto;
    @FXML private TableColumn<Peticion, Integer> tablecolumn_peticionCantidad;
    @FXML private TableColumn<Peticion, Float> tablecolumn_peticionMonto;
    @FXML private TableColumn<Peticion, String> tablecolumn_peticionMetodo;
    @FXML private TableColumn<Peticion, String> tablecolumn_peticionEstado;
    @FXML private TableColumn<Peticion, LocalDate> tablecolumn_peticionFecha;
    @FXML private TableView<Peticion> tableview_peticionList;
    @FXML private Button button_peticionAdministrar;
    @FXML private Button button_peticion_nueva;
    private boolean administrarPeticion = false;
    
    @FXML private Button button_peticionBuscarProducto;
    @FXML private Button button_peticionBuscarProveedor;
    @FXML private TextField textfield_peticionProducto;
    @FXML private TextField textfield_peticionProveedor;
    @FXML private ComboBox<String> combobox_peticionProveedor;
    @FXML private Spinner<Integer> spinner_peticionCantidad;
    @FXML private TextField textfield_peticionMonto;
    @FXML private ComboBox<String> combobox_peticionEstado;
    @FXML private RadioButton radiobutton_peticionEfectivo;
    @FXML private RadioButton radiobutton_peticionCredito;
    @FXML private RadioButton radiobutton_peticionTarjeta;
    
    @FXML private TableColumn<Producto, String> tablecolumn_peticionProductoCodigo;
    @FXML private TableColumn<Producto, String> tablecolumn_peticionProductoNombre;
    @FXML private TableColumn<Producto, Float> tablecolumn_peticionProductoExistenciaInicial;
    @FXML private TableColumn<Producto, Float> tablecolumn_peticionProductoExistenciaActual;
    @FXML private TableColumn<Producto, Float> tablecolumn_peticionProductoExistenciaMinima;
    @FXML private TableColumn<Producto, Float> tablecolumn_peticionProductoExistenciaMaxima;
    @FXML private TableColumn<Producto, String> tablecolumn_peticionProductoTipo;
    @FXML private TableColumn<Producto, Rubro> tablecolumn_peticionProductoRubro;
    @FXML private TableColumn<Producto, Float> tablecolumn_peticionProductoPrecio;
    @FXML private TableColumn<Producto, Float> tablecolumn_peticionProductoCosto;
    @FXML private TableColumn<Producto, String> tablecolumn_peticionProductoDescripcion;
    @FXML private TableView<Producto> tableview_peticionProducto;
    
    @FXML private ComboBox<String> combobox_peticionProducto;
    @FXML private TextField textfield_peticionProductoBuscar;
    @FXML private TextField textfield_peticionProductoSeleccionado;
    @FXML private Button button_peticionProductoSeleccionar;
    @FXML private TitledPane titledpane_infoAdicionalPeticion;
    @FXML private TitledPane titledpane_busquedaProveedorPeticiones;
    
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorCodigo;
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorNombre;
    @FXML private TableColumn<Proveedores, Rubro> tablecolumn_peticionProveedorRubro;
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorDomicilio;
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorCorreo;
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorTelefono;
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorRNC;
    @FXML private TableColumn<Proveedores, String> tablecolumn_peticionProveedorSitioWeb;
    @FXML private TableColumn<Proveedores, Float> tablecolumn_peticionProveedorSaldo;
    @FXML private TableView<Proveedores> tableview_peticionProveedoresList;
    
    @FXML private TextField textfield_peticionProveedorSeleccionado;
    @FXML private Button button_peticionProveedorSeleccionar;
    
    //DESPLIEGUE DE ATRIBUTOS
    @FXML private TextField textfield_register_familia;
    @FXML private TextField textfield_registrar_atributo;
    @FXML private Button button_agregar_atributo;
    @FXML private Button button_cerrar_atributo;
    @FXML private TableColumn<Atributos, GrupoAtributo> tablecolumn_atributogrupo; 
    @FXML private TableColumn<Atributos, String> tablecolumn_atributonombre;
    @FXML private TableView<Atributos> tableView_atributos;
    @FXML private ListView<String> listView_grupoAtributos = new ListView<>();
    @FXML private TextField textfield_infoFamilia;
    @FXML private RadioButton radiobutton_atributo;
    @FXML private RadioButton radiobutton_familia;
    
    //DESPLIEGUE DE CLIENTE
    @FXML private TableColumn<Cliente, String> tablecolumn_clienteCodigo;
    @FXML private TableColumn<Cliente, String> tablecolumn_clienteNombre;
    @FXML private TableColumn<Cliente, String> tablecolumn_clienteTelefono;
    @FXML private TableColumn<Cliente, LocalDate> tablecolumn_clienteCumple;
    @FXML private TableColumn<Cliente, String> tablecolumn_clienteRNC;
    @FXML private TableColumn<Cliente, String> tablecolumn_clienteTipo;
    @FXML private TableView<Cliente> tableview_clientesList;
    @FXML private Button button_nuevoCliente;
    @FXML private Button button_modificarCliente;
    @FXML private Button button_eliminarCliente;
    @FXML private TextField textfield_clienteBusqueda;
    @FXML private ComboBox<String> combobox_buscarCliente;
    
    //DESPLIEGUE DE PROVEEDOR
    @FXML private Button button_nuevoProveedor;
    @FXML private Button button_modificarProveedor;
    @FXML private Button button_eliminarProveedor;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorCodigo;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorNombre;
    @FXML private TableColumn<Proveedores, Rubro> tablecolumn_proveedorRubro;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorDomicilio;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorCorreo;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorTelefono;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorRNC;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorSitioWeb;
    @FXML private TableColumn<Proveedores, Float> tablecolumn_proveedorSaldo;
    @FXML private TableView<Proveedores> tableview_proveedoresList;
    @FXML private TextField textfield_proveedorBusqueda;
    @FXML private ComboBox<String> combobox_buscarProveedor;
    
    //DESPLIEGUE DE Empleado
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoCodigo;
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoNombre;
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoTelefono;
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoDireccion;
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoCorreo;
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoRNC;
    @FXML private TableColumn<Empleado, String> tablecolumn_empleadoTipo;
    @FXML private TableColumn<Empleado, Float> tablecolumn_empleadoSueldo;
    @FXML private TableView<Empleado> tableview_empleadoList;
    @FXML private Button button_nuevoEmpleado;
    @FXML private Button button_modificarVendedor;
    @FXML private Button button_eliminarVendedor;
    @FXML private TextField textfield_buscarEmpleado;
    @FXML private ComboBox<String> combobox_buscarEmpleado;
    
    //DESPLIEGUE DE RUBROS
    @FXML private Button button_rubroNuevo;
    @FXML private Button button_rubroModificar;
    @FXML private Button button_rubroEliminar;
    @FXML private TextField textfield_rubroBusqueda;
    @FXML private ComboBox<String> combobox_rubroBusqueda;
    @FXML private TableColumn<Rubro, String> tablecolumn_rubroCodigo;
    @FXML private TableColumn<Rubro, String> tablecolumn_rubroNombre;
    @FXML private TableView<Rubro> tableview_rubro;
    
    //CREACION DE RUBROS
    @FXML private Pane pane_rubroCreate;
    @FXML private TextField textfield_rubroCodigo;
    @FXML private TextField textfield_rubroNombre;
    @FXML private Button button_rubroGuardar;    
    
    //Atributos
    @FXML private TableView<Atributos> tableView_Atributos;
    @FXML private Button button_atributosEliminar;
    
    //GASTOS GENERALES
    @FXML private TextField textfield_nombreGastoG;
    @FXML private TextField textfield_montoGastoG;
    @FXML private TextArea textarea_descricionGastoG;
    @FXML private DatePicker datepicker_fechaGastoG;
    @FXML private TextField textfield_busquedaGastoG;
    @FXML private ListView<String> listview_gastosG;
    @FXML private Button button_guardarGastoG;
    @FXML private Button button_modificarGastoG;
    @FXML private Button button_eliminarGastoG;
    
    //VENTAS	
    @FXML private Button button_GuardarPromedioVenta;
    @FXML private TextField textfield_PromedioVenta;
    @FXML private Button button_nuevaFactura;
    @FXML private TabPane tabpane_Ventas;
    @FXML private Tab tab_facturacion;
    @FXML private TitledPane titledpane_infoadicionalfactura;
    @FXML private Tab tab_promocion;
    @FXML private Button button_nuevaPromocion;
    @FXML private Button button_eliminarPromocion;
    
    //CONFIGURACION
    @FXML private AnchorPane pane_nuevoUsuario;
    @FXML private TitledPane titledpane_busquedaEmpleadoUsuario;
    
    @FXML private TextField textfield_empresaNombre;
    @FXML private TextField textfield_empresaRNC;
    @FXML private TextArea textarea_empresaDomicilio;
    @FXML private TextField textfield_empresaTelefono;
    
    @FXML private Spinner<Integer> spinner_empresaValorFiscalMin;
    @FXML private Spinner<Integer> spinner_empresaValorFiscalMax;
    
    @FXML private DatePicker datepicker_empresaFechaSolicitada;
    @FXML private DatePicker datepicker_empresaFechaVencimiento;
    
    @FXML private DatePicker datepicker_empresaFechaInicio;
    @FXML private DatePicker datepicker_empresaFechaFinal;
    
    @FXML private TextField textfield_cajaMaximo;
    
    @FXML private Button button_empresaGuardar;
    
    @FXML private TextField textfield_usuario;
    @FXML private TextField textfield_empleadoUsuario;
    @FXML private ComboBox<String> combobox_cargoUsuario = new ComboBox<String>();
    @FXML private TextField textfield_passwordUsuario;
    @FXML private Button button_guardarUsuario;
    @FXML private TextField textfield_buscarUsuario;
    
    @FXML private TableColumn<Usuario, String> tablecolumn_usuarioUsuario;
    @FXML private TableColumn<Usuario, String> tablecolumn_usuarioNombre;
    @FXML private TableColumn<Usuario, String> tablecolumn_usuarioCargo;
    @FXML private TableColumn<Usuario, String> tablecolumn_usuarioUltimaVez;
    @FXML private TableView<Usuario> tableview_usuarioList;
    
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoCodigo;
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoNombre;
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoTelefono;
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoDireccion;
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoCorreo;
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoRNC;
    @FXML private TableColumn<Empleado, String> tablecolumn_usuarioEmpleadoTipo;
    @FXML private TableColumn<Empleado, Float> tablecolumn_usuarioEmpleadoSueldo;
    @FXML private TableView<Empleado> tableview_usuarioEmpleadoList;
    
    @FXML private Button button_seleccionarEmpleadoUsuario;
    
    
    //Historial
    @FXML private VBox vbox_totalTransacciones;
    @FXML private VBox vbox_transaccionesporDia;
    @FXML private TextField textfield_CantidadVentasPagadas;
    @FXML private TextField textfield_IngresosVentasPagadas;
    @FXML private TextField textfield_GananciaVentasPagadas;
    @FXML private TextField textfield_CantidadVentasPorCobrar;
    @FXML private TextField textfield_IngresosVentasPorCobrar;
    @FXML private TextField textfield_PagosVentasPorCobrar;
    @FXML private TextField textfield_DeudaVentasPorCobrar;
    @FXML private TextField textfield_totalIngresos;
    @FXML private TextField textfield_TotalGanancias;
    @FXML private TextField textfield_cantidadComprasPagadas;
    @FXML private TextField textfield_egresosPagos;
    @FXML private TextField textfield_cantidadComprasPorPagar;
    @FXML private TextField textfield_egresosPeticionesPorPagar;
    @FXML private TextField textField_pagosPeticionesPorPagar;
    @FXML private TextField textField_deudaPeticionesPorPagar;
    @FXML private DatePicker datepicker_totalTransaccionesInicial;
    @FXML private DatePicker datepicker_totalTransaccionesFinal;
    @FXML private DatePicker datepicker_transaccionesPorDia;
    @FXML private TextField textfield_ventasPagadasPorDia;
    @FXML private TextField textfield_pagosVentasPorDia;
    @FXML private TextField textfield_comprasPagadasPorDia;
    @FXML private TextField textfield_pagoComprasPorDia;
    @FXML private TextField textfield_salarioEmpleadoPorDia;
    @FXML private TextField textfield_ingresosTotales;
    @FXML private TextField textfield_EgresosTotales;
    @FXML private TextField textfield_balanceTotal;
    @FXML private Button button_totalTransacciones;
    @FXML private Button button_transaccionesPorDia;
    @FXML private Button boton_cuentasPorPagar;
    @FXML private Button boton_cuentasPorCobrar;
    
    @FXML private VBox pane_cuentasPorPagar;
    @FXML private VBox pane_cuentasPorCobrar;
    
    //CAJA CHICA
    @FXML private TextField textfield_cajaFondoActual;
    
    @FXML private TextField textfield_cajaMontoAdd;
    @FXML private TextArea textarea_cajaDescripcionAdd;
    @FXML private Button button_cajaGuardarAdd;
    
    @FXML private TextField textfield_cajaMontoRemove;
    @FXML private TextArea textarea_cajaDescripcionRemove;
    @FXML private Button button_cajaGuardarRemove;
    
    @FXML private TableColumn<Float, TransaccionesCuentaBanco> tablecolumn_cuentaMonto;
    @FXML private TableColumn<String, TransaccionesCuentaBanco> tablecolumn_cuentaDescripcion;
    @FXML private TableColumn<LocalDate, TransaccionesCuentaBanco> tablecolumn_cuentaFecha;
    @FXML private TableView<TransaccionesCuentaBanco> tableview_cuentaList;
    
    //CUENTA DE BANCO
    @FXML private TextField textfield_cuentaFondoActual;
    
    @FXML private TextField textfield_cuentaMontoAdd;
    @FXML private TextArea textarea_cuentaDescripcionAdd;
    @FXML private Button button_cuentaGuardarAdd;
    
    @FXML private TextField textfield_cuentaMontoRemove;
    @FXML private TextArea textarea_cuentaDescripcionRemove;
    @FXML private Button button_cuentaGuardarRemove;
    
    @FXML private TableColumn<Float, TransaccionesCajaChica> tablecolumn_cajaMonto;
    @FXML private TableColumn<String, TransaccionesCajaChica> tablecolumn_cajaDescripcion;
    @FXML private TableColumn<LocalDate, TransaccionesCajaChica> tablecolumn_cajaFecha;
    @FXML private TableColumn<String, TransaccionesCajaChica> tablecolumn_cajaUsuario;
    @FXML private TableView<TransaccionesCajaChica> tableview_cajaList;
    
    //ADMINSITRACION
    @FXML private TabPane tabpane_administracion;
    @FXML private Tab tab_administracionGeneral;
    @FXML private Tab tab_administracionUsuarios;
    @FXML private Tab tab_administracionCajaChica;
    @FXML private Tab tab_administracionCuentaBanco;
    @FXML private Button button_nuevo_usuario;
    @FXML private Button button_eliminar_usuario;
    
    //MENU PRINCIPAL
    @FXML private AnchorPane menuPane;
    @FXML private AnchorPane bodyPane;

    @FXML private Pane pane_Principal;
    @FXML private Pane pane_Productos;
    @FXML private Pane pane_Gastos;
    @FXML private Pane pane_Ventas;
    @FXML private Pane pane_Historial;
    @FXML private Pane pane_rh;
    @FXML private Pane pane_Admin;
    @FXML private Pane pane_Ayuda;
    @FXML private VBox pane_Config;

    @FXML private MenuBar menuBar;

    @FXML private ToggleGroup toggleMenu;
    
    @FXML private Label text_menuName;
    @FXML private Label text_menuOptions;
    
    @FXML private MenuItem menuItem_Usuarios;

    Image pressed_principal = new Image(getClass().getResourceAsStream("images/buttons/selected_button_principal.png"));
	Image pressed_gastos  = new Image(getClass().getResourceAsStream("images/buttons/selected_button_gastos.png"));
	Image pressed_productos = new Image(getClass().getResourceAsStream("images/buttons/selected_button_productos.png"));
	Image pressed_ventas = new Image(getClass().getResourceAsStream("images/buttons/selected_button_ventas.png"));
	Image pressed_historial = new Image(getClass().getResourceAsStream("images/buttons/selected_button_historial.png"));
	Image pressed_rh = new Image(getClass().getResourceAsStream("images/buttons/selected_button_rh.png"));
	Image pressed_admin = new Image(getClass().getResourceAsStream("images/buttons/selected_button_admin.png"));
	Image pressed_help = new Image(getClass().getResourceAsStream("images/buttons/selected_button_help.png"));
	Image pressed_config = new Image(getClass().getResourceAsStream("images/buttons/selected_button_config.png"));
	
	Image nonpressed_principal = new Image(getClass().getResourceAsStream("images/buttons/button_principal.png"));
	Image nonpressed_gastos  = new Image(getClass().getResourceAsStream("images/buttons/button_gastos.png"));
	Image nonpressed_productos = new Image(getClass().getResourceAsStream("images/buttons/button_productos.png"));
	Image nonpressed_ventas = new Image(getClass().getResourceAsStream("images/buttons/button_ventas.png"));
	Image nonpressed_historial = new Image(getClass().getResourceAsStream("images/buttons/button_historial.png"));
	Image nonpressed_rh = new Image(getClass().getResourceAsStream("images/buttons/button_rh.png"));
	Image nonpressed_admin = new Image(getClass().getResourceAsStream("images/buttons/button_admin.png"));
	Image nonpressed_help = new Image(getClass().getResourceAsStream("images/buttons/button_help.png"));
	Image nonpressed_config = new Image(getClass().getResourceAsStream("images/buttons/button_config.png"));
	
	Image nonclicked_nuevoProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_nuevo.png"));
	Image nonclicked_modificarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_modificar.png"));
	Image nonclicked_eliminarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_eliminar.png"));
	
	Image clicked_nuevoProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_nuevo_clicked.png"));
	Image clicked_modificarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_modificar_clicked.png"));
	Image clicked_eliminarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_eliminar_clicked.png"));
	Image producto_nuevo = new Image(getClass().getResourceAsStream("images/buttons/producto_nuevo.png"));
	
	
	ImageInput pressed_principal1 = new ImageInput(); 
	ImageInput pressed_gastos1 = new ImageInput(); 
	ImageInput pressed_productos1 = new ImageInput(); 
	ImageInput pressed_ventas1 = new ImageInput(); 
	ImageInput pressed_historial1 = new ImageInput();
	ImageInput pressed_rh1 = new ImageInput(); 
	ImageInput pressed_admin1 = new ImageInput(); 
	ImageInput pressed_help1 = new ImageInput();
	ImageInput pressed_config1 = new ImageInput();
	
	ImageInput nonpressed_principal1 = new ImageInput(); 
	ImageInput nonpressed_gastos1 = new ImageInput(); 
	ImageInput nonpressed_productos1 = new ImageInput(); 
	ImageInput nonpressed_ventas1 = new ImageInput(); 
	ImageInput nonpressed_historial1 = new ImageInput(); 
	ImageInput nonpressed_rh1 = new ImageInput(); 
	ImageInput nonpressed_admin1 = new ImageInput(); 
	ImageInput nonpressed_help1 = new ImageInput(); 
	ImageInput nonpressed_config1 = new ImageInput(); 
	
	ImageInput clicked_nuevoProducto1 = new ImageInput(); 
	ImageInput clicked_modificarProducto1 = new ImageInput(); 
	ImageInput clicked_eliminarProducto1 = new ImageInput(); 
	
	ImageInput nonclicked_nuevoProducto1 = new ImageInput(); 
	ImageInput nonclicked_modificarProducto1 = new ImageInput(); 
	ImageInput nonclicked_eliminarProducto1 = new ImageInput();
	/**FIN VARIABLE PANTALLA PRINCIPAL**/
	


/**FUNCIONES MENU PRINCIPAL**/
	
	//Función que se activa al presionar el botón de principal.
   /* public void principal_pressed(ActionEvent event){  	
    	pressed_principal1.setSource(pressed_principal); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    	selected_Principal.setVisible(true);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_principal.setEffect(pressed_principal1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Principal");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(false);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(true);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    	
    } */
    
    //Función que se activa al presionar el botón de gastos.
    public void gastos_pressed(ActionEvent event){
    	pressed_gastos1.setSource(pressed_gastos ); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    //	selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(true);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_gastos.setEffect(pressed_gastos1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Gastos");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	//button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(false);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(true);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    	
    }
    
    //Función que se activa al presionar el botón de productos.
    public void productos_pressed(ActionEvent event){
    	//root es el usuario por defecto del programa, no esta guardado en la base de datos,
    	//algunas situaciones han sido validadas, pero recomendamos tener cuidado al trabajar
    	//en el programa con este usuario.
    	if(Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador")) {}
    	else if(Controladora.getInstance().getUsuarioLogueado().getCargo().getNombre().equalsIgnoreCase("Administrador"))
		{
			tab_atributos.setDisable(false);
			tab_rubros.setDisable(false);
		}
    	
    	pressed_productos1.setSource(pressed_productos); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    	//selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(true);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_productos.setEffect(pressed_productos1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Productos");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	//button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(false);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(true);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);
    	
    	
    	
    }
    
    //Función que se activa al presionar el botón de ventas.
    public void ventas_pressed(ActionEvent event){
    	pressed_ventas1.setSource(pressed_ventas); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    	//selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(true);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_ventas.setEffect(pressed_ventas1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Ventas");
    	
    //	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	//button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(false);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(true);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    	
    	
    }
    
    //Función que se activa al presionar el botón de historial.
    public void historial_pressed(ActionEvent event){
    	pressed_historial1.setSource(pressed_historial); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    //	selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(true);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_historial.setEffect(pressed_historial1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Historial");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	//button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(false);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(true);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    }
    
    //Función que se activa al presionar el botón de RR.HH.
    public void rh_pressed(ActionEvent event){
    	pressed_rh1.setSource(pressed_rh); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config); 
    	
    	//selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(true);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_rh.setEffect(pressed_rh1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Recursos Humanos");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(false);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(true);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);
    	
    	datepicker_fechaGastoG.setValue(LocalDate.now());
    	
    }
    
    //Función que se activa al presionar el botón de administración.
    public void admin_pressed(ActionEvent event){
    	pressed_admin1.setSource(pressed_admin); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config); 
    	
    	//selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(true);
    	selected_help.setVisible(false);
    	selected_config.setVisible(false);
    	
    	button_admin.setEffect(pressed_admin1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Administración");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	//button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(false);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(true);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);
    	
    }
    
    //Función que se activa al presionar el botón de configuración.
    public void config_pressed(ActionEvent event){
    	pressed_config1.setSource(pressed_config); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    	//selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	selected_config.setVisible(true);
    	
    	button_config.setEffect(pressed_config1);
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Configuración");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	//button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(false);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(true);
    	
    }
    
    //Función que se activa al presionar el botón de ayuda.
    public void help_pressed(ActionEvent event){
    	pressed_help1.setSource(pressed_help); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config); 
    	
    	//selected_Principal.setVisible(false);
    	selected_Gastos.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(true);
    	selected_config.setVisible(false);
    	
    	button_help.setEffect(pressed_help1);
    	
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuName.setText("Ayuda");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	//button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	//pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(false);
    	pane_Config.setDisable(true);
    	
    	//pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(true);
    	pane_Config.setVisible(false);

    	
    }
    
    //Función que determina si la tecla presionada en un textfield cumple con los parámetros para ser considero un valor de tipo float.
    public void floatFieldPressed(KeyEvent event) {
    	//Cuando se presiona una tecla para ser registrada como input en un textfield de javafx,
    	//esta no queda registrada hasta que se termine de procesar el evento.
    	TextField source = (TextField) event.getSource();
    	if(source.getLength() == 0) {
    		if(!Controladora.getInstance().isFloat("", event.getCharacter())) {
        		event.consume();
        	}
    	}
    	else {
    		if(!Controladora.getInstance().isFloat(source.getText(), event.getCharacter())) {
    			event.consume();
    		}
    	}
    	
    }

    //Esta función actualmente no hace nada, para serte sincero probablemente pueda ser borrada.
    public void config_close(ActionEvent event){
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuOptions.setText("Configuración");
    	
    	pane_Config.setDisable(true);
    	pane_Config.setVisible(false);
    	
    }
    
    //Determina si los parámetros de familia y atributo localizados en el tab de atributos en la ventana de productos están completos.
    public void activar_nuevoAtributo(KeyEvent event) {
    	if(!textfield_register_familia.getText().isEmpty() && !textfield_registrar_atributo.getText().isEmpty()) {
    		button_agregar_atributo.setDisable(false);
    	}
    	else {
    		button_agregar_atributo.setDisable(true);
    	}
    }
    
    //Al hacer click en el listview de gastos generales, rellena los campos del gasto y activa los botones de modificar y eliminar.
    public void selected_gastoGeneral(MouseEvent event)
    {
    	listview_gastosG.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String gasto = listview_gastosG.getSelectionModel().getSelectedItem();
    	
    	if(gasto == null)
    	{
    		textfield_nombreGastoG.setDisable(false);
    		textfield_montoGastoG.setDisable(false);
    		textarea_descricionGastoG.setDisable(false);
    		datepicker_fechaGastoG.setDisable(false);
    		textfield_nombreGastoG.setText("");
        	textfield_montoGastoG.setText("");
        	textarea_descricionGastoG.setText("");
        	datepicker_fechaGastoG.setValue(null);
        	button_modificarGastoG.setDisable(true);
        	button_eliminarGastoG.setDisable(true);
    		
    	}
    	else
    	{
    		int i;
        	ArrayList<GastoGeneral> m = Controladora.getInstance().getMisGastosGenerales();
        	for(i=0; i<m.size(); i++)
        	{
        		if(m.get(i).getNombre().equalsIgnoreCase(gasto))
        		{
        			GastoGeneral encontrado = m.get(i);
        			textfield_nombreGastoG.setText(encontrado.getNombre());
        			textfield_montoGastoG.setText(Float.toString(encontrado.getPrecioUnitario()));
        			textarea_descricionGastoG.setText(encontrado.getDescripcion());
        			datepicker_fechaGastoG.setValue(encontrado.getRemodelado());
        			break;
        		}
        	}
        	
        	textfield_nombreGastoG.setDisable(true);
    		//textfield_montoGastoG.setDisable(true);
    		//textarea_descricionGastoG.setDisable(true);
    		//datepicker_fechaGastoG.setDisable(true);
    		button_modificarGastoG.setDisable(false);
        	button_eliminarGastoG.setDisable(false);
    	}
    }
    
    //Buscador de atributos por familia.
    public void selected_familiaAtributoList(MouseEvent event) {
    	listView_grupoAtributos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String familia = listView_grupoAtributos.getSelectionModel().getSelectedItem();
    	ArrayList<Atributos> a = Controladora.getInstance().getMisAtributos();
    	String info; 
    	int cont = 0;
    	ArrayList<Atributos> filtrados = new ArrayList<>();
    	int i;
    	
    	if(familia.equalsIgnoreCase("Todos"))
    	{
    		fillAtributesList(null);
    		cont = a.size();
    	}
    	else
    	{
    		for(i=0; i<a.size(); i++)
        	{
        		if(a.get(i).getGrupo().equalsIgnoreCase(familia))
        		{
        			filtrados.add(a.get(i));
        			cont++;
        		}
        	}
    		fillAtributesList(filtrados);
    	}
    	
    	info = "Familia: " + familia + ", Cantidad de Atributos: " + cont;
    	textfield_infoFamilia.setText(info);
    }
    
    //Guarda un nuevo atributo y una nueva familia de atributo (si esta no estaba registrada posteriormente).
    public void pressed_nuevoAtributo(ActionEvent event) {
    	ObservableList<Atributos> data = FXCollections.observableArrayList();
    	ObservableList<GrupoAtributo> data2 = FXCollections.observableArrayList();
    	String nombreAtributo = textfield_registrar_atributo.getText();
    	String nombreFamilia = textfield_register_familia.getText();
    	GrupoAtributo g = new GrupoAtributo(nombreFamilia);
    	if(!Controladora.getInstance().verificarFamiliaAtributo(nombreFamilia))
    	{
    		data2.add(g);
    		if(listView_grupoAtributos.getItems().isEmpty())
    		{
    			listView_grupoAtributos.getItems().add("Todos");
    		}
    		listView_grupoAtributos.getItems().add(g.getNombre());
    		Controladora.getInstance().addGrupoAtributo(g);
    		Controladora.getInstance().guardarGrupoAtributoSQL(g);
    	}
    	else {
    		g = Controladora.getInstance().buscarGrupoAtributo(nombreFamilia);
    	}
    	Atributos a = new Atributos(nombreAtributo, g);
    	data.add(a);
    	Controladora.getInstance().addAtributo(a);
    	Controladora.getInstance().guardarAtributoSQL(a);
    	tablecolumn_atributogrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
    	tablecolumn_atributonombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tableView_atributos.getItems().add(a);
    	tableView_atributos.refresh();
    	textfield_registrar_atributo.setText("");
    	textfield_register_familia.setText("");
    	button_agregar_atributo.setDisable(true);
    }
    
    //Activa el botón de eliminar atributo si se selecciona algún elemento del tableview.
    public void activarEliminarAtributo(MouseEvent event) {
    	if(tableView_atributos.getSelectionModel().getSelectedItem() != null) {
    		button_atributosEliminar.setDisable(false);
    	}
    }
    
    //Guarda un nuevo gasto general (El if estaba pensado por si se realizaba la modificación en la misma función).
    public void pressed_nuevoGastoGeneral(ActionEvent event)
    {
    	if(!textfield_nombreGastoG.isDisabled()) {
    		Alert a = new Alert(AlertType.NONE); 
           	a.setAlertType(AlertType.WARNING);
        	String nombre = "";
       		float monto = 0;
       		String descripcion = "";
       		LocalDate fecha = null;
       		boolean canRegister = true;
       		try {
       			nombre = textfield_nombreGastoG.getText();
       			
       			descripcion = textarea_descricionGastoG.getText();
       			fecha = datepicker_fechaGastoG.getValue();
        	}
        	catch(NullPointerException e) {}
       		try {
       			monto = Float.parseFloat(textfield_montoGastoG.getText());
       		}
        	catch(NumberFormatException n) {}
        	
       		if(nombre == "" || monto == 0 || fecha == null) {
       			a.setContentText("Se requieren mas datos para registrar el gasto.");
       			a.show();
        		canRegister = false;
        	}
        		
           	if(canRegister) {
           		GastoGeneral g = new GastoGeneral(nombre, monto, descripcion, fecha);
           		int indice = listview_gastosG.getSelectionModel().getSelectedIndex();
           	
           		if(indice <= -1)
            	{
            		Controladora.getInstance().addGastoGeneral(g);
           			listview_gastosG.getItems().add(g.getNombre());
           		}
           		else
           		{
           			Controladora.getInstance().getMisGastosGenerales().remove(indice);
           			Controladora.getInstance().getMisGastosGenerales().add(indice, g);
           			listview_gastosG.getItems().remove(indice);
           			listview_gastosG.getItems().add(indice, g.getNombre());
            	}
            	
            	textfield_nombreGastoG.setText("");
           		textfield_montoGastoG.setText("");
           		textarea_descricionGastoG.setText("");
           		datepicker_fechaGastoG.setValue(LocalDate.now());
           		
           		if(Controladora.getInstance().activarLoadEmpleados() && Controladora.getInstance().activarLoadInfoEmpresa() && Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador"))
        		{
        				button_gastos.setDisable(false);
        				button_productos.setDisable(true);
        				button_ventas.setDisable(true);
        				button_historial.setDisable(true);
        				button_admin.setDisable(false);
        				button_rh.setDisable(false);
        				button_help.setDisable(false); 
        				button_config.setDisable(false); 
          	        
        				tab_administracionGeneral.setDisable(false);
        				tab_administracionCajaChica.setDisable(true);
        				tab_administracionCuentaBanco.setDisable(true);
        				admin_pressed(null);
        				selectTabAdministracionGeneral();
        		}
        		else {
        		      	rh_pressed(null);
        		      	selectTabEmpleado();
        		      	//selectTabCategoriaEmpleado();
        		        button_gastos.setDisable(true);
        		        button_productos.setDisable(true);
        		        button_ventas.setDisable(true);
        		        button_historial.setDisable(true);
        		        button_admin.setDisable(true);
        		        button_rh.setDisable(false);
        		        button_help.setDisable(false); 
        		        button_config.setDisable(false); 
        		        
        		        tab_administracionGeneral.setDisable(true);
        		        tab_administracionCajaChica.setDisable(true);
        		        tab_administracionCuentaBanco.setDisable(true);
        		}
           	}
    	}   	
    	
   }
    
    //Exporta el inventario (los productos) a una plantilla de Excel.
    public void pressed_exportarInventarioExcel(ActionEvent event)
    {
    	Archivos.carpeta();
		
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea exportar su inventario al archivo c:/ERPdata/data/inventario.xls?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    		//File archivo = new File("c:/ERPdata/data/inventario.csv");
    		
    		//Productos Estandar
    		HSSFWorkbook libro = new HSSFWorkbook();
    		HSSFSheet hoja1 = libro.createSheet("Productos Estandar");
    		int i;
    		ArrayList<String> titulosEstandar = new ArrayList<>();
    		titulosEstandar.add("Código");
    		titulosEstandar.add("Nombre");
    		titulosEstandar.add("Existencia Inicial");
    		titulosEstandar.add("Existencia Actual");
    		titulosEstandar.add("Existencia Mínima");
    		titulosEstandar.add("Existencia Máxima");
    		titulosEstandar.add("Tipo");
    		titulosEstandar.add("Rubro");
    		titulosEstandar.add("Proveedor");
    		titulosEstandar.add("Unidad de medida");
    		titulosEstandar.add("Fabricado");
    		titulosEstandar.add("Precio");
    		titulosEstandar.add("Costo de Compra");
    		titulosEstandar.add("Costo por Partida");
    		titulosEstandar.add("Costo por Mano de Obra");
    		titulosEstandar.add("Costo Total");
    		
    		HSSFRow fila = hoja1.createRow(0);
    		for (i = 0; i < titulosEstandar.size(); i++) {
    			HSSFCell celda = fila.createCell(i);
    			celda.setCellValue(titulosEstandar.get(i));
			}
    		
    		int i2 = titulosEstandar.size();
    		for (i = 0; i < Controladora.getInstance().getMisGastosGenerales().size(); i++) {
    			HSSFCell celda = fila.createCell(i2);
    			celda.setCellValue(Controladora.getInstance().getMisGastosGenerales().get(i).getNombre());
    			i2++;
			}
    		
    		int i3 = 1;
    		for (Estandar est : Controladora.getInstance().getMisProductosEstandar()) {
    			HSSFRow fila2 = hoja1.createRow(i3);
    			HSSFCell celda2 = fila2.createCell(0);
    			celda2.setCellValue(est.getCodigo());
    			HSSFCell celda3 = fila2.createCell(1);
    			celda3.setCellValue(est.getNombre());
    			HSSFCell celda4 = fila2.createCell(2);
    			celda4.setCellValue(est.getExistenciaInicial());
    			HSSFCell celda5 = fila2.createCell(3);
    			celda5.setCellValue(est.getExistenciaActual());
    			HSSFCell celda6 = fila2.createCell(4);
    			celda6.setCellValue(est.getExistenciaMinima());
    			HSSFCell celda7 = fila2.createCell(5);
    			celda7.setCellValue(est.getExistenciaMaxima());
    			HSSFCell celda8 = fila2.createCell(6);
    			celda8.setCellValue(est.getTipoProducto());
    			HSSFCell celda9 = fila2.createCell(7);
    			celda9.setCellValue(est.getRubroProducto());
    			HSSFCell celda10 = fila2.createCell(8);
    			celda10.setCellValue(est.getProveedorPrin());
    			HSSFCell celda11 = fila2.createCell(9);
    			if(est.getUnidadMedida() != null)
				{
    				celda11.setCellValue(est.getUnidadMedida().getNombre());
				}
				else
				{
					celda11.setCellValue("Sin unidad");
				}
    			HSSFCell celda12 = fila2.createCell(10);
    			if(est.isFabricado())
    			{
    				celda12.setCellValue("SI");
    			}
    			else
    			{
    				celda12.setCellValue("NO");
    			}
    			HSSFCell celda13 = fila2.createCell(11);
    			celda13.setCellValue(est.getPrecio());
    			HSSFCell celda14 = fila2.createCell(12);
    			celda14.setCellValue(est.getCostoDeCompra());
    			HSSFCell celda15 = fila2.createCell(13);
    			celda15.setCellValue(est.getCostoPartida());
    			HSSFCell celda16 = fila2.createCell(14);
    			celda16.setCellValue(est.getManodeobra());
    			HSSFCell celda17 = fila2.createCell(15);
    			celda17.setCellValue(est.getCosto());
    			int i4 = 16;
    			for (GastoGeneral gast : Controladora.getInstance().getMisGastosGenerales()) 
    			{
    				HSSFCell celda18 = fila2.createCell(i4);
    				if(est.getCostosIndirectos() != null)
    				{
    					for(CostoIndirectoProducto cost: est.getCostosIndirectos())
    					{
    	    				if(cost.getNombre().equalsIgnoreCase(gast.getNombre()))
    						{
    	    					celda18.setCellValue(cost.getValor());
    	    					break;
    						}
    						else
    						{
    							celda18.setCellValue(0);
    						}
    					}
    				}
    				else
    				{
    					celda18.setCellValue(0);
    				}
	    			i4++;
    			}
    			i3++;
			}
    		
    		HSSFSheet hoja2 = libro.createSheet("Productos Matriz");
    		int i5;
    		ArrayList<String> titulosMatriz = new ArrayList<>();
    		titulosMatriz.add("Código");
    		titulosMatriz.add("Nombre");
    		titulosMatriz.add("N. de Serie");
    		titulosMatriz.add("Atributo 1");
    		titulosMatriz.add("Atributo 2");
    		titulosMatriz.add("Atributo 3");
    		titulosMatriz.add("Existencia Inicial");
    		titulosMatriz.add("Existencia Actual");
    		titulosMatriz.add("Existencia Mínima");
    		titulosMatriz.add("Existencia Máxima");
    		titulosMatriz.add("Tipo");
    		titulosMatriz.add("Rubro");
    		titulosMatriz.add("Proveedor");
    		titulosMatriz.add("Unidad de medida");
    		titulosMatriz.add("Fabricado");
    		titulosMatriz.add("Precio");
    		titulosMatriz.add("Costo de Compra");
    		titulosMatriz.add("Costo por Partida");
    		titulosMatriz.add("Costo por Mano de Obra");
    		titulosMatriz.add("Costo Total");
    		
    		HSSFRow fila3 = hoja2.createRow(0);
    		for (i5 = 0; i5 < titulosMatriz.size(); i5++) {
    			HSSFCell celda = fila3.createCell(i5);
    			celda.setCellValue(titulosMatriz.get(i5));
			}
    		
    		int i6 = titulosMatriz.size();
    		for (i5 = 0; i5 < Controladora.getInstance().getMisGastosGenerales().size(); i5++) {
    			HSSFCell celda = fila3.createCell(i6);
    			celda.setCellValue(Controladora.getInstance().getMisGastosGenerales().get(i5).getNombre());
    			i6++;
			}
    		
    		int i7 = 1;
    		for (Estandar est : Controladora.getInstance().getMisProductosMatriz()) {
    			for (Combinaciones comb : est.getCombinaciones()) {
    				HSSFRow fila4 = hoja2.createRow(i7);
        			HSSFCell celda2 = fila4.createCell(0);
        			celda2.setCellValue(est.getCodigo());
        			HSSFCell celda3 = fila4.createCell(1);
        			celda3.setCellValue(est.getNombre());
        			HSSFCell celda19 = fila4.createCell(2);
        			celda19.setCellValue(comb.numeroSerie);
        			HSSFCell celda20 = fila4.createCell(3);
        			if(comb.getAtributo1() != "")
        			{
        				celda20.setCellValue(comb.getAtributo1());
        			}
        			else
        			{
        				celda20.setCellValue("Sin Atributo");
        			}
        			HSSFCell celda21 = fila4.createCell(4);
        			if(comb.getAtributo2() != "")
        			{
        				celda21.setCellValue(comb.getAtributo2());
        			}
        			else
        			{
        				celda21.setCellValue("Sin Atributo");
        			}
        			
        			HSSFCell celda22 = fila4.createCell(5);
        			if(comb.getAtributo3() != "")
        			{
        				celda22.setCellValue(comb.getAtributo3());
        			}
        			else
        			{
        				celda22.setCellValue("Sin Atributo");
        			}
        			
        			HSSFCell celda4 = fila4.createCell(6);
        			celda4.setCellValue(est.getExistenciaInicial());
        			HSSFCell celda5 = fila4.createCell(7);
        			celda5.setCellValue(comb.getExistenciaActual());
        			HSSFCell celda6 = fila4.createCell(8);
        			celda6.setCellValue(est.getExistenciaMinima());
        			HSSFCell celda7 = fila4.createCell(9);
        			celda7.setCellValue(est.getExistenciaMaxima());
        			HSSFCell celda8 = fila4.createCell(10);
        			celda8.setCellValue(est.getTipoProducto());
        			HSSFCell celda9 = fila4.createCell(11);
        			celda9.setCellValue(est.getRubroProducto());
        			HSSFCell celda10 = fila4.createCell(12);
        			celda10.setCellValue(est.getProveedorPrin());
        			HSSFCell celda11 = fila4.createCell(13);
        			if(est.getUnidadMedida() != null)
    				{
        				celda11.setCellValue(est.getUnidadMedida().getNombre());
    				}
    				else
    				{
    					celda11.setCellValue("Sin unidad");
    				}
        			HSSFCell celda12 = fila4.createCell(14);
        			if(est.isFabricado())
        			{
        				celda12.setCellValue("SI");
        			}
        			else
        			{
        				celda12.setCellValue("NO");
        			}
        			HSSFCell celda13 = fila4.createCell(15);
        			celda13.setCellValue(est.getPrecio());
        			HSSFCell celda14 = fila4.createCell(16);
        			celda14.setCellValue(est.getCostoDeCompra());
        			HSSFCell celda15 = fila4.createCell(17);
        			celda15.setCellValue(est.getCostoPartida());
        			HSSFCell celda16 = fila4.createCell(18);
        			celda16.setCellValue(est.getManodeobra());
        			HSSFCell celda17 = fila4.createCell(19);
        			celda17.setCellValue(est.getCosto());
        			int i4 = 20;
        			for (GastoGeneral gast : Controladora.getInstance().getMisGastosGenerales()) 
        			{
        				HSSFCell celda18 = fila4.createCell(i4);
        				if(est.getCostosIndirectos() != null)
        				{
        					for(CostoIndirectoProducto cost: est.getCostosIndirectos())
        					{
        	    				if(cost.getNombre().equalsIgnoreCase(gast.getNombre()))
        						{
        	    					celda18.setCellValue(cost.getValor());
        						}
        						else
        						{
        							celda18.setCellValue(0);
        						}
        					}
        				}
        				else
        				{
        					celda18.setCellValue(0);
        				}
        				
        				i4++;
        			}
        			i7++;
    			}
			}
    		
    		//Servicios
    		HSSFSheet hoja3 = libro.createSheet("Servicios");
    		int i8;
    		ArrayList<String> titulosServicio = new ArrayList<>();
    		titulosServicio.add("Código");
    		titulosServicio.add("Nombre");
    		titulosServicio.add("Tipo");
    		titulosServicio.add("Rubro");
    		titulosServicio.add("Encargados Mano de Obra");
    		titulosServicio.add("Horas Mano de Obra");
    		titulosServicio.add("Precio");
    		titulosServicio.add("Costo por Mano de Obra");
    		titulosServicio.add("Costo Total");
    		
    		HSSFRow fila5 = hoja3.createRow(0);
    		for (i8 = 0; i8 < titulosServicio.size(); i8++) {
    			HSSFCell celda = fila5.createCell(i8);
    			celda.setCellValue(titulosServicio.get(i8));
			}
    		
    		int i9 = titulosServicio.size();
    		for (i8 = 0; i8 < Controladora.getInstance().getMisGastosGenerales().size(); i8++) {
    			HSSFCell celda = fila5.createCell(i9);
    			celda.setCellValue(Controladora.getInstance().getMisGastosGenerales().get(i8).getNombre());
    			i9++;
			}
    		
    		int i10 = 1;
    		for (Servicio est : Controladora.getInstance().getMisProductosServicio()) {
    			HSSFRow fila2 = hoja3.createRow(i10);
    			HSSFCell celda2 = fila2.createCell(0);
    			celda2.setCellValue(est.getCodigo());
    			HSSFCell celda3 = fila2.createCell(1);
    			celda3.setCellValue(est.getNombre());
    			HSSFCell celda8 = fila2.createCell(2);
    			celda8.setCellValue(est.getTipoProducto());
    			HSSFCell celda9 = fila2.createCell(3);
    			celda9.setCellValue(est.getRubroProducto());
    			HSSFCell celda13 = fila2.createCell(4);
    			celda13.setCellValue(est.getCategoria().getNombre());
    			HSSFCell celda14 = fila2.createCell(5);
    			celda14.setCellValue(est.getInfoManoDeObra().getCantidadHoras());
    			HSSFCell celda15 = fila2.createCell(6);
    			celda15.setCellValue(est.getPrecio());
    			HSSFCell celda16 = fila2.createCell(7);
    			celda16.setCellValue(est.getManodeobra());
    			HSSFCell celda17 = fila2.createCell(8);
    			celda17.setCellValue(est.getCosto());
    			int i4 = 9;
    			for (GastoGeneral gast : Controladora.getInstance().getMisGastosGenerales()) 
    			{
    				HSSFCell celda18 = fila2.createCell(i4);
    				if(est.getCostosIndirectos() != null)
    				{
    					for(CostoIndirectoProducto cost: est.getCostosIndirectos())
    					{
    	    				if(cost.getNombre().equalsIgnoreCase(gast.getNombre()))
    						{
    	    					celda18.setCellValue(cost.getValor());
    						}
    						else
    						{
    							celda18.setCellValue(0);
    						}
    					}
    				}
    				else {
    					celda18.setCellValue(0);
    				}
	    			i4++;
    			}
    			i10++;
			}
    		
    		int o;
    		for(o=0; o < titulosEstandar.size()+Controladora.getInstance().getMisGastosGenerales().size(); o++)
    		{
    			hoja1.autoSizeColumn(o);
    			hoja2.autoSizeColumn(o);
    			hoja3.autoSizeColumn(o);
    		}
    		
    		try {
    			FileOutputStream archivo = new FileOutputStream("c:/ERPdata/data/inventario/inventario.xls");
    			libro.write(archivo);
    			archivo.close();
    		} catch (Exception e) {
				System.out.println("No se escribió el archivo de inventario en Excel");
			}
    	}	
    }
    
    //Realiza la modificación de un gasto general 
    //(la data anterior no se elimina, se realiza otra instancia de la clase y otra fila en la base de datos, 
    //y la data anterior se le marca una variable booleana llamada “borrado” como true).
    public void pressed_modificarGastoGeneral(ActionEvent event)
    {
    	GastoGeneral gasto = Controladora.getInstance().buscarGasto(textfield_nombreGastoG.getText());
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar " + gasto.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	if(alert.getResult() == ButtonType.YES) {
    		boolean canModify = true;
    		boolean productUsing = false;
    		boolean stillSame = false;
    		ArrayList<String> productosGastos = new ArrayList<>();
    		for(Producto p : Controladora.getInstance().getMisProductos()) {
    			for(CostoIndirectoProducto g : p.getCostosIndirectos()) {
    				if(g.getNombre() == gasto.getNombre()) {
    					canModify = false;
    					productUsing = true;
    					productosGastos.add(p.getNombre());
    				}
    			}
    		}
    		if(Float.parseFloat(textfield_montoGastoG.getText()) == gasto.getPrecioUnitario() && textfield_montoGastoG.getText().isEmpty()
    				&& datepicker_fechaGastoG.getValue() == gasto.getRemodelado() && textarea_descricionGastoG.getText() == gasto.getDescripcion()) {
    			canModify = false;
    			if(!productUsing) {
    				stillSame = true;
    			}
    		}
    		
    		if(canModify) {
    			int indice = Controladora.getInstance().getMisGastosGenerales().indexOf(gasto);
    			Controladora.getInstance().getMisGastosGenerales().get(indice).setBorrado(true);
    			Controladora.getInstance().borrarGastoGeneral(indice+1);
    			listview_gastosG.getItems().clear();
    			fillGastosGenerales(null);
    			textfield_nombreGastoG.setDisable(false);
    			pressed_nuevoGastoGeneral(null);		
    		}
    		else {
    			Alert a = new Alert(AlertType.WARNING);
    			if(productUsing) {
    				a.setContentText("Remueva el gasto del producto que lo esta utilizando");
    			}
    			else if(stillSame) {
    				a.setContentText("No ha modificado nada del producto");
    			}
    			a.show();
    		}
    		
    	}
    	/**if(textfield_nombreGastoG.isDisabled() && textfield_montoGastoG.isDisabled() && textarea_descricionGastoG.isDisabled() && datepicker_fechaGastoG.isDisabled())
    	{
    		textfield_nombreGastoG.setDisable(false);
    		textfield_montoGastoG.setDisable(false);
    		textarea_descricionGastoG.setDisable(false);
    		datepicker_fechaGastoG.setDisable(false);
    	}
    	else
    	{
    		textfield_nombreGastoG.setDisable(true);
    		textfield_montoGastoG.setDisable(true);
    		textarea_descricionGastoG.setDisable(true);
    		datepicker_fechaGastoG.setDisable(true);
    	}**/
    }
    
    //Se elimina un gasto general (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void eliminarGastoGeneral(ActionEvent event) {
    	GastoGeneral encontrado = null;
    	Controladora.getInstance().getMisCostosIndirectos();
    	String gasto = listview_gastosG.getSelectionModel().getSelectedItem();
    	for(GastoGeneral g : Controladora.getInstance().getMisGastosGenerales()) {
    		if(g.getNombre().equalsIgnoreCase(gasto))
    		{
    			encontrado = g;
    			break;
    		}
    	}
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar " + encontrado.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	if(alert.getResult() == ButtonType.YES) {
    		boolean canDelete = true;
    		ArrayList<String> productosGastos = new ArrayList<>();
    		for(Producto p : Controladora.getInstance().getMisProductos()) {
    			for(CostoIndirectoProducto g : p.getCostosIndirectos()) {
    				if(g.getNombre() == encontrado.getNombre()) {
    					canDelete = false;
    					productosGastos.add(p.getNombre());
    				}
    			}
    		}
    		if(canDelete) {
    			int indice = Controladora.getInstance().getMisGastosGenerales().indexOf(encontrado);
    			Controladora.getInstance().getMisGastosGenerales().get(indice).setBorrado(true);
    			Controladora.getInstance().borrarGastoGeneral(indice+1);
    			listview_gastosG.getItems().clear();
    			fillGastosGenerales(null);
    		}
    		else {
    			Alert a = new Alert(AlertType.WARNING);
    			a.setContentText("Remueva el gasto del producto que lo esta utilizando");
    			a.show();
    		}
    	}
    }
    
    //Puedes encargarte de darle algún propósito a una función, es completamente opcional.
    public void pressed_modificarAtributoOGrupo(ActionEvent event) {
    	//if()
    }
    
    //Abre la ventana de nuevoProducto.fxml que es manejada por el controlador ControllerNuevoProducto.java.
    public void pressed_nuevoProducto(ActionEvent event){
    	
    	/**ABRIENDO nuevoProducto.fxml**/
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoProducto.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Window owner = button_nuevoProducto.getScene().getWindow();
			
			/* Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			 if(primaryScreenBounds.getHeight()<750) {
			 stage.setY(primaryScreenBounds.getMinY());
			  stage.setHeight(primaryScreenBounds.getHeight());
			 }*/
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Producto");
			stage.setScene(new Scene(root1)); 
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.productos_pressed(null);
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);

			  		    primaryStage.show();
			  		    owner.hide();
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  });
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //Abre la ventana de nuevoProducto.fxml que es manejada por el controlador ControllerNuevoProducto.java con la intención de modificar un producto.
    public void pressed_modificarProducto(ActionEvent event){
    	try {
    		Producto producto = tableview_productList.getSelectionModel().getSelectedItem();
    		
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoProducto.fxml"));
			Parent root1;
			root1 = fxmlLoader.load();
			ControllerNuevoProducto controllerProducto = fxmlLoader.getController();
			controllerProducto.modifyOpen(producto);
			
			Stage stage = new Stage();
			Window owner = button_modificarProducto.getScene().getWindow();
			
			stage.setTitle("Nuevo Producto");
			stage.setScene(new Scene(root1)); 
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.productos_pressed(null);
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);

			  		    primaryStage.show();
			  		    owner.hide();
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  });
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }
    
    //Se elimina un producto (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void pressed_eliminarProducto(ActionEvent event){
    	Producto producto = tableview_productList.getSelectionModel().getSelectedItem();

    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar " + producto.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    		if(producto!=null) {
    			int indice = Controladora.getInstance().getMisProductos().indexOf(producto);
    			Controladora.getInstance().getMisProductos().get(indice).setBorrado(true);
    			if(producto.getTipoProducto().equalsIgnoreCase("Estandar") || producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    				int indiceEstandar = Controladora.getInstance().getMisProductosEstandar().indexOf(producto);
    				Controladora.getInstance().getMisProductosEstandar().get(indiceEstandar).setBorrado(true);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    				int indiceKit = Controladora.getInstance().getMisProductosKit().indexOf(producto);
    				Controladora.getInstance().getMisProductosKit().get(indiceKit).setBorrado(true);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Servicio")) {
    				int indiceServicio = Controladora.getInstance().getMisProductosServicio().indexOf(producto);
    				Controladora.getInstance().getMisProductosServicio().get(indiceServicio).setBorrado(true);
    			}
        		Controladora.getInstance().borrarProducto(indice+1);
        		fillProductList(null, "");
        	}
    	}
    	
    	//Agregar acá un reload
    }
    
    //Reinicia la ventana (no creo que se este usando pero recomendamos dejarla en cualquier caso).
    public void reload(Stage stage) {
    	
   		try {
        	Stage primaryStage = new Stage();
        	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		    Parent root = f.load();
		    Controller c = f.getController();
		    c.productos_pressed(null);
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.setTitle("Centro Pymes");
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
		    primaryStage.setMaximized(true);
		    Window owner = stage.getOwner();
		    primaryStage.show();
		    owner.hide();

		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
   		
	}
    
    //Abre la ventana de nuevoCliente.fxml que es manejada por el controlador ControllerNuevoCliente.java.
    public void pressed_nuevoCliente(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoCliente.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Window owner = button_nuevoCliente.getScene().getWindow();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Cliente");
			stage.setScene(new Scene(root1));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
			  		 
			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.ventas_pressed(null);
			  		    c.selectTabCliente();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);
			  		    
			  		    primaryStage.show();
			  		    owner.hide();
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  }); 
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //Se elimina un cliente (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void pressed_eliminarCliente(ActionEvent event) {
    	Cliente cliente = tableview_clientesList.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar " + cliente.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	
    	if (alert.getResult() == ButtonType.YES) {
    		if(cliente!=null) {
    			if(cliente.getDeuda() > 0) {
    				Alert deudaAlert = new Alert(AlertType.WARNING);
    				deudaAlert.setContentText("El cliente aun tiene deudas pendientes");
    				deudaAlert.show();
    			}
    			else if(cliente.getCredito() > 0) {
    				Alert creditoAlert = new Alert(AlertType.WARNING);
    				creditoAlert.setContentText("El cliente aun posee credito.");
    				creditoAlert.show();
    			}
    			else {
    				int indice = Controladora.getInstance().getMisClientes().indexOf(cliente);
    				Controladora.getInstance().getMisClientes().get(indice).setBorrado(true);
    				Controladora.getInstance().borrarCliente(indice+1);
    				fillClientList(null);
    			}  			
        	}
    	}
    }
    
    //Abre la ventana de nuevoProveedor.fxml que es manejada por el controlador ControllerNuevoProveedor.java.
    public void pressed_nuevoProveedor(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoProveedor.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Window owner = button_nuevoCliente.getScene().getWindow();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Proveedor");
			stage.setScene(new Scene(root1));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.ventas_pressed(null);
					    c.selectTabProveedor();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);

			  		    primaryStage.show();
			  		    owner.hide();
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  }); 
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Se elimina un proveedor (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void pressed_eliminarProveedor(ActionEvent event) {
    	Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar a" + proveedor.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	
    	if (alert.getResult() == ButtonType.YES) {
    		if(proveedor!=null) {
    			int indice = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
    			Controladora.getInstance().getMisProveedores().get(indice).setBorrado(true);
    			Controladora.getInstance().borrarProveedor(indice+1);
    			fillProveedorList(null, "");	
        	}
    	}
    }
    
    //Abre la ventana de nuevoEmpleado.fxml que es manejada por el controlador ControllerNuevoEmpleado.java.
    public void pressed_nuevoEmpleado(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoEmpleado.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Window owner = button_nuevoEmpleado.getScene().getWindow();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Vendedor");
			stage.setScene(new Scene(root1));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.rh_pressed(null);
			  		    c.selectTabEmpleado();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);

			  		    primaryStage.show();
			  		    owner.hide();
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  });
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Se elimina un empleado (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void pressed_eliminarEmpleado(ActionEvent event) {
    	Empleado empleado = tableview_empleadoList.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar a" + empleado.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	
    	if(Controladora.getInstance().isEmpleadoInUsuario(empleado.getCodigo())) {
    		Alert exist = new Alert(AlertType.WARNING, "Empleado en uso.");
    		exist.showAndWait();
    	}
    	else if (alert.getResult() == ButtonType.YES) {
    		if(empleado!=null) {
    			int indice = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
    			Controladora.getInstance().getMisEmpleados().get(indice).setBorrado(true);
    			Controladora.getInstance().borrarEmpleado(indice+1);
    			fillEmpleadoList(null, "");	
        	}
    	}
    }
    
    //Guarda un nuevo usuario.
    public void pressed_guardarUsuario(ActionEvent event)
    {
    	//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validación de los parámetros, si se te es posible estandarizarlo, recomendamos hacerlo.
    	if(!textfield_usuario.getText().equals("") && !textfield_empleadoUsuario.getText().equals("") && !textfield_passwordUsuario.getText().equals("") && combobox_cargoUsuario.getSelectionModel().getSelectedIndex()>0)
    	{
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Confirmar creación del usuario" + textfield_usuario, ButtonType.YES, ButtonType.NO);
        	alert.showAndWait();
        	
        	if (alert.getResult() == ButtonType.YES) {
        		Empleado emp = Controladora.getInstance().buscarEmpleado(textfield_empleadoUsuario.getText());
        		String contrasena = textfield_passwordUsuario.getText();
        		String usuario = textfield_usuario.getText();
        		Cargo cargo = Controladora.getInstance().buscarCargo(combobox_cargoUsuario.getSelectionModel().getSelectedItem());
        		Usuario usu = new Usuario(usuario, emp, true, contrasena, true, cargo);
        		Controladora.getInstance().getMisUsuarios().add(usu);
        		
        	}
        }
    	
    }

    //Abre la ventana de nuevaFactura.fxml que es manejada por el controlador ControllerNuevaFactura.java.
    public void pressed_nuevaFactura(ActionEvent event){
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevaFactura.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Window owner = button_nuevaFactura.getScene().getWindow();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nueva Factura");
			stage.setScene(new Scene(root1));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.ventas_pressed(null);
			  		    c.selectTabFacturas();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);

			  		    primaryStage.show();
			  		    owner.hide();
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  });
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //Abre la ventana de nuevaPromocion.fxml que es manejada por el controlador ControllerNuevaPromocion.java.
    public void pressed_nuevaPromocion(ActionEvent event){
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevaPromocion.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Window owner = button_nuevaPromocion.getScene().getWindow();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nueva Promoción");
			stage.setScene(new Scene(root1));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//Teníamos la dificultad en que no podíamos actualizar los datos de la ventana principal una vez añadida una nueva información en otra ventana,
			//el código dentro de setOnCloseRequest se encarga de cerrar y abrir la ventana principal una vez se salga de la ventana externa, esta hecho de
			//forma que retorne al punto desde donde se ingresó a la ventana externa, eres bienvenido a intentar una solución alterna puesto que entendemos
			//que esto no es lo mas efectivo.
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.ventas_pressed(null);
			  		    c.selectTabPromocion();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);

			  		    primaryStage.show();
			  		    owner.hide();
			  		    
			  		    
			  		} catch (IOException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  			}
			      }
			  });
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**FUNCIONES GENERALES**/
    
    //Verifica si el input de un textfield es un número.
    public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    }
    
    //Cierra la venta de nuevoProducto (Realmente no esta haciendo nada).
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    
    //Busqueda de clientes.
    public void buscarClientes(KeyEvent event) {
    	ArrayList<Cliente> clientes = new ArrayList<>();
    	if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_clienteBusqueda.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
    		clientes = Controladora.getInstance().searchClientes(textfield_clienteBusqueda.getText().toLowerCase(), combobox_buscarCliente.getValue());
    	}
    	else {
    		clientes = Controladora.getInstance().searchClientes(textfield_clienteBusqueda.getText().toLowerCase() + event.getCharacter().toLowerCase(), combobox_buscarCliente.getValue());
    	}
    	if(clientes.size() == 0) {
    		fillClientList(null);
    	}
    	else {
    		fillClientList(clientes);
    	}
    }
    
    //Busqueda de productos.
    public void buscarProductos(KeyEvent event) {
    	TextField textfield = (TextField) event.getSource();
    	ArrayList<Producto> productos = new ArrayList<>();
    	if(textfield.equals(textfield_productBuscar)) {
    		if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_productBuscar.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
        		productos = Controladora.getInstance().searchProducts(textfield_productBuscar.getText().toLowerCase(), combobox_productBuscar.getValue());
        	}
        	else {
        		productos = Controladora.getInstance().searchProducts(textfield_productBuscar.getText().toLowerCase() + event.getCharacter().toLowerCase(), combobox_productBuscar.getValue());
        	}
    		if(productos.size() == 0) {
    			fillProductList(null, "");
    		}
    		else {
    			fillProductList(productos, "");
    		}
    	}
    	else if(textfield.equals(textfield_peticionProductoBuscar)) {
    		if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_productBuscar.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
        		productos = Controladora.getInstance().searchProducts(textfield_peticionProductoBuscar.getText().toLowerCase(), combobox_peticionProducto.getValue());
        	}
        	else {
        		productos = Controladora.getInstance().searchProducts(textfield_peticionProductoBuscar.getText().toLowerCase() + event.getCharacter().toLowerCase(), combobox_peticionProducto.getValue());
        	}
    		if(productos.size() == 0) {
    			fillProductList(null, "Peticion");
    		}
    		else {
    			fillProductList(productos, "Peticion");
    		}
    	}
    }
    
    //Busqueda de proveedores.
    public void buscarProveedores(KeyEvent event) {
    	ArrayList<Proveedores> proveedores = new ArrayList<>();
    	if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_proveedorBusqueda.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_proveedorBusqueda.getText().toLowerCase(), combobox_buscarProveedor.getValue());
    	}
    	else {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_proveedorBusqueda.getText().toLowerCase() + event.getCharacter().toLowerCase(), combobox_buscarProveedor.getValue());
    	}
    	if(proveedores.size() == 0) {
    		fillProveedorList(null, "");
    	}
    	else {
    		fillProveedorList(proveedores, "");
    	}
    }
    
    //Busqueda de empleados.
    public void buscarEmpleados(KeyEvent event) {
    	ArrayList<Empleado> empleados = new ArrayList<>();
    	if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_buscarEmpleado.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
    		empleados = Controladora.getInstance().searchEmpleados(textfield_buscarEmpleado.getText().toLowerCase(), combobox_buscarEmpleado.getValue());
    	}
    	else {
    		empleados = Controladora.getInstance().searchEmpleados(textfield_buscarEmpleado.getText().toLowerCase() + event.getCharacter().toLowerCase(), combobox_buscarEmpleado.getValue());
    	}
    	if(empleados.size() == 0) {
    		fillEmpleadoList(null, "");;
    	}
    	else {
    		fillEmpleadoList(empleados, "");
    	}
    	
    }
    
    //Busqueda de rubros.
    public void buscarRubros(KeyEvent event) {
    	ArrayList<Rubro> rubros = new ArrayList<>();
    	if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_rubroBusqueda.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
    		rubros = Controladora.getInstance().searchRubro(textfield_rubroBusqueda.getText().toLowerCase(), combobox_rubroBusqueda.getValue());
    	}
    	else {
    		rubros = Controladora.getInstance().searchRubro(textfield_rubroBusqueda.getText().toLowerCase() + event.getCharacter().toLowerCase(), combobox_rubroBusqueda.getValue());
    	}
    	if(rubros.size() == 0) {
    		fillRubroList(null);
    	}
    	else {
    		fillRubroList(rubros);
    	}
    	
    }
    
    //Busqueda de usuarios
    public void buscarUsuarios(KeyEvent event) {
    	ArrayList<Usuario> usuarios = new ArrayList<>();
    	if(event.getCode().equals(KeyCode.BACK_SPACE) || (textfield_buscarUsuario.getLength() == 1 && event.getCode().equals(KeyCode.BACK_SPACE))) {
    		usuarios = Controladora.getInstance().searchUsuarios(textfield_buscarUsuario.getText().toLowerCase());
    	}
    	else {
    		usuarios = Controladora.getInstance().searchUsuarios(textfield_buscarUsuario.getText().toLowerCase() + event.getCharacter().toLowerCase());
    	}
    	
    	if(usuarios.size() == 0) {
    		fillUsuario(null);
    	}
    	else {
    		fillUsuario(usuarios);
    	}
    	
    }
    
    //FUNCIONES PARA CREAR RUBROS
    
    public void activarRegistro(ActionEvent event) {
    	pane_rubroCreate.setVisible(true);
    }
    
    public void cerrarRegistro(ActionEvent event) {
    	pane_rubroCreate.setVisible(false);
    }
    
    //Determina si los parámetros de los rubros localizado en el tab de rubros en la ventana de rubros están completos.
    public void activarGuardarRubro(KeyEvent event) {
    	//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validación de los parámetros, si se te es posible estandarizarlo, recomendamos hacerlo.
    	if(textfield_rubroCodigo.getLength() > 0 && textfield_rubroNombre.getLength() > 0) {
    		button_rubroGuardar.setDisable(false);
    	}
    	else {
    		button_rubroGuardar.setDisable(true);
    	}
    }
    
    //Guarda un nuevo rubro.
    public void guardarRubro(ActionEvent event) {
    	ObservableList<Rubro> data = FXCollections.observableArrayList();
    	String codigo = textfield_rubroCodigo.getText();
    	String nombre = textfield_rubroNombre.getText();
    	Rubro rubro = new Rubro(codigo, nombre);
    	if(!Controladora.getInstance().rubroCodeExists(rubro)) {
    		data.add(rubro);
    		Controladora.getInstance().getMisRubros().add(rubro);
    		Controladora.getInstance().guardarRubroSQL(rubro);
    		tablecolumn_rubroCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		tablecolumn_rubroNombre.setCellValueFactory(new PropertyValueFactory<>("nombreRubro"));
    		tableview_rubro.getItems().add(rubro);
    		tableview_rubro.refresh();
    		textfield_rubroCodigo.setText("");
    		textfield_rubroNombre.setText("");
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING, "El código ya está en uso");
    		alert.showAndWait();
    	}
    	
    }
    
    //Activa el botón de eliminar rubro dependiendo si hay un elemento seleccionado en el tableview.
    public void rubroTableViewClicked(MouseEvent event) {
    	if(!tableview_rubro.getSelectionModel().isEmpty()) {
    		button_rubroEliminar.setDisable(false);
    	}
    	else {
    		button_rubroEliminar.setDisable(true);
    	}
    }
    
    //Se elimina un rubro (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void eliminarRubro(ActionEvent event) {
    	Rubro rubro = tableview_rubro.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar " + rubro.getNombreRubro() + "?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	
    	if (alert.getResult() == ButtonType.YES) {
    		if(rubro!=null) {
    			if(Controladora.getInstance().isRubroInProduct(rubro)) {
    				Alert rubroTaken = new Alert(AlertType.CONFIRMATION);
    				rubroTaken.setContentText("Este rubro es parte de un producto.");
    				rubroTaken.show();
    			}
    			else {
    				int indice = Controladora.getInstance().getMisRubros().indexOf(rubro);
    				Controladora.getInstance().getMisRubros().get(indice).setBorrado(true);
    				Controladora.getInstance().borrarRubro(indice+1);
    				fillRubroList(null);
    			}
    				
        	}
    	}
    }
    
    //Se elimina un atributo o familia atributo dependiendo del radiobutton seleccionado (la data no se elimina, se le marca una variable booleana llamada “borrado” como true).
    public void eliminarAtributo(ActionEvent event) {
    	Atributos atributo = tableView_atributos.getSelectionModel().getSelectedItem();
    	if(radiobutton_atributo.isSelected()) {
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar " + atributo.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    		alert.showAndWait();
    	
    		if(alert.getResult() == ButtonType.YES) {
    			if(Controladora.getInstance().isAtributoInProduct(atributo.getNombre())) {
    				Alert atributoUsed = new Alert(AlertType.WARNING, "Este atributo pertenece a un producto");
    				atributoUsed.showAndWait();
    			}
    			else {
    				int indice = Controladora.getInstance().getMisAtributos().indexOf(atributo);
    				Controladora.getInstance().getMisAtributos().get(indice).setBorrado(true);
    				Controladora.getInstance().borrarAtributo(indice+1);
    				fillAtributesList(null);
    			}
    		}
    	}
    	else if(radiobutton_familia.isSelected()) {
    		GrupoAtributo grupoAtributo = atributo.getGrupoAtributo();
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar " + grupoAtributo.getNombre() + "?", ButtonType.YES, ButtonType.NO);
    		alert.showAndWait();
    		
    		
    		if(alert.getResult() == ButtonType.YES) {
    			if(Controladora.getInstance().isFamiliaInProduct(grupoAtributo.getNombre())) {
    				Alert grupoUsed = new Alert(AlertType.WARNING, "Este grupo de familia pertenece a un producto");
    				grupoUsed.showAndWait();
    			}
    			else {
    				for(Atributos a : Controladora.getInstance().getMisAtributos()) {
    					if(a.getGrupoAtributo().equals(grupoAtributo)) {
    						int indiceAtributo = Controladora.getInstance().getMisAtributos().indexOf(a);
    						Controladora.getInstance().getMisAtributos().get(indiceAtributo).setBorrado(true);
    					}
    				}
    				int indice = Controladora.getInstance().getMisGrupoAtributo().indexOf(grupoAtributo);
    				Controladora.getInstance().getMisGrupoAtributo().get(indice).setBorrado(true);
    				Controladora.getInstance().borrarAtributo(indice+1);
    				fillAtributesList(null);
    			}
    		}
    	}
    	tableView_atributos.getSelectionModel().clearSelection();
    	button_atributosEliminar.setDisable(true);
    }

    //Esta función y eliminarProveedor existían antes de implementar la base de datos correctamente, actualmente no tienen uso.
    public void eliminarCliente(ActionEvent event) {
    	int index = tableview_clientesList.getSelectionModel().getSelectedIndex();
    	tableview_clientesList.getItems().remove(index);
    }
  
    public void eliminarProveedor(ActionEvent event) {
    	int index = tableview_proveedoresList.getSelectionModel().getSelectedIndex();
    	tableview_proveedoresList.getItems().remove(index);
    }
    
    //Guarda una nueva categoría de empleado.
    public void pressed_guardarCategoriaEmp(ActionEvent event)
    {
    	if(!textfield_nombreCategoriaEmp.getText().isEmpty() && !textfield_salarioCategoriaEmp.getText().isEmpty())
    	{
    		String nombre = textfield_nombreCategoriaEmp.getText();
    		float salario = Float.parseFloat(textfield_salarioCategoriaEmp.getText());
		
    		if(radiobutton_PorDia.isSelected())
    		{
    			salario = salario/8;
    		}
		
    		CategoriaEmpleado cat = new CategoriaEmpleado(nombre, salario);
		
    		Controladora.getInstance().addCategoriaEmpleado(cat);
		
    		tablecolumn_NombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_SueldoCategoria.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
		
    		textfield_nombreCategoriaEmp.setText("");
    		textfield_salarioCategoriaEmp.setText("");
    		ObservableList<CategoriaEmpleado> data = FXCollections.observableArrayList();
    		data.add(cat);
    		tableview_CategoriaEmp.getItems().add(cat);
    		tableview_CategoriaEmp.refresh();	
    		
    		if(Controladora.getInstance().activarLoadEmpleados() && Controladora.getInstance().activarLoadInfoEmpresa() && Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador"))
    		{
    				button_gastos.setDisable(true);
    				button_productos.setDisable(true);
    				button_ventas.setDisable(true);
    				button_historial.setDisable(true);
    				button_admin.setDisable(false);
    				button_rh.setDisable(false);
    				button_help.setDisable(true); 
    				button_config.setDisable(false); 
      	        
    				tab_administracionGeneral.setDisable(true);
    				tab_administracionCajaChica.setDisable(true);
    				tab_administracionCuentaBanco.setDisable(true);
    				admin_pressed(null);
    				selectTabUsuarios();
    		}
    		else {
    		      	rh_pressed(null);
    		      	selectTabEmpleado();
    		      	//selectTabCategoriaEmpleado();
    		        button_gastos.setDisable(true);
    		        button_productos.setDisable(true);
    		        button_ventas.setDisable(true);
    		        button_historial.setDisable(true);
    		        button_admin.setDisable(true);
    		        button_rh.setDisable(false);
    		        button_help.setDisable(false); 
    		        button_config.setDisable(false); 
    		        
    		        tab_administracionGeneral.setDisable(true);
    		        tab_administracionCajaChica.setDisable(true);
    		        tab_administracionCuentaBanco.setDisable(true);
    		}
    	}
    }
    
    public void tableview_categoriaClicked(MouseEvent event) {
    	CategoriaEmpleado categoria = tableview_CategoriaEmp.getSelectionModel().getSelectedItem();
    	textfield_nombreCategoriaEmp.setText(categoria.getNombre());
    	textfield_nombreCategoriaEmp.setEditable(false);
    	textfield_salarioCategoriaEmp.setPromptText(Float.toString(categoria.getSueldo()));
    	button_editarCategoria.setDisable(false);
    }
    
    public void editarCategoria(ActionEvent event) {
    	CategoriaEmpleado categoria = tableview_CategoriaEmp.getSelectionModel().getSelectedItem();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar esta categoría (Productos y empleados se veran afectados por esta accion)?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	if(alert.getResult() == ButtonType.YES) {
    		if(!Controladora.getInstance().isCategoriaInProducto(categoria) && !Controladora.getInstance().isCategoryInEmpleado(categoria)) {
    			if(!textfield_nombreCategoriaEmp.getText().isEmpty() && !textfield_salarioCategoriaEmp.getText().isEmpty())
            	{
            		String nombre = textfield_nombreCategoriaEmp.getText();
            		float salario = Float.parseFloat(textfield_salarioCategoriaEmp.getText());
        		
            		if(radiobutton_PorDia.isSelected())
            		{
            			salario = salario/8;
            		}
            		if(salario != categoria.getSueldo()) {
            			int index = Controladora.getInstance().getMisCategoriasEmpleado().indexOf(categoria);
            			Controladora.getInstance().getMisCategoriasEmpleado().get(index).setBorrado(true);
            			Controladora.getInstance().borrarCategoriaEmpleado(index+1);
            		
            			CategoriaEmpleado cat = new CategoriaEmpleado(nombre, salario);
            			Controladora.getInstance().addCategoriaEmpleado(cat);
            		}
            		Alert success = new Alert(AlertType.CONFIRMATION, "Modificación realizada.");
            		success.showAndWait();
            		textfield_nombreCategoriaEmp.setText("");
            		textfield_nombreCategoriaEmp.setPromptText("");
            		textfield_nombreCategoriaEmp.setEditable(true);
            		textfield_salarioCategoriaEmp.setText("");
            		textfield_salarioCategoriaEmp.setPromptText("");
            		
            		tableview_CategoriaEmp.getSelectionModel().clearSelection();
            		fillCategoriaEmpleado();
            	}
    		} 		
    		else if(!textfield_nombreCategoriaEmp.getText().isEmpty() && !textfield_salarioCategoriaEmp.getText().isEmpty()){
    			String nombre = textfield_nombreCategoriaEmp.getText();
        		float salario = Float.parseFloat(textfield_salarioCategoriaEmp.getText());
    		
        		if(radiobutton_PorDia.isSelected())
        		{
        			salario = salario/8;
        		}
        		
        		if(salario != categoria.getSueldo()) {
        			CategoriaEmpleado cat = new CategoriaEmpleado(nombre, salario);
        			
        			ArrayList<Estandar> productosEstandar = Controladora.getInstance().getProductsEstandarWithCategory(categoria);
        			ArrayList<Servicio> productosServicio = Controladora.getInstance().getProductsServicioWithCategory(categoria);
        			ArrayList<Empleado> empleados = Controladora.getInstance().getEmpleadoWithCategory(categoria);
        			
        			int indexCategoria = Controladora.getInstance().getMisCategoriasEmpleado().indexOf(categoria);
        			
        			Controladora.getInstance().getMisCategoriasEmpleado().get(indexCategoria).setBorrado(true);
        			Controladora.getInstance().borrarCategoriaEmpleado(indexCategoria+1);
        		
        			Controladora.getInstance().addCategoriaEmpleado(cat);
        			int indexNewCategoria = Controladora.getInstance().getMisCategoriasEmpleado().indexOf(cat);
        			
        			for(Estandar estandar : productosEstandar) {
        				int index = Controladora.getInstance().getMisProductos().indexOf(estandar);
        				float costo = Controladora.getInstance().getMisProductos().get(index).getCosto();
    					float costoitbis = Controladora.getInstance().getMisProductos().get(index).getCostoitbis();
    					float precio = Controladora.getInstance().getMisProductos().get(index).getPrecio();
        				if(estandar.getTipoProducto().equalsIgnoreCase("Estandar")) {
        						
        						int indexEstandar = Controladora.getInstance().getMisProductosEstandar().indexOf(estandar);
        						
        						ManoDeObra mano = Controladora.getInstance().getMisProductosEstandar().get(indexEstandar).getInfoManoDeObra();
        						int indexManoObra = Controladora.getInstance().getMisManosDeObras().indexOf(mano);
        						
        						float oldCostoManoObra = mano.getCosto();
        						
        						//La nueva mano de obra
        						float newCostoManoObra = cat.getSueldo() * mano.getCantidadHoras();
        						Controladora.getInstance().getMisProductosEstandar().get(indexEstandar).setManodeobra(newCostoManoObra);
        						Controladora.getInstance().getMisProductosEstandar().get(indexEstandar).getInfoManoDeObra().setCosto(newCostoManoObra);
        						Controladora.getInstance().getMisProductosEstandar().get(indexEstandar).getInfoManoDeObra().setCategoria(cat);
        						Controladora.getInstance().editarEstandarCostoManoObra(indexEstandar, newCostoManoObra);
        						Controladora.getInstance().editarManoDeObraCosto(indexManoObra+1, newCostoManoObra);
        						Controladora.getInstance().editarCategoriaManoObraEstandar(indexNewCategoria+1, indexManoObra+1);
        						
        						//El nuevo costo
        						float newCosto = costo + (newCostoManoObra-oldCostoManoObra);
        						
        						Controladora.getInstance().getMisManosDeObras().get(indexManoObra).setCosto(newCostoManoObra);
        						Controladora.getInstance().getMisProductos().get(index).setCosto(newCosto);
        						Controladora.getInstance().editarProductoCosto(index+1, newCosto);
        						
        						double porcientoGanancia = ((precio - costo - (costo*0.18)) / costo) * 100;
        						System.out.println("El precio es: " + precio);
        						System.out.println("El costo es: " + costo);
        						System.out.println("El costoitbis es: " + costoitbis);
        						System.out.println("El porciento de ganancia es: " + porcientoGanancia);
        						
        						//El nuevo precio
        						double nuevoPrecio = Controladora.getInstance().calcularPrecio(newCosto, porcientoGanancia, 18);
        						Controladora.getInstance().getMisProductos().get(index).getPrecioClass().setPrecio((float) nuevoPrecio);
        						int indexPrecio = Controladora.getInstance().getMisPrecios().indexOf(Controladora.getInstance().getMisProductos().get(index).getPrecioClass());
        						Controladora.getInstance().editarPrecio(indexPrecio+1, (float) nuevoPrecio);
        						
        						
        				}
        				else if(estandar.getTipoProducto().equalsIgnoreCase("Matriz")) {
        					int indexMatrizEstandar = Controladora.getInstance().getMisProductosEstandar().indexOf(estandar);
    						int indexMatriz = Controladora.getInstance().getMisProductosMatriz().indexOf(estandar);
    						
    						
    						ManoDeObra mano = Controladora.getInstance().getMisProductosMatriz().get(indexMatriz).getInfoManoDeObra();
    						int indexManoObra = Controladora.getInstance().getMisManosDeObras().indexOf(mano);
    						
    						float oldCostoManoObra = mano.getCosto();
    						
    						//La nueva mano de obra
    						float newCostoManoObra = cat.getSueldo() * mano.getCantidadHoras();
    						Controladora.getInstance().getMisProductosMatriz().get(indexMatriz).setManodeobra(newCostoManoObra);
    						Controladora.getInstance().getMisProductosMatriz().get(indexMatriz).getInfoManoDeObra().setCosto(newCostoManoObra);
    						Controladora.getInstance().getMisProductosMatriz().get(indexMatriz).getInfoManoDeObra().setCategoria(cat);
    						Controladora.getInstance().editarEstandarCostoManoObra(indexMatrizEstandar, newCostoManoObra);
    						Controladora.getInstance().editarManoDeObraCosto(indexManoObra+1, newCostoManoObra);
    						Controladora.getInstance().editarCategoriaManoObraEstandar(indexNewCategoria+1, indexManoObra+1);
    						
    						//El nuevo costo
    						float newCosto = costo + (newCostoManoObra-oldCostoManoObra);
    						
    						Controladora.getInstance().getMisManosDeObras().get(indexManoObra).setCosto(newCostoManoObra);
    						Controladora.getInstance().getMisProductos().get(index).setCosto(newCosto);
    						Controladora.getInstance().editarProductoCosto(index+1, newCosto);
    						
    						double porcientoGanancia = ((precio - costo - (costo*0.18)) / costo) * 100;
    						
    						//El nuevo precio
    						double nuevoPrecio = Controladora.getInstance().calcularPrecio(newCosto, porcientoGanancia, 18);
    						Controladora.getInstance().getMisProductos().get(index).getPrecioClass().setPrecio((float) nuevoPrecio);
    						int indexPrecio = Controladora.getInstance().getMisPrecios().indexOf(Controladora.getInstance().getMisProductos().get(index).getPrecioClass());
    						Controladora.getInstance().editarPrecio(indexPrecio+1, (float) nuevoPrecio);
        				}
        			}
        			for(Servicio servicio : productosServicio) {
        				int index = Controladora.getInstance().getMisProductos().indexOf(servicio);
    					int indexServicio = Controladora.getInstance().getMisProductosServicio().indexOf(servicio);
    					
    					float costo = Controladora.getInstance().getMisProductos().get(index).getCosto();
    					float costoitbis = Controladora.getInstance().getMisProductos().get(index).getCostoitbis();
    					float precio = Controladora.getInstance().getMisProductos().get(index).getPrecio();
    					
    					
    					ManoDeObra mano = Controladora.getInstance().getMisProductosServicio().get(indexServicio).getInfoManoDeObra();
    					int indexManoObra = Controladora.getInstance().getMisManosDeObras().indexOf(mano);
    					
    					float oldCostoManoObra = mano.getCosto();
    					
    					//La nueva mano de obra
    					float newCostoManoObra = cat.getSueldo() * mano.getCantidadHoras();
    					Controladora.getInstance().getMisProductosServicio().get(indexServicio).setManodeobra(newCostoManoObra);
    					Controladora.getInstance().getMisProductosServicio().get(indexServicio).getInfoManoDeObra().setCosto(newCostoManoObra);
    					Controladora.getInstance().getMisProductosServicio().get(indexServicio).getInfoManoDeObra().setCategoria(cat);
    					//Controladora.getInstance().editarEstandarCostoManoObra(indexMatrizEstandar, newCostoManoObra);
    					Controladora.getInstance().editarManoDeObraCosto(indexManoObra+1, newCostoManoObra);
    					Controladora.getInstance().editarCategoriaManoObraServicio(indexNewCategoria+1, indexManoObra+1);
    					
    					
    					//El nuevo costo
    					float newCosto = costo + (newCostoManoObra-oldCostoManoObra);
    					
    					Controladora.getInstance().getMisManosDeObras().get(indexManoObra).setCosto(newCostoManoObra);
    					Controladora.getInstance().getMisProductos().get(index).setCosto(newCosto);
    					Controladora.getInstance().editarProductoCosto(index+1, newCosto);
    					
    					double porcientoGanancia = ((precio - costo - (costo*0.18)) / costo) * 100;
    					
    					//El nuevo precio
    					double nuevoPrecio = Controladora.getInstance().calcularPrecio(newCosto, porcientoGanancia, 18);
    					Controladora.getInstance().getMisProductos().get(index).getPrecioClass().setPrecio((float) nuevoPrecio);
    					int indexPrecio = Controladora.getInstance().getMisPrecios().indexOf(Controladora.getInstance().getMisProductos().get(index).getPrecioClass());
    					Controladora.getInstance().editarPrecio(indexPrecio+1, (float) nuevoPrecio);
        			}
        			
        			for(Empleado empleado : empleados) {
        				int indexEmpleado = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
        				Controladora.getInstance().getMisEmpleados().get(indexEmpleado).setCategoria(cat);
        				Controladora.getInstance().getMisEmpleados().get(indexEmpleado).setSueldo(cat.getSueldo());
        				Controladora.getInstance().editarSueldoCategoriaEmpleado(indexEmpleado+1, indexNewCategoria+1, Controladora.getInstance().getMisEmpleados().get(indexEmpleado).getSueldo());
        			}
        			
        		}
        		Alert success = new Alert(AlertType.CONFIRMATION, "Modificación realizada.");
        		success.showAndWait();
        		textfield_nombreCategoriaEmp.setText("");
        		textfield_nombreCategoriaEmp.setPromptText("");
        		textfield_nombreCategoriaEmp.setEditable(true);
        		textfield_salarioCategoriaEmp.setText("");
        		textfield_salarioCategoriaEmp.setPromptText("");
        		
        		tableview_CategoriaEmp.getSelectionModel().clearSelection();
        		fillCategoriaEmpleado();
        		fillProductList(null, "");
    		}
    		
    	}
    }
    
    //Rellena los datos de la empresa (puede usar refinamiento).
    public void fillEmpresa()
    {
    	if(Controladora.getInstance().getMiEmpresa() != null)
    	{
    		Empresa emp  = Controladora.getInstance().getMiEmpresa();
    		textfield_empresaNombre.setText(emp.getNombre());
    		textfield_empresaRNC.setText(emp.getRnc());
    		textarea_empresaDomicilio.setText(emp.getDomicilio());
    		textfield_empresaTelefono.setText(emp.getTelefono());
    		spinner_empresaValorFiscalMin.getValueFactory().setValue(emp.getValorFiscalInferior());
    		spinner_empresaValorFiscalMax.getValueFactory().setValue(emp.getValorFiscalMayor());
    		datepicker_empresaFechaSolicitada.setValue(emp.getFechaSecSolicitada());
    		datepicker_empresaFechaVencimiento.setValue(emp.getFechasecvencimiento());
    		datepicker_empresaFechaInicio.setValue(emp.getFechaInicio());
    		datepicker_empresaFechaFinal.setValue(emp.getFechaFinal());
    		textfield_cajaMaximo.setText("" + emp.getCajaMaximo());
    	}
    }
    
    //Guarda los datos de la empresa (puede usar refinamiento).
    public void guardarEmpresa(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "¿Desea actualizar la información de su empresa?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();
    	if(alert.getResult() == ButtonType.YES) {
	    	String nombre = "";
	    	String rnc = "";
	    	String telefono = "";
	    	String domicilio = "";
	    	int valorFiscalInferior = spinner_empresaValorFiscalMin.getValue();
	    	int valorFiscalMayor = spinner_empresaValorFiscalMax.getValue();
	    	LocalDate fechaSolicitada = null;
	    	LocalDate fechaVencimiento = null;
	    	LocalDate fechaInicio = null;
	    	LocalDate fechaFinal = null;
	    	float cajaMaximo = 0;
	    	try {
	    		nombre = textfield_empresaNombre.getText();
	    		rnc = textfield_empresaRNC.getText();
	    		domicilio = textarea_empresaDomicilio.getText();
	    		telefono = textfield_empresaTelefono.getText();
	    		fechaSolicitada = datepicker_empresaFechaSolicitada.getValue();
	    		fechaVencimiento = datepicker_empresaFechaVencimiento.getValue();
	    		fechaInicio = datepicker_empresaFechaInicio.getValue();
	    		fechaFinal = datepicker_empresaFechaFinal.getValue();
	    		cajaMaximo = Float.parseFloat(textfield_cajaMaximo.getText());
	    		if(nombre != null)
	    		{
	    			Controladora.getInstance().actualizarProveedorPorDefecto(nombre);
	    		}
	    	}catch(NullPointerException e) {
	    		
	    	}
	    	
	    	Empresa empresa = new Empresa(nombre, rnc, telefono, domicilio, valorFiscalInferior, valorFiscalMayor, fechaSolicitada, fechaVencimiento, fechaInicio, fechaFinal, cajaMaximo);
	    	Controladora.getInstance().setMiEmpresa(empresa);
	    	
	    	Controladora.getInstance().guardarInfoEmpresaSQL(empresa);
	    	Controladora.getInstance().guardarAnioFiscal(empresa);
	    	Controladora.getInstance().guardarRangoNumerosValorFiscal(empresa);
	    	
	    	text_negocioName.setText(nombre);
	    	
	    	alert = new Alert(AlertType.INFORMATION, "Operación satisfactoria");
    		alert.setTitle("Informaciones");
    		alert.showAndWait();
    		
    		
    		if(Controladora.getInstance().activarLoadEmpleados() && Controladora.getInstance().activarLoadInfoEmpresa() && Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador"))
    		{
    				button_gastos.setDisable(true);
    				button_productos.setDisable(true);
    				button_ventas.setDisable(true);
    				button_historial.setDisable(true);
    				button_admin.setDisable(false);
    				button_rh.setDisable(false);
    				button_help.setDisable(true); 
    				button_config.setDisable(false); 
      	        
    				tab_administracionGeneral.setDisable(true);
    				tab_administracionCajaChica.setDisable(true);
    				tab_administracionCuentaBanco.setDisable(true);
    				admin_pressed(null);
    				selectTabUsuarios();
    		}
    		else {
    		      	rh_pressed(null);
    		      	//selectTabEmpleado();
    		      	selectTabCategoriaEmpleado();
    		        button_gastos.setDisable(true);
    		        button_productos.setDisable(true);
    		        button_ventas.setDisable(true);
    		        button_historial.setDisable(true);
    		        button_admin.setDisable(true);
    		        button_rh.setDisable(false);
    		        button_help.setDisable(false); 
    		        button_config.setDisable(false); 
    		        
    		        tab_administracionGeneral.setDisable(true);
    		        tab_administracionCajaChica.setDisable(true);
    		        tab_administracionCuentaBanco.setDisable(true);
    		}
    	}
    	/*textfield_empresaNombre.setText("");
    	textfield_empresaRNC.setText("");
    	textfield_empresaTelefono.setText("");
    	textarea_empresaDomicilio.setText("");
    	spinner_empresaValorFiscalMin.getValueFactory().setValue(0);
    	spinner_empresaValorFiscalMax.getValueFactory().setValue(0);
    	datepicker_empresaFechaInicio.setValue(null);
    	datepicker_empresaFechaFinal.setValue(null);*/
    	
   
    }
    
    /**FUNCIONES DE LAS PETICIONES**/
    
    //Selecciona un producto para las peticiones.
    public void tableview_peticionProductosClicked(MouseEvent event) {
    	Producto producto = tableview_peticionProducto.getSelectionModel().getSelectedItem();
    	textfield_peticionProductoSeleccionado.setText(producto.getNombre());
    	button_peticionProductoSeleccionar.setDisable(false);
    }
    
    //Guarda la selección del producto para la petición.
    public void peticionSelectProducto(ActionEvent event) {
    	textfield_peticionProducto.setText(textfield_peticionProductoSeleccionado.getText());
    	//combobox_peticionProveedor.setDisable(false);
    	spinner_peticionCantidad.setDisable(false);
    	textfield_peticionMonto.setDisable(false);
    	combobox_peticionEstado.setDisable(false);
    	radiobutton_peticionEfectivo.setDisable(false);
    	radiobutton_peticionCredito.setDisable(false);
    	radiobutton_peticionTarjeta.setDisable(false);
    	cerrar_titledpane_busquedaProductosPeticiones(event);
    }
    
    //Selecciona un proveedor para las peticiones.
    public void tableview_peticionProveedorClicked(MouseEvent event) {
    	Proveedores proveedor = tableview_peticionProveedoresList.getSelectionModel().getSelectedItem();
    	textfield_peticionProveedorSeleccionado.setText(proveedor.getCodigo());
    	button_peticionProveedorSeleccionar.setDisable(false);
    }
    
    //Guarda la selección del proveedor para la petición.
    public void peticionSelectProveedor(ActionEvent event) {
    	textfield_peticionProveedor.setText(textfield_peticionProveedorSeleccionado.getText());
    	cerrar_infoBusquedaProveedoresPeticiones(event);
    }
    
    //Determina el tipo de pago que se hará a la petición (efectivo, crédito, tarjeta (no implementado)).
    public void peticionTipoDePago(ActionEvent event) {
    	if(event.getSource().equals(radiobutton_peticionEfectivo)) {
    		radiobutton_peticionCredito.setSelected(false);
    		radiobutton_peticionTarjeta.setSelected(false);
    	}
    	else if(event.getSource().equals(radiobutton_peticionCredito)) {
    		radiobutton_peticionEfectivo.setSelected(false);
    		radiobutton_peticionTarjeta.setSelected(false);
    	}
    	else if(event.getSource().equals(radiobutton_peticionTarjeta)) {
    		radiobutton_peticionEfectivo.setSelected(false);
    		radiobutton_peticionCredito.setSelected(false);
    	}
    }
    
    //Calcula el precio a pagar tomando en cuenta la cantidad del producto que se pide
    public void peticionCalcularMonto(MouseEvent event) {
    	Producto producto = Controladora.getInstance().buscarProducto(textfield_peticionProducto.getText());
    	textfield_peticionMonto.setText(Float.toString(producto.getCosto()*spinner_peticionCantidad.getValue()));
    }
    
    //Activa el botón para poder administrar una petición.
    public void activarAdministrarPeticion(MouseEvent event) {
    	if(!tableview_peticionList.getSelectionModel().isEmpty()) {
    		button_peticionAdministrar.setDisable(false);
    	}
    }
    
    //Abre la administración de una petición para determinar su estado (Aceptada o pendiente).
    public void openAdministrarPeticion(ActionEvent event) {
    	Alert a = new Alert(AlertType.WARNING);
    	Peticion peticion = tableview_peticionList.getSelectionModel().getSelectedItem();
    	if(peticion.getEstado().equalsIgnoreCase("Aceptada")) {
    		a.setContentText("Esta peticion ya ha sido aceptada");
    		a.show();
    	}
    	else if(peticion.getEstado().equalsIgnoreCase("Declinada")) {
    		a.setContentText("Esta peticion ya ha sido declinada");
    		a.show();
    	}
    	else {
    		textfield_peticionProducto.setText(peticion.getProductoNombre());
    		textfield_peticionProveedor.setText(peticion.getProveedorCodigo());
    		spinner_peticionCantidad.getValueFactory().setValue(peticion.getCantidad());
    		textfield_peticionMonto.setText(Float.toString(peticion.getMonto()));
    		combobox_peticionEstado.setValue(peticion.getEstado());
    	
    		radiobutton_peticionEfectivo.setDisable(false);
    		radiobutton_peticionCredito.setDisable(false);
    		radiobutton_peticionTarjeta.setDisable(false);
    		combobox_peticionEstado.setDisable(false);
    	
    		button_peticionBuscarProducto.setDisable(true);
    		button_peticionBuscarProveedor.setDisable(true);
    		administrarPeticion = true;
    	
    		abrir_titledpaneNuevaPeticion(event);
    	}
    	
    }
    
    //Guarda una nueva petición.
    public void guardarPeticion(ActionEvent event) {
    	boolean canRegister = true;
    	Producto producto = Controladora.getInstance().buscarProducto(textfield_peticionProducto.getText());
    	Alert a = new Alert(AlertType.WARNING);
    	
    	//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validación de los parámetros, si se te es posible estandarizarlo, recomendamos hacerlo.
    	if(textfield_peticionProveedor.getText().equalsIgnoreCase("")) {
    		a.setContentText("Falta ingresar los datos del proveedor");
    		a.show();
    		canRegister = false;
    	}
    	if(producto.getTipoProducto().equalsIgnoreCase("Estandar") || producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		Estandar estandar = (Estandar) producto;
    		float cantidadMaxima = estandar.getExistenciaMaxima() - estandar.getExistenciaActual();
    		if(spinner_peticionCantidad.getValue() > cantidadMaxima) {
    			a.setContentText("Al cumplirse esta peticion, se estaria sobrepasando la existencia maxima del producto");
    			a.show();
    			canRegister = false;
    		}
    	}
    	if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    		Kit kit = (Kit) producto;
    		float cantidadMaxima = kit.getExistenciaMaxima() - kit.getExistenciaActual();
    		if(spinner_peticionCantidad.getValue() > cantidadMaxima) {
    			a.setContentText("Al cumplirse esta peticion, se estaria sobrepasando la existencia maxima del producto");
    			a.show();
    			canRegister = false;
    		}
    	}

    	
    	
    	if(canRegister) {
    		String metodoPago = "";
    		if(administrarPeticion) {
    			if(radiobutton_peticionEfectivo.isSelected()) {
            		metodoPago = "Efectivo";
            	}
            	else if(radiobutton_peticionCredito.isSelected()) {
            		metodoPago = "Credito";
            	}
            	else if(radiobutton_peticionTarjeta.isSelected()) {
            		metodoPago = "Tarjeta";
            	}
    			if(combobox_peticionEstado.getValue().equalsIgnoreCase("Aceptada")) {
    				Producto productoSuma = tableview_peticionList.getSelectionModel().getSelectedItem().getProducto();
    				//int indiceProducto = Controladora.getInstance().getMisProductos().indexOf(productoSuma);
    				if(productoSuma.getTipoProducto().equalsIgnoreCase("Estandar") || productoSuma.getTipoProducto().equalsIgnoreCase("Matriz")) {
    					Estandar productoSumaEstandar = (Estandar) productoSuma;
    					int indiceProductoEstandar = Controladora.getInstance().getMisProductosEstandar().indexOf(productoSumaEstandar);
    					Controladora.getInstance().getMisProductosEstandar().get(indiceProductoEstandar).setExistenciaActual(
    							Controladora.getInstance().getMisProductosEstandar().get(indiceProductoEstandar).getExistenciaActual() + spinner_peticionCantidad.getValue());
    					Controladora.getInstance().sumarExistenciaActual(
    							Controladora.getInstance().getMisProductosEstandar().get(indiceProductoEstandar).getExistenciaActual(), indiceProductoEstandar+1);
    				}
    				else if(productoSuma.getTipoProducto().equalsIgnoreCase("Kit")) {
    					Kit productoSumakit = (Kit) productoSuma;
    					int indiceProductoKit = Controladora.getInstance().getMisProductosKit().indexOf(productoSumakit);
    					Controladora.getInstance().getMisProductosKit().get(indiceProductoKit).setExistenciaActual(
    						Controladora.getInstance().getMisProductosKit().get(indiceProductoKit).getExistenciaActual() + spinner_peticionCantidad.getValue());
    					Controladora.getInstance().sumarExistenciaActualKit(
    							Controladora.getInstance().getMisProductosKit().get(indiceProductoKit).getExistenciaActual(), indiceProductoKit);
    				}
    				Peticion peticionModificar = tableview_peticionList.getSelectionModel().getSelectedItem();
    				int indicePeticion = Controladora.getInstance().getMisPeticiones().indexOf(peticionModificar);
    				Controladora.getInstance().getMisPeticiones().get(indicePeticion).setEstado("Aceptada");
    				Controladora.getInstance().getMisPeticiones().get(indicePeticion).setMetodoPago(metodoPago);
    				Controladora.getInstance().modificarEstadoPeticion("Aceptada", metodoPago, peticionModificar.getCodigo());
    				
    				Proveedores proveedor = peticionModificar.getProveedor();
    				int indiceProveedor = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
    				Controladora.getInstance().getMisProveedores().get(indiceProveedor).setSaldo(
    						Controladora.getInstance().getMisProveedores().get(indiceProveedor).getSaldo() + peticionModificar.getMonto());
    				Controladora.getInstance().modificarProveedorSaldo(Controladora.getInstance().getMisProveedores().get(indiceProveedor).getSaldo(), indiceProveedor+1);
    			}
    			else if(combobox_peticionEstado.getValue().equalsIgnoreCase("Rechazada")) {
    				Alert alert = new Alert(AlertType.CONFIRMATION, "¿Desea declinar esta petición?", ButtonType.YES, ButtonType.NO);
    		    	alert.showAndWait();
    		    	if(alert.getResult() == ButtonType.YES) {
    		    		Peticion peticionModificar = tableview_peticionList.getSelectionModel().getSelectedItem();
        				int indicePeticion = Controladora.getInstance().getMisPeticiones().indexOf(peticionModificar);
        				Controladora.getInstance().getMisPeticiones().get(indicePeticion).setEstado("Declinada");
        				Controladora.getInstance().getMisPeticiones().get(indicePeticion).setMetodoPago(metodoPago);
        				Controladora.getInstance().modificarEstadoPeticion("Declinada", metodoPago, peticionModificar.getCodigo());
    		    	}
    			}
    			fillProductList(null, "");
    			fillProveedorList(null, "");
    			fillProveedorList(null, "Peticion");
    			cerrar_titledpaneNuevaPeticion(event);
    			administrarPeticion = false;
    			
    		}
    		else {
    			String codigo = "00000" + Controladora.getInstance().getMisPeticiones().size()+1;
            	Proveedores proveedor = Controladora.getInstance().buscarProveedorCodigo(textfield_peticionProveedor.getText());
            	
            	int cantidad = spinner_peticionCantidad.getValue();
            	float monto = Float.parseFloat(textfield_peticionMonto.getText());
            	
            	if(radiobutton_peticionEfectivo.isSelected()) {
            		metodoPago = "Efectivo";
            	}
            	else if(radiobutton_peticionCredito.isSelected()) {
            		metodoPago = "Credito";
            	}
            	else if(radiobutton_peticionTarjeta.isSelected()) {
            		metodoPago = "Tarjeta";
            	}
            	String estado = combobox_peticionEstado.getValue();
            	LocalDate fecha = LocalDate.now();
            	Peticion peticion = new Peticion(codigo, proveedor, producto, cantidad, monto, metodoPago, estado, fecha);
            	Controladora.getInstance().getMisPeticiones().add(peticion);
            	Controladora.getInstance().guardarPeticionSQL(peticion);
            	if(radiobutton_peticionCredito.isSelected()) {
            		peticion.setAdeudado(monto);
            		Controladora.getInstance().guardarPeticionesCreditoSQL(peticion);
            	}
            	
            	textfield_peticionProducto.setText("");
            	textfield_peticionProveedor.setText("");;
            	spinner_peticionCantidad.getValueFactory().setValue(0);
            	textfield_peticionMonto.setText("");
            	combobox_peticionEstado.getSelectionModel().clearSelection();
            	radiobutton_peticionEfectivo.setSelected(true);
            	radiobutton_peticionCredito.setSelected(false);
            	radiobutton_peticionTarjeta.setSelected(false);
            	
            	textfield_peticionProveedor.setDisable(true);
            	spinner_peticionCantidad.setDisable(true);
            	textfield_peticionMonto.setDisable(true);
            	combobox_peticionEstado.setDisable(true);
            	radiobutton_peticionEfectivo.setDisable(true);
            	radiobutton_peticionCredito.setDisable(true);
            	radiobutton_peticionTarjeta.setDisable(true);
    		}        	
        	fillPeticion();
    	}
    }

    //Guarda un nuevo usuario.
    public void guardarUsuario(ActionEvent event) {
    	Alert a = new Alert(AlertType.WARNING);
    	boolean canRegister = true;
    	
    	String usuarioNombre = "";
    	String codigoEmpleado = "";
    	String cargoNombre = "";
    	String password = "";
    	try {
    		usuarioNombre = textfield_usuario.getText();
    		codigoEmpleado = textfield_empleadoUsuario.getText();
    		cargoNombre = combobox_cargoUsuario.getValue();
    		password = textfield_passwordUsuario.getText();
    	}catch(NullPointerException e) {
    		
    	}
    	
    	//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validación de los parámetros, si se te es posible estandarizarlo, recomendamos hacerlo.
    	if(usuarioNombre.equalsIgnoreCase("")) {
    		a.setContentText("Seleccione el nombre del usuario");
    		a.show();
    		canRegister = false;
    	}
    	else if(codigoEmpleado.equalsIgnoreCase("")) {
    		a.setContentText("Seleccione el código del empleado correspondiente al usuario");
    		a.show();
    		canRegister = false;
    	}
    	else if(cargoNombre.equalsIgnoreCase("Seleccione")) {
    		a.setContentText("Seleccione el cargo correspondiente al usuario");
    		a.show();
    		canRegister = false;
    	}
    	else if(password.equalsIgnoreCase("")) {
    		a.setContentText("Seleccione la contraseña correspondiente al usuario");
    		a.show();
    		canRegister = false;
    	}
    	
    	if(canRegister) {
    		Alert alert = new Alert(AlertType.CONFIRMATION, "Confirmar creación del usuario" + " " + textfield_usuario.getText(), ButtonType.YES, ButtonType.NO);
        	alert.showAndWait();
        	
        	if (alert.getResult() == ButtonType.YES) {
        		Empleado empleado = Controladora.getInstance().buscarEmpleado(codigoEmpleado);
        		Cargo cargo = Controladora.getInstance().buscarCargo(cargoNombre);
        		String passhash = DigestUtils.md5Hex(password);
        		Usuario usuario = new Usuario(usuarioNombre, empleado, true, passhash, true, cargo);
        	
        		Controladora.getInstance().getMisUsuarios().add(usuario);
        		Controladora.getInstance().guardarUsuarioSQL(usuario);
        		
        		combobox_cargoUsuario.setValue("Seleccione");
        		textfield_usuario.setText("");
        		textfield_empleadoUsuario.setText("");
        		textfield_passwordUsuario.setText("");
        		
        		pane_nuevoUsuario.setVisible(false);
        		fillEmpleadoList(null, "Usuario");
        		fillUsuario(null);
        		
        		alert = new Alert(AlertType.INFORMATION, "Operación satisfactoria");
        		alert.setTitle("Informaciones");
        		alert.showAndWait();
        	}
    	}
    	
    }
    
    //Guarda la caja chica o cualquier transacción que se haga en ella.
    public void guardarCajaChica(ActionEvent event) {
    	float monto = 0;
    	String descripcion = "";
    	boolean canRegister = true;
    	Alert warning = new Alert(AlertType.WARNING, "Ingrese todos los datos");
    	Alert success = new Alert(AlertType.INFORMATION, "Los datos han sido guardados exitosamente.");
    	Alert confirmation = new Alert(AlertType.CONFIRMATION, "Esta seguro que desea realizar esta operación?", ButtonType.YES, ButtonType.NO);
    	confirmation.showAndWait();
    	if(confirmation.getResult() == ButtonType.YES) {
    		if(event.getSource().equals(button_cajaGuardarAdd)) {
    			Alert limitWarning = new Alert(AlertType.WARNING, "Sobrepasa el límite de caja");
    			float montoMaximo = 0;
    			if(Controladora.getInstance().getMiEmpresa() != null) {
    				montoMaximo = Controladora.getInstance().getMiEmpresa().getCajaMaximo();
    			}
        		if(textfield_cajaMontoAdd.getText().equalsIgnoreCase("") || textarea_cajaDescripcionAdd.getText().equalsIgnoreCase("")) {
        			warning.showAndWait();
        			canRegister = false;
        		}
        		else if(Float.parseFloat(textfield_cajaMontoAdd.getText()) > montoMaximo) {
        			limitWarning.showAndWait();
        			canRegister = false;
        		}
        		if(canRegister) {
        			monto = Float.parseFloat(textfield_cajaMontoAdd.getText());
        			descripcion = textarea_cajaDescripcionAdd.getText();
        			if(Controladora.getInstance().getMiCajaChica() == null) {
        				CajaChica cajaChica = new CajaChica(0);
        				Controladora.getInstance().setCajaChica(cajaChica);
        				Controladora.getInstance().guardarCajaChicaSQL(Controladora.getInstance().getMiCajaChica());
        			}
        			TransaccionesCajaChica transaccion = new TransaccionesCajaChica(monto, descripcion, Controladora.getInstance().getUsuarioLogueado(), LocalDate.now());
        			Controladora.getInstance().getMiCajaChica().getTransacciones().add(transaccion);
        		    float montoActual = Controladora.getInstance().getMiCajaChica().getMontoActual();
        			Controladora.getInstance().getMiCajaChica().setMontoActual(montoActual + monto);
        			Controladora.getInstance().guardarTransaccionCaja(transaccion);
        		}
        	}
        	else if(event.getSource().equals(button_cajaGuardarRemove)) {
        		Alert warningMoney = new Alert(AlertType.WARNING, "Se está excediendo del presupuesto de la caja.");
        		if(textfield_cajaMontoRemove.getText().equalsIgnoreCase("") || textarea_cajaDescripcionRemove.getText().equalsIgnoreCase("")) {
        			warning.showAndWait();
        			canRegister = false;
        		}
        		else if(Float.parseFloat(textfield_cajaMontoRemove.getText()) > Float.parseFloat(textfield_cajaFondoActual.getText())) {
        			warningMoney.showAndWait();
        			canRegister = false;
        		}
        		if(canRegister) {
        			monto = Float.parseFloat(textfield_cajaMontoRemove.getText());
        			descripcion = textarea_cajaDescripcionRemove.getText();
        			if(Controladora.getInstance().getMiCajaChica() == null) {
        				CajaChica cajaChica = new CajaChica(0);
        				Controladora.getInstance().setCajaChica(cajaChica);
        				Controladora.getInstance().guardarCajaChicaSQL(Controladora.getInstance().getMiCajaChica());
        			}
        			TransaccionesCajaChica transaccion = new TransaccionesCajaChica(monto*-1, descripcion, Controladora.getInstance().getUsuarioLogueado(), LocalDate.now());
        			Controladora.getInstance().getMiCajaChica().getTransacciones().add(transaccion);
        			float montoActual = Controladora.getInstance().getMiCajaChica().getMontoActual();
        			Controladora.getInstance().getMiCajaChica().setMontoActual(montoActual - monto);
        			Controladora.getInstance().guardarTransaccionCaja(transaccion);

        		}
        	}
        	if(canRegister) {
        		success.showAndWait();
        		textfield_cajaMontoRemove.setText("");
        		textarea_cajaDescripcionRemove.setText("");
        		textfield_cajaMontoAdd.setText("");
        		textarea_cajaDescripcionAdd.setText("");
    		
        		textfield_cajaFondoActual.setText(Float.toString(Controladora.getInstance().getMiCajaChica().getMontoActual()));
        		fillCajaTransacciones();
        	}
    	}
    }
    
    //Guarda la cuenta de banco o cualquier transacción que se haga en ella (puede ser refinado).
    public void guardarCuentaBanco(ActionEvent event) {
    	float monto = 0;
    	String descripcion = "";
    	boolean canRegister = true;
    	Alert warning = new Alert(AlertType.WARNING, "Ingrese todos los datos.");
    	Alert success = new Alert(AlertType.INFORMATION, "Los datos han sido guardados exitosamente.");
    	Alert confirmation = new Alert(AlertType.CONFIRMATION, "Esta seguro que desea realizar esta operación?", ButtonType.YES, ButtonType.NO);
    	confirmation.showAndWait();
    	if(confirmation.getResult() == ButtonType.YES) {
    		if(event.getSource().equals(button_cuentaGuardarAdd)) {
        		if(textfield_cuentaMontoAdd.getText().equalsIgnoreCase("") || textarea_cuentaDescripcionAdd.getText().equalsIgnoreCase("")) {
        			warning.showAndWait();
        			canRegister = false;
        		}
        		if(canRegister) {
        			monto = Float.parseFloat(textfield_cuentaMontoAdd.getText());
        			descripcion = textarea_cuentaDescripcionAdd.getText();
        			if(Controladora.getInstance().getMiCuentaBanco() == null) {
        				CuentaBanco cuentaBanco = new CuentaBanco(0);
        				Controladora.getInstance().setCuentaBanco(cuentaBanco);
        				Controladora.getInstance().guardarCuentaBancoSQL(Controladora.getInstance().getMiCuentaBanco());
        			}
        			TransaccionesCuentaBanco transaccion = new TransaccionesCuentaBanco(monto, descripcion, LocalDate.now());
        			Controladora.getInstance().getMiCuentaBanco().getTransacciones().add(transaccion);
        		    float montoActual = Controladora.getInstance().getMiCuentaBanco().getMontoActual();
        			Controladora.getInstance().getMiCuentaBanco().setMontoActual(montoActual + monto);
        			Controladora.getInstance().guardarTransaccionCuenta(transaccion);
        		}
        	}
        	else if(event.getSource().equals(button_cuentaGuardarRemove)) {
        		Alert warningMoney = new Alert(AlertType.WARNING, "Se está excediendo del presupuesto de la caja.");
        		if(textfield_cuentaMontoRemove.getText().equalsIgnoreCase("") || textarea_cuentaDescripcionRemove.getText().equalsIgnoreCase("")) {
        			warning.showAndWait();
        			canRegister = false;
        		}
        		else if(Float.parseFloat(textfield_cuentaMontoRemove.getText()) > Float.parseFloat(textfield_cuentaFondoActual.getText())) {
        			warningMoney.showAndWait();
        			canRegister = false;
        		}
        		if(canRegister) {
        			monto = Float.parseFloat(textfield_cuentaMontoRemove.getText());
        			descripcion = textarea_cuentaDescripcionRemove.getText();
        			if(Controladora.getInstance().getMiCuentaBanco() == null) {
        				CuentaBanco cuentaBanco = new CuentaBanco(0);
        				Controladora.getInstance().setCuentaBanco(cuentaBanco);
        				Controladora.getInstance().guardarCuentaBancoSQL(Controladora.getInstance().getMiCuentaBanco());
        			}
        			TransaccionesCuentaBanco transaccion = new TransaccionesCuentaBanco(monto*-1, descripcion, LocalDate.now());
        			Controladora.getInstance().getMiCuentaBanco().getTransacciones().add(transaccion);
        			float montoActual = Controladora.getInstance().getMiCuentaBanco().getMontoActual();
        			Controladora.getInstance().getMiCuentaBanco().setMontoActual(montoActual - monto);
        			Controladora.getInstance().guardarTransaccionCuenta(transaccion);
        		}
        	}
        	if(canRegister) {
        		success.showAndWait();
        		textfield_cuentaMontoRemove.setText("");
        		textarea_cuentaDescripcionRemove.setText("");
        		textfield_cuentaMontoAdd.setText("");
        		textarea_cuentaDescripcionAdd.setText("");
    		
        		textfield_cuentaFondoActual.setText(Float.toString(Controladora.getInstance().getMiCuentaBanco().getMontoActual()));
        		fillCuentaTransacciones();
        	}
    	}	
    }

    //Determina los permisos del usuario logeado.
	public void verifyUserPermissions()
	{
		Usuario user = Controladora.getInstance().getUsuarioLogueado();
		System.out.println(user.getUsuario());
		//root es el usuario por defecto del programa, no esta guardado en la base de datos,
    	//algunas situaciones han sido validadas, pero recomendamos tener cuidado al trabajar
    	//en el programa con este usuario.
		
		if(Controladora.getInstance().activarLoadEmpleados() && Controladora.getInstance().activarLoadInfoEmpresa() && Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador"))
		{
				button_gastos.setDisable(false);
				button_productos.setDisable(true);
				button_ventas.setDisable(true);
				button_historial.setDisable(true);
				button_admin.setDisable(false);
				button_rh.setDisable(false);
				button_help.setDisable(true); 
				button_config.setDisable(false); 
  	        
				tab_administracionGeneral.setDisable(true);
				tab_administracionCajaChica.setDisable(true);
				tab_administracionCuentaBanco.setDisable(true);
				admin_pressed(null);
				selectTabUsuarios();
		}
		else {
			if(user.getUsuario().equalsIgnoreCase("administrador")) {
				//selectTabUsuarios();
		    	button_gastos.setDisable(true);
		    	button_productos.setDisable(true);
		    	button_ventas.setDisable(true);
		    	button_historial.setDisable(true);
		    	button_admin.setDisable(true);
		    	button_rh.setDisable(true);
		    	button_help.setDisable(false); 
		    	button_config.setDisable(false); 
		    	config_pressed(null);
		    
		    	
		    	tab_administracionGeneral.setDisable(true);
		    	tab_administracionCajaChica.setDisable(true);
		    	tab_administracionCuentaBanco.setDisable(true);
			}
			else {
				if(!user.getCargo().isManejodeproductos())
				{
					button_nuevoProducto.setDisable(true);
					button_modificarProducto.setDisable(true);
					button_eliminarProducto.setDisable(true);
				}
				
				if(!user.getCargo().isInfoproductos())
				{
					button_abrirInfoAdicional.setDisable(true);
					button_exportarInventario.setDisable(true);
				}
				
				if(!user.getCargo().isFacturarcompra())
				{
					button_nuevaFactura.setDisable(true);
				}
				
				if(!user.getCargo().isInfofactura())
				{
					button_facturaInfoAdicional.setDisable(true);
				}
				
				if(!user.getCargo().isManejopromociones())
				{
					button_nuevaPromocion.setDisable(true);
					button_eliminarPromocion.setDisable(true);
				}
				
				if(!user.getCargo().isAccesomodulorrhh())
				{
					selected_rh.setDisable(true);
					button_rh.setDisable(true);
				}
				
				if(!user.getCargo().isAccesomodulogastos())
				{
					selected_Gastos.setDisable(true);
					button_gastos.setDisable(true);
				}
				
				if(!user.getCargo().isAccesomodulohistorial())
				{
					selected_historial.setDisable(true);
					button_historial.setDisable(true);
				}
				
				if(!user.getCargo().isAccesomoduloconfiguracion())
				{
					selected_config.setDisable(true);
					button_config.setDisable(true);
				}
				
				if(user.getCargo().getNombre().equalsIgnoreCase("Cajero"))
				{
					selected_admin.setDisable(true);
					button_admin.setDisable(true);
					tab_atributos.setDisable(true);
					tab_rubros.setDisable(true);
				}
			}
		}
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	//Seteando la ventana principal por permisos del cargo del usuario
    	verifyUserPermissions();
    	
    	//Seteando los clientes
    	ObservableList<String> combobox_clienteData = FXCollections.observableArrayList();
    	combobox_clienteData.addAll("Codigo", "Nombre");
    	combobox_buscarCliente.setItems(combobox_clienteData);
    	fillClientList(null);
    	
    	//Seteando los proveedores
    	ObservableList<String> combobox_proveedorData = FXCollections.observableArrayList();
    	combobox_buscarProveedor.setValue("Buscar por:");
    	combobox_proveedorData.addAll("Codigo", "Nombre");
    	combobox_buscarProveedor.setItems(combobox_proveedorData);
    	fillProveedorList(null, "");
    	
    	//Seteando los empleados
    	fillEmpleadoList(null, "");
    	ObservableList<String> combobox_empleadoData = FXCollections.observableArrayList();
    	combobox_buscarEmpleado.setValue("Buscar por:");
    	combobox_empleadoData.addAll("Codigo", "Nombre");
    	combobox_buscarEmpleado.setItems(combobox_proveedorData);
    	
    	//Seteando los rubros
    	ObservableList<String> combobox_rubroData = FXCollections.observableArrayList();
    	combobox_rubroBusqueda.setValue("Buscar por:");
    	combobox_rubroData.addAll("Codigo", "Nombre");
    	combobox_rubroBusqueda.setItems(combobox_rubroData);
    	fillRubroList(null);
    	
    	//Seteando el combobox de productos
    	ObservableList<String> combobox_data = FXCollections.observableArrayList();
    	combobox_data.addAll("Codigo", "Nombre", "Proveedor", "Rubro");
    	combobox_productBuscar.setItems(combobox_data);
    	fillProductList(null, "");
    	
    	//Seteando los gastos generales
    	fillGastosGenerales(null);
    	
    	//Seteando los atributos
    	fillAtributesList(null);
    	
    	//Seteando los facturas
    	fillFactura(null);
    	
    	//Seteando las promociones
    	fillPromocion(null);
    	
    	//Seteando la categoria de empleados
    	fillCategoriaEmpleado();
    	
    	//Setear valor del promedio de venta anual
    	if(Controladora.getInstance().activarLoadPromedioGananciaAnual())
    	{
    		textfield_PromedioVenta.setText(Float.toString(Controladora.getInstance().getVentaPromedioMensual()));
    	}
    	
    	//Seteando los spinners y datepickers de la configuracion
    	setDatePickersConfiguracion();
    	setSpinnersConfiguracion();
    	
    	//Seteando el nombre de la empresa
    	if(Controladora.getInstance().getMiEmpresa() != null) {
    		text_negocioName.setText(Controladora.getInstance().getMiEmpresa().getNombre());
    	}
    	
    	//Seteando peticiones
    	setPeticiones();
    	fillPeticion();
    	
    	//Seteando usuarios
    	fillCargoUsuario();
    	fillUsuario(null);
    	
    	//Seteando reportes
    	fillReporteTotalTransacciones();
    	
    	//Seteando info empresa
    	fillEmpresa();
    	
    	//Obtener fecha actual y traducir dias y meses
    	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    	//java.util.Date currentTime = localCalendar.getTime();
    	DateFormatSymbols letras = new DateFormatSymbols();
    	
    	//Los días de la semana y los meses en java (y realmente en cualquier otro lenguaje) están escritas en inglés,
    	//el switch fue la solución que encontramos a ese tropiezo.
    	String dia = letras.getWeekdays()[localCalendar.get(Calendar.DAY_OF_WEEK)];
    	switch (dia) {
    	  case "Monday":
    		  dia = "lunes";
    	    break;
    	  case "Tuesday":
    		  dia = "martes";
    	    break;
    	  case "Wednesday":
    		  dia = "miércoles";
    	    break;
    	  case "Thursday":
    		  dia = "jueves";
    	    break;
    	  case "Friday":
    		  dia = "viernes";
    	    break;
    	  case "Saturday":
    		  dia = "sábado";
    	    break;
    	  case "Sunday":
    		  dia = "domingo";
    	    break;
    	}
    	
    	String mes = letras.getMonths()[localCalendar.get(Calendar.MONTH)];
    	switch (mes) {
    	  case "January":
    	    mes = "enero";
    	    break;
    	  case "February":
    		  mes = "febrero";
    	    break;
    	  case "March":
    		  mes = "marzo";
    	    break;
    	  case "April":
    		  mes = "abril";
    	    break;
    	  case "May":
    		  mes = "mayo";
    	    break;
    	  case "June":
    		  mes = "junio";
    	    break;
    	  case "July":
    		  mes = "julio";
    	    break;
    	  case "August":
    		  mes = "agosto";
    	    break;
    	  case "September":
    		  mes = "septiembre";
    	    break;
    	  case "October":
    		  mes = "octubre";
    	    break;
    	  case "November":
    		  mes = "noviembre";
    	    break;
    	  case "December":
    		  mes = "diciembre";
    	    break;
    	}
    	
    	label_bienvenido.setText("Bienvenido, " + Controladora.getInstance().getUsuarioLogueado().getUsuario() + ". Hoy es " + dia 
    	+ ", "+ localCalendar.get(Calendar.DAY_OF_MONTH) + " de " + mes + " de " + localCalendar.get(Calendar.YEAR));
    	
    	//Seteando Caja chica
    	fillCajaTransacciones();
    	if(Controladora.getInstance().getMiCajaChica() != null) {
    		textfield_cajaFondoActual.setText(Float.toString(Controladora.getInstance().getMiCajaChica().getMontoActual()));
    	}
    	else {
    		textfield_cajaFondoActual.setText("0.0");
    	}
    	
    	//Seteando cuenta de banco
    	fillCuentaTransacciones();
    	if(Controladora.getInstance().getMiCuentaBanco() != null) {
    		textfield_cuentaFondoActual.setText(Float.toString(Controladora.getInstance().getMiCuentaBanco().getMontoActual()));
    	}
    	else {
    		textfield_cuentaFondoActual.setText("0.0");
    	}
    	
    	//Llenando el listview para la busqueda de atributos
    	fillAtributosSearchBar();
    	
    	//Seteando modificacion de tableviews
    	setEditEmpleado();
    	setEditRubros();
    	setEditClientes();
    	setEditProveedores();
    	
    	Image prod_nuevo = new Image(getClass().getResourceAsStream("images/buttons/producto_nuevo.png"));
	    ImageInput prod_nuevo1 = new ImageInput();
	    prod_nuevo1.setSource(prod_nuevo);
    	button_nuevoProducto.setEffect(prod_nuevo1);
    	
    	Image prod_modificado = new Image(getClass().getResourceAsStream("images/buttons/producto_modificar.png"));
	    ImageInput prod_modificado1 = new ImageInput();
	    prod_modificado1.setSource(prod_modificado);
    	button_modificarProducto.setEffect(prod_modificado1);
    	
    	Image prod_eliminar = new Image(getClass().getResourceAsStream("images/buttons/producto_eliminar.png"));
	    ImageInput prod_eliminar1 = new ImageInput();
	    prod_eliminar1.setSource(prod_eliminar);
    	button_eliminarProducto.setEffect(prod_eliminar1);
    	
    	Image rubro_nuevo = new Image(getClass().getResourceAsStream("images/buttons/rubro_add.png"));
	    ImageInput rubro_nuevo1 = new ImageInput();
	    rubro_nuevo1.setSource(rubro_nuevo);
    	button_rubroNuevo.setEffect(rubro_nuevo1);
    	
    	Image rubro_eliminar = new Image(getClass().getResourceAsStream("images/buttons/rubro_delete.png"));
	    ImageInput rubro_eliminar1 = new ImageInput();
	    rubro_eliminar1.setSource(rubro_nuevo);
    	button_rubroEliminar.setEffect(rubro_eliminar1);
    	
    	Image peticion_nueva = new Image(getClass().getResourceAsStream("images/buttons/peticion_nueva.png"));
	    ImageInput peticion_nueva1 = new ImageInput();
	    peticion_nueva1.setSource(peticion_nueva);
    	button_peticion_nueva.setEffect(peticion_nueva1);
    	
    	Image peticion_admin = new Image(getClass().getResourceAsStream("images/buttons/peticion_admin.png"));
	    ImageInput peticion_admin1 = new ImageInput();
	    peticion_admin1.setSource(peticion_admin);
    	button_peticionAdministrar.setEffect(peticion_admin1);
    	
    	Image nuevo_emp = new Image(getClass().getResourceAsStream("images/buttons/empleado_new.png"));
	    ImageInput nuevo_emp1 = new ImageInput();
	    nuevo_emp1.setSource(nuevo_emp);
    	button_nuevoEmpleado.setEffect(nuevo_emp1);
    	
    	Image eliminar_emp = new Image(getClass().getResourceAsStream("images/buttons/empleado_delete.png"));
	    ImageInput eliminar_emp1 = new ImageInput();
	    eliminar_emp1.setSource(eliminar_emp);
    	button_eliminarVendedor.setEffect(eliminar_emp1);
    	
    	Image nuevo_usu = new Image(getClass().getResourceAsStream("images/buttons/user_new.png"));
	    ImageInput nuevo_usu1 = new ImageInput();
	    nuevo_usu1.setSource(nuevo_usu);
    	button_nuevo_usuario.setEffect(nuevo_usu1);
    	
    	Image eliminar_usu = new Image(getClass().getResourceAsStream("images/buttons/user_delete.png"));
	    ImageInput eliminar_usu1 = new ImageInput();
	    eliminar_usu1.setSource(eliminar_usu);
    	button_eliminar_usuario.setEffect(eliminar_usu1);
    	
    	Image factura_nueva = new Image(getClass().getResourceAsStream("images/buttons/factura_nueva.png"));
	    ImageInput factura_nueva1 = new ImageInput();
	    factura_nueva1.setSource(factura_nueva);
    	button_nuevaFactura.setEffect(factura_nueva1);
    	
    	Image promocion_nueva = new Image(getClass().getResourceAsStream("images/buttons/promo_nueva.png"));
	    ImageInput promocion_nueva1 = new ImageInput();
	    promocion_nueva1.setSource(promocion_nueva);
    	button_nuevaPromocion.setEffect(promocion_nueva1);
    	
    	Image promocion_eliminar = new Image(getClass().getResourceAsStream("images/buttons/promo_delete.png"));
	    ImageInput promocion_eliminar1 = new ImageInput();
	    promocion_eliminar1.setSource(promocion_eliminar);
    	button_eliminarPromocion.setEffect(promocion_eliminar1);
    	
    	Image cliente_nuevo = new Image(getClass().getResourceAsStream("images/buttons/client_nuevo.png"));
	    ImageInput cliente_nuevo1 = new ImageInput();
	    cliente_nuevo1.setSource(cliente_nuevo);
    	button_nuevoCliente.setEffect(cliente_nuevo1);
    	
    	Image cliente_eliminar = new Image(getClass().getResourceAsStream("images/buttons/client_delete.png"));
	    ImageInput cliente_eliminar1 = new ImageInput();
	    cliente_eliminar1.setSource(cliente_eliminar);
    	button_eliminarCliente.setEffect(cliente_eliminar1);
    	
    	Image proveedor_nuevo = new Image(getClass().getResourceAsStream("images/buttons/provee_nuevo.png"));
	    ImageInput proveedor_nuevo1 = new ImageInput();
	    proveedor_nuevo1.setSource(proveedor_nuevo);
    	button_nuevoProveedor.setEffect(proveedor_nuevo1);
    	
    	Image proveedor_delete = new Image(getClass().getResourceAsStream("images/buttons/provee_delete.png"));
	    ImageInput proveedor_delete1 = new ImageInput();
	    proveedor_delete1.setSource(proveedor_delete);
    	button_eliminarProveedor.setEffect(proveedor_delete1);
    	
    	pressed_productos1.setSource(pressed_productos); 
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_gastos1.setSource(nonpressed_gastos ); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	nonpressed_config1.setSource(nonpressed_config);
    	
    	button_productos.setEffect(pressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	button_gastos.setEffect(nonpressed_gastos1);
    }
    
	public void fillCargoUsuario()
    {
		ObservableList<String> combobox_data = FXCollections.observableArrayList();
    	combobox_data.addAll("Nombre");
    	combobox_cargoUsuario.setItems(combobox_data);
    	
    	combobox_data = FXCollections.observableArrayList();
    	
    	if(Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador"))
    	{
    		combobox_data.add("Administrador");
    	}
    	else
    	{
    		for (Cargo cargo : Controladora.getInstance().getMisCargos()) {
    			combobox_data.add(cargo.getNombre());
    		}
    	}
    	
    	combobox_cargoUsuario.setItems(combobox_data);
    	
    	
    }
    
    public void fillReporteTotalTransacciones()
    {
    	DecimalFormat df = new DecimalFormat("#.00");
    	//
    	textfield_CantidadVentasPagadas.setText("" + Controladora.getInstance().calcularCantidadVentasPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	Controladora.getInstance().calcularIngresosVentasPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_IngresosVentasPagadas.setText("" + df.format(Controladora.getInstance().getIngresosVentasPagadas()));
    	//
    	Controladora.getInstance().calcularGananciaVentasPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_GananciaVentasPagadas.setText("" + df.format(Controladora.getInstance().getGananciaVentasPagadas()));
    	//
    	textfield_CantidadVentasPorCobrar.setText("" + Controladora.getInstance().cantidadVentasPorCobrar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	Controladora.getInstance().calcularIngresosVentasPorPagar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_IngresosVentasPorCobrar.setText("" + df.format(Controladora.getInstance().getIngresosVentasPorPagar()));
    	//
    	Controladora.getInstance().calcularDeudafacturas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_DeudaVentasPorCobrar.setText("" + df.format(Controladora.getInstance().getDeudaTotal()));
    	//
    	Controladora.getInstance().calcularIngresoTotal();
    	textfield_totalIngresos.setText("" + df.format(Controladora.getInstance().getIngresoTotal()));
    	//
    	Controladora.getInstance().calcularGanaciaTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_TotalGanancias.setText("" + df.format(Controladora.getInstance().getGananciasTotal()));
    	//
    	Controladora.getInstance().calcularPagosDeudasClientesTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_PagosVentasPorCobrar.setText("" + df.format(Controladora.getInstance().getPagosDeudasClientesTotal()));
    	//
    	textfield_cantidadComprasPagadas.setText("" + Controladora.getInstance().calcularCantidadPeticionesPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	textfield_egresosPagos.setText("" + df.format(Controladora.getInstance().calculoEgresosPagos(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	//
    	textfield_cantidadComprasPorPagar.setText("" + Controladora.getInstance().calcularCantidadPeticionesPorPagar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	textfield_egresosPeticionesPorPagar.setText("" + df.format(Controladora.getInstance().calcularEgresosPeticionesPorPagar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	//
    	textField_pagosPeticionesPorPagar.setText("" + df.format(Controladora.getInstance().calcularMontoPagosPeticionesPorPagarTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	
    	textField_deudaPeticionesPorPagar.setText("" + df.format(Controladora.getInstance().calcularDeudaPeticionesTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	
    	float ingresoTotal = Float.parseFloat(textfield_IngresosVentasPagadas.getText()) + Float.parseFloat(textfield_GananciaVentasPagadas.getText());
    	
    	textfield_ingresosTotales.setText("" + df.format(ingresoTotal));
    	
    	float egresoTotal = Float.parseFloat(textfield_egresosPagos.getText()) + Float.parseFloat(textfield_egresosPeticionesPorPagar.getText());
    	
    	textfield_EgresosTotales.setText("" + df.format(egresoTotal));
    	
    }
    
    public void fillReportePorDia(ActionEvent event)
    {
    	textfield_ventasPagadasPorDia.setText("" + Controladora.getInstance().calcularVentasPagadasPorDia(datepicker_transaccionesPorDia.getValue()));
    	textfield_pagosVentasPorDia.setText("" + Controladora.getInstance().calcularPagosDeVentasPorDia(datepicker_transaccionesPorDia.getValue()));
    	textfield_comprasPagadasPorDia.setText("" + Controladora.getInstance().calculoComprasPagadasPorDia(datepicker_transaccionesPorDia.getValue()));
    	textfield_pagoComprasPorDia.setText("" + Controladora.getInstance().calcularPagosDeComprasPorDia(datepicker_transaccionesPorDia.getValue()));
    	textfield_salarioEmpleadoPorDia.setText("" + Controladora.getInstance().calcularPagoEmpleadosPorDia());
    }
    
    public void fillReporteTotalTransaccionesAction(ActionEvent event)
    {
    	DecimalFormat df = new DecimalFormat("#.00");
    	
    	textfield_CantidadVentasPagadas.setText("" + Controladora.getInstance().calcularCantidadVentasPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	
    	Controladora.getInstance().calcularIngresosVentasPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_IngresosVentasPagadas.setText("" + df.format(Controladora.getInstance().getIngresosVentasPagadas()));
    	//
    	Controladora.getInstance().calcularGananciaVentasPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_GananciaVentasPagadas.setText("" + df.format(Controladora.getInstance().getGananciaVentasPagadas()));
    	//
    	textfield_CantidadVentasPorCobrar.setText("" + Controladora.getInstance().cantidadVentasPorCobrar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	Controladora.getInstance().calcularIngresosVentasPorPagar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_IngresosVentasPorCobrar.setText("" + df.format(Controladora.getInstance().getIngresosVentasPorPagar()));
    	//
    	Controladora.getInstance().calcularDeudafacturas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_DeudaVentasPorCobrar.setText("" + df.format(Controladora.getInstance().getDeudaTotal()));
    	//
    	Controladora.getInstance().calcularIngresoTotal();
    	textfield_totalIngresos.setText("" + df.format(Controladora.getInstance().getIngresoTotal()));
    	//
    	Controladora.getInstance().calcularGanaciaTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_TotalGanancias.setText("" + df.format(Controladora.getInstance().getGananciasTotal()));
    	//
    	Controladora.getInstance().calcularPagosDeudasClientesTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue());
    	textfield_PagosVentasPorCobrar.setText("" + df.format(Controladora.getInstance().getPagosDeudasClientesTotal()));
    	//
    	textfield_cantidadComprasPagadas.setText("" + Controladora.getInstance().calcularCantidadPeticionesPagadas(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	textfield_egresosPagos.setText("" + df.format(Controladora.getInstance().calculoEgresosPagos(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	//
    	textfield_cantidadComprasPorPagar.setText("" + Controladora.getInstance().calcularCantidadPeticionesPorPagar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue()));
    	//
    	textfield_egresosPeticionesPorPagar.setText("" + df.format(Controladora.getInstance().calcularEgresosPeticionesPorPagar(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	//
    	textField_pagosPeticionesPorPagar.setText("" + df.format(Controladora.getInstance().calcularMontoPagosPeticionesPorPagarTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	
    	textField_deudaPeticionesPorPagar.setText("" + df.format(Controladora.getInstance().calcularDeudaPeticionesTotal(datepicker_totalTransaccionesInicial.getValue(), datepicker_totalTransaccionesFinal.getValue())));
    	
    	float ingresoTotal = Float.parseFloat(textfield_IngresosVentasPagadas.getText()) + Float.parseFloat(textfield_GananciaVentasPagadas.getText());
    	
    	textfield_ingresosTotales.setText("" + df.format(ingresoTotal));
    	
    	float egresoTotal = Float.parseFloat(textfield_egresosPagos.getText()) + Float.parseFloat(textfield_egresosPeticionesPorPagar.getText());
    	
    	textfield_EgresosTotales.setText("" + df.format(egresoTotal));
    }
    
    public void fillRubroList(ArrayList<Rubro> r) {
    	ObservableList<Rubro> data = FXCollections.observableArrayList();
    	if(r == null) {
    		for(Rubro rubro : Controladora.getInstance().getMisRubros()) {
    			if(!rubro.isBorrado()) {
    				data.add(rubro);
    			}
    		}
		}
    	else {
    		for(Rubro rubro : r) {
    			if(!rubro.isBorrado()) {
    				data.add(rubro);
    			}
    		}
    	}
    	tablecolumn_rubroCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_rubroNombre.setCellValueFactory(new PropertyValueFactory<>("nombreRubro"));
    	tableview_rubro.setItems(data);
    	tableview_rubro.refresh();
    }
    
    public void fillGastosGenerales(ArrayList<GastoGeneral> g)
    {
    	for(GastoGeneral gasto : Controladora.getInstance().getMisGastosGenerales()) {
    		if(!gasto.isBorrado()) {
    			listview_gastosG.getItems().add(gasto.getNombre());
    		}
    	}
    }
    
    public void fillProductList(ArrayList<Producto> p, String belongsTo) {
    	ObservableList<Producto> data = FXCollections.observableArrayList();
    	if(p == null) {
    		for(Producto productos : Controladora.getInstance().getMisProductos()) {
    			if(!productos.isBorrado()) {
    				data.add(productos);
    			}
    		}
    		
    	}
    	else {
    		data.addAll(p);
    	}
    	if(belongsTo.equalsIgnoreCase("")) {
    		tablecolumn_productCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		tablecolumn_productNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_productExistenciaInicial.setCellValueFactory(new PropertyValueFactory<>("existenciaInicial"));
    		tablecolumn_productExistenciaActual.setCellValueFactory(new PropertyValueFactory<>("existenciaActual"));
    		tablecolumn_productExistenciaMinima.setCellValueFactory(new PropertyValueFactory<>("existenciaMinima"));
    		tablecolumn_productExistenciaMaxima.setCellValueFactory(new PropertyValueFactory<>("existenciaMaxima"));
    		tablecolumn_productTipo.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
    		tablecolumn_productRubro.setCellValueFactory(new PropertyValueFactory<>("rubroProducto"));
    		tablecolumn_productProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorPrin"));
    		tablecolumn_productPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
    		tablecolumn_productCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
    		tablecolumn_productDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripionFija"));
    		tableview_productList.setItems(data);
    		tableview_productList.refresh();
    	}
    	else if(belongsTo.equalsIgnoreCase("Peticion")) {
    		tablecolumn_peticionProductoCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        	tablecolumn_peticionProductoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        	tablecolumn_peticionProductoExistenciaInicial.setCellValueFactory(new PropertyValueFactory<>("existenciaInicial"));
        	tablecolumn_peticionProductoExistenciaActual.setCellValueFactory(new PropertyValueFactory<>("existenciaActual"));
        	tablecolumn_peticionProductoExistenciaMinima.setCellValueFactory(new PropertyValueFactory<>("existenciaMinima"));
        	tablecolumn_peticionProductoExistenciaMaxima.setCellValueFactory(new PropertyValueFactory<>("existenciaMaxima"));
        	tablecolumn_peticionProductoTipo.setCellValueFactory(new PropertyValueFactory<>("tipoProducto"));
        	tablecolumn_peticionProductoRubro.setCellValueFactory(new PropertyValueFactory<>("rubroProducto"));
        	tablecolumn_peticionProductoPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        	tablecolumn_peticionProductoCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        	tablecolumn_peticionProductoDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripionFija"));
        	tableview_peticionProducto.setItems(data);
        	tableview_peticionProducto.refresh();
        	
        	setPeticiones();
    	}
    	
    }
    
    public void fillAtributesList(ArrayList<Atributos> a) {
    	ObservableList<Atributos> data = FXCollections.observableArrayList();
    	if(a == null) {
    		for(Atributos atributo : Controladora.getInstance().getMisAtributos()) {
    			if(!atributo.isBorrado()) {
    				data.add(atributo);
    			}
    		}
    	}
    	else {
    		for(Atributos atributo : a) {
    			if(!atributo.isBorrado()) {
    				data.add(atributo);
    			}
    		}
    	}
		tablecolumn_atributogrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
    	tablecolumn_atributonombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tableView_atributos.setItems(data);
    	tableView_atributos.refresh();
    }
    
    public void fillClientList(ArrayList<Cliente> c) {
    	ObservableList<Cliente> data = FXCollections.observableArrayList();
    	if(c == null) {
    		for(Cliente cliente : Controladora.getInstance().getMisClientes()) {
    			if(!cliente.isBorrado()) {
    				data.add(cliente);
    			}
    		}
    	}
    	else {
    		for(Cliente cliente : c) {
    			data.add(cliente);
    		}
    	}
		tablecolumn_clienteCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_clienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_clienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    	tablecolumn_clienteCumple.setCellValueFactory(new PropertyValueFactory<>("cumpleanos"));
    	tablecolumn_clienteRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    	tablecolumn_clienteTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
    	tableview_clientesList.setItems(data);
    	tableview_clientesList.refresh();
	}
    
    public void fillProveedorList(ArrayList<Proveedores> p, String belongsTo) {
    	ObservableList<Proveedores> data = FXCollections.observableArrayList();
    	if(p == null) { 		
    		for(Proveedores proveedor : Controladora.getInstance().getMisProveedores()) {
    			if(!proveedor.isBorrado()) {
    				data.add(proveedor);
    			}
    		}
    		if(data.get(0).getCodigo().equalsIgnoreCase("00"))
    		{
    			data.remove(0);
    		}
    		if(data.size() > 1)
    		{
    			if(data.get(0).getCodigo().equalsIgnoreCase(data.get(1).getCodigo()))
        		{
        			data.remove(1);
        		}
    		}	
    	}
    	else {	
    		for(Proveedores proveedor : p) {
    			if(!proveedor.isBorrado()) {
    				data.add(proveedor);
    			}
    		}
    		if(data.get(0).getCodigo().equalsIgnoreCase("00"))
    		{
    			data.remove(0);
    		}
    		if(data.size() > 1)
    		{
    			if(data.get(0).getCodigo().equalsIgnoreCase(data.get(1).getCodigo()))
        		{
        			data.remove(1);
        		}
    		}
    	}
    	if(belongsTo.equalsIgnoreCase("")) {
    		tablecolumn_proveedorCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		tablecolumn_proveedorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_proveedorRubro.setCellValueFactory(new PropertyValueFactory<>("rubro"));
    		tablecolumn_proveedorDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
    		tablecolumn_proveedorCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    		tablecolumn_proveedorTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    		tablecolumn_proveedorRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    		tablecolumn_proveedorSitioWeb.setCellValueFactory(new PropertyValueFactory<>("sitioWeb"));
    		tablecolumn_proveedorSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
    		tableview_proveedoresList.setItems(data);
    		tableview_proveedoresList.refresh();
    	}
    	else if(belongsTo.equalsIgnoreCase("Peticion")) {
    		tablecolumn_peticionProveedorCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		tablecolumn_peticionProveedorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_peticionProveedorRubro.setCellValueFactory(new PropertyValueFactory<>("rubro"));
    		tablecolumn_peticionProveedorDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
    		tablecolumn_peticionProveedorCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    		tablecolumn_peticionProveedorTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    		tablecolumn_peticionProveedorRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    		tablecolumn_peticionProveedorSitioWeb.setCellValueFactory(new PropertyValueFactory<>("sitioWeb"));
    		tablecolumn_peticionProveedorSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
    		tableview_peticionProveedoresList.setItems(data);
    		tableview_peticionProveedoresList.refresh();
    	}
    	
    }
    
    public void fillEmpleadoList(ArrayList<Empleado> e, String belongsTo) {
    	ObservableList<Empleado> data = FXCollections.observableArrayList();
    	if(e == null) {
    		for(Empleado empleado : Controladora.getInstance().getMisEmpleados()) {
    			if(!empleado.isBorrado()) {
    				data.add(empleado);
    			}
    		}
    		
    	}
    	else {
    		for(Empleado empleado : e){
    			if(!empleado.isBorrado()) {
    				data.add(empleado);
    			}
    		}
    	}
    	if(belongsTo.equalsIgnoreCase("")) {
    		tablecolumn_empleadoCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		tablecolumn_empleadoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_empleadoTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    		tablecolumn_empleadoDireccion.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
    		tablecolumn_empleadoCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    		tablecolumn_empleadoRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    		tablecolumn_empleadoTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    		tablecolumn_empleadoSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
    		tableview_empleadoList.setItems(data);
    		tableview_empleadoList.refresh();
    	}
    	else if(belongsTo.equalsIgnoreCase("Usuario")) {
    		tablecolumn_usuarioEmpleadoCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		tablecolumn_usuarioEmpleadoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_usuarioEmpleadoTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    		tablecolumn_usuarioEmpleadoDireccion.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
    		tablecolumn_usuarioEmpleadoCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    		tablecolumn_usuarioEmpleadoRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    		tablecolumn_usuarioEmpleadoTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    		tablecolumn_usuarioEmpleadoSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
    		tableview_usuarioEmpleadoList.setItems(data);
    		tableview_usuarioEmpleadoList.refresh();
    	}
    	
    }
    
    public void fillCategoriaEmpleado() {
    	ObservableList<CategoriaEmpleado> dataC = FXCollections.observableArrayList();
    	if(Controladora.getInstance().getMisCategoriasEmpleado().size() > 0) {
    		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
    			if(!c.isBorrado())
    			{
    				dataC.add(c);
    			}
    		}
    		tablecolumn_NombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_SueldoCategoria.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
    		tableview_CategoriaEmp.setItems(dataC);
    		tableview_CategoriaEmp.refresh();
    	}
    }
    
    public void fillCajaTransacciones() {
    	ObservableList<TransaccionesCajaChica> data = FXCollections.observableArrayList();
    	if(Controladora.getInstance().getMiCajaChica() != null) {
    		for(TransaccionesCajaChica t : Controladora.getInstance().getMiCajaChica().getTransacciones()) {
    			data.add(t);
    		}
    	}
    	tablecolumn_cajaMonto.setCellValueFactory(new PropertyValueFactory<>("actualizacion"));
    	tablecolumn_cajaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    	tablecolumn_cajaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    	tablecolumn_cajaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuarioNombre"));
    	
    	tableview_cajaList.setItems(data);
    	tableview_cajaList.refresh();
    }
    
    public void fillCuentaTransacciones() {
    	ObservableList<TransaccionesCuentaBanco> data = FXCollections.observableArrayList();
    	if(Controladora.getInstance().getMiCuentaBanco() != null) {
    		for(TransaccionesCuentaBanco t : Controladora.getInstance().getMiCuentaBanco().getTransacciones()) {
    			data.add(t);
    		}
    	}
    	tablecolumn_cuentaMonto.setCellValueFactory(new PropertyValueFactory<>("actualizacion"));
    	tablecolumn_cuentaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    	tablecolumn_cuentaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    	
    	tableview_cuentaList.setItems(data);
    	tableview_cuentaList.refresh();
    }
    
    public TableView<Cliente> getTableview_clientesList(){
    	//tableview_clientesList.setId("tableview_clientesList");
    	return this.tableview_clientesList;
    }
    
    //Ventas
    public void pressed_GuardarPromedioVenta(ActionEvent event)
    {
    	ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
    	 Dialog<ButtonType> dialog = new Dialog<>();
    	 dialog.getDialogPane().getButtonTypes().add(loginButtonType);
    	 boolean disabled = false; // computed based on content of text fields, for example
    	 dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
    	 dialog.setContentText("Venta promedio anual modificada con éxito!");
    	 dialog.setTitle("Notificación");
    	 
    	if(!textfield_PromedioVenta.getText().isEmpty())
    	{
    		Optional<ButtonType> result = dialog.showAndWait();
    		
    			 float monto = Float.parseFloat(textfield_PromedioVenta.getText());
    	    		
    	    		if(monto != Controladora.getInstance().getVentaPromedioMensual())
    	    		{
    	    			Controladora.getInstance().setVentaPromedioMensual(monto);
    	        		if(Controladora.getInstance().activarLoadPromedioGananciaAnual())
    	        		{
    	        			Controladora.getInstance().desactivarPromedioGananciaAnualActual();
    	        		}	
    	        		Controladora.getInstance().guardarPromedioGananciaAnualSQL(monto);
    	    		}
    	}
    	if(Controladora.getInstance().activarLoadEmpleados() && Controladora.getInstance().activarLoadInfoEmpresa() && Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador"))
		{
				button_gastos.setDisable(false);
				button_productos.setDisable(true);
				button_ventas.setDisable(true);
				button_historial.setDisable(true);
				button_admin.setDisable(false);
				button_rh.setDisable(false);
				button_help.setDisable(false); 
				button_config.setDisable(false); 
  	        
				tab_administracionGeneral.setDisable(false);
				tab_administracionCajaChica.setDisable(true);
				tab_administracionCuentaBanco.setDisable(true);
				admin_pressed(null);
				selectTabUsuarios();
		}
		else {
		      	rh_pressed(null);
		      	selectTabEmpleado();
		      	//selectTabCategoriaEmpleado();
		        button_gastos.setDisable(true);
		        button_productos.setDisable(true);
		        button_ventas.setDisable(true);
		        button_historial.setDisable(true);
		        button_admin.setDisable(true);
		        button_rh.setDisable(false);
		        button_help.setDisable(false); 
		        button_config.setDisable(false); 
		        
		        tab_administracionGeneral.setDisable(true);
		        tab_administracionCajaChica.setDisable(true);
		        tab_administracionCuentaBanco.setDisable(true);
		}
    }
    
    public void selectTabCliente() {
    	tabpane_Ventas.getSelectionModel().select(tab_clientes);
    }
    
    public void selectTabProveedor() {
    	tabpane_Ventas.getSelectionModel().select(tab_proveedores);;
    }
    
    public void selectTabAdministracionGeneral() {
    	tabpane_Ventas.getSelectionModel().select(tab_administracionGeneral);;
    }
    
    public void selectTabEmpleado() {
    	tabpane_recursosHumanos.getSelectionModel().select(tab_empleados);;
    }
    
    public void selectTabCategoriaEmpleado() {
    	tabpane_recursosHumanos.getSelectionModel().select(tab_CategoriaEmpleado);;
    }
    
    public void selectTabFacturas() {
    	tabpane_Ventas.getSelectionModel().select(tab_facturacion);;
    }
    
    public void selectTabPromocion() {
    	tabpane_Ventas.getSelectionModel().select(tab_promocion);;
    }
    
    public void selectTabUsuarios() {
    	tabpane_administracion.getSelectionModel().select(tab_administracionUsuarios);
    }
    
    public void abrirInfoAdicionalProducto(ActionEvent event) {
    	pane_InfoAdicionalProducto.setVisible(true);
    	titledpane_productoInformacionAdicional.setVisible(true);
    	Producto p = tableview_productList.getSelectionModel().getSelectedItem();
    	System.out.println("El nombre de este producto es: " + p.getNombre());
    	if(p.getTipoProducto().equalsIgnoreCase("Estandar") || p.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		try {
    			Estandar e = (Estandar) Controladora.getInstance().buscarProducto(p.getNombre());
    			if(e.getPartida() != null) {
    				ObservableList<CantProductosUtilizados> data = FXCollections.observableArrayList();
    				for(CantProductosUtilizados c : e.getPartida().getListaMateriales()) {
    					data.add(c);
    				}
    				tablecolumn_productoPartidaUtilizado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    				tablecolumn_productoPartidaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadBig"));
    				tablecolumn_productoPartidaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
    				tableview_productoPartidaList.setItems(data);
    				tableview_productoPartidaList.refresh();
    			}
    			
    		}
    		catch(NullPointerException e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	if(p.getTipoProducto().equalsIgnoreCase("Servicio")) {
    		try {
    			ArrayList<Producto> productoSearch = Controladora.getInstance().searchProducts(p.getNombre().toLowerCase(), "Nombre");
    			Servicio s = (Servicio) productoSearch.get(0);
    			ObservableList<CantProductosUtilizados> data = FXCollections.observableArrayList();
    			for(CantProductosUtilizados c : s.getMaterialesUtilizados()) {
    				data.add(c);
    			}
    			tablecolumn_productoPartidaUtilizado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    			tablecolumn_productoPartidaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    			tablecolumn_productoPartidaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
    			tableview_productoPartidaList.setItems(data);
    			tableview_productoPartidaList.refresh();
    		}
    		catch(NullPointerException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	if(p.getTipoProducto().equalsIgnoreCase("Kit")) {
    		try {
    			ArrayList<Producto> productoSearch = Controladora.getInstance().searchProducts(p.getNombre().toLowerCase(), "Nombre");
    			Kit k = (Kit) productoSearch.get(0);
    			ObservableList<CantProductosUtilizados> data = FXCollections.observableArrayList();
    			for(CantProductosUtilizados c : k.getProductosContenidos()) {
    				data.add(c);
    			}
    			tablecolumn_productoPartidaUtilizado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    			tablecolumn_productoPartidaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    			tablecolumn_productoPartidaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
    			tableview_productoPartidaList.setItems(data);
    			tableview_productoPartidaList.refresh();
    		}
    		catch(NullPointerException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	ObservableList<Producto> dataProducto = FXCollections.observableArrayList();
    	dataProducto.add(p);
    	tablecolumn_productoCostoPartida.setCellValueFactory(new PropertyValueFactory<>("costoPartida"));
    	tablecolumn_productoCostoManoObra.setCellValueFactory(new PropertyValueFactory<>("manodeobra"));
    	tablecolumn_productoCostoCompra.setCellValueFactory(new PropertyValueFactory<>("costoDeCompra"));
    	tableview_productoCostosList.setItems(dataProducto);
    	tableview_productoCostosList.refresh();
    	
    	if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		Estandar m = (Estandar) Controladora.getInstance().buscarProducto(p.getNombre());
    		try {
    			ObservableList<Combinaciones> combinacionesData = FXCollections.observableArrayList();
    			System.out.println("La cantidad de combinaciones: " + m.getCombinaciones().size());
    			for(Combinaciones c : m.getCombinaciones()) {
    				combinacionesData.add(c);
    				System.out.println(c.getAtributo1() + " " + c.getAtributo2() + " " + c.getAtributo3());
    			}
    			tablecolumn_atributo1.setCellValueFactory(new PropertyValueFactory<>("atributo1"));
    			tablecolumn_atributo2.setCellValueFactory(new PropertyValueFactory<>("atributo2"));
    			tablecolumn_atributo3.setCellValueFactory(new PropertyValueFactory<>("atributo3"));
    			tablecolumn_existenciaActualCombinacion.setCellValueFactory(new PropertyValueFactory<>("existenciaActual"));
    			tablecolumn_numSerieCombinacion.setCellValueFactory(new PropertyValueFactory<>("numeroSerie"));
    			tableview_atributosList.setItems(combinacionesData);
    			tableview_atributosList.refresh();
    		}catch(NullPointerException e) {
    			
    		}
    		
    	}
    	
    	if(p.getFoto() != null)
    	{	
    		byte[] foto = p.getFoto();
        	InputStream in = new ByteArrayInputStream(foto);
        	Image fotomuestra = new Image(in);
        	
        	imageview_image.setImage(fotomuestra);
        	
        	double w = 0;
            double h = 0;

            double ratioX = imageview_image.getFitWidth() / fotomuestra.getWidth();
            double ratioY = imageview_image.getFitHeight() / fotomuestra.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = fotomuestra.getWidth() * reducCoeff;
            h = fotomuestra.getHeight() * reducCoeff;

            imageview_image.setX((imageview_image.getFitWidth() - w) / 2);
            imageview_image.setY((imageview_image.getFitHeight() - h) / 2);
    	}
    	
    	ObservableList<CostoIndirectoProducto> dataCostoIndirecto = FXCollections.observableArrayList();
    	for(CostoIndirectoProducto c : p.getCostosIndirectos()) {
    		dataCostoIndirecto.add(c);
    	}
    	tablecolumn_productoCostoIndirectoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_productoCostoIndirectoMonto.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	tableview_productoCostoIndirecto.setItems(dataCostoIndirecto);
    	tableview_productoCostoIndirecto.refresh();
    }
    
    public void cerrarInfoAdicionalProducto(ActionEvent event) {
    	pane_InfoAdicionalProducto.setVisible(false);
    	tableview_productoPartidaList.getItems().clear();
    	tableview_productoCostosList.getItems().clear();
    	tableview_atributosList.getItems().clear();
    }
    
    public void activarInfoAdicionalProducto(MouseEvent event) {
    	if(Controladora.getInstance().getUsuarioLogueado().getUsuario().equalsIgnoreCase("administrador")) {}
    	else if(!tableview_productList.getSelectionModel().isEmpty() && Controladora.getInstance().getUsuarioLogueado().getCargo().isInfoproductos()) {
    		button_abrirInfoAdicional.setDisable(false);
    	}
    }
    
    public void fillFactura(ArrayList<Factura> factura) {
    	ObservableList<Factura> data = FXCollections.observableArrayList();
    	if(factura == null) {
    		data.addAll(Controladora.getInstance().getMisFacturas());
    	}
    	else {
    		data.addAll(factura);
    	}
    	tablecolumn_facturaDate.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    	tablecolumn_facturaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    	tablecolumn_facturaTotalPagar.setCellValueFactory(new PropertyValueFactory<>("montoTotal"));
    	tablecolumn_facturaRecibido.setCellValueFactory(new PropertyValueFactory<>("montoRecibido"));
    	tablecolumn_facturaCambio.setCellValueFactory(new PropertyValueFactory<>("cambio"));
    	tablecolumn_facturaCliente.setCellValueFactory(new PropertyValueFactory<>("nombreUsuarioFact"));
    	tableview_facturaList.setItems(data);
    	tableview_facturaList.refresh();
    }
    
    public void fillPromocion(ArrayList<Promocion> promocion) {
    	ObservableList<Promocion> data = FXCollections.observableArrayList();
    	if(promocion == null) {
    		data.addAll(Controladora.getInstance().getMisPromociones());
    	}
    	else {
    		data.addAll(promocion);
    	}
    	tablecolumn_promocionCodigo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_promocionPorcentaje.setCellValueFactory(new PropertyValueFactory<>("porcentajeDescuento"));
    	tablecolumn_promocionFechaInicial.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
    	tablecolumn_promocionFechaFinal.setCellValueFactory(new PropertyValueFactory<>("fechaFinal"));
    	tablecolumn_promocionHoraInicial.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
    	tablecolumn_promocionHoraFinal.setCellValueFactory(new PropertyValueFactory<>("horaFinal"));
    	tablecolumn_promocionDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
    	tableview_promocionList.setItems(data);
    	tableview_promocionList.refresh();
    }
    
    
    
    public void habilitarNuevoUsuario(ActionEvent event) {
    	pane_nuevoUsuario.setVisible(true);
    	
    	//fillCargoUsuario();
    }
    
    public void cerrarNuevoUsuario(ActionEvent event) {
    	pane_nuevoUsuario.setVisible(false);
    }
    
    public void habilitarBusquedaEmpleadoUsuario(ActionEvent event) {
    	fillEmpleadoList(null, "Usuario");
    	titledpane_busquedaEmpleadoUsuario.setVisible(true);
    }
    
    public void empleadoUsuarioHabilitarSeleccion(MouseEvent event) {
    	if(!tableview_usuarioEmpleadoList.getSelectionModel().isEmpty()) {
    		button_seleccionarEmpleadoUsuario.setDisable(false);
    	}
    }
    
    public void seleccionarEmpleadoUsuario(ActionEvent event) {
    	Empleado empleado = tableview_usuarioEmpleadoList.getSelectionModel().getSelectedItem();
    	textfield_empleadoUsuario.setText(empleado.getCodigo());
    	cerrarBusquedaEmpleadoUsuario(null);
    }
    
    public void cerrarBusquedaEmpleadoUsuario(ActionEvent event) {
    	button_seleccionarEmpleadoUsuario.setDisable(true);
    	tableview_usuarioEmpleadoList.getSelectionModel().clearSelection();
    	titledpane_busquedaEmpleadoUsuario.setVisible(false);
    }
    
    public void tableviewFacturaClicked(MouseEvent event) {
    	if(!tableview_facturaList.getSelectionModel().isEmpty() && Controladora.getInstance().getUsuarioLogueado().getCargo().isInfofactura()) {
    		button_facturaInfoAdicional.setDisable(false);
    	}
    	else {
    		button_facturaInfoAdicional.setDisable(true);
    	}
    }
    
    public void habilitarInfoAdicionalFactura(ActionEvent event) {
    	Factura factura = tableview_facturaList.getSelectionModel().getSelectedItem();
    	fillInfoAdicionalFactura(factura);
    	titledpane_infoadicionalfactura.setVisible(true);
    }
    
    public void cerrarInfoAdicionalFactura(ActionEvent event) {
    	titledpane_infoadicionalfactura.setVisible(false);
    	tableview_facturaProductoList.getItems().clear();
    	textfield_facturaClienteCodigo.setText("");
    	textfield_facturaClienteNombre.setText("");
    	textfield_facturaTotalPagado.setText("");
    	
    	tableview_facturaList.getSelectionModel().clearSelection();
    	tableviewFacturaClicked(null);
    }
    
    public void fillInfoAdicionalFactura(Factura factura) {
    	ObservableList<CantBienesYServiciosUtilizados> data = FXCollections.observableArrayList();
    	System.out.println(factura.getFacturados().size());
    	for(CantBienesYServiciosUtilizados c : factura.getFacturados()) {
    		System.out.println("Hola");
    		data.add(c);
    	}
    	tablecolumn_facturaProductoList.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_facturaProductoUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
    	tablecolumn_facturaProductoCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    	tablecolumn_facturaProductoPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));
    	tablecolumn_facturaProductoValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    	
    	tableview_facturaProductoList.setItems(data);
    	tableview_facturaProductoList.refresh();
    	
    	if(factura.getMiCliente() != null) {
    		textfield_facturaClienteCodigo.setText(factura.getClienteCodigo());
    		textfield_facturaClienteNombre.setText(factura.getMiCliente().getNombre());
    	}
    	
    	textfield_facturaTotalPagado.setText(Float.toString(factura.getMontoTotal()));
    }
    
    public void fillUsuario(ArrayList<Usuario> u) {
    	ObservableList<Usuario> data = FXCollections.observableArrayList();
    	if(u == null) {
    		for(Usuario usuario : Controladora.getInstance().getMisUsuarios()) {
    			if(usuario.isUsuarioActivo()) {
    				data.add(usuario);
    			}
    		}
    	}
    	else {
    		for(Usuario usuario : u) {
    			if(usuario.isUsuarioActivo()) {
    				data.add(usuario);
    			}
    		}
    	}
    	
    	tablecolumn_usuarioUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    	tablecolumn_usuarioNombre.setCellValueFactory(new PropertyValueFactory<>("empleadoNombre"));
    	tablecolumn_usuarioCargo.setCellValueFactory(new PropertyValueFactory<>("cargoNombre"));
    	tableview_usuarioList.setItems(data);
    	tableview_usuarioList.refresh();
    }
    
    
    public void fillPeticion() {
    	ObservableList<Peticion> data = FXCollections.observableArrayList();
    	for(Peticion peticion : Controladora.getInstance().getMisPeticiones()) {
    		data.add(peticion);
    	}
    	tablecolumn_peticionCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_peticionProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorCodigo"));
    	tablecolumn_peticionProducto.setCellValueFactory(new PropertyValueFactory<>("productoNombre"));
    	tablecolumn_peticionCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    	tablecolumn_peticionMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
    	tablecolumn_peticionMetodo.setCellValueFactory(new PropertyValueFactory<>("metodoPago"));
    	tablecolumn_peticionEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    	tablecolumn_peticionFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    	
    	tableview_peticionList.setItems(data);
    	tableview_peticionList.refresh();
    }
    
 
    
    
    public void setPeticiones() {
    	ObservableList<String> combobox_data = FXCollections.observableArrayList();
    	combobox_data.addAll("Codigo", "Nombre", "Proveedor", "Rubro");
    	combobox_peticionProducto.setItems(combobox_data);
    	
    	combobox_data = FXCollections.observableArrayList();
    	for(Proveedores p : Controladora.getInstance().getMisProveedores()) {
    		if(!p.isBorrado()) {
    			combobox_data.add(p.getCodigo());
    		}
    	}
    	//combobox_peticionProveedor.setItems(combobox_data);
    	
    	combobox_data = FXCollections.observableArrayList();
    	combobox_data.addAll("Aceptada", "Declinada", "Pendiente");
    	combobox_peticionEstado.setItems(combobox_data);
    	combobox_peticionEstado.getSelectionModel().select("Pendiente");
    	
    	//Spinner
    	SpinnerValueFactory<Integer> cantidad = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999999, 0);
    	spinner_peticionCantidad.setValueFactory(cantidad);
    }
    
    public void setDatePickersConfiguracion() {		
		datepicker_empresaFechaFinal.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		datepicker_empresaFechaVencimiento.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		datepicker_fechaGastoG.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		/*datepicker_empresaFechaSolicitada.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });*/
	}
    
    public void setSpinnersConfiguracion() {
    	SpinnerValueFactory<Integer> valueFactoryDateInicial = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0);
		SpinnerValueFactory<Integer> valueFactoryDateFinal = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0);
		spinner_empresaValorFiscalMin.setValueFactory(valueFactoryDateInicial);
		spinner_empresaValorFiscalMax.setValueFactory(valueFactoryDateFinal);
    }
    
    public void totaltransacciones(ActionEvent event) {
    	button_totalTransacciones.setDisable(true);
    	button_transaccionesPorDia.setDisable(false);
    	vbox_totalTransacciones.setVisible(true);
    	vbox_transaccionesporDia.setVisible(false);
    }
    
    public void transaccionespordia(ActionEvent event) {
    	button_totalTransacciones.setDisable(false);
    	button_transaccionesPorDia.setDisable(true);
    	vbox_totalTransacciones.setVisible(false);
    	vbox_transaccionesporDia.setVisible(true);
    }
    
    public void abrir_titledpaneNuevaPeticion(ActionEvent event) {
    	titledPane_nuevaPeticion.setVisible(true);
    }
    
    public void cerrar_titledpaneNuevaPeticion(ActionEvent event) {
    	titledPane_nuevaPeticion.setVisible(false);
    	if(button_peticionBuscarProducto.isDisable() && button_peticionBuscarProveedor.isDisable()){
    		textfield_peticionProducto.setText("");
        	textfield_peticionProveedor.setText("");;
        	spinner_peticionCantidad.getValueFactory().setValue(0);
        	textfield_peticionMonto.setText("");
        	combobox_peticionEstado.getSelectionModel().clearSelection();
        	radiobutton_peticionEfectivo.setSelected(true);
        	radiobutton_peticionCredito.setSelected(false);
        	radiobutton_peticionTarjeta.setSelected(false);
        	button_peticionBuscarProducto.setDisable(false);
        	button_peticionBuscarProveedor.setDisable(false);
    	}
    }
    
    //Abre el titledpane de productos en el area de peticiones
    public void abrir_titledpane_busquedaProductosPeticiones(ActionEvent event) {
    	ArrayList<Producto> productos = new ArrayList<>();
    	for (Estandar prod : Controladora.getInstance().getMisProductosEstandar()) {
			if(!prod.isBorrado() && !prod.isFabricado())
			{
				productos.add(prod);
			}
		}
    	for (Kit kit : Controladora.getInstance().getMisProductosKit()) {
    		if(!kit.isBorrado())
    		{
    			productos.add(kit);
    		}
		}
    	fillProductList(productos, "Peticion");
    	titledpane_busquedaProductosPeticiones.setVisible(true);
    }
    
    //Cierra el titledpane de productos en el area de peticiones
    public void cerrar_titledpane_busquedaProductosPeticiones(ActionEvent event) {
    	tableview_peticionProducto.getSelectionModel().clearSelection();
    	textfield_peticionProductoSeleccionado.clear();
    	button_peticionProductoSeleccionar.setDisable(true);
    	titledpane_busquedaProductosPeticiones.setVisible(false);
    }
    //Abre el titledpane de info adicional en el area de peticiones
    public void abrir_infoAdicionalPeticiones(ActionEvent event) {
    	titledpane_infoAdicionalPeticion.setVisible(true);
    }
  //Cierra el titledpane de info adicional en el area de peticiones
    public void cerrar_infoAdicionalPeticiones(ActionEvent event) {
    	titledpane_infoAdicionalPeticion.setVisible(false);
    }
    
    //Abre el titledpane de proveedores en el area de peticiones
    public void abrir_infoBusquedaProveedoresPeticiones(ActionEvent event) {
    	ArrayList<Proveedores> prov = Controladora.getInstance().getMisProveedores();
    	if(prov.get(0).getCodigo().equalsIgnoreCase("00"))
    	{
    		prov.remove(0);
    	}
    	fillProveedorList(prov, "Peticion");
    	titledpane_busquedaProveedorPeticiones.setVisible(true);
    }
  //Cierra el titledpane de proveedores en el area de peticiones
    public void cerrar_infoBusquedaProveedoresPeticiones(ActionEvent event) {
    	tableview_peticionProveedoresList.getSelectionModel().clearSelection();
    	titledpane_busquedaProveedorPeticiones.setVisible(false);
    }
    
    public void abrirCuentasPorPagar(ActionEvent event) {
    	boton_cuentasPorPagar.setDisable(true);
    	boton_cuentasPorCobrar.setDisable(false);
    	pane_cuentasPorPagar.setVisible(true);
    	pane_cuentasPorCobrar.setVisible(false);
    }
    
    public void abrirCuentasPorCobrar(ActionEvent event) {
    	boton_cuentasPorPagar.setDisable(false);
    	boton_cuentasPorCobrar.setDisable(true);
    	pane_cuentasPorPagar.setVisible(false);
    	pane_cuentasPorCobrar.setVisible(true);
    }
    
    public void setEditEmpleado() {
    	tablecolumn_empleadoNombre.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_empleadoNombre.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Empleado, String>>() {
					@Override
					public void handle(CellEditEvent<Empleado, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Empleado empleado = tableview_empleadoList.getSelectionModel().getSelectedItem();
							Empleado newEmpleado = new Empleado(empleado.getCodigo(), t.getNewValue(), empleado.getTelefono(), empleado.getDomicilio(), 
									empleado.getCorreo(), empleado.getRnc(), empleado.getSueldo(), empleado.getCategoria());
							
							if(Controladora.getInstance().isEmpleadoInUsuario(empleado.getCodigo())) {
								Alert alert = new Alert(AlertType.WARNING, "Empleado en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
				    			fillEmpleadoList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este empleado?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el empleado...");
					    			int index = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
					    			Controladora.getInstance().getMisEmpleados().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarEmpleado(index+1);
							
					    			Controladora.getInstance().getMisEmpleados().add(newEmpleado);
					    			Controladora.getInstance().guardarEmpleadoSQL(newEmpleado);
					    			
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getNewValue());
					    			fillEmpleadoList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
					    			fillEmpleadoList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	
    	tablecolumn_empleadoTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_empleadoTelefono.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Empleado, String>>() {
					@Override
					public void handle(CellEditEvent<Empleado, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Empleado empleado = tableview_empleadoList.getSelectionModel().getSelectedItem();
							Empleado newEmpleado = new Empleado(empleado.getCodigo(), empleado.getNombre(), t.getNewValue(), empleado.getDomicilio(), 
									empleado.getCorreo(), empleado.getRnc(), empleado.getSueldo(), empleado.getCategoria());
							
							if(Controladora.getInstance().isEmpleadoInUsuario(empleado.getCodigo())) {
								Alert alert = new Alert(AlertType.WARNING, "Empleado en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getOldValue());
				    			fillEmpleadoList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este empleado?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el empleado...");
					    			int index = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
					    			Controladora.getInstance().getMisEmpleados().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarEmpleado(index+1);
							
					    			Controladora.getInstance().getMisEmpleados().add(newEmpleado);
					    			Controladora.getInstance().guardarEmpleadoSQL(newEmpleado);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getNewValue());
					    			fillEmpleadoList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getOldValue());
					    			fillEmpleadoList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	
    	tablecolumn_empleadoDireccion.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_empleadoDireccion.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Empleado, String>>() {
					@Override
					public void handle(CellEditEvent<Empleado, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Empleado empleado = tableview_empleadoList.getSelectionModel().getSelectedItem();
							Empleado newEmpleado = new Empleado(empleado.getCodigo(), empleado.getNombre(), empleado.getTelefono(), t.getNewValue(), 
									empleado.getCorreo(), empleado.getRnc(), empleado.getSueldo(), empleado.getCategoria());
							
							if(Controladora.getInstance().isEmpleadoInUsuario(empleado.getCodigo())) {
								Alert alert = new Alert(AlertType.WARNING, "Empleado en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setDomicilio(t.getOldValue());
				    			fillEmpleadoList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este empleado?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el empleado...");
					    			int index = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
					    			Controladora.getInstance().getMisEmpleados().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarEmpleado(index+1);
							
					    			Controladora.getInstance().getMisEmpleados().add(newEmpleado);
					    			Controladora.getInstance().guardarEmpleadoSQL(newEmpleado);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setDomicilio(t.getNewValue());
					    			fillEmpleadoList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setDomicilio(t.getOldValue());
					    			fillEmpleadoList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	
    	tablecolumn_empleadoCorreo.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_empleadoCorreo.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Empleado, String>>() {
					@Override
					public void handle(CellEditEvent<Empleado, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Empleado empleado = tableview_empleadoList.getSelectionModel().getSelectedItem();
							Empleado newEmpleado = new Empleado(empleado.getCodigo(), empleado.getNombre(), empleado.getTelefono(), empleado.getDomicilio(), 
									t.getNewValue(), empleado.getRnc(), empleado.getSueldo(), empleado.getCategoria());
							
							if(Controladora.getInstance().isEmpleadoInUsuario(empleado.getCodigo())) {
								Alert alert = new Alert(AlertType.WARNING, "Empleado en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setCorreo(t.getOldValue());
				    			fillEmpleadoList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este empleado?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el empleado...");
					    			int index = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
					    			Controladora.getInstance().getMisEmpleados().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarEmpleado(index+1);
							
					    			Controladora.getInstance().getMisEmpleados().add(newEmpleado);
					    			Controladora.getInstance().guardarEmpleadoSQL(newEmpleado);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setCorreo(t.getNewValue());
					    			fillEmpleadoList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setCorreo(t.getOldValue());
					    			fillEmpleadoList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	
    	tablecolumn_empleadoRNC.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_empleadoRNC.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Empleado, String>>() {
					@Override
					public void handle(CellEditEvent<Empleado, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Empleado empleado = tableview_empleadoList.getSelectionModel().getSelectedItem();
							Empleado newEmpleado = new Empleado(empleado.getCodigo(), empleado.getNombre(), empleado.getTelefono(), empleado.getDomicilio(), 
									empleado.getCorreo(), t.getNewValue(), empleado.getSueldo(), empleado.getCategoria());
							
							if(Controladora.getInstance().isEmpleadoInUsuario(empleado.getCodigo())) {
								Alert alert = new Alert(AlertType.WARNING, "Empleado en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
				    			fillEmpleadoList(null, "");
							}
							else if(Controladora.getInstance().empleadoRNCExists(newEmpleado)) {
								Alert alert = new Alert(AlertType.WARNING, "RNC en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
				    			fillEmpleadoList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este empleado?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el empleado...");
					    			int index = Controladora.getInstance().getMisEmpleados().indexOf(empleado);
					    			Controladora.getInstance().getMisEmpleados().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarEmpleado(index+1);
							
					    			Controladora.getInstance().getMisEmpleados().add(newEmpleado);
					    			Controladora.getInstance().guardarEmpleadoSQL(newEmpleado);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getNewValue());
					    			fillEmpleadoList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
					    			fillEmpleadoList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	
    }
    
    public void setEditRubros() {
    	tablecolumn_rubroNombre.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_rubroNombre.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Rubro, String>>() {
					@Override
					public void handle(CellEditEvent<Rubro, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Rubro rubro = tableview_rubro.getSelectionModel().getSelectedItem();
							Rubro newRubro = new Rubro(rubro.getCodigo(), t.getNewValue());
							
							if(Controladora.getInstance().isRubroInProduct(rubro)) {
								Alert alert = new Alert(AlertType.WARNING, "Empleado en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombreRubro(t.getOldValue());
								fillRubroList(null);
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este rubro?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el rubro...");
					    			int index = Controladora.getInstance().getMisRubros().indexOf(rubro);
					    			Controladora.getInstance().getMisRubros().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarRubro(index+1);
							
					    			Controladora.getInstance().getMisRubros().add(newRubro);
					    			Controladora.getInstance().guardarRubroSQL(newRubro);;
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombreRubro(t.getNewValue());
									fillRubroList(null);
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombreRubro(t.getOldValue());
									fillRubroList(null);
					    		}
							}
						}
						
					}
    		    }
    		);
    }
    
    public void setEditAtributos() {
    	tablecolumn_atributonombre.setCellFactory(TextFieldTableCell.forTableColumn());
    	
    }
    
    public void setEditClientes() {
    	tablecolumn_clienteNombre.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_clienteNombre.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Cliente, String>>() {
					@Override
					public void handle(CellEditEvent<Cliente, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Cliente cliente = tableview_clientesList.getSelectionModel().getSelectedItem();
							Cliente newCliente = new Cliente(cliente.getCodigo(), t.getNewValue(), cliente.getTelefono(), cliente.getTipoCliente(), 
									cliente.getCumpleanos(), cliente.getRnc());

							if(Controladora.getInstance().isClienteInFactura(cliente)) {
								Alert alert = new Alert(AlertType.WARNING, "Cliente en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
								fillClientList(null);
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el cliente...");
					    			int index = Controladora.getInstance().getMisClientes().indexOf(cliente);
					    			Controladora.getInstance().getMisClientes().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarCliente(index+1);
							
					    			Controladora.getInstance().getMisClientes().add(newCliente);
					    			Controladora.getInstance().guardarClienteSQL(newCliente);;
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getNewValue());
									fillClientList(null);
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
									fillClientList(null);
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_clienteTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_clienteTelefono.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Cliente, String>>() {
					@Override
					public void handle(CellEditEvent<Cliente, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Cliente cliente = tableview_clientesList.getSelectionModel().getSelectedItem();
							Cliente newCliente = new Cliente(cliente.getCodigo(), cliente.getNombre(), t.getNewValue(), cliente.getTipoCliente(), 
									cliente.getCumpleanos(), cliente.getRnc());

							if(Controladora.getInstance().isClienteInFactura(cliente)) {
								Alert alert = new Alert(AlertType.WARNING, "Cliente en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getOldValue());
								fillClientList(null);
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el cliente...");
					    			int index = Controladora.getInstance().getMisClientes().indexOf(cliente);
					    			Controladora.getInstance().getMisClientes().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarCliente(index+1);
							
					    			Controladora.getInstance().getMisClientes().add(newCliente);
					    			Controladora.getInstance().guardarClienteSQL(newCliente);;
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getNewValue());
									fillClientList(null);
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getOldValue());
									fillClientList(null);
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_clienteRNC.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_clienteRNC.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Cliente, String>>() {
					@Override
					public void handle(CellEditEvent<Cliente, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Cliente cliente = tableview_clientesList.getSelectionModel().getSelectedItem();
							Cliente newCliente = new Cliente(cliente.getCodigo(), cliente.getNombre(), cliente.getTelefono(), cliente.getTipoCliente(), 
									cliente.getCumpleanos(), t.getNewValue());

							if(Controladora.getInstance().isClienteInFactura(cliente)) {
								Alert alert = new Alert(AlertType.WARNING, "Cliente en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
								fillClientList(null);
							}
							else if(Controladora.getInstance().clienteRNCExists(newCliente)) {
								Alert alert = new Alert(AlertType.WARNING, "RNC en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
								fillClientList(null);
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el cliente...");
					    			int index = Controladora.getInstance().getMisClientes().indexOf(cliente);
					    			Controladora.getInstance().getMisClientes().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarCliente(index+1);
							
					    			Controladora.getInstance().getMisClientes().add(newCliente);
					    			Controladora.getInstance().guardarClienteSQL(newCliente);;
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getNewValue());
									fillClientList(null);
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
									fillClientList(null);
					    		}
							}
						}
						
					}
    		    }
    		);
    }
    
    public void setEditProveedores() {
    	tablecolumn_proveedorNombre.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_proveedorNombre.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Proveedores, String>>() {
					@Override
					public void handle(CellEditEvent<Proveedores, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
							Proveedores newProveedor = new Proveedores(proveedor.getCodigo(), t.getNewValue(), proveedor.getTelefono(), proveedor.getDomicilio(),
									proveedor.getCorreo(), proveedor.getRnc(), null, proveedor.getSitioWeb());
							
							if(Controladora.getInstance().isProveedorInProducto(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "Proveedor en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
								fillProveedorList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el proveedor...");
					    			int index = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
					    			Controladora.getInstance().getMisProveedores().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarProveedor(index+1);
					    			
					    			Controladora.getInstance().getMisProveedores().add(newProveedor);
					    			Controladora.getInstance().guardarProveedorSQL(newProveedor);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getNewValue());
					    			fillProveedorList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNombre(t.getOldValue());
					    			fillProveedorList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_proveedorRNC.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_proveedorRNC.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Proveedores, String>>() {
					@Override
					public void handle(CellEditEvent<Proveedores, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
							Proveedores newProveedor = new Proveedores(proveedor.getCodigo(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getDomicilio(),
									proveedor.getCorreo(), t.getNewValue(), null, proveedor.getSitioWeb());
							
							if(Controladora.getInstance().isProveedorInProducto(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "Proveedor en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
								fillProveedorList(null, "");
							}
							else if(Controladora.getInstance().proveedorRNCExists(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "RNC en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
								fillProveedorList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el proveedor...");
					    			int index = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
					    			Controladora.getInstance().getMisProveedores().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarProveedor(index+1);
					    			
					    			Controladora.getInstance().getMisProveedores().add(newProveedor);
					    			Controladora.getInstance().guardarProveedorSQL(newProveedor);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getNewValue());
					    			fillProveedorList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setRnc(t.getOldValue());
					    			fillProveedorList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_proveedorTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_proveedorTelefono.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Proveedores, String>>() {
					@Override
					public void handle(CellEditEvent<Proveedores, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
							Proveedores newProveedor = new Proveedores(proveedor.getCodigo(), proveedor.getNombre(), t.getNewValue(), proveedor.getDomicilio(),
									proveedor.getCorreo(), proveedor.getRnc(), null, proveedor.getSitioWeb());
							
							if(Controladora.getInstance().isProveedorInProducto(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "Proveedor en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getOldValue());
								fillProveedorList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el proveedor...");
					    			int index = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
					    			Controladora.getInstance().getMisProveedores().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarProveedor(index+1);
					    			
					    			Controladora.getInstance().getMisProveedores().add(newProveedor);
					    			Controladora.getInstance().guardarProveedorSQL(newProveedor);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getNewValue());
					    			fillProveedorList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setTelefono(t.getOldValue());
					    			fillProveedorList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_proveedorDomicilio.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_proveedorDomicilio.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Proveedores, String>>() {
					@Override
					public void handle(CellEditEvent<Proveedores, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
							Proveedores newProveedor = new Proveedores(proveedor.getCodigo(), proveedor.getNombre(), proveedor.getTelefono(), t.getNewValue(),
									proveedor.getCorreo(), proveedor.getRnc(), null, proveedor.getSitioWeb());
							
							if(Controladora.getInstance().isProveedorInProducto(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "Proveedor en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setDomicilio(t.getOldValue());
								fillProveedorList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el proveedor...");
					    			int index = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
					    			Controladora.getInstance().getMisProveedores().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarProveedor(index+1);
					    			
					    			Controladora.getInstance().getMisProveedores().add(newProveedor);
					    			Controladora.getInstance().guardarProveedorSQL(newProveedor);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setDomicilio(t.getNewValue());
					    			fillProveedorList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setDomicilio(t.getOldValue());
					    			fillProveedorList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_proveedorCorreo.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_proveedorCorreo.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Proveedores, String>>() {
					@Override
					public void handle(CellEditEvent<Proveedores, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
							Proveedores newProveedor = new Proveedores(proveedor.getCodigo(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getDomicilio(),
									t.getNewValue(), proveedor.getRnc(), null, proveedor.getSitioWeb());
							
							if(Controladora.getInstance().isProveedorInProducto(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "Proveedor en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setCorreo(t.getOldValue());
								fillProveedorList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el proveedor...");
					    			int index = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
					    			Controladora.getInstance().getMisProveedores().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarProveedor(index+1);
					    			
					    			Controladora.getInstance().getMisProveedores().add(newProveedor);
					    			Controladora.getInstance().guardarProveedorSQL(newProveedor);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setCorreo(t.getNewValue());
					    			fillProveedorList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setCorreo(t.getOldValue());
					    			fillProveedorList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    	
    	tablecolumn_proveedorSitioWeb.setCellFactory(TextFieldTableCell.forTableColumn());
    	tablecolumn_proveedorSitioWeb.setOnEditCommit(
    		    new EventHandler<CellEditEvent<Proveedores, String>>() {
					@Override
					public void handle(CellEditEvent<Proveedores, String> t) {
						if(!t.getOldValue().equalsIgnoreCase(t.getNewValue())) {
							Proveedores proveedor = tableview_proveedoresList.getSelectionModel().getSelectedItem();
							Proveedores newProveedor = new Proveedores(proveedor.getCodigo(), proveedor.getNombre(), proveedor.getTelefono(), proveedor.getDomicilio(),
									proveedor.getCorreo(), proveedor.getRnc(), null, t.getNewValue());
							
							if(Controladora.getInstance().isProveedorInProducto(proveedor)) {
								Alert alert = new Alert(AlertType.WARNING, "Proveedor en uso.");
								alert.showAndWait();
								t.getTableView().getItems().get(t.getTablePosition().getRow()).setSitioWeb(t.getOldValue());
								fillProveedorList(null, "");
							}
							else {
								Alert alert = new Alert(AlertType.CONFIRMATION, "Desea modificar este cliente?", ButtonType.YES, ButtonType.NO);
					    		alert.showAndWait();
					    		if(alert.getResult() == ButtonType.YES) {
					    			System.out.println("Cambie el proveedor...");
					    			int index = Controladora.getInstance().getMisProveedores().indexOf(proveedor);
					    			Controladora.getInstance().getMisProveedores().get(index).setBorrado(true);
					    			Controladora.getInstance().borrarProveedor(index+1);
					    			
					    			Controladora.getInstance().getMisProveedores().add(newProveedor);
					    			Controladora.getInstance().guardarProveedorSQL(newProveedor);
								
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setSitioWeb(t.getNewValue());
					    			fillProveedorList(null, "");
					    		}
					    		else {
					    			t.getTableView().getItems().get(t.getTablePosition().getRow()).setSitioWeb(t.getOldValue());
					    			fillProveedorList(null, "");
					    		}
							}
						}
						
					}
    		    }
    		);
    }
    
    public void fillAtributosSearchBar() {
    	listView_grupoAtributos.getItems().clear();
    	listView_grupoAtributos.getItems().add("Todos");
    	for(GrupoAtributo grupoAtributo : Controladora.getInstance().getMisGrupoAtributo()) {
    		if(!listView_grupoAtributos.getItems().contains(grupoAtributo.getNombre()))
        	{
        		listView_grupoAtributos.getItems().add(grupoAtributo.getNombre());
        	}
    	}
    }
}
