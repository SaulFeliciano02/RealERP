package visual;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import logico.Cliente;
import logico.Controladora;

public class ControllerNuevoCliente implements Initializable {
	
	/**VARIABLES PARA LA CREACION DE UN CLIENTE**/
	@FXML private Pane nuevoClientePane;
	@FXML private Button button_clienteCancel;
	@FXML private Button button_clienteGuardar;
	@FXML private TextField textfield_codigoCliente;
	@FXML private TextField textfield_nombreCliente;
	@FXML private TextField textfield_telefonoCliente;
	@FXML private TextField textfield_tipoCliente;
	@FXML private TextField textfield_rncCliente;
	@FXML private DatePicker datepicker_cumpleCliente;
/**FUNCIONES AGREGAR CLIENTE**/
	
	//Funcion abre y cierra la ventana principal para poder actualizar los datos
	public void reload(Stage stage) {
    	
   		try {
        	Stage primaryStage = new Stage();
        	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		 
		    Parent root = f.load();
		    Controller c = f.getController();
		    c.ventas_pressed(null);
		    c.selectTabCliente();
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
	
	//Funcion para determinar si el caracter presionado sobre un textfield es un numero
	public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	else if(event.getSource().equals(textfield_telefonoCliente)) {
    		clienteActivarGuardar(event);
    	}
    }
    
    //Cierra la ventana
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
    	reload(stage);
    }
	
    //Función que determina si es posible guardar un cliente.
	public void clienteActivarGuardar(KeyEvent event) {
		if(textfield_codigoCliente.getLength() > 0 && textfield_nombreCliente.getLength() > 0 && textfield_telefonoCliente.getLength() > 0 && 
				textfield_rncCliente.getLength() > 0 && datepicker_cumpleCliente.getValue() != null) {
			button_clienteGuardar.setDisable(false);
		}
		else {
			button_clienteGuardar.setDisable(true);
		}
	}
	
	//Un datepicker no funciona de la misma manera que un textfield, entonces para determinar si un datepicker es diferente de null
	//se tiene que utilizar una funcion diferente.
	public void clienteActivarGuardarDatePicker(ActionEvent event) {
		if(textfield_codigoCliente.getLength() > 0 && textfield_nombreCliente.getLength() > 0 && textfield_telefonoCliente.getLength() > 0 && 
				textfield_rncCliente.getLength() > 0 && datepicker_cumpleCliente.getValue() != null) {
			button_clienteGuardar.setDisable(false);
		}
		else {
			button_clienteGuardar.setDisable(true);
		}
	}
	
	/**AQUI ESTA EL ASUNTO DE LAS TABLEVIEW**/
	public void guardarCliente(ActionEvent event) {
		Alert success = new Alert(AlertType.INFORMATION, "Los datos han sido guardados exitosamente.");
		success.showAndWait();
		Cliente cliente = new Cliente(textfield_codigoCliente.getText(), textfield_nombreCliente.getText(), textfield_telefonoCliente.getText(), textfield_tipoCliente.getText(),
				(java.sql.Date.valueOf(datepicker_cumpleCliente.getValue())), textfield_rncCliente.getText());
		textfield_codigoCliente.setText("");
		textfield_nombreCliente.setText("");
		textfield_telefonoCliente.setText("");
		textfield_tipoCliente.setText("");
		textfield_rncCliente.setText("");
		datepicker_cumpleCliente.setValue(null);
		Controladora.getInstance().addCliente(cliente);
	}

	@Override
    public void initialize(URL location, ResourceBundle resources) {
    }
	
	public void modifyOpen(Cliente cliente) {
		textfield_codigoCliente.setText(cliente.getCodigo());
		textfield_nombreCliente.setText(cliente.getNombre());
		textfield_telefonoCliente.setText(cliente.getTelefono());
		textfield_rncCliente.setText(cliente.getRnc());
		textfield_tipoCliente.setText(cliente.getTipoCliente());
		//Transforma una variable tipo Date a LocalDate.
		datepicker_cumpleCliente.setValue(cliente.getCumpleanos().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
}
