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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerNuevaFactura implements Initializable{
        @FXML private TextField textfield_buscarClienteFactura;
	    @FXML private Button button_buscarClienteFactura;
	    @FXML private CheckBox checkbox_clienteFactura;
	    @FXML private TitledPane titledpane_busquedaClientesFactura;

	public void reload(Stage stage) {
    	
   		try {
        	Stage primaryStage = new Stage();
        	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		 
		    Parent root = f.load();
		    Controller c = f.getController();
		    c.ventas_pressed(null);
		    c.selectTabFacturas();
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
	
    public void habilitarClienteFactura(ActionEvent event) {
    	if(checkbox_clienteFactura.isSelected()) {
    	textfield_buscarClienteFactura.setDisable(false);
    	button_buscarClienteFactura.setDisable(false);
    	}else {
        	textfield_buscarClienteFactura.setDisable(true);
        	button_buscarClienteFactura.setDisable(true);
    	}
    }
    
    //Cierra la ventana
    public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
    	reload(stage);
    }
    
    public void cerrarBusquedaCliente(ActionEvent event) {
    	titledpane_busquedaClientesFactura.setVisible(false);
    }
    
    public void abrirBusquedaCliente(ActionEvent event) {
    	titledpane_busquedaClientesFactura.setVisible(true);
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
