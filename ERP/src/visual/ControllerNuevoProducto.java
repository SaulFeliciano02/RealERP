package visual;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import basededatos.Conexion;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
import logico.Empleado;
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

public class ControllerNuevoProducto implements Initializable {
	
/**VARIABLES PARA CREAR PRODUCTOS**/
	@FXML TabPane tabpane_everything;
	
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
	
	//PARTIDA
	@FXML private ListView<String> listview_partida;
	@FXML private ListView<String> listview_partidaSelect;
	@FXML private Button button_partidaSendTo;
	@FXML private Button button_partidaSendBack;
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
	@FXML private Button button_derCosto;
	@FXML private Button button_izqCosto;
	@FXML private Button button_GuardarCostos;
	@FXML private ListView<String> listview_CostosResumen;
	@FXML private ListView<String> listview_GastosGeneralesIndirectos;
	@FXML private Button button_DerCostoIndirecto;
	@FXML private Button button_IzqCostoIndirecto;
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
    
    //VARIABLES PARA LA BUSQUEDA DE UNIDAD DE MEDIDAS
    
    @FXML TableColumn<UnidadMedida, String> tablecolumn_unidadCategoria;
    @FXML TableColumn<UnidadMedida, String> tablecolumn_unidadNombre;
    @FXML TableColumn<UnidadMedida, String> tablecolumn_unidadAbreviatura;
    @FXML TableView<UnidadMedida> tableview_unidadList;
    @FXML Button button_aceptarUnidad;
   
    /**FUNCIONES GENERALES**/
    
    //Verifica si el input de un textfield es un numero
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
    
    public void floatFieldPressed(KeyEvent event) {
    	
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
    
    //Cierra la venta de nuevoProducto
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
    
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
	   	reload(stage);
    }
    
