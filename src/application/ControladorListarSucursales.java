package application;


import java.util.ResourceBundle;

import BLL.ControladorBLL;
import BLL.Sucursal;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorListarSucursales extends ControladorCasoDeUso{
	@FXML
	 private TableView<Sucursal> sucursales;
	 @FXML
	 private TableColumn<Sucursal, Integer> id;
	 @FXML
	 private TableColumn<Sucursal, String> direccion;
	 @FXML
	 private Button aceptar;
	 @FXML
	 private void acept(ActionEvent evento){
		Node minodo = (Node) evento.getSource();
		minodo.getScene().getWindow().hide();
	 }

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE SUCURSALES");
		id.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getId()));
		direccion.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getDireccion()));
		this.sucursales.getItems().addAll(ControladorBLL.getControlador().listarSucursales());
	}
}
