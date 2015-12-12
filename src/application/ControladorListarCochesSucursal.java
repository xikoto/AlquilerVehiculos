package application;

import java.net.URL;
import java.util.ResourceBundle;

import BLL.Coche;
import BLL.ControladorBLL;
import BLL.Sucursal;
import UTIL.DAOExcepcion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListarCochesSucursal extends ControladorCasoDeUso{
	@FXML
	private TableView<Sucursal> sucursales;
	@FXML
	private TableColumn<Sucursal, Integer> firstNameColumn;
	@FXML
	private TableColumn<Sucursal, String> lastNameColumn;
	
	@FXML
	private TableView<Coche> coches;
	@FXML
	private TableColumn<Coche, String> matricula;
	@FXML
	private TableColumn<Coche, Double> kmsActuales;
	@FXML
	private TableColumn<Coche, String> categoria;
	
	private ObservableList<Coche> c;
	
	@FXML
	private Button aceptar;
	
	private Stage dialogStage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE COCHES POR SUCURSAL");
		aceptar.setOnAction(event -> {
			Node minodo = (Node) event.getSource();
			minodo.getScene().getWindow().hide();
		});
		firstNameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		lastNameColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDireccion()));
		this.sucursales.getItems().addAll(ControladorBLL.getControlador().listarSucursales());
		
		matricula.setCellValueFactory(cellData -> 
		new ReadOnlyObjectWrapper<>(cellData.getValue().getMatricula()));
		kmsActuales.setCellValueFactory(param -> 
		new ReadOnlyObjectWrapper<>(param.getValue().getKmsActuales()));
		categoria.setCellValueFactory(param -> 
		new ReadOnlyObjectWrapper<>(param.getValue().getCategoria().getNombre()));
		
		sucursales.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				c=FXCollections.observableList(ControladorBLL.getControlador().listarVehiculosDisponibles(newValue.getId()));
				this.coches.setItems(c);
			} catch (DAOExcepcion e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("La sucursal no tiene coches");
				alert.setHeaderText("Por favor selecciona otra sucursal");
				alert.setContentText(e.getMessage());

				alert.showAndWait();
			}
		});
		
		
	}

}
