package visual;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Atributos;
import logico.CantProductosUtilizados;
import logico.CategoriaEmpleado;
import logico.Combinaciones;
import logico.Controladora;
import logico.CostoDirecto;
import logico.CostoIndirectoProducto;
import logico.Estandar;
import logico.GastoGeneral;
import logico.GrupoAtributo;
import logico.Kit;
import logico.ManoDeObra;
import logico.Partida;
import logico.Precio;
import logico.Producto;
import logico.Proveedores;
import logico.Rubro;
import logico.Servicio;
import logico.UnidadMedida;
import logico.Volumen;

public class ControllerNuevoProducto implements Initializable {
	
/**VARIABLES PARA CREAR PRODUCTOS**/
	@FXML TabPane tabpane_everything;
	private Boolean modificado = false;
	
	//TABS
	@FXML private Tab tab_general;
	@FXML private Tab tab_partida;
	@FXML private Tab tab_costos;
	@FXML private Tab tab_imagen;
	@FXML private Tab tab_sustitutos;
	@FXML private Tab tab_promocion;
	@FXML private Tab tab_combinaciones;
	
	//GENERAL
	@FXML private Text text_generalTipoProducto;
	@FXML private ComboBox<String> combobox_generalTipoProducto;
	@FXML private CheckBox checkbox_generalProducible;
	@FXML private Text text_generalCodigo;
	@FXML private TextField textfield_generalCodigo;
	@FXML private Text text_generalBarra;
	@FXML private TextField textfield_generalBarra;
	@FXML private MenuButton menubutton_generalBarra;
	@FXML private Button button_generalImprimir;
	@FXML private Text text_generalDescripcion;
	@FXML private TextArea textarea_generalDescripcion;
	@FXML private Text text_generalRubro;
	@FXML private TextField textfield_generalRubro;
	@FXML private Button button_productoBuscarRubro;
	@FXML private Text text_generalProveedor;
	@FXML private TextField textfield_generalProveedor;
	@FXML private Button button_productoBuscarProveedor;
	@FXML private Text text_generalExistencia;
	@FXML private TextField exAct;
	@FXML private Text text_generalMinimo;
	@FXML private TextField exMin;
	@FXML private Text text_generalMaximo;
	@FXML private TextField exMax;
	@FXML private RadioButton radiobutton_generalSerie;
	@FXML private RadioButton radiobutton_generalFecha;
	@FXML private Button button_productCancel;
	@FXML private Button button_productGuardar;
	@FXML private TextField textfield_generalNombre; 
	@FXML private TitledPane titledpane_productoBuscarUnidadMedida;
	@FXML private TextField textfield_generalUnidad;
	@FXML private Button button_BuscarUnidadMedida;
	@FXML private TextField precioCompraProducto;
	@FXML private AnchorPane pane_nuevorubro;
	
	//PARTIDA
	@FXML private ListView<String> listview_partida;
	@FXML private ListView<String> listview_partidaSelect;
	@FXML private ImageView button_partidaSendTo;
	@FXML private ImageView button_partidaSendBack;
	@FXML private TextField textfield_partidaCantidad;
	@FXML private ComboBox<String> combobox_ConversorUnidad;
	
	//COSTOS
	@FXML private TextField textfield_costosValor;
	@FXML private RadioButton radiobutton_costosDirectos;
	@FXML private RadioButton radiobutton_costosIndirectos;
	@FXML private Pane pane_costosDirectos;
	@FXML private Pane pane_costosIndirectos;
	@FXML private ListView<String> listview_CostosGenerales;
	@FXML private ListView<String> listview_CostosSelect;
	@FXML private ImageView button_derCosto;
	@FXML private ImageView button_izqCosto;
	@FXML private Button button_GuardarCostos;
	@FXML private ListView<String> listview_CostosResumen;
	@FXML private ListView<String> listview_GastosGeneralesIndirectos;
	@FXML private ImageView button_DerCostoIndirecto;
	@FXML private ImageView button_IzqCostoIndirecto;
	@FXML private ListView<String> listview_CostosSelecIndirectos;
	@FXML private Button button_GuardarCostoIndirecto;
	private ArrayList<CostoDirecto> gastosDirectos = new ArrayList<>();
	private ArrayList<CostoIndirectoProducto> gastosIndirectos = new ArrayList<>();
	@FXML private TextField textfield_costosTiempoFabricacion;
	@FXML private ComboBox<String> combobox_costosTiempoFabricacion;
	@FXML private ComboBox<String> combobox_costosEncargadosFabricacion;
	@FXML private TextField textfield_costoPrecioCompraProducto;
	@FXML private TextField textfield_TotalManoObra;

	//PRECIOS
	@FXML private TextField textfield_preciosCostos;
	@FXML private TextField textfield_preciosPorcientoGanancia;
	@FXML private TextField textfield_preciosImpuestos;
	@FXML private TextField textfield_preciosPrecio;
	@FXML private CheckBox checkbox_preciosHabilitar;
	@FXML private CheckBox checkbox_Impuestos;
	@FXML private AnchorPane anchor_izquierdaPrecio;
	@FXML private AnchorPane anchor_abajoprecio;
	@FXML private AnchorPane anchor_derechaPrecio;
	
	//VARIABLES PARA BUSQUEDA DE PROVEEDOR
	@FXML private TitledPane titledpane_productoBuscarProveedor;
	@FXML private TableColumn<Proveedores, String> tablecolumn_proveedorCodigo;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorNombre;
    @FXML private TableColumn<Proveedores, Rubro> tablecolumn_proveedorRubro;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorDomicilio;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorCorreo;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorTelefono;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorRNC;
    @FXML private TableColumn<Proveedores, String> tablecolumn_proveedorSitioWeb;
    @FXML private TableColumn<Proveedores, Float> tablecolumn_proveedorSaldo;
    @FXML private TableView<Proveedores> tableview_proveedorBuscar;
    @FXML private TextField textfield_productoBusquedaProveedor;
    @FXML private ComboBox<String> combobox_productoBusquedaProveedor;
    @FXML private Button button_cerrarBusquedaProveedor;
    @FXML private Button button_aceptarBusquedaProveedor;
    
    //VARIABLES PARA BUSQUEDA DE RUBRO
    
    @FXML private TitledPane titledpane_productoBuscarRubro;
    @FXML private TableColumn<Rubro, String> tablecolumn_rubroCodigo;
    @FXML private TableColumn<Rubro, String> tablecolumn_rubroNombre;
    @FXML private TableView<Rubro> tableview_rubroBuscar;
    @FXML private TextField textfield_productoBusquedaRubro;
    @FXML private ComboBox<String> combobox_productoBusquedaRubro;
    @FXML private Button button_cerrarBusquedaRubro;
    @FXML private Button button_aceptarBusquedaRubro;
    
    @FXML private TextField textfield_rubroCodigo;
    @FXML private TextField textfield_rubroNombre;
    @FXML private Button button_rubroGuardar;
    @FXML private ImageView button_rubroEliminar;
    
    //COMBINACIONES
    @FXML private TextField textfield_busquedaFamilia1;
    @FXML private TextField textfield_busquedaFamilia2;
    @FXML private TextField textfield_busquedaFamilia3;
    @FXML private TextField textfield_numSerie;
    @FXML private TextField textfield_cantidadComb;
    @FXML private ListView<String> listView_atributos1;
    @FXML private ListView<String> listView_atributos2;
    @FXML private ListView<String> listView_atributos3;
    @FXML private ListView<String> listView_combinaciones;
    @FXML private Button button_combinar;
    @FXML private Button button_buscarFamilia1;
    @FXML private Button button_buscarFamilia2;
    @FXML private Button button_buscarFamilia3;
    ArrayList<Combinaciones> combinacionFinal = new ArrayList<>();
    
    //Atributos y Familia en Combinaciones
    @FXML private TextField textfield_register_familia;
    @FXML private TextField textfield_registrar_atributo;
    @FXML private Button button_agregar_atributo;
    @FXML private Button button_cerrar_atributo;
    @FXML private TableColumn<Atributos, GrupoAtributo> tablecolumn_atributogrupo; 
    @FXML private TableColumn<Atributos, String> tablecolumn_atributonombre;
    @FXML private TableView<Atributos> tableView_atributos;
    @FXML private ListView<String> listView_grupoAtributos = new ListView<>();
    @FXML private TextField textfield_infoFamilia;
    @FXML private TableView<Atributos> tableView_Atributos;
    @FXML private Button button_atributosEliminar;
    @FXML private Button button_cerrarBusquedaAtributo;
    @FXML private TitledPane titledpane_productoBuscarAtributo;
    @FXML private Button button_borrarCombinacion;
    
    //VARIABLES PARA LA BUSQUEDA DE UNIDAD DE MEDIDAS
    
    @FXML TableColumn<UnidadMedida, String> tablecolumn_unidadCategoria;
    @FXML TableColumn<UnidadMedida, String> tablecolumn_unidadNombre;
    @FXML TableColumn<UnidadMedida, String> tablecolumn_unidadAbreviatura;
    @FXML TableView<UnidadMedida> tableview_unidadList;
    @FXML Button button_aceptarUnidad;
    
    //VARIABLES PARA LAS IMAGENES
    @FXML TextField textfield_imagen;
    @FXML ImageView imageview_imagen;
    @FXML Button button_agregarImagen;
    
    //VARIABLES PARA AYUDA
    @FXML CheckBox checkbox_habilitarAyuda;
    @FXML ImageView help_tipoProducto;
    @FXML ImageView help_producible;
    @FXML ImageView help_unidadMedida;
    @FXML ImageView help_rubro;
    @FXML ImageView help_proveedor;
    @FXML ImageView help_cantidadActual;
    @FXML ImageView help_cantidadMin;
    @FXML ImageView help_cantidadMax;
    @FXML ImageView help_busquedaPartida;
    @FXML ImageView help_cantidadPartida;
    @FXML ImageView help_precioEstimado;
    @FXML ImageView help_precioCompra;
    @FXML ImageView help_costosIndirectos;
    @FXML ImageView help_costosDirectos;
    @FXML ImageView help_tabPrecios;
    @FXML ImageView help_tabImagen;
    @FXML ImageView help_tabSustitutos;
    @FXML ImageView help_tabPromocion;
    @FXML ImageView help_tabCombinaciones;
    @FXML ImageView help_numeroSerie;
    
    /**FUNCIONES GENERALES**/
    
