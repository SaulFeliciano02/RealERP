package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import logico.Controladora;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
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
	
	@FXML private Button button_acceder;
	@FXML private Button button_salir;
	@FXML private PasswordField passwordfield_login;
	@FXML private Label message;
	@FXML private ProgressBar loading_progress;
	
	@Override
	public void start(Stage primaryStage){
		try {

			FXMLLoader f = new FXMLLoader(getClass().getResource("Login.fxml"));
		 
		    Parent root = f.load();
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.setTitle("Centro Pymes");
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
		    primaryStage.setMaximized(false);
		    primaryStage.setResizable(false);
		    
		    
		    primaryStage.show();

		    			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void access_clicked(ActionEvent event) {
		
		float count=0;
		if (!passwordfield_login.getText().equals("")) {
			message.setText("¡Tu contraseña es incorrecta!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
			
		} else {
			
	    	/**ABRIENDO viewPrincipal.fxml**/
			message.setText("Tu contraseña ha sido confirmada");
			message.setTextFill(Color.rgb(21, 117, 84));
			
			loading_progress.setVisible(true);
			
			Timeline timeline = new Timeline();

			KeyValue keyValue = new KeyValue(loading_progress.progressProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(new Duration(1000), keyValue);
			timeline.getKeyFrames().add(keyFrame);

			timeline.play();
			Stage loginStage = (Stage) button_acceder.getScene().getWindow();
			loginStage.close();
				try {
					//Controladora.getInstance().sendUnidadesIntoDatabase();
					//Controladora.getInstance().loadProductos();
					if(Controladora.getInstance().activarLoadGastosGenerales())
					{
						Controladora.getInstance().loadGastosGenerales();
					}
					if(Controladora.getInstance().activarRecuperarRubros())
					{
						Controladora.getInstance().recuperarRubros();
					}
					if(Controladora.getInstance().activarLoadProveedores())
					{
						Controladora.getInstance().loadProveedores();
					}
					if(Controladora.getInstance().activarLoadCategoriaEmpleado())
					{
						Controladora.getInstance().loadCategoriaEmpleado();
					}
					if(Controladora.getInstance().activarLoadEmpleados())
					{
						Controladora.getInstance().loadEmpleados();
					}
					if(Controladora.getInstance().activarLoadPrecio())
					{
						Controladora.getInstance().loadPrecio();
					}
					if(Controladora.getInstance().activarLoadProductos())
					{
						Controladora.getInstance().loadProductos();
					}
					if(Controladora.getInstance().activarLoadPartida())
					{
						Controladora.getInstance().loadPartida();
					}
					if(Controladora.getInstance().activarLoadGrupoAtributo())
					{
						Controladora.getInstance().loadGrupoAtributo();
					}
					if(Controladora.getInstance().activarLoadAtributos())
					{
						Controladora.getInstance().loadAtributos();
					}
					if(Controladora.getInstance().activarloadMatriz())
					{
						Controladora.getInstance().loadMatriz();
					}
					if(Controladora.getInstance().activarLoadKit())
					{
						Controladora.getInstance().loadKit();
					}
					if(Controladora.getInstance().activarLoadServicios())
					{
						Controladora.getInstance().loadServicios();
					}
					if(Controladora.getInstance().activarLoadManoDeObra())
					{
						Controladora.getInstance().loadManoDeObra();
					}
					if(Controladora.getInstance().activarLoadCostoIndirecto())
					{
						Controladora.getInstance().loadCostoIndirecto();
					}
					if(Controladora.getInstance().activarLoadPromedioGananciaAnual())
					{
						Controladora.getInstance().loadPromedioGananciaAnual();
					}
					Controladora.getInstance().loadFactura();
					Controladora.getInstance().loadPromocion();
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
						Parent root1;
						root1 = (Parent) fxmlLoader.load();
						Stage stage = new Stage();
						stage.setTitle("Centro Pymes");
						stage.setScene(new Scene(root1)); 
						stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
						stage.setMaximized(true);

						stage.showAndWait();
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}
		passwordfield_login.clear();
		
	}
		
		
	public void handleCloseButtonAction(ActionEvent event) {
	    Stage stage = (Stage) button_salir.getScene().getWindow();
	    stage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}



}


