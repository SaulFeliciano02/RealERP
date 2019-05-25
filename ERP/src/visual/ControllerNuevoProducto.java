package visual;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logico.Controladora;
import logico.CostoDirecto;
import logico.CostoIndirectoProducto;
import logico.Proveedores;
import logico.Rubro;

public class ControllerNuevoProducto implements Initializable {
	
/**VARIABLES PARA CREAR PRODUCTOS**/
	@FXML TabPane tabpane_everything;
	
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
	@FXML private TextField textfield_generalDescripcion;
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
    	else if(event.getSource().equals(textfield_preciosPorcientoGanancia)) {
    		calcularPrecio(event);
    	}
    	else if(event.getSource().equals(exAct) || event.getSource().equals(exMax) || event.getSource().equals(exMin)) {
    		activarProductoGuardar(event);
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
    
    //Guardar Producto (En Progreso)
    public void guardarProducto(ActionEvent event) {
    	String codigo = textfield_generalCodigo.getText();
    	Rubro rubro = null;
    	Proveedores proveedor = null;
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
    	if(combobox_generalTipoProducto.getSelectionModel().getSelectedItem().equalsIgnoreCase("Estandar")) {
    		String existenciaActual = exAct.getText();
    		String existenciaMinima = exMin.getText();
    		String existenciaMaxima = exMax.getText();
    	}
    	
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
	
	//FUNCIONES BUSQUEDA PROVEEDOR
	public void buscarProveedor(ActionEvent event) {
		titledpane_productoBuscarProveedor.setVisible(true);
		titledpane_productoBuscarProveedor.setDisable(false);
		fillProveedorList(null);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

}
