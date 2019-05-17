package visual;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logico.Cliente;
import logico.Controladora;
import logico.CostoDirecto;
import logico.CostoIndirecto;
import logico.CostoIndirectoProducto;
import logico.Proveedores;
import logico.Rubro;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Controller implements Initializable{
	
	/**VARIABLES DE LA PANTALLA PRINCIPAL**/
    @FXML private HBox box_Principal;

    @FXML private Button selected_Inventario;
    @FXML private Button selected_Principal;
    @FXML private Button selected_ventas;
    @FXML private Button selected_productos;
    @FXML private Button selected_help;
    @FXML private Button selected_rh;
    @FXML private Button selected_historial;
    @FXML private Button selected_admin;

    @FXML private Button button_inventario;
    @FXML private Button button_principal;
    @FXML private Button button_ventas;
    @FXML private Button button_productos;
    @FXML private Button button_help;
    @FXML private Button button_rh;
    @FXML private Button button_historial;
    @FXML private Button button_admin;
    @FXML private Button button_nuevoProducto;
    @FXML private Button button_modificarProducto;
    @FXML private Button button_eliminarProducto;
    @FXML private Button button_nuevoCliente;
    @FXML private Button button_modificarCliente;
    @FXML private Button button_eliminarCliente;
    @FXML private Button button_nuevoProveedor;
    @FXML private Button button_modificarProveedor;
    @FXML private Button button_eliminarProveedor;
    @FXML private Button button_nuevoVendedor;
    @FXML private Button button_modificarVendedor;
    @FXML private Button button_eliminarVendedor;


    @FXML private AnchorPane menuPane;
    @FXML private AnchorPane bodyPane;
    
    @FXML private Pane pane_Principal;
    @FXML private Pane pane_Productos;
    @FXML private Pane pane_Inventario;
    @FXML private Pane pane_Ventas;
    @FXML private Pane pane_Historial;
    @FXML private Pane pane_rh;
    @FXML private Pane pane_Admin;
    @FXML private Pane pane_Ayuda;

    @FXML private MenuBar menuBar;

    @FXML private ToggleGroup toggleMenu;
    
    @FXML private Text text_menuName;

    Image pressed_principal = new Image(getClass().getResourceAsStream("images/buttons/selected_button_principal.png"));
	Image pressed_inventario = new Image(getClass().getResourceAsStream("images/buttons/selected_button_inventario.png"));
	Image pressed_productos = new Image(getClass().getResourceAsStream("images/buttons/selected_button_productos.png"));
	Image pressed_ventas = new Image(getClass().getResourceAsStream("images/buttons/selected_button_ventas.png"));
	Image pressed_historial = new Image(getClass().getResourceAsStream("images/buttons/selected_button_historial.png"));
	Image pressed_rh = new Image(getClass().getResourceAsStream("images/buttons/selected_button_rh.png"));
	Image pressed_admin = new Image(getClass().getResourceAsStream("images/buttons/selected_button_admin.png"));
	Image pressed_help = new Image(getClass().getResourceAsStream("images/buttons/selected_button_help.png"));
	
	Image nonpressed_principal = new Image(getClass().getResourceAsStream("images/buttons/button_principal.png"));
	Image nonpressed_inventario = new Image(getClass().getResourceAsStream("images/buttons/button_inventario.png"));
	Image nonpressed_productos = new Image(getClass().getResourceAsStream("images/buttons/button_productos.png"));
	Image nonpressed_ventas = new Image(getClass().getResourceAsStream("images/buttons/button_ventas.png"));
	Image nonpressed_historial = new Image(getClass().getResourceAsStream("images/buttons/button_historial.png"));
	Image nonpressed_rh = new Image(getClass().getResourceAsStream("images/buttons/button_rh.png"));
	Image nonpressed_admin = new Image(getClass().getResourceAsStream("images/buttons/button_admin.png"));
	Image nonpressed_help = new Image(getClass().getResourceAsStream("images/buttons/button_help.png"));
	
	Image nonclicked_nuevoProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_nuevo.png"));
	Image nonclicked_modificarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_modificar.png"));
	Image nonclicked_eliminarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_eliminar.png"));
	
	Image clicked_nuevoProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_nuevo_clicked.png"));
	Image clicked_modificarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_modificar_clicked.png"));
	Image clicked_eliminarProducto = new Image(getClass().getResourceAsStream("images/buttons/producto_eliminar_clicked.png"));
	
	ImageInput pressed_principal1 = new ImageInput(); 
	ImageInput pressed_inventario1 = new ImageInput(); 
	ImageInput pressed_productos1 = new ImageInput(); 
	ImageInput pressed_ventas1 = new ImageInput(); 
	ImageInput pressed_historial1 = new ImageInput();
	ImageInput pressed_rh1 = new ImageInput(); 
	ImageInput pressed_admin1 = new ImageInput(); 
	ImageInput pressed_help1 = new ImageInput();
	
	ImageInput nonpressed_principal1 = new ImageInput(); 
	ImageInput nonpressed_inventario1 = new ImageInput(); 
	ImageInput nonpressed_productos1 = new ImageInput(); 
	ImageInput nonpressed_ventas1 = new ImageInput(); 
	ImageInput nonpressed_historial1 = new ImageInput(); 
	ImageInput nonpressed_rh1 = new ImageInput(); 
	ImageInput nonpressed_admin1 = new ImageInput(); 
	ImageInput nonpressed_help1 = new ImageInput(); 
	
	ImageInput clicked_nuevoProducto1 = new ImageInput(); 
	ImageInput clicked_modificarProducto1 = new ImageInput(); 
	ImageInput clicked_eliminarProducto1 = new ImageInput(); 
	
	ImageInput nonclicked_nuevoProducto1 = new ImageInput(); 
	ImageInput nonclicked_modificarProducto1 = new ImageInput(); 
	ImageInput nonclicked_eliminarProducto1 = new ImageInput();
	/**FIN VARIABLE PANTALLA PRINCIPAL**/
	
	
	/**VARIABLES PARA CREAR PRODUCTOS**/
	
	//GENERAL
	@FXML private TextField exAct;
	@FXML private TextField exMin;
	@FXML private TextField exMax;
	@FXML private Button button_productCancel;
	
	//PARTIDA
	@FXML private Tab tab_partida;
	@FXML private ListView<String> listview_partida = new ListView<>();
	@FXML private ListView<String> listview_partidaSelect = new ListView<>();
	@FXML private Button button_partidaSendTo;
	@FXML private Button button_partidaSendBack;
	@FXML private TextField textfield_partidaCantidad;
	
	//COSTOS DIRECTOS
	@FXML private TextField textfield_costosDirectosNombre;
	@FXML private TextField textfield_costosDirectosValor;
	@FXML private TextArea textarea_costosDirectosDescripcion;
	@FXML private Button button_costosDirectosAgregar;
	@FXML private TableColumn<CostoDirecto, String> tablecolumn_costosDirectosNombre;
	@FXML private TableColumn<CostoDirecto, Double> tablecolumn_costosDirectosValor;
	@FXML private TableColumn<CostoDirecto, String> tablecolumn_costosDirectosDescripcion;
	@FXML private TableView<CostoDirecto> tableview_costosDirectos;
	@FXML private Button button_costosDirectosModificar;
	@FXML private Button button_costosDirectosEliminar;
	
	//COSTOS INDIRECTOS
	@FXML private TextField textfield_costosIndirectosNombre;
	@FXML private TextField textfield_costosIndirectosValor;
	@FXML private TextArea textarea_costosIndirectosDescripcion;
	@FXML private Button button_costosIndirectosAgregar;
	@FXML private TableColumn<CostoIndirectoProducto, String> tablecolumn_costosIndirectosNombre;
	@FXML private TableColumn<CostoIndirectoProducto, Double> tablecolumn_costosIndirectosValor;
	@FXML private TableColumn<CostoIndirectoProducto, String> tablecolumn_costosIndirectosDescripcion;
	@FXML private TableView<CostoIndirectoProducto> tableview_costosIndirectos;
	@FXML private Button button_costosIndirectosModificar;
	@FXML private Button button_costosIndirectosEliminar;
	
	//PRECIOS
	@FXML private Tab precios;
	@FXML private TextField textfield_preciosCostos;
	@FXML private TextField textfield_preciosPorcientoGanancia;
	@FXML private TextField textfield_preciosImpuestos;
	@FXML private TextField textfield_preciosPrecio;
	@FXML private CheckBox checkbox_preciosHabilitar;
	
	/**VARIABLES PARA LA CREACION DE UN CLIENTE**/
	@FXML private Button button_clienteCancel;
	@FXML private Button button_clienteGuardar;
	@FXML private TextField textfield_codigoCliente;
	@FXML private TextField textfield_nombreCliente;
	@FXML private TextField textfield_telefonoCliente;
	@FXML private TextField textfield_tipoCliente;
	@FXML private TextField textfield_rncCliente;
	@FXML private DatePicker datepicker_cumpleCliente;
	
	/**VARIABLE PARA LA CREACION DE UN PROVEEDOR**/
	@FXML private Button button_proveedorCancel;
	@FXML private Button button_proveedorGuardar;
	@FXML private TextField textfield_codigoProveedor;
	@FXML private TextField textfield_nombreProveedor;
	@FXML private TextField textfield_telefonoProveedor;
	@FXML private TextArea textarea_direccionProveedor;
	@FXML private TextField textfield_rncProveedor;
	@FXML private TextField textfield_correoElectronicoProveedor;
	@FXML private TextField textfield_sitioWebProveedor;
	


/**FUNCIONES MENU PRINCIPAL**/
	
    public void principal_pressed(ActionEvent event){
    	pressed_principal1.setSource(pressed_principal); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(true);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	
    	button_principal.setEffect(pressed_principal1);
    	text_menuName.setText("Principal");
    	
    	//button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(false);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(true);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	
    }
    
    public void inventario_pressed(ActionEvent event){
    	pressed_inventario1.setSource(pressed_inventario); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(true);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	
    	button_inventario.setEffect(pressed_inventario1);
    	text_menuName.setText("Inventario");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	//button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(false);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(true);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	
    }
    
    public void productos_pressed(ActionEvent event){
    	pressed_productos1.setSource(pressed_productos); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(true);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	
    	button_productos.setEffect(pressed_productos1);
    	text_menuName.setText("Productos");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	//button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(false);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(true);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    }
    
    public void ventas_pressed(ActionEvent event){
    	pressed_ventas1.setSource(pressed_ventas); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(true);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	
    	button_ventas.setEffect(pressed_ventas1);
    	text_menuName.setText("Ventas");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	//button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(false);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(true);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	
    }
    
    public void historial_pressed(ActionEvent event){
    	pressed_historial1.setSource(pressed_historial); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(true);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	
    	button_historial.setEffect(pressed_historial1);
    	text_menuName.setText("Historial");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	//button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(false);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(true);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	
    }
    
    public void rh_pressed(ActionEvent event){
    	pressed_rh1.setSource(pressed_rh); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(true);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(false);
    	
    	button_rh.setEffect(pressed_rh1);
    	text_menuName.setText("Recursos Humanos");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	//button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(false);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(true);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(false);
    	
    }
    
    public void admin_pressed(ActionEvent event){
    	pressed_admin1.setSource(pressed_admin); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(true);
    	selected_help.setVisible(false);
    	
    	button_admin.setEffect(pressed_admin1);
    	text_menuName.setText("Administración");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	//button_admin.setEffect(nonpressed_admin1);
    	button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(false);
    	pane_Ayuda.setDisable(true);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(true);
    	pane_Ayuda.setVisible(false);
    	
    }
    
    
    public void help_pressed(ActionEvent event){
    	pressed_help1.setSource(pressed_help); 
    	
    	nonpressed_principal1.setSource(nonpressed_principal); 
    	nonpressed_inventario1.setSource(nonpressed_inventario); 
    	nonpressed_productos1.setSource(nonpressed_productos); 
    	nonpressed_ventas1.setSource(nonpressed_ventas); 
    	nonpressed_historial1.setSource(nonpressed_historial); 
    	nonpressed_rh1.setSource(nonpressed_rh); 
    	nonpressed_admin1.setSource(nonpressed_admin); 
    	nonpressed_help1.setSource(nonpressed_help); 
    	
    	selected_Principal.setVisible(false);
    	selected_Inventario.setVisible(false);
    	selected_productos.setVisible(false);
    	selected_ventas.setVisible(false);
    	selected_historial.setVisible(false);
    	selected_rh.setVisible(false);
    	selected_admin.setVisible(false);
    	selected_help.setVisible(true);
    	
    	button_help.setEffect(pressed_help1);
    	text_menuName.setText("Ayuda");
    	
    	button_principal.setEffect(nonpressed_principal1);
    	button_inventario.setEffect(nonpressed_inventario1);
    	button_productos.setEffect(nonpressed_productos1);
    	button_ventas.setEffect(nonpressed_ventas1);
    	button_historial.setEffect(nonpressed_historial1);
    	button_rh.setEffect(nonpressed_rh1);
    	button_admin.setEffect(nonpressed_admin1);
    	//button_help.setEffect(nonpressed_help1); 
    	
    	pane_Principal.setDisable(true);
    	pane_Inventario.setDisable(true);
    	pane_Productos.setDisable(true);
    	pane_Ventas.setDisable(true);
    	pane_Historial.setDisable(true);
    	pane_rh.setDisable(true);
    	pane_Admin.setDisable(true);
    	pane_Ayuda.setDisable(false);
    	
    	pane_Principal.setVisible(false);
    	pane_Inventario.setVisible(false);
    	pane_Productos.setVisible(false);
    	pane_Ventas.setVisible(false);
    	pane_Historial.setVisible(false);
    	pane_rh.setVisible(false);
    	pane_Admin.setVisible(false);
    	pane_Ayuda.setVisible(true);
    	
    }
    
    public void pressed_nuevoProducto(ActionEvent event){
    	clicked_nuevoProducto1.setSource(clicked_nuevoProducto);
    	nonclicked_nuevoProducto1.setSource(nonclicked_nuevoProducto); 
    	
    	button_nuevoProducto.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (pressed) {
            	button_nuevoProducto.setEffect(clicked_nuevoProducto1);
            } else {
            	button_nuevoProducto.setEffect(nonclicked_nuevoProducto1);
            }
    
        });
    	
    	/**ABRIENDO nuevoProducto.fxml**/
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoProducto.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Producto");
			stage.setScene(new Scene(root1, 1150, 750)); 
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(button_nuevoProducto.getScene().getWindow());
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void pressed_modificarProducto(ActionEvent event){
    	clicked_modificarProducto1.setSource(clicked_modificarProducto);
    	nonclicked_modificarProducto1.setSource(nonclicked_modificarProducto); 
    	
    	button_modificarProducto.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (pressed) {
            	button_modificarProducto.setEffect(clicked_modificarProducto1);
            } else {
            	button_modificarProducto.setEffect(nonclicked_modificarProducto1);
            }
        });
    }
    
    public void pressed_eliminarProducto(ActionEvent event){
    	clicked_eliminarProducto1.setSource(clicked_eliminarProducto);
    	nonclicked_eliminarProducto1.setSource(nonclicked_eliminarProducto);
    	
    	button_eliminarProducto.pressedProperty().addListener((observable, wasPressed, pressed) -> {
            if (wasPressed) {
            	button_eliminarProducto.setEffect(clicked_eliminarProducto1);
            } else {
            	button_eliminarProducto.setEffect(nonclicked_eliminarProducto1);
            }
        });
    }

    public void pressed_nuevoCliente(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoCliente.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Cliente");
			stage.setScene(new Scene(root1, 1150, 750));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(button_nuevoCliente.getScene().getWindow());
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
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
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Proveedor");
			stage.setScene(new Scene(root1, 1150, 750));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(button_nuevoProveedor.getScene().getWindow());
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void pressed_nuevoVendedor(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoVendedor.fxml"));
			Parent root1;
			root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Vendedor");
			stage.setScene(new Scene(root1, 1150, 750));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(button_nuevoVendedor.getScene().getWindow());
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
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
    	//Especifico sobre que variable el evento surgio
    	if(event.getSource().equals(textfield_costosDirectosValor)) {
    		costoDirectoActivarAgregar(event);
    	}
    	else if(event.getSource().equals(textfield_costosIndirectosValor)) {
    		costoIndirectoActivarAgregar(event);
    	}
    	else if(event.getSource().equals(textfield_telefonoCliente)) {
    		clienteActivarGuardar(event);
    	}
    	else if(event.getSource().equals(textfield_preciosPorcientoGanancia)) {
    		calcularPrecio(event);
    	}
    	else if(event.getSource().equals(textfield_telefonoProveedor)) {
    		proveedorActivarGuardar(event);
    	}
    }
    
    //Cierra la venta de nuevoProducto
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    
    /**FUNCIONES CREACION DE PRODUCTO**/  
   
    //FUNCIONES CREACION DE LA PARTIDA
    public void listview_PartidaClicked(MouseEvent event) {
    	if(!listview_partida.getSelectionModel().isEmpty() && textfield_partidaCantidad.getLength() > 0) {
    		button_partidaSendTo.setDisable(false);
    	}
    	else if (listview_partida.getSelectionModel().isEmpty() || textfield_partidaCantidad.getLength() == 0) {
    		button_partidaSendTo.setDisable(true);
    	}
    }
    
    public void listview_partidaSelectClicked(MouseEvent event) {
    	if(!listview_partidaSelect.getSelectionModel().isEmpty()) {
    		button_partidaSendBack.setDisable(false);
    	}
    	else {
    		button_partidaSendBack.setDisable(true);
    	}
    }
    
    public void movePartida(ActionEvent event) {
    	String select_items = listview_partida.getSelectionModel().getSelectedItem();
    	listview_partida.getItems().remove(listview_partida.getSelectionModel().getSelectedIndex());
    	listview_partidaSelect.getItems().addAll(select_items);
    	button_partidaSendTo.setDisable(true);
    	textfield_partidaCantidad.clear();
    }
    
    public void movePartidaSelect(ActionEvent event) {
    	String select_items = listview_partidaSelect.getSelectionModel().getSelectedItem();
    	listview_partidaSelect.getItems().remove(listview_partida.getSelectionModel().getSelectedIndex()+1);
    	listview_partida.getItems().add(select_items);
    	button_partidaSendBack.setDisable(true);
    }
    
    //FUNCIONES COSTO DIRECTO
    public void costoDirectoActivarAgregar(KeyEvent event) {
    	if(textfield_costosDirectosNombre.getLength() > 0 && textfield_costosDirectosValor.getLength() >= 0) {
    		button_costosDirectosAgregar.setDisable(false);
    	}
    	else {
    		button_costosDirectosAgregar.setDisable(true);
    	}
    }
    
	public void costoDirectoIngresarTableView(ActionEvent event) {
    	CostoDirecto costodirecto = new CostoDirecto(textfield_costosDirectosNombre.getText(), Double.parseDouble(textfield_costosDirectosValor.getText()), textarea_costosDirectosDescripcion.getText());
    	ObservableList<CostoDirecto> data = FXCollections.observableArrayList();
    	data.add(costodirecto);
    	tablecolumn_costosDirectosNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
    	tablecolumn_costosDirectosValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
    	tablecolumn_costosDirectosDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
    	tableview_costosDirectos.getItems().add(costodirecto);
    	textfield_costosDirectosNombre.clear();
    	textfield_costosDirectosValor.clear();
    	textarea_costosDirectosDescripcion.clear();
    	button_costosDirectosAgregar.setDisable(true);
    }
	
	public void costosDirectosTableViewClicked(MouseEvent event) {
		if(tableview_costosDirectos.getSelectionModel().isEmpty()) {
			button_costosDirectosModificar.setDisable(true);
			button_costosDirectosEliminar.setDisable(true);
		}
		else if(!tableview_costosDirectos.getSelectionModel().isEmpty()) {
			button_costosDirectosModificar.setDisable(false);
			button_costosDirectosEliminar.setDisable(false);
		}
	}
	
	public void costosDirectosEliminar(ActionEvent event) {
		int index = tableview_costosDirectos.getSelectionModel().getSelectedIndex();
		tableview_costosDirectos.getItems().remove(index);
	}
	
	//FUNCIONES COSTO INDIRECTO
	public void costoIndirectoActivarAgregar(KeyEvent event) {
    	if(textfield_costosIndirectosNombre.getLength() > 0 && textfield_costosIndirectosValor.getLength() > 0) {
    		button_costosIndirectosAgregar.setDisable(false);
    	}
    	else {
    		button_costosIndirectosAgregar.setDisable(true);
    	}
    }
	
	public void costoIndirectoIngresarTableView(ActionEvent event) {
    	CostoIndirectoProducto costoindirectoProducto = new CostoIndirectoProducto(textfield_costosIndirectosNombre.getText(), Double.parseDouble(textfield_costosIndirectosValor.getText()), textarea_costosIndirectosDescripcion.getText());
    	ObservableList<CostoIndirectoProducto> data = FXCollections.observableArrayList();
    	data.add(costoindirectoProducto);
    	tablecolumn_costosIndirectosNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
    	tablecolumn_costosIndirectosValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
    	tablecolumn_costosIndirectosDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
    	tableview_costosIndirectos.getItems().add(costoindirectoProducto);
    	textfield_costosIndirectosNombre.clear();
    	textfield_costosIndirectosValor.clear();
    	textarea_costosIndirectosDescripcion.clear();
    	button_costosIndirectosAgregar.setDisable(true);
    }
	
	public void costosIndirectosTableViewClicked(MouseEvent event) {
		if(tableview_costosIndirectos.getSelectionModel().isEmpty()) {
			button_costosIndirectosModificar.setDisable(true);
			button_costosIndirectosEliminar.setDisable(true);
		}
		else if(!tableview_costosIndirectos.getSelectionModel().isEmpty()) {
			button_costosIndirectosModificar.setDisable(false);
			button_costosIndirectosEliminar.setDisable(false);
		}
	}
	
	public void costosIndirectosEliminar(ActionEvent event) {
		int index = tableview_costosIndirectos.getSelectionModel().getSelectedIndex();
		tableview_costosIndirectos.getItems().remove(index);
	}
    
   //FUNCIONES PRECIO
	public void setCostoYPrecioTotal(Event event) {
		double valorDirecto = 0;
		double valorIndirecto = 0;
		for(CostoDirecto valor : tableview_costosDirectos.getItems()) {
			valorDirecto += valor.getValor();
		}
		for(CostoIndirectoProducto valor : tableview_costosIndirectos.getItems()) {
			valorIndirecto += valor.getValor();
		}
		textfield_preciosCostos.setText(Double.toString(valorDirecto + valorIndirecto));
		if(checkbox_preciosHabilitar.isSelected()) {
			textfield_preciosPorcientoGanancia.setDisable(false);
		}
		else {
			textfield_preciosPorcientoGanancia.setDisable(true);
		}
		try {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					Double.parseDouble(textfield_preciosPorcientoGanancia.getText()) ,
					Double.parseDouble(textfield_preciosImpuestos.getText()));	
			textfield_preciosPrecio.setText(Double.toString(precioTotal));
		}
		//Si el porciento de ganancia esta vacio, hago el calculo solo con el impuesto.
		catch (NumberFormatException e) {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					0 ,
					Double.parseDouble(textfield_preciosImpuestos.getText()));			
			textfield_preciosPrecio.setText(Double.toString(precioTotal));
		}		
	}
		
	
	public void habilitarPorcientoGanancia(ActionEvent event) {
		if(checkbox_preciosHabilitar.isSelected()) {
			textfield_preciosPorcientoGanancia.setDisable(false);
		}
		else {
			textfield_preciosPorcientoGanancia.setDisable(true);
		}
		
	}
	
	public void calcularPrecio(KeyEvent event) {
		//Tengo que sumarle el caracter del evento.
		String textfield = textfield_preciosPorcientoGanancia.getText() + event.getCharacter();
		try {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					Double.parseDouble(textfield) ,
					Double.parseDouble(textfield_preciosImpuestos.getText()));	
			textfield_preciosPrecio.setText(Double.toString(precioTotal));
		}
		//Si el porciento de ganancia esta vacio, hago el calculo solo con el impuesto.
		catch (NumberFormatException e) {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					0 ,
					Double.parseDouble(textfield_preciosImpuestos.getText()));			
			textfield_preciosPrecio.setText(Double.toString(precioTotal));
		}		
	}
	
	/**FUNCIONES AGREGAR CLIENTE**/
	
	public void clienteActivarGuardar(KeyEvent event) {
		if(textfield_codigoCliente.getLength() > 0 && textfield_nombreCliente.getLength() > 0 && textfield_telefonoCliente.getLength() > 0 && 
				textfield_rncCliente.getLength() > 0 && datepicker_cumpleCliente.getValue() != null) {
			button_clienteGuardar.setDisable(false);
		}
		else {
			button_clienteGuardar.setDisable(true);
		}
	}
	
	public void clienteActivarGuardarDatePicker(ActionEvent event) {
		if(textfield_codigoCliente.getLength() > 0 && textfield_nombreCliente.getLength() > 0 && textfield_telefonoCliente.getLength() > 0 && 
				textfield_rncCliente.getLength() > 0 && datepicker_cumpleCliente.getValue() != null) {
			button_clienteGuardar.setDisable(false);
		}
		else {
			button_clienteGuardar.setDisable(true);
		}
	}
	
	public void guardarCliente(ActionEvent event) {
		Cliente cliente = new Cliente(textfield_codigoCliente.getText(), textfield_nombreCliente.getText(), textfield_telefonoCliente.getText(), textfield_tipoCliente.getText(),
				 datepicker_cumpleCliente.getValue(), textfield_rncCliente.getText());
		Controladora.getInstance().addCliente(cliente);
	}
	
	/**FUNCIONES AGREGAR PROVEEDOR**/
	
	public void proveedorActivarGuardar(KeyEvent event) {
		if(textfield_codigoProveedor.getLength() > 0 && textfield_nombreProveedor.getLength() > 0 && textfield_rncProveedor.getLength() > 0
				&& textfield_telefonoProveedor.getLength() > 0) {
			button_proveedorGuardar.setDisable(false);
		}
		else {
			button_proveedorGuardar.setDisable(true);
		}
	}
	
	public void guardarProveedor(ActionEvent Event) {
		Rubro rubro = null;
		Proveedores proveedor = new Proveedores(textfield_codigoProveedor.getText(), textfield_nombreProveedor.getText(), textfield_telefonoProveedor.getText(),
				textarea_direccionProveedor.getText(), textfield_correoElectronicoProveedor.getText(), textfield_rncProveedor.getText(),
				rubro, textfield_sitioWebProveedor.getText());
		Controladora.getInstance().addProveedor(proveedor);
	}
	
	/**FUNCIONES PARA AGREGAR VENDEDOR**/
	
	ObservableList<String> observableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	observableList.add("Help");
    	listview_partida.setItems(observableList);
 
    }
    
}
