package visual;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logico.Controladora;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Main extends Application{
	
	
	@Override
	public void start(Stage primaryStage){
		try {
			//Controladora.getInstance().sendUnidadesIntoDatabase();
			//Controladora.getInstance().loadProductos();
			Controladora.getInstance().loadGastosGenerales();
			Controladora.getInstance().recuperarRubros();
			Controladora.getInstance().loadProveedores();
			Controladora.getInstance().loadCategoriaEmpleado();
			Controladora.getInstance().loadEmpleados();
			Controladora.getInstance().loadPrecio();
			Controladora.getInstance().loadProductos();
			Controladora.getInstance().loadPartida();
			Controladora.getInstance().loadManoDeObra();
			FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		 
		    Parent root = f.load();
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.setTitle("Centro Pymes");
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
		    primaryStage.setMaximized(true);
		    
		    primaryStage.show();

		    			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}


