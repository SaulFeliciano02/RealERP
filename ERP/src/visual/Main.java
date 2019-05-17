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
	
	//ImageView menu_bckgrnd = new ImageView("../images/background/Menu.png");
	
	@Override
	public void start(Stage primaryStage){
		try {
			
			FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
			//FXMLLoader f = new FXMLLoader(getClass().getResource("nuevoProducto.fxml"));
		 
		    Parent root = f.load();
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.sizeToScene();
		    primaryStage.setTitle("Centro Pymes");
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
		    primaryStage.setMaximized(true);
		    
		  //  menu_bckgrnd.fitWidthProperty().bind(primaryStage.widthProperty()); 
		    
		    primaryStage.show();

		    
		 /*   sc.heightProperty().addListener(new ChangeListener() {
		    		@Override
		    		public void changed (ObservableValue arg0, Object arg1, Object arg2) {
		    			double height = (double) arg2;
		    			//button.setPreHeight(height/2);
		    		}
		    }); */
		    			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}


