package ui;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Product;
import model.Shop;

public class InventoryController {
	
	//View inventory
	@FXML
    private TableColumn<Product, String> columnCode;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, Integer> columnQuantity;

    @FXML
    private TableColumn<Product, Integer> columnSales;

    @FXML
    private TableView<Product> tableInventory;
    
    @FXML
    private TextField txtSearchProduct;
    
    private ViewInformationController infoController;
    
    private Shop shop;
    
    public InventoryController(Shop shop) {
    	this.shop = shop;
    	infoController = new ViewInformationController(shop);
    }
    
    public void initialize() {
    	loadInformation();
    }
    
    public void loadInformation() {
    	
    	ObservableList<Product> data = FXCollections.observableArrayList(shop.getProducts());
    	 
    	columnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	columnSales.setCellValueFactory(new PropertyValueFactory<>("sales"));
    	
        tableInventory.setItems(data);
        tableInventory.setOnMouseClicked(e->{
        	if(tableInventory.getSelectionModel().getSelectedItem() != null) {
        		try {
					showInfoScreen(shop.getProducts().indexOf(tableInventory.getSelectionModel().getSelectedItem()));
				} catch (IOException e1) {
					
				}
        	}
        });
    }
    
    private void showInfoScreen(int productIndex) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewInformation.fxml"));
		
    	
		fxmlLoader.setController(infoController);

		infoController.setIndexProduct(productIndex);
		
		Parent root = fxmlLoader.load();
		
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Producto");
		stage.setResizable(false);
		
		infoController.setIndexProduct(productIndex);
		
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e ->{
			tableInventory.refresh();
		});
		stage.showAndWait();
    }
    
    private void loadInventory(List<Product> list) {
    	ObservableList<Product> data = FXCollections.observableArrayList(list);
   	 
    	columnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	columnSales.setCellValueFactory(new PropertyValueFactory<>("sales"));
    	
        tableInventory.setItems(data);
    }
    
    @FXML
    public void typingProduct(KeyEvent event) {
    	
    	if(!txtSearchProduct.getText().isEmpty() || (!txtSearchProduct.getText().isEmpty() && event.getCode() == KeyCode.DELETE)) {
    		String characters = txtSearchProduct.getText();
    		int size = characters.length();
    		
        	List<Product> aux = shop.getProducts().stream()
        			.filter(product -> characters.length() <= product.getName().length())
        			.collect(Collectors.toList());
        			
        	List<Product> searches = aux.stream()
        			.filter(product -> characters.equalsIgnoreCase(product.getName().substring(0, size)))
        			.collect(Collectors.toList());
        	
        	loadInventory(searches);
    	} else {
    		loadInformation();
    	}
    }
    
    @FXML
    public void SaveChanges(ActionEvent event) {

    }

    @FXML
    public void activeFields(ActionEvent event) {

    }
}
