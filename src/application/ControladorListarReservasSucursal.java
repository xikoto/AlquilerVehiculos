package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import BLL.ControladorBLL;
import BLL.Reserva;
import BLL.Sucursal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListarReservasSucursal extends ControladorCasoDeUso {
	@FXML
	private TableView<Sucursal> sucursales;
	@FXML
	private TableColumn<Sucursal, Integer> firstNameColumn;
	@FXML
	private TableColumn<Sucursal, String> lastNameColumn;
	

	@FXML
	private TableView<Reserva> reservas;
	@FXML
	private TableColumn<Reserva, Integer> id;
	@FXML
	private TableColumn<Reserva, LocalDateTime> fechaRecogida;
	@FXML
	private TableColumn<Reserva, LocalDateTime> fechaDevolucion;
	@FXML
	private TableColumn<Reserva, String> modalidadAlquiler;
	@FXML
	private TableColumn<Reserva, String> sucursalDevolucion;
	@FXML
	private TableColumn<Reserva, String> categoria;
	@FXML
	private TableColumn<Reserva, String> cliente;
	@FXML
	private TableColumn<Reserva, String> entrega;
	@FXML
	private Button aceptar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE RESERVAS POR SUCURSAL");
		aceptar.setOnAction(event -> {
			Node minodo = (Node) event.getSource();
			minodo.getScene().getWindow().hide();
		});
		firstNameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		lastNameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDireccion()));
		this.sucursales.getItems().addAll(ControladorBLL.getControlador().listarSucursales());
		
		id.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		fechaRecogida.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getFechaRecogida()));
		fechaDevolucion.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getFechaDevolucion()));
		modalidadAlquiler.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getModalidadAlquiler()));
		sucursalDevolucion.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getSucursalDevolucion().getDireccion()));
		categoria.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getCategoria().getNombre()));
		cliente.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getCliente().getDni()));
		/*entrega.setCellValueFactory(param -> 
			new ReadOnlyObjectWrapper<>(param.getValue().getEntrega().getTipoSeguro()));*/

		sucursales.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			this.reservas.getItems().clear();
			this.reservas.getItems().addAll(ControladorBLL.getControlador().listarReservasSucursal(newValue.getId()));
		});
		
	}
}
