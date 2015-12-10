package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static final String SAMPLE = "../application/Sample.fxml";
	private Stage primaryStage;
	 private BorderPane rootLayout;
	 public static void main(String[] args) {
	 launch(args);
	 }
	 @Override
	 public void start(Stage primaryStage) {
	 this.primaryStage = primaryStage;
	 this.primaryStage.setTitle("ALQUILER DE VEHICULOS");
	 initRootLayout();
	 }
	 public void initRootLayout() {
	 try {
	 // Load root layout from fxml file.
	 FXMLLoader loader = new FXMLLoader();

	loader.setLocation(Main.class.getResource(SAMPLE));
	 rootLayout = (BorderPane) loader.load();
	 // Show the scene containing the root layout.
	 Scene scene = new Scene(rootLayout);
	 primaryStage.setScene(scene);
	 primaryStage.setResizable(false);
	 primaryStage.show();
	 SampleController controlador = loader.getController();
	 controlador.setPrimaryStage(primaryStage);
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 }
}
