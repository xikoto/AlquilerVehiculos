package application;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ControladorCasoDeUso implements Initializable {

	protected Stage stage;
	protected SampleController controladorPrincipal;
	protected ControladorCrearReserva controladorReserva;

	public void setControladorPrincipal(SampleController controladorPrincipal) {
		this.controladorPrincipal = controladorPrincipal;
	}

	public Stage getStage() {
		return stage;
	}

	public void show() {
		stage.show();
	}

	public static <T extends ControladorCasoDeUso> T initCasoDeUso(String urlVista, Class<T> controlClass, Stage owner,	SampleController controladorPrincipal) {
		FXMLLoader fxmlLoader = new FXMLLoader(ControladorCasoDeUso.class.getResource(urlVista));
		T controlador = null;
		try {
			Parent parent = (Parent) fxmlLoader.load();
			controlador = fxmlLoader.getController();
			Scene s = new Scene(parent);
			controlador.stage.setScene(s);
			controlador.stage.initOwner(owner);
			controlador.setControladorPrincipal(controladorPrincipal);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return controlador;
	}

	@Override
	public abstract void initialize(java.net.URL arg0, ResourceBundle arg1);
}
