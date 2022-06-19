package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MainController {

   @FXML
    private AnchorPane newScreen;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
    
    public MainController() {
		// TODO Auto-generated constructor stub
	}
    
    @FXML
    public void makeSale(ActionEvent event) {
    	
    }

    @FXML
    public void openAddNewProduct(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProductScreen.fxml"));
		
		fxmlLoader.setController(this);
		
		Parent root = fxmlLoader.load();
		
		newScreen.getChildren().clear();
		newScreen.getChildren().add(root);
    }

    @FXML
    public void openAddOldProduct(ActionEvent event) {

    }

    @FXML
    public void openInventory(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvetoryScreen.fxml"));
		
		fxmlLoader.setController(this);
		
		Parent root = fxmlLoader.load();
		
		newScreen.getChildren().clear();
		newScreen.getChildren().add(root);
    }

}
