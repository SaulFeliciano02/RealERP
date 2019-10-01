package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.CategoriaEmpleado;
import logico.Controladora;
import logico.Empleado;

public class ControllerNuevoEmpleado implements Initializable{

	@FXML private TextField textfield_empleadoCodigo;
	@FXML private TextField textfield_empleadoNombre;
	@FXML private TextField textfield_empleadoTelefono;
	@FXML private TextField textfield_empleadoTelefonoAdicional;
	@FXML private TextArea textarea_empleadoDomicilio;
	@FXML private TextField textfield_empleadoSueldo;
	@FXML private TextField textfield_empleadoRNC;
	@FXML private TextField textfield_empleadoCorreo;
	@FXML private RadioButton radiobutton_empleadoDia;
	@FXML private RadioButton radiobutton_empleadoHora;
	@FXML private Button button_empleadoGuardar;
	@FXML private Button button_empleadoCancelar;
	@FXML private TextField textfield_RegEmpCategoria;
	@FXML private Button button_BuscarCategoriaEmp;
	@FXML private TitledPane buscarCategoriaEmpleados;
	
    @FXML private TextField textfield_nombreCategoriaEmp;
    @FXML private TextField textfield_salarioCategoriaEmp;
    @FXML private Button button_guardarCategoriaEmp;
    @FXML private TableView<CategoriaEmpleado> tableview_CategoriaEmp;
    @FXML private TableColumn<CategoriaEmpleado, String> tablecolumn_NombreCategoria;
    @FXML private TableColumn<CategoriaEmpleado, Float> tablecolumn_SueldoCategoria;
    @FXML private RadioButton radiobutton_PorHora;
    @FXML private RadioButton radiobutton_PorDia;
	
