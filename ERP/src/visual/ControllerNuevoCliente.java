package visual;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
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
	
	//Cierra y vuelve a abrir la ventana principal.
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
	
	//Verifica si el input de un textfield es un número.
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
	
    //Determina si los parámetros de un cliente están completos.
	public void clienteActivarGuardar(KeyEvent event) {
		//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validación de los parámetros, si se te es posible estandarizarlo, recomendamos hacerlo.
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
	
	//Guarda un nuevo cliente
	public void guardarCliente(ActionEvent event) {
		
		Cliente cliente = new Cliente(textfield_codigoCliente.getText(), textfield_nombreCliente.getText(), textfield_telefonoCliente.getText(), textfield_tipoCliente.getText(),
				(java.sql.Date.valueOf(datepicker_cumpleCliente.getValue())), textfield_rncCliente.getText());
		if(Controladora.getInstance().clienteCodigoExists(cliente)) {
			Alert alert = new Alert(AlertType.WARNING, "El código ya está en uso");
			alert.showAndWait();
		}
		else if(Controladora.getInstance().clienteRNCExists(cliente)) {
			Alert alert = new Alert(AlertType.WARNING, "El RNC ya está en uso");
			alert.showAndWait();
		}
		else {
			Alert success = new Alert(AlertType.INFORMATION, "Los datos han sido guardados exitosamente.");
			success.showAndWait();
			textfield_codigoCliente.setText("");
			textfield_nombreCliente.setText("");
			textfield_telefonoCliente.setText("");
			textfield_tipoCliente.setText("");
			textfield_rncCliente.setText("");
			datepicker_cumpleCliente.setValue(null);
			Controladora.getInstance().addCliente(cliente);
		}
		
	}

	@Override
    public void initialize(URL location, ResourceBundle resources) {
    }
	
	
	//Íbamos a implementar una modificación al cliente (y probablemente al proveedor y empleado también)
	//como lo hicimos con el producto, pero nos quedamos sin tiempo.
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
