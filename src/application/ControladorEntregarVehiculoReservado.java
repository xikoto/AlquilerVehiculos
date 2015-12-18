package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import BLL.Coche;
import BLL.ControladorBLL;
import BLL.Empleado;
import BLL.Reserva;
import BLL.Sucursal;
import DAO.dto.EntregaDTO;
import UTIL.DAOExcepcion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorEntregarVehiculoReservado extends ControladorCasoDeUso{
	private Stage dialogStage;

	@FXML
	private ComboBox<String> nombreSucursal;
	
	@FXML
	private TableView<Reserva> reservas;
	@FXML
	private TableColumn<Reserva, Integer> id;
	@FXML
	private TableColumn<Reserva, LocalDateTime> fechar;
	@FXML
	private TableColumn<Reserva, LocalDateTime> fechad;
	@FXML
	private TableColumn<Reserva, String> cat;
	@FXML
	private TableColumn<Reserva, String> mod;
	@FXML
	private TableColumn<Reserva, String> dni;
	@FXML
	private TableColumn<Reserva, String> nombre;
	
	@FXML
	private TableView<Coche> coches;
	@FXML
	private TableColumn<Coche, String> mat;
	@FXML
	private TableColumn<Coche, Double> kms;
	@FXML
	private TableColumn<Coche, String> catc;
	
	@FXML
	private ComboBox<String> tiposeguro;
	@FXML
	private TextField kmsactuales;
	@FXML
	private TextField combustible;
	@FXML
	private ComboBox<String> empleado;
	
	
	@FXML
	private Button aceptar;
	
	private IntegerProperty idreserva = new SimpleIntegerProperty(0);
	private IntegerProperty idsucursal = new SimpleIntegerProperty(0);
	private StringProperty matriculacoche = new SimpleStringProperty("");
	LocalDateTime time;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE COCHES POR SUCURSAL");
		
		//visibilidad de los parametros de la entrega
		tiposeguro.managedProperty().bind(tiposeguro.visibleProperty());
		tiposeguro.setVisible(false);
		
		kmsactuales.managedProperty().bind(kmsactuales.visibleProperty());
		kmsactuales.setVisible(false);
		
		combustible.managedProperty().bind(combustible.visibleProperty());
		combustible.setVisible(false);
		
		empleado.managedProperty().bind(empleado.visibleProperty());
		empleado.setVisible(false);
		
		aceptar.managedProperty().bind(aceptar.visibleProperty());
		aceptar.setVisible(false);
		
		
		//Rellenar combobox sucursal
		List<Sucursal> listasucur=ControladorBLL.getControlador().listarSucursales();
		for(Sucursal suc : listasucur){
			nombreSucursal.getItems().add(suc.getId()+"-"+suc.getDireccion());
		}
		
		//Rellenar combobox tiposeguro
		tiposeguro.getItems().add("todoriesgo");
		tiposeguro.getItems().add("a terceros");
		
		//Reservas
		id.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		fechar.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaRecogida()));
		fechad.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getFechaDevolucion()));
		mod.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getModalidadAlquiler()));
		cat.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getCategoria().getNombre()));
		dni.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getCliente().getDni()));
		nombre.setCellValueFactory(
				param -> new ReadOnlyObjectWrapper<>(param.getValue().getCliente().getNombre()));
		
		//Coche
		mat.setCellValueFactory(
				cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getMatricula()));
		kms.setCellValueFactory(
				cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKmsActuales()));
		catc.setCellValueFactory(
				cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCategoria().getNombre()));
		
		//Listener de sucursal
		nombreSucursal.valueProperty().addListener((ov, t, t1)->{
			tiposeguro.setVisible(false);
			kmsactuales.setVisible(false);
			combustible.setVisible(false);
			empleado.setVisible(false);
			aceptar.setVisible(false);
			empleado.getItems().clear();
			int idSuc = Integer.parseInt(t1.split("-")[0]);
			idsucursal.set(idSuc);
			try{
				this.reservas.getItems().clear();
				this.coches.getItems().clear();
				for(Reserva r : ControladorBLL.getControlador().listarReservasPendientesEntrega(idSuc))
					this.reservas.getItems().add(r);
			}catch(DAOExcepcion e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Sucursal vacia");
				alert.setHeaderText("Por favor selecciona otra sucursal");
				alert.setContentText(e.getMessage());

				alert.showAndWait();
			}
			//Rellenar combobox empleado
			try {
				List<Empleado> listaemp = ControladorBLL.getControlador().obtenerEmpleados(idSuc);
				for(Empleado e : listaemp)
					empleado.getItems().add(e.getDni());
			} catch (DAOExcepcion e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Sucursal vacia");
				alert.setHeaderText("Por favor selecciona otra sucursal");
				alert.setContentText(e.getMessage());

				alert.showAndWait();
			}
		});
		
		//listener de reservas
		reservas.getSelectionModel().selectedItemProperty().addListener((ov, old, newV)->{
			if(newV!=null){
				idreserva.set(newV.getId());
				try{
					this.coches.getItems().clear();
					for(Coche c : ControladorBLL.getControlador().listarCochesPorCategoria(newV.getCategoria().getNombre()))
						this.coches.getItems().add(c);
				}catch(DAOExcepcion e){
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(dialogStage);
					alert.setTitle("No hay coches disponibles");
					alert.setHeaderText("No ha sido posible encontrar un coche");
					alert.setContentText(e.getMessage());

					alert.showAndWait();
				}
				catch(NullPointerException e){
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(dialogStage);
					alert.setTitle("No hay coches disponibles");
					alert.setHeaderText("No ha sido posible encontrar un coche");
					alert.setContentText("El coche de esta categoria o superior no se encuentra en la sucursal");

					alert.showAndWait();
				}
			}
		});
		
		//listener de coches
		coches.getSelectionModel().selectedItemProperty().addListener((ov, old, newV)->{
			if(newV!=null){
				tiposeguro.setVisible(true);
				kmsactuales.setVisible(true);
				combustible.setVisible(true);
				empleado.setVisible(true);
				aceptar.setVisible(true);
				matriculacoche.set(newV.getMatricula());
			}
		});
		
		aceptar.setOnAction(event -> {
			EntregaDTO e=null;
			if(isInputValid()){
				e = new EntregaDTO(idreserva.get(),
				   				   LocalDateTime.now(),
								   tiposeguro.getSelectionModel().getSelectedItem(),
								   Double.parseDouble(kmsactuales.getText()),
								   Double.parseDouble(combustible.getText()),
								   matriculacoche.get(),
								   empleado.getSelectionModel().getSelectedItem());
			}
			
			if(e!=null){
				try {
					ControladorBLL.getControlador().entregarVehiculoReservado(e,idsucursal.get());
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(dialogStage);
					alert.setTitle("Entrega nueva");
					alert.setHeaderText("Nueva entrega creada");
					alert.setContentText("Entrega con id: "+e.getId());

					alert.showAndWait();
					
					Node minodo = (Node) event.getSource();
					minodo.getScene().getWindow().hide();
				} catch (DAOExcepcion e1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(dialogStage);
					alert.setTitle("Error entrega");
					alert.setHeaderText("No ha sido posible crear la entrega");
					alert.setContentText(e1.getMessage());

					alert.showAndWait();
				}
			}
		});
	}
	private boolean isInputValid(){
		String errorMessage = "";
		
		if (tiposeguro.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "No valid tipo de seguro!\n";
		}
		
		if (empleado.getSelectionModel().getSelectedItem() == null) {
			errorMessage += "No valid empleado!\n";
		}
		
		if (kmsactuales.getText() == null) {
			errorMessage += "No valid kms actuales!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Double.parseDouble(kmsactuales.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid number of kms actuales!\n";
			}
		}
		
		if (combustible.getText() == null) {
			errorMessage += "No valid combustible!\n";
		} else {
			try {
				Double.parseDouble(combustible.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid number of combustible!\n";
			}
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
}
