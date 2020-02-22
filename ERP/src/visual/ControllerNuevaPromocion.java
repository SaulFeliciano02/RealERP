package visual;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Controladora;
import logico.Producto;
import logico.Promocion;
import logico.Rubro;

public class ControllerNuevaPromocion implements Initializable{


    @FXML private RadioButton radiobutton_producto;
    @FXML private RadioButton radiobutton_rubro;
    @FXML private AnchorPane pane_PromocionProductos;
    @FXML private AnchorPane pane_PromocionRubro;
    @FXML private AnchorPane pane_infoPorDia;
    
    @FXML private Button button_promocionSend;
    @FXML private Button button_promocionReturn;
    
    @FXML Spinner<Integer> spinner_inicialHora;
    @FXML Spinner<Integer> spinner_inicialMinuto;
    @FXML Spinner<Integer> spinner_finalHora;
    @FXML Spinner<Integer> spinner_finalMinuto;
    
    @FXML DatePicker datepicker_fechaInicial;
    @FXML DatePicker datepicker_fechaFinal;
    
    @FXML CheckBox checkbox_lunes;
    @FXML CheckBox checkbox_martes;
    @FXML CheckBox checkbox_miercoles;
    @FXML CheckBox checkbox_jueves;
    @FXML CheckBox checkbox_viernes;
    @FXML CheckBox checkbox_sabado;
    @FXML CheckBox checkbox_domingo;
    
    @FXML private Label label_producto_rubro;
    
    @FXML private TextField textfield_promocionPorCiento;
    @FXML private TextField textfield_promocionNombre;
    
    @FXML private ListView<String> listview_promocionListado;
    @FXML private ListView<String> listview_promocionSeleccionados;
    
    @FXML private RadioButton radiobutton_porDia;
    @FXML private RadioButton radiobutton_porFecha;
    @FXML private VBox pane_porFecha;
    @FXML private VBox pane_porDia;
    
    @FXML private Button button_guardarPromocion;
    
