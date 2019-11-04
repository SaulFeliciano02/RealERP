package visual;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;

import basededatos.BootstrapServices;
import basededatos.Conexion;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import logico.Controladora;
import logico.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Main extends Application{
	
	@FXML private Button button_acceder;
	@FXML private Button button_salir;
	@FXML private PasswordField passwordfield_login;
	@FXML private Label message;
	@FXML private ProgressBar loading_progress;
	@FXML private TextField textfield_usuario;
	
	
	@Override
	public void start(Stage primaryStage){
		try {
			new BootstrapServices().crearTablas();
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
	
	public void access_clicked(ActionEvent event) throws IOException, InterruptedException {
		//Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
		//TimeUnit.SECONDS.sleep(2);
		String user = textfield_usuario.getText();
		String passwordHash = DigestUtils.md5Hex(passwordfield_login.getText());
		
		if (passwordfield_login.getText().equals("") && !Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText())) {
			message.setText("¡Tu usuario o contraseña es incorrecta!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
			
		} 
		
		else if(((passwordfield_login.getText().equalsIgnoreCase("root") && textfield_usuario.getText().equalsIgnoreCase("root")) && !Controladora.getInstance().activarLoadUsuarios()) ||
				Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()))
		{
			System.out.println("wawawa");
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
					if(!Controladora.getInstance().activarUnidadMedidaMasa()) {
						Controladora.getInstance().sendMasaIntoDatabase();
					}
					if(!Controladora.getInstance().activarUnidadMedidaLongitud()) {
						Controladora.getInstance().sendLongitudIntoDatabase();
					}
					if(!Controladora.getInstance().activarUnidadMedidaArea()) {
						Controladora.getInstance().sendAreaIntoDatabase();
					}
					if(!Controladora.getInstance().activarUnidadMedidaVolumen()) {
						Controladora.getInstance().sendVolumenIntoDatabase();
					}
					//Controladora.getInstance().sendUnidadesIntoDatabase();
					//Controladora.getInstance().loadProductos();
					if(Controladora.getInstance().activarLoadInfoEmpresa())
					{
						Controladora.getInstance().loadInfoEmpresa();
					}
					if(Controladora.getInstance().activarLoadCliente())
					{
						Controladora.getInstance().loadCliente();
					}
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
					if(Controladora.getInstance().activarLoadUsuarios())
					{
						Controladora.getInstance().loadUsuarios();
					}
					Controladora.getInstance().loadFactura();
					Controladora.getInstance().loadPromocion();
					if(Controladora.getInstance().activarLoadImagenProducto())
					{
						Controladora.getInstance().loadImagenProducto();
					}
					if(Controladora.getInstance().activarLoadFacturaCreditoClienteSQL())
					{
						Controladora.getInstance().loadFacturaCreditoCliente();
					}
					if(Controladora.getInstance().activarLoadPeticiones())
					{
						Controladora.getInstance().loadPeticiones();
					}
					if(Controladora.getInstance().activarLoadPeticionesCredito())
					{
						Controladora.getInstance().loadPeticionesCredito();
					}
					if(Controladora.getInstance().activarLoadPagoPeticionesCredito())
					{
						Controladora.getInstance().loadPagoPeticionesCredito();
					}
					if(Controladora.getInstance().activarLoadCajaChica())
					{
						Controladora.getInstance().loadCajaChica();
					}
					if(Controladora.getInstance().activarLoadCuentaBancaria())
					{
						Controladora.getInstance().loadCuentaBancaria();
					}
					if(textfield_usuario.getText().equalsIgnoreCase("root")) {
						Usuario userRoot = new Usuario("root", null, true, "root", true, null);
						Controladora.getInstance().setUsuarioLogueado(userRoot);
					}
					else {
						Controladora.getInstance().setUsuarioLogueado(Controladora.getInstance().buscarUsuario(user, passwordHash));
					}
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
						Parent root1;
						root1 = (Parent) fxmlLoader.load();
						Stage stage = new Stage();
						stage.setTitle("Centro Pymes");
						stage.setScene(new Scene(root1)); 
						stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
						stage.setMaximized(true);
						stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						      public void handle(WindowEvent we) {
						    	  try {
						    		  Runtime.getRuntime().exec("taskkill /F /PID 827");
						    		  Runtime.getRuntime().exec("taskkill /F /PID 827");
						    		  Runtime.getRuntime().exec("taskkill /F /PID 827");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						  }});

						stage.showAndWait();
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		else if(textfield_usuario.getText().equalsIgnoreCase("root") && Controladora.getInstance().activarLoadUsuarios())
		{
			message.setText("¡Este usuario ya no está disponible!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
		}
		
		passwordfield_login.clear();
		
	}
	
	@FXML
	public void onEnter(ActionEvent event) throws IOException, InterruptedException{
		//Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
		//TimeUnit.SECONDS.sleep(2);
		String user = textfield_usuario.getText();
		String passwordHash = DigestUtils.md5Hex(passwordfield_login.getText());
		
		if (passwordfield_login.getText().equals("") && !Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText())) {
			message.setText("¡Tu usuario o contraseña es incorrecta!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
			
		} 
		
		else if(((passwordfield_login.getText().equalsIgnoreCase("root") && textfield_usuario.getText().equalsIgnoreCase("root")) && !Controladora.getInstance().activarLoadUsuarios()) ||
				Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()))
		{
			System.out.println("wawawa");
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
					if(!Controladora.getInstance().activarUnidadMedidaMasa()) {
						Controladora.getInstance().sendMasaIntoDatabase();
					}
					if(!Controladora.getInstance().activarUnidadMedidaLongitud()) {
						Controladora.getInstance().sendLongitudIntoDatabase();
					}
					if(!Controladora.getInstance().activarUnidadMedidaArea()) {
						Controladora.getInstance().sendAreaIntoDatabase();
					}
					if(!Controladora.getInstance().activarUnidadMedidaVolumen()) {
						Controladora.getInstance().sendVolumenIntoDatabase();
					}
					//Controladora.getInstance().sendUnidadesIntoDatabase();
					//Controladora.getInstance().loadProductos();
					if(Controladora.getInstance().activarLoadInfoEmpresa())
					{
						Controladora.getInstance().loadInfoEmpresa();
					}
					if(Controladora.getInstance().activarLoadCliente())
					{
						Controladora.getInstance().loadCliente();
					}
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
					if(Controladora.getInstance().activarLoadUsuarios())
					{
						Controladora.getInstance().loadUsuarios();
					}
					Controladora.getInstance().loadFactura();
					Controladora.getInstance().loadPromocion();
					if(Controladora.getInstance().activarLoadImagenProducto())
					{
						Controladora.getInstance().loadImagenProducto();
					}
					if(Controladora.getInstance().activarLoadFacturaCreditoClienteSQL())
					{
						Controladora.getInstance().loadFacturaCreditoCliente();
					}
					if(Controladora.getInstance().activarLoadPeticiones())
					{
						Controladora.getInstance().loadPeticiones();
					}
					if(Controladora.getInstance().activarLoadPeticionesCredito())
					{
						Controladora.getInstance().loadPeticionesCredito();
					}
					if(Controladora.getInstance().activarLoadPagoPeticionesCredito())
					{
						Controladora.getInstance().loadPagoPeticionesCredito();
					}
					if(Controladora.getInstance().activarLoadCajaChica())
					{
						Controladora.getInstance().loadCajaChica();
					}
					if(Controladora.getInstance().activarLoadCuentaBancaria())
					{
						Controladora.getInstance().loadCuentaBancaria();
					}
					if(textfield_usuario.getText().equalsIgnoreCase("root")) {
						Usuario userRoot = new Usuario("root", null, true, "root", true, null);
						Controladora.getInstance().setUsuarioLogueado(userRoot);
					}
					else {
						Controladora.getInstance().setUsuarioLogueado(Controladora.getInstance().buscarUsuario(user, passwordHash));
					}
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

		else if(textfield_usuario.getText().equalsIgnoreCase("root") && Controladora.getInstance().activarLoadUsuarios())
		{
			message.setText("¡Este usuario ya no está disponible!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
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


