package application;

import java.time.LocalDate;
import java.util.ResourceBundle;
import BLL.ControladorBLL;
import DAO.dto.ClienteDTO;
import UTIL.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControladorCrearCliente extends ControladorCasoDeUso{
	@FXML
	private TextField dni;
	@FXML
	private TextField nombreApellidos;
	@FXML
	private TextField direccion;
	@FXML
	private TextField anyoTC;
	@FXML
	private TextField mesTC;
	@FXML
	private TextField codigoPostal;
	@FXML
	private TextField poblacion;
	@FXML
	private DatePicker fechaCarnet;
	@FXML
	private TextField cvc;
	@FXML
	private TextField tipoTarjeta;
	@FXML
	private TextField digitosTC;
	@FXML
	private Button aceptar;
	@FXML
	private Button cancelar;
	private ClienteDTO nuevoCliente;
	private Stage dialogStage;
	
	public static boolean chequeaDNI(String dni){
		final String letra = "TRWAGMYFPDXBNJZSQVHLCKE";
		Boolean res = false;
		if (dni.length() == 9) {
			res = true;
			for (int i = 0; i < dni.length() - 1; i++) {
				res = res && Character.isDigit(dni.charAt(i));
			}
			Integer valor = new Integer(dni.substring(0, 8));
			int aux = valor % 23;
			Character letraReal = dni.charAt(8);
			Character letraCalculada = letra.charAt(aux);
			if (letraReal == letraCalculada) {
				res = true;
			}
		}
		return res;
	}
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("CREAR CLIENTE");
		cancelar.setOnAction(event -> {
			Node minodo = (Node) event.getSource();
			minodo.getScene().getWindow().hide();
		});
		aceptar.setOnAction(event -> {
			nuevoCliente=null;
			if (isInputValid()) { //Cambiar la creacion de un cliente por un clienteDTO
			nuevoCliente = new ClienteDTO(dni.getText(), nombreApellidos.getText(), direccion.getText(),
					poblacion.getText(), codigoPostal.getText(), fechaCarnet.getValue().atStartOfDay(),
					digitosTC.getText(), Integer.parseInt(mesTC.getText()),
					Integer.parseInt(anyoTC.getText()), Integer.parseInt(cvc.getText()), tipoTarjeta.getText());
			}
			if (nuevoCliente != null) {
				// Invocamos el servicio encargado de Crear un nuevo cliente

				try{
					ControladorBLL.getControlador().crearCliente(nuevoCliente);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(dialogStage);
					alert.setTitle("Cliente nuevo");
					alert.setHeaderText("Nuevo cliente creado");
					alert.setContentText(nuevoCliente.getNombreyApellidos());

					alert.showAndWait();
					Node minodo = (Node) event.getSource();
					minodo.getScene().getWindow().hide();
				}catch(BLLExcepcion e){
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(dialogStage);
					alert.setTitle("Error");
					alert.setHeaderText("");
					alert.setContentText(e.getMessage());

					alert.showAndWait();
				}catch(DAOExcepcion e){
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(dialogStage);
					alert.setTitle("Invalid Fields");
					alert.setHeaderText("Please correct invalid fields");
					alert.setContentText(e.getMessage());

					alert.showAndWait();
				}
			} 
		});
	}

	private boolean isInputValid() {
		String errorMessage = "";
		
		if (!chequeaDNI(dni.getText()) ) {
			errorMessage += "No valid dni!\n";
		}

		if (nombreApellidos.getText() == null || nombreApellidos.getText().length() == 0) {
			errorMessage += "No valid name!\n";
		}
		if (direccion.getText() == null || direccion.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}

		if (codigoPostal.getText() == null || codigoPostal.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(codigoPostal.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}
		
		if (anyoTC.getText() == null || anyoTC.getText().length() != 4 || mesTC.getText() == null || mesTC.getText().length() != 2) {
			errorMessage += "No valid year or month of credit card!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(anyoTC.getText());
				if(Integer.parseInt(anyoTC.getText()) < LocalDate.now().getYear())
					errorMessage += "No valid year of credit card add other new!\n";
				Integer.parseInt(mesTC.getText());
				if(Integer.parseInt(mesTC.getText()) <= LocalDate.now().getMonthValue() && 
						Integer.parseInt(anyoTC.getText()) < LocalDate.now().getYear())
					errorMessage += "No valid month of credit card add other new!\n";
			} catch (NumberFormatException e) {
				errorMessage += "No valid type of number!\n";
			}
		}
		
		if (cvc.getText() == null || cvc.getText().length() != 3) {
			errorMessage += "No valid CVC!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Integer.parseInt(cvc.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid type of number CVC!\n";
			}
		}
		if (digitosTC.getText() == null || digitosTC.getText().length() > 16 || digitosTC.getText().length() < 15) {
			errorMessage += "No valid credit card!\n";
		} else {
			// try to parse the postal code into an int.
			try {
				Long.parseLong(digitosTC.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid type of number credit card!\n";
			}
		}

		if (poblacion.getText() == null || poblacion.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}
		
		if (tipoTarjeta.getText() == null || tipoTarjeta.getText().length() == 0) {
			errorMessage += "No valid type of credit card!\n";
		}

		if (fechaCarnet.getValue() == null) {
			errorMessage += "No valid date of driver license!\n";
		} else {
			/*if (fechaCarnet.getValue().getMonth().getValue() > LocalDate.now().getMonth().getValue() ||
					fechaCarnet.getValue().getYear() > LocalDate.now().getYear() ||
					fechaCarnet.getValue().getDayOfMonth() >= LocalDate.now().getDayOfMonth()) {
				errorMessage += "No valid driver lisense. Use the other day!\n";
			}*/
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
