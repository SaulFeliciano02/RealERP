package visual;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import archivos.FacturaDeConsumo;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
import logico.Peticion;
import logico.Producto;
import logico.Promocion;
import logico.Servicio;
import logico.ServicioUtilizado;

public class ControllerNuevaFactura implements Initializable{
        @FXML private TextField textfield_buscarClienteFactura;
	    @FXML private Button button_buscarClienteFactura;
	    @FXML private CheckBox checkbox_clienteFactura;
	    @FXML private TitledPane titledpane_busquedaClientesFactura;
	    @FXML private TitledPane titledpane_busquedaPeticiones;
	    
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
	    @FXML private AnchorPane paneTarjeta;
	   
	    @FXML private ImageView button_sendProducto;
	    @FXML private ImageView button_returnProducto;
	    @FXML private Button guardarFactura;
	    
	    @FXML private ComboBox<String> combobox_facturaMedida;
	    
	    @FXML private RadioButton radiobutton_15Dias;
	    @FXML private RadioButton radiobutton_30Dias;
	    @FXML private RadioButton radiobutton_90Dias;
	    @FXML private RadioButton radiobutton_60Dias;
	    @FXML private DatePicker datepicker_fechaDePago;
	    
	    @FXML private ListView<String> listview_facturaProductoList;
	    @FXML private ListView<String> listview_productosFacturados;
	    @FXML private ListView<String> listview_productosFacturadosMain;
	    
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteCodigo;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteNombre;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteTelefono;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteCumple;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteRNC;
	    @FXML private TableColumn<Cliente, String> tablecolumn_clienteTipo;
	    @FXML private TableView<Cliente> tableview_clienteList;
	    
	    @FXML private TextField textfield_clienteNombre;
	    @FXML private TextField textfield_clienteRNC;
	    @FXML private TextField textfield_clienteCredito;
	    @FXML private TextField textfield_clienteDeuda;

	    @FXML private TextField textfield_clienteSeleccionado;
	    @FXML private Button button_clienteSeleccionar;
	    @FXML private CheckBox checkbox_facturaValorFiscal;
	    @FXML private Spinner<Integer> spinner_cantcopias;
	    
	    @FXML private TitledPane titledpane_busquedaProductos;
	    
	    @FXML private TextField textfield_balancependiente;
	    @FXML private TextField textfield_descuento;
	    @FXML private TextField textfield_penalizacion;
	    @FXML private TextField textfield_pagorecibido;
	    
	    @FXML private Button button_buscarClienteFacturaCredito;
	    
	    @FXML private TextField textfield_buscarClienteFacturaCredito;
	    @FXML private TextField textfield_clienteNombreCredito;
	    @FXML private TextField textfield_clienteRNCCredito;
	    @FXML private TextField textfield_clienteCreditoCredito;
	    @FXML private TextField textfield_clienteDeudaCredito;
	    
	    @FXML private ListView<String> listview_clienteDeuda;
	    
	    @FXML private TextField textfield_fechaSolicitud;
	    @FXML private TextField textfield_balanceOriginal;
	    @FXML private TextField textfield_ultimoPago;
	    @FXML private TextField textfield_creditoBalancePendiente;
	    
	    @FXML private TextField textfield_creditoRecibido;
	    @FXML private TextField textfield_creditoCambio;
	    @FXML private TextField textfield_nuevoBalance;
	    @FXML private Button button_seleccionarFacturaDeuda;
	    @FXML private TextField textfield_montoDelUltimoPago;
	    @FXML private CheckBox checkbox_facturaValorFiscal1;
	    
	    @FXML private TableColumn<Peticion, String> tablecolumn_facturaPeticionCodigo;
	    @FXML private TableColumn<Peticion, String> tablecolumn_facturaPeticionProveedor;
	    @FXML private TableColumn<Peticion, String> tablecolumn_facturaPeticionProducto;
	    @FXML private TableColumn<Peticion, Integer> tablecolumn_facturaPeticionCantidad;
	    @FXML private TableColumn<Peticion, Float> tablecolumn_facturaPeticionMonto;
	    @FXML private TableColumn<Peticion, String> tablecolumn_facturaPeticionMetodo;
	    @FXML private TableColumn<Peticion, String> tablecolumn_facturaPeticionEstado;
	    @FXML private TableColumn<Peticion, LocalDate> tablecolumn_facturaPeticionFecha;
	    @FXML private TableView<Peticion> tableview_facturaPeticionList;

	    @FXML private TextField textfield_facturaPeticionSeleccionada;
	    @FXML private Button button_facturaPeticionSeleccionar;

	    @FXML private TextField textfield_buscarPeticionFactura;
	    @FXML private TextField textfield_facturaPeticionProducto;
	    @FXML private TextField textfield_facturaPeticionProveedor;
	    @FXML private TextField textfield_facturaPeticionCantidad;
	    @FXML private TextField textfield_facturaPeticionMonto;
	    @FXML private TextField textfield_facturaPeticionMontoAdeudado;
	    
	    @FXML private TextField textfield_peticionFacturaNCF;
	    @FXML private DatePicker datepicker_peticionFacturaFecha;
	    @FXML private RadioButton radiobutton_peticionFacturaEfectivo;
	    @FXML private RadioButton radiobutton_peticionFacturaTarjeta;
	    @FXML private TextField textfield_peticionFacturaTotalPagado;
	    
