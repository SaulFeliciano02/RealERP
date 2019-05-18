package visual;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logico.Controladora;
import logico.Proveedores;
import logico.Rubro;

public class ControllerNuevoProveedor {
	
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
        stage.close();
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
		Rubro rubro = null;
		Proveedores proveedor = new Proveedores(textfield_codigoProveedor.getText(), textfield_nombreProveedor.getText(), textfield_telefonoProveedor.getText(),
				textarea_direccionProveedor.getText(), textfield_correoElectronicoProveedor.getText(), textfield_rncProveedor.getText(),
				rubro, textfield_sitioWebProveedor.getText());
		Controladora.getInstance().addProveedor(proveedor);
	}
	

}
