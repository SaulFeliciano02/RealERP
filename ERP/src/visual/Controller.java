package visual;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import archivos.Archivos;
import basededatos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
//import jdk.nashorn.internal.ir.SetSplitState;
import logico.Atributos;
import logico.CantBienesYServiciosUtilizados;
import logico.CantKitsUtilizados;
import logico.CantProductosUtilizados;
import logico.CategoriaEmpleado;
import logico.Cliente;
import logico.Combinaciones;
import logico.Controladora;
import logico.CostoDirecto;
import logico.CostoIndirecto;
import logico.CostoIndirectoProducto;
import logico.Empleado;
import logico.Empresa;
import logico.Estandar;
import logico.Factura;
import logico.GastoGeneral;
import logico.GrupoAtributo;
import logico.Kit;
import logico.Producto;
import logico.Promocion;
import logico.Proveedores;
import logico.Rubro;
import logico.Servicio;
import logico.ServicioUtilizado;
import logico.UnidadMedida;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.poi.hssf.usermodel.*;

public class Controller implements Initializable{
	
	
	/**VARIABLES DE LA PANTALLA PRINCIPAL**/
	@FXML private Pane mainPane;
    @FXML private HBox box_Principal;

    @FXML private Button selected_Gastos;
    @FXML private Button selected_Principal;
    @FXML private Button selected_ventas;
    @FXML private Button selected_productos;
    @FXML private Button selected_help;
    @FXML private Button selected_rh;
    @FXML private Button selected_historial;
    @FXML private Button selected_admin;
    @FXML private Button selected_config;

    @FXML private Button button_gastos;
    @FXML private Button button_principal;
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
    @FXML private TextField textfield_nombreCategoriaEmp;
    @FXML private TextField textfield_salarioCategoriaEmp;
    @FXML private Button button_guardarCategoriaEmp;
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
    
    //CONFIGURACION
    @FXML private AnchorPane pane_nuevoUsuario;
    @FXML private TitledPane titledpane_busquedaEmpleadoUsuario;
    
    @FXML private TextField textfield_empresaNombre;
    @FXML private TextField textfield_empresaRNC;
    @FXML private TextArea textarea_empresaDomicilio;
    @FXML private TextField textfield_empresaTelefono;
    
    @FXML private Spinner<Integer> spinner_empresaValorFiscalMin;
    @FXML private Spinner<Integer> spinner_empresaValorFiscalMax;
    
    @FXML private DatePicker datepicker_empresaFechaInicio;
    @FXML private DatePicker datepicker_empresaFechaFinal;
    
    @FXML private Button button_empresaGuardar;
    
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
	
