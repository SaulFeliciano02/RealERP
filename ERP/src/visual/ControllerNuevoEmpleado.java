package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Controladora;
import logico.Empleado;
import logico.Proveedores;
import logico.Rubro;

public class ControllerNuevoEmpleado implements Initializable{

	@FXML private TextField textfield_empleadoCodigo;
	@FXML private TextField textfield_empleadoNombre;
	@FXML private TextField textfield_empleadoTelefono;
	@FXML private TextField textfield_empleadoTelefonoAdicional;
	@FXML private TextArea textarea_empleadoDomicilio;
	@FXML private TextField textfield_empleadoSueldo;
	@FXML private TextField textfield_empleadoRNC;
	@FXML private TextField textfield_empleadoCorreo;
	@FXML private RadioButton radiobutton_empleadoDia;
	@FXML private RadioButton radiobutton_empleadoHora;
	@FXML private Button button_empleadoGuardar;
	@FXML private Button button_empleadoCancelar;
	@FXML private ComboBox<String> combobox_empleadoTipo;
	
	public void reload(Stage stage) {
    	
   		try {
        	Stage primaryStage = new Stage();
        	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		    Parent root = f.load();
		    Controller c = f.getController();
		    c.rh_pressed(null);
		    c.selectTabEmpleado();
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.sizeToScene();
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
	
    public void numericFieldPressed(KeyEvent event) {
    	TextField source = (TextField) event.getSource();
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	else if(source.equals(textfield_empleadoTelefono)) {
    		empleadoActivarGuardar(event);
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
    	if(source.equals(textfield_empleadoSueldo)){
    		empleadoActivarGuardar(event);
    	}
	 }
	
	
	public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
	   	reload(stage);
	}
	

	public void empleadoActivarGuardar(KeyEvent event) {
		if(textfield_empleadoCodigo.getLength() > 0 && textfield_empleadoNombre.getLength() > 0 && textfield_empleadoRNC.getLength() > 0
				&& textfield_empleadoTelefono.getLength() > 0) {
			button_empleadoGuardar.setDisable(false);
		}
		else {
			button_empleadoGuardar.setDisable(true);
		}
	}
	
	public void guardarEmpleado(ActionEvent Event) {
		boolean isEmpty = false;
		boolean validRegister = true;
		Alert a = new Alert(AlertType.NONE); 
		a.setAlertType(AlertType.ERROR);
		String codigo = textfield_empleadoCodigo.getText();
		String nombre = textfield_empleadoNombre.getText();
		String telefono = textfield_empleadoTelefono.getText();
		String rnc = textfield_empleadoRNC.getText();
		float saldo = Float.parseFloat(textfield_empleadoSueldo.getText());
		String tipo = combobox_empleadoTipo.getSelectionModel().getSelectedItem();
		String direccion = "";
		String correo = "";
		try {
			direccion = textarea_empleadoDomicilio.getText();
			correo = textfield_empleadoCorreo.getText();
		}catch(NullPointerException e) {
			isEmpty = true;
		}
		for(Empleado e: Controladora.getInstance().getMisEmpleados()) {
			if(e.getCodigo().equalsIgnoreCase(codigo)) {
				a.setContentText("Este codigo ya esta registrado");
				a.show();
				validRegister = false;
			}
			else if(e.getRnc().equalsIgnoreCase(rnc)) {
				a.setContentText("Este rnc ya esta registrado");
				a.show();
				validRegister = false;
			}
		}
		if(saldo == 0) {
			a.setContentText("Asignele un sueldo al empleado.");
			a.show();
		}
		if(validRegister) {
			Empleado empleado = new Empleado(codigo, nombre, telefono, direccion, correo, rnc, tipo, saldo);
			Controladora.getInstance().addEmpleado(empleado);
			textfield_empleadoCodigo.setText("");
			textfield_empleadoNombre.setText("");
			textfield_empleadoTelefono.setText("");
			textfield_empleadoSueldo.setText("");
			textfield_empleadoRNC.setText("");
			combobox_empleadoTipo.getSelectionModel().select("Administrativo");
			if(!isEmpty) {
				textarea_empleadoDomicilio.setText("");
				textfield_empleadoCorreo.setText("");
				textfield_empleadoTelefonoAdicional.setText("");
			}
			button_empleadoGuardar.setDisable(true);
		}
	}
	  
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> combobox_data = FXCollections.observableArrayList();
		combobox_data.addAll("Administrativo", "Vendedor", "Prestador de servicios");
		combobox_empleadoTipo.setItems(combobox_data);
		combobox_empleadoTipo.getSelectionModel().select("Administrativo");
	}

}