    public void reload(Stage stage) {
    	
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
		    Window owner = stage.getOwner();
		   
		    primaryStage.show();
		    owner.hide();
		} catch (IOException e) {
			e.printStackTrace();
			}
	}
    
    //Cierra la ventana
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
    	reload(stage);
    }
    
    public void floatFieldPressed(KeyEvent event) {
    	TextField source = (TextField) event.getSource();
    	//ke.getCode().equals(KeyCode.VK_BACK_SPACE)
    	if(source.getLength() == 0 && !event.getCode().equals(KeyCode.BACK_SPACE)) {
    		if(!Controladora.getInstance().isFloat("", event.getCharacter())) {
        		event.consume();
        	}
    	}
    	else {
    		if(!Controladora.getInstance().isFloat(source.getText(), event.getCharacter()) && !event.getCode().equals(KeyCode.BACK_SPACE)) {
    			event.consume();
    		}
    	}
    }
    
    public void numericFieldPressed(KeyEvent event) {
    	TextField source = (TextField) event.getSource();
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	if(source.equals(textfield_promocionPorCiento)) {
    		activarGuardar(null);
    	}
    }
    
    public void sendToActivar(KeyEvent event) {
    	activarGuardar(null);
    }


    public void cambiodePane(ActionEvent event){
    	if(radiobutton_rubro.isSelected()) {
    		label_producto_rubro.setText("Listado de rubros");
    		fillRubros(null);
    	}
    	if(radiobutton_producto.isSelected()) {
    		label_producto_rubro.setText("Listado de productos");
    		fillProductos(null);
    	}
    	listview_promocionSeleccionados.getItems().clear();
    }
    
    public void activarGuardar(ActionEvent event) {
    	System.out.println("I got in here!");
    	if((listview_promocionSeleccionados.getItems().size() > 0 && textfield_promocionNombre.getLength() > 0) && (datepicker_fechaFinal.getValue() != null && datepicker_fechaInicial.getValue() != null
    			|| (checkbox_lunes.isSelected() || checkbox_martes.isSelected() || checkbox_miercoles.isSelected() || checkbox_jueves.isSelected() || checkbox_viernes.isSelected() || checkbox_sabado.isSelected() ||
    					checkbox_domingo.isSelected()))) {
    		button_guardarPromocion.setDisable(false);
    	}
    	else {
    		button_guardarPromocion.setDisable(true);
    	}
    }
    
    public void guardarPromocion(ActionEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	Alert success = new Alert(AlertType.INFORMATION, "La promoción ha sido guardada exitosamente.");
    	boolean canRegister = true;
    	String nombre = textfield_promocionNombre.getText();
    	int porciento = Integer.parseInt(textfield_promocionPorCiento.getText());
    	LocalDate fechaInicio = datepicker_fechaInicial.getValue();
    	LocalDate fechaFinal = datepicker_fechaFinal.getValue();
    	LocalTime horaInicio = LocalTime.of(spinner_inicialHora.getValue(), spinner_inicialMinuto.getValue(), 0);
    	LocalTime horaFinal = LocalTime.of(spinner_finalHora.getValue(), spinner_finalMinuto.getValue(), 0);
    	String dia = "";
    	try {
    		if(fechaInicio.compareTo(fechaFinal) > 0) {
    			a.setContentText("La fecha inicial es mayor que la fecha inicial!");
    			a.show();
    			canRegister = false;
    		}
    	}catch(NullPointerException e) {
    		
    	}
    	if(canRegister) {
    		if(checkbox_lunes.isSelected()) {
        		dia = "Lunes";
        	}
        	else if(checkbox_miercoles.isSelected()) {
        		dia = "Miercoles";
        	}
        	else if(checkbox_jueves.isSelected()) {
        		dia = "Jueves";
        	}
        	else if(checkbox_viernes.isSelected()) {
        		dia = "Viernes";
        	}
        	else if(checkbox_sabado.isSelected()) {
        		dia = "Sabado";
        	}
        	else if(checkbox_martes.isSelected()) {
        		dia = "Martes";
        	}
        	else if(checkbox_domingo.isSelected()) {
        		dia = "Domingo";
        	}
        	
        	Promocion promocion = null;
        	if(radiobutton_porDia.isSelected()) {
        		promocion = new Promocion(porciento, nombre, dia);
        	}
        	else {
        		promocion = new Promocion(porciento, nombre, fechaInicio, fechaFinal, horaInicio, horaFinal);
        	}
        	
        	if(radiobutton_producto.isSelected()) {
        		for(String items : listview_promocionSeleccionados.getItems()) {
        			Producto producto = Controladora.getInstance().buscarProducto(Controladora.getInstance().findFacturaNombre(items));
        			promocion.getProductos().add(producto);
        		}
        	}
        	else {
        		for(String items : listview_promocionSeleccionados.getItems()) {
        			ArrayList<Producto> productos = new ArrayList<>();
        			for(Producto p : Controladora.getInstance().getMisProductos()) {
        				if(!p.isBorrado()) {
        					if(p.getRubroProductoClass().getNombreRubro().equalsIgnoreCase(items)) {
        						productos.add(p);
        					}
        				}
        			}
        			promocion.setProductos(productos);
        		}
        	}
        	Controladora.getInstance().getMisPromociones().add(promocion);
        	
        	Controladora.getInstance().guardarPromocionSQL(promocion);
        	for(Producto p : promocion.getProductos()) {
        		Controladora.getInstance().guardarPromoProductoSQL(p, promocion);
        	}
        	success.showAndWait();
        	
        	listview_promocionSeleccionados.getItems().clear();
        	listview_promocionSeleccionados.refresh();
        	spinner_finalHora.getValueFactory().setValue(12);
        	spinner_inicialHora.getValueFactory().setValue(12);
        	spinner_finalMinuto.getValueFactory().setValue(0);
        	spinner_inicialMinuto.getValueFactory().setValue(0);
        	textfield_promocionNombre.setText("");
        	textfield_promocionPorCiento.setText("");
        	datepicker_fechaInicial.setValue(null);
        	datepicker_fechaFinal.setValue(null);
        	
        	checkbox_lunes.setSelected(false);
        	checkbox_martes.setSelected(false);
        	checkbox_miercoles.setSelected(false);
        	checkbox_jueves.setSelected(false);
        	checkbox_viernes.setSelected(false);
        	checkbox_sabado.setSelected(false);
        	checkbox_domingo.setSelected(false);
        	
        	radiobutton_producto.setSelected(true);
        	radiobutton_rubro.setSelected(false);
        	cambiodePane(null);
    	}
    	
    	
    }
    
    public void checkDiaFecha(ActionEvent event) {
    	RadioButton radioEvent = null;
    	try {
    		radioEvent = (RadioButton) event.getSource();
    	}
    	catch(NullPointerException e) {
    		
    	}
    	
    	if(radiobutton_porFecha.equals(radioEvent) || event == null) {
    		pane_porFecha.setVisible(true);
    		pane_porDia.setVisible(false);
    		
    		checkbox_lunes.setDisable(true);
    		checkbox_martes.setDisable(true);
    		checkbox_miercoles.setDisable(true);
    		checkbox_jueves.setDisable(true);
    		checkbox_viernes.setDisable(true);
    		checkbox_sabado.setDisable(true);
    		checkbox_domingo.setDisable(true);
    		
    		spinner_finalHora.setDisable(false);
    		spinner_finalMinuto.setDisable(false);
    		spinner_inicialHora.setDisable(false);
    		spinner_inicialMinuto.setDisable(false);
    		datepicker_fechaFinal.setDisable(false);
    		datepicker_fechaInicial.setDisable(false);
    		
    		pane_infoPorDia.setVisible(false);
    		
    	}
    	else {
    		
    		pane_porFecha.setVisible(false);
    		pane_porDia.setVisible(true);
    		
    		checkbox_lunes.setDisable(false);
    		checkbox_martes.setDisable(false);
    		checkbox_miercoles.setDisable(false);
    		checkbox_jueves.setDisable(false);
    		checkbox_viernes.setDisable(false);
    		checkbox_sabado.setDisable(false);
    		checkbox_domingo.setDisable(false);
    		
    		spinner_finalHora.setDisable(true);
    		spinner_finalMinuto.setDisable(true);
    		spinner_inicialHora.setDisable(true);
    		spinner_inicialMinuto.setDisable(true);
    		
    		datepicker_fechaFinal.setDisable(true);
    		datepicker_fechaInicial.setDisable(true);
    		
    		pane_infoPorDia.setVisible(true);
    		
    	}
    	activarGuardar(null);
    }
    
    public void checkDiaSemana(ActionEvent event) {
    	CheckBox checkboxEvent = null;
    	try {
    		checkboxEvent = (CheckBox) event.getSource();
    	}
    	catch(NullPointerException e) {
    	}
    	if(checkboxEvent.equals(checkbox_lunes)) {
    		checkbox_martes.setSelected(false);
    		checkbox_miercoles.setSelected(false);
    		checkbox_jueves.setSelected(false);
    		checkbox_viernes.setSelected(false);
    		checkbox_sabado.setSelected(false);
    		checkbox_domingo.setSelected(false);
    	}
    	else if(checkboxEvent.equals(checkbox_martes)) {
    		checkbox_lunes.setSelected(false);
    		checkbox_miercoles.setSelected(false);
    		checkbox_jueves.setSelected(false);
    		checkbox_viernes.setSelected(false);
    		checkbox_sabado.setSelected(false);
    		checkbox_domingo.setSelected(false);
    	}
    	else if(checkboxEvent.equals(checkbox_miercoles)) {
    		checkbox_lunes.setSelected(false);
    		checkbox_martes.setSelected(false);
    		checkbox_jueves.setSelected(false);
    		checkbox_viernes.setSelected(false);
    		checkbox_sabado.setSelected(false);
    		checkbox_domingo.setSelected(false);
    	}
    	else if(checkboxEvent.equals(checkbox_jueves)) {
    		checkbox_lunes.setSelected(false);
    		checkbox_miercoles.setSelected(false);
    		checkbox_martes.setSelected(false);
    		checkbox_viernes.setSelected(false);
    		checkbox_sabado.setSelected(false);
    		checkbox_domingo.setSelected(false);
    	}
    	else if(checkboxEvent.equals(checkbox_viernes)) {
    		checkbox_lunes.setSelected(false);
    		checkbox_miercoles.setSelected(false);
    		checkbox_jueves.setSelected(false);
    		checkbox_martes.setSelected(false);
    		checkbox_sabado.setSelected(false);
    		checkbox_domingo.setSelected(false);
    	}
    	else if(checkboxEvent.equals(checkbox_sabado)) {
    		checkbox_lunes.setSelected(false);
    		checkbox_miercoles.setSelected(false);
    		checkbox_jueves.setSelected(false);
    		checkbox_viernes.setSelected(false);
    		checkbox_martes.setSelected(false);
    		checkbox_domingo.setSelected(false);
    	}
    	else if(checkboxEvent.equals(checkbox_domingo)) {
    		checkbox_lunes.setSelected(false);
    		checkbox_miercoles.setSelected(false);
    		checkbox_jueves.setSelected(false);
    		checkbox_viernes.setSelected(false);
    		checkbox_sabado.setSelected(false);
    		checkbox_martes.setSelected(false);
    	}
    	activarGuardar(null);
    }
    
    public void sendPromocion(ActionEvent event) {
    	String item = listview_promocionListado.getSelectionModel().getSelectedItem();
    	if(item != null) {
    		listview_promocionListado.getItems().remove(item);
    		listview_promocionSeleccionados.getItems().add(item);
    		listview_promocionListado.getSelectionModel().clearSelection();
    		activarGuardar(null);
    	}
    }
    
    public void returnPromocion(ActionEvent event) {
    	String item = listview_promocionSeleccionados.getSelectionModel().getSelectedItem();
    	if(item != null) {
    		listview_promocionListado.getItems().add(item);
    		listview_promocionSeleccionados.getItems().remove(item);
    		listview_promocionSeleccionados.getSelectionModel().clearSelection();
    		activarGuardar(null);
    	}
    }
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillProductos(null);
		
		setSpinners();
		
		setDatePickers();
		
		radiobutton_porFecha.setSelected(true);
		checkDiaFecha(null);
		
		button_guardarPromocion.setDisable(true);
		
		//checkbox_lunes.setSelected(true);
		
		Image derecha_promo = new Image(getClass().getResourceAsStream("images/misc/derecha.png"));
	    ImageInput derecha_promo1 = new ImageInput();
	    derecha_promo1.setSource(derecha_promo);
		button_promocionSend.setEffect(derecha_promo1);
		
		Image izquierda_promo = new Image(getClass().getResourceAsStream("images/misc/izquierda.png"));
	    ImageInput izquierda_promo1 = new ImageInput();
	    izquierda_promo1.setSource(izquierda_promo);
		button_promocionReturn.setEffect(izquierda_promo1);
		
	}
	
	//Rellena el listview con todos los productos disponibles
	public void fillProductos(ArrayList<Producto> producto) {
		ObservableList<String> data = FXCollections.observableArrayList();
		if(producto == null) {
			for(Producto p : Controladora.getInstance().getMisProductos()) {
				if(!p.isBorrado()) {
					if(p.getUnidadMedida() == null) {
						data.add(p.getNombre() + ": " + p.getPrecio() + "$");
					}
					else {
						data.add(p.getNombre() + ": " + p.getPrecio() + "$ (" + p.getUnidadMedida().getNombre() + ")");
					}
				}
			}
		}
		else {
			for(Producto p : producto) {
				if(!p.isBorrado()) {
					if(p.getUnidadMedida() == null) {
						data.add(p.getNombre() + ": " + p.getPrecio() + "$");
					}
					else {
						data.add(p.getNombre() + ": " + p.getPrecio() + "$ (" + p.getUnidadMedida().getNombre() + ")");
					}
				}
			}
		}
		listview_promocionListado.setItems(data);
		listview_promocionListado.refresh();
	}
	
	//Rellena el listview con todos los rubros disponibles
	public void fillRubros(ArrayList<Rubro> rubro) {
		ObservableList<String> data = FXCollections.observableArrayList();
		if(rubro == null) {
			for(Rubro r : Controladora.getInstance().getMisRubros()) {
				if(!r.isBorrado()) {
					data.add(r.getNombreRubro());
				}
			}
		}
		else {
			for(Rubro r : rubro) {
				if(!r.isBorrado()) {
					data.add(r.getNombreRubro());
				}
			}
		}
		listview_promocionListado.setItems(data);
		listview_promocionListado.refresh();
	}
	
	//Asigna los valores de los spinners para que esten acorde al formato de hora
	public void setSpinners() {
		SpinnerValueFactory<Integer> valueFactoryHourInicial = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12);
		SpinnerValueFactory<Integer> valueFactoryMinuteInicial = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0);
		SpinnerValueFactory<Integer> valueFactoryHourFinal = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 12);
		SpinnerValueFactory<Integer> valueFactoryMinuteFinal = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0);
		spinner_inicialHora.setValueFactory(valueFactoryHourInicial);;
		spinner_inicialMinuto.setValueFactory(valueFactoryMinuteInicial);
		spinner_finalHora.setValueFactory(valueFactoryHourFinal);
		spinner_finalMinuto.setValueFactory(valueFactoryMinuteFinal);
	}
	
	//Evita que los datepickers tengan fechas que ya pasaron
	public void setDatePickers() {
		datepicker_fechaInicial.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		datepicker_fechaFinal.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
	}

}
