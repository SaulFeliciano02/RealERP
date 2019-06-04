package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Controladora;
import logico.Proveedores;
import logico.Rubro;

public class ControllerNuevoProveedor implements Initializable{
	
	/**VARIABLE PARA LA CREACION DE UN PROVEEDOR**/
	@FXML private Button button_proveedorCancel;
	@FXML private Button button_proveedorGuardar;
	@FXML private TextField textfield_codigoProveedor;
	@FXML private TextField textfield_nombreProveedor;
	@FXML private TextField textfield_telefonoProveedor;
	@FXML private TextArea textarea_direccionProveedor;
	@FXML private TextField textfield_rncProveedor;
	@FXML private TextField textfield_correoElectronicoProveedor;
	@FXML private TextField textfield_sitioWebProveedor;
	
	/**FUNCIONES AGREGAR PROVEEDOR**/
	
	public void reload(Stage stage) {
    	Window owner = stage.getOwner();
		
   		try {
        	Stage primaryStage = new Stage();
        	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		    Parent root = f.load();
		    Controller c = f.getController();
		    c.rh_pressed(null);
		    c.selectTabProveedor();
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.sizeToScene();
		    primaryStage.setTitle("Centro Pymes");
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
		    primaryStage.setMaximized(true);

		    primaryStage.show();
		    owner.hide();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
    public void numericFieldPressed(KeyEvent event) {
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	else if(event.getSource().equals(textfield_telefonoProveedor)) {
    		proveedorActivarGuardar(event);
    	}
    }
    
    //Cierra la venta de nuevoProducto
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
    	reload(stage);
    }
	
	public void proveedorActivarGuardar(KeyEvent event) {
		if(textfield_codigoProveedor.getLength() > 0 && textfield_nombreProveedor.getLength() > 0 && textfield_rncProveedor.getLength() > 0
				&& textfield_telefonoProveedor.getLength() > 0) {
			button_proveedorGuardar.setDisable(false);
		}
		else {
			button_proveedorGuardar.setDisable(true);
		}
	}
	
	public void guardarProveedor(ActionEvent Event) {
		boolean isEmpty = false;
		boolean validRegister = true;
		Alert a = new Alert(AlertType.NONE); 
		Rubro rubro = null;
		String codigo = textfield_codigoProveedor.getText();
		String nombre = textfield_nombreProveedor.getText();
		String telefono = textfield_telefonoProveedor.getText();
		String rnc = textfield_rncProveedor.getText();
		String direccion = "";
		String correo = "";
		String sitioweb = "";
		try {
			direccion = textarea_direccionProveedor.getText();
			correo = textfield_correoElectronicoProveedor.getText();
			sitioweb = textfield_sitioWebProveedor.getText();
		}catch(NullPointerException e) {
			isEmpty = true;
		}
		for(Proveedores p: Controladora.getInstance().getMisProveedores()) {
			if(p.getCodigo().equalsIgnoreCase(codigo)) {
				a.setAlertType(AlertType.ERROR);
				a.setContentText("Este codigo ya esta registrado");
				a.show();
				validRegister = false;
			}
			else if(p.getRnc().equalsIgnoreCase(rnc)) {
				a.setAlertType(AlertType.ERROR);
				a.setContentText("Este rnc ya esta registrado");
				a.show();
				validRegister = false;
			}
		}
		if(validRegister) {
			Proveedores proveedor = new Proveedores(codigo, nombre, telefono, direccion, correo, rnc, rubro, sitioweb);
			Controladora.getInstance().addProveedor(proveedor);
			textfield_codigoProveedor.setText("");
			textfield_nombreProveedor.setText("");
			textfield_telefonoProveedor.setText("");
			textfield_rncProveedor.setText("");
			if(!isEmpty) {
				textarea_direccionProveedor.setText("");
				textfield_correoElectronicoProveedor.setText("");
				textfield_sitioWebProveedor.setText("");
			}
			button_proveedorGuardar.setDisable(true);
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
