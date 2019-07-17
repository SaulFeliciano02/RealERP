package visual;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import archivos.FacturaValorFiscal;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import logico.Atributos;
import logico.CantKitsUtilizados;
import logico.CantProductosUtilizados;
import logico.Cliente;
import logico.Combinaciones;
import logico.Controladora;
import logico.Estandar;
import logico.Factura;
import logico.Kit;
import logico.Producto;
import logico.Promocion;
import logico.Servicio;
import logico.ServicioUtilizado;

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
	    @FXML private RadioButton radiobutton_facturaCredito;
	    @FXML private AnchorPane paneEfectivo;
	    @FXML private AnchorPane paneCredito;
	   
	    @FXML private Button button_sendProducto;
	    @FXML private Button button_returnProducto;
	    @FXML private Button guardarFactura;
	    
	    @FXML private ComboBox<String> combobox_facturaMedida;
	    
	    @FXML private RadioButton radiobutton_15Dias;
	    @FXML private RadioButton radiobutton_30Dias;
	    @FXML private RadioButton radiobutton_90Dias;
	    @FXML private RadioButton radiobutton_60Dias;
	    @FXML private DatePicker datepicker_fechaDePago;
	    
	    @FXML private ListView<String> listview_facturaProductoList;
	    @FXML private ListView<String> listview_productosFacturados;
	    
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteCodigo;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteNombre;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteTelefono;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteCumple;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteRNC;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteTipo;
	    @FXML private TableView<Cliente> tableview_clienteList;
	    
	    @FXML private TextField textfield_clienteSeleccionado;
	    @FXML private Button button_clienteSeleccionar;

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
    	radiobutton_facturaCredito.setDisable(false);
    	}else {
        	textfield_buscarClienteFactura.setDisable(true);
        	button_buscarClienteFactura.setDisable(true);
        	radiobutton_facturaCredito.setDisable(true);
        	radiobutton_facturaEfectivo.setSelected(true);
        	tipoPago(event);
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
    	textfield_clienteSeleccionado.setText("");
    	button_clienteSeleccionar.setDisable(true);
    	tableview_clienteList.getSelectionModel().clearSelection();
    }
    
    public void abrirBusquedaCliente(ActionEvent event) {
    	titledpane_busquedaClientesFactura.setVisible(true);
    	fillClientList(null);
    }
    
    public void selectingCliente(MouseEvent event) {
    	System.out.println("Hola");
    	if(!tableview_clienteList.getSelectionModel().isEmpty()) {
    		Cliente cliente = tableview_clienteList.getSelectionModel().getSelectedItem();
    		textfield_clienteSeleccionado.setText(cliente.getCodigo());
    		button_clienteSeleccionar.setDisable(false);
    	}
    }
    
    public void clienteSelect(ActionEvent event) {
    	textfield_buscarClienteFactura.setText(textfield_clienteSeleccionado.getText());
    	cerrarBusquedaCliente(event);
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
    	else if(radiobutton_facturaTarjeta.isSelected()) {
    		tipoPago = radiobutton_facturaTarjeta.getText();
    	}
    	else if(radiobutton_facturaCredito.isSelected()) {
    		tipoPago = radiobutton_facturaCredito.getText();
    		montoRecibido = 0;
    		montoCambio = 0;
    	}
    	ArrayList<String> alreadyUsed = new ArrayList<>();
    	ArrayList<CantProductosUtilizados> prodFacturados = new ArrayList<>();
    	ArrayList<CantKitsUtilizados> kitFacturados = new ArrayList<>();
    	ArrayList<ServicioUtilizado> serviciosFacturados = new ArrayList<>();
    	
    	for(String items : listview_productosFacturados.getItems()) {
    		CantProductosUtilizados cantidadUtilizados = null;
    		CantKitsUtilizados cantidadKitUtilizados = null;
    		ServicioUtilizado cantidadServicioUtilizados = null;
    		String nombreItem = Controladora.getInstance().findFacturaNombre(items);
    		if(!alreadyUsed.contains(nombreItem)) {
    			Producto producto = Controladora.getInstance().buscarProducto(nombreItem);
    			float cantidad = 0;
    			if(producto.getUnidadMedida() == null) {
    				for(String searchSame : listview_productosFacturados.getItems()) {
    					String searchSameName = Controladora.getInstance().findFacturaNombre(searchSame);
    					if(searchSameName.equalsIgnoreCase(nombreItem)) {			
    							cantidad++;
    					}
    				}
    				alreadyUsed.add(nombreItem);
    			}
    			else {
    				float precioConvertido = Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));
    				float precio = producto.getPrecio();
    				cantidad = precioConvertido / precio;
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
    				cantidadServicioUtilizados = new ServicioUtilizado((Servicio) producto);
    				serviciosFacturados.add(cantidadServicioUtilizados);
    			}
    			
    			
    			if(producto.getTipoProducto().equalsIgnoreCase("Estandar") || producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    				Estandar estandar = (Estandar) producto;
    				int indiceProducto = Controladora.getInstance().getMisProductosEstandar().indexOf(estandar)+1;
    				float cantidadRestar = estandar.getExistenciaActual() - cantidad;
    				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
    				Controladora.getInstance().getMisProductosEstandar().get(indiceProducto-1).setExistenciaActual(cantidadRestar);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    				Estandar matriz = (Estandar) producto;
    				int indiceProducto = Controladora.getInstance().getMisProductosMatriz().indexOf(matriz)+1;
    				float cantidadRestar = matriz.getExistenciaActual() - cantidad;
    				Controladora.getInstance().restarExistenciaActualMatriz(cantidadRestar, indiceProducto, matriz);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    				Kit kit = (Kit) producto;
    				int indiceProducto = Controladora.getInstance().getMisProductosKit().indexOf(kit)+1;
    				float cantidadRestar = kit.getExistenciaActual() - cantidad;
    				Controladora.getInstance().restarExistenciaActualKit(cantidadRestar, indiceProducto);
    				Controladora.getInstance().getMisProductosKit().get(indiceProducto-1).setExistenciaActual(cantidadRestar);
    			}
    		}
    	}
    	Factura factura = new Factura(prodFacturados, kitFacturados, serviciosFacturados, montoTotal, tipoPago, montoRecibido, montoCambio, cliente);
    	Controladora.getInstance().getMisFacturas().add(factura);
    	
    	Controladora.getInstance().guardarFacturaSQL(factura);
    	
    	for(CantProductosUtilizados c : prodFacturados) {
    		Controladora.getInstance().getMisCantProductosUtilizados().add(c);
    		Controladora.getInstance().guardarCantProductosUtilizadosSQL( (Estandar) c.getProducto(), c);
    		Controladora.getInstance().guardarProductosFacturadosSQL(c, factura);
    	}
    	for(CantKitsUtilizados k : kitFacturados) {
    		Controladora.getInstance().getMisCantKitsUtilizados().add(k);
    		Controladora.getInstance().guardarKitsUtilizadosSQL(k);
    		Controladora.getInstance().guardarKitsFacturadosSQL(k, factura);
    	}
    	for(ServicioUtilizado s : serviciosFacturados) {
    		Controladora.getInstance().guardarServiciosFacturadosSQL(s.getServicio(), factura);
    	}
    	
    	if(radiobutton_facturaCredito.isSelected()) {
    		cliente.setDeuda(cliente.getDeuda() + montoTotal);
    		Controladora.getInstance().guardarClienteDeudaSQL(cliente);
    	}
    	
    	listview_productosFacturados.getItems().clear();
    	listview_productosFacturados.refresh();
    	textfield_totalAPagar.setText("");
    	textfield_totalRecibido.setText("");
    	textfield_totalCambio.setText("");
    	
    	//System.out.print(Controladora.getInstance().getMisFacturas().size());
    	FacturaValorFiscal.CrearFactura(factura);
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
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	boolean isValid = true;
    	String item = listview_facturaProductoList.getSelectionModel().getSelectedItem();
    	Producto producto = Controladora.getInstance().buscarProducto(Controladora.getInstance().findFacturaNombre(item));
    	float precioConvertido = 0;
    	float cantidadCheck = Float.parseFloat(textfield_facturaCantidad.getText());
    	for(String items : listview_productosFacturados.getItems()) {
    		if(producto.getNombre().equalsIgnoreCase(Controladora.getInstance().findFacturaNombre(items))) {
    			cantidadCheck++;
    		}
    	}
    	isValid = checkExistenciaMinima(producto, cantidadCheck);
    	
    	if(producto.getUnidadMedida() == null && isValid) {
    		for(int i = 0; i < Integer.parseInt(textfield_facturaCantidad.getText()); i++) {
    			listview_productosFacturados.getItems().add(item);
    			listview_productosFacturados.refresh();
    		}
    	}
    	
    	else {
    		isValid = true;
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
        	cantidadCheck = cantidadConvertida;
        	for(String items : listview_productosFacturados.getItems()) {
        		if(producto.getNombre().equalsIgnoreCase(Controladora.getInstance().findFacturaNombre(items))) {
        			Producto productoFacturado = Controladora.getInstance().buscarProducto(Controladora.getInstance().findFacturaNombre(items));
        			float precioCheck = Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));
        			cantidadCheck += (precioCheck / productoFacturado.getPrecio());
        		}
        	}
        	System.out.println(cantidadCheck);
        	isValid = checkExistenciaMinima(producto, cantidadCheck);
        	if(isValid) {
        		precioConvertido = cantidadConvertida * Float.parseFloat(Controladora.getInstance().findFacturaCosto(item));
        		String itemNombre = Controladora.getInstance().findFacturaNombre(item);
        		Boolean hasPromotion = false;
				//float precioPromocion = 0;
				for(Promocion promocion : Controladora.getInstance().getMisPromociones()) {
					for(Producto promoProductos : promocion.getProductos()) {
							if(promoProductos.equals(producto) && 
									((LocalDate.now().compareTo(promocion.getFechaInicio()) >= 0 && LocalDate.now().compareTo(promocion.getFechaFinal()) <= 0
									&& LocalTime.now().compareTo(promocion.getHoraInicio()) >= 0 && LocalTime.now().compareTo(promocion.getHoraFinal()) <= 0)
									|| promocion.getDia().equalsIgnoreCase(LocalDate.now().getDayOfWeek().toString()))) {
								hasPromotion = true;
							}
					}
				}
				String itemMedida = "";
				if(hasPromotion) {
					if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
						Estandar estandar = (Estandar) producto;
						String numeroSerie = Controladora.getInstance().findFacturaNumeroSerie(item);
						for(Combinaciones c : estandar.getCombinaciones()) {
							if(numeroSerie.equalsIgnoreCase(c.getNumeroSerie())) {
								itemMedida = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
								for(Atributos atributo : c.getListaAtributos()) {
									itemMedida += ", (" + atributo.getNombre() + "), ";
								}
								itemMedida += ": " + cantidadConvertida + "$" +  "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")" + " (Promoción)";
							}
						}
					}
					else {
						itemMedida = itemNombre + ": " + precioConvertido + "$ " + "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")" + " (Promoción)";
					}
					
				}else {
					if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
						Estandar estandar = (Estandar) producto;
						String numeroSerie = Controladora.getInstance().findFacturaNumeroSerie(item);
						for(Combinaciones c : estandar.getCombinaciones()) {
							if(numeroSerie.equalsIgnoreCase(c.getNumeroSerie())) {
								itemMedida = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
								for(Atributos atributo : c.getListaAtributos()) {
									itemMedida += ", (" + atributo.getNombre() + "), ";
								}
								itemMedida += ": " + cantidadConvertida + "$" +  "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")";
							}
						}
					}
					itemMedida = itemNombre + ": " + precioConvertido + "$ " + "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")";
				}
        		
        		listview_productosFacturados.getItems().add(itemMedida);
        	}
        	
    	}
    	
    	
    	
    	float precio = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		precio += Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));
    	}
    	textfield_totalAPagar.setText(Float.toString(precio));
    	listview_facturaProductoList.getSelectionModel().clearSelection();
    	combobox_facturaMedida.getItems().clear();
    	textfield_facturaCantidad.setText("1");
    	calcularCambio(null);
    }
    
    public void returnProducto(ActionEvent event) {
    	String producto = listview_productosFacturados.getSelectionModel().getSelectedItem();
    	listview_productosFacturados.getItems().remove(producto);
    	listview_productosFacturados.refresh();
    	float precio = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		precio += Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));	
    	}
    	textfield_totalAPagar.setText(Float.toString(precio));
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
    
    public void calcularDias(ActionEvent event) {
    	RadioButton radioEvent = null;
    	LocalDate localdate = LocalDate.now();
    	LocalDate newDate = null;
    	try {
    		radioEvent = (RadioButton) event.getSource();
    	}catch(NullPointerException e) {
    		
    	}
    	if(radioEvent.equals(radiobutton_15Dias)) {
    		radiobutton_30Dias.setSelected(false);
    		radiobutton_90Dias.setSelected(false);
    		radiobutton_60Dias.setSelected(false);
    		newDate = localdate.plusDays(15);
    	}
    	else if(radioEvent.equals(radiobutton_30Dias)) {
    		radiobutton_15Dias.setSelected(false);
    		radiobutton_90Dias.setSelected(false);
    		radiobutton_60Dias.setSelected(false);
    		newDate = localdate.plusDays(30);
    	}
    	else if(radioEvent.equals(radiobutton_60Dias)) {
    		radiobutton_30Dias.setSelected(false);
    		radiobutton_15Dias.setSelected(false);
    		radiobutton_90Dias.setSelected(false);
    		newDate = localdate.plusDays(60);
    	}
    	else if(radioEvent.equals(radiobutton_90Dias)) {
    		radiobutton_30Dias.setSelected(false);
    		radiobutton_60Dias.setSelected(false);
    		radiobutton_15Dias.setSelected(false);
    		newDate = localdate.plusDays(90);
    	}
    	datepicker_fechaDePago.setValue(newDate);
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillProductos(null);
		
		setDatePickers();
	}
	
	public void fillProductos(ArrayList<Producto> producto) {
		ObservableList<String> data = FXCollections.observableArrayList();
		if(producto == null) {
			for(Producto p : Controladora.getInstance().getMisProductos()) {
				Boolean hasPromotion = false;
				float precioPromocion = 0;
				for(Promocion promocion : Controladora.getInstance().getMisPromociones()) {
					for(Producto promoProductos : promocion.getProductos()) {
							if(promoProductos.equals(p) && 
									((LocalDate.now().compareTo(promocion.getFechaInicio()) >= 0 && LocalDate.now().compareTo(promocion.getFechaFinal()) <= 0
									&& LocalTime.now().compareTo(promocion.getHoraInicio()) >= 0 && LocalTime.now().compareTo(promocion.getHoraFinal()) <= 0)
									|| promocion.getDia().equalsIgnoreCase(LocalDate.now().getDayOfWeek().toString()))) {
								hasPromotion = true;
								precioPromocion = ((100-promocion.getPorcentajeDescuento())*p.getPrecio()) / 100;
							}
					}
				}
				if(!p.isBorrado()) {
					if(p.getUnidadMedida() == null) {
						if(hasPromotion) {
							if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
								Estandar estandar = (Estandar) p;
								for(Combinaciones c : estandar.getCombinaciones()) {
									String matrizInfo = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
									for(Atributos atributo : c.getListaAtributos()) {
										matrizInfo += ", (" + atributo.getNombre() + "), ";
									}
									matrizInfo += ": " + precioPromocion + "$" + " (Promocion)";
									data.add(matrizInfo);
								}
							}
							else {
								data.add(p.getNombre() + ": " + precioPromocion + "$" + " (Promoción)");
							}
							
						}
						else {
							if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
								Estandar estandar = (Estandar) p;
								for(Combinaciones c : estandar.getCombinaciones()) {
									String matrizInfo = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
									for(Atributos atributo : c.getListaAtributos()) {
										matrizInfo += ", (" + atributo.getNombre() + "), ";
									}
									matrizInfo += ": " + estandar.getPrecio() + "$";
									data.add(matrizInfo);
								}
							}
							else {
								data.add(p.getNombre() + ": " + p.getPrecio() + "$");
							}
							
						}
					}
					else {
						if(hasPromotion) {
							if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
								Estandar estandar = (Estandar) p;
								for(Combinaciones c : estandar.getCombinaciones()) {
									String matrizInfo = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
									for(Atributos atributo : c.getListaAtributos()) {
										matrizInfo += ", (" + atributo.getNombre() + "), ";
									}
									matrizInfo += ": " + precioPromocion + "$ (" + estandar.getUnidadMedida().getNombre() + ")" + " (Promocion)";
									data.add(matrizInfo);
								}
							}
							else {
								data.add(p.getNombre() + ": " + precioPromocion + "$ (" + p.getUnidadMedida().getNombre() + ")" + " (Promoción)");
							}
							
						}
						else {
							if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
								Estandar estandar = (Estandar) p;
								for(Combinaciones c : estandar.getCombinaciones()) {
									String matrizInfo = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
									for(Atributos atributo : c.getListaAtributos()) {
										matrizInfo += ", (" + atributo.getNombre() + "), ";
									}
									matrizInfo += ": " + precioPromocion + "$ (" + estandar.getUnidadMedida().getNombre() + ")";
									data.add(matrizInfo);
								}
							}
							else {
								data.add(p.getNombre() + ": " + p.getPrecio() + "$ (" + p.getUnidadMedida().getNombre() + ")");
							}
							
						}
					}
				}
			}
		}
		else {
			for(Producto p : producto) {
				if(!p.isBorrado()) {
					if(p.getUnidadMedida() == null) {
						if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
							Estandar estandar = (Estandar) p;
							for(Combinaciones c : estandar.getCombinaciones()) {
								String matrizInfo = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
								for(Atributos atributo : c.getListaAtributos()) {
									matrizInfo += ", (" + atributo.getNombre() + "), ";
								}
								matrizInfo += ": " + estandar.getPrecio() + "$";
								data.add(matrizInfo);
							}
						}
						else {
							data.add(p.getNombre() + ": " + p.getPrecio() + "$");
						}
						
					}
					else {
						if(p.getTipoProducto().equalsIgnoreCase("Matriz")) {
							Estandar estandar = (Estandar) p;
							for(Combinaciones c : estandar.getCombinaciones()) {
								String matrizInfo = estandar.getNombre() + ", Num.Serie (" + c.getNumeroSerie()  + ")";
								for(Atributos atributo : c.getListaAtributos()) {
									matrizInfo += ", (" + atributo.getNombre() + "), ";
								}
								matrizInfo += ": " + estandar.getPrecio() + "$ (" + p.getUnidadMedida().getNombre() + ")";
								data.add(matrizInfo);
							}
						}
						else {
							data.add(p.getNombre() + ": " + p.getPrecio() + "$ (" + p.getUnidadMedida().getNombre() + ")");
						}
						
					}
				}
			}
		}
		listview_facturaProductoList.setItems(data);
		listview_facturaProductoList.refresh();
	}
	
	public void fillClientList(ArrayList<Cliente> c) {
    	ObservableList<Cliente> data = FXCollections.observableArrayList();
    	if(c == null) {
    		data.addAll(Controladora.getInstance().getMisClientes());
    	}
    	else {
    		data.addAll(c);
    	}
		tablecolumn_clienteCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	tablecolumn_clienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	tablecolumn_clienteTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    	tablecolumn_clienteCumple.setCellValueFactory(new PropertyValueFactory<>("cumpleanos"));
    	tablecolumn_clienteRNC.setCellValueFactory(new PropertyValueFactory<>("rnc"));
    	tablecolumn_clienteTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
    	tableview_clienteList.setItems(data);
    	tableview_clienteList.refresh();
	}
	
	public void setDatePickers() {
		datepicker_fechaDePago.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
	}
	
	public boolean checkExistenciaMinima(Producto producto, float cantidadCheck) {
		boolean isValid = true;
		Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
		if(producto.getTipoProducto().equalsIgnoreCase("Estandar") || producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		Estandar estandar = (Estandar) producto;
    		System.out.println(cantidadCheck);
    		System.out.println(estandar.getExistenciaActual() - estandar.getExistenciaMinima());
   			if(cantidadCheck > (estandar.getExistenciaActual() - estandar.getExistenciaMinima())) {
   				a.setContentText("Esta sobrepasando la existencia minima de un producto!");
   				a.show();
   				isValid = false;
   			}
   		}
    	else if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    		Kit kit = (Kit) producto;
    		if(cantidadCheck > (kit.getExistenciaActual() - kit.getExistenciaMinima())) {
    			a.setContentText("Esta sobrepasando la existencia minima de un producto!");
    			a.show();
    			isValid = false;
    		}
    	}
		return isValid;
	}
	
	public void tipoPago(ActionEvent event) {
		if(radiobutton_facturaEfectivo.isSelected()) {
			paneEfectivo.setVisible(true);
			paneCredito.setVisible(false);
		}
		
		if(radiobutton_facturaCredito.isSelected()) {
			paneEfectivo.setVisible(false);
			paneCredito.setVisible(true);
		}
		
		if(radiobutton_facturaTarjeta.isSelected()) {
			paneEfectivo.setVisible(false);
			paneCredito.setVisible(false);
		}
		
	}

}
