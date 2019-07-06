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
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ControllerNuevaPromocion implements Initializable{


    @FXML private RadioButton radiobutton_producto;
    @FXML private RadioButton radiobutton_rubro;
    @FXML private AnchorPane pane_PromocionProductos;
    @FXML private AnchorPane pane_PromocionRubro;
    
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


    public void cambiodePane(ActionEvent event){
    	if(radiobutton_rubro.isSelected()) {
    		pane_PromocionRubro.setVisible(true);
    		pane_PromocionProductos.setVisible(false);
    	}
    	if(radiobutton_producto.isSelected()) {
    		pane_PromocionRubro.setVisible(false);
    		pane_PromocionProductos.setVisible(true);
    	}
    }
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
