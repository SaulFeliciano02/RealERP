package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Cliente;
import logico.Controladora;

public class ControllerNuevoCliente implements Initializable {
	
	/**VARIABLES PARA LA CREACION DE UN CLIENTE**/
	@FXML private Button button_clienteCancel;
	@FXML private Button button_clienteGuardar;
	@FXML private TextField textfield_codigoCliente;
	@FXML private TextField textfield_nombreCliente;
	@FXML private TextField textfield_telefonoCliente;
	@FXML private TextField textfield_tipoCliente;
	@FXML private TextField textfield_rncCliente;
	@FXML private DatePicker datepicker_cumpleCliente;
/**FUNCIONES AGREGAR CLIENTE**/
	
	public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	else if(event.getSource().equals(textfield_telefonoCliente)) {
    		clienteActivarGuardar(event);
    	}
    }
    
    //Cierra la venta de nuevoProducto
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
	
	public void clienteActivarGuardar(KeyEvent event) {
		if(textfield_codigoCliente.getLength() > 0 && textfield_nombreCliente.getLength() > 0 && textfield_telefonoCliente.getLength() > 0 && 
				textfield_rncCliente.getLength() > 0 && datepicker_cumpleCliente.getValue() != null) {
			button_clienteGuardar.setDisable(false);
		}
		else {
			button_clienteGuardar.setDisable(true);
		}
	}
	
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
		cancelCreation(event);
		Cliente cliente = new Cliente(textfield_codigoCliente.getText(), textfield_nombreCliente.getText(), textfield_telefonoCliente.getText(), textfield_tipoCliente.getText(),
				 datepicker_cumpleCliente.getValue(), textfield_rncCliente.getText());
		Controladora.getInstance().addCliente(cliente);
		
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

	@Override
    public void initialize(URL location, ResourceBundle resources) {
    }
	
}
