package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Main extends Application{
	
	@FXML private ImageView button_acceder;
	@FXML private ImageView button_salir;
	@FXML private PasswordField passwordfield_login;
	@FXML private Label message;
	@FXML private ProgressBar loading_progress;
	@FXML private TextField textfield_usuario;
	@FXML private CheckBox checkremember;
	
	String usu;
	
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
		    primaryStage.sizeToScene();
		    primaryStage.show();
		    /*Image access = new Image(getClass().getResourceAsStream("images/misc/access.png"));
		    ImageInput access1 = new ImageInput();
		    access1.setSource(access);
			button_acceder.setEffect(access1);*/
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void field_clicked(ActionEvent event) 
	{
		if(Controladora.getInstance().activarUsuarioRecordado() && checkremember.isSelected())
	    {
	    	usu = Controladora.getInstance().datosUsuarioRecordado();
	    	textfield_usuario.setText(usu.substring(0, usu.indexOf(" ")));
	    	passwordfield_login.setText(usu.substring(usu.indexOf(" ")+1, usu.length()));
	    	System.out.println(usu.substring(usu.indexOf(" ")+1, usu.length()));
	    }
		
	}
	
	public void access_clicked(MouseEvent event) throws IOException, InterruptedException {
		//Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
				//TimeUnit.SECONDS.sleep(2);
				String user = textfield_usuario.getText();
				String passwordHash = DigestUtils.md5Hex(passwordfield_login.getText());
				
				if (passwordfield_login.getText().equals("") || !Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) || (!checkremember.isSelected() && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))) {
					message.setText("¡Tu usuario o contraseña es incorrecta!");
					message.setTextFill(Color.rgb(210, 39, 30));
					
					loading_progress.setVisible(false);
					loading_progress.setProgress(0);
					
					if(!Controladora.getInstance().activarLoadUsuarios() && !passwordfield_login.getText().equalsIgnoreCase("administrador") && !textfield_usuario.getText().equalsIgnoreCase("administrador"))
					{
						Alert alert = new Alert(AlertType.INFORMATION, "Usted no posee usuarios registrados. Favor de acceder al sistema con la siguiente información: \n\nUsuario: administrador \nContraseña: administrador");
			    		alert.showAndWait();
			    		textfield_usuario.setText("administrador");
			    		passwordfield_login.setText("administrador");
					}
					//passwordfield_login.clear();
				} 
				
				if(((passwordfield_login.getText().equalsIgnoreCase("administrador") && textfield_usuario.getText().equalsIgnoreCase("administrador")) && !Controladora.getInstance().activarLoadUsuarios()) ||
						Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) || (checkremember.isSelected() && Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText())))
				{
					System.out.println("wawawa");
					/**ABRIENDO viewPrincipal.fxml**/
					message.setText("Tu contraseña ha sido confirmada");
					message.setTextFill(Color.rgb(21, 117, 84));
					
					if(Controladora.getInstance().activarUsuarioRecordado() && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
					{
						//no esta borrando bien
						Controladora.getInstance().OlvidarContrasena(textfield_usuario.getText());
						Controladora.getInstance().reiniciarUsuarioRecordado();
					}
					
					if(checkremember.isSelected() && Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
					{
						System.out.println("Entre al if de rememberme " + textfield_usuario.getText());
						Controladora.getInstance().RecordarContrasena(textfield_usuario.getText());
					}
					
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
							else
							{
								Controladora.getInstance().reiniciarInfoEmpresa();
							}
							if(Controladora.getInstance().activarLoadCliente())
							{
								Controladora.getInstance().loadCliente();
							}
							else
							{
								Controladora.getInstance().reiniciarClientes();
							}
							if(Controladora.getInstance().activarLoadGastosGenerales())
							{
								Controladora.getInstance().loadGastosGenerales();
							}
							else
							{
								Controladora.getInstance().reiniciarGastosGenerales();
							}
							if(Controladora.getInstance().activarRecuperarRubros())
							{
								Controladora.getInstance().recuperarRubros();
							}
							else
							{
								Controladora.getInstance().reiniciarRubros();
							}
							if(Controladora.getInstance().activarLoadProveedores())
							{
								Controladora.getInstance().loadProveedores();
							}
							else
							{
								Controladora.getInstance().reiniciarProveedores();
							}
							if(Controladora.getInstance().activarLoadCategoriaEmpleado())
							{
								Controladora.getInstance().loadCategoriaEmpleado();
							}
							else
							{
								Controladora.getInstance().reiniciarCategoriaEmpleado();
							}
							if(Controladora.getInstance().activarLoadEmpleados())
							{
								Controladora.getInstance().loadEmpleados();
							}
							else
							{
								Controladora.getInstance().reiniciarEmpleados();
							}
							if(Controladora.getInstance().activarLoadPrecio())
							{
								Controladora.getInstance().loadPrecio();
							}
							else
							{
								Controladora.getInstance().reiniciarPrecios();
							}
							if(Controladora.getInstance().activarLoadProductos())
							{
								Controladora.getInstance().loadProductos();
							}
							else
							{
								Controladora.getInstance().reiniciarProducto();
							}
							if(Controladora.getInstance().activarLoadPartida())
							{
								Controladora.getInstance().loadPartida();
							}
							else
							{
								Controladora.getInstance().reiniciarPartida();
							}
							if(Controladora.getInstance().activarLoadGrupoAtributo())
							{
								Controladora.getInstance().loadGrupoAtributo();
							}
							else
							{
								Controladora.getInstance().reiniciarGrupoAtributos();
							}
							if(Controladora.getInstance().activarLoadAtributos())
							{
								Controladora.getInstance().loadAtributos();
							}
							else
							{
								Controladora.getInstance().reiniciarAtributos();
							}
							if(Controladora.getInstance().activarloadMatriz())
							{
								Controladora.getInstance().loadMatriz();
							}
							else
							{
								Controladora.getInstance().reiniciarMatriz();
							}
							if(Controladora.getInstance().activarLoadKit())
							{
								Controladora.getInstance().loadKit();
							}
							else
							{
								Controladora.getInstance().reiniciarKit();
							}
							if(Controladora.getInstance().activarLoadServicios())
							{
								Controladora.getInstance().loadServicios();
							}
							else
							{
								Controladora.getInstance().reiniciarServicio();
							}
							if(Controladora.getInstance().activarLoadManoDeObra())
							{
								Controladora.getInstance().loadManoDeObra();
							}
							else
							{
								Controladora.getInstance().reiniciarManoDeObra();
							}
							if(Controladora.getInstance().activarLoadCostoIndirecto())
							{
								Controladora.getInstance().loadCostoIndirecto();
							}
							else
							{
								Controladora.getInstance().reiniciarCostoIndirecto();
							}
							if(Controladora.getInstance().activarLoadPromedioGananciaAnual())
							{
								Controladora.getInstance().loadPromedioGananciaAnual();
							}
							else
							{
								Controladora.getInstance().reiniciarPromedioGananciaAnual();
							}
							if(Controladora.getInstance().activarLoadUsuarios())
							{
								Controladora.getInstance().loadUsuarios();
							}
							else
							{
								Controladora.getInstance().reiniciarUsuarios();
							}
							Controladora.getInstance().loadFactura();
							Controladora.getInstance().loadPromocion();
							if(Controladora.getInstance().activarLoadImagenProducto())
							{
								Controladora.getInstance().loadImagenProducto();
							}
							else
							{
								Controladora.getInstance().reiniciarImagenProducto();
							}
							if(Controladora.getInstance().activarLoadFacturaCreditoClienteSQL())
							{
								Controladora.getInstance().loadFacturaCreditoCliente();
							}
							else
							{
								Controladora.getInstance().reiniciarFacturaCreditoCliente();
							}
							if(Controladora.getInstance().activarLoadPeticiones())
							{
								Controladora.getInstance().loadPeticiones();
							}
							else
							{
								Controladora.getInstance().reiniciarPeticiones();
							}
							if(Controladora.getInstance().activarLoadPeticionesCredito())
							{
								Controladora.getInstance().loadPeticionesCredito();
							}
							else
							{
								Controladora.getInstance().reiniciarPeticionesCredito();
							}
							if(Controladora.getInstance().activarLoadPagoPeticionesCredito())
							{
								Controladora.getInstance().loadPagoPeticionesCredito();
							}
							else
							{
								Controladora.getInstance().reiniciarPagoPeticionesCredito();
							}
							if(Controladora.getInstance().activarLoadCajaChica())
							{
								Controladora.getInstance().loadCajaChica();
							}
							else
							{
								Controladora.getInstance().reiniciarCajaChica();
							}
							if(Controladora.getInstance().activarLoadCuentaBancaria())
							{
								Controladora.getInstance().loadCuentaBancaria();
							}
							else
							{
								Controladora.getInstance().reiniciarCuentaBancaria();
							}
							if(textfield_usuario.getText().equalsIgnoreCase("administrador")) {
								Usuario userRoot = new Usuario("administrador", null, true, "administrador", true, null);
								Controladora.getInstance().setUsuarioLogueado(userRoot);
							}
							else {
								if(checkremember.isSelected() && Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
								{
									Controladora.getInstance().setUsuarioLogueado(Controladora.getInstance().buscarUsuario(user, passwordfield_login.getText()));
								}
								else
								{
									Controladora.getInstance().setUsuarioLogueado(Controladora.getInstance().buscarUsuario(user, passwordHash));
								}
								
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

				if(textfield_usuario.getText().equalsIgnoreCase("administrador") && Controladora.getInstance().activarLoadUsuarios())
				{
					message.setText("¡Este usuario ya no está disponible!");
					message.setTextFill(Color.rgb(210, 39, 30));
					
					loading_progress.setVisible(false);
					loading_progress.setProgress(0);
					passwordfield_login.clear();
				}
		
	}
	
	@FXML
	public void onEnter(ActionEvent event) throws IOException, InterruptedException{
		//Process process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
		//TimeUnit.SECONDS.sleep(2);
		String user = textfield_usuario.getText();
		String passwordHash = DigestUtils.md5Hex(passwordfield_login.getText());
		
		if (passwordfield_login.getText().equals("") || !Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) || (!checkremember.isSelected() && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))) {
			message.setText("¡Tu usuario o contraseña es incorrecta!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
			
			if(!Controladora.getInstance().activarLoadUsuarios() && !passwordfield_login.getText().equalsIgnoreCase("administrador") && !textfield_usuario.getText().equalsIgnoreCase("administrador"))
			{
				Alert alert = new Alert(AlertType.INFORMATION, "Usted no posee usuarios registrados. Favor de acceder al sistema con la siguiente información: \n\nUsuario: administrador \nContraseña: administrador");
	    		alert.showAndWait();
	    		textfield_usuario.setText("administrador");
	    		passwordfield_login.setText("administrador");
			}
			//passwordfield_login.clear();
		} 
		
		if(((passwordfield_login.getText().equalsIgnoreCase("administrador") && textfield_usuario.getText().equalsIgnoreCase("administrador")) && !Controladora.getInstance().activarLoadUsuarios()) ||
				Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) || (checkremember.isSelected() && Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText())))
		{
			System.out.println("wawawa");
			/**ABRIENDO viewPrincipal.fxml**/
			message.setText("Tu contraseña ha sido confirmada");
			message.setTextFill(Color.rgb(21, 117, 84));
			
			if(Controladora.getInstance().activarUsuarioRecordado() && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
			{
				//no esta borrando bien
				Controladora.getInstance().OlvidarContrasena(textfield_usuario.getText());
				Controladora.getInstance().reiniciarUsuarioRecordado();
			}
			
			if(checkremember.isSelected() && Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
			{
				System.out.println("Entre al if de rememberme " + textfield_usuario.getText());
				Controladora.getInstance().RecordarContrasena(textfield_usuario.getText());
			}
			
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
					else
					{
						Controladora.getInstance().reiniciarInfoEmpresa();
					}
					if(Controladora.getInstance().activarLoadCliente())
					{
						Controladora.getInstance().loadCliente();
					}
					else
					{
						Controladora.getInstance().reiniciarClientes();
					}
					if(Controladora.getInstance().activarLoadGastosGenerales())
					{
						Controladora.getInstance().loadGastosGenerales();
					}
					else
					{
						Controladora.getInstance().reiniciarGastosGenerales();
					}
					if(Controladora.getInstance().activarRecuperarRubros())
					{
						Controladora.getInstance().recuperarRubros();
					}
					else
					{
						Controladora.getInstance().reiniciarRubros();
					}
					if(Controladora.getInstance().activarLoadProveedores())
					{
						Controladora.getInstance().loadProveedores();
					}
					else
					{
						Controladora.getInstance().reiniciarProveedores();
					}
					if(Controladora.getInstance().activarLoadCategoriaEmpleado())
					{
						Controladora.getInstance().loadCategoriaEmpleado();
					}
					else
					{
						Controladora.getInstance().reiniciarCategoriaEmpleado();
					}
					if(Controladora.getInstance().activarLoadEmpleados())
					{
						Controladora.getInstance().loadEmpleados();
					}
					else
					{
						Controladora.getInstance().reiniciarEmpleados();
					}
					if(Controladora.getInstance().activarLoadPrecio())
					{
						Controladora.getInstance().loadPrecio();
					}
					else
					{
						Controladora.getInstance().reiniciarPrecios();
					}
					if(Controladora.getInstance().activarLoadProductos())
					{
						Controladora.getInstance().loadProductos();
					}
					else
					{
						Controladora.getInstance().reiniciarProducto();
					}
					if(Controladora.getInstance().activarLoadPartida())
					{
						Controladora.getInstance().loadPartida();
					}
					else
					{
						Controladora.getInstance().reiniciarPartida();
					}
					if(Controladora.getInstance().activarLoadGrupoAtributo())
					{
						Controladora.getInstance().loadGrupoAtributo();
					}
					else
					{
						Controladora.getInstance().reiniciarGrupoAtributos();
					}
					if(Controladora.getInstance().activarLoadAtributos())
					{
						Controladora.getInstance().loadAtributos();
					}
					else
					{
						Controladora.getInstance().reiniciarAtributos();
					}
					if(Controladora.getInstance().activarloadMatriz())
					{
						Controladora.getInstance().loadMatriz();
					}
					else
					{
						Controladora.getInstance().reiniciarMatriz();
					}
					if(Controladora.getInstance().activarLoadKit())
					{
						Controladora.getInstance().loadKit();
					}
					else
					{
						Controladora.getInstance().reiniciarKit();
					}
					if(Controladora.getInstance().activarLoadServicios())
					{
						Controladora.getInstance().loadServicios();
					}
					else
					{
						Controladora.getInstance().reiniciarServicio();
					}
					if(Controladora.getInstance().activarLoadManoDeObra())
					{
						Controladora.getInstance().loadManoDeObra();
					}
					else
					{
						Controladora.getInstance().reiniciarManoDeObra();
					}
					if(Controladora.getInstance().activarLoadCostoIndirecto())
					{
						Controladora.getInstance().loadCostoIndirecto();
					}
					else
					{
						Controladora.getInstance().reiniciarCostoIndirecto();
					}
					if(Controladora.getInstance().activarLoadPromedioGananciaAnual())
					{
						Controladora.getInstance().loadPromedioGananciaAnual();
					}
					else
					{
						Controladora.getInstance().reiniciarPromedioGananciaAnual();
					}
					if(Controladora.getInstance().activarLoadUsuarios())
					{
						Controladora.getInstance().loadUsuarios();
					}
					else
					{
						Controladora.getInstance().reiniciarUsuarios();
					}
					Controladora.getInstance().loadFactura();
					Controladora.getInstance().loadPromocion();
					if(Controladora.getInstance().activarLoadImagenProducto())
					{
						Controladora.getInstance().loadImagenProducto();
					}
					else
					{
						Controladora.getInstance().reiniciarImagenProducto();
					}
					if(Controladora.getInstance().activarLoadFacturaCreditoClienteSQL())
					{
						Controladora.getInstance().loadFacturaCreditoCliente();
					}
					else
					{
						Controladora.getInstance().reiniciarFacturaCreditoCliente();
					}
					if(Controladora.getInstance().activarLoadPeticiones())
					{
						Controladora.getInstance().loadPeticiones();
					}
					else
					{
						Controladora.getInstance().reiniciarPeticiones();
					}
					if(Controladora.getInstance().activarLoadPeticionesCredito())
					{
						Controladora.getInstance().loadPeticionesCredito();
					}
					else
					{
						Controladora.getInstance().reiniciarPeticionesCredito();
					}
					if(Controladora.getInstance().activarLoadPagoPeticionesCredito())
					{
						Controladora.getInstance().loadPagoPeticionesCredito();
					}
					else
					{
						Controladora.getInstance().reiniciarPagoPeticionesCredito();
					}
					if(Controladora.getInstance().activarLoadCajaChica())
					{
						Controladora.getInstance().loadCajaChica();
					}
					else
					{
						Controladora.getInstance().reiniciarCajaChica();
					}
					if(Controladora.getInstance().activarLoadCuentaBancaria())
					{
						Controladora.getInstance().loadCuentaBancaria();
					}
					else
					{
						Controladora.getInstance().reiniciarCuentaBancaria();
					}
					if(textfield_usuario.getText().equalsIgnoreCase("administrador")) {
						Usuario userRoot = new Usuario("administrador", null, true, "administrador", true, null);
						Controladora.getInstance().setUsuarioLogueado(userRoot);
					}
					else {
						if(checkremember.isSelected() && Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
						{
							Controladora.getInstance().setUsuarioLogueado(Controladora.getInstance().buscarUsuario(user, passwordfield_login.getText()));
						}
						else
						{
							Controladora.getInstance().setUsuarioLogueado(Controladora.getInstance().buscarUsuario(user, passwordHash));
						}
						
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

		if(textfield_usuario.getText().equalsIgnoreCase("administrador") && Controladora.getInstance().activarLoadUsuarios())
		{
			message.setText("¡Este usuario ya no está disponible!");
			message.setTextFill(Color.rgb(210, 39, 30));
			
			loading_progress.setVisible(false);
			loading_progress.setProgress(0);
			passwordfield_login.clear();
		}
		
	}
	
		
		
	public void handleCloseButtonAction(MouseEvent event) {
	    Stage stage = (Stage) button_salir.getScene().getWindow();
	    stage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}



}