	    @FXML private TabPane tabpane_factura;
	    @FXML private Tab tab_facturarProducto;
	    @FXML private Tab tab_facturarCredito;
	    @FXML private Tab tab_facturarPeticion;
	    

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
    	if(source.equals(textfield_totalRecibido) || source.equals(textfield_totalAPagar) || source.equals(textfield_creditoCambio) || source.equals(textfield_creditoRecibido)) {
    		calcularCambio(event);
    	}
    	if(source.equals(textfield_pagorecibido) || source.equals(textfield_totalAPagar))
    	{
    		calcularDeuda(event);
    	}
    	
    }
	
    public void habilitarClienteFactura(ActionEvent event) {
    	if(checkbox_clienteFactura.isSelected()) {
    	textfield_buscarClienteFactura.setDisable(false);
    	button_buscarClienteFactura.setDisable(false);
    	if(Controladora.getInstance().getUsuarioLogueado().getCargo().isFacturaporcredito())
    	{
    		radiobutton_facturaCredito.setDisable(false);
    	}
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
    
    public void rellenarCamposFacturaDeuda(ActionEvent event)
    {
    	Factura fac = null;
    	
    	String codigo = listview_clienteDeuda.getSelectionModel().getSelectedItem().substring(0, listview_clienteDeuda.getSelectionModel().getSelectedItem().indexOf(" "));
    	
    	fac = Controladora.getInstance().buscarFactura(codigo);
    	
    	textfield_fechaSolicitud.setText(fac.getFecha().toString());
    	textfield_balanceOriginal.setText(Float.toString(fac.getMontoTotal()));
    	if(fac.getMontoDelUltimoPago() > 0)
    	{
    		textfield_montoDelUltimoPago.setText(Float.toString(fac.getMontoDelUltimoPago()));
        	textfield_ultimoPago.setText(fac.getFechaDelUltimoPago().toString());
    	}
    	else
    	{
    		textfield_montoDelUltimoPago.setText("0.0");
        	textfield_ultimoPago.setText("0.0");
    	}
    	textfield_creditoBalancePendiente.setText(Float.toString(fac.getAdeudado()));
    	
    }
    
    public void actualizarDeudaFactura(ActionEvent event)
    {
    	Factura fac = Controladora.getInstance().buscarFactura(listview_clienteDeuda.getSelectionModel().getSelectedItem().substring(0, listview_clienteDeuda.getSelectionModel().getSelectedItem().indexOf(" ")));
    	
    	fac.setMontoDelUltimoPago(Float.parseFloat(textfield_creditoRecibido.getText()));
    	fac.setFechaDelUltimoPago(LocalDate.now());
    	fac.setAdeudado(Float.parseFloat(textfield_creditoBalancePendiente.getText()) - Float.parseFloat(textfield_creditoRecibido.getText()));
    	fac.getPagosDeuda().add(Float.parseFloat(textfield_creditoRecibido.getText()));
    	Controladora.getInstance().guardarNuevoPagoDeuda(fac);
    	
    	Alert a = new Alert(AlertType.CONFIRMATION, "Confirmar pago", ButtonType.YES, ButtonType.NO);
    	a.showAndWait();
    	
    	if(a.getResult() == ButtonType.YES)
    	{
    		cleanFieldsPagoFacturaCreditoCliente();
    	}
    	
    }
    
    public void selectingCliente(MouseEvent event) {
    	if(!tableview_clienteList.getSelectionModel().isEmpty()) {
    		Cliente cliente = tableview_clienteList.getSelectionModel().getSelectedItem();
    		textfield_clienteSeleccionado.setText(cliente.getCodigo());
    		button_clienteSeleccionar.setDisable(false);
    	}
    }
    
    public void cleanFieldsPagoFacturaCreditoCliente()
    {
    	textfield_clienteNombreCredito.setText("");
    	textfield_clienteRNCCredito.setText("");
    	textfield_clienteCreditoCredito.setText("");
    	textfield_clienteDeudaCredito.setText("");
    	textfield_buscarClienteFacturaCredito.setText("");
    	for (int i = 0; i < listview_clienteDeuda.getItems().size(); i++) {
    		listview_clienteDeuda.getItems().remove(i);
		}
    	textfield_fechaSolicitud.setText("");
    	textfield_balanceOriginal.setText("");
    	textfield_montoDelUltimoPago.setText("");
    	textfield_ultimoPago.setText("");
    	textfield_creditoBalancePendiente.setText("");
    	textfield_creditoRecibido.setText("");
    	textfield_creditoCambio.setText("");
    	textfield_nuevoBalance.setText("");
    	checkbox_facturaValorFiscal1.setSelected(false);
    	
    	Alert a = new Alert(AlertType.INFORMATION, "Operaci�n satisfactoria");
    	a.showAndWait();
    }
    
    public void clienteSelect(ActionEvent event) {
    	
    	Cliente cliente = Controladora.getInstance().buscarCliente(textfield_clienteSeleccionado.getText());
    	if(tab_facturarCredito.isSelected()) {
    		textfield_buscarClienteFacturaCredito.setText(textfield_clienteSeleccionado.getText());
        	textfield_clienteNombreCredito.setText(cliente.getNombre());
        	textfield_clienteRNCCredito.setText(cliente.getRnc());
        	textfield_clienteCreditoCredito.setText(Float.toString(cliente.getCredito()));
        	textfield_clienteDeudaCredito.setText(Float.toString(cliente.getDeuda()));
        	
        	/**ObservableList<String> facturaData = FXCollections.observableArrayList();
        	for(Factura factura : Controladora.getInstance().getMisFacturas()) {
        		if(factura.getClienteCodigo().equalsIgnoreCase(cliente.getCodigo())) {
        			facturaData.add(")
        		}
        	}**/
        	Factura factura = null;
        	float balanceOriginal = 0;
        	for(Factura f : Controladora.getInstance().getMisFacturas()) {
        		if(f.getClienteCodigo().equalsIgnoreCase(cliente.getCodigo())) {
        			if(factura == null) {
        				factura = f;
        			}
        			if(factura.getFecha().isAfter(f.getFecha())) {
        				factura = f;
        			}
        			balanceOriginal += factura.getMontoTotal();
        			
        			if(f.getEstado().equalsIgnoreCase("Pendiente"))
        			{
        				listview_clienteDeuda.getItems().add(f.getCodigo() + " Pendiente: " + f.getAdeudado() + "$");
        			}
        		}
        		
        	}
        	if(factura != null) {
        		//textfield_fechaSolicitud.setText(factura.getFecha().toString());
        		//textfield_balanceOriginal.setText(Float.toString(balanceOriginal));
        		//textfield_ultimoPago.setText(cliente.getUltimaActualizacionDeuda().toString());
        		//textfield_creditoBalancePendiente.setText(Float.toString(cliente.getDeuda()));
        	}
        	
        	
        	
    	}
    	else if(tab_facturarProducto.isSelected()) {
    		textfield_buscarClienteFactura.setText(textfield_clienteSeleccionado.getText());
    		textfield_clienteNombre.setText(cliente.getNombre());
    		textfield_clienteRNC.setText(cliente.getRnc());
    		textfield_clienteCredito.setText(Float.toString(cliente.getCredito()));
    		textfield_clienteDeuda.setText(Float.toString(cliente.getDeuda()));
    	}
    	
    	cerrarBusquedaCliente(event);
    }
    
    public void setSpinnersConfiguracion() {
    	SpinnerValueFactory<Integer> copias = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999999999, 1);
		spinner_cantcopias.setValueFactory(copias);
    }
    
    public void guardarFactura(ActionEvent event) {
    	Alert a2 = new Alert(AlertType.CONFIRMATION, "Confirmar compra", ButtonType.YES, ButtonType.NO);
    	a2.showAndWait();
    	
    	if(a2.getResult() == ButtonType.YES)
    	{
    		
    	Cliente cliente = null;
    	for(Cliente c : Controladora.getInstance().getMisClientes()) {
    		if(c.getCodigo().equalsIgnoreCase(textfield_buscarClienteFactura.getText())) {
    			cliente = c;	
    		}
    	}
    	
    	float montoTotal = Float.parseFloat(textfield_totalAPagar.getText());
    	float montoRecibido = 0;
    	float montoCambio = 0;
    	
    	if(radiobutton_facturaEfectivo.isSelected())
    	{
    		montoRecibido = Float.parseFloat(textfield_totalRecibido.getText());
        	montoCambio = Float.parseFloat(textfield_totalCambio.getText());
    	}
    	
    	String tipoPago = "";
    	int cantcopias = spinner_cantcopias.getValue();
    	
    	if(radiobutton_facturaEfectivo.isSelected()) {
    		tipoPago = radiobutton_facturaEfectivo.getText();
    	}
    	else if(radiobutton_facturaTarjeta.isSelected()) {
    		tipoPago = radiobutton_facturaTarjeta.getText();
    	}
    	else if(radiobutton_facturaCredito.isSelected()) {
    		tipoPago = radiobutton_facturaCredito.getText();
    	}
    	ArrayList<String> alreadyUsed = new ArrayList<>();
    	ArrayList<String> serialAlreadyUsed = new ArrayList<>();
    	ArrayList<CantProductosUtilizados> prodFacturados = new ArrayList<>();
    	ArrayList<CantKitsUtilizados> kitFacturados = new ArrayList<>();
    	ArrayList<ServicioUtilizado> serviciosFacturados = new ArrayList<>();
    	
    	for(String items : listview_productosFacturados.getItems()) {
    		CantProductosUtilizados cantidadUtilizados = null;
    		CantKitsUtilizados cantidadKitUtilizados = null;
    		ServicioUtilizado cantidadServicioUtilizados = null;
    		String nombreItem = Controladora.getInstance().findFacturaNombre(items);
    		String serialItem = "";
    		if(Controladora.getInstance().buscarProducto(nombreItem).getTipoProducto().equalsIgnoreCase("Matriz")) {
    			serialItem = Controladora.getInstance().findFacturaNumeroSerie(items);
    		}
    		System.out.println("El item antes de entrar es " + nombreItem + " y el serial es " + serialItem);
    		if(!alreadyUsed.contains(nombreItem)) {
    			System.out.println("Ingrese aqui con el item: " + nombreItem + " y el serial " + serialItem); 
    			Producto producto = Controladora.getInstance().buscarProducto(nombreItem);
    			float cantidad = 0;
    			if(producto.getUnidadMedida() == null && !producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    				for(String searchSame : listview_productosFacturados.getItems()) {
    					String searchSameName = Controladora.getInstance().findFacturaNombre(searchSame);
    					if(searchSameName.equalsIgnoreCase(nombreItem) && !producto.getTipoProducto().equalsIgnoreCase("Matriz")) {			
    							cantidad++;
    					}
    					else if(producto.getTipoProducto().equalsIgnoreCase("Matriz") && !serialAlreadyUsed.contains(serialItem)) {
    						//String serialItem = Controladora.getInstance().findFacturaNumeroSerie(items);
    						String searchSameSerial = Controladora.getInstance().findFacturaNumeroSerie(searchSame);
    						if(searchSameSerial.equalsIgnoreCase(serialItem)) {
    							cantidad++;
    						}
    					}
    				}
    				serialAlreadyUsed.add(serialItem);
    				alreadyUsed.add(nombreItem);
    			}
    			else {
    				float precioConvertido = Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));
    				float precio = producto.getPrecio();
    				cantidad = precioConvertido / precio;
    			}
    			
    			if(producto.getTipoProducto().equalsIgnoreCase("Estandar") || producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
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
    			
    			
    			if(producto.getTipoProducto().equalsIgnoreCase("Estandar")) {
    				Estandar estandar = (Estandar) producto;
    				int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(estandar)+1;
    				float cantidadRestar = estandar.getExistenciaActual() - cantidad;
    				
    				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
    				Controladora.getInstance().getMisProductosEstandar().get(indiceProducto-1).setExistenciaActual(cantidadRestar);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    				Estandar matriz = (Estandar) producto;
    				int indiceProducto = Controladora.getInstance().getProductoEstandarIndice(matriz)+1;
    				System.out.println("El indice del producto tipo matriz es: " + indiceProducto);
    				float cantidadRestar = matriz.getExistenciaActual() - cantidad;
    				Controladora.getInstance().restarExistenciaActual(cantidadRestar, indiceProducto);
    				
    				for(Combinaciones c : Controladora.getInstance().getMisProductosEstandar().get(indiceProducto-1).getCombinaciones()) {
    					if(c.getNumeroSerie().equalsIgnoreCase(Controladora.getInstance().findFacturaNumeroSerie(items))) {
    						System.out.println("Estoy en la combinacion con la serie: " + c.getNumeroSerie());
    						int indiceCombinacion = Controladora.getInstance().getCombinacionesIndice(c)+1;	
    						Controladora.getInstance().restarExistenciaActualMatriz(c.getExistenciaActual()-cantidad, indiceCombinacion);
    						System.out.println("La existencia actual de este producto antes de es: " + c.getExistenciaActual());
    						c.setExistenciaActual(c.getExistenciaActual()-cantidad);
    						System.out.println("La existencia actual de este producto despues de es: " + c.getExistenciaActual());
    					}
    				}
    				Controladora.getInstance().getMisProductosEstandar().get(indiceProducto-1).setExistenciaActual(cantidadRestar);
    			}
    			else if(producto.getTipoProducto().equalsIgnoreCase("Kit")) {
    				Kit kit = (Kit) producto;
    				int indiceProducto = Controladora.getInstance().getProductoKitIndice(kit)+1;
    				float cantidadRestar = kit.getExistenciaActual() - cantidad;
    				Controladora.getInstance().restarExistenciaActualKit(cantidadRestar, indiceProducto);
    				Controladora.getInstance().getMisProductosKit().get(indiceProducto-1).setExistenciaActual(cantidadRestar);
    			}
    		}
    	}
    	
    	String tipoFactura = null;
    	
    	if(checkbox_facturaValorFiscal.isSelected())
    	{
    		tipoFactura = "01";
    	}
    	if(!checkbox_facturaValorFiscal.isSelected())
    	{
    		tipoFactura = "02";
    	}
    	
    	Factura factura;
    	
    	if(radiobutton_facturaCredito.isSelected()) {
    		
    		String estado = "Pendiente";
    		float adeudado = Float.parseFloat(textfield_balancependiente.getText());
    		int plazoPagoDias = 0;
    		cliente.setDeuda(cliente.getDeuda() + adeudado);
    		cliente.setUltimaActualizacionDeuda(LocalDate.now());
    		Controladora.getInstance().guardarClienteDeudaSQL(cliente);
    		if(radiobutton_15Dias.isSelected())
    		{
    			plazoPagoDias = 15;
    		}
    		else if(radiobutton_30Dias.isSelected())
    		{
    			plazoPagoDias = 30;
    		}
    		else if(radiobutton_60Dias.isSelected())
    		{
    			plazoPagoDias = 60;
    		}
    		else if(radiobutton_90Dias.isSelected())
    		{
    			plazoPagoDias = 90;
    		}
    		
    		float porcientoDescuento;
    		
    		if(!textfield_descuento.getText().isEmpty())
    		{
    			porcientoDescuento = Float.parseFloat(textfield_descuento.getText());
    		}
    		else
    		{
    			porcientoDescuento = 0;
    		}
    		
    		LocalDate fechaLimiteDescuento = null;
    		
    		if(!datepicker_fechaDePago.equals(null))
    		{
    			fechaLimiteDescuento = datepicker_fechaDePago.getValue();
    		}
    		
    		float porcientoPenalizacion;
    		
    		if(!textfield_penalizacion.getText().isEmpty())
    		{
    			porcientoPenalizacion = Float.parseFloat(textfield_penalizacion.getText());
    		}
    		else
    		{
    			porcientoPenalizacion = 0;
    		}
    		
    		montoRecibido = Float.parseFloat(textfield_pagorecibido.getText());
    		
    		factura = new Factura(prodFacturados, kitFacturados, serviciosFacturados, montoTotal, tipoPago, montoRecibido, 0, cliente, tipoFactura, cantcopias, estado, adeudado, plazoPagoDias, porcientoDescuento, fechaLimiteDescuento, porcientoPenalizacion);
    		factura.setMontoDelUltimoPago(Float.parseFloat(textfield_pagorecibido.getText()));
    		factura.getPagosDeuda().add(Float.parseFloat(textfield_pagorecibido.getText()));
    		factura.setUsuarioFacturador(Controladora.getInstance().getUsuarioLogueado());
    		Controladora.getInstance().getMisFacturas().add(factura);
        	Controladora.getInstance().guardarFacturaSQL(factura, tipoFactura);
        	Controladora.getInstance().guardarFacturaCreditoClienteSQL(factura);
        	Controladora.getInstance().guardarNuevoPagoDeuda(factura);
    	}
    	
    	else
    	{
    		String estado = "Saldado";
    		factura = new Factura(prodFacturados, kitFacturados, serviciosFacturados, montoTotal, tipoPago, montoRecibido, montoCambio, cliente, tipoFactura, cantcopias, estado);
    		factura.setUsuarioFacturador(Controladora.getInstance().getUsuarioLogueado());
    		Controladora.getInstance().getMisFacturas().add(factura);
        	Controladora.getInstance().guardarFacturaSQL(factura, tipoFactura);
    	}
    	
    	
    	for(CantProductosUtilizados c : prodFacturados) {
    		//System.out.println("En esta parte se esta guardando: " + c.getNombre());
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
    	
    	fillProductos(null);
    	listview_productosFacturados.getItems().clear();
    	listview_productosFacturados.refresh();
    	textfield_totalAPagar.setText("");
    	textfield_totalRecibido.setText("");
    	textfield_totalCambio.setText("");
    	spinner_cantcopias.getValueFactory().setValue(1);
    	
    	textfield_descuento.setText("");
    	datepicker_fechaDePago.setValue(null);
    	textfield_penalizacion.setText("");
    	textfield_pagorecibido.setText("");
    	textfield_balancependiente.setText("");
    	
    	textfield_buscarClienteFactura.setText("");
		textfield_clienteNombre.setText("");
		textfield_clienteRNC.setText("");
		textfield_clienteCredito.setText("");
		textfield_clienteDeuda.setText("");
    	
    	
    	//System.out.print(Controladora.getInstance().getMisFacturas().size());
    	if(checkbox_facturaValorFiscal.isSelected())
    	{
    		FacturaValorFiscal.CrearFactura(factura);
    	}
    	if(!checkbox_facturaValorFiscal.isSelected())
    	{
    		FacturaDeConsumo.CrearFactura(factura);
    	}
    	checkbox_facturaValorFiscal.setSelected(false);
    	radiobutton_15Dias.setSelected(false);
    	radiobutton_30Dias.setSelected(false);
    	radiobutton_60Dias.setSelected(false);
    	radiobutton_90Dias.setSelected(false);
    	radiobutton_facturaEfectivo.setSelected(true);
    	radiobutton_facturaCredito.setSelected(false);
    	radiobutton_facturaTarjeta.setSelected(false);
    	
    	tipoPago(null);
    	Alert a = new Alert(AlertType.INFORMATION, "Operaci�n satisfactoria");
    	a.showAndWait();
    	}
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
    			combobox_facturaMedida.getItems().addAll("Pulgadas Cb", "Pies  Cb", "Yardas Cb", "Cuchara de t�", "Cuchara de madera", "Onza fluida", "Taza", "Medio litro", "Cuarto de gal�n", "Gal�n", "Barril", "Mil�metros cb", "Cent�metros cb", "Metros cb", "Mililitros", "Litros");
    		}
    		if(p.getUnidadMedida() == null) {
    			combobox_facturaMedida.getItems().clear();
    		}
    		else {
    			combobox_facturaMedida.getSelectionModel().select(p.getUnidadMedida().getNombre());
    		}
    		
    	}
    }
    
    public void calcularDeuda(KeyEvent event)
    {
    	float recibido = 0;
    	float total = 0;
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	if(event != null) {
    		if(event.getCode().equals(KeyCode.BACK_SPACE)) {
    			recibido = Float.parseFloat(textfield_pagorecibido.getText());
    			total = Float.parseFloat(textfield_totalAPagar.getText());
    		}
    		else if(!event.getCode().equals(KeyCode.BACK_SPACE) && event.getSource().equals(textfield_pagorecibido)) {
    			recibido = Float.parseFloat(textfield_pagorecibido.getText() + event.getCharacter());
    			total = Float.parseFloat(textfield_totalAPagar.getText());
    		}
    	}
    	else {
    		recibido = Float.parseFloat(textfield_pagorecibido.getText());
			total = Float.parseFloat(textfield_totalAPagar.getText());
    	}
    	
    	float deuda = Math.abs(recibido - total);
    	
    	if(deuda <= total) {
    		textfield_balancependiente.setText(Float.toString(deuda));
    	}
    	else {
    		textfield_balancependiente.setText(Float.toString(0));
    	}
    	
    }
    
    
    public void calcularCambio(KeyEvent event) {
    	float recibido = 0;
    	float total = 0;
    	Alert a = new Alert(AlertType.NONE); 
    	if(tab_facturarProducto.isSelected()){
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
    	}
    	else if(tab_facturarCredito.isSelected()) {
    		if(event != null) {
    			if(event.getCode().equals(KeyCode.BACK_SPACE)) {
    				recibido = Float.parseFloat(textfield_creditoRecibido.getText());
    				total = Float.parseFloat(textfield_creditoBalancePendiente.getText());
    			}
    			else if(!event.getCode().equals(KeyCode.BACK_SPACE) && event.getSource().equals(textfield_creditoRecibido)) {
    				recibido = Float.parseFloat(textfield_creditoRecibido.getText() + event.getCharacter());
    				total = Float.parseFloat(textfield_creditoBalancePendiente.getText());
    			}
    		}
    		else {
    			recibido = Float.parseFloat(textfield_creditoRecibido.getText());
    			total = Float.parseFloat(textfield_creditoBalancePendiente.getText());
    		}
    	}
    	a.setAlertType(AlertType.ERROR);
    	
    	
    	float cambio = recibido - total;
    	if(cambio < 0) {
    		if(tab_facturarProducto.isSelected()) {
    			textfield_totalCambio.setStyle("-fx-text-inner-color: red;");
    			textfield_totalCambio.setText(Float.toString(cambio));
    		}
    		else if(tab_facturarCredito.isSelected()) {
    			textfield_creditoCambio.setText("0");
    		}
    		float nuevoBalance = total - recibido;
    		textfield_nuevoBalance.setText(Float.toString(nuevoBalance));
    	}
    	else {
    		if(tab_facturarProducto.isSelected()) {
    			textfield_totalCambio.setStyle("-fx-text-inner-color: black;");
    			textfield_totalCambio.setText(Float.toString(cambio));
    		}
    		else if(tab_facturarCredito.isSelected()) {
    			textfield_creditoCambio.setText(Float.toString(cambio));
    		}
    		textfield_nuevoBalance.setText("0");
    	}
    }
    
    public void sendProducto(MouseEvent event) {
    	Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
    	boolean isValid = true;
    	String item = listview_facturaProductoList.getSelectionModel().getSelectedItem();
    	String serie = "";
    	//System.out.println("El nombre es " + Controladora.getInstance().findFacturaNombre(item));
    	Producto producto = Controladora.getInstance().buscarProducto(Controladora.getInstance().findFacturaNombre(item));
    	float precioConvertido = 0;
    	float cantidadCheck = Float.parseFloat(textfield_facturaCantidad.getText());
    	if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		serie = Controladora.getInstance().findFacturaNumeroSerie(item);
    	}
    	for(String items : listview_productosFacturados.getItems()) {
    		if(producto.getNombre().equalsIgnoreCase(Controladora.getInstance().findFacturaNombre(items)) && !producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    			cantidadCheck++;
    		}
    		else {			
    			System.out.println("El valor de la serie en donde suma la cantidad check es " + serie);
    			if(serie.equalsIgnoreCase(Controladora.getInstance().findFacturaNumeroSerie(items))) {
    				cantidadCheck++;
    			}
    		}
    	}
    	System.out.println("El cantidad check es: " + cantidadCheck);
    	isValid = checkExistenciaMinima(producto, cantidadCheck, serie);
    	
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
        		case "Cuchara de t�":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Cuchara de t�", Float.parseFloat(textfield_facturaCantidad.getText()));
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
        		case "Cuarto de gal�n":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Cuarto de gal�n", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Gal�n":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Gal�n", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Barril":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Barril", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Mil�metros cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Mil�metros cb", Float.parseFloat(textfield_facturaCantidad.getText()));
        			break;
        		case "Cent�metros cb":
        			cantidadConvertida = producto.getUnidadMedida().Conversion("Cent�metros cb", Float.parseFloat(textfield_facturaCantidad.getText()));
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
        	isValid = checkExistenciaMinima(producto, cantidadCheck, serie);
        	if(isValid) {
        		precioConvertido = cantidadConvertida * Float.parseFloat(Controladora.getInstance().findFacturaCosto(item));
        		String itemNombre = Controladora.getInstance().findFacturaNombre(item);
        		String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        		String dia = turnDaysInSpanish(dayOfWeek);
				//float precioPromocion = 0;
        		Boolean hasPromotion = (Boolean) checkPromotion(producto, dia).get(0);
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
								itemMedida += ": " + cantidadConvertida + "$" +  "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")" + " (Promoci�n)";
							}
						}
					}
					else {
						itemMedida = itemNombre + ": " + precioConvertido + "$ " + "(" + cantidadConvertida + " " + producto.getUnidadMedida().getNombre() + ")" + " (Promoci�n)";
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
    }
    
    public void returnProducto(MouseEvent event) {
    	String producto = listview_productosFacturados.getSelectionModel().getSelectedItem();
    	listview_productosFacturados.getItems().remove(producto);
    	listview_productosFacturados.refresh();
    	float precio = 0;
    	for(String items : listview_productosFacturados.getItems()) {
    		precio += Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));	
    	}
    	textfield_totalAPagar.setText(Float.toString(precio));
    	textfield_balancependiente.setText(Float.toString(precio));
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
    
    public void sendProductsToMain(ActionEvent event) {
    	ObservableList<String> selectedItems = listview_productosFacturados.getItems();
    	listview_productosFacturadosMain.setItems(selectedItems);
    	listview_productosFacturadosMain.refresh();
    	titledpane_busquedaProductos.setVisible(false);
    	float precio = 0;
    	for(String items : listview_productosFacturadosMain.getItems()) {
    		System.out.println(Controladora.getInstance().findFacturaCosto(items));
    		precio += Float.parseFloat(Controladora.getInstance().findFacturaCosto(items));
    	}
    	textfield_totalAPagar.setText(Float.toString(precio));
    	listview_facturaProductoList.getSelectionModel().clearSelection();
    	combobox_facturaMedida.getItems().clear();
    	textfield_facturaCantidad.setText("1");
    	calcularCambio(null);
    	
    	titledpane_busquedaProductos.setVisible(false);
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
		
		setSpinnersConfiguracion();
		
	}
	
	public void fillProductos(ArrayList<Producto> producto) {
		ObservableList<String> data = FXCollections.observableArrayList();
		String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
		String dia = turnDaysInSpanish(dayOfWeek);
		if(producto == null) {
			for(Producto p : Controladora.getInstance().getMisProductos()) {
				//El objeto precio_hasPromotion contiene el boolean que indica si el producto aplica la promocion y el precio de dicha promocion
				ArrayList<Object> precio_hasPromotion = checkPromotion (p, dia);
				Boolean hasPromotion = (Boolean) precio_hasPromotion.get(0);
				float precioPromocion = (float) precio_hasPromotion.get(1);
				
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
								data.add(p.getNombre() + ": " + precioPromocion + "$" + " (Promoci�n)");
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
								data.add(p.getNombre() + ": " + precioPromocion + "$ (" + p.getUnidadMedida().getNombre() + ")" + " (Promoci�n)");
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
    		for(Cliente cliente : Controladora.getInstance().getMisClientes()) {
    			if(!cliente.isBorrado()) {
    				data.add(cliente);
    			}
    		}
    	}
    	else {
    		for(Cliente cliente : c) {
    			if(!cliente.isBorrado()) {
    				data.add(cliente);
    			}
    		}
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
	
	public void fillPeticion() {
    	ObservableList<Peticion> data = FXCollections.observableArrayList();
    	for(Peticion peticion : Controladora.getInstance().getMisPeticiones()) {
    		if(peticion.getMetodoPago().equalsIgnoreCase("Credito") && peticion.getAdeudado() > 0) {
    			data.add(peticion);
    		}
	    }
	   	tablecolumn_facturaPeticionCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	   	tablecolumn_facturaPeticionProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorCodigo"));
	   	tablecolumn_facturaPeticionProducto.setCellValueFactory(new PropertyValueFactory<>("productoNombre"));
	   	tablecolumn_facturaPeticionCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    	tablecolumn_facturaPeticionMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
    	tablecolumn_facturaPeticionMetodo.setCellValueFactory(new PropertyValueFactory<>("metodoPago"));
	    //tablecolumn_facturaPeticionEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
	   	tablecolumn_facturaPeticionFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

	   	tableview_facturaPeticionList.setItems(data);
	   	tableview_facturaPeticionList.refresh();
	}

	public void pressed_tableviewFacturaPeticionList(MouseEvent event) {
		Peticion peticion = tableview_facturaPeticionList.getSelectionModel().getSelectedItem();
		button_facturaPeticionSeleccionar.setDisable(false);
		textfield_facturaPeticionSeleccionada.setText(peticion.getCodigo());
	}

	public void selectPeticion(ActionEvent event) {
		Peticion peticion = tableview_facturaPeticionList.getSelectionModel().getSelectedItem();
		textfield_buscarPeticionFactura.setText(peticion.getCodigo());
		textfield_facturaPeticionProducto.setText(peticion.getProductoNombre());
		textfield_facturaPeticionProveedor.setText(peticion.getProveedorCodigo());
		textfield_facturaPeticionCantidad.setText(Float.toString(peticion.getCantidad()));
		textfield_facturaPeticionMonto.setText(Float.toString(peticion.getMonto()));
		textfield_facturaPeticionMontoAdeudado.setText(Float.toString(peticion.getAdeudado()));

		cerrarBusquedaPeticion(event);
	}
	
	public void checkEfectivoTarjeta(ActionEvent event) {
		if(radiobutton_peticionFacturaEfectivo.isSelected()) {
			radiobutton_peticionFacturaTarjeta.setSelected(false);
		}
		else if(radiobutton_peticionFacturaTarjeta.isSelected()) {
			radiobutton_peticionFacturaEfectivo.setSelected(false);
		}
	}
	
	public void guardarFacturaPeticion(ActionEvent event) {
		boolean canRegister = true;
		if(datepicker_peticionFacturaFecha.equals(null) || textfield_peticionFacturaTotalPagado.getText().equalsIgnoreCase("") || textfield_buscarPeticionFactura.getText().equalsIgnoreCase("")) {
			Alert a = new Alert(AlertType.WARNING, "Faltan datos para completar la factura");
			a.showAndWait();
			canRegister = false;
		}
		if(canRegister) {
			Peticion peticion = Controladora.getInstance().buscarPeticion(textfield_buscarPeticionFactura.getText());
			float monto = Float.parseFloat(textfield_peticionFacturaTotalPagado.getText());
			String tipoPago = null;
			if(radiobutton_peticionFacturaEfectivo.isSelected())
			{
				tipoPago = "Efectivo";
			}
			else if(radiobutton_peticionFacturaTarjeta.isSelected())
			{
				tipoPago = "Tarjeta";
			}
			peticion.setAdeudado(peticion.getAdeudado() - monto);
			Controladora.getInstance().guardarPagoPeticionesCreditoSQL(peticion, monto, tipoPago);
			Controladora.getInstance().pagarDeudaPeticion(peticion, monto);
			
			new Alert(AlertType.INFORMATION, "Operaci�n Satisfactoria").showAndWait();
			textfield_buscarPeticionFactura.setText("");
			textfield_facturaPeticionProducto.setText("");
			textfield_facturaPeticionProveedor.setText("");
			textfield_facturaPeticionCantidad.setText("");
			textfield_facturaPeticionMonto.setText("");
			textfield_facturaPeticionMontoAdeudado.setText("");
			textfield_peticionFacturaTotalPagado.setText("");
			textfield_peticionFacturaNCF.setText("");
			datepicker_peticionFacturaFecha.setValue(null);
			
			radiobutton_peticionFacturaEfectivo.setSelected(false);
			radiobutton_peticionFacturaTarjeta.setSelected(false);

		}
	}

	
	public void setDatePickers() {
		datepicker_fechaDePago.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		datepicker_peticionFacturaFecha.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) > 0 );
	        }
	    });
	}
	
	public boolean checkExistenciaMinima(Producto producto, float cantidadCheck, String serial) {
		boolean isValid = true;
		Alert a = new Alert(AlertType.NONE); 
    	a.setAlertType(AlertType.ERROR);
		if(producto.getTipoProducto().equalsIgnoreCase("Estandar")) {
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
    	else if(producto.getTipoProducto().equalsIgnoreCase("Matriz")) {
    		Estandar estandar = (Estandar) producto;
    		System.out.println("Detecte que el producto es una matriz");
    		for(Combinaciones c : estandar.getCombinaciones()) {
    			System.out.println("El serial es: " + serial);
    			System.out.println("El numero de serie es" + c.getNumeroSerie());
    			if(c.getNumeroSerie().equalsIgnoreCase(serial)) {
    				System.out.println("El valor de cantidad check aqui es: " + cantidadCheck);
    				System.out.println("El valor de la existencia actual es: " + c.getExistenciaActual());
    				if(cantidadCheck > c.getExistenciaActual()) {
    					a.setContentText("Esta sobrepasando la existencia minima de un producto!");
    	    			a.show();
    					isValid = false;
    				}
    			}
    		}
    	}
		return isValid;
	}
	
	public void abrirBusquedaProductos(ActionEvent event) {
		titledpane_busquedaProductos.setVisible(true);
	}
	
	public void cerrarBusquedaProductos(ActionEvent event) {
		titledpane_busquedaProductos.setVisible(false);
	}
	
	public void tipoPago(ActionEvent event) {
		if(radiobutton_facturaEfectivo.isSelected()) {
			paneEfectivo.setVisible(true);
			paneCredito.setVisible(false);
			paneTarjeta.setVisible(false);
		}
		
		if(radiobutton_facturaCredito.isSelected()) {
			paneEfectivo.setVisible(false);
			paneCredito.setVisible(true);
			paneTarjeta.setVisible(false);
		}
		
		if(radiobutton_facturaTarjeta.isSelected()) {
			paneEfectivo.setVisible(false);
			paneCredito.setVisible(false);
			paneTarjeta.setVisible(true);
		}
		
	}
	
	public void abrirBusquedaPeticion(ActionEvent event) {
		fillPeticion();
		titledpane_busquedaPeticiones.setVisible(true);
	}
	
	public void cerrarBusquedaPeticion(ActionEvent event) {
		tableview_facturaPeticionList.getSelectionModel().clearSelection();
		textfield_facturaPeticionSeleccionada.setText("");
		button_facturaPeticionSeleccionar.setDisable(true);
		titledpane_busquedaPeticiones.setVisible(false);
	}
	
	public String turnDaysInSpanish(String dayOfTheWeek) {
		String dia = "";
		switch (dayOfTheWeek) {
  	  		case "MONDAY":
  	  			dia = "lunes";
  	  			break;
  	  		case "TUESDAY":
  	  			dia = "martes";
  	  			break;
  	  		case "WEDNESDAY":
  	  			dia = "miercoles";
  	  			break;
  	  		case "THURSDAY":
  	  			dia = "jueves";
  	  			break;
  	  		case "FRIDAY":
  	  			dia = "viernes";
  	  			break;
  	  		case "SATURDAY":
  	  			dia = "sabado";
  	  			break;
  	  		case "SUNDAY":
  	  			dia = "domingo";
  	  			break;
		}
		return dia;
	}
	
	public ArrayList<Object> checkPromotion(Producto p, String dia) {
		ArrayList<Object> has_precio_promocion = new ArrayList<>();
		float precioPromocion = 0;
		boolean hasPromotion = false;
		for(Promocion promocion : Controladora.getInstance().getMisPromociones()) {
			if(!promocion.isBorrado()) {
				for(Producto promoProductos : promocion.getProductos()) {
					if(promoProductos.equals(p)) {
						if(promocion.getFechaInicio() == null) {
							if(promocion.getDia().equalsIgnoreCase(dia)) {
								hasPromotion = true;
							}
						}
						else {
							if((LocalDate.now().compareTo(promocion.getFechaInicio()) >= 0 && LocalDate.now().compareTo(promocion.getFechaFinal()) <= 0
									&& LocalTime.now().compareTo(promocion.getHoraInicio()) >= 0 && LocalTime.now().compareTo(promocion.getHoraFinal()) <= 0)) {
								hasPromotion = true;
							}
						}
						if(hasPromotion) {precioPromocion = ((100-promocion.getPorcentajeDescuento())*p.getPrecio()) / 100;}
					}
				}
			}
		}
		has_precio_promocion.add(hasPromotion);
		has_precio_promocion.add(precioPromocion);
		return has_precio_promocion;
	}
	
	public void openTabFacturaPeticion() {
		tabpane_factura.getSelectionModel().select(tab_facturarPeticion);
	}

}
