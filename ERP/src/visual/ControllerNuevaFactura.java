package visual;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.CantKitsUtilizados;
import logico.CantProductosUtilizados;
import logico.Cliente;
import logico.Controladora;
import logico.Estandar;
import logico.Factura;
import logico.Kit;
import logico.Producto;
import logico.Servicio;

public class ControllerNuevaFactura implements Initializable{
        @FXML private TextField textfield_buscarClienteFactura;
	    @FXML private Button button_buscarClienteFactura;
	    @FXML private CheckBox checkbox_clienteFactura;
	    @FXML private TitledPane titledpane_busquedaClientesFactura;
	    
	    @FXML private TextField textfield_facturaCantidad;
	    @FXML private TextField textfield_productoBusqueda;
	    @FXML private TextField textfield_totalAPagar;
	    @FXML private TextField textfield_totalRecibido;
	    @FXML private TextField textfield_totalCambio;
	    
	    @FXML private RadioButton radiobutton_facturaEfectivo;
	    @FXML private RadioButton radiobutton_facturaTarjeta;
	   
	    @FXML private Button button_sendProducto;
	    @FXML private Button button_returnProducto;
	    @FXML private Button guardarFactura;
	    
	    @FXML private ComboBox<String> combobox_facturaMedida;
	    
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
    
    public void guardarFactura(ActionEvent event) {
    	Cliente cliente = null;
    	for(Cliente c : Controladora.getInstance().getMisClientes()) {
    		if(c.getCodigo().equalsIgnoreCase(textfield_buscarClienteFactura.getText())) {
    			cliente = c;	
    		}
    	}
    	
    	float montoTotal = Float.parseFloat(textfield_totalAPagar.getText());
    	float montoRecibido = Float.parseFloat(textfield_totalRecibido.getText());
    	float montoCambio = Float.parseFloat(textfield_totalCambio.getText());
    	String tipoPago = "";
    	
    	if(radiobutton_facturaEfectivo.isSelected()) {
    		tipoPago = radiobutton_facturaEfectivo.getText();
    	}
    	else {
    		tipoPago = radiobutton_facturaTarjeta.getText();
    	}
    	ArrayList<String> alreadyUsed = new ArrayList<>();
    	ArrayList<CantProductosUtilizados> prodFacturados = new ArrayList<>();
    	ArrayList<CantKitsUtilizados> kitFacturados = new ArrayList<>();
    	ArrayList<Servicio> serviciosFacturados = new ArrayList<>();
    	
    	for(String items : listview_productosFacturados.getItems()) {
    		CantProductosUtilizados cantidadUtilizados = null;
    		CantKitsUtilizados cantidadKitUtilizados = null;
    		String nombreItem = Controladora.getInstance().findFacturaNombre(items);
    		if(!alreadyUsed.contains(nombreItem)) {
    			Producto producto = Controladora.getInstance().buscarProducto(nombreItem);
    			int cantidad = 0;
    			for(String searchSame : listview_productosFacturados.getItems()) {
    				String searchSameName = Controladora.getInstance().findFacturaNombre(searchSame);
    				if(searchSameName.equalsIgnoreCase(nombreItem)) {			
    					if(producto.getUnidadMedida() == null) {
    						cantidad++;
    					}
    					
    				}
    			}
    			if(producto.getTipoProducto().equalsIgnoreCase("Estandar")) {
    				cantidadUtilizados = new CantProductosUtilizados(producto, cantidad);
    				prodFacturados.add(cantidadUtilizados);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    				cantidadKitUtilizados = new CantKitsUtilizados((Kit) producto, cantidad);
    				kitFacturados.add(cantidadKitUtilizados);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Servicio")) {
    				serviciosFacturados.add((Servicio) producto);
    			}
    			
    			alreadyUsed.add(nombreItem);
    		}
    	}
    	Factura factura = new Factura(prodFacturados, kitFacturados, serviciosFacturados, montoTotal, tipoPago, montoRecibido, montoCambio, cliente);
    	Controladora.getInstance().getMisFacturas().add(factura);
    	
    	Controladora.getInstance().guardarFacturaSQL(factura);
    	
    	for(CantProductosUtilizados c : prodFacturados) {
    		Controladora.getInstance().getMisCantProductosUtilizados().add(c);
    		Controladora.getInstance().guardarCantProductosUtilizadosSQL( (Estandar) c.getProductoClass(), c);
    		Controladora.getInstance().guardarProductosFacturadosSQL(c, factura);
    	}
    	for(CantKitsUtilizados k : kitFacturados) {
    		Controladora.getInstance().getMisCantKitsUtilizados().add(k);
    		Controladora.getInstance().guardarKitsUtilizadosSQL(k);
    		Controladora.getInstance().guardarKitsFacturadosSQL(k, factura);
    	}
    	for(Servicio s : serviciosFacturados) {
    		Controladora.getInstance().guardarServiciosFacturadosSQL(s, factura);
    	}
    	
    	listview_productosFacturados.getItems().clear();
    	listview_productosFacturados.refresh();
    	textfield_totalAPagar.setText("");
    	textfield_totalRecibido.setText("");
    	textfield_totalCambio.setText("");
    	
    	//System.out.print(Controladora.getInstance().getMisFacturas().size());
    }
    
