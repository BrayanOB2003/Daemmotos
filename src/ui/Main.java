package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{
	
	private MainController mainController;
	
	public static void main(String[] args){
		
		launch(args);
	}
	
	public Main() {
		mainController = new MainController();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
		
		fxmlLoader.setController(mainController);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Dammotos");
		primaryStage.setResizable(true);
		primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e->{
			
			String path = "src/data/data.csv";
			
			mainController.exportData(path);
		});
		primaryStage.show();
	}
	
	
}
