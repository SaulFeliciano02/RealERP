package visual;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import logico.PreguntaSeguridad;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Main extends Application{
	
	@FXML private ImageView button_acceder;
	@FXML private ImageView button_salir;
	@FXML private PasswordField passwordfield_login;
	@FXML private Label message;
	@FXML private ProgressBar loading_progress;
	@FXML private TextField textfield_usuario;
	@FXML private CheckBox checkremember;
	@FXML private AnchorPane pane_forgotPass;
	@FXML private VBox vbox_userDataForgotPass;
	@FXML private VBox vbox_securityQuestions;
	@FXML private HBox hbox_siguiente;
	@FXML private Text text_userNoExist;
	@FXML private Text text_questionsIncorrect;
	@FXML private Button button_siguiente;
	@FXML private Button button_guardar;
	@FXML private VBox vbox_newPassword;
	
	@FXML private TextField textfield_usuarioBusqueda;
	@FXML private TextField textfield_pregunta1;
	@FXML private TextField textfield_respuesta1;
	@FXML private TextField textfield_pregunta2;
	@FXML private TextField textfield_respuesta2;
	@FXML private TextField textfield_nuevaContrasena;
	@FXML private TextField textfield_confirmarNuevaContrasena;
	
	
	String usu;
	PreguntaSeguridad ps = null;
	
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
		    
		    String fileName = "C:\\Users\\" + "\\" + System.getProperty("user.name") + "\\ERP Logs";
			File file = new File(fileName);
			file.mkdirs();
		    
		    if(Controladora.getInstance().activarLoadUsuarios()) {
		    	Controladora.getInstance().loadEmpleados();
		    	Controladora.getInstance().loadUsuarios();
		    }
		    /*if(!Controladora.getInstance().activarLoadUsuarios())
		    {
		    	checkremember.setVisible(false);
		    }
		    if(Controladora.getInstance().activarUsuarioRecordado())
		    {
		    	checkremember.setText("Recuperar datos");
		    }*/
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
			    		//textfield_usuario.setText("administrador");
			    		//passwordfield_login.setText("administrador");
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
						Controladora.getInstance().OlvidarContrasena(textfield_usuario.getText());
						Controladora.getInstance().reiniciarUsuarioRecordado();
					}
					
					if(checkremember.isSelected() && Controladora.getInstance().validarUsuario(textfield_usuario.getText(), passwordfield_login.getText()) && !Controladora.getInstance().verificarUsuarioRecordado(textfield_usuario.getText(), passwordfield_login.getText()))
					{
						System.out.println("Entre al if de rememberme " + textfield_usuario.getText());
						Controladora.getInstance().RecordarContrasena(textfield_usuario.getText());
						
						Alert alert = new Alert(AlertType.INFORMATION, "Usted ha guardado los datos de su usuario en acceso directo. Para rellenar los campos de nombre de usuario y contraseña automaticamente, marque de nuevo el cuadro de 'recordar contraseña' en su próximo acceso. \n\nSi quiere eliminar el acceso directo a su usuario, acceda al sistema con los datos de su usuario \n *Nombre de usuario\n *Contraseña\n\ny el cuadro de 'recordar contraseña' desmarcado.");
			    		alert.showAndWait();
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
								Controladora.getInstance().setMisEmpleadosToNull();
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
								Controladora.getInstance().setMisUsuariosToNull();
								Controladora.getInstance().loadUsuarios();
							}
							else
							{
								Controladora.getInstance().reiniciarUsuarios();
								Controladora.getInstance().reiniciarPreguntasRecuperacion();
							}
							if(Controladora.getInstance().activarLoadFacturas())
							{
								Controladora.getInstance().loadFactura();
							}
							else
							{
								Controladora.getInstance().reiniciarFactura();
							}
							if(Controladora.getInstance().activarLoadPromocion())
							{
								Controladora.getInstance().loadPromocion();
							}
							else
							{
								Controladora.getInstance().reiniciarPromocion();
							}
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
							if(!Controladora.getInstance().activarUsuarioRecordado())
							{
								Controladora.getInstance().reiniciarUsuarioRecordado();
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
			}
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
				
				Alert alert = new Alert(AlertType.INFORMATION, "Usted ha guardado los datos de su usuario en acceso directo. Para rellenar los campos de nombre de usuario y contraseña automaticamente, marque de nuevo el cuadro de 'recordar contraseña' en su próximo acceso. \n\nSi quiere eliminar el acceso directo a su usuario, acceda al sistema con los datos de su usuario \n *Nombre de usuario\n *Contraseña\n\ny el cuadro de 'recordar contraseña' desmarcado.");
	    		alert.showAndWait();
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
						Controladora.getInstance().setMisEmpleadosToNull();
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
						Controladora.getInstance().setMisUsuariosToNull();
						Controladora.getInstance().loadUsuarios();
					}
					else
					{
						Controladora.getInstance().reiniciarUsuarios();
						Controladora.getInstance().reiniciarPreguntasRecuperacion();
					}
					if(Controladora.getInstance().activarLoadFacturas())
					{
						Controladora.getInstance().loadFactura();
					}
					else
					{
						Controladora.getInstance().reiniciarFactura();
					}
					if(Controladora.getInstance().activarLoadPromocion())
					{
						Controladora.getInstance().loadPromocion();
					}
					else
					{
						Controladora.getInstance().reiniciarPromocion();
					}
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
					if(!Controladora.getInstance().activarUsuarioRecordado())
					{
						Controladora.getInstance().reiniciarUsuarioRecordado();
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
	
    public void changeColorFontExited (MouseEvent event) {
    	Label label = (Label) event.getSource();
    	label.setStyle("-fx-text-fill: #000000");
    }
    public void changeColorFontEnter (MouseEvent event) {
    	Label label = (Label) event.getSource();
    	label.setStyle("-fx-text-fill: #3c80df");
    	
    }
    
    public void openPaneForgotPas(MouseEvent event) {
    	
    	pane_forgotPass.setVisible(true);
    	
    	pane_forgotPass.setStyle("-fx-border-color: #0000");
    	pane_forgotPass.setStyle("-fx-background-color: white");
    	vbox_userDataForgotPass.setStyle("-fx-border-color: lightgray");
    	vbox_securityQuestions.setStyle("-fx-border-color: lightgray");
    	vbox_newPassword.setStyle("-fx-border-color: lightgray");
    	
    	
    }
    
    public void verificarPreguntas(ActionEvent event) {
    	//Las respuestas fueron verificadas
    	if(textfield_respuesta1.getText().equalsIgnoreCase(ps.getRespuesta1()) && textfield_respuesta2.getText().equalsIgnoreCase(ps.getRespuesta2())) {
    		vbox_securityQuestions.setVisible(false);
    		button_siguiente.setVisible(false);
    		button_guardar.setVisible(true);
    		vbox_newPassword.setVisible(true);	
    	}
    	else {
    		//Error al verificar respuestas
    		text_questionsIncorrect.setVisible(true);
    	}
    	
    	
    }
    
    public void actualizarContrasena(ActionEvent event)
    {
    	if(!textfield_nuevaContrasena.getText().isEmpty() && !textfield_confirmarNuevaContrasena.getText().isEmpty() && (textfield_nuevaContrasena.getText().equals(textfield_confirmarNuevaContrasena.getText())))
    	{
    		Usuario usuario = Controladora.getInstance().buscarUsuario(textfield_usuarioBusqueda.getText());
    		Controladora.getInstance().nuevaContrasena(textfield_nuevaContrasena.getText(), usuario);
    		
    		pane_forgotPass.setVisible(false);
    		
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("La contraseña ha sido actualizada satisfactoriamente!");
    		a.show();
    	}
    	else {
    		//Error al verificar respuestas
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Las contraseñas escritas en ambos campos no coinciden. Favor de escribirlas correctamente.");
    		a.show();
    		textfield_nuevaContrasena.clear();
    		textfield_confirmarNuevaContrasena.clear();
    	}
    }
    
    public void verificarUsuario(ActionEvent event) throws SQLException {
    	Usuario usuario = Controladora.getInstance().buscarUsuario(textfield_usuarioBusqueda.getText());
    	if(usuario != null) {
    		int index = Controladora.getInstance().getMisUsuarios().indexOf(usuario) + 1;
    		ps = Controladora.getInstance().buscarPreguntaSeguridad(index);
    		textfield_pregunta1.setText(ps.getPregunta1());
    		textfield_pregunta2.setText(ps.getPregunta2());
    		vbox_securityQuestions.setDisable(false);
    		hbox_siguiente.setDisable(false);
    	}else {
    		//Usuario no existe
    		text_userNoExist.setVisible(true);
    	} 	
    }
    
    public void cerrarRecuperacion(MouseEvent event) {
    	pane_forgotPass.setVisible(false);
    }
	
		
		
	public void handleCloseButtonAction(MouseEvent event) {
	    Stage stage = (Stage) button_salir.getScene().getWindow();
	    stage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}



}


