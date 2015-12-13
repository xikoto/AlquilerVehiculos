package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import UTIL.*;

public class SampleController {
	@FXML
	private MenuItem salir;
	@FXML
	private MenuItem crearr;
	@FXML
	private MenuItem crearc;
	@FXML
	private MenuItem crearcemp;
	@FXML
	private MenuItem listarrs;
	@FXML
	private MenuItem listars;
	@FXML
	private MenuItem listarc;
	@FXML
	private MenuItem entregac;
	private static final String CREAR_CLIENTE = "../application/crear-cliente.fxml";
	 private static final String LISTAR_RESERVAS_SUCURSAL = "../application/listarreservas-sucursal.fxml";
	 private static final String LISTAR_SUCURSALES = "../application/listarsucursales.fxml";
	 private static final String CREAR_RESERVA = "../application/crear-reserva.fxml";
	 private static final String LISTAR_COCHES_SUCURSAL = "../application/listarcochessucursal.fxml";
	 private static final String ENTREGAR_VEHICULO_RESERVADO = "../application/entregavehiculoreservado.fxml";
	 private Stage primaryStage;
	 @FXML
	 void listarSucursales(ActionEvent event) throws BLLExcepcion {
		Stage stage = initCasoDeUso(LISTAR_SUCURSALES,ControladorListarSucursales.class).getStage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.show();
	 }
	 @FXML
	 void crearCliente(ActionEvent event) throws BLLExcepcion {
		 Stage stage = initCasoDeUso(CREAR_CLIENTE,ControladorCrearCliente.class).getStage();
		 stage.initModality(Modality.WINDOW_MODAL);
		 stage.show();
	 }
	 @FXML
	 void crearReserva(ActionEvent event) {
		 Stage stage = initCasoDeUso(CREAR_RESERVA,ControladorCrearReserva.class).getStage();
		 stage.initModality(Modality.WINDOW_MODAL);
		 ControladorCrearReserva.setStage(primaryStage);
		 stage.show();
	 }
	 @FXML
	 void listarReservasSucursal(ActionEvent event) throws BLLExcepcion {
		 Stage stage = initCasoDeUso(LISTAR_RESERVAS_SUCURSAL,ControladorListarReservasSucursal.class).getStage();
		 stage.initModality(Modality.WINDOW_MODAL);
		 stage.show();
	 }
	 @FXML
	 void listarCochesSucursal(ActionEvent event) throws BLLExcepcion {
		 Stage stage = initCasoDeUso(LISTAR_COCHES_SUCURSAL,ControladorListarCochesSucursal.class).getStage();
		 stage.initModality(Modality.WINDOW_MODAL);
		 stage.show();
	 }
	 @FXML
	 void entregarVehiculoReservado(ActionEvent event) throws BLLExcepcion {
		 Stage stage = initCasoDeUso(ENTREGAR_VEHICULO_RESERVADO,ControladorEntregarVehiculoReservado.class).getStage();
		 stage.initModality(Modality.WINDOW_MODAL);
		 stage.show();
	 }
	 @FXML
	 void salir(ActionEvent event) {
	 Platform.exit();
	 }
	 public void setPrimaryStage(Stage primaryStage) {
	 this.primaryStage = primaryStage;
	 }
	 private <T extends ControladorCasoDeUso> T initCasoDeUso(String urlVista, Class<T> controlClass) {
			  return ControladorCasoDeUso.initCasoDeUso(urlVista, controlClass, primaryStage, SampleController.this);
			  }
}