    public void tableViewFacturaClicked(MouseEvent event) {
    	if(listview_facturaProductoList.getSelectionModel().getSelectedIndex() > -1)
    	{
    		String posicion = listview_facturaProductoList.getSelectionModel().getSelectedItem();
    		String selection = Controladora.getInstance().findFacturaNombre(posicion);
    		Producto p = Controladora.getInstance().buscarProducto(selection);
    		
    		combobox_facturaMedida.getItems().clear();
    		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Area"))
    		{
    			combobox_facturaMedida.getItems().addAll("Sq Pulgadas", "Sq Pies", "Sq Yardas", "Sq Milimetros", "Sq Centimetros", "Sq Metros");
    		}
    		
    		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Longitud"))
    		{
    			combobox_facturaMedida.getItems().addAll("Pulgadas", "Pies", "Yardas", "Milimetros", "Centimetros", "Metros");
    		}
    		
    		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Masa"))
    		{
    			combobox_facturaMedida.getItems().addAll("Grano", "Onza", "Libra", "Miligramo", "Gramo", "Kilogramo");
    		}
    		
    		if(p.getUnidadMedida().getCategoria().equalsIgnoreCase("Volumen"))
    		{
    			combobox_facturaMedida.getItems().addAll("Pulgadas Cb", "Pies  Cb", "Yardas Cb", "Cuchara de té", "Cuchara de madera", "Onza fluida", "Taza", "Medio litro", "Cuarto de galón", "Galón", "Barril", "Milímetros cb", "Centímetros cb", "Metros cb", "Mililitros", "Litros");
    		}
    		if(p.getUnidadMedida() == null) {
    			combobox_facturaMedida.getItems().clear();
    		}
    		else {
    			combobox_facturaMedida.getSelectionModel().select(p.getUnidadMedida().getNombre());
    		}
    		
    	}
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
    	String item = listview_facturaProductoList.getSelectionModel().getSelectedItem();
    	Producto producto = Controladora.getInstance().buscarProducto(Controladora.getInstance().findEncargadoNombre(item));
    	float costoConvertido = 0;
    	if(producto.getUnidadMedida() == null) {
    		for(int i = 0; i < Integer.parseInt(textfield_facturaCantidad.getText()); i++) {
    			listview_productosFacturados.getItems().add(item);
    			listview_productosFacturados.refresh();
    		}
    	}
    	else {
    		float cantidadConvertida = 0;
    		String tipoConversion = combobox_facturaMedida.getSelectionModel().getSelectedItem();
        	switch(tipoConversion){
        		case "Pulgadas":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Pulgadas", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Pies":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Pies", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Yardas":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Yardas", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Milimetros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Milimetros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Centimetros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Centimetros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Metros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Metros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		
        		case "Pulgadas cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Pulgadas cb", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Pies cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Pies cb", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Cuchara de té":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Cuchara de té", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Onza fluida":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Onza fluida", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Taza":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Taza", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Medio litro":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Medio litro", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Cuarto de galón":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Cuarto de galón", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Galón":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Galón", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Barril":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Barril", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Milímetros cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Milímetros cb", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Centímetros cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Centímetros cb", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Metros cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Metros cb", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Mililitros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Mililitros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Litros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Litros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Cuchara de madera":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Cuchara de madera", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        			
        		case "Grano":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Grano", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Onza":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Onza", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Libra":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Libra", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Miligramo":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Miligramo", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Gramo":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Gramo", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Kilogramo":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Kilogramo", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;

        		case "Sq Pulgadas":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Sq Pulgadas", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Sq Pies":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Sq Pies", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Sq Yardas":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Sq Yardas", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Sq Milimetros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Sq Milimetros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Sq Centimetros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Sq Centimetros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Sq Metros":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Sq Metros", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        	};
        	costoConvertido = cantidadConvertida * producto.getCosto();
        	String itemNombre = Controladora.getInstance().findFacturaNombre(item);
        	String itemMedida = itemNombre + ": " + costoConvertido + "$ " + "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")";
        	listview_productosFacturados.getItems().add(itemMedida);
    	}
    	
    	
    	
    	float costo = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		costo += Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));
    	}
    	textfield_totalAPagar.setText(Float.toString(costo));
    	listview_facturaProductoList.getSelectionModel().clearSelection();
    	combobox_facturaMedida.getItems().clear();
    	textfield_facturaCantidad.setText("1");
    	calcularCambio(null);
    }
    
    public void returnProducto(ActionEvent event) {
    	String producto = listview_productosFacturados.getSelectionModel().getSelectedItem();
    	listview_productosFacturados.getItems().remove(producto);
    	listview_productosFacturados.refresh();
    	float costo = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		costo += Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));	
    	}
    	textfield_totalAPagar.setText(Float.toString(costo));
    	calcularCambio(null);
    }
    
    public void searchProductos(KeyEvent event) {
    	ArrayList<Producto> productos = new ArrayList<>();
    	System.out.println(event.getCharacter());
    	if(Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
    		productos = Controladora.getInstance().searchProducts(textfield_productoBusqueda.getText() + event.getCharacter(), "Codigo");
    	}
    	else {
    		productos = Controladora.getInstance().searchProducts(textfield_productoBusqueda.getText() + event.getCharacter(), "Codigo");
    	}
    	
    	if(productos.size() == 0) {
    		fillProductos(null);
    	}
    	else {
    		fillProductos(productos);
    	}
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
