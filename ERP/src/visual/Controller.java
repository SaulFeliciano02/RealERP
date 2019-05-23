package visual;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.stage.Window;
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
	@FXML private Pane mainPane;
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
    @FXML private TableView<Proveedores> tableview_proveedoresList;
    @FXML private TextField textfield_proveedorBusqueda;
    
    //DESPLIEGUE DE VENDEDOR
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
			Window owner = button_nuevoCliente.getScene().getWindow();
			//stage.initModality(Modality.APPLICATION_MODAL);
			//stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Cliente");
			stage.setScene(new Scene(root1, 1150, 750));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			//SI CIERRO LA VENTANA DE REGISTROS DE CLIENTES CIERRO LA PRINCIPAL Y LA VUELVO A ABRIR
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          owner.hide();
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
			  		 
			  		    Parent root = f.load();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.sizeToScene();
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);
			  		    
			  		    primaryStage.show();
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
			stage.setScene(new Scene(root1, 1150, 750));  
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(owner);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			      public void handle(WindowEvent we) {
			          owner.hide();
			          try {
			          	Stage primaryStage = new Stage();
			          	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
			  		 
			  		    Parent root = f.load();
			  		    Scene sc = new Scene(root);
			  		    primaryStage.setScene(sc);
			  		    primaryStage.sizeToScene();
			  		    primaryStage.setTitle("Centro Pymes");
			  		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
			  		    primaryStage.setMaximized(true);
			  		    
			  		    primaryStage.show();
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
    
    public void buscarProveedores(KeyEvent event) {
    	ArrayList<Proveedores> proveedores = new ArrayList<>();
    	if(Character.isLetter(event.getCharacter().charAt(0))) {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_proveedorBusqueda.getText().toLowerCase() + event.getCharacter(), "Nombre");
    	}
    	else {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_proveedorBusqueda.getText().toLowerCase(), "Nombre");
    	}
    	if(proveedores.size() == 0) {
    		fillClientList(null);
    	}
    	else {
    		fillProveedorList(proveedores);
    	}
    }
	

	

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	fillClientList(null);
    	fillProveedorList(null);
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
    	tableview_proveedoresList.setItems(data);
    	tableview_proveedoresList.refresh();
    }
    
    public TableView<Cliente> getTableview_clientesList(){
    	//tableview_clientesList.setId("tableview_clientesList");
    	return this.tableview_clientesList;
    }
}