    //Cierra y vuelve a abrir la ventana principal.
	public void reload(Stage stage) {
    	
   		try {
        	Stage primaryStage = new Stage();
        	FXMLLoader f = new FXMLLoader(getClass().getResource("viewPrincipal.fxml"));
		    Parent root = f.load();
		    Controller c = f.getController();
		    c.rh_pressed(null);
		    c.selectTabEmpleado();
		    Scene sc = new Scene(root);
		    primaryStage.setScene(sc);
		    primaryStage.setTitle("Centro Pymes");
		    primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("images/favicon.png")));
		    primaryStage.setMaximized(true);
		    Window owner = stage.getOwner();
		    primaryStage.show();
		    owner.hide();

		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
   		
	}
	
	//Verifica si el input de un textfield es un número.
    public void numericFieldPressed(KeyEvent event) {
    	TextField source = (TextField) event.getSource();
    	if(!Controladora.getInstance().isNumber(event.getCharacter())) {
    		event.consume();
    	}
    	//Especifico sobre que variable el evento surgio
    	else if(source.equals(textfield_empleadoTelefono)) {
    		empleadoActivarGuardar(event);
    	}
    }
	
    //Función que determina si la tecla presionada en un textfield cumple con los parámetros para ser considero un valor de tipo float.
	public void floatFieldPressed(KeyEvent event) {	
    	TextField source = (TextField) event.getSource();
    	//Cuando se presiona una tecla para ser registrada como input en un textfield de javafx,
    	//esta no queda registrada hasta que se termine de procesar el evento.
    	if(source.getLength() == 0) {
	   		if(!Controladora.getInstance().isFloat("", event.getCharacter())) {
	       		event.consume();
	       	}
	   	}
    	else {
	    	if(!Controladora.getInstance().isFloat(source.getText(), event.getCharacter())) {
	    		event.consume();
	   		}
	   	}
    	if(source.equals(textfield_empleadoSueldo)){
    		empleadoActivarGuardar(event);
    	}
	 }
	
	//Cierra la ventana
	public void cancelCreation(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	Stage stage = (Stage) button.getScene().getWindow();
	   	reload(stage);
	}
	
	//Determina si los parámetros de un empleado están completos.
	public void empleadoActivarGuardar(KeyEvent event) {
		//Nota sobre los guardar: En el programa encontraras que algunas funciones de guardar tratan de manera diferente
    	//la validación de los parámetros, si se te es posible estandarizarlo, recomendamos hacerlo.
		if(textfield_empleadoCodigo.getLength() > 0 && textfield_empleadoNombre.getLength() > 0 && textfield_empleadoRNC.getLength() > 0) {
			button_empleadoGuardar.setDisable(false);
		}
		else {
			button_empleadoGuardar.setDisable(true);
		}
	}

	//Guarda un nuevo empleado
	public void guardarEmpleado(ActionEvent Event) {
		boolean isEmpty = false;
		boolean validRegister = true;
		Alert a = new Alert(AlertType.NONE); 
		a.setAlertType(AlertType.ERROR);
		Alert success = new Alert(AlertType.INFORMATION, "Los datos han sido guardados exitosamente.");
		String codigo = textfield_empleadoCodigo.getText();
		String nombre = textfield_empleadoNombre.getText();
		String telefono = textfield_empleadoTelefono.getText();
		String rnc = textfield_empleadoRNC.getText();
		float saldo = 0;
		if(!textfield_empleadoSueldo.isDisabled())
		{
			saldo = Float.parseFloat(textfield_empleadoSueldo.getText());
		}
		String direccion = "";
		String correo = "";
		CategoriaEmpleado categoria= Controladora.getInstance().buscarCategoria(textfield_RegEmpCategoria.getText());
		try {
			direccion = textarea_empleadoDomicilio.getText();
			correo = textfield_empleadoCorreo.getText();
		}catch(NullPointerException e) {
			isEmpty = true;
		}
		for(Empleado e: Controladora.getInstance().getMisEmpleados()) {
			if(e.getCodigo().equalsIgnoreCase(codigo)) {
				a.setContentText("Este codigo ya esta registrado");
				a.show();
				validRegister = false;
			}
			else if(e.getRnc().equalsIgnoreCase(rnc)) {
				a.setContentText("Este rnc ya esta registrado");
				a.show();
				validRegister = false;
			}
		}
		if(saldo == 0 && !textfield_empleadoSueldo.isDisabled()) {
			a.setContentText("Asignele un sueldo al empleado.");
			a.show();
		}
		if(validRegister) {
			Empleado empleado = new Empleado(codigo, nombre, telefono, direccion, correo, rnc, saldo, categoria);
			success.showAndWait();
			Controladora.getInstance().addEmpleado(empleado);
			textfield_empleadoCodigo.setText("");
			textfield_empleadoNombre.setText("");
			textfield_empleadoTelefono.setText("");
			textfield_empleadoSueldo.setText("");
			textfield_empleadoRNC.setText("");
			textfield_RegEmpCategoria.setText("");
			if(!isEmpty) {
				textarea_empleadoDomicilio.setText("");
				textfield_empleadoCorreo.setText("");
				textfield_empleadoTelefonoAdicional.setText("");
			}
			button_empleadoGuardar.setDisable(true);
		}
	}
	
	public void abrirBuscadorCategorias(ActionEvent event) {
		buscarCategoriaEmpleados.setVisible(true);
	}
	
	public void cerrarBuscadorCategorias(ActionEvent event) {
		buscarCategoriaEmpleados.setVisible(false);
	}
	
	public void pressed_guardarCategoriaEmp(ActionEvent event)
	{
		if(!textfield_nombreCategoriaEmp.getText().isEmpty() && !textfield_salarioCategoriaEmp.getText().isEmpty())
		{
			String nombre = textfield_nombreCategoriaEmp.getText();
			float salario = Float.parseFloat(textfield_salarioCategoriaEmp.getText());
			
			if(radiobutton_PorDia.isSelected())
			{
				salario = salario/8;
			}
			
			CategoriaEmpleado cat = new CategoriaEmpleado(nombre, salario);
			
			Controladora.getInstance().getMisCategoriasEmpleado().add(cat);
			
			tablecolumn_NombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			tablecolumn_SueldoCategoria.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
			
			textfield_nombreCategoriaEmp.setText("");
			textfield_salarioCategoriaEmp.setText("");
			ObservableList<CategoriaEmpleado> data = FXCollections.observableArrayList();
			data.add(cat);
			tableview_CategoriaEmp.getItems().add(cat);
			tableview_CategoriaEmp.refresh();	
		}
	}
	
    public void retornaSeleccionado(ActionEvent event) {
    	textfield_RegEmpCategoria.setText(tableview_CategoriaEmp.getSelectionModel().getSelectedItem().getNombre());
    	textfield_empleadoSueldo.setText(String.valueOf(tableview_CategoriaEmp.getSelectionModel().getSelectedItem().getSueldo()));
    	textfield_empleadoSueldo.setDisable(false);
    	button_guardarCategoriaEmp.setDisable(true);
    	buscarCategoriaEmpleados.setVisible(false);
    }
    
    public void tooltipMessage(MouseEvent event) {
    	final Tooltip tooltip = new Tooltip();
    	tooltip.setText(
    		    "El valor fijo del salario de una categoría\n" +
    		    " de empleados es la que se utiliza para la\n" +
    		    " mano de obra de los productos y servicios\n" +
    		    " pernitentes, si modificas este valor, no se\n" +
    		    " tomará en cuenta para esos calculos.\n"
    		);
    	textfield_empleadoSueldo.setTooltip(tooltip);
    }
	  
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillCategoriaEmpleado();
	}
	
	 public void fillCategoriaEmpleado() {
	    	ObservableList<CategoriaEmpleado> dataC = FXCollections.observableArrayList();
	    	System.out.println("Klk");
	    	if(Controladora.getInstance().getMisCategoriasEmpleado().size() > 0) {
	    		
	    		for(CategoriaEmpleado c : Controladora.getInstance().getMisCategoriasEmpleado()) {
	    			dataC.add(c);
	    		}
	    		tablecolumn_NombreCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	    		tablecolumn_SueldoCategoria.setCellValueFactory(new PropertyValueFactory<>("sueldo"));
	    		tableview_CategoriaEmp.setItems(dataC);
	    		tableview_CategoriaEmp.refresh();
	    	}
	    }

}