    //Verifica si el input de un textfield es un n�mero.
    public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter()) && !event.getCode().equals(KeyCode.BACK_SPACE)) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	/*if(event.getSource().equals(textfield_costosValor)) {
    		costoDirectoActivarAgregar(event);
    	}*/

    	else if(event.getSource().equals(textfield_preciosPorcientoGanancia)) {
    		calcularPrecio(event);
    	}
    	else if(event.getSource().equals(textfield_preciosImpuestos)) {
    		calcularPrecio(event);
    	}
    	else if(event.getSource().equals(exAct) || event.getSource().equals(exMax) || event.getSource().equals(exMin)) {
    		activarProductoGuardar(event);
    	}
    }
    
    //Funci�n que determina si la tecla presionada en un textfield cumple con los par�metros para ser considero un valor de tipo float.
    public void floatFieldPressed(KeyEvent event) {
    	
    	TextField source = (TextField) event.getSource();
    	//Cuando se presiona una tecla para ser registrada como input en un textfield de javafx,
    	//esta no queda registrada hasta que se termine de procesar el evento.
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
    
    //Cierra y vuelve a abrir la ventana principal
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
    
    //Cierra la ventana
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
	   	reload(stage);
    }
    
    //Determina si los par�metros de un producto est�n completos.
    public void activarProductoGuardar(KeyEvent event) {
    	String tipoProducto = combobox_generalTipoProducto.getSelectionModel().getSelectedItem();
    	//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validaci�n de los par�metros, si se te es posible estandarizarlo, recomendamos hacerlo.
    	if(!modificado) {
    		if(tipoProducto.equalsIgnoreCase("Matriz")) {
    			if(textfield_generalCodigo.getLength() > 0 && textfield_generalRubro.getLength() > 0 && textfield_generalProveedor.getLength() > 0 && textfield_generalNombre.getLength() > 0) {
    				button_productGuardar.setDisable(false);
    			}
    			else {
    				button_productGuardar.setDisable(true);
    			}
    		}
    		else if(tipoProducto.equalsIgnoreCase("Servicio")) {
    			if(textfield_generalCodigo.getLength() > 0 && textfield_generalRubro.getLength() > 0 && textfield_generalNombre.getLength() > 0) {
    				button_productGuardar.setDisable(false);
    			}
    			else {
    				button_productGuardar.setDisable(true);
    			}
    		}
    		else if(tipoProducto.equalsIgnoreCase("Estandar") || tipoProducto.equalsIgnoreCase("Kit")) {
    			if(textfield_generalCodigo.getLength() > 0 && textfield_generalRubro.getLength() > 0
    					&& exAct.getLength() > 0 && exMax.getLength() > 0 && exAct.getLength() > 0 && textfield_generalNombre.getLength() > 0) {
    				button_productGuardar.setDisable(false);
    			}
    			else {
    				button_productGuardar.setDisable(true);
    			}
    		}
    		else if(tipoProducto.equalsIgnoreCase("Estandar")) {
    			if(textfield_generalCodigo.getLength() > 0 && textfield_generalRubro.getLength() > 0
    					&& exAct.getLength() > 0 && exMax.getLength() > 0 && exAct.getLength() > 0 && textfield_generalNombre.getLength() > 0
    					&& textfield_generalProveedor.getLength() > 0) {
    				button_productGuardar.setDisable(false);
    			}
    			else {
    				button_productGuardar.setDisable(true);
    			}
    		}
    	}
    		
    }
    
    //Activa el tab de partida.
    public void activarPartida(ActionEvent event) {
    	
    	if(checkbox_generalProducible.isSelected()) {
    		tab_partida.setDisable(false);
    		
    		radiobutton_costosDirectos.setDisable(false);
    		radiobutton_costosDirectos.setSelected(true);
    		pane_costosDirectos.setVisible(true);
    		
    		radiobutton_costosIndirectos.setSelected(false);
    		pane_costosIndirectos.setVisible(false);
    		textfield_costoPrecioCompraProducto.setText("0");
    		textfield_costoPrecioCompraProducto.setDisable(true);
    		//textfield_generalProveedor.setText(Controladora.getInstance().getMiEmpresa().getNombre());
    		textfield_generalProveedor.setDisable(true);
    		button_productoBuscarProveedor.setDisable(true);

    	}
    	else {
    		tab_partida.setDisable(true);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		
    		textfield_costoPrecioCompraProducto.setText("");
    		textfield_costoPrecioCompraProducto.setDisable(false);
    		textfield_generalProveedor.setText("");
    		textfield_generalProveedor.setDisable(false);
    		button_productoBuscarProveedor.setDisable(false);
    	}
    }
    
    public void seleccionarFoto(ActionEvent action)
    {
    	JFileChooser j = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        j.setFileFilter(fil);
        
        int s = j.showOpenDialog(j);
        if(s == JFileChooser.APPROVE_OPTION){
            String ruta = j.getSelectedFile().getAbsolutePath();
            textfield_imagen.setText(ruta);
            try{
            	File fotofile = new File(textfield_imagen.getText());
            	//BufferedImage imagen = ImageIO.read(fotofile);
                Image imagenmuestra = new Image(fotofile.toURI().toString());
            	//Image imagenmuestra = imagen.getScaledInstance(649, 324, (Integer) null);
                //ImageIcon imgi = new ImageIcon(((java.awt.Image) image).getScaledInstance(60, 60, 0));
                imageview_imagen.setImage(imagenmuestra);
                double w = 0;
                double h = 0;

                double ratioX = imageview_imagen.getFitWidth() / imagenmuestra.getWidth();
                double ratioY = imageview_imagen.getFitHeight() / imagenmuestra.getHeight();

                double reducCoeff = 0;
                if(ratioX >= ratioY) {
                    reducCoeff = ratioY;
                } else {
                    reducCoeff = ratioX;
                }

                w = imagenmuestra.getWidth() * reducCoeff;
                h = imagenmuestra.getHeight() * reducCoeff;

                imageview_imagen.setX((imageview_imagen.getFitWidth() - w) / 2);
                imageview_imagen.setY((imageview_imagen.getFitHeight() - h) / 2);
                //imageview_imagen.setFitWidth(649);
                //imageview_imagen.setFitHeight(324);
                //fila[4] = new JLabel(imgi);

            }catch(Exception ex){
                System.out.println("Error al cargar la imagen");
            }
        }
    }
    
    //Guardar Producto
    public void guardarProducto(ActionEvent event) {
    	
    	/*if(textfield_preciosPrecio.getText().isEmpty() == true) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confirma");
    		alert.setHeaderText("");
    		alert.setContentText("Tu producto no tiene precio, �est� seguro de proceder?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    // ... user chose OK
    		} else {
    		    // ... user chose CANCEL or closed the dialog
    		}
    	} */
    	
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	Alert success = new Alert(AlertType.INFORMATION, "Los datos han sido guardados exitosamente.");
    	boolean canRegister = true;
    	String codigo = textfield_generalCodigo.getText();
    	String nombre = textfield_generalNombre.getText();
    	Rubro rubro = null;
    	Proveedores proveedor = null;
    	setCostoYPrecioTotal(null);
    	Precio precio = new Precio(Float.parseFloat(textfield_preciosPrecio.getText()), "", true, Integer.parseInt(textfield_preciosPorcientoGanancia.getText()), Integer.parseInt(textfield_preciosImpuestos.getText()));
    	float costoitbis = 0;
    	byte[] foto = null;
    	
    	if(textfield_imagen.getText() != null)
    	{
    		File fotofile = new File(textfield_imagen.getText());
    		try{
    			foto = new byte[(int) fotofile.length()];
	            FileInputStream input = new FileInputStream(fotofile);
	            input.read(foto);
	            input.close();
	            
    	    }catch(Exception ex){
    	    	System.out.println("Error al cargar la imagen");
    	    }
    	}
    	
    	float precioImpuesto = Float.parseFloat(textfield_preciosImpuestos.getText());
    	if(Controladora.getInstance().getMiEmpresa() != null && checkbox_Impuestos.isSelected()) {
    		costoitbis = (float) (((precio.getPrecio() * (precioImpuesto/100.0)) * 100.0) / 100.0);
    		System.out.println("El valor del costoitbis1: " + costoitbis + "Precio: " + precio.getPrecio());
    	}
    	else if(checkbox_Impuestos.isSelected()) {
    		costoitbis = (float) (((precio.getPrecio() * (precioImpuesto/100.0)) * 100.0) / 100.0);
    		System.out.println("El valor del costoitbis2: " + costoitbis + "Precio: " + precio.getPrecio());
    	}
    	
    	//Guardando precio en la base de datos
    	
    	
    	String descripcion = "";
    	String codigoBarra = "";
    	String tipoProducto = combobox_generalTipoProducto.getSelectionModel().getSelectedItem();
    	UnidadMedida unidad = null;
    	
    	float costoTotal = Float.parseFloat(textfield_preciosCostos.getText());
    	if(textfield_generalUnidad.getLength() != 0) {
    		for(UnidadMedida u : Controladora.getInstance().getMisUnidadMedida()) {
    			if(u.getNombre().equalsIgnoreCase(textfield_generalUnidad.getText())) {
    				unidad = u;
    			}
    		}
    		
    	}
    	try {
    		descripcion = textarea_generalDescripcion.getText();
    		codigoBarra = textfield_generalBarra.getText();
    	}
    	catch(NullPointerException e) {
    		
    	}
    	
    	for(Rubro r : Controladora.getInstance().getMisRubros()) {
    		if(r.getNombreRubro().equalsIgnoreCase(textfield_generalRubro.getText())) {
    			rubro = r;
    		}
    	}
    	
    	if(checkbox_generalProducible.isSelected())
		{
			proveedor = Controladora.getInstance().getMisProveedores().get(0);
		}
    	else {
    		for(Proveedores p : Controladora.getInstance().getMisProveedores()) {
        		if(p.getCodigo().equalsIgnoreCase(textfield_generalProveedor.getText())) {
        			proveedor = p;
        		}
        	}
    	}
    	
    	//Iniciado del modificar
    	if(modificado) {
    		Alert alertModify = new Alert(AlertType.CONFIRMATION, "Est� seguro que desea guardar estos cambios?", ButtonType.YES, ButtonType.NO);
    		ArrayList<Producto> producto = Controladora.getInstance().searchProducts(codigo, "Codigo");
    		if(producto.get(0).getTipoProducto().equalsIgnoreCase("Estandar")) {
    			Estandar estandar = (Estandar) producto.get(0);
    			float existenciaInicial = estandar.getExistenciaInicial();
    			if(!estandar.getNombre().equalsIgnoreCase(nombre) || estandar.getProveedorPrinClass() != proveedor || estandar.getRubroProductoClass() != rubro
    				|| estandar.getUnidadMedida() != unidad || estandar.getExistenciaActual() != Float.parseFloat(exAct.getText()) || estandar.getExistenciaMinima() != Float.parseFloat(exMin.getText())
    				|| estandar.getExistenciaMaxima() != Float.parseFloat(exMax.getText()) || estandar.getCostoDeCompra() != Float.parseFloat(textfield_costoPrecioCompraProducto.getText())
    				|| estandar.getCosto() != Float.parseFloat(textfield_preciosCostos.getText()) || estandar.getPrecio() != Float.parseFloat(textfield_preciosPrecio.getText())
    				|| checkPartida(estandar) || checkCostosIndirectos(estandar) || checkManoDeObra(estandar) || estandar.getPrecioClass().getPorc_ganancia() != Integer.parseInt(textfield_preciosPorcientoGanancia.getText()) || estandar.getPrecioClass().getItbis() != Integer.parseInt(textfield_preciosImpuestos.getText())) {
    				
    				
    				alertModify.showAndWait();
    				if(alertModify.getResult() == ButtonType.YES) {
    					System.out.println("Klk");
    					String existenciaActual = exAct.getText();
        	    		String existenciaMinima = exMin.getText();
        	    		String existenciaMaxima = exMax.getText();
        	    		float costoDeCompra = 0;
        	    		float costoManoObra = 0;
        	    		boolean fabricado = false;
        	    		ManoDeObra infoManoDeObra = null;
        	    		CategoriaEmpleado categoriaempleado = null;
        	    		if(checkbox_generalProducible.isSelected()) {
        	    			try {
        	    				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
        	    				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
        	    				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
        	    				costoManoObra = Controladora.getInstance().calcularManoDeObra(nombreCategoria, tiempoMedida, tiempoCantidad);
        	    				categoriaempleado = Controladora.getInstance().buscarCategoria(Controladora.getInstance().
        	    						findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem()));
        	    				infoManoDeObra = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoriaempleado);
        	    			}catch(NullPointerException e) {
        	    				
        	    			}
        	    			fabricado = true;
        	    		}
        	    		else if(textfield_costoPrecioCompraProducto.getLength() > 0){
        	    			costoDeCompra = Float.parseFloat(textfield_costoPrecioCompraProducto.getText());
        	    		}
        	    		a.setAlertType(AlertType.WARNING);
        	    		if(Float.parseFloat(existenciaMinima) > Float.parseFloat(existenciaMaxima)) {
        	    			a.setContentText("La existencia minima no puede ser mayor que la maxima");
        	    			a.show();
        	    			canRegister = false;
        	    		}
        	    		else if(Float.parseFloat(existenciaActual) < Float.parseFloat(existenciaMinima)){
        	    			a.setContentText("La existencia actual no puede ser menor que la existencia minima");
        	    			a.show();
        	    			canRegister = false;
        	    		}
        	    		else if(Float.parseFloat(existenciaActual) > Float.parseFloat(existenciaMaxima)){
        	    			a.setContentText("La existencia actual no puede ser mayor que la existencia maxima");
        	    			a.show();
        	    			canRegister = false;
        	    		}
        	    		else if(Float.parseFloat(existenciaMaxima) < Float.parseFloat(existenciaMinima)){
        	    			a.setContentText("La existencia maxima no puede ser menor que la existencia minima");
        	    			a.show();
        	    			canRegister = false;
        	    		}
        	    		Date date = null;
        	    		Partida partida = null;	
        	    		if(checkbox_generalProducible.isSelected()) {
        	    			partida = new Partida();
        	    			for(String s : listview_partidaSelect.getItems()) {
        	    				String nombreSelect = Controladora.getInstance().findPartidaNombre(s);
        	    				String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
        	    				Estandar productoPart = (Estandar) Controladora.getInstance().buscarProducto(nombreSelect);
        	    				CantProductosUtilizados c = new CantProductosUtilizados(productoPart, Float.parseFloat(cantidadSelect));
        	    				
        	    				Controladora.getInstance().getMisCantProductosUtilizados().add(c);
        	    				Controladora.getInstance().guardarCantProductosUtilizadosSQL(productoPart, c);
        	    				partida.agregarProductoUtilizado(c);
        	    				
        	    				int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(productoPart)+1;
        	    				for(String itemOriginal : listview_partida.getItems()) {
        							String itemOriginalName = Controladora.getInstance().findPartidaNombre(itemOriginal);
        							if(itemOriginalName.equalsIgnoreCase(nombreSelect)) {
        								float itemOriginalCantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(itemOriginal));
        								float cantidadSumar = (itemOriginalCantidad + Float.parseFloat(cantidadSelect)*Float.parseFloat(existenciaActual));
        								System.out.println("La cantidad que se esta sumando es: "  + cantidadSumar);
        								Controladora.getInstance().sumarExistenciaActual(cantidadSumar, indiceProducto);
        								int indiceEstandar = Controladora.getInstance().getProductoEstandarIndice(productoPart);
        								Controladora.getInstance().getMisProductosEstandar().get(indiceEstandar).setExistenciaActual(cantidadSumar);
        							}
        						}
        	    				float cantidadRestar = (productoPart.getExistenciaActual() - (Float.parseFloat(cantidadSelect) * Float.parseFloat(existenciaActual)));
        	    				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
        	    			}
        	    		}
        	    		
        	    		//No se registra nombre, fecha, y muchas otras cosas
        	    		if(canRegister) {
        	    			Controladora.getInstance().getMisPrecios().add(precio);
                        	Controladora.getInstance().guardarPrecioSQL(precio);
                        	
        	    			Estandar newEstandar = new Estandar(Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), existenciaInicial, date, costoDeCompra, fabricado, partida, codigo, nombre,
        	    				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costoManoObra, "", "", costoTotal, costoitbis);
        	    			
        	    			
        	    			Controladora.getInstance().getMisProductosEstandar().add(newEstandar);
        	    			Controladora.getInstance().getMisProductos().add(newEstandar);
        	    			
        	    			Controladora.getInstance().guardarProductosSQL(newEstandar);
        	    			Controladora.getInstance().guardarEstandarSQL(newEstandar);
        	    			
        	    			for(CostoIndirectoProducto c : gastosIndirectos) {
        	    				newEstandar.getCostosIndirectos().add(c);
        	    				
        	    				Controladora.getInstance().getMisCostosIndirectos().add(c);
        	    				Controladora.getInstance().guardarCostoIndirectoSQL(newEstandar, c);
        	    			}
        	    			
        	    			if(infoManoDeObra != null) {
        	    				Controladora.getInstance().getMisManosDeObras().add(infoManoDeObra);
        	    				newEstandar.setInfoManoDeObra(infoManoDeObra);
        	    				Controladora.getInstance().guardarManoDeObraSQL(infoManoDeObra);
        	    				Controladora.getInstance().guardarManoDeObraProductoSQL(newEstandar, infoManoDeObra, categoriaempleado);
        	    			}
        	    			if(checkbox_generalProducible.isSelected()) {
        	    				
        	    				
        	    				
        	    				Controladora.getInstance().getMisPartidas().add(partida);
        	    				Controladora.getInstance().guardarPartidaSQL();
        	    				for(CantProductosUtilizados c : partida.getListaMateriales()) {
        	    					Controladora.getInstance().guardarPartidaProdutilSQL(partida, c);
        	    				}
        	    				Controladora.getInstance().guardarProductoPartida(newEstandar, partida);
        	    			}	
        	    			
        	    			Controladora.getInstance().guardarPrecioProductoSQL(newEstandar, precio);
        	    			
        	    			if(proveedor != null) {
        	    				Controladora.getInstance().guardarProveedorPrincipalProductoSQL(newEstandar, proveedor);
        	    			}
        	    					
        	    			
        	    			Controladora.getInstance().guardarRubroProductoSQL(newEstandar, rubro);
        	    			
        	    			int oldIndiceProducto = Controladora.getInstance().getProductoIndice(estandar);//Controladora.getInstance().getMisProductos().indexOf(estandar);
        	    			int oldIndiceProductoEstandar = Controladora.getInstance().getProductoEstandarIndice(estandar);//Controladora.getInstance().getMisProductosEstandar().indexOf(estandar);
        	    			
        	    			Controladora.getInstance().getMisProductos().get(oldIndiceProducto).setBorrado(true);
        	    			Controladora.getInstance().getMisProductosEstandar().get(oldIndiceProductoEstandar).setBorrado(true);
        
        	    			Controladora.getInstance().borrarProducto(oldIndiceProducto+1);
        	    			
        	    			success.showAndWait();
        	    			cleanEverything(tipoProducto);
        	    			cancelCreation(event);
        				
        				
        	    		}
    				}
    			}
    		}
    		
    		if(producto.get(0).getTipoProducto().equalsIgnoreCase("Kit")) {
    			Kit kit = (Kit) producto.get(0);
    			float existenciaInicial = kit.getExistenciaInicial();
   
    			if(!kit.getNombre().equalsIgnoreCase(nombre) || kit.getProveedorPrinClass() != proveedor || kit.getRubroProductoClass() != rubro
        				|| kit.getUnidadMedida() != unidad || kit.getExistenciaActual() != Float.parseFloat(exAct.getText()) || kit.getExistenciaMinima() != Float.parseFloat(exMin.getText())
        				|| kit.getExistenciaMaxima() != Float.parseFloat(exMax.getText()) || kit.getCosto() != Float.parseFloat(textfield_preciosCostos.getText()) || kit.getPrecio() != Float.parseFloat(textfield_preciosPrecio.getText())
        				|| checkPartida(kit) || checkCostosIndirectos(kit) 
        				|| kit.getPrecioClass().getPorc_ganancia() != Integer.parseInt(textfield_preciosPorcientoGanancia.getText()) 
        				|| kit.getPrecioClass().getItbis() != Integer.parseInt(textfield_preciosImpuestos.getText())) {
    				
    				alertModify.showAndWait();
    				if(alertModify.getResult() == ButtonType.YES) {
    					String existenciaActual = exAct.getText();
                		String existenciaMinima = exMin.getText();
                		String existenciaMaxima = exMax.getText();
                		float costo = 0;
                		a.setAlertType(AlertType.WARNING);
                		if(Float.parseFloat(existenciaMinima) > Float.parseFloat(existenciaMaxima)) {
                			a.setContentText("La existencia minima no puede ser mayor que la maxima");
                			a.show();
                			canRegister = false;
                		}
                		else if(Float.parseFloat(existenciaActual) < Float.parseFloat(existenciaMinima)){
                			a.setContentText("La existencia actual no puede ser menor que la existencia minima");
                			a.show();
                			canRegister = false;
                		}
                		else if(Float.parseFloat(existenciaActual) > Float.parseFloat(existenciaMaxima)){
                			a.setContentText("La existencia actual no puede ser mayor que la existencia maxima");
                			a.show();
                			canRegister = false;
                		}
                		else if(Float.parseFloat(existenciaMaxima) < Float.parseFloat(existenciaMinima)){
                			a.setContentText("La existencia maxima no puede ser menor que la existencia minima");
                			a.show();
                			canRegister = false;
                		}
                		else if(listview_partidaSelect.getItems().size() == 0) {
                			a.setContentText("Debe elegir los productos que conforman el kit");
                			a.show();
                			canRegister = false;
                		}
                		Controladora.getInstance().getMisPrecios().add(precio);
                    	Controladora.getInstance().guardarPrecioSQL(precio);
                		Date date = null;
                		ArrayList<CantProductosUtilizados> productsForKit = new ArrayList<>();
                		for(String item : listview_partidaSelect.getItems()) {
                			String nombreItem = Controladora.getInstance().findPartidaNombre(item);
                			String cantidad = Controladora.getInstance().findPartidaCantidad(item);
                			for(Estandar p : Controladora.getInstance().getMisProductosEstandar()) {
                				if(!p.isBorrado()) {
                					if(nombreItem.equals(p.getNombre())) {
                						CantProductosUtilizados c = new CantProductosUtilizados(p, Float.parseFloat(cantidad));
                						Controladora.getInstance().getMisCantProductosUtilizados().add(c);
                						Controladora.getInstance().guardarCantProductosUtilizadosSQL(p, c);
                						productsForKit.add(c);
                						
                						//float cantidadSumar = 
                						int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(p)+1;
                						for(String itemOriginal : listview_partida.getItems()) {
                							String itemOriginalName = Controladora.getInstance().findPartidaNombre(itemOriginal);
                							if(itemOriginalName.equalsIgnoreCase(nombreItem)) {
                								float itemOriginalCantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(itemOriginal));
                								float cantidadSumar = (itemOriginalCantidad + Float.parseFloat(cantidad)*Float.parseFloat(existenciaActual));
                								Controladora.getInstance().sumarExistenciaActual(cantidadSumar, indiceProducto);
                								int indiceEstandar = Controladora.getInstance().getProductoEstandarIndice(p);
                								Controladora.getInstance().getMisProductosEstandar().get(indiceEstandar).setExistenciaActual(cantidadSumar);
                							}
                						}
                						float cantidadRestar = (p.getExistenciaActual() - (Float.parseFloat(cantidad) * Float.parseFloat(existenciaActual)));
                    				
                						Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
                					}
                				}
                				
                			}
                		}
                		//Visitar esto nuevamente
                		if(canRegister) {
                			Kit newKit = new Kit(productsForKit, Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), existenciaInicial, date, codigo, nombre,
                				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costo, "", "", costoTotal, costoitbis);
                			
                			Controladora.getInstance().getMisProductos().add(newKit);
                			Controladora.getInstance().guardarProductosSQL(newKit);
                			
                			Controladora.getInstance().getMisProductosKit().add(newKit);
                			Controladora.getInstance().guardarKitSQL(newKit);
                			
                			for(CostoIndirectoProducto c : gastosIndirectos) {
                				newKit.getCostosIndirectos().add(c);
                				
                				Controladora.getInstance().getMisCostosIndirectos().add(c);
                				Controladora.getInstance().guardarCostoIndirectoSQL(newKit, c);
                			}
                			
                			Controladora.getInstance().guardarRubroProductoSQL(newKit, rubro);
                			Controladora.getInstance().guardarPrecioProductoSQL(newKit, precio);
                			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(newKit, proveedor);
                			
                			for(CantProductosUtilizados c : productsForKit) {
                				Controladora.getInstance().guardarKitProductosSQL(newKit, c);
                			}
                			
                			int oldIndiceProducto = Controladora.getInstance().getProductoIndice(kit);
        	    			int oldIndiceProductoKit = Controladora.getInstance().getProductoKitIndice(kit);
        	    			
        	    			Controladora.getInstance().getMisProductos().get(oldIndiceProducto).setBorrado(true);
        	    			Controladora.getInstance().getMisProductosKit().get(oldIndiceProductoKit).setBorrado(true);
        	    			
        	    			Controladora.getInstance().borrarProducto(oldIndiceProducto+1);
        	    			success.showAndWait();
        	    			cleanEverything(tipoProducto);
        	    			cancelCreation(event);
                			
                		}
    				}
    			}
    		}
    		
    		if(producto.get(0).getTipoProducto().equalsIgnoreCase("Servicio")) {
    			Servicio servicio = (Servicio) producto.get(0);
    			
    			if(!servicio.getNombre().equalsIgnoreCase(nombre) || servicio.getProveedorPrinClass() != proveedor || servicio.getRubroProductoClass() != rubro
        				|| servicio.getUnidadMedida() != unidad || servicio.getCosto() != Float.parseFloat(textfield_preciosCostos.getText()) 
        				|| servicio.getPrecio() != Float.parseFloat(textfield_preciosPrecio.getText())
        				|| checkPartida(servicio) || checkCostosIndirectos(servicio) || checkManoDeObra(servicio) 
        				|| servicio.getPrecioClass().getPorc_ganancia() != Integer.parseInt(textfield_preciosPorcientoGanancia.getText()) 
        				|| servicio.getPrecioClass().getItbis() != Integer.parseInt(textfield_preciosImpuestos.getText())) {
    				alertModify.showAndWait();
    				if(alertModify.getResult() == ButtonType.YES) {
    					a.setAlertType(AlertType.WARNING);
            			if(combobox_costosEncargadosFabricacion.getSelectionModel().isEmpty()) {
            				a.setContentText("Eliga la categoria de empleado que ejercera este servicio.");
                			a.show();
                			canRegister = false;
            			}
        				
                		if(canRegister) {
                			float costoManoObra = 0;
                			ManoDeObra infoManoDeObra = null;
                			CategoriaEmpleado categoriaempleado = null;
                			Controladora.getInstance().getMisPrecios().add(precio);
                			Controladora.getInstance().guardarPrecioSQL(precio);
                			ArrayList<CantProductosUtilizados> productsForServicio = new ArrayList<>();
                			for(String item : listview_partidaSelect.getItems()) {
                				String nombreItem = Controladora.getInstance().findPartidaNombre(item);
                				String cantidadItem = Controladora.getInstance().findPartidaCantidad(item);
                				for(Estandar p : Controladora.getInstance().getMisProductosEstandar()) {
                					if(p.isBorrado()) {
                						if(nombreItem.equals(p.getNombre())) {
                							CantProductosUtilizados c = new CantProductosUtilizados(p, Float.parseFloat(cantidadItem));
                				
                							Controladora.getInstance().getMisCantProductosUtilizados().add(c);
                							Controladora.getInstance().guardarCantProductosUtilizadosSQL(p, c);
                					
                							productsForServicio.add(c);
                					
                							
                							int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(p)+1;
                							
                							for(String itemOriginal : listview_partida.getItems()) {
                    							String itemOriginalName = Controladora.getInstance().findPartidaNombre(itemOriginal);
                    							if(itemOriginalName.equalsIgnoreCase(nombreItem)) {
                    								float itemOriginalCantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(itemOriginal));
                    								float cantidadSumar = (itemOriginalCantidad + Float.parseFloat(cantidadItem));
                    								Controladora.getInstance().sumarExistenciaActual(cantidadSumar, indiceProducto);
                    								int indiceEstandar = Controladora.getInstance().getProductoEstandarIndice(p);
                    								Controladora.getInstance().getMisProductosEstandar().get(indiceEstandar).setExistenciaActual(cantidadSumar);
                    							}
                    						}
                							float cantidadRestar = (p.getExistenciaActual() - (Float.parseFloat(cantidadItem)));
                							Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);

                						}
                					}
                				}
                			}
          
                			CategoriaEmpleado categoriaEmpleado = Controladora.getInstance().buscarCategoria(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().substring(0, combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().indexOf(":")));
                			
                			Servicio newServicio = new Servicio(codigo, nombre, descripcion, rubro, tipoProducto, proveedor, null, "", unidad, precio, "", codigoBarra,
               					descripcion, categoriaEmpleado, productsForServicio, costoTotal, costoitbis);
               				Controladora.getInstance().addProductoServicio(newServicio);
               				Controladora.getInstance().addProducto(newServicio);
               			
               				Controladora.getInstance().guardarProductosSQL(newServicio);
               				Controladora.getInstance().guardarServiciosSQL(newServicio, categoriaEmpleado);
               				Controladora.getInstance().guardarRubroProductoSQL(newServicio, rubro);
                   			Controladora.getInstance().guardarPrecioProductoSQL(newServicio, precio);
                   			
                   			for(CostoIndirectoProducto c : gastosIndirectos) {
                   				newServicio.getCostosIndirectos().add(c);
                    			
                    			Controladora.getInstance().getMisCostosIndirectos().add(c);
                   				Controladora.getInstance().guardarCostoIndirectoSQL(newServicio, c);
                   			}
                		
               				for(CantProductosUtilizados c : newServicio.getMaterialesUtilizados()) {
               					Controladora.getInstance().guardarServiciosMaterialesSQL(newServicio, c);
               				}
               			
               				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
               				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
               				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
               				costoManoObra = Controladora.getInstance().calcularManoDeObra(nombreCategoria, tiempoMedida, tiempoCantidad);
               				categoriaempleado = Controladora.getInstance().buscarCategoria(Controladora.getInstance().
           						findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem()));
               				infoManoDeObra = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoriaempleado);
            			
                			if(infoManoDeObra != null) {
                				Controladora.getInstance().getMisManosDeObras().add(infoManoDeObra);
               					newServicio.setInfoManoDeObra(infoManoDeObra);
               					Controladora.getInstance().guardarManoDeObraSQL(infoManoDeObra);
               					Controladora.getInstance().guardarManoDeObraServicioSQL(newServicio, infoManoDeObra, categoriaempleado);
               				}
                			
                			int oldIndiceProducto = Controladora.getInstance().getProductoIndice(servicio);
        	    			int oldIndiceProductoServicio = Controladora.getInstance().getProductoServicioIndice(servicio);
        	    			
        	    			Controladora.getInstance().getMisProductos().get(oldIndiceProducto).setBorrado(true);
        	    			Controladora.getInstance().getMisProductosServicio().get(oldIndiceProductoServicio).setBorrado(true);
        	    			
        	    			Controladora.getInstance().borrarProducto(oldIndiceProducto+1);
        	    			success.showAndWait();
        	    			cleanEverything(tipoProducto);
        	    			cancelCreation(event);
               			
                		}
    				}
    			}
    		}
    		
    		if(producto.get(0).getTipoProducto().equalsIgnoreCase("Matriz")) {
    			Estandar matriz = (Estandar) producto.get(0);
    			float existenciaInicial = matriz.getExistenciaInicial();
    			
    			if(!matriz.getNombre().equalsIgnoreCase(nombre) || matriz.getProveedorPrinClass() != proveedor || matriz.getRubroProductoClass() != rubro
        				|| matriz.getUnidadMedida() != unidad || matriz.getExistenciaMinima() != Float.parseFloat(exMin.getText())
        				|| matriz.getExistenciaMaxima() != Float.parseFloat(exMax.getText()) || matriz.getCostoDeCompra() != Float.parseFloat(textfield_costoPrecioCompraProducto.getText())
        				|| matriz.getCosto() != Float.parseFloat(textfield_preciosCostos.getText()) || matriz.getPrecio() != Float.parseFloat(textfield_preciosPrecio.getText())
        				|| checkPartida(matriz) || checkCostosIndirectos(matriz) || checkManoDeObra(matriz) || checkCombinaciones(matriz) 
        				|| matriz.getPrecioClass().getPorc_ganancia() != Integer.parseInt(textfield_preciosPorcientoGanancia.getText()) 
        				|| matriz.getPrecioClass().getItbis() != Integer.parseInt(textfield_preciosImpuestos.getText())){
    				alertModify.showAndWait();
    				if(alertModify.getResult() == ButtonType.YES) {
    	   				float existenciaActual = 0;
                		String existenciaMinima = exMin.getText();
                		String existenciaMaxima = exMax.getText();
                		float costoManoObra = 0;
                		float costoDeCompra = 0;
                		boolean fabricado = false;
                		ManoDeObra infoManoDeObra = null;
                		CategoriaEmpleado categoriaempleado = null;
                		
                		//Calculando la existencia actual de una matriz
                		for(Combinaciones c : combinacionFinal) {
                			existenciaActual += c.getExistenciaActual();
                		}
                		
                		//Calculando la mano de obra del producto
                		if(checkbox_generalProducible.isSelected()) {
                			try {
                				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
                				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
                				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
                				costoManoObra = Controladora.getInstance().calcularManoDeObra(nombreCategoria, tiempoMedida, tiempoCantidad);
                				categoriaempleado = Controladora.getInstance().buscarCategoria(Controladora.getInstance().
                						findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem()));
                				infoManoDeObra = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoriaempleado);
                			}catch(NullPointerException e) {
                				
                			}
                			fabricado = true;
                		}
                		else if(textfield_costoPrecioCompraProducto.getLength() > 0){
                			costoDeCompra = Float.parseFloat(textfield_costoPrecioCompraProducto.getText());
                		}
                		
                		//Calculando la partida de un producto
                		a.setAlertType(AlertType.WARNING);
                		Date date = null;
                		Partida partida = null;
                		if(checkbox_generalProducible.isSelected()) {
                			partida = new Partida();
                			for(String s : listview_partidaSelect.getItems()) {
                				String nombreSelect = Controladora.getInstance().findPartidaNombre(s);
                				String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
                				Estandar productoPart = (Estandar) Controladora.getInstance().buscarProducto(nombreSelect);
                				CantProductosUtilizados c = new CantProductosUtilizados(productoPart, Float.parseFloat(cantidadSelect));
                				
                				Controladora.getInstance().getMisCantProductosUtilizados().add(c);
                				Controladora.getInstance().guardarCantProductosUtilizadosSQL(productoPart, c);
                				partida.agregarProductoUtilizado(c);
                				float cantidadRestar = (productoPart.getExistenciaActual() - (Float.parseFloat(cantidadSelect) * existenciaActual));
                				int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(productoPart)+1;
                				
                				for(String itemOriginal : listview_partida.getItems()) {
        							String itemOriginalName = Controladora.getInstance().findPartidaNombre(itemOriginal);
        							if(itemOriginalName.equalsIgnoreCase(nombreSelect)) {
        								float itemOriginalCantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(itemOriginal));
        								float cantidadSumar = (itemOriginalCantidad + Float.parseFloat(cantidadSelect)*existenciaActual);
        								Controladora.getInstance().sumarExistenciaActual(cantidadSumar, indiceProducto);
        								int indiceEstandar = Controladora.getInstance().getProductoEstandarIndice(productoPart);
        								Controladora.getInstance().getMisProductosEstandar().get(indiceEstandar).setExistenciaActual(cantidadSumar);
        							}
        						}
                				
                				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
                			}
                		}
                		Controladora.getInstance().getMisPrecios().add(precio);
                    	Controladora.getInstance().guardarPrecioSQL(precio);
                		
                		//Registrando un producto tipo matriz
                		if(canRegister) {
                			Estandar newMatriz = new Estandar(existenciaActual, Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), existenciaInicial, date, costoDeCompra, fabricado, partida, codigo, nombre,
                				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costoManoObra, "", "", costoTotal, costoitbis);
                			for(Combinaciones c : combinacionFinal) {
                				newMatriz.getCombinaciones().add(c);
                				Controladora.getInstance().getMisCombinaciones().add(c);
                				Controladora.getInstance().guardarCombinacionesSQL(c);
                				for(Atributos atributo : c.getListaAtributos()) {
                					Controladora.getInstance().guardarCombinacionesAtributosSQL(atributo, c);
                				}
                			}
                			Controladora.getInstance().getMisProductos().add(newMatriz);
                			Controladora.getInstance().getMisProductosEstandar().add(newMatriz);
                			Controladora.getInstance().getMisProductosMatriz().add(newMatriz);
                			
                			Controladora.getInstance().guardarProductosSQL(newMatriz);
                			Controladora.getInstance().guardarEstandarSQL(newMatriz);
                			
                			for(CostoIndirectoProducto c : gastosIndirectos) {
                				newMatriz.getCostosIndirectos().add(c);
                				
                				Controladora.getInstance().getMisCostosIndirectos().add(c);
                				Controladora.getInstance().guardarCostoIndirectoSQL(newMatriz, c);
                			}
                			
                			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(newMatriz, proveedor);
                			Controladora.getInstance().guardarRubroProductoSQL(newMatriz, rubro);
                			Controladora.getInstance().guardarPrecioProductoSQL(newMatriz, precio);
                			
                			for(Combinaciones c : combinacionFinal) {
                				Controladora.getInstance().guardarMatrizSQL(newMatriz, c);
                			}
                			
                			
                			if(infoManoDeObra != null) {
                				Controladora.getInstance().getMisManosDeObras().add(infoManoDeObra);
                				newMatriz.setInfoManoDeObra(infoManoDeObra);
                				Controladora.getInstance().guardarManoDeObraSQL(infoManoDeObra);
                				Controladora.getInstance().guardarManoDeObraProductoSQL(newMatriz, infoManoDeObra, categoriaempleado);
                			}
                			
                			if(checkbox_generalProducible.isSelected()) {
                				Controladora.getInstance().getMisPartidas().add(partida);
                				Controladora.getInstance().guardarPartidaSQL();
                				for(CantProductosUtilizados c : partida.getListaMateriales()) {
                					Controladora.getInstance().guardarPartidaProdutilSQL(partida, c);
                				}
                				Controladora.getInstance().guardarProductoPartida(newMatriz, partida);
                			}	
                			
                			Controladora.getInstance().guardarPrecioProductoSQL(newMatriz, precio);
                			
                			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(newMatriz, proveedor);
                			
                			Controladora.getInstance().guardarRubroProductoSQL(newMatriz, rubro);
                			
                			int oldIndiceProducto = Controladora.getInstance().getProductoIndice(matriz);
        	    			int oldIndiceProductoEstandar = Controladora.getInstance().getProductoEstandarIndice(matriz);
        	    			
        	    			Controladora.getInstance().getMisProductos().get(oldIndiceProducto).setBorrado(true);
        	    			Controladora.getInstance().getMisProductosEstandar().get(oldIndiceProductoEstandar).setBorrado(true);
        	    			
        	    			Controladora.getInstance().borrarProducto(oldIndiceProducto+1);
        	    			success.showAndWait();
        	    			cleanEverything(tipoProducto);
        	    			cancelCreation(event);
                		}
    				}
    			}
    		}
    	}
    	
    	else {
    		//Inicio del registro
    		for(Producto producto : Controladora.getInstance().getMisProductos()) {
    			if(producto.getCodigo().equalsIgnoreCase(codigo)) {
    				a.setAlertType(AlertType.WARNING);
    				a.setContentText("Este codigo ya existe");
        			a.show();
        			canRegister = false;
    			}
    		}
    		if(canRegister) {
    			if(tipoProducto.equalsIgnoreCase("Estandar")) {
            		String existenciaActual = exAct.getText();
            		String existenciaMinima = exMin.getText();
            		String existenciaMaxima = exMax.getText();
            		float costoDeCompra = 0;
            		float costoManoObra = 0;
            		boolean fabricado = false;
            		ManoDeObra infoManoDeObra = null;
            		CategoriaEmpleado categoriaempleado = null;
            		if(checkbox_generalProducible.isSelected()) {
            			try {
            				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
            				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
            				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
            				costoManoObra = Controladora.getInstance().calcularManoDeObra(nombreCategoria, tiempoMedida, tiempoCantidad);
            				categoriaempleado = Controladora.getInstance().buscarCategoria(Controladora.getInstance().
            						findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem()));
            				infoManoDeObra = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoriaempleado);
            			}catch(NullPointerException e) {
            				
            			}
            			fabricado = true;
            		}
            		else if(textfield_costoPrecioCompraProducto.getLength() > 0){
            			costoDeCompra = Float.parseFloat(textfield_costoPrecioCompraProducto.getText());
            		}
            		a.setAlertType(AlertType.WARNING);
            		if(Integer.parseInt(existenciaMinima) > Integer.parseInt(existenciaMaxima)) {
            			a.setContentText("La existencia minima no puede ser mayor que la maxima");
            			a.show();
            			canRegister = false;
            		}
            		else if(Integer.parseInt(existenciaActual) < Integer.parseInt(existenciaMinima)){
            			a.setContentText("La existencia actual no puede ser menor que la existencia minima");
            			a.show();
            			canRegister = false;
            		}
            		else if(Integer.parseInt(existenciaActual) > Integer.parseInt(existenciaMaxima)){
            			a.setContentText("La existencia actual no puede ser mayor que la existencia maxima");
            			a.show();
            			canRegister = false;
            		}
            		else if(Integer.parseInt(existenciaMaxima) < Integer.parseInt(existenciaMinima)){
            			a.setContentText("La existencia maxima no puede ser menor que la existencia minima");
            			a.show();
            			canRegister = false;
            		}
            		Controladora.getInstance().getMisPrecios().add(precio);
                	Controladora.getInstance().guardarPrecioSQL(precio);
            		Date date = null;
            		Partida partida = null;	
            		if(checkbox_generalProducible.isSelected()) {
            			partida = new Partida();
            			for(String s : listview_partidaSelect.getItems()) {
            				String nombreSelect = Controladora.getInstance().findPartidaNombre(s);
            				String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
            				Estandar productoPart = (Estandar) Controladora.getInstance().buscarProducto(nombreSelect);
            				CantProductosUtilizados c = new CantProductosUtilizados(productoPart, Float.parseFloat(cantidadSelect));
            				
            				Controladora.getInstance().getMisCantProductosUtilizados().add(c);
            				Controladora.getInstance().guardarCantProductosUtilizadosSQL(productoPart, c);
            				partida.agregarProductoUtilizado(c);
            				float cantidadRestar = (productoPart.getExistenciaActual() - (Float.parseFloat(cantidadSelect) * Float.parseFloat(existenciaActual)));
            				int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(productoPart)+1;
            				
            				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
            			}
            		}
            		
            		
            		//No se registra nombre, fecha, y muchas otras cosas
            		if(canRegister) {
            			Estandar estandar = new Estandar(Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), Float.parseFloat(existenciaActual), date, costoDeCompra, fabricado, partida, codigo, nombre,
            				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costoManoObra, "", "", costoTotal, costoitbis);
            			
            			Controladora.getInstance().getMisProductosEstandar().add(estandar);
            			Controladora.getInstance().getMisProductos().add(estandar);
            			
            			Controladora.getInstance().guardarProductosSQL(estandar);
            			Controladora.getInstance().guardarEstandarSQL(estandar);
            			
            			if(foto != null)
            			{
            				estandar.setFoto(foto);
            				Controladora.getInstance().guardarImagenProductoSQL(foto, estandar);
            			}
            			
            			for(CostoIndirectoProducto c : gastosIndirectos) {
            				estandar.getCostosIndirectos().add(c);
            				
            				Controladora.getInstance().getMisCostosIndirectos().add(c);
            				Controladora.getInstance().guardarCostoIndirectoSQL(estandar, c);
            			}
            			
            			if(infoManoDeObra != null) {
            				Controladora.getInstance().getMisManosDeObras().add(infoManoDeObra);
            				estandar.setInfoManoDeObra(infoManoDeObra);
            				Controladora.getInstance().guardarManoDeObraSQL(infoManoDeObra);
            				Controladora.getInstance().guardarManoDeObraProductoSQL(estandar, infoManoDeObra, categoriaempleado);
            			}
            			if(checkbox_generalProducible.isSelected()) {
            				
            				
            				
            				Controladora.getInstance().getMisPartidas().add(partida);
            				Controladora.getInstance().guardarPartidaSQL();
            				for(CantProductosUtilizados c : partida.getListaMateriales()) {
            					Controladora.getInstance().guardarPartidaProdutilSQL(partida, c);
            				}
            				Controladora.getInstance().guardarProductoPartida(estandar, partida);
            			}	
            			
            			Controladora.getInstance().guardarPrecioProductoSQL(estandar, precio);
            			
            			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(estandar, proveedor);
            			
            			Controladora.getInstance().guardarRubroProductoSQL(estandar, rubro);
            		
            		
            		}
            	}
            	
            	else if(tipoProducto.equalsIgnoreCase("Kit")) {
            		String existenciaActual = exAct.getText();
            		String existenciaMinima = exMin.getText();
            		String existenciaMaxima = exMax.getText();
            		float costo = 0;
            		a.setAlertType(AlertType.WARNING);
            		if(Integer.parseInt(existenciaMinima) > Integer.parseInt(existenciaMaxima)) {
            			a.setContentText("La existencia minima no puede ser mayor que la maxima");
            			a.show();
            			canRegister = false;
            		}
            		else if(Integer.parseInt(existenciaActual) < Integer.parseInt(existenciaMinima)){
            			a.setContentText("La existencia actual no puede ser menor que la existencia minima");
            			a.show();
            			canRegister = false;
            		}
            		else if(Integer.parseInt(existenciaActual) > Integer.parseInt(existenciaMaxima)){
            			a.setContentText("La existencia actual no puede ser mayor que la existencia maxima");
            			a.show();
            			canRegister = false;
            		}
            		else if(Integer.parseInt(existenciaMaxima) < Integer.parseInt(existenciaMinima)){
            			a.setContentText("La existencia maxima no puede ser menor que la existencia minima");
            			a.show();
            			canRegister = false;
            		}
            		else if(listview_partidaSelect.getItems().size() == 0) {
            			a.setContentText("Debe elegir los productos que conforman el kit");
            			a.show();
            			canRegister = false;
            		}
            		Controladora.getInstance().getMisPrecios().add(precio);
                	Controladora.getInstance().guardarPrecioSQL(precio);
            		Date date = null;
            		ArrayList<CantProductosUtilizados> productsForKit = new ArrayList<>();
            		for(String item : listview_partidaSelect.getItems()) {
            			String nombreItem = Controladora.getInstance().findPartidaNombre(item);
            			String cantidad = Controladora.getInstance().findPartidaCantidad(item);
            			for(Estandar p : Controladora.getInstance().getMisProductosEstandar()) {
            				if(!p.isBorrado()) {
            					if(nombreItem.equals(p.getNombre())) {
            						CantProductosUtilizados c = new CantProductosUtilizados(p, Float.parseFloat(cantidad));
            						Controladora.getInstance().getMisCantProductosUtilizados().add(c);
            						Controladora.getInstance().guardarCantProductosUtilizadosSQL(p, c);
            						productsForKit.add(c);
            						float cantidadRestar = (p.getExistenciaActual() - (Float.parseFloat(cantidad) * Float.parseFloat(existenciaActual)));
            						int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(p)+1;
                				
            						Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
            					}
            				}
            				
            			}
            		}
            		//Visitar esto nuevamente
            		if(canRegister) {
            			Kit kit = new Kit(productsForKit, Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), Float.parseFloat(existenciaActual), date, codigo, nombre,
            				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costo, "", "", costoTotal, costoitbis);
            			
            			Controladora.getInstance().getMisProductos().add(kit);
            			Controladora.getInstance().guardarProductosSQL(kit);
            			
            			Controladora.getInstance().getMisProductosKit().add(kit);
            			Controladora.getInstance().guardarKitSQL(kit);
            			
            			if(foto != null)
            			{
            				kit.setFoto(foto);
            				Controladora.getInstance().guardarImagenProductoSQL(foto, kit);
            			}
            			
            			for(CostoIndirectoProducto c : gastosIndirectos) {
            				kit.getCostosIndirectos().add(c);
            				
            				Controladora.getInstance().getMisCostosIndirectos().add(c);
            				Controladora.getInstance().guardarCostoIndirectoSQL(kit, c);
            			}
            			
            			Controladora.getInstance().guardarRubroProductoSQL(kit, rubro);
            			Controladora.getInstance().guardarPrecioProductoSQL(kit, precio);
            			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(kit, proveedor);
            			
            			for(CantProductosUtilizados c : productsForKit) {
            				Controladora.getInstance().guardarKitProductosSQL(kit, c);
            			}
            			
            		}
            	}
            	
            	else if(tipoProducto.equalsIgnoreCase("Servicio")) {
            		//float costo = 0;
            		float costoManoObra = 0;
            		ManoDeObra infoManoDeObra = null;
            		CategoriaEmpleado categoriaempleado = null;
            		Controladora.getInstance().getMisPrecios().add(precio);
                	Controladora.getInstance().guardarPrecioSQL(precio);
            		ArrayList<CantProductosUtilizados> productsForServicio = new ArrayList<>();
            		for(String item : listview_partidaSelect.getItems()) {
            			String nombreItem = Controladora.getInstance().findPartidaNombre(item);
            			String cantidadItem = Controladora.getInstance().findPartidaCantidad(item);
            			for(Estandar p : Controladora.getInstance().getMisProductosEstandar()) {
            				if(p.isBorrado()) {
            					if(nombreItem.equals(p.getNombre())) {
            						CantProductosUtilizados c = new CantProductosUtilizados(p, Float.parseFloat(cantidadItem));
            				
            						Controladora.getInstance().getMisCantProductosUtilizados().add(c);
            						Controladora.getInstance().guardarCantProductosUtilizadosSQL(p, c);
            					
            						productsForServicio.add(c);
            					
            						float cantidadRestar = (p.getExistenciaActual() - (Float.parseFloat(cantidadItem)));
            						int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(p)+1;
                				
            						Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);

            					}
            				}
            				
            			}
            		}
            		if(canRegister) {
            			//costo = Float.parseFloat(textfield_preciosCostos.getText());
            			a.setAlertType(AlertType.WARNING);
            			if(combobox_costosEncargadosFabricacion.getSelectionModel().isEmpty()) {
            				a.setContentText("Eliga la categoria de empleado que ejercera este servicio.");
                			a.show();
                			canRegister = false;
            			}
            			else {
            				CategoriaEmpleado categoriaEmpleado = Controladora.getInstance().buscarCategoria(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().substring(0, combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().indexOf(":")));
            			
            				System.out.println(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().substring(0, combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().indexOf(":")));
            			
            				Servicio servicio = new Servicio(codigo, nombre, descripcion, rubro, tipoProducto, proveedor, null, "", unidad, precio, "", codigoBarra,
            					descripcion, categoriaEmpleado, productsForServicio, costoTotal, costoitbis);
            				Controladora.getInstance().addProductoServicio(servicio);
            				Controladora.getInstance().addProducto(servicio);
            			
            				Controladora.getInstance().guardarProductosSQL(servicio);
            				Controladora.getInstance().guardarServiciosSQL(servicio, categoriaEmpleado);
            				Controladora.getInstance().guardarRubroProductoSQL(servicio, rubro);
                			Controladora.getInstance().guardarPrecioProductoSQL(servicio, precio);
                			
                			if(foto != null)
                			{
                				servicio.setFoto(foto);
                				Controladora.getInstance().guardarImagenProductoSQL(foto, servicio);
                			}
                			
                			for(CostoIndirectoProducto c : gastosIndirectos) {
                				servicio.getCostosIndirectos().add(c);
                				
                				Controladora.getInstance().getMisCostosIndirectos().add(c);
                				Controladora.getInstance().guardarCostoIndirectoSQL(servicio, c);
                			}
            			
            				for(CantProductosUtilizados c : servicio.getMaterialesUtilizados()) {
            					Controladora.getInstance().guardarServiciosMaterialesSQL(servicio, c);
            				}
            			
            				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
            				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
            				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
            				costoManoObra = Controladora.getInstance().calcularManoDeObra(nombreCategoria, tiempoMedida, tiempoCantidad);
            				categoriaempleado = Controladora.getInstance().buscarCategoria(Controladora.getInstance().
        						findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem()));
            				infoManoDeObra = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoriaempleado);
        				
            				if(infoManoDeObra != null) {
            					Controladora.getInstance().getMisManosDeObras().add(infoManoDeObra);
            					servicio.setInfoManoDeObra(infoManoDeObra);
            					Controladora.getInstance().guardarManoDeObraSQL(infoManoDeObra);
            					Controladora.getInstance().guardarManoDeObraServicioSQL(servicio, infoManoDeObra, categoriaempleado);
            				}
            			}
            		}
            	}
            	
            	else if(tipoProducto.equalsIgnoreCase("Matriz")) {
            		float existenciaActual = 0;
            		String existenciaMinima = exMin.getText();
            		String existenciaMaxima = exMax.getText();
            		float costoManoObra = 0;
            		float costoDeCompra = 0;
            		boolean fabricado = false;
            		ManoDeObra infoManoDeObra = null;
            		CategoriaEmpleado categoriaempleado = null;
            		
            		//Calculando la existencia actual de una matriz
            		for(Combinaciones c : combinacionFinal) {
            			existenciaActual += c.getExistenciaActual();
            		}
            		
            		//Calculando la mano de obra del producto
            		if(checkbox_generalProducible.isSelected()) {
            			try {
            				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
            				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
            				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
            				costoManoObra = Controladora.getInstance().calcularManoDeObra(nombreCategoria, tiempoMedida, tiempoCantidad);
            				categoriaempleado = Controladora.getInstance().buscarCategoria(Controladora.getInstance().
            						findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem()));
            				infoManoDeObra = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoriaempleado);
            			}catch(NullPointerException e) {
            				
            			}
            			fabricado = true;
            		}
            		else if(textfield_costoPrecioCompraProducto.getLength() > 0){
            			costoDeCompra = Float.parseFloat(textfield_costoPrecioCompraProducto.getText());
            		}
            		
            		//Calculando la partida de un producto
            		a.setAlertType(AlertType.WARNING);
            		Date date = null;
            		Partida partida = null;
            		if(checkbox_generalProducible.isSelected()) {
            			partida = new Partida();
            			for(String s : listview_partidaSelect.getItems()) {
            				String nombreSelect = Controladora.getInstance().findPartidaNombre(s);
            				String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
            				Estandar productoPart = (Estandar) Controladora.getInstance().buscarProducto(nombreSelect);
            				CantProductosUtilizados c = new CantProductosUtilizados(productoPart, Float.parseFloat(cantidadSelect));
            				
            				Controladora.getInstance().getMisCantProductosUtilizados().add(c);
            				Controladora.getInstance().guardarCantProductosUtilizadosSQL(productoPart, c);
            				partida.agregarProductoUtilizado(c);
            				float cantidadRestar = (productoPart.getExistenciaActual() - (Float.parseFloat(cantidadSelect) * existenciaActual));
            				int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(productoPart)+1;
            				
            				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
            			}
            		}
            		Controladora.getInstance().getMisPrecios().add(precio);
                	Controladora.getInstance().guardarPrecioSQL(precio);
            		
            		//Registrando un producto tipo matriz
            		if(canRegister) {
            			Estandar matriz = new Estandar(existenciaActual, Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), existenciaActual, date, costoDeCompra, fabricado, partida, codigo, nombre,
            				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costoManoObra, "", "", costoTotal, costoitbis);
            			for(Combinaciones c : combinacionFinal) {
            				matriz.getCombinaciones().add(c);
            				Controladora.getInstance().getMisCombinaciones().add(c);
            				Controladora.getInstance().guardarCombinacionesSQL(c);
            				for(Atributos atributo : c.getListaAtributos()) {
            					Controladora.getInstance().guardarCombinacionesAtributosSQL(atributo, c);
            				}
            			}
            			Controladora.getInstance().getMisProductos().add(matriz);
            			Controladora.getInstance().getMisProductosEstandar().add(matriz);
            			Controladora.getInstance().getMisProductosMatriz().add(matriz);
            			
            			Controladora.getInstance().guardarProductosSQL(matriz);
            			Controladora.getInstance().guardarEstandarSQL(matriz);
            			
            			if(foto != null)
            			{
            				matriz.setFoto(foto);
            				Controladora.getInstance().guardarImagenProductoSQL(foto, matriz);
            			}
            			
            			for(CostoIndirectoProducto c : gastosIndirectos) {
            				matriz.getCostosIndirectos().add(c);
            				
            				Controladora.getInstance().getMisCostosIndirectos().add(c);
            				Controladora.getInstance().guardarCostoIndirectoSQL(matriz, c);
            			}
            			
            			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(matriz, proveedor);
            			Controladora.getInstance().guardarRubroProductoSQL(matriz, rubro);
            			Controladora.getInstance().guardarPrecioProductoSQL(matriz, precio);
            			
            			for(Combinaciones c : combinacionFinal) {
            				Controladora.getInstance().guardarMatrizSQL(matriz, c);
            			}
            			
            			
            			if(infoManoDeObra != null) {
            				Controladora.getInstance().getMisManosDeObras().add(infoManoDeObra);
            				matriz.setInfoManoDeObra(infoManoDeObra);
            				Controladora.getInstance().guardarManoDeObraSQL(infoManoDeObra);
            				Controladora.getInstance().guardarManoDeObraProductoSQL(matriz, infoManoDeObra, categoriaempleado);
            			}
            			
            			if(checkbox_generalProducible.isSelected()) {
            				Controladora.getInstance().getMisPartidas().add(partida);
            				Controladora.getInstance().guardarPartidaSQL();
            				for(CantProductosUtilizados c : partida.getListaMateriales()) {
            					Controladora.getInstance().guardarPartidaProdutilSQL(partida, c);
            				}
            				Controladora.getInstance().guardarProductoPartida(matriz, partida);
            			}	
            			
            			Controladora.getInstance().guardarPrecioProductoSQL(matriz, precio);
            			
            			Controladora.getInstance().guardarProveedorPrincipalProductoSQL(matriz, proveedor);
            			
            			Controladora.getInstance().guardarRubroProductoSQL(matriz, rubro);
            		}
            	}
            	
            	if(canRegister) {
            		success.showAndWait();
            		cleanEverything(tipoProducto);
            	}
    		}
    	}
    	
    	
    	
		
    	
    }
    
    /**FUNCIONES CREACION DE PRODUCTO**/  
    
    public void tipoProducto(ActionEvent event) {
    	checkbox_generalProducible.setSelected(false);
    	
    	if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Estandar")) {
    		tab_combinaciones.setDisable(true);
    		
    		tab_partida.setDisable(true);
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		exAct.setDisable(false);
    		exMin.setDisable(false);
    		exMax.setDisable(false);
    		checkbox_generalProducible.setDisable(false);
    		checkbox_generalProducible.setSelected(false);
    		textfield_generalProveedor.setDisable(false);
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalUnidad.setDisable(false);
    		button_BuscarUnidadMedida.setDisable(false);
    		textfield_costoPrecioCompraProducto.setDisable(false);
    		
    		exAct.setText("");
    		exMin.setText("");
    		exMax.setText("");
    		
    		textfield_generalProveedor.setText("");
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalProveedor.setDisable(false);
    	}
    	else if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Kit")) {
    		exAct.setDisable(false);
    		exMin.setDisable(false);
    		exMax.setDisable(false);
    		
    		tab_partida.setDisable(false);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		textfield_generalProveedor.setDisable(false);
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalUnidad.setDisable(false);
    		button_BuscarUnidadMedida.setDisable(false);
    		textfield_costoPrecioCompraProducto.setDisable(false);
    		
    		checkbox_generalProducible.setDisable(true);
    		checkbox_generalProducible.setSelected(true);
    		activarPartida(null);
    		
    		exAct.setText("");
    		exMin.setText("");
    		exMax.setText("");
    		
    		textfield_generalProveedor.setText("");
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalProveedor.setDisable(false);
    	}
    	else if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Servicio")) {
    		exAct.setDisable(true);
    		exMin.setDisable(true);
    		exMax.setDisable(true);
    		tab_combinaciones.setDisable(true);
    		//textfield_generalProveedor.setDisable(true);
    		//button_productoBuscarProveedor.setDisable(true);
    		textfield_generalUnidad.setDisable(true);
    		button_BuscarUnidadMedida.setDisable(true);
    		tab_partida.setDisable(false);
    		textfield_costoPrecioCompraProducto.setDisable(true);
    		
    		radiobutton_costosDirectos.setDisable(false);
    		radiobutton_costosDirectos.setSelected(true);
    		pane_costosDirectos.setVisible(true);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(false);
    		pane_costosIndirectos.setVisible(false);
    		
    		checkbox_generalProducible.setDisable(true);
    		checkbox_generalProducible.setSelected(true);
    		activarPartida(null);
    		
    		exAct.setText("0");
    		exMin.setText("0");
    		exMax.setText("0");
    		
    		textfield_generalProveedor.setText(Controladora.getInstance().getMiEmpresa().getNombre());
    		textfield_generalProveedor.setDisable(true);
    		button_productoBuscarProveedor.setDisable(true);
    		
    		textfield_generalUnidad.setText("");
    	}
    	else if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Matriz")) {
    		tab_combinaciones.setDisable(false);
    		exAct.setDisable(true);
    		exMin.setDisable(false);
    		exMax.setDisable(false);
    		tab_partida.setDisable(true);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		
    		textfield_generalProveedor.setDisable(false);
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalUnidad.setDisable(false);
    		button_BuscarUnidadMedida.setDisable(false);
    		textfield_costoPrecioCompraProducto.setDisable(false);
    		checkbox_generalProducible.setDisable(false);
    		checkbox_generalProducible.setSelected(false);
    		activarPartida(null);
    		
    		exAct.setText("");
    		exMin.setText("");
    		exMax.setText("");
    		
    		textfield_generalProveedor.setText("");
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalProveedor.setDisable(false);
    	}
    }
    
    //FUNCIONES DE CREACION DE COMBINACION
    public void habilitar_busqueda1(KeyEvent event) {
    	if(!textfield_busquedaFamilia1.getText().isEmpty())
    	{
    		button_buscarFamilia1.setDisable(false);
    	}
    }
    
    public void habilitar_busqueda2(KeyEvent event) {
    	if(!textfield_busquedaFamilia2.getText().isEmpty())
    	{
    		button_buscarFamilia2.setDisable(false);
    	}
    }
    
    public void habilitar_busqueda3(KeyEvent event) {
    	if(!textfield_busquedaFamilia3.getText().isEmpty())
    	{
    		button_buscarFamilia3.setDisable(false);
    	}
    }
    
    public void rellenarListview_atributos1(ActionEvent event) {
    	String familia = textfield_busquedaFamilia1.getText();
    	ArrayList<String> a = new ArrayList<>();
    	ArrayList<Atributos> b = Controladora.getInstance().getMisAtributos();
    	int i;
    	listView_atributos1.getItems().clear();
    	for(i=0; i<b.size(); i++)
    	{
    		if(b.get(i).getGrupo().equalsIgnoreCase(familia))
    		{
    			a.add(b.get(i).getNombre());
    		}
    	}
    		
    	listView_atributos1.getItems().addAll(a);
    	
    }
    
    public void rellenarListview_atributos2(ActionEvent event) {
    		String familia = textfield_busquedaFamilia2.getText();
    		ArrayList<String> a = new ArrayList<>();
    		ArrayList<Atributos> b = Controladora.getInstance().getMisAtributos();
    		int i;
    		listView_atributos2.getItems().clear();
    		for(i=0; i<b.size(); i++)
    		{
    			if(b.get(i).getGrupo().equalsIgnoreCase(familia))
    			{
    				a.add(b.get(i).getNombre());
    			}
    		}
    		
    		listView_atributos2.getItems().addAll(a);
    }
    
    public void rellenarListview_atributos3(ActionEvent event) {
    		String familia = textfield_busquedaFamilia3.getText();
    		ArrayList<String> a = new ArrayList<>();
    		ArrayList<Atributos> b = Controladora.getInstance().getMisAtributos();
    		int i;
    		listView_atributos3.getItems().clear();
    		for(i=0; i<b.size(); i++)
    		{
    			if(b.get(i).getGrupo().equalsIgnoreCase(familia))
    			{
    				a.add(b.get(i).getNombre());
    			}
    		}
    		
    		listView_atributos3.getItems().addAll(a);
    }
    
    public void habilitar_combinar(MouseEvent event) {
    	listView_atributos1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String atributo1 = listView_atributos1.getSelectionModel().getSelectedItem();
    	listView_atributos2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String atributo2 = listView_atributos2.getSelectionModel().getSelectedItem();
    	listView_atributos3.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String atributo3 = listView_atributos3.getSelectionModel().getSelectedItem();
    	
    	if(atributo1!=null && (atributo2!=null || atributo3!=null))
    	{
    		button_combinar.setDisable(false);
    	}
    	
    	if(atributo2!=null && (atributo1!=null || atributo3!=null))
    	{
    		button_combinar.setDisable(false);
    	}
    	
    	if(atributo3!=null && (atributo1!=null || atributo2!=null))
    	{
    		button_combinar.setDisable(false);
    	}
    	
    }
    
    public void createCombination(ActionEvent event) {
    	
    	if(textfield_numSerie.getText().isEmpty() == true) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Informaci�n faltante");
    		alert.setContentText("Debe ingresar el n�mero de serie de la combinaci�n.");

    		alert.showAndWait();
    	}
    	
    	else if(textfield_cantidadComb.getText().isEmpty() == true) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Informaci�n faltante");
    		alert.setContentText("Debe ingresar la cantidad de la combinaci�n.");

    		alert.showAndWait();
    	}
    	else {
    	String atributo1 = listView_atributos1.getSelectionModel().getSelectedItem();
    	String atributo2 = listView_atributos2.getSelectionModel().getSelectedItem();
    	String atributo3 = listView_atributos3.getSelectionModel().getSelectedItem();
    	//int i;
    	if((!textfield_numSerie.getText().isEmpty() && !textfield_cantidadComb.getText().isEmpty()))
    	{
    		String num = textfield_numSerie.getText();
    		if(!Controladora.getInstance().validarNumeroSerie(num) || !checkNumSerie(num)) {
    			Alert alert = new Alert(AlertType.WARNING, "Este n�mero de serie esta en uso.");
    			alert.showAndWait();
    		}
    		else {
    			float cant = Float.parseFloat(textfield_cantidadComb.getText());
    			ArrayList<Atributos> a = Controladora.getInstance().getMisAtributos();
    			ArrayList<Atributos> b = new ArrayList<>(); 
    			int i1;
    			for(i1=0; i1<a.size(); i1++)
    			{
    				if(a.get(i1).getNombre().equalsIgnoreCase(atributo1) && atributo1!=null){
    					b.add(a.get(i1));
    				}    			
    				if(a.get(i1).getNombre().equalsIgnoreCase(atributo2) && atributo2!=null){
    					b.add(a.get(i1));
    				}
    				if(a.get(i1).getNombre().equalsIgnoreCase(atributo3) && atributo3!=null){
    					b.add(a.get(i1));
    				}
    			}
    			//System.out.println(atributo1 + " " + atributo2 + " " + atributo3);
    			Combinaciones comb = new Combinaciones(num, cant, b);
    			combinacionFinal.add(comb);
    			String atri1 =  b.get(0).getGrupo() + ": " + b.get(0).getNombre();
    			String atri2 = ", " + b.get(1).getGrupo() + ": " + b.get(1).getNombre();
    			String pcomb;
    			pcomb = comb.numeroSerie + " " + atri1 + atri2 + ", " + "Existencia: " + comb.getExistenciaActual(); 
    			if(b.size()>2){
    				String atri3 = ", " + b.get(2).getGrupo() + ": " + b.get(2).getNombre();
    			
    				pcomb = comb.numeroSerie + " " + atri1 + atri2 + atri3 + ", " + "Existencia: " + comb.getExistenciaActual(); 
    			}
    			else{
    				pcomb = comb.numeroSerie + " " + atri1 + atri2 + ", " + "Existencia: " + comb.getExistenciaActual();
    			}
    		 
    			listView_combinaciones.getItems().add(pcomb);
    			textfield_numSerie.setText("");
    			textfield_cantidadComb.setText("");
    			listView_atributos1.getSelectionModel().clearSelection();
    			listView_atributos2.getSelectionModel().clearSelection();
    			listView_atributos3.getSelectionModel().clearSelection();
    		}		
    	}
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
    
    public void eliminarAtributo(ActionEvent event) {
    	int index = tableView_Atributos.getSelectionModel().getSelectedIndex();
    	tableView_Atributos.getItems().remove(index);
    }
    
    public void activar_nuevoAtributo(KeyEvent event) {
    	if(!textfield_register_familia.getText().isEmpty() && !textfield_registrar_atributo.getText().isEmpty()) {
    		button_agregar_atributo.setDisable(false);
    	}
    	else {
    		button_agregar_atributo.setDisable(true);
    	}
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
    	}
    	Atributos a = new Atributos(nombreAtributo, g);
    	data.add(a);
    	Controladora.getInstance().addAtributo(a);
    	tablecolumn_atributogrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
    	tablecolumn_atributonombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tableView_atributos.getItems().add(a);
    	tableView_atributos.refresh();
    	textfield_registrar_atributo.setText("");
    	textfield_register_familia.setText("");
    	//pane_rubroCreate.setDisable(true);
    	button_agregar_atributo.setDisable(true);
    }
    
    public void cerrarBusquedaAtributo(ActionEvent event) {
    	titledpane_productoBuscarAtributo.setVisible(false);
    }
    
    public void abrirBusquedaAtributo(ActionEvent event) {
    	titledpane_productoBuscarAtributo.setVisible(true);
    }
    
    public boolean checkNumSerie(String numSerie) {
    	for(Combinaciones c : combinacionFinal) {
    		if(c.getNumeroSerie().equalsIgnoreCase(numSerie)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public String findNumeroSerie(String combinacion) {
    	int i = 0;
    	String result = "";
    	System.out.println(combinacion);
    	while(Character.isDigit(combinacion.charAt(i))) {
    		System.out.println(combinacion.charAt(i));
    		result += combinacion.charAt(i);
    		i++;
    	}
    	return result;
    }
    
    public void listview_viewCombinacionesClicked(MouseEvent event) {
    	if(!listView_combinaciones.getSelectionModel().getSelectedItem().isEmpty() && !modificado) {
    		button_borrarCombinacion.setDisable(false);
    	}
    	else {
    		button_borrarCombinacion.setDisable(true);
    	}
    }
    
    public void borrarCombinacion(ActionEvent event) {
    	String combinacionSelected = listView_combinaciones.getSelectionModel().getSelectedItem();
    	String numeroSerieCombinacion = findNumeroSerie(combinacionSelected);
    	listView_combinaciones.getItems().remove(combinacionSelected);
    	for(Combinaciones c : combinacionFinal) {
    		if(numeroSerieCombinacion.equalsIgnoreCase(c.getNumeroSerie())) {
    			combinacionFinal.remove(c);
    		}
    	}
    	button_borrarCombinacion.setDisable(true);
    }
    
    
    //COSTOS
	public void costosRadioButton(ActionEvent event) {
		if(radiobutton_costosDirectos.isSelected()) {
			pane_costosDirectos.setVisible(true);
			pane_costosIndirectos.setVisible(false);
		}
		else if(radiobutton_costosIndirectos.isSelected()) {
			pane_costosDirectos.setVisible(false);
			pane_costosIndirectos.setVisible(true);
		}
	}
    
    public void pasarDerCosto(MouseEvent event)
    {
    	String costo = listview_CostosGenerales.getSelectionModel().getSelectedItem();
    	listview_CostosSelect.getItems().add(costo);
    	listview_CostosGenerales.getItems().remove(costo);
    }
    
    public void pasarIzqCosto(MouseEvent event)
    {
    	String costo = listview_CostosSelect.getSelectionModel().getSelectedItem();
    	listview_CostosGenerales.getItems().add(costo);
    	listview_CostosSelect.getItems().remove(costo);
    }
    
    public void activarPasarDerCostoIndirecto(MouseEvent event)
    {
    	listview_GastosGeneralesIndirectos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String gastogeneral = listview_GastosGeneralesIndirectos.getSelectionModel().getSelectedItem();
    	
    	if(gastogeneral != null)
    	{
    		button_DerCostoIndirecto.setDisable(false);
    		button_IzqCostoIndirecto.setDisable(true);
    	}
    }
    
    public void activarPasarIzqCostoIndirecto(MouseEvent event)
    {
    	listview_CostosSelecIndirectos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	String gastogeneral = listview_CostosSelecIndirectos.getSelectionModel().getSelectedItem();
    	
    	if(gastogeneral != null)
    	{
    		button_IzqCostoIndirecto.setDisable(false);
    		button_DerCostoIndirecto.setDisable(true);
    	}
    }
    
    public void pasarDerCostoIndirecto(MouseEvent event)
    {
    	String costo = listview_GastosGeneralesIndirectos.getSelectionModel().getSelectedItem();
    	if(costo != null)
    	{
    		listview_CostosSelecIndirectos.getItems().add(costo);
        	listview_GastosGeneralesIndirectos.getItems().remove(costo);
    	}
    	if(listview_GastosGeneralesIndirectos.getSelectionModel().isEmpty())
    	{
    		button_DerCostoIndirecto.setDisable(true);
    	}
    	
    }
    
    public void pasarIzqCostoIndirecto(MouseEvent event)
    {
    	String costo = listview_CostosSelecIndirectos.getSelectionModel().getSelectedItem();
    	if(costo != null)
    	{
    		listview_GastosGeneralesIndirectos.getItems().add(costo);
        	listview_CostosSelecIndirectos.getItems().remove(costo);
    	}
    	if(listview_CostosSelecIndirectos.getSelectionModel().isEmpty())
    	{
    		button_IzqCostoIndirecto.setDisable(true);
    	}
    }
    
    public void agregarNuevosCostos(ActionEvent event)
    {
    	if(radiobutton_costosDirectos.isSelected())
    	{        		
			if(checkbox_generalProducible.isSelected() || combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equals("Servicio")) {
    			try {
    				float costoManoObra = 0;
    				CategoriaEmpleado categoria = null;
    				String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
    				String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
    				float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
    				for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
    					if(c.getNombre().equals(nombreCategoria)) {
    						categoria = c;
    						
    						if(tiempoMedida.equalsIgnoreCase("Minutos")) {
    							tiempoCantidad = tiempoCantidad / 60;
    						}
    						else if(tiempoMedida.equalsIgnoreCase("Segundos")) {
    							tiempoCantidad = tiempoCantidad / 3600; 
    						}
    						costoManoObra = c.getSueldo() * tiempoCantidad;
    					}
    				}
    				
    				ManoDeObra mano = new ManoDeObra(costoManoObra, tiempoCantidad, Date.valueOf(LocalDate.now()), categoria);
    				
    				textfield_TotalManoObra.setText(Float.toString(mano.getCosto()));
    				
    			}catch(NullPointerException e) {
    				
    			}
			}
			
    	}
    	
    	if((!textfield_costosValor.getText().isEmpty() && (!textfield_costoPrecioCompraProducto.getText().isEmpty() && !checkbox_generalProducible.isSelected())) || (!textfield_costosValor.getText().isEmpty() && (textfield_costoPrecioCompraProducto.isDisable() && checkbox_generalProducible.isSelected())))
    	{	
    		if(radiobutton_costosIndirectos.isSelected())
        	{
    			for(int i = 0; i < listview_CostosResumen.getItems().size(); i++) {
    				listview_CostosResumen.getItems().remove(i);
    				listview_CostosResumen.refresh();
    			}
 
        		gastosIndirectos = null;
        		gastosIndirectos = new ArrayList<>();
        		for(int i = 0; i < listview_CostosSelecIndirectos.getItems().size(); i++)
        		{
        			System.out.println("Klk");
        			
        			GastoGeneral enlistado = Controladora.getInstance().buscarGasto(listview_CostosSelecIndirectos.getItems().get(i));
        			
        			DecimalFormat df = new DecimalFormat("#.00");
        			
        			float atribucion = Float.parseFloat(df.format(Controladora.getInstance().calcularCostos(enlistado, Float.parseFloat(textfield_costosValor.getText()))));
        			
        			CostoIndirectoProducto nuevo = new CostoIndirectoProducto(enlistado.getNombre(), atribucion, null);
        			
        			gastosIndirectos.add(nuevo);
        			
        			String m = nuevo.getNombre() + " Costo: " + nuevo.getValor();
        			
        			listview_CostosResumen.getItems().add(m);
        		}
        	}
        }
    	else if(listview_CostosSelecIndirectos.getItems().size() > 0 && textfield_costosValor.getText().isEmpty()) {
    		Alert a = new Alert(AlertType.NONE); 
        	a.setAlertType(AlertType.WARNING);
        	a.setContentText("Necesita Ingresar el precio estimado del producto.");
        	a.show();
        	
    	}
    	if(textfield_costoPrecioCompraProducto.getText().isEmpty() && !checkbox_generalProducible.isSelected())
    	{
    		Alert b = new Alert(AlertType.NONE); 
        	b.setAlertType(AlertType.WARNING);
        	b.setContentText("Necesita Ingresar el costo de compra del producto.");
        	b.show();
    	}
    	
    }
    
    //FUNCIONES CREACION DE LA PARTIDA
    public void listview_PartidaClicked(MouseEvent event) {
    	if(!listview_partida.getSelectionModel().isEmpty()) {
    		button_partidaSendTo.setDisable(false);
    		textfield_partidaCantidad.setDisable(false);
    		
    		String selection = null;
        	int posicion;
        	Estandar p = null;
        	
        	if(listview_partida.getSelectionModel().getSelectedIndex() > -1)
        	{
        		posicion = listview_partida.getSelectionModel().getSelectedItem().indexOf("[");
        		selection = listview_partida.getSelectionModel().getSelectedItem().substring(0, posicion);
        		p = (Estandar) Controladora.getInstance().buscarProducto(selection);
        		
        		if(p.getUnidadMedida() != null)
        		{
        			if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Area"))
            		{
            			combobox_ConversorUnidad.getItems().clear();
            			combobox_ConversorUnidad.getItems().addAll("Sq Pulgadas", "Sq Pies", "Sq Yardas", "Sq Milimetros", "Sq Centimetros", "Sq Metros");
            			combobox_ConversorUnidad.getSelectionModel().select(p.getUnidadMedida().getNombre());
            		}
            		
            		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Longitud"))
            		{
            			combobox_ConversorUnidad.getItems().clear();
            			combobox_ConversorUnidad.getItems().addAll("Pulgadas", "Pies", "Yardas", "Milimetros", "Centimetros", "Metros");
            			combobox_ConversorUnidad.getSelectionModel().select(p.getUnidadMedida().getNombre());
            		}
            		
            		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Masa"))
            		{
            			combobox_ConversorUnidad.getItems().clear();
            			combobox_ConversorUnidad.getItems().addAll("Grano", "Onza", "Libra", "Miligramo", "Gramo", "Kilogramo");
            			combobox_ConversorUnidad.getSelectionModel().select(p.getUnidadMedida().getNombre());
            		}
            		
            		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Volumen"))
            		{
            			combobox_ConversorUnidad.getItems().clear();
            			combobox_ConversorUnidad.getItems().addAll("Pulgadas Cb", "Pies  Cb", "Yardas Cb", "Cuchara de t�", "Cuchara de madera", "Onza fluida", "Taza", "Medio litro", "Cuarto de gal�n", "Gal�n", "Barril", "Mil�metros cb", "Cent�metros cb", "Metros cb", "Mililitros", "Litros");
            			combobox_ConversorUnidad.getSelectionModel().select(p.getUnidadMedida().getNombre());
            		}
        		}
        		
        		else
        		{
        			combobox_ConversorUnidad.getItems().clear();
        		}
        	}
    	}
    	else if (listview_partida.getSelectionModel().isEmpty() || textfield_partidaCantidad.getLength() == 0) {
    		button_partidaSendTo.setDisable(true);
    		textfield_partidaCantidad.setDisable(true);
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
    
    public void filtrarUnidadMedida(MouseEvent event)
    {
    	
    }
    
    public void movePartida(MouseEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	boolean isAlreadySelected = false;
    	//String selection = null;
    	//int posicion;
    	//posicion = listview_partida.getSelectionModel().getSelectedItem().indexOf("[");
		//selection = listview_partida.getSelectionModel().getSelectedItem().substring(0, posicion);
    	
    	if(textfield_partidaCantidad.getText().isEmpty() == true) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Informaci�n faltante");
    		alert.setContentText("Debe ingresar datos en el campo de Cantidad.");

    		alert.showAndWait();
    	} 
    	else if(exAct.getText().isEmpty() == true) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Informaci�n faltante");
    		alert.setContentText("Debe ingresar datos en el campo de Existencia (Cantidad actual) en la pesta�a General.");

    		alert.showAndWait();
    	}
    	
    	else {
    	String select_items = listview_partida.getSelectionModel().getSelectedItem();
    	String cantidad = Controladora.getInstance().findPartidaCantidad(select_items);
    	String nameOriginal = Controladora.getInstance().findPartidaNombre(select_items);
    	String item_moved = "";
    	String tipoConversion = "";
    	//float costo = 0;
    	float cantidadConvertida = 0;
    	ArrayList<Estandar> estandar = Controladora.getInstance().searchProductsEstandar(nameOriginal.toLowerCase(), "Nombre");
    	if(estandar.get(0).getUnidadMedida() == null) {
    		tipoConversion = "";
    	}
    	else {
    		tipoConversion = combobox_ConversorUnidad.getSelectionModel().getSelectedItem();
    	}
    	switch(tipoConversion){
    		case "Pulgadas":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Pulgadas", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Pies":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Pies", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Yardas":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Yardas", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Milimetros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Milimetros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Centimetros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Centimetros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Metros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Metros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		
    		case "Pulgadas cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Pulgadas cb", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Pies cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Pies cb", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Cuchara de t�":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Cuchara de t�", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Onza fluida":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Onza fluida", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Taza":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Taza", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Medio litro":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Medio litro", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Cuarto de gal�n":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Cuarto de gal�n", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Gal�n":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Gal�n", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Barril":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Barril", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Mil�metros cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Mil�metros cb", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Cent�metros cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Cent�metros cb", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Metros cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Metros cb", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Mililitros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Mililitros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Litros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Litros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Cuchara de madera":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Cuchara de madera", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    			
    		case "Grano":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Grano", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Onza":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Onza", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Libra":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Libra", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Miligramo":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Miligramo", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Gramo":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Gramo", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Kilogramo":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Kilogramo", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;

    		case "Sq Pulgadas":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Sq Pulgadas", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Sq Pies":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Sq Pies", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Sq Yardas":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Sq Yardas", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Sq Milimetros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Sq Milimetros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Sq Centimetros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Sq Centimetros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Sq Metros":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Sq Metros", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    			
    		case "":
    			cantidadConvertida = Float.parseFloat(textfield_partidaCantidad.getText());
    			break;
    		
    	};
    	float existencia = 0;
    	if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Servicio")) {
    		existencia = 1;
    	}
    	else {
    		try {
    			existencia = Float.parseFloat(exAct.getText());
    		}
    		catch(NullPointerException e) {
    			Alert needExistencia = new Alert(AlertType.WARNING, "Determina la existencia del producto");
    			needExistencia.showAndWait();
    		}
    	}
   		
    	
    	
    	if(textfield_partidaCantidad.getLength() == 0) {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Eliga la cantida que utilizara.");
    		a.show();
    	}
    	else if(existencia == 0){
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Eliga la existencia actual del producto.");
    	}
    	else if(cantidadConvertida*existencia > Float.parseFloat(cantidad)) {
    		a.setAlertType(AlertType.ERROR);
			a.setContentText("La cantidad de materiales ha utilizar no son suficientes con la existencia actual.");
			a.show();
    	}
    	else {
    		int i = 0;
    		while(select_items.charAt(i) != ':') {
    			item_moved += select_items.charAt(i);
    			i++;
    		}
    		//Guardar este codigo para cuando guardemos un producto
    		/**for(int j = 0; j < Controladora.getInstance().getMisProductosEstandar().size(); j++) {
    			if(Controladora.getInstance().getMisProductosEstandar().get(j).equals(estandar.get(0))) {
    				Controladora.getInstance().getMisProductosEstandar().get(j).setExistenciaActual(
    					Controladora.getInstance().getMisProductosEstandar().get(j).getExistenciaActual() - Float.parseFloat(textfield_partidaCantidad.getText()));
    			}
    		}**/
    		DecimalFormat formatter = new DecimalFormat("0.00000");
    		if(estandar.get(0).getUnidadMedida() != null) {
    			
    			item_moved = nameOriginal + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + formatter.format(cantidadConvertida) + "]" + " (" + formatter.format(cantidadConvertida*existencia) + ")";
    		}
    		else {
    			item_moved = nameOriginal + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + formatter.format(cantidadConvertida) + "]" + " (" + formatter.format(cantidadConvertida*existencia) + ")";
    		}
    		
    		//e.getNombre() + "[" + "Unidad: " + e.getUnidadMedida().getAbreviatura() + ", disponibles: " + e.getExistenciaActual() + "]"
    		listview_partida.getItems().remove(listview_partida.getSelectionModel().getSelectedIndex());
    	
    		float cantidadRestante = (Float.parseFloat(cantidad) - (cantidadConvertida*existencia));
    		DecimalFormat formato1 = new DecimalFormat("0.0000");
    		if(Float.parseFloat(cantidad) != Float.parseFloat(textfield_partidaCantidad.getText())) {
    			if(estandar.get(0).getUnidadMedida() != null) {
    				listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
    					formato1.format(cantidadRestante) + "]");
    			}
    			else {
    				listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
        					formato1.format(cantidadRestante) + "]");
    			}
    			
    		
    			listview_partida.refresh();
    		}
    		for(String s : listview_partidaSelect.getItems()) {
    			String nameSelect = Controladora.getInstance().findPartidaNombre(s);
    			String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
    			if(nameSelect.equalsIgnoreCase(nameOriginal)) {
    				listview_partidaSelect.getItems().remove(s);
    				if(estandar.get(0).getUnidadMedida() != null) {
    					item_moved = nameSelect + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", Usando: " + 
    						(formato1.format(cantidadConvertida + Float.parseFloat(cantidadSelect))) + "]" + " (" + (formato1.format((cantidadConvertida + Float.parseFloat(cantidadSelect)) * existencia)) + ")";
    				}
    				else {
    					item_moved = nameSelect + "[" + "Unidad: " + "Unidad nula" + ", Usando: " + 
        						(formato1.format(cantidadConvertida + Float.parseFloat(cantidadSelect))) + "]" + " (" + (formato1.format((cantidadConvertida + Float.parseFloat(cantidadSelect)) * existencia)) + ")";
    				}
    				
    				listview_partidaSelect.getItems().add(item_moved);
    				isAlreadySelected = true;
    			}
    		}
    		if(!isAlreadySelected) {
    			listview_partidaSelect.getItems().addAll(item_moved);
    		}
    	
    		button_partidaSendTo.setDisable(true);
    		textfield_partidaCantidad.setDisable(true);
    		textfield_partidaCantidad.clear();
    	}
    	}
    }
    
    public void movePartidaSelect(MouseEvent event) {
    	String select_items = listview_partidaSelect.getSelectionModel().getSelectedItem();
    	String nombreSelect = Controladora.getInstance().findPartidaNombre(select_items);
    	String cantidad = Controladora.getInstance().findPartidaCantidad(select_items);
    	float existencia = Float.parseFloat(exAct.getText());
    	ArrayList<Estandar> estandar = Controladora.getInstance().searchProductsEstandar(nombreSelect.toLowerCase(), "Nombre");
    	DecimalFormat formato1 = new DecimalFormat("0.0000");
    	
    	String original = "";
    	float originalCantidad = 0;
    	for(String s : listview_partida.getItems()) {
    		String originalEx = Controladora.getInstance().findPartidaNombre(s);
    		if(originalEx.equals(nombreSelect)) {
    			originalCantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(s));
    		}
    	}
    	System.out.println("La cantidad del original es: " + originalCantidad);
    	if(estandar.get(0).getUnidadMedida() != null) {
    		if(modificado) {
    			original = nombreSelect + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
    	    			(formato1.format(originalCantidad)) + "]";
    		}
    		else {
    			original = nombreSelect + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
    			(formato1.format(estandar.get(0).getExistenciaActual() - (Float.parseFloat(cantidad)*existencia))) + "]";
    		}	
    	}
    	else {
    		if(modificado) {
    			original = nombreSelect + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
            			(formato1.format(estandar.get(0).getExistenciaActual())) + "]";
    		}
    		else {
    			original = nombreSelect + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
        			(formato1.format(estandar.get(0).getExistenciaActual() - (Float.parseFloat(cantidad)*existencia))) + "]";
    		}
    		
    	}
    	
    	listview_partidaSelect.getItems().remove(listview_partidaSelect.getSelectionModel().getSelectedItem());
    	listview_partida.getItems().remove(original);
    	for(String s : listview_partida.getItems()) {
    		System.out.println(s);
    		System.out.println("Esta es la original: " + original);
    	}
    	
    	
    	
    	if(estandar.get(0).getUnidadMedida() != null) {
    		if(modificado) {
    			listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
    					(formato1.format(originalCantidad + (Float.parseFloat(cantidad)*existencia))) + "]");
    		}
    		else {
    			listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
				(formato1.format(estandar.get(0).getExistenciaActual())) + "]");
    		}
    		
    	}
    	else {
    		if(modificado) {
    			listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
        				(formato1.format(estandar.get(0).getExistenciaActual() + (Float.parseFloat(cantidad)*existencia))) + "]");
    		}
    		else {
    			listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
    				(formato1.format(estandar.get(0).getExistenciaActual())) + "]");
    		}
    	}
    	
    	listview_partida.refresh();
    	button_partidaSendBack.setDisable(true);
    }
    
    public ArrayList<CostoDirecto> getGastosDirectos() {
		return gastosDirectos;
	}

	public ArrayList<CostoIndirectoProducto> getGastosIndirectos() {
		return gastosIndirectos;
	}
    
   //FUNCIONES PRECIO
	public void setCostoYPrecioTotal(Event event) {
		double valorDirecto = 0;
		double valorIndirecto = 0;
		double valorPartida = 0;
		double valorFabricacion = 0;
		double valorCompraProducto = 0;
		
		/*
		for(CostoDirecto valor : tableview_costosDirectos.getItems()) {
			valorDirecto += valor.getValor();
		}
		for(CostoIndirectoProducto valor : tableview_costosIndirectos.getItems()) {
			valorIndirecto += valor.getValor();
		}
		
		*/
		
		for(CostoDirecto costo : getGastosDirectos())
		{
			valorDirecto += costo.getValor();
		}
		
		for(CostoIndirectoProducto costo : getGastosIndirectos())
		{
			valorIndirecto += costo.getValor();
		}
		
		for(String valor : listview_partidaSelect.getItems()) {
			 //int posicion = valor.indexOf("[");
    		 //String selection = valor.substring(0, posicion);
    		 String nombre = Controladora.getInstance().findPartidaNombre(valor);
    		 Estandar p = (Estandar) Controladora.getInstance().buscarProducto(nombre);
    		 
			 //String partida = Controladora.getInstance().findPartidaCosto(valor);
			 String cantidad = Controladora.getInstance().findPartidaCantidad(valor);
			 valorPartida += p.getCosto() * Float.parseFloat(cantidad);
		}
		if((checkbox_generalProducible.isSelected() || combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Servicio")) && textfield_costosTiempoFabricacion.getLength() > 0) {
			System.out.println(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
			String nombreCategoria = Controladora.getInstance().findEncargadoNombre(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem());
			String tiempoMedida = combobox_costosTiempoFabricacion.getSelectionModel().getSelectedItem();
			float tiempoCantidad = Float.parseFloat(textfield_costosTiempoFabricacion.getText());
			for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
				if(c.getNombre().equals(nombreCategoria)) {
					if(tiempoMedida.equalsIgnoreCase("Minutos")) {
						tiempoCantidad = tiempoCantidad / 60;
					}
					else if(tiempoMedida.equalsIgnoreCase("Segundos")) {
						tiempoCantidad = tiempoCantidad / 3600; 
					}
					valorFabricacion = c.getSueldo() * tiempoCantidad;
				}
			}
			System.out.println(valorFabricacion);
		}
		else if(!checkbox_generalProducible.isSelected() && textfield_costoPrecioCompraProducto.getLength() > 0) {
			valorCompraProducto += Float.parseFloat(textfield_costoPrecioCompraProducto.getText());
		}
		
		DecimalFormat formato1 = new DecimalFormat("0.00");
		textfield_preciosCostos.setText(formato1.format((valorDirecto + valorIndirecto + valorPartida + valorFabricacion + valorCompraProducto)));
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
			textfield_preciosPrecio.setText(formato1.format((precioTotal)));
		}
		//Si el porciento de ganancia esta vacio, hago el calculo solo con el impuesto.
		catch (NumberFormatException e) {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					0 ,
					Double.parseDouble(textfield_preciosImpuestos.getText()));			
			textfield_preciosPrecio.setText(formato1.format((precioTotal)));
		}		
	}
		
	
	public void habilitarPorcientoGanancia(ActionEvent event) {
		if(checkbox_preciosHabilitar.isSelected()) {
			textfield_preciosPorcientoGanancia.setDisable(false);
			if(!modificado)
			{
				textfield_preciosPorcientoGanancia.setText("");
			}
		}
		else {
			textfield_preciosPorcientoGanancia.setDisable(true);
			if(!modificado)
			{
				textfield_preciosPorcientoGanancia.setText("0");
			}
			calcularPrecio(null);
		}
		
	}
	
	public void habilitarImpuestos(ActionEvent event) {
		if(checkbox_Impuestos.isSelected()) {
			textfield_preciosImpuestos.setDisable(false);
			
			if(!modificado)
			{
				if(Controladora.getInstance().getMiEmpresa() != null) {
					textfield_preciosImpuestos.setText(Integer.toString(Controladora.getInstance().getMiEmpresa().getITBIS()));
				}
				else {
					textfield_preciosImpuestos.setText("18");
				}
			}
			
			calcularPrecio(null);
		}
		else {
			textfield_preciosImpuestos.setDisable(true);
			if(!modificado)
			{
				textfield_preciosImpuestos.setText("0");
			}
			
			calcularPrecio(null);
		}

	}
	
	public void calcularPrecio(KeyEvent event) {
		//Tengo que sumarle el caracter del evento.
		String textfield = "";
		String textfieldImpuesto = "";
		DecimalFormat formato1 = new DecimalFormat("0.00");
		try {
			textfield = textfield_preciosPorcientoGanancia.getText();
			textfieldImpuesto = textfield_preciosImpuestos.getText();
		}catch(NullPointerException e){
			
		}
		try {
			if(event.getSource().equals(textfield_preciosPorcientoGanancia)) {
				textfield = textfield_preciosPorcientoGanancia.getText() + event.getCharacter();
			}
			else if(event.getSource().equals(textfield_preciosImpuestos)) {
				textfieldImpuesto = textfield_preciosImpuestos.getText() + event.getCharacter();
			}
		}catch(NullPointerException e) {
			
		}
		
		try {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					Double.parseDouble(textfield) ,
					Double.parseDouble(textfieldImpuesto));	
			textfield_preciosPrecio.setText(formato1.format((precioTotal)));
		}
		//Si el porciento de ganancia esta vacio, hago el calculo solo con el impuesto.
		catch (NumberFormatException e) {
			double precioTotal = Controladora.getInstance().calcularPrecio
					(Double.parseDouble(textfield_preciosCostos.getText()),
					0 ,
					Double.parseDouble(textfieldImpuesto));			
			textfield_preciosPrecio.setText(formato1.format((precioTotal)));
		}		
	}
	
	//FUNCIONES BUSQUEDA PROVEEDOR
	public void buscarProveedor(ActionEvent event) {
		titledpane_productoBuscarProveedor.setVisible(true);
		titledpane_productoBuscarProveedor.setDisable(false);
		fillProveedorList(null);
		combobox_productoBusquedaProveedor.getSelectionModel().select("Codigo");
	}
	
    public void buscarProveedores(KeyEvent event) {
    	ArrayList<Proveedores> proveedores = new ArrayList<>();
    	System.out.println(combobox_productoBusquedaProveedor.getValue());
    	if(Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_productoBusquedaProveedor.getText().toLowerCase() + event.getCharacter(), combobox_productoBusquedaProveedor.getValue());
    		System.out.println(proveedores.size());
    	}
    	else {
    		proveedores = Controladora.getInstance().searchProveedores(textfield_productoBusquedaProveedor.getText().toLowerCase(), combobox_productoBusquedaProveedor.getValue());
    	}
    	if(proveedores.size() == 0) {
    		fillProveedorList(null);
    	}
    	else {
    		fillProveedorList(proveedores);
    	}
    }
    
    public void cerrarBusquedaProveedor(ActionEvent event) {
    	titledpane_productoBuscarProveedor.setVisible(false);
    }
    
    public void proveedorTableViewClicked(MouseEvent event) {
    	if(!tableview_proveedorBuscar.getSelectionModel().isEmpty()) {
    		button_aceptarBusquedaProveedor.setDisable(false);
    	}
    }
    
    public void returnProveedorSearch(ActionEvent event) {
    	textfield_generalProveedor.setText(tableview_proveedorBuscar.getSelectionModel().getSelectedItem().getCodigo());
    	button_aceptarBusquedaProveedor.setDisable(true);
    	titledpane_productoBuscarProveedor.setVisible(false);
    	activarProductoGuardar(null);
    }
    
    //FUNCIONES BUSQUEDA DE RUBRO
    
    public void buscarRubro(ActionEvent event) {
    	titledpane_productoBuscarRubro.setVisible(true);
		titledpane_productoBuscarRubro.setDisable(false);
		fillRubroList(null);
		button_aceptarBusquedaRubro.setDisable(true);
    }
    
    public void buscarRubros(KeyEvent event) {
    	ArrayList<Rubro> rubros = new ArrayList<>();
    	if(Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
    		rubros = Controladora.getInstance().searchRubro(textfield_productoBusquedaRubro.getText().toLowerCase() + event.getCharacter(), combobox_productoBusquedaRubro.getValue());
    	}
    	else {
    		rubros = Controladora.getInstance().searchRubro(textfield_productoBusquedaRubro.getText().toLowerCase(), combobox_productoBusquedaRubro.getValue());
    	}
    	if(rubros.size() == 0) {
    		fillRubroList(null);
    	}
    	else {
    		fillRubroList(rubros);
    	}
    }
    
    public void cerrarBusquedaRubro(ActionEvent event) {
    	titledpane_productoBuscarRubro.setVisible(false);
    }
    
    public void rubroTableViewClicked(MouseEvent event) {
    	if(!tableview_rubroBuscar.getSelectionModel().isEmpty()) {
    		button_aceptarBusquedaRubro.setDisable(false);
    		button_rubroEliminar.setDisable(false);
    	}
    }
    
    public void returnRubroSearch(ActionEvent event) {
    	textfield_generalRubro.setText(tableview_rubroBuscar.getSelectionModel().getSelectedItem().getNombreRubro());
    	button_aceptarBusquedaRubro.setDisable(true);
    	titledpane_productoBuscarRubro.setVisible(false);
    	activarProductoGuardar(null);
    }
    
    public void activarRegistro(MouseEvent event) {
    	pane_nuevorubro.setVisible(true);
    }
    
    public void cerrarRegistro(ActionEvent event) {
    	pane_nuevorubro.setVisible(false);
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
    	tableview_rubroBuscar.getItems().add(rubro);
    	tableview_rubroBuscar.refresh();
    	textfield_rubroCodigo.setText("");
    	textfield_rubroNombre.setText("");
    	textfield_rubroCodigo.setDisable(true);
    	textfield_rubroNombre.setDisable(true);
    }
    
    
    public void eliminarRubro(MouseEvent event) {
    	Rubro rubro = tableview_rubroBuscar.getSelectionModel().getSelectedItem();
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
    				int indice = Controladora.getInstance().getRubroIndice(rubro);
    				Controladora.getInstance().getMisRubros().get(indice).setBorrado(true);
    				Controladora.getInstance().borrarRubro(indice+1);
    				fillRubroList(null);
    			}
    				
        	}
    	}
    }
    
    //UNIDAD DE MEDIDAS
    public void buscarUnidadMedida(ActionEvent event) {
    	titledpane_productoBuscarUnidadMedida.setVisible(true);
    	titledpane_productoBuscarUnidadMedida.setDisable(false);
    	fillUnidadMedida(null);
    }
    
    public void cerrarbuscarUnidadMedida(ActionEvent event) {
    	titledpane_productoBuscarUnidadMedida.setVisible(false);
    	titledpane_productoBuscarUnidadMedida.setDisable(true);
    }
    
    public void unidadTableViewClicked(MouseEvent event) {
    	if(!tableview_unidadList.getSelectionModel().isEmpty()) {
    		button_aceptarUnidad.setDisable(false);
    	}
    }
    
    public void returnUnidadSearch(ActionEvent event) {
    	String unidadSelected = tableview_unidadList.getSelectionModel().getSelectedItem().getNombre();
    	if(unidadSelected.equalsIgnoreCase("Unidad")) {
    		textfield_generalUnidad.setText("");
    	}
    	else {
    		textfield_generalUnidad.setText(unidadSelected);
    	}
    	button_aceptarUnidad.setDisable(true);
    	titledpane_productoBuscarUnidadMedida.setVisible(false);
    }
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Seteando tableview de la partida
		fillPartida(null);
		
		//Seteando general
		fillGeneralTab();
		
		//Seteando tab de precios
		fillPreciosTab();
		
		
		//Seteando listview gastosgenerales
		rellenarCostosGenerales(null);
		//textfield_preciosImpuestos.setEditable(false);
		
		Image partida_derecha = new Image(getClass().getResourceAsStream("images/misc/derecha.png"));
	    ImageInput partida_derecha1 = new ImageInput();
	    partida_derecha1.setSource(partida_derecha);
		button_partidaSendTo.setEffect(partida_derecha1);
		
		Image partida_izquierda = new Image(getClass().getResourceAsStream("images/misc/izquierda.png"));
	    ImageInput partida_izquierda1 = new ImageInput();
	    partida_izquierda1.setSource(partida_izquierda);
		button_partidaSendBack.setEffect(partida_izquierda1);
		
		Image costo_derecha = new Image(getClass().getResourceAsStream("images/misc/derecha.png"));
	    ImageInput costo_derecha1 = new ImageInput();
	    costo_derecha1.setSource(costo_derecha);
		button_DerCostoIndirecto.setEffect(costo_derecha1);
		
		Image costo_izquierda = new Image(getClass().getResourceAsStream("images/misc/izquierda.png"));
	    ImageInput costo_izquierda1 = new ImageInput();
	    costo_izquierda1.setSource(costo_izquierda);
		button_IzqCostoIndirecto.setEffect(costo_izquierda1);
		
		Image precio_izquierda = new Image(getClass().getResourceAsStream("images/misc/flecha_izq.png"));
	    ImageInput precio_izquierda1 = new ImageInput();
	    precio_izquierda1.setSource(precio_izquierda);
		anchor_izquierdaPrecio.setEffect(precio_izquierda1);
		
		Image precio_abajo = new Image(getClass().getResourceAsStream("images/misc/flecha_abajo.png"));
	    ImageInput precio_abajo1 = new ImageInput();
	    precio_abajo1.setSource(precio_abajo);
		anchor_abajoprecio.setEffect(precio_abajo1);
		
		Image precio_derecha = new Image(getClass().getResourceAsStream("images/misc/flecha_der.png"));
	    ImageInput precio_derecha1 = new ImageInput();
	    precio_derecha1.setSource(precio_derecha);
		anchor_derechaPrecio.setEffect(precio_derecha1);
		
	}
	
	public void fillUnidadMedida(ArrayList<UnidadMedida> u) {
		ObservableList<UnidadMedida> data = FXCollections.observableArrayList();
		Volumen defaultUnidad = new Volumen("Unidad", "Unidad", "Und");
		if(u == null) {
			data.add(defaultUnidad);
			data.addAll(Controladora.getInstance().getMisUnidadMedida());
		}
		else {
    		data.addAll(u);
    	}
		tablecolumn_unidadCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    	tablecolumn_unidadNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_unidadAbreviatura.setCellValueFactory(new PropertyValueFactory<>("abreviatura"));
    	tableview_unidadList.setItems(data);
    	tableview_unidadList.refresh();
	}
	
	public void fillProveedorList(ArrayList<Proveedores> p) {
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
    	tablecolumn_proveedorCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_proveedorNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_proveedorRubro.setCellValueFactory(new PropertyValueFactory<>("rubro"));
    	tablecolumn_proveedorDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
    	tablecolumn_proveedorCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    	tablecolumn_proveedorTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    	tablecolumn_proveedorRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    	tablecolumn_proveedorSitioWeb.setCellValueFactory(new PropertyValueFactory<>("sitioWeb"));
    	tablecolumn_proveedorSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
    	tableview_proveedorBuscar.setItems(data);
    	tableview_proveedorBuscar.refresh();
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
    	tableview_rubroBuscar.setItems(data);
    	tableview_rubroBuscar.refresh();
	}
	
	public void fillPartida(Producto producto) {
		DecimalFormat formato1 = new DecimalFormat("0.0000");
		ObservableList<String> dataPartida = FXCollections.observableArrayList();
		for(Estandar e : Controladora.getInstance().getMisProductosEstandar()) {
			if(!e.isBorrado()) {
				if(e.getUnidadMedida() != null) {
					dataPartida.add(e.getNombre() + "[" + "Unidad: " + e.getUnidadMedida().getAbreviatura() + ", disponibles: " + formato1.format(e.getExistenciaActual()) + "]");
				}
				else {
					dataPartida.add(e.getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + formato1.format(e.getExistenciaActual()) + "]");
				}
			}
			
		}
		
		listview_partida.setItems(dataPartida);
		listview_partida.refresh();
		
		String itemRemove = "";
		if(producto != null) {
			for(String original : listview_partida.getItems()) {
				String originalName = Controladora.getInstance().findPartidaNombre(original);
				if(originalName.equalsIgnoreCase(producto.getNombre())) {
					itemRemove = original;
				}
			}
		}
		listview_partida.getItems().remove(itemRemove);
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
    
    public void fillGeneralTab() {
    	//Seteando combobox de general
   		ObservableList<String> dataType = FXCollections.observableArrayList();
  		dataType.addAll("Estandar", "Kit", "Servicio", "Matriz");
    	combobox_generalTipoProducto.setItems(dataType);
   		combobox_generalTipoProducto.getSelectionModel().select("Estandar");
    	tipoProducto(null);
    			
    	//Seteando busqueda de proveedores
   		ObservableList<String> dataProveedor = FXCollections.observableArrayList();
    	dataProveedor.addAll("Codigo", "Nombre", "Rubro");
    	combobox_productoBusquedaProveedor.setItems(dataProveedor);
    	combobox_productoBusquedaProveedor.getSelectionModel().select("Codigo");
  		
    	//Seteando busqueda de rubros
   		ObservableList<String> dataRubro = FXCollections.observableArrayList();
    	dataRubro.addAll("Codigo", "Nombre");
    	combobox_productoBusquedaRubro.setItems(dataRubro);
    	combobox_productoBusquedaRubro.getSelectionModel().select("Codigo");
    }
    
    public void fillPreciosTab() {
    	textfield_preciosPrecio.setText("0.0");
		textfield_preciosPorcientoGanancia.setText("0");
		textfield_preciosCostos.setText("0.0");
		textfield_preciosImpuestos.setText("0");
    }
    
    public void rellenarCostosGenerales(Producto producto) //Ver porqu� no funciona
    {
    	ObservableList<String> ob = FXCollections.observableArrayList();
    	
    	if(producto == null) {
    		for(GastoGeneral e : Controladora.getInstance().getMisGastosGenerales()) {
    			if(!e.isBorrado()) {
    				ob.add(e.getNombre());
    			}
    		}
    	}
    	else {
    		if(producto.getCostosIndirectos().size() > 0) {
    			for(GastoGeneral e : Controladora.getInstance().getMisGastosGenerales()) {
    				boolean alreadyRegistered = false;
    				for(CostoIndirectoProducto c : producto.getCostosIndirectos()) {
    					if(e.getNombre() != c.getNombre()) {
    						alreadyRegistered = true;
    						System.out.println("El de la controladora es: " + e.getNombre() + "El del producto es: " + c.getNombre());
    					}
    				}
    				if(alreadyRegistered) {
    					ob.add(e.getNombre());
    				}
    			}
    		}
		}
		listview_GastosGeneralesIndirectos.setItems(ob);;
		listview_GastosGeneralesIndirectos.refresh();

		ObservableList<String> fabricacionData = FXCollections.observableArrayList();
		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
			if(!c.isBorrado()) {
				fabricacionData.add(c.getNombre() + ": " + c.getSueldo() + "$");
			}		
		}
		combobox_costosEncargadosFabricacion.setItems(fabricacionData);
		
		ObservableList<String> tiempoData = FXCollections.observableArrayList();
		tiempoData.addAll("Segundos", "Minutos", "Horas");
		combobox_costosTiempoFabricacion.setItems(tiempoData);
		combobox_costosTiempoFabricacion.getSelectionModel().select("Horas");
    }
    
    public void modifyOpen(Producto producto) {
    	modificado = true;
    	textfield_generalCodigo.setEditable(false);
    	button_productGuardar.setDisable(false);
    	System.out.println(producto);
    	textfield_generalCodigo.setText(producto.getCodigo());
    	textfield_generalNombre.setText(producto.getNombre());
    	textfield_preciosPorcientoGanancia.setText(Integer.toString(producto.getPrecioClass().getPorc_ganancia()));
    	textfield_preciosImpuestos.setText(Integer.toString(producto.getPrecioClass().getItbis()));
    	if(producto.getUnidadMedida() != null) {
    		textfield_generalUnidad.setText(producto.getUnidadMedida().getNombre());
    	}
    	if(producto.getRubroProductoClass() != null) {
    		textfield_generalRubro.setText(producto.getRubroProductoClass().getNombreRubro());
    	}
    	if(producto.getProveedorPrinClass() != null) {
    		textfield_generalProveedor.setText(producto.getProveedorPrinClass().getCodigo());
    	}
    	if(producto.getCostosIndirectos().size() > 0) {
    		for(CostoIndirectoProducto c : producto.getCostosIndirectos()) {
    			gastosIndirectos.add(c);
    			listview_CostosResumen.getItems().add(c.getNombre() + ": " + c.getValor());
    			listview_CostosResumen.refresh();
    			listview_CostosSelecIndirectos.getItems().add(c.getNombre());
    		}
    		rellenarCostosGenerales(producto);
    	}
    	
    	if(producto.getTipoProducto().equalsIgnoreCase("Estandar")) {
    		Estandar estandar = (Estandar) producto;
    		
    		combobox_generalTipoProducto.getSelectionModel().select(estandar.getTipoProducto());
    		
    		exAct.setText(Float.toString(estandar.getExistenciaActual()));
    		exMin.setText(Float.toString(estandar.getExistenciaMinima()));
    		exMax.setText(Float.toString(estandar.getExistenciaMaxima()));
    		textfield_costoPrecioCompraProducto.setText(Float.toString(estandar.getCostoDeCompra()));
    		
    		if(estandar.isFabricado()) {
    			fillPartida(estandar);
    			checkbox_generalProducible.setSelected(true);
    			activarPartida(null);
    			ObservableList<String> data = FXCollections.observableArrayList();
    			for(CantProductosUtilizados c : estandar.getPartida().getListaMateriales()) {
    				String item_moved = "";
    				 
    				if(c.getProducto().getUnidadMedida() != null) {
    	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + c.getProducto().getUnidadMedida().getAbreviatura() + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad()*estandar.getExistenciaActual() + ")";
    	    		}
    	    		else {
    	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad()*estandar.getExistenciaActual() + ")";
    	    		}
    				data.add(item_moved);
    			}
    			listview_partidaSelect.setItems(data);
    			if(estandar.getInfoManoDeObra() != null) {
    				textfield_TotalManoObra.setText(Float.toString(estandar.getInfoManoDeObra().getCosto()));
    	    		textfield_costosTiempoFabricacion.setText(Float.toString(estandar.getInfoManoDeObra().getCantidadHoras()));
    	    		combobox_costosEncargadosFabricacion.getSelectionModel().select(estandar.getInfoManoDeObra().getCategoria().getNombre() + ": " + estandar.getInfoManoDeObra().getCategoria().getSueldo() + "$");
    			}
    		}
    		textfield_preciosCostos.setText(Float.toString(estandar.getCosto()));
    		textfield_preciosPrecio.setText(Float.toString(estandar.getPrecio()));
    	}
    	
    	if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    		Kit kit = (Kit) Controladora.getInstance().buscarProducto(producto.getNombre());
    		combobox_generalTipoProducto.getSelectionModel().select(kit.getTipoProducto());
    		System.out.println("La existencia actual: " + kit.getExistenciaActual());
    		
    		fillPartida(kit);
    		tipoProducto(null);
    		
    		exAct.setText(Float.toString(kit.getExistenciaActual()));
    		exMin.setText(Float.toString(kit.getExistenciaMinima()));
    		exMax.setText(Float.toString(kit.getExistenciaMaxima()));
    		
    		ObservableList<String> data = FXCollections.observableArrayList();
			for(CantProductosUtilizados c : kit.getProductosContenidos()) {
				String item_moved = "";
				 
				if(c.getProducto().getUnidadMedida() != null) {
	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + c.getProducto().getUnidadMedida().getAbreviatura() + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad()*kit.getExistenciaActual() + ")";
	    		}
	    		else {
	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad()*kit.getExistenciaActual() + ")";
	    		}
				data.add(item_moved);
			}
			
			listview_partidaSelect.setItems(data);
			
			textfield_preciosCostos.setText(Float.toString(kit.getCosto()));
			textfield_preciosPrecio.setText(Float.toString(kit.getPrecio()));
		}
    	
    	if(producto.getTipoProducto().equalsIgnoreCase("Servicio")) {
    		Servicio servicio = (Servicio) Controladora.getInstance().buscarProducto(producto.getNombre());
    		combobox_generalTipoProducto.getSelectionModel().select(servicio.getTipoProducto());
    		
    		tipoProducto(null);
    		
    		if(servicio.getMaterialesUtilizados() != null) {
    			ObservableList<String> data = FXCollections.observableArrayList();
    			for(CantProductosUtilizados c : servicio.getMaterialesUtilizados()) {
    				String item_moved = "";
    				 
    				if(c.getProducto().getUnidadMedida() != null) {
    	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + c.getProducto().getUnidadMedida().getAbreviatura() + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad() + ")";
    	    		}
    	    		else {
    	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad() + ")";
    	    		}
    				data.add(item_moved);
    			}
    			listview_partidaSelect.setItems(data);	
    			
    		}
    		
    		System.out.println(servicio.getCosto());
    		//textfield_costosTiempoFabricacion.setText(servicio.get);
    		textfield_TotalManoObra.setText(Float.toString(servicio.getInfoManoDeObra().getCosto()));
    		textfield_costosTiempoFabricacion.setText(Float.toString(servicio.getInfoManoDeObra().getCantidadHoras()));
    		combobox_costosEncargadosFabricacion.getSelectionModel().select(servicio.getInfoManoDeObra().getCategoria().getNombre() + ": " + servicio.getInfoManoDeObra().getCategoria().getSueldo() + "$");
    		
    		textfield_preciosCostos.setText(Float.toString(servicio.getCosto()));
			textfield_preciosPrecio.setText(Float.toString(servicio.getPrecio()));
    	}
    	
    	if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		Estandar estandar = (Estandar) producto;
    		combobox_generalTipoProducto.getSelectionModel().select(estandar.getTipoProducto());
    		tipoProducto(null);
    		
    		exAct.setText(Float.toString(estandar.getExistenciaActual()));
    		exMin.setText(Float.toString(estandar.getExistenciaMinima()));
    		exMax.setText(Float.toString(estandar.getExistenciaMaxima()));
    		
    		if(estandar.isFabricado()) {
    			fillPartida(estandar);
    			checkbox_generalProducible.setSelected(true);
    			activarPartida(null);
    			ObservableList<String> data = FXCollections.observableArrayList();
    			for(CantProductosUtilizados c : estandar.getPartida().getListaMateriales()) {
    				String item_moved = "";
    				 
    				if(c.getProducto().getUnidadMedida() != null) {
    	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + c.getProducto().getUnidadMedida().getAbreviatura() + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad()*estandar.getExistenciaActual() + ")";
    	    		}
    	    		else {
    	    			item_moved = c.getProducto().getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + c.getCantidad() + "]" + " (" + c.getCantidad()*estandar.getExistenciaActual() + ")";
    	    		}
    				data.add(item_moved);
    			}
    			listview_partidaSelect.setItems(data);
    		}
    		if(estandar.getInfoManoDeObra() != null) {
				textfield_TotalManoObra.setText(Float.toString(estandar.getInfoManoDeObra().getCosto()));
	    		textfield_costosTiempoFabricacion.setText(Float.toString(estandar.getInfoManoDeObra().getCantidadHoras()));
	    		combobox_costosEncargadosFabricacion.getSelectionModel().select(estandar.getInfoManoDeObra().getCategoria().getNombre() + ": " + estandar.getInfoManoDeObra().getCategoria().getSueldo() + "$");
			}
    		textfield_preciosCostos.setText(Float.toString(estandar.getCosto()));
    		textfield_preciosPrecio.setText(Float.toString(estandar.getPrecio()));
    		System.out.println(estandar.getCombinaciones().get(0).getAtributo1());
    		ObservableList<String> combinacionesData = FXCollections.observableArrayList();
    		for(Combinaciones c : estandar.getCombinaciones()) {
    			if(c.getListaAtributos().size() == 2) {
    				combinacionesData.add(c.numeroSerie + " " + c.getListaAtributos().get(0).getGrupo() + ": " + c.getListaAtributos().get(0).getNombre() + ", " +
    						c.getListaAtributos().get(1).getGrupo() + ": " + c.getListaAtributos().get(1).getNombre() + ", Existencia: " + c.getExistenciaActual());
    			}
    			else if(c.getListaAtributos().size() == 3) {
    				combinacionesData.add(c.numeroSerie + " " + c.getListaAtributos().get(0).getGrupo() + ": " + c.getListaAtributos().get(0).getNombre() + ", " +
    						c.getListaAtributos().get(1).getGrupo() + ": " + c.getListaAtributos().get(1).getNombre() + 
    						", " + c.getListaAtributos().get(2).getGrupo() + ": " + c.getListaAtributos().get(2).getNombre() + 
    						", Existencia: " + c.getExistenciaActual());
    			}
    			//System.out.println(c.getListaAtributos().size());
    		}
    		listView_combinaciones.setItems(combinacionesData);
    		
    	}
    	
    }
    
    public boolean checkPartida(Producto producto) {
    	boolean isDifferent = false;
    	int check = 0;
    	if(producto.getTipoProducto().equalsIgnoreCase("Estandar")) {
    		Estandar estandar = (Estandar) producto;
    		if(estandar.getPartida() != null) {
    			for(CantProductosUtilizados c : estandar.getPartida().getListaMateriales()) {
    				for(String s : listview_partidaSelect.getItems()) {
    					String nombre = Controladora.getInstance().findPartidaNombre(s);
    					float cantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(s));
    					if(nombre.equalsIgnoreCase(c.getProducto().getNombre()) && cantidad == c.getCantidad()) {
    						check++;
    					}
    				}
    			}
    			if(check != estandar.getPartida().getListaMateriales().size()) {
    				isDifferent = true;
    			}
    		}
    	}
    	
    	else if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    		Kit kit = (Kit) producto;
    		for(CantProductosUtilizados c : kit.getProductosContenidos()) {
    			for(String s : listview_partidaSelect.getItems()) {
    				String nombre = Controladora.getInstance().findPartidaNombre(s);
    				float cantidad = Float.parseFloat(Controladora.getInstance().findPartidaCantidad(s));
    				if(nombre.equalsIgnoreCase(c.getProducto().getNombre()) && cantidad == c.getCantidad()) {
    					check++;
    				}
    			}
    		}
    		if(check != kit.getProductosContenidos().size()) {
    			isDifferent = true;
    		}
    	}
    	System.out.println("Chequee las partidas");
    	return isDifferent;
    }
    
    public boolean checkCostosIndirectos(Producto producto) {
    	boolean isDifferent = false;
    	int check = 0;
    	for(CostoIndirectoProducto costoNew : gastosIndirectos) {
    		for(CostoIndirectoProducto costoOld : producto.getCostosIndirectos()) {
    			if(costoOld.getNombre().equalsIgnoreCase(costoNew.getNombre()) && costoOld.getValor() == costoNew.getValor()) {
    				check++;
    			}
    		}
    	}
    	if(check != producto.getCostosIndirectos().size()) {
    		isDifferent = true;
    	}
    	System.out.println("Chequee los costos indirectos");
    	return isDifferent;
    }
    
    public boolean checkManoDeObra(Producto producto) {
    	boolean isDifferent = false;
    	if(producto.getTipoProducto().equalsIgnoreCase("Estandar") || producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		Estandar estandar = (Estandar) producto;
    		if(estandar.getInfoManoDeObra() != null) {
    			String categoria = estandar.getInfoManoDeObra().getCategoria().getNombre();
    			float sueldo = estandar.getInfoManoDeObra().getCategoria().getSueldo();
    			if(estandar.getInfoManoDeObra().getCantidadHoras() == Float.parseFloat(textfield_costosTiempoFabricacion.getText()) &&
    				combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().equalsIgnoreCase(categoria + ": " + Float.toString(sueldo) + "$")) {
    				isDifferent = true;
    			}
    		}	
    	}
    	else if(producto.getTipoProducto().equalsIgnoreCase("Servicio")) {
    		Servicio servicio = (Servicio) producto;
    		if(servicio.getInfoManoDeObra() != null) {
    			String categoria = servicio.getInfoManoDeObra().getCategoria().getNombre();
    			float sueldo = servicio.getInfoManoDeObra().getCategoria().getSueldo();
    			if(servicio.getInfoManoDeObra().getCantidadHoras() == Float.parseFloat(textfield_costosTiempoFabricacion.getText()) &&
    				combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().equalsIgnoreCase(categoria + ": " + Float.toString(sueldo) + "$")) {
    				isDifferent = true;
    			}
    		}
    	}
    	System.out.println("Chequee la mano de obra");
    	return isDifferent;
    }
    
    public boolean checkCombinaciones(Estandar matriz) {
    	boolean isDifferent = false;
    	if(matriz.getCombinaciones().size() != listView_combinaciones.getItems().size()) {
    		isDifferent = true;
    	}
    	return isDifferent;
    }
    
    public void cleanEverything(String tipoProducto) {
    	//Limpiando tab general
		float existencia = 1;
		try {
			if(tipoProducto.equalsIgnoreCase("Estandar") || tipoProducto.equalsIgnoreCase("Kit")) {
				existencia = Float.parseFloat(exAct.getText());
			}
			else if(tipoProducto.equals("Matriz")) {
				for(Combinaciones c : combinacionFinal) {
					existencia += c.getExistenciaActual();
				}
			}
			
		}
		catch(NullPointerException e) {
			
		}
		//Limpiando tab partida
		for(String item : listview_partidaSelect.getItems()) {
			String itemNombre = Controladora.getInstance().findPartidaNombre(item);
			String itemCantidad = Controladora.getInstance().findPartidaCantidad(item);
			System.out.println(itemNombre + itemCantidad);
			Estandar listview_estandar = (Estandar) Controladora.getInstance().buscarProducto(itemNombre);
			for(int i = 0; i < Controladora.getInstance().getMisProductosEstandar().size(); i++) {
				if(!Controladora.getInstance().getMisProductosEstandar().get(i).isBorrado()) {
					if(Controladora.getInstance().getMisProductosEstandar().get(i).equals(listview_estandar)) {
						Controladora.getInstance().getMisProductosEstandar().get(i).setExistenciaActual(
						Controladora.getInstance().getMisProductosEstandar().get(i).getExistenciaActual() - (Float.parseFloat(itemCantidad)*existencia));
					}
				}
				}	
		}
		listview_partidaSelect.getItems().clear();
		exAct.setText(""); exMin.setText(""); exMax.setText("");
		textfield_generalProveedor.setText(""); textfield_generalRubro.setText("");
		textfield_generalCodigo.setText("");  textarea_generalDescripcion.setText("");
		textfield_generalNombre.setText(""); textfield_generalUnidad.setText("");
		checkbox_generalProducible.setSelected(false);
		tableview_proveedorBuscar.getSelectionModel().clearSelection();
		tableview_rubroBuscar.getSelectionModel().clearSelection();
		combobox_costosEncargadosFabricacion.getSelectionModel().clearSelection();
		textfield_TotalManoObra.setText("0.0");
		
		for(int i = 0; i < listview_partida.getItems().size(); i++) {
			listview_partida.getItems().remove(i);
		}
		for(int i = 0; i < listview_partidaSelect.getItems().size(); i++) {
			listview_partidaSelect.getItems().remove(i);
		}
	
		//Limpiando combinaciones
		textfield_busquedaFamilia1.setText("");
		textfield_busquedaFamilia2.setText("");
		textfield_busquedaFamilia3.setText("");
		for(int i = 0; i < listView_atributos1.getItems().size(); i++) {
			listView_atributos1.getItems().remove(i);
		}
		for(int i = 0; i < listView_atributos2.getItems().size(); i++) {
			listView_atributos2.getItems().remove(i);
		}
		for(int i = 0; i < listView_atributos3.getItems().size(); i++) {
			listView_atributos3.getItems().remove(i);
		}
		for(int i = 0; i < listView_combinaciones.getItems().size(); i++) {
			listView_combinaciones.getItems().remove(i);
		}
		textfield_numSerie.setText("");
		textfield_cantidadComb.setText("");
		tabpane_everything.getSelectionModel().select(tab_general);
		button_productGuardar.setDisable(true);
		combinacionFinal.clear();
		imageview_imagen.setImage(null);
		textfield_imagen.setText("");
		listView_combinaciones.getItems().clear();
		listView_atributos1.getItems().clear();
		listView_atributos2.getItems().clear();
		listView_atributos3.getItems().clear();
		
		//Limpiando costos
		textfield_costoPrecioCompraProducto.setText("");
		textfield_costosValor.setText("");
		textfield_costosTiempoFabricacion.setText("");
		for(int i = 0; i < listview_CostosResumen.getItems().size(); i++) {
			listview_CostosResumen.getItems().remove(i);
		}
		for(int i = 0; i < listview_CostosSelecIndirectos.getItems().size(); i++) {
			listview_CostosSelecIndirectos.getItems().remove(i);
		}
		for(int i = 0; i < listview_GastosGeneralesIndirectos.getItems().size(); i++) {
			listview_GastosGeneralesIndirectos.getItems().remove(i);
		}
		for(int i = 0; i < listview_CostosResumen.getItems().size(); i++) {
			listview_CostosResumen.getItems().remove(i);
		}
		listview_CostosResumen.getItems().clear();
		listview_CostosSelecIndirectos.getItems().clear();
		listview_GastosGeneralesIndirectos.getItems().clear();
		checkbox_Impuestos.setSelected(false);
		checkbox_preciosHabilitar.setSelected(false);

		fillPartida(null);
		fillPreciosTab();
		fillGeneralTab();
		rellenarCostosGenerales(null);
		getGastosDirectos().clear();
		getGastosIndirectos().clear();
    }
    
    public void habilitarAyuda(ActionEvent event) {
    	if(checkbox_habilitarAyuda.isSelected()) {
    		help_tipoProducto.setVisible(true);
    		help_producible.setVisible(true);
    		help_unidadMedida.setVisible(true);
    		help_rubro.setVisible(true);
    		help_proveedor.setVisible(true);
    		help_cantidadActual.setVisible(true);
    		help_cantidadMin.setVisible(true);
    		help_cantidadMax.setVisible(true);
    		help_busquedaPartida.setVisible(true);
    		help_cantidadPartida.setVisible(true);
    		help_precioEstimado.setVisible(true);
    		help_precioCompra.setVisible(true);
    		help_costosIndirectos.setVisible(true);
    		help_costosDirectos.setVisible(true);
    		help_tabPrecios.setVisible(true);
    		help_tabImagen.setVisible(true);
    		help_tabSustitutos.setVisible(true);
    		help_tabPromocion.setVisible(true);
    		help_tabCombinaciones.setVisible(true);
    		help_numeroSerie.setVisible(true);
    	} else {
    		help_tipoProducto.setVisible(false);
    		help_producible.setVisible(false);
    		help_unidadMedida.setVisible(false);
    		help_rubro.setVisible(false);
    		help_proveedor.setVisible(false);
    		help_cantidadActual.setVisible(false);
    		help_cantidadMin.setVisible(false);
    		help_cantidadMax.setVisible(false);
    		help_busquedaPartida.setVisible(false);
    		help_cantidadPartida.setVisible(false);
    		help_precioEstimado.setVisible(false);
    		help_precioCompra.setVisible(false);
    		help_costosIndirectos.setVisible(false);
    		help_costosDirectos.setVisible(false);
    		help_tabPrecios.setVisible(false);
    		help_tabImagen.setVisible(false);
    		help_tabSustitutos.setVisible(false);
    		help_tabPromocion.setVisible(false);
    		help_tabCombinaciones.setVisible(false);
    		help_numeroSerie.setVisible(false);
    	}
    }
    
    public void infoTipoProducto(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre los tipos de productos");
    	alert.setContentText("Est�ndar: es el tipo de producto por defecto, normalmente utilizado para materias primas o productos corriente.\r\n" + 
    			" Ej:  Azucar, tomate, vino, etc. \r\n" + 
    			"\r\n" + 
    			"Kit: producto que se compone de varios productos que se venden en conjunto y estos tambi�n se venden como productos est�ndar.\r\n" + 
    			"Ej: Productos est�ndar: Pasta dental y cepillo dental. \r\n" + 
    			"Producto kit: combo de pasta dental con cepillo dental incluido. \r\n" + 
    			"\r\n" + 
    			"Servicio: producto sin existencia agotable y depende del personal capacitado del negocio. Estos pueden requerir el uso de alg�n producto, tambi�n descontando la existencia del producto. \r\n" + 
    			"Ej: Servicios: masajes. \r\n" + 
    			"Producto relacionado: crema corporal. \r\n" + 
    			"\r\n" + 
    			"Productos de matriz: son aquellos productos que poseen distintas versiones de s� mismo y no poseen variaciones en su precio. Estos productos podr�an variar en color, tama�o, sabor, entre otras caracter�sticas. \r\n" + 
    			"Ej: T-Shirt de la marca Polo color rojo, T-Shirt de la marca Polo color azul. \r\n" + 
    			"");
    			
    	alert.showAndWait();
    }
    
    public void infoProducible(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre producible");
    	alert.setContentText("Al marcar esta opci�n se indica que este producto es creado en su negocio y es necesario agregarle contos relacionados a la materia prima y mano de obra involucrada.");

    	alert.showAndWait();
    }
    
    public void infoUnidadMedida(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre unidad de medida");
    	alert.setContentText("Al presionar el bot�n de buscar, se presentar� una tabla con todas las unidades de medida disponibles en el programa, agrupadas por longitud, masa, volumen, �rea y unidad. Al elegir una de estas unidades de medida, en base a esta es que se debe colocar la existencia del producto. \r\n" + 
    			"Ej: Producto: Alcohol \r\n" + 
    			"Unidad de medida: Litros \r\n" + 
    			"Existencia: 200 \r\n" + 
    			"Esto es equivalente a 200 litros de alcohol en el inventario. \r\n" + 
    			"\r\n" + 
    			"Las unidades de medida de longitud, masa, volumen y �rea son recomendadas para productos que se vender�n al detalle o ser�n utilizadas medidas exactas de estos para la fabricaci�n de otros productos (partida). \r\n" + 
    			"\r\n" + 
    			"La unidad de medida del tipo unidad son para los productos que se vender�n o utilizaran como tal. \r\n" + 
    			"Ej:  \r\n" + 
    			"Producto: Una botella de alcohol. \r\n" + 
    			"Unidad de medida: Unidad \r\n" + 
    			"Existencia: 200 \r\n" + 
    			"Esto es equivalente a 200 botellas de alcohol en el inventario. \r\n" + 
    			"");

    	alert.showAndWait();
    }
    
    public void infoRubro(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre rubro");
    	alert.setContentText("Los rubros son agrupaciones de productos que poseen caracter�sticas en com�n. \r\n" + 
    			"Ej: Fresas, manzanas, peras y bananas, pertenecen al rubro de las frutas. \r\n" + 
    			"\r\n" + 
    			"Al presionar el bot�n de buscar, mostrar� una tabla con todos sus rubros registrados y podr� seleccionar uno para este producto. \r\n" + 
    			"");

    	alert.showAndWait();
    }

    public void infoProveedor(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre proveedor");
    	alert.setContentText("Al presionar el bot�n de buscar, mostrar� una tabla con todos sus proveedores registrados y podr� seleccionar uno para este producto. Este campo estar� deshabilitado para productos producibles, ya que el proveedor ser� la empresa misma. ");

    	alert.showAndWait();
    }
    
    public void infoCantidadActual(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre cantidad actual");
    	alert.setContentText("Cantidad existente del producto en el inventario. ");

    	alert.showAndWait();
    }
    
    public void infoCantMinima(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre existencia m�nima");
    	alert.setContentText("Cantidad m�nima del producto en el inventario. El programa le notificara cuando la existencia actual se aproxime a la existencia m�nima, indic�ndole que debe reabastecerse del producto. ");

    	alert.showAndWait();
    }
    
    public void infoCantMax(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre existencia m�xima");
    	alert.setContentText("Cantidad m�xima del producto en el inventario. El programa le notificara cuando la existencia actual se aproxime a la existencia m�xima, indic�ndole que debe reducir la del producto para evitar gastos extra. ");

    	alert.showAndWait();
    }
    
    public void infoBusquedaProducto(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre b�squeda de productos");
    	alert.setContentText("Es posible buscar un producto en este campo por su nombre. ");

    	alert.showAndWait();
    }
    
    public void infoCantidadPartida(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre cantidad");
    	alert.setContentText("En este campo se coloca la cantidad a utilizar del producto seleccionado en la partida del producto a fabricar. Debajo contamos con un peque�o men� de las unidades de medidas de la misma categor�a (longitud, masa, volumen o �rea), a las que es posible convertir la unidad de medida del producto seleccionado. \r\n" + 
    			"Ej: Producto: Alcohol \r\n" + 
    			"Unidad de medida: Litros \r\n" + 
    			"Existencia: 200 \r\n" + 
    			"Cantidad a utilizar: 50 \r\n" + 
    			"\r\n" + 
    			"Medida a utilizar para la aplicaci�n de este producto en la partida del producto a fabricar: Mililitros \r\n" + 
    			"50 mililitros = 0.05 litros de alcohol ser�n utilizados en la fabricaci�n de este producto. \r\n" + 
    			"");

    	alert.showAndWait();
    }
    
    public void infoPrecioEstimado(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre precio estimado del producto");
    	alert.setContentText("Precio de venta que se estima que el producto posea. Se recomienda calcular el precio promedio del producto en el mercado y colocarlo en este campo. ");

    	alert.showAndWait();
    }
    
    public void infoPrecioCompra(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre precio de compra del producto");
    	alert.setContentText("Colocar el precio de compra del producto en este campo. No aplica para productos producibles. ");

    	alert.showAndWait();
    }
    
    public void infoCostosIndirectos(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre costos indirectos");
    	alert.setContentText("Listado de los costos indirectos de su empresa registrados en el sistema. En este campo puede seleccionar los costos indirectos que aplicaran para este producto clic�ndolo en la lista y envi�ndolo a la lista de los seleccionados con la flecha a derecha. Esto aumentara el costo del producto para cubrir gastos de la empresa. ");

    	alert.showAndWait();
    }
    
    public void infoCostosDirectos(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre costos directos");
    	alert.setContentText("En esta secci�n se calcular� el costo atribuido a su producto por su mano de obra. Es necesario indicar el tiempo de fabricaci�n (que requiera intervenci�n humana) del producto y la categor�a de empleados encargados de la fabricaci�n del producto. \r\n" + 
    			"\r\n" + 
    			"Al presionar el bot�n de aplicar, se calcular� el costo atribuido por mano de obra al precio del producto. (Solo aplica a productos producibles). \r\n" + 
    			"");

    	alert.showAndWait();
    }
    
    public void infoPrecios(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre precios");
    	alert.setContentText("En esta ventana se puede visualizar el costo del producto generado por mano de obra (para producto producible), costo de compra y costos indirectos de la empresa. Es posible aplicarle un porcentaje de ganancia al producto y de impuestos, aumentando el precio de venta del producto. ");

    	alert.showAndWait();
    }
    
    public void infoImagen(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre imagen");
    	alert.setContentText("Al presionar el bot�n agregar, puede buscar dentro de su ordenador una imagen para asign�rsela a su producto y visualizarla en la informaci�n adicional del producto en el listado de productos. ");

    	alert.showAndWait();
    }
    
    public void infoSustitutos(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre productos sustitutos");
    	alert.setContentText("Es posible colocar productos para ofrecer al cliente como sustituto del producto que desee en caso de que este no se encuentre disponible para la venta. \r\n" + 
    			"\r\n" + 
    			"Ej: El cliente busca un pantal�n de la marca X y este no se encuentra disponible en el inventario, as� que puede ofrecerle un pantal�n de la marca Y o Z a ver si alguno de estos tambi�n cumple sus necesidades y expectativas.  \r\n" + 
    			"");

    	alert.showAndWait();
    }
    
    public void infoPromocion(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre promoci�n");
    	alert.setContentText("Puede incluir al producto en una de las promociones disponibles de la empresa. ");

    	alert.showAndWait();
    }
    
    public void infoCombinaciones(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre combinaciones");
    	alert.setContentText("En esta secci�n puede generar combinaciones de atributos para sus productos de tipo matriz. En las barras de arriba puede colocar el nombre de la familia de atributos que desea utilizar y presiona el bot�n de buscar para que se muestren en la lista. Una vez mostrados en la lista, debe cliquear uno o varios atributos que vaya a utilizar, colocarle un n�mero de serie y la cantidad (existencia) de esta combinaci�n en el stock. Para terminar de procesar dicha combinaci�n, debe cliquear el bot�n �combinar�. ");

    	alert.showAndWait();
    }
    
    public void infoNumeroSerie(MouseEvent event){
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Informaci�n");
    	alert.setHeaderText("Informaci�n sobre n�mero de serie");
    	alert.setContentText("El n�mero de serie es un identificador para la combinaci�n a crear.\r\n" + 
    			"Ej: Atributo #1: Color, Rojo\r\n" + 
    			"Atributo #2: Tama�o, Small\r\n" + 
    			"N�mero de Serie: NRS001\r\n" + 
    			"Cantidad: 10\r\n" + 
    			"\r\n" + 
    			"La combinaci�n se ver�a como tal: NRS001 Color: Azul, Tama�o: Small, Existencia: 10.0\r\n" + 
    			"");

    	alert.showAndWait();
    }
    
}
