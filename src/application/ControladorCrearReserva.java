package application;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import BLL.Categoria;
import BLL.ControladorBLL;
import BLL.Sucursal;
import DAO.dto.ReservaDTO;
import UTIL.DAOExcepcion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorCrearReserva extends ControladorCasoDeUso {
	private static final Logger LOG = Logger.getLogger(ControladorCrearReserva.class.getName());
	
	@FXML
	private ComboBox<String> modalidad;
	@FXML
	private DatePicker fechaRecogida;
	@FXML
	private DatePicker fechaDevolucion;
	@FXML
	private ComboBox<String> sucursalDevolucion;
	@FXML
	private ComboBox<String> sucursalRecogida;
	@FXML
	private ComboBox<String> categoria;
	@FXML
	private TextField dnicliente;
	@FXML
	private ComboBox<String> tiposeguro;

	@FXML
	private Button aceptar;
	@FXML
	private Button cancelar;
	private ReservaDTO nuevaReserva;
	private Stage dialogStage;
	private static final String CREAR_CLIENTE = "../application/crear-cliente.fxml";
	private static Stage primary;
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("CREAR RESERVA");
		List<Sucursal> listasucur=ControladorBLL.getControlador().listarSucursales();
		List<Categoria> listacat=ControladorBLL.getControlador().listarCategorias();
		for(Sucursal suc : listasucur){
			sucursalDevolucion.getItems().add(suc.getId()+"-"+suc.getDireccion());
			sucursalRecogida.getItems().add(suc.getId()+"-"+suc.getDireccion());
		}
		
		for(Categoria cat : listacat){
			categoria.getItems().add(cat.getNombre());
		}
		tiposeguro.getItems().add("A todo riesgo");
		tiposeguro.getItems().add("A terceros");
		
		modalidad.getItems().add("Ilimitados");
		modalidad.getItems().add("Kilometros");
		cancelar.setOnAction(event -> {
			Node minodo = (Node) event.getSource();
			minodo.getScene().getWindow().hide();
		});
		
		aceptar.setOnAction(event -> {
			boolean b = false;
			if(!ControladorBLL.getControlador().buscarCliente(dnicliente.getText())){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Cliente nuevo");
				alert.setHeaderText("Mira, el cliente no existe");
				alert.setContentText("Quieres crear uno nuevo?");
				Optional<ButtonType> result = alert.showAndWait(); 
				if(result.get() == ButtonType.OK){
					FXMLLoader loader = new FXMLLoader(ControladorCasoDeUso.class.getResource(CREAR_CLIENTE));
					Parent parent;
					try{
						parent = loader.load();
						ControladorCrearCliente c = loader.getController();
						c.stage.setScene(new Scene(parent));
						c.stage.initOwner(primary);
						c.setControladorPrincipal(controladorPrincipal);
						c.show();
						c.wait();
						b=true;
					}catch(Exception e){}
				}
			}else{
				b=true;
			}
			
			nuevaReserva=null;
			if(b)
				if (isInputValid()) {
					nuevaReserva = new ReservaDTO(0, fechaRecogida.getValue().atStartOfDay(),
							fechaDevolucion.getValue().atStartOfDay(), 
							modalidad.getSelectionModel().getSelectedItem(),
							categoria.getSelectionModel().getSelectedItem(),
							dnicliente.getText(),
							Integer.parseInt(sucursalRecogida.getSelectionModel().getSelectedItem().split("-")[0]),
							Integer.parseInt(sucursalDevolucion.getSelectionModel().getSelectedItem().split("-")[0]));
				}
			
			if (nuevaReserva != null) {
				// Invocamos el servicio encargado de Crear un nuevo cliente

				try{
					ControladorBLL.getControlador().crearReserva(nuevaReserva);
				}catch(DAOExcepcion e){
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(dialogStage);
					alert.setTitle("Error");
					alert.setHeaderText("Problema al crear la reserva");
					alert.setContentText(e.getMessage());

					alert.showAndWait();
				}
				LOG.log(Level.INFO, "Se ha creado una nueva Reserva: " + nuevaReserva);
			} else {
				LOG.log(Level.INFO, "No se ha podido crear una nueva reserva.");
			}
			if(b){
				Node minodo = (Node) event.getSource();
				minodo.getScene().getWindow().hide();
			}
		});
	}

	private boolean isInputValid() {
		String errorMessage = "";
		
		if (fechaDevolucion.getValue() == null) {
			errorMessage += "No valid date!\n";
		}
		
		if (fechaRecogida.getValue() == null) {
			errorMessage += "No valid date!\n";
		}
		
		if (sucursalDevolucion.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "No valid sucursal devolucion!\n";
		}
		
		if (sucursalRecogida.getSelectionModel().getSelectedItem() == null ) {
			errorMessage += "No valid sucursal recogida!\n";
		}
		
		if (categoria.getSelectionModel().getSelectedItem() == null ) {
			errorMessage += "No valid categoria!\n";
		}
		
		if (tiposeguro.getSelectionModel().getSelectedItem() == null ) {
			errorMessage += "No valid tipo seguro!\n";
		}
		
		if (!ControladorCrearCliente.chequeaDNI(dnicliente.getText())) {
			errorMessage += "No valid dni!\n";
		}
		
		if (modalidad.getSelectionModel().getSelectedItem() == null ) {
			errorMessage += "No valid modalidad!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}

	}
	
	public static void setStage(Stage primaryStage){
		primary=primaryStage;
	}
}
