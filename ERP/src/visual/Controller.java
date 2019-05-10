package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import logico.Controladora;
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
	/**GENERAL**/
	@FXML TextField exAct;
	@FXML TextField exMin;
	@FXML TextField exMax;
	
	/**PARTIDA**/
	@FXML private Tab tab_partida;
	@FXML private ListView<String> listview_partida = new ListView<>();
	@FXML private ListView<String> listview_partidaSelect;
	@FXML private Button button_partidaSendTo;
	@FXML private Button button_partidaSendBack;
	@FXML private TextField textfield_partidaCantidad;
	@FXML private Button button_productCancel;
	
	
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
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("Nuevo Producto");
			stage.setScene(new Scene(root1, 1150, 750));  
			stage.show();
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

    /**FUNCIONES CREACION DE PRODUCTO**/
    
    
    public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    }
    
    public void cancelCreation(ActionEvent event) {
    	Stage stage = (Stage) button_productCancel.getScene().getWindow();
        stage.close();
    }
    
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
    ObservableList<String> observableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	observableList.add("Help");
    	listview_partida.setItems(observableList);
    	//listview_partida.getSelectionModel().set
 
    }
    
}
