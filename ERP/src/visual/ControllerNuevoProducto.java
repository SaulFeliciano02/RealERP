package visual;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logico.Atributos;
import logico.Combinaciones;
import logico.Controladora;
import logico.CostoDirecto;
import logico.CostoIndirectoProducto;
import logico.Estandar;
import logico.GastoGeneral;
import logico.GrupoAtributo;
import logico.Kit;
import logico.Precio;
import logico.Producto;
import logico.Proveedores;
import logico.Rubro;

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
	
	//PARTIDA
	@FXML private ListView<String> listview_partida;
	@FXML private ListView<String> listview_partidaSelect;
	@FXML private Button button_partidaSendTo;
	@FXML private Button button_partidaSendBack;
	@FXML private TextField textfield_partidaCantidad;
	
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
	
	//PRECIOS
	@FXML private TextField textfield_preciosCostos;
	@FXML private TextField textfield_preciosPorcientoGanancia;
	@FXML private TextField textfield_preciosImpuestos;
	@FXML private TextField textfield_preciosPrecio;
	@FXML private CheckBox checkbox_preciosHabilitar;
	
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
   
    /**FUNCIONES GENERALES**/
    
    //Verifica si el input de un textfield es un numero
    public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	/*if(event.getSource().equals(textfield_costosValor)) {
    		costoDirectoActivarAgregar(event);
    	}*/

    	else if(event.getSource().equals(textfield_preciosPorcientoGanancia)) {
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
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    
    public void activarProductoGuardar(KeyEvent event) {
    	if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Estandar")) {
    		if(textfield_generalCodigo.getLength() > 0 && textfield_generalRubro.getLength() > 0 && textfield_generalProveedor.getLength() > 0
    			&& exAct.getLength() > 0 && exMax.getLength() > 0 && exAct.getLength() > 0) {
    			button_productGuardar.setDisable(false);
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

    	}
    	else {
    		tab_partida.setDisable(true);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    	}
    }
    
    
    //Guardar Producto (En Progreso)
    public void guardarProducto(ActionEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	boolean canRegister = true;
    	String codigo = textfield_generalCodigo.getText();
    	Rubro rubro = null;
    	Proveedores proveedor = null;
    	Precio precio = new Precio(Float.parseFloat(textfield_preciosPrecio.getText()), "", true);
    	String descripcion = "";
    	String codigoBarra = "";
    	String tipoProducto = combobox_generalTipoProducto.getSelectionModel().getSelectedItem();
    	try {
    		descripcion = textarea_generalDescripcion.getText();
    		codigoBarra = textfield_generalBarra.getText();
    	}
    	catch(NullPointerException e) {
    		
    	}
    	
    	for(Rubro r : Controladora.getInstance().getMisRubros()) {
    		if(r.getCodigo().equalsIgnoreCase(textfield_generalRubro.getText())) {
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
    		float costo = 0;
    		boolean fabricado = false;
    		if(checkbox_generalProducible.isSelected()) {
    			costo = Float.parseFloat(textfield_preciosCostos.getText());
    			fabricado = true;
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
    		//No se registra nombre, fecha, y muchas otras cosas
    		if(canRegister) {
    			Estandar estandar = new Estandar(Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), date, costo, fabricado, codigo, "",
    				descripcion, rubro, tipoProducto, proveedor, null, null, "", null, precio, "", codigoBarra, costo, "", "");
    			/*for(CostoIndirectoProducto c : tableview_costosIndirectos.getItems()) {
    				estandar.getCostosIndirectos().add(c);
    			}*/
    			Controladora.getInstance().addProducto(estandar);
    			Controladora.getInstance().addProductoEstandar(estandar);
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
    		ArrayList<Producto> productsForKit = new ArrayList<>();
    		for(String item : listview_partidaSelect.getItems()) {
    			String nombre = Controladora.getInstance().findPartidaNombre(item);
    			for(Producto p : Controladora.getInstance().getMisProductosEstandar()) {
    				if(nombre.equals(p.getNombre())) {
    					productsForKit.add(p);
    				}
    			}
    		}
    		//Visitar esto nuevamente
    		if(canRegister) {
    			Kit kit = new Kit(productsForKit, Integer.parseInt(existenciaActual), Integer.parseInt(existenciaMinima), Integer.parseInt(existenciaMaxima), date, codigo, "",
    				descripcion, rubro, tipoProducto, proveedor, null, null, "", null, precio, "", codigoBarra, costo, "", "");
    			Controladora.getInstance().getMisProductos().add(kit);
    			Controladora.getInstance().getMisProductosKit().add(kit);
    		}
    	}
    	
    	else if(tipoProducto.equalsIgnoreCase("Servicio")) {
    		
    	}
    	
    	else if(tipoProducto.equalsIgnoreCase("Matriz")) {
    		String existenciaActual = exAct.getText();
    		String existenciaMinima = exMin.getText();
    		String existenciaMaxima = exMax.getText();
    		float costo = 0;
    		boolean fabricado = false;
    		if(checkbox_generalProducible.isSelected()) {
    			costo = Float.parseFloat(textfield_preciosCostos.getText());
    			fabricado = true;
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
    		//No se registra nombre, fecha, y muchas otras cosas
    		if(canRegister) {
    			Estandar estandar = new Estandar(Float.parseFloat(existenciaActual), Float.parseFloat(existenciaMinima), Float.parseFloat(existenciaMaxima), date, costo, fabricado, codigo, "",
    				descripcion, rubro, tipoProducto, proveedor, null, null, "", null, precio, "", codigoBarra, costo, "", "");
    			for(Combinaciones c : combinacionFinal) {
    				estandar.getCombinaciones().add(c);
    			}
    			/*for(CostoIndirectoProducto c : tableview_costosIndirectos.getItems()) {
    				estandar.getCostosIndirectos().add(c);
    			}*/
    			Controladora.getInstance().addProducto(estandar);
    			Controladora.getInstance().addProductoEstandar(estandar);
    		}
    	}
    	
    	if(canRegister) {
    		//Limpiando tab general
    		exAct.setText(""); exMin.setText(""); exMax.setText("");
    		textfield_generalProveedor.setText(""); textfield_generalRubro.setText("");
    		textfield_generalCodigo.setText(""); textfield_generalBarra.setText(""); textarea_generalDescripcion.setText("");
		
    		//Limpiando tab partida
    		for(String item : listview_partidaSelect.getItems()) {
    			String itemNombre = Controladora.getInstance().findPartidaNombre(item);
    			String itemCantidad = Controladora.getInstance().findPartidaCantidad(item);
    			ArrayList<Estandar> listview_estandar = Controladora.getInstance().searchProductsEstandar(itemNombre, "Nombre");
    			for(int i = 0; i < Controladora.getInstance().getMisProductosEstandar().size(); i++) {
    				if(Controladora.getInstance().getMisProductosEstandar().get(i).equals(listview_estandar.get(0))) {
    					Controladora.getInstance().getMisProductosEstandar().get(i).setExistenciaActual(
    							Controladora.getInstance().getMisProductosEstandar().get(i).getExistenciaActual() - Float.parseFloat(itemCantidad));
    				}
    			}
    		}
    		for(int i = 0; i < listview_partida.getItems().size(); i++) {
    			listview_partida.getItems().remove(i);
    		}
    		for(int i = 0; i < listview_partidaSelect.getItems().size(); i++) {
    			listview_partidaSelect.getItems().remove(i);
    		}
		
		
    		/*
			//Limpiando costos directos
			textfield_costosDirectosNombre.setText("");
			textfield_costosDirectosValor.setText("");
			textarea_costosDirectosDescripcion.setText("");
			int tableview_costosDirectosSize = tableview_costosDirectos.getItems().size();
			tableview_costosDirectos.getItems().remove(0, tableview_costosDirectosSize-1);
		
			//Limpiando costos indirectos
			textfield_costosIndirectosNombre.setText("");
			textfield_costosIndirectosValor.setText("");
			textarea_costosIndirectosDescripcion.setText("");
			int tableview_costosIndirectosSize = tableview_costosIndirectos.getItems().size();
			tableview_costosIndirectos.getItems().remove(0, tableview_costosIndirectosSize);
    		 */
		
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
    		tabpane_everything.getSelectionModel().select(tab_general);
    		fillPartida();
    		fillPreciosTab();
    		fillGeneralTab();
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
    	}
    	else if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Kit")) {
    		exAct.setDisable(false);
    		exMin.setDisable(false);
    		exMax.setDisable(false);
    		
    		tab_partida.setDisable(true);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		
    		checkbox_generalProducible.setDisable(true);
    	}
    	else if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Servicio")) {
    		exAct.setDisable(true);
    		exMin.setDisable(true);
    		exMax.setDisable(true);
    		tab_combinaciones.setDisable(true);
    		
    		tab_partida.setDisable(false);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		
    		checkbox_generalProducible.setDisable(true);
    	}
    	else if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Matriz")) {
    		tab_combinaciones.setDisable(false);
    		exAct.setDisable(false);
    		exMin.setDisable(false);
    		exMax.setDisable(false);
    		tab_partida.setDisable(true);
    		
    		radiobutton_costosDirectos.setDisable(true);
    		radiobutton_costosDirectos.setSelected(false);
    		pane_costosDirectos.setVisible(false);
    		radiobutton_costosIndirectos.setDisable(false);
    		radiobutton_costosIndirectos.setSelected(true);
    		pane_costosIndirectos.setVisible(true);
    		
    		checkbox_generalProducible.setDisable(false);
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
    
    public void rellenarCostosGenerales()
    {
    	ArrayList<String> nombreYprecio = new ArrayList<>();
    	ArrayList<GastoGeneral> gasto = Controladora.getInstance().getMisGastosGenerales();
    	int i;
    	for(i = 0; i<gasto.size(); i++)
    	{
    		nombreYprecio.add(gasto.get(i).getNombre() + " " + gasto.get(i).getPrecioUnitario() + "$RD");
    	}
    	listview_CostosGenerales.getItems().addAll(nombreYprecio);
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
    
    public void agregarNuevosCostos(ActionEvent event)
    {
    	if(!textfield_costosValor.getText().isEmpty())
    	{
    		if(radiobutton_costosDirectos.isSelected())
        	{
        		ArrayList<CostoDirecto> gastos = new ArrayList<>();
        		int i;
        		for(i = 0; i < listview_CostosSelect.getItems().size(); i++)
        		{
        			//Hacer un método en la clase controladora logica para que calcule el costo
        		}
        	}
    	}
    	
    }
    
    //FUNCIONES CREACION DE LA PARTIDA
    public void listview_PartidaClicked(MouseEvent event) {
    	if(!listview_partida.getSelectionModel().isEmpty()) {
    		button_partidaSendTo.setDisable(false);
    		textfield_partidaCantidad.setDisable(false);
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
    
    public void movePartida(ActionEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	boolean isAlreadySelected = false;
    	String select_items = listview_partida.getSelectionModel().getSelectedItem();
    	String cantidad = Controladora.getInstance().findPartidaCantidad(select_items);
    	String nameOriginal = Controladora.getInstance().findPartidaNombre(select_items);
    	String item_moved = "";
    	if(textfield_partidaCantidad.getLength() == 0) {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Eliga la cantida que utilizara");
    		a.show();
    	}
    	else if(Float.parseFloat(textfield_partidaCantidad.getText()) > Float.parseFloat(cantidad)) {
    		a.setAlertType(AlertType.ERROR);
			a.setContentText("No puede tomar mas productos de lo que tiene.");
			a.show();
    	}
    	else {
    		int i = 0;
    		while(select_items.charAt(i) != ':') {
    			item_moved += select_items.charAt(i);
    			i++;
    		}
    		ArrayList<Estandar> estandar = Controladora.getInstance().searchProductsEstandar(item_moved, "Nombre");
    		//Guardar este codigo para cuando guardemos un producto
    		/**for(int j = 0; j < Controladora.getInstance().getMisProductosEstandar().size(); j++) {
    			if(Controladora.getInstance().getMisProductosEstandar().get(j).equals(estandar.get(0))) {
    				Controladora.getInstance().getMisProductosEstandar().get(j).setExistenciaActual(
    					Controladora.getInstance().getMisProductosEstandar().get(j).getExistenciaActual() - Float.parseFloat(textfield_partidaCantidad.getText()));
    			}
    		}**/
    		item_moved += ": " + "cuesta " + estandar.get(0).getPrecio().getPrecio() + ", Usando: " + 
    				(Float.parseFloat(textfield_partidaCantidad.getText()));
    		listview_partida.getItems().remove(listview_partida.getSelectionModel().getSelectedIndex());
    	
    		float cantidadRestante = (Float.parseFloat(cantidad) - Float.parseFloat(textfield_partidaCantidad.getText()));
    		DecimalFormat formato1 = new DecimalFormat("0.00");
    		if(Float.parseFloat(cantidad) != Float.parseFloat(textfield_partidaCantidad.getText())) {
    			listview_partida.getItems().add(estandar.get(0).getNombre() + ": " + "cuesta " + estandar.get(0).getPrecio().getPrecio() + ", disponibles: " + 
    					formato1.format(cantidadRestante));
    		
    			listview_partida.refresh();
    		}
    		for(String s : listview_partidaSelect.getItems()) {
    			String nameSelect = Controladora.getInstance().findPartidaNombre(s);
    			if(nameSelect.equalsIgnoreCase(nameOriginal)) {
    				String cantidadSelect = Controladora.getInstance().findPartidaCantidad(s);
    				listview_partidaSelect.getItems().remove(s);
    				item_moved = nameSelect + ": " + "cuesta " + estandar.get(0).getPrecio().getPrecio() + ", Usando: " + 
    						(Float.parseFloat(textfield_partidaCantidad.getText()) + Float.parseFloat(cantidadSelect));
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
    	ArrayList<Estandar> estandar = Controladora.getInstance().searchProductsEstandar(nombreSelect, "Nombre");
    	
    	String original = nombreSelect + ": " + "cuesta " + estandar.get(0).getPrecio().getPrecio() + ", disponibles: " + 
				(estandar.get(0).getExistenciaActual() - Float.parseFloat(cantidad));
    	listview_partida.getItems().remove(original);
    	listview_partidaSelect.getItems().remove(listview_partida.getSelectionModel().getSelectedIndex()+1);
    	listview_partida.getItems().add(estandar.get(0).getNombre() + ": " + "cuesta " + estandar.get(0).getPrecio().getPrecio() + ", disponibles: " + 
				(estandar.get(0).getExistenciaActual()));
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
    
   //FUNCIONES PRECIO
	public void setCostoYPrecioTotal(Event event) {
		double valorDirecto = 0;
		double valorIndirecto = 0;
		double valorPartida = 0;
		listview_partidaSelect.getItems();
		
		/*
		for(CostoDirecto valor : tableview_costosDirectos.getItems()) {
			valorDirecto += valor.getValor();
		}
		for(CostoIndirectoProducto valor : tableview_costosIndirectos.getItems()) {
			valorIndirecto += valor.getValor();
		}
		
		*/
		for(String valor : listview_partidaSelect.getItems()) {
			 String partida = Controladora.getInstance().findPartidaCosto(valor);
			 String cantidad = Controladora.getInstance().findPartidaCantidad(valor);
			 valorPartida += Float.parseFloat(partida) * Float.parseFloat(cantidad);
		}
		textfield_preciosCostos.setText(Double.toString(valorDirecto + valorIndirecto + valorPartida));
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
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Seteando tableview de la partida
		fillPartida();
		
		//Seteando general
		fillGeneralTab();
		
		//Seteando tab de precios
		fillPreciosTab();
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
			dataPartida.add(e.getNombre() + ": " + "cuesta " + e.getPrecio().getPrecio() + ", disponibles: " + e.getExistenciaActual());
			
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
		textfield_preciosImpuestos.setText("18");
    }
    


}
