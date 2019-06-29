package visual;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Controladora;
import logico.Producto;

public class ControllerNuevaFactura implements Initializable{
        @FXML private TextField textfield_buscarClienteFactura;
	    @FXML private Button button_buscarClienteFactura;
	    @FXML private CheckBox checkbox_clienteFactura;
	    @FXML private TitledPane titledpane_busquedaClientesFactura;
	    
	    @FXML private TextField textfield_totalAPagar;
	    @FXML private TextField textfield_totalRecibido;
	    @FXML private TextField textfield_totalCambio;
	   
	    @FXML private Button button_sendProducto;
	    @FXML private Button button_returnProducto;
	    
	    @FXML private ListView<String> listview_facturaProductoList;
	    @FXML private ListView<String> listview_productosFacturados;

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
	
	public void floatFieldPressed(KeyEvent event) {
    	TextField source = (TextField) event.getSource();
    	//ke.getCode().equals(KeyCode.VK_BACK_SPACE)
    	if(source.getLength() == 0 && !event.getCode().equals(KeyCode.BACK_SPACE)) {
    		if(!Controladora.getInstance().isFloat("", event.getCharacter())) {
        		event.consume();
        	}
    	}
    	else {
    		if(!Controladora.getInstance().isFloat(source.getText(), event.getCharacter()) && !event.getCode().equals(KeyCode.BACK_SPACE)) {
    			event.consume();
    		}
    	}
    	if(source.equals(textfield_totalRecibido) || source.equals(textfield_totalAPagar)) {
    		calcularCambio(event);
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
    
    public void calcularCambio(KeyEvent event) {
    	float recibido = 0;
    	float total = 0;
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	if(event != null) {
    		if(event.getCode().equals(KeyCode.BACK_SPACE)) {
    			recibido = Float.parseFloat(textfield_totalRecibido.getText());
    			total = Float.parseFloat(textfield_totalAPagar.getText());
    		}
    		else if(!event.getCode().equals(KeyCode.BACK_SPACE) && event.getSource().equals(textfield_totalRecibido)) {
    			recibido = Float.parseFloat(textfield_totalRecibido.getText() + event.getCharacter());
    			total = Float.parseFloat(textfield_totalAPagar.getText());
    		}
    	}
    	else {
    		recibido = Float.parseFloat(textfield_totalRecibido.getText());
			total = Float.parseFloat(textfield_totalAPagar.getText());
    	}
    	
    	float cambio = recibido - total;
    	if(cambio < 0) {
    		textfield_totalCambio.setStyle("-fx-text-inner-color: red;");
    		textfield_totalCambio.setText(Float.toString(cambio));
    	}
    	else {
    		textfield_totalCambio.setStyle("-fx-text-inner-color: black;");
    		textfield_totalCambio.setText(Float.toString(cambio));
    	}
    }
    
    public void sendProducto(ActionEvent event) {
    	String producto = listview_facturaProductoList.getSelectionModel().getSelectedItem();
    	listview_productosFacturados.getItems().add(producto);
    	listview_productosFacturados.refresh();
    	float costo = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		costo += Float.parseFloat(Controladora.getInstance().findPartidaCosto(items));
    	}
    	textfield_totalAPagar.setText(Float.toString(costo));
    	calcularCambio(null);
    }
    
    public void returnProducto(ActionEvent event) {
    	String producto = listview_productosFacturados.getSelectionModel().getSelectedItem();
    	listview_productosFacturados.getItems().remove(producto);
    	listview_productosFacturados.refresh();
    	float costo = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		costo += Float.parseFloat(Controladora.getInstance().findPartidaCosto(items));
    	}
    	textfield_totalAPagar.setText(Float.toString(costo));
    	calcularCambio(null);
    }
    
    public void searchProductos(KeyEvent event) {
    	
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillProductos(null);
	}
	
	public void fillProductos(ArrayList<Producto> producto) {
		ObservableList<String> data = FXCollections.observableArrayList();
		if(producto == null) {
			for(Producto p : Controladora.getInstance().getMisProductos()) {
				if(!p.isBorrado()) {
					if(p.getUnidadMedida() == null) {
						data.add(p.getNombre() + ": " + p.getCosto() + "$");
					}
					else {
						data.add(p.getNombre() + ": " + p.getCosto() + "$ (" + p.getUnidadMedida().getNombre() + ")");
					}
				}
			}
		}
		else {
			for(Producto p : producto) {
				if(!p.isBorrado()) {
					if(p.getUnidadMedida() == null) {
						data.add(p.getNombre() + ": " + p.getCosto() + "$");
					}
					else {
						data.add(p.getNombre() + ": " + p.getCosto() + "$ (" + p.getUnidadMedida().getNombre() + ")");
					}
				}
			}
		}
		listview_facturaProductoList.setItems(data);
		listview_facturaProductoList.refresh();
	}

}