    public void principal_pressed(ActionEvent event){
    	
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

    	
    }
    
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	//button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(false);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(true);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    	
    }
    
    public void productos_pressed(ActionEvent event){
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	//button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(false);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(true);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    }
    
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	//button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(false);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(true);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    	
    }
    
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	//button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(false);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(true);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);

    }
    
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	//button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(false);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	//button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(false);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(true);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(false);
    	
    }
    
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	//button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	pane_Config.setDisable(false);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	pane_Config.setVisible(true);
    	
    }
    
    
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
    	
    	selected_Principal.setVisible(false);
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
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_gastos.setEffect(nonpressed_gastos1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	//button_help.setEffect(nonpressed_help1); 
    	button_config.setEffect(nonpressed_config1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Gastos.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(false);
    	pane_Config.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Gastos.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(true);
    	pane_Config.setVisible(false);

    	
    }
    
  /*  public void config_pressed(ActionEvent event){
    	
    	    	text_menuName.setVisible(false);
    	    	text_menuOptions.setVisible(true);
    	    	text_menuOptions.setText("Configuración");
    	    	
    	    	pane_Config.setDisable(false);
    	    	pane_Config.setVisible(true);
    } */
    
    public void config_close(ActionEvent event){
    	text_menuName.setVisible(true);
    	text_menuOptions.setVisible(false);
    	text_menuOptions.setText("Configuración");
    	
    	pane_Config.setDisable(true);
    	pane_Config.setVisible(false);
    	
    }
    
    public void activar_nuevoAtributo(KeyEvent event) {
    	if(!textfield_register_familia.getText().isEmpty() && !textfield_registrar_atributo.getText().isEmpty()) {
    		button_agregar_atributo.setDisable(false);
    	}
    	else {
    		button_agregar_atributo.setDisable(true);
    	}
    }
    
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
        			if(encontrado.getDescripcion() != null)
        			{
        				textarea_descricionGastoG.setText(encontrado.getDescripcion());
        			}
        			datepicker_fechaGastoG.setValue(encontrado.getRemodelado());
        			break;
        		}
        	}
        	
        	textfield_nombreGastoG.setDisable(true);
    		textfield_montoGastoG.setDisable(true);
    		textarea_descricionGastoG.setDisable(true);
    		datepicker_fechaGastoG.setDisable(true);
    		button_modificarGastoG.setDisable(false);
        	button_eliminarGastoG.setDisable(false);
    	}
    }
    
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
    	//pane_rubroCreate.setDisable(true);
    	button_agregar_atributo.setDisable(true);
    }
    
    public void pressed_nuevoGastoGeneral(ActionEvent event)
    {
    	if (!textfield_nombreGastoG.isDisabled() && !textfield_montoGastoG.isDisabled() && !textarea_descricionGastoG.isDisabled() && !datepicker_fechaGastoG.isDisabled())
    	{
    		String nombre = textfield_nombreGastoG.getText();
        	float monto = Float.parseFloat(textfield_montoGastoG.getText());
        	String descripcion = textarea_descricionGastoG.getText();
        	LocalDate fecha = datepicker_fechaGastoG.getValue();
        	
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
        	
    	}
    }
    
    public void pressed_exportarInventarioExcel(ActionEvent event)
    {
    	Archivos.carpeta();
		
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Desea exportar su inventario al archivo c:/ERPdata/data/inventario.xls?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    		//File archivo = new File("c:/ERPdata/data/inventario.csv");
    		
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
	    			i4++;
    			}
    			i3++;
			}
    		
    		int o;
    		for(o=0; o < titulosEstandar.size()+Controladora.getInstance().getMisGastosGenerales().size(); o++)
    		{
    			hoja1.autoSizeColumn(o);
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
    
    public void pressed_modificarGastoGeneral(ActionEvent event)
    {
    	if(textfield_nombreGastoG.isDisabled() && textfield_montoGastoG.isDisabled() && textarea_descricionGastoG.isDisabled() && datepicker_fechaGastoG.isDisabled())
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
    	}
    }
    /*public void activarGuardarGastoG(KeyEvent event) // HACER UN WARNING QUE APAREZCA CUANDO INTENTA REGISTRAR UN GASTOGENERAL SIN NOMBRE, SIN MONTO O SIN FECHA
    {
    	String nombre = textfield_nombreGastoG.getText();
    	String monto = textfield_montoGastoG.getText();
    	LocalDate fecha = datepicker_fechaGastoG.getValue();
    	
    	if(nombre!=null || monto!=null || fecha!=null)
    	{
    		Dialog<String> dialog = new Dialog<>();
    		 dialog.getDialogPane().getButtonTypes().add(loginButtonType);
    		 boolean disabled = false; // computed based on content of text fields, for example
    		 dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
    	}
    }*/
    
    public void pressed_modificarAtributoOGrupo(ActionEvent event) {
    	//if()
    }
    
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
        		fillProductList(null);
        	}
    	}
    	
    	//Agregar acá un reload
    }
    
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
			//SI CIERRO LA VENTANA DE REGISTROS DE CLIENTES CIERRO LA PRINCIPAL Y LA VUELVO A ABRIR
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
			  		 
			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.rh_pressed(null);
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
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));

			  		    Parent root = f.load();
			  		    Controller c = f.getController();
			  		    c.rh_pressed(null);
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
    //Verifica si el input de un textfield es un numero
    public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    }
    
    //Cierra la venta de nuevoProducto
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    
    //Busqueda de clientes
    public void buscarClientes(KeyEvent event) {
    	ArrayList<Cliente> clientes = new ArrayList<>();
    	if(Character.isLetter(event.getCharacter().charAt(0))) {
    		clientes = Controladora.getInstance().searchClientes(textfield_clienteBusqueda.getText().toLowerCase() + event.getCharacter(), "Nombre");
    	}
    	else {
    		clientes = Controladora.getInstance().searchClientes(textfield_clienteBusqueda.getText().toLowerCase(), "Nombre");
    	}
    	//System.out.println(clientes.size());
    	//System.out.println(textfield_clienteBusqueda.getText().toLowerCase());
    	if(clientes.size() == 0) {
    		fillClientList(null);
    	}
    	else {
    		fillClientList(clientes);
    	}
    }
    
    public void buscarProductos(KeyEvent event) {
    	ArrayList<Producto> productos = new ArrayList<>();
    	if(Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
    		productos = Controladora.getInstance().searchProducts(textfield_productBuscar.getText().toLowerCase() + event.getCharacter(), combobox_productBuscar.getSelectionModel().getSelectedItem());
    	}
    	else {
    		productos = Controladora.getInstance().searchProducts(textfield_productBuscar.getText().toLowerCase(), combobox_productBuscar.getSelectionModel().getSelectedItem());
    	}
    	if(productos.size() == 0) {
    		fillProductList(null);
    	}
    	else {
    		fillProductList(productos);
    	}
    }
    
    //Busqueda de proveedores
    public void buscarProveedores(KeyEvent event) {
    	ArrayList<Proveedores> proveedores = new ArrayList<>();
    	if(Character.isLetter(event.getCharacter().charAt(0))) {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_proveedorBusqueda.getText().toLowerCase() + event.getCharacter(), "Nombre");
    	}
    	else {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_proveedorBusqueda.getText().toLowerCase(), "Nombre");
    	}
    	if(proveedores.size() == 0) {
    		fillProveedorList(null);
    	}
    	else {
    		fillProveedorList(proveedores);
    	}
    }
    
    //FUNCIONES PARA CREAR RUBROS
    
    public void activarRegistro(ActionEvent event) {
    	pane_rubroCreate.setDisable(false);
    	button_rubroGuardar.setDisable(true);
    }
    
    public void activarGuardarRubro(KeyEvent event) {
    	if(textfield_rubroCodigo.getLength() > 0 && textfield_rubroNombre.getLength() > 0) {
    		button_rubroGuardar.setDisable(false);
    	}
    	else {
    		button_rubroGuardar.setDisable(true);
    	}
    }
    
    public void guardarRubro(ActionEvent event) {
    	ObservableList<Rubro> data = FXCollections.observableArrayList();
    	String codigo = textfield_rubroCodigo.getText();
    	String nombre = textfield_rubroNombre.getText();
    	Rubro rubro = new Rubro(codigo, nombre);
    	data.add(rubro);
    	Controladora.getInstance().getMisRubros().add(rubro);
    	Controladora.getInstance().guardarRubroSQL(rubro);
    	tablecolumn_rubroCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_rubroNombre.setCellValueFactory(new PropertyValueFactory<>("nombreRubro"));
    	tableview_rubro.getItems().add(rubro);
    	tableview_rubro.refresh();
    	textfield_rubroCodigo.setText("");
    	textfield_rubroNombre.setText("");
    	pane_rubroCreate.setDisable(true);
    }
    
    public void rubroTableViewClicked(MouseEvent event) {
    	System.out.print("klk");
    	if(!tableview_rubro.getSelectionModel().isEmpty()) {
    		button_rubroEliminar.setDisable(false);
    	}
    	else {
    		button_rubroEliminar.setDisable(true);
    	}
    }
    
    public void eliminarRubro(ActionEvent event) {
    	int index = tableview_rubro.getSelectionModel().getSelectedIndex();
    	tableview_rubro.getItems().remove(index);
    }
    
    public void eliminarAtributo(ActionEvent event) {
    	int index = tableView_Atributos.getSelectionModel().getSelectedIndex();
    	tableView_Atributos.getItems().remove(index);
    }

    
    public void eliminarCliente(ActionEvent event) {
    	int index = tableview_clientesList.getSelectionModel().getSelectedIndex();
    	tableview_clientesList.getItems().remove(index);
    }

    
    public void eliminarProveedor(ActionEvent event) {
    	int index = tableview_proveedoresList.getSelectionModel().getSelectedIndex();
    	tableview_proveedoresList.getItems().remove(index);
    }
    

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
    	}
    }
    
    public void guardarEmpresa(ActionEvent event) {
    	String nombre = "";
    	String rnc = "";
    	String telefono = "";
    	String domicilio = "";
    	int valorFiscalInferior = spinner_empresaValorFiscalMin.getValue();
    	int valorFiscalMayor = spinner_empresaValorFiscalMax.getValue();
    	LocalDate fechaInicio = null;
    	LocalDate fechaFinal = null;
    	try {
    		nombre = textfield_empresaNombre.getText();
    		rnc = textfield_empresaRNC.getText();
    		domicilio = textarea_empresaDomicilio.getText();
    		telefono = textfield_empresaTelefono.getText();
    		fechaInicio = datepicker_empresaFechaInicio.getValue();
    		fechaFinal = datepicker_empresaFechaFinal.getValue();
    	}catch(NullPointerException e) {
    		
    	}
    	
    	Empresa empresa = new Empresa(nombre, rnc, telefono, domicilio, valorFiscalInferior, valorFiscalMayor, null, null, fechaInicio, fechaFinal);
    	Controladora.getInstance().setMiEmpresa(empresa);
    	
    	Controladora.getInstance().guardarInfoEmpresaSQL(empresa);
    	Controladora.getInstance().guardarAnioFiscal(empresa);
    	Controladora.getInstance().guardarRangoNumerosValorFiscal(empresa);
    	
    	text_negocioName.setText(nombre);
    	
    	textfield_empresaNombre.setText("");
    	textfield_empresaRNC.setText("");
    	textfield_empresaTelefono.setText("");
    	textarea_empresaDomicilio.setText("");
    	spinner_empresaValorFiscalMin.getValueFactory().setValue(0);
    	spinner_empresaValorFiscalMax.getValueFactory().setValue(0);
    	datepicker_empresaFechaInicio.setValue(null);
    	datepicker_empresaFechaFinal.setValue(null);
    	
   
    }

	

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	//Seteando los clientes
    	fillClientList(null);
    	
    	//Seteando los proveedores
    	fillProveedorList(null);
    	
    	//Seteando los empleados
    	fillEmpleadoList(null);
    	
    	//Seteando los rubros
    	fillRubroList(null);
    	
    	//Seteando el combobox de productos
    	ObservableList<String> combobox_data = FXCollections.observableArrayList();
    	combobox_data.addAll("Codigo", "Nombre", "Proveedor", "Rubro");
    	combobox_productBuscar.setItems(combobox_data);
    	fillProductList(null);
    	
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
    }
    
    public void fillRubroList(ArrayList<Rubro> r) {
    	ObservableList<Rubro> data = FXCollections.observableArrayList();
    	if(r == null) {
    		data.addAll(Controladora.getInstance().getMisRubros());
		}
    	else {
    		data.addAll(r);
    	}
    	tablecolumn_rubroCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_rubroNombre.setCellValueFactory(new PropertyValueFactory<>("nombreRubro"));
    	tableview_rubro.setItems(data);
    	tableview_rubro.refresh();
    }
    
    public void fillGastosGenerales(ArrayList<GastoGeneral> g)
    {
    	int i;
    	
    	for(i = 0; i < Controladora.getInstance().getMisGastosGenerales().size(); i++)
    	{
    		listview_gastosG.getItems().add(Controladora.getInstance().getMisGastosGenerales().get(i).getNombre());
    	}
    }
    
    public void fillProductList(ArrayList<Producto> p) {
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
    
    public void fillAtributesList(ArrayList<Atributos> a) {
    	ObservableList<Atributos> data = FXCollections.observableArrayList();
    	if(a == null) {
    		data.addAll(Controladora.getInstance().getMisAtributos());
    	}
    	else {
    		data.addAll(a);
    	}
		tablecolumn_atributogrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
    	tablecolumn_atributonombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tableView_atributos.setItems(data);
    	tableView_atributos.refresh();
    }
    
    public void fillClientList(ArrayList<Cliente> c) {
    	ObservableList<Cliente> data = FXCollections.observableArrayList();
    	if(c == null) {
    		data.addAll(Controladora.getInstance().getMisClientes());
    	}
    	else {
    		data.addAll(c);
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
    
    public void fillProveedorList(ArrayList<Proveedores> p) {
    	ObservableList<Proveedores> data = FXCollections.observableArrayList();
    	if(p == null) {
    		data.addAll(Controladora.getInstance().getMisProveedores());
    	}
    	else {
    		data.addAll(p);
    	}
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
    
    public void fillEmpleadoList(ArrayList<Empleado> e) {
    	ObservableList<Empleado> data = FXCollections.observableArrayList();
    	if(e == null) {
    		data.addAll(Controladora.getInstance().getMisEmpleados());
    	}
    	else {
    		data.addAll(e);
    	}
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
    
    public void fillCategoriaEmpleado() {
    	ObservableList<CategoriaEmpleado> dataC = FXCollections.observableArrayList();
    	if(Controladora.getInstance().getMisCategoriasEmpleado().size() > 0) {
    		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
    			dataC.add(c);
    		}
    		tablecolumn_NombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		tablecolumn_SueldoCategoria.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
    		tableview_CategoriaEmp.setItems(dataC);
    		tableview_CategoriaEmp.refresh();
    	}
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
    }
    
    public void selectTabCliente() {
    	tabpane_recursosHumanos.getSelectionModel().select(tab_clientes);
    }
    
    public void selectTabProveedor() {
    	tabpane_recursosHumanos.getSelectionModel().select(tab_proveedores);;
    }
    
    public void selectTabEmpleado() {
    	tabpane_recursosHumanos.getSelectionModel().select(tab_empleados);;
    }
    
    public void selectTabFacturas() {
    	tabpane_Ventas.getSelectionModel().select(tab_facturacion);;
    }
    
    public void selectTabPromocion() {
    	tabpane_Ventas.getSelectionModel().select(tab_promocion);;
    }
    
    public void abrirInfoAdicionalProducto(ActionEvent event) {
    	pane_InfoAdicionalProducto.setVisible(true);
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
    				tablecolumn_productoPartidaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
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
    	if(!tableview_productList.getSelectionModel().isEmpty()) {
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
    	tablecolumn_facturaCliente.setCellValueFactory(new PropertyValueFactory<>("clienteCodigo"));
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
    	pane_nuevoUsuario.setDisable(false);
    }
    
    public void habilitarBusquedaEmpleadoUsuario(ActionEvent event) {
    	titledpane_busquedaEmpleadoUsuario.setVisible(true);
    }
    
    public void cerrarBusquedaEmpleadoUsuario(ActionEvent event) {
    	titledpane_busquedaEmpleadoUsuario.setVisible(false);
    }
    
    public void tableviewFacturaClicked(MouseEvent event) {
    	if(!tableview_facturaList.getSelectionModel().isEmpty()) {
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
    
    public void setDatePickersConfiguracion() {
		datepicker_empresaFechaInicio.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		datepicker_empresaFechaFinal.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
	}
    
    public void setSpinnersConfiguracion() {
    	SpinnerValueFactory<Integer> valueFactoryDateInicial = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0);
		SpinnerValueFactory<Integer> valueFactoryDateFinal = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999999999, 0);
		spinner_empresaValorFiscalMin.setValueFactory(valueFactoryDateInicial);
		spinner_empresaValorFiscalMax.setValueFactory(valueFactoryDateFinal);
    }
    
}