    public void activarProductoGuardar(KeyEvent event) {
    	String tipoProducto = combobox_generalTipoProducto.getSelectionModel().getSelectedItem();
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
    		if(textfield_generalCodigo.getLength() > 0 && textfield_generalRubro.getLength() > 0 && textfield_generalProveedor.getLength() > 0
    			&& exAct.getLength() > 0 && exMax.getLength() > 0 && exAct.getLength() > 0 && textfield_generalNombre.getLength() > 0) {
    			button_productGuardar.setDisable(false);
    		}
    		else {
    			button_productGuardar.setDisable(true);
    		}
    	}	
    }
    
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
    	}
    }
    
    
    //Guardar Producto (En Progreso)
    public void guardarProducto(ActionEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	boolean canRegister = true;
    	String codigo = textfield_generalCodigo.getText();
    	String nombre = textfield_generalNombre.getText();
    	Rubro rubro = null;
    	Proveedores proveedor = null;
    	setCostoYPrecioTotal(null);
    	Precio precio = new Precio(Float.parseFloat(textfield_preciosPrecio.getText()), "", true);
    	
    	//Guardando precio en la base de datos
    	Controladora.getInstance().getMisPrecios().add(precio);
    	Controladora.getInstance().guardarPrecioSQL(precio);
    	
    	String descripcion = "";
    	String codigoBarra = "";
    	String tipoProducto = combobox_generalTipoProducto.getSelectionModel().getSelectedItem();
    	UnidadMedida unidad = null;
    	
    	float costoTotal = Float.parseFloat(textfield_preciosCostos.getText());
    	if(textfield_generalUnidad.getLength() != 0) {
    		unidad = tableview_unidadList.getSelectionModel().getSelectedItem();
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
    	for(Proveedores p : Controladora.getInstance().getMisProveedores()) {
    		if(p.getCodigo().equalsIgnoreCase(textfield_generalProveedor.getText())) {
    			proveedor = p;
    		}
    	}
    	
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
    		Date date = null;
    		Partida partida = null;	
    		if(checkbox_generalProducible.isSelected()) {
    			partida = new Partida();
    			for(String s : listview_partidaSelect.getItems()) {
    				String nombreSelect = Controladora.getInstance().findPartidaNombre(s);
    				String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
    				Estandar productoPart = Controladora.getInstance().buscarProducto(nombreSelect);
    				CantProductosUtilizados c = new CantProductosUtilizados(productoPart, Float.parseFloat(cantidadSelect));
    				
    				Controladora.getInstance().getMisCantProductosUtilizados().add(c);
    				Controladora.getInstance().guardarCantProductosUtilizadosSQL(productoPart, c);
    				partida.agregarProductoUtilizado(c);
    				float cantidadRestar = (productoPart.getExistenciaActual() - (Float.parseFloat(cantidadSelect) * Float.parseFloat(existenciaActual)));
    				int indiceProducto = Controladora.getInstance().getMisProductosEstandar().indexOf(productoPart)+1;
    				
    				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
    			}
    		}
    		
    		
    		//No se registra nombre, fecha, y muchas otras cosas
    		if(canRegister) {
    			Estandar estandar = new Estandar(Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), Float.parseFloat(existenciaActual), date, costoDeCompra, fabricado, partida, codigo, nombre,
    				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costoManoObra, "", "", costoTotal);
    			//for(CostoIndirectoProducto c : tableview_costosIndirectos.getItems()) {
    				//estandar.getCostosIndirectos().add(c);
    			//}
    			
    			
    			Controladora.getInstance().getMisProductosEstandar().add(estandar);
    			Controladora.getInstance().getMisProductos().add(estandar);
    			
    			Controladora.getInstance().guardarProductosSQL(estandar);
    			Controladora.getInstance().guardarEstandarSQL(estandar);
    			
    			
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
    		Date date = null;
    		ArrayList<CantProductosUtilizados> productsForKit = new ArrayList<>();
    		for(String item : listview_partidaSelect.getItems()) {
    			String nombreItem = Controladora.getInstance().findPartidaNombre(item);
    			String cantidad = Controladora.getInstance().findPartidaCantidad(item);
    			for(Estandar p : Controladora.getInstance().getMisProductosEstandar()) {
    				if(nombreItem.equals(p.getNombre())) {
    					CantProductosUtilizados c = new CantProductosUtilizados(p, Float.parseFloat(cantidad));
    					Controladora.getInstance().getMisCantProductosUtilizados().add(c);
    					Controladora.getInstance().guardarCantProductosUtilizadosSQL(p, c);
    					productsForKit.add(c);
    				}
    			}
    		}
    		//Visitar esto nuevamente
    		if(canRegister) {
    			Kit kit = new Kit(productsForKit, Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), Float.parseFloat(existenciaActual), date, codigo, nombre,
    				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costo, "", "", costoTotal);
    			
    			Controladora.getInstance().getMisProductos().add(kit);
    			Controladora.getInstance().guardarProductosSQL(kit);
    			
    			Controladora.getInstance().getMisProductosKit().add(kit);
    			Controladora.getInstance().guardarKitSQL(kit);
    			
    			for(CantProductosUtilizados c : productsForKit) {
    				Controladora.getInstance().guardarKitProductosSQL(kit, c);
    			}
    			
    		}
    	}
    	
    	else if(tipoProducto.equalsIgnoreCase("Servicio")) {
    		float costo = 0;
    		float costoManoObra = 0;
    		ManoDeObra infoManoDeObra = null;
    		CategoriaEmpleado categoriaempleado = null;
    		ArrayList<CantProductosUtilizados> productsForServicio = new ArrayList<>();
    		for(String item : listview_partidaSelect.getItems()) {
    			String nombreItem = Controladora.getInstance().findPartidaNombre(item);
    			String cantidadItem = Controladora.getInstance().findPartidaCantidad(item);
    			for(Estandar p : Controladora.getInstance().getMisProductosEstandar()) {
    				if(nombreItem.equals(p.getNombre())) {
    					CantProductosUtilizados c = new CantProductosUtilizados(p, Float.parseFloat(cantidadItem));
    				
    					Controladora.getInstance().getMisCantProductosUtilizados().add(c);
    					Controladora.getInstance().guardarCantProductosUtilizadosSQL(p, c);
    					
    					productsForServicio.add(c);
    				}
    			}
    		}
    		if(canRegister) {
    			costo = Float.parseFloat(textfield_preciosCostos.getText());
    			CategoriaEmpleado categoriaEmpleado = Controladora.getInstance().buscarCategoria(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().substring(0, combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().indexOf(":")));
    			
    			System.out.println(combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().substring(0, combobox_costosEncargadosFabricacion.getSelectionModel().getSelectedItem().indexOf(":")));
    			
    			Servicio servicio = new Servicio(codigo, nombre, descripcion, rubro, tipoProducto, proveedor, null, "", unidad, precio, "", codigoBarra,
    					descripcion, categoriaEmpleado, productsForServicio, costoTotal);
    			Controladora.getInstance().addProductoServicio(servicio);
    			Controladora.getInstance().addProducto(servicio);
    			
    			Controladora.getInstance().guardarProductosSQL(servicio);
    			Controladora.getInstance().guardarServiciosSQL(servicio, categoriaEmpleado);
    			
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
    				Estandar productoPart = Controladora.getInstance().buscarProducto(nombreSelect);
    				CantProductosUtilizados c = new CantProductosUtilizados(productoPart, Float.parseFloat(cantidadSelect));
    				
    				Controladora.getInstance().getMisCantProductosUtilizados().add(c);
    				Controladora.getInstance().guardarCantProductosUtilizadosSQL(productoPart, c);
    				partida.agregarProductoUtilizado(c);
    				float cantidadRestar = (productoPart.getExistenciaActual() - (Float.parseFloat(cantidadSelect) * existenciaActual));
    				int indiceProducto = Controladora.getInstance().getMisProductosEstandar().indexOf(productoPart)+1;
    				
    				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
    			}
    		}
    		
    		//Registrando un producto tipo matriz
    		if(canRegister) {
    			Estandar matriz = new Estandar(existenciaActual, Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), existenciaActual, date, costoDeCompra, fabricado, partida, codigo, nombre,
    				descripcion, rubro, tipoProducto, proveedor, null, null, "", unidad, precio, "", codigoBarra, costoManoObra, "", "", costoTotal);
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
    			
    			Controladora.getInstance().guardarProductosSQL(matriz);
    			Controladora.getInstance().guardarEstandarSQL(matriz);
    			
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
    		//Limpiando tab general
    		float existencia = 1;
    		try {
    			if(tipoProducto.equalsIgnoreCase("Estandar") || tipoProducto.equalsIgnoreCase("Kit")) {
    				existencia = Float.parseFloat(exAct.getText());
    			}
    			else {
    				for(Combinaciones c : combinacionFinal) {
    					existencia += c.getExistenciaActual();
    				}
    			}
    			
    		}
    		catch(NullPointerException e) {
    			
    		}
    		exAct.setText(""); exMin.setText(""); exMax.setText("");
    		textfield_generalProveedor.setText(""); textfield_generalRubro.setText("");
    		textfield_generalCodigo.setText(""); textfield_generalBarra.setText(""); textarea_generalDescripcion.setText("");
    		textfield_generalNombre.setText(""); textfield_generalUnidad.setText("");
    		checkbox_generalProducible.setSelected(false);
    		tableview_proveedorBuscar.getSelectionModel().clearSelection();
    		tableview_rubroBuscar.getSelectionModel().clearSelection();
		
    		//Limpiando tab partida
    		for(String item : listview_partidaSelect.getItems()) {
    			String itemNombre = Controladora.getInstance().findPartidaNombre(item);
    			String itemCantidad = Controladora.getInstance().findPartidaCantidad(item);
    			System.out.println(itemNombre + itemCantidad);
    			Estandar listview_estandar = Controladora.getInstance().buscarProducto(itemNombre);
    			for(int i = 0; i < Controladora.getInstance().getMisProductosEstandar().size(); i++) {
    				if(Controladora.getInstance().getMisProductosEstandar().get(i).equals(listview_estandar)) {
    					Controladora.getInstance().getMisProductosEstandar().get(i).setExistenciaActual(
    							Controladora.getInstance().getMisProductosEstandar().get(i).getExistenciaActual() - (Float.parseFloat(itemCantidad)*existencia));
    				}
    			}
    		}
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
    		for(int i = 0; i < listView_atributos2.getItems().size(); i++) {
    			listView_atributos2.getItems().remove(i);
    		}
    		for(int i = 0; i < listView_combinaciones.getItems().size(); i++) {
    			listView_combinaciones.getItems().remove(i);
    		}
    		textfield_numSerie.setText("");
    		textfield_cantidadComb.setText("");
    		tabpane_everything.getSelectionModel().select(tab_general);
    		button_productGuardar.setDisable(true);
    		combinacionFinal.clear();
    		
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
    		fillPartida();
    		fillPreciosTab();
    		fillGeneralTab();
    		rellenarCostosGenerales();
    		getGastosDirectos().clear();
    		getGastosIndirectos().clear();
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
    		textfield_generalProveedor.setDisable(false);
    		button_productoBuscarProveedor.setDisable(false);
    		textfield_generalUnidad.setDisable(false);
    		button_BuscarUnidadMedida.setDisable(false);
    		textfield_costoPrecioCompraProducto.setDisable(false);
    		
    		exAct.setText("");
    		exMin.setText("");
    		exMax.setText("");
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
    		
    		exAct.setText("");
    		exMin.setText("");
    		exMax.setText("");
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
    		
    		exAct.setText("0");
    		exMin.setText("0");
    		exMax.setText("0");
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
    		
    		exAct.setText("");
    		exMin.setText("");
    		exMax.setText("");
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
    	String atributo1 = listView_atributos1.getSelectionModel().getSelectedItem();
    	String atributo2 = listView_atributos2.getSelectionModel().getSelectedItem();
    	String atributo3 = listView_atributos3.getSelectionModel().getSelectedItem();
    	int i;
    	if((!textfield_numSerie.getText().isEmpty() && !textfield_cantidadComb.getText().isEmpty()))
    	{
    		String num = textfield_numSerie.getText();
    		float cant = Float.parseFloat(textfield_cantidadComb.getText());
    		ArrayList<Atributos> a = Controladora.getInstance().getMisAtributos();
    		ArrayList<Atributos> b = new ArrayList<>(); 
    		int i1;
    		for(i1=0; i1<a.size(); i1++)
    		{
    			if(a.get(i1).getNombre().equalsIgnoreCase(atributo1) && atributo1!=null)
    			{
    				b.add(a.get(i1));
    			}
    			
    			if(a.get(i1).getNombre().equalsIgnoreCase(atributo2) && atributo2!=null)
    			{
    				b.add(a.get(i1));
    			}
    			
    			if(a.get(i1).getNombre().equalsIgnoreCase(atributo3) && atributo3!=null)
    			{
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
    		if(b.size()>2)
    		{
    			String atri3 = ", " + b.get(2).getGrupo() + ": " + b.get(2).getNombre();
    			
    			pcomb = comb.numeroSerie + " " + atri1 + atri2 + atri3 + ", " + "Existencia: " + comb.getExistenciaActual(); 
    		}
    		else
    		{
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
    
    public void pasarDerCosto(ActionEvent event)
    {
    	String costo = listview_CostosGenerales.getSelectionModel().getSelectedItem();
    	listview_CostosSelect.getItems().add(costo);
    	listview_CostosGenerales.getItems().remove(costo);
    }
    
    public void pasarIzqCosto(ActionEvent event)
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
    
    public void pasarDerCostoIndirecto(ActionEvent event)
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
    
    public void pasarIzqCostoIndirecto(ActionEvent event)
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
    	
    	if(!textfield_costosValor.getText().isEmpty())
    	{	
    		if(radiobutton_costosIndirectos.isSelected())
        	{
        		int i;
        		
        		for(i = 0; i < listview_CostosSelecIndirectos.getItems().size(); i++)
        		{
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
        		p = Controladora.getInstance().buscarProducto(selection);
        		
        		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Area"))
        		{
        			combobox_ConversorUnidad.getItems().clear();
        			combobox_ConversorUnidad.getItems().addAll("Sq Pulgadas", "Sq Pies", "Sq Yardas", "Sq Milimetros", "Sq Centimetros", "Sq Metros");
        		}
        		
        		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Longitud"))
        		{
        			combobox_ConversorUnidad.getItems().clear();
        			combobox_ConversorUnidad.getItems().addAll("Pulgadas", "Pies", "Yardas", "Milimetros", "Centimetros", "Metros");
        		}
        		
        		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Masa"))
        		{
        			combobox_ConversorUnidad.getItems().clear();
        			combobox_ConversorUnidad.getItems().addAll("Grano", "Onza", "Libra", "Miligramo", "Gramo", "Kilogramo");
        		}
        		
        		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Volumen"))
        		{
        			combobox_ConversorUnidad.getItems().clear();
        			combobox_ConversorUnidad.getItems().addAll("Pulgadas Cb", "Pies  Cb", "Yardas Cb", "Cuchara de té", "Cuchara de madera", "Onza fluida", "Taza", "Medio litro", "Cuarto de galón", "Galón", "Barril", "Milímetros cb", "Centímetros cb", "Metros cb", "Mililitros", "Litros");
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
    
    public void movePartida(ActionEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	boolean isAlreadySelected = false;
    	String selection = null;
    	int posicion;
    	posicion = listview_partida.getSelectionModel().getSelectedItem().indexOf("[");
		selection = listview_partida.getSelectionModel().getSelectedItem().substring(0, posicion);
    	String select_items = listview_partida.getSelectionModel().getSelectedItem();
    	String cantidad = Controladora.getInstance().findPartidaCantidad(select_items);
    	String nameOriginal = Controladora.getInstance().findPartidaNombre(select_items);
    	String item_moved = "";
    	String tipoConversion = "";
    	float costo = 0;
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
    		case "Cuchara de té":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Cuchara de té", Float.parseFloat(textfield_partidaCantidad.getText()));
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
    		case "Cuarto de galón":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Cuarto de galón", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Galón":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Galón", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Barril":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Barril", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Milímetros cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Milímetros cb", Float.parseFloat(textfield_partidaCantidad.getText()));
    			break;
    		case "Centímetros cb":
    			cantidadConvertida = estandar.get(0).getUnidadMedida().Conversion("Centímetros cb", Float.parseFloat(textfield_partidaCantidad.getText()));
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
    	if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Servicio") || combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Kit")) {
    		existencia = 1;
    	}
    	else {
    		try {
        		existencia = Float.parseFloat(exAct.getText());
        	}
        	catch(NullPointerException e) {
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
    		if(estandar.get(0).getUnidadMedida() != null) {
    			item_moved = nameOriginal + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + cantidadConvertida + "]" + " (" + cantidadConvertida*existencia + ")";
    		}
    		else {
    			item_moved = nameOriginal + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + cantidadConvertida + "]" + " (" + cantidadConvertida*existencia + ")";
    		}
    		
    		//e.getNombre() + "[" + "Unidad: " + e.getUnidadMedida().getAbreviatura() + ", disponibles: " + e.getExistenciaActual() + "]"
    		listview_partida.getItems().remove(listview_partida.getSelectionModel().getSelectedIndex());
    	
    		float cantidadRestante = (Float.parseFloat(cantidad) - (cantidadConvertida*existencia));
    		DecimalFormat formato1 = new DecimalFormat("0.00");
    		if(Float.parseFloat(cantidad) != Float.parseFloat(textfield_partidaCantidad.getText())) {
    			if(estandar.get(0).getUnidadMedida() != null) {
    				listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
    					cantidadRestante + "]");
    			}
    			else {
    				listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
        					cantidadRestante + "]");
    			}
    			
    		
    			listview_partida.refresh();
    		}
    		for(String s : listview_partidaSelect.getItems()) {
    			String nameSelect = Controladora.getInstance().findPartidaNombre(s);
    			String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
    			System.out.println(cantidadSelect);
    			if(nameSelect.equalsIgnoreCase(nameOriginal)) {
    				
    				listview_partidaSelect.getItems().remove(s);
    				if(estandar.get(0).getUnidadMedida() != null) {
    					item_moved = nameSelect + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", Usando: " + 
    						(cantidadConvertida + Float.parseFloat(cantidadSelect)) + "]" + " (" + ((cantidadConvertida + Float.parseFloat(cantidadSelect)) * existencia) + ")";
    				}
    				else {
    					item_moved = nameSelect + "[" + "Unidad: " + "Unidad nula" + ", Usando: " + 
        						(cantidadConvertida + Float.parseFloat(cantidadSelect)) + "]" + " (" + ((cantidadConvertida + Float.parseFloat(cantidadSelect)) * existencia) + ")";
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
    
    public void movePartidaSelect(ActionEvent event) {
    	String select_items = listview_partidaSelect.getSelectionModel().getSelectedItem();
    	String nombreSelect = Controladora.getInstance().findPartidaNombre(select_items);
    	String cantidad = Controladora.getInstance().findPartidaCantidad(select_items);
    	float existencia = Float.parseFloat(exAct.getText());
    	System.out.println(nombreSelect);
    	ArrayList<Estandar> estandar = Controladora.getInstance().searchProductsEstandar(nombreSelect.toLowerCase(), "Nombre");
    	DecimalFormat formato1 = new DecimalFormat("0.00");
    	
    	String original = "";
    	if(estandar.get(0).getUnidadMedida() != null) {
    		original = nombreSelect + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
    			(estandar.get(0).getExistenciaActual() - (Float.parseFloat(cantidad)*existencia)) + "]";
    	}
    	else {
    		original = nombreSelect + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
        			(estandar.get(0).getExistenciaActual() - (Float.parseFloat(cantidad))) + "]";
    	}
    	
    	listview_partidaSelect.getItems().remove(listview_partidaSelect.getSelectionModel().getSelectedItem());
    	listview_partida.getItems().remove(original);
    	for(String s : listview_partida.getItems()) {
    		System.out.println(s);
    		System.out.println("Esta es la original: " + original);
    	}
    	
    	
    	
    	if(estandar.get(0).getUnidadMedida() != null) {
    		listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + estandar.get(0).getUnidadMedida().getAbreviatura() + ", disponibles: " + 
				(estandar.get(0).getExistenciaActual()) + "]");
    	}
    	else {
    		listview_partida.getItems().add(estandar.get(0).getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + 
    				(estandar.get(0).getExistenciaActual()) + "]");
    	}
    	
    	listview_partida.refresh();
    	button_partidaSendBack.setDisable(true);
    }
    
    /*
    //FUNCIONES COSTO DIRECTO
    public void costoDirectoActivarAgregar(KeyEvent event) {
    	if(textfield_costosDirectosNombre.getLength() > 0 && textfield_costosDirectosValor.getLength() >= 0) {
    		button_costosDirectosAgregar.setDisable(false);
    	}
    	else {
    		button_costosDirectosAgregar.setDisable(true);
    	}
    } */
    
    /*
	public void costoDirectoIngresarTableView(ActionEvent event) {
    	CostoDirecto costodirecto = new CostoDirecto(textfield_costosDirectosNombre.getText(), Double.parseDouble(textfield_costosDirectosValor.getText()), textarea_costosDirectosDescripcion.getText());
    	ObservableList<CostoDirecto> data = FXCollections.observableArrayList();
    	data.add(costodirecto);
    	tablecolumn_costosDirectosNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
    	tablecolumn_costosDirectosValor.setCellValueFactory(new PropertyValueFactory<>("Valor"));
    	tablecolumn_costosDirectosDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
    	tableview_costosDirectos.getItems().add(costodirecto);
    	System.out.println(tableview_costosDirectos);
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
    } */
	
    /*
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
	*/
    
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
			 int posicion = valor.indexOf("[");
    		 String selection = valor.substring(0, posicion);
    		 String nombre = Controladora.getInstance().findPartidaNombre(valor);
    		 Estandar p = Controladora.getInstance().buscarProducto(nombre);
    		 
			 //String partida = Controladora.getInstance().findPartidaCosto(valor);
			 String cantidad = Controladora.getInstance().findPartidaCantidad(valor);
			 valorPartida += p.getCosto() * Float.parseFloat(cantidad);
		}
		if(checkbox_generalProducible.isSelected() && textfield_costosTiempoFabricacion.getLength() > 0) {
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
			textfield_preciosPorcientoGanancia.setText("");
		}
		else {
			textfield_preciosPorcientoGanancia.setDisable(true);
			textfield_preciosPorcientoGanancia.setText("0");
			calcularPrecio(null);
		}
		
	}
	
	public void habilitarImpuestos(ActionEvent event) {
		if(checkbox_Impuestos.isSelected()) {
			textfield_preciosImpuestos.setDisable(false);
			textfield_preciosImpuestos.setText("18");
			calcularPrecio(null);
		}
		else {
			textfield_preciosImpuestos.setDisable(true);
			textfield_preciosImpuestos.setText("0");
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
    	}
    }
    
    public void returnRubroSearch(ActionEvent event) {
    	textfield_generalRubro.setText(tableview_rubroBuscar.getSelectionModel().getSelectedItem().getNombreRubro());
    	button_aceptarBusquedaRubro.setDisable(true);
    	titledpane_productoBuscarRubro.setVisible(false);
    	activarProductoGuardar(null);
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
    	textfield_generalUnidad.setText(tableview_unidadList.getSelectionModel().getSelectedItem().getNombre());
    	button_aceptarUnidad.setDisable(true);
    	titledpane_productoBuscarUnidadMedida.setVisible(false);
    }
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Seteando tableview de la partida
		fillPartida();
		
		//Seteando general
		fillGeneralTab();
		
		//Seteando tab de precios
		fillPreciosTab();
		
		
		//Seteando listview gastosgenerales
		rellenarCostosGenerales();
	}
	
	public void fillUnidadMedida(ArrayList<UnidadMedida> u) {
		ObservableList<UnidadMedida> data = FXCollections.observableArrayList();
		if(u == null) {
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
    	tableview_proveedorBuscar.setItems(data);
    	tableview_proveedorBuscar.refresh();
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
    	tableview_rubroBuscar.setItems(data);
    	tableview_rubroBuscar.refresh();
	}
	
	public void fillPartida() {
		ObservableList<String> dataPartida = FXCollections.observableArrayList();
		for(Estandar e : Controladora.getInstance().getMisProductosEstandar()) {
			if(e.getUnidadMedida() != null) {
				dataPartida.add(e.getNombre() + "[" + "Unidad: " + e.getUnidadMedida().getAbreviatura() + ", disponibles: " + e.getExistenciaActual() + "]");
			}
			else {
				dataPartida.add(e.getNombre() + "[" + "Unidad: " + "Unidad nula" + ", disponibles: " + e.getExistenciaActual() + "]");
			}
		}
		listview_partida.setItems(dataPartida);
		listview_partida.refresh();
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
    
    public void rellenarCostosGenerales() //Ver porqué no funciona
    {
    	ObservableList<String> ob = FXCollections.observableArrayList();
    	
		for(GastoGeneral e : Controladora.getInstance().getMisGastosGenerales()) {
			ob.add(e.getNombre());
			
		}
		listview_GastosGeneralesIndirectos.setItems(ob);;
		listview_GastosGeneralesIndirectos.refresh();

		ObservableList<String> fabricacionData = FXCollections.observableArrayList();
		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
			fabricacionData.add(c.getNombre() + ": " + c.getSueldo() + "$");
		}
		combobox_costosEncargadosFabricacion.setItems(fabricacionData);
		
		ObservableList<String> tiempoData = FXCollections.observableArrayList();
		tiempoData.addAll("Segundos", "Minutos", "Horas");
		combobox_costosTiempoFabricacion.setItems(tiempoData);
		combobox_costosTiempoFabricacion.getSelectionModel().select("Horas");
    }

}
