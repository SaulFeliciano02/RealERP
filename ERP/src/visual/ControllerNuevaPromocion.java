package visual;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Controladora;
import logico.Producto;
import logico.Rubro;

public class ControllerNuevaPromocion implements Initializable{


    @FXML private RadioButton radiobutton_producto;
    @FXML private RadioButton radiobutton_rubro;
    @FXML private AnchorPane pane_PromocionProductos;
    @FXML private AnchorPane pane_PromocionRubro;
    
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
    
    public void checkDiaFecha(ActionEvent event) {
    	RadioButton radioEvent = null;
    	try {
    		radioEvent = (RadioButton) event.getSource();
    	}
    	catch(NullPointerException e) {
    		
    	}
    	
    	if(radiobutton_porFecha.equals(radioEvent) || event == null) {
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
    		
    		radiobutton_porFecha.setSelected(true);
    		radiobutton_porDia.setSelected(false);
    	}
    	else {
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
    		
    		radiobutton_porFecha.setSelected(false);
    		radiobutton_porDia.setSelected(true);
    	}
    }
    
    public void sendPromocion(ActionEvent event) {
    	String item = listview_promocionListado.getSelectionModel().getSelectedItem();
    	listview_promocionListado.getItems().remove(item);
    	listview_promocionSeleccionados.getItems().add(item);
    	listview_promocionListado.getSelectionModel().clearSelection();
    	
    }
    
    public void returnPromocion(ActionEvent event) {
    	String item = listview_promocionSeleccionados.getSelectionModel().getSelectedItem();
    	listview_promocionListado.getItems().add(item);
    	listview_promocionListado.getItems().remove(item);
    	listview_promocionSeleccionados.getSelectionModel().clearSelection();
    }
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillProductos(null);
		
		setSpinners();
		
		radiobutton_porFecha.setSelected(true);
		checkDiaFecha(null);
		
		
	}
	
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
		SortedList<String> sortedList = new SortedList<String>(data);
		listview_promocionListado.setItems(data);
		listview_promocionListado.refresh();
	}
	
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

}
