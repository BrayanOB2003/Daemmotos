package ui;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Product;
import model.Shop;

public class InventoryController {
	
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
    
    private Shop shop;
    
    public InventoryController(Shop shop) {
    	this.shop = shop;
    	
    }
    
    public void loadInventory() {
    	
    	ObservableList<Product> data = FXCollections.observableArrayList(shop.getProducts());
    	 
    	columnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	columnSales.setCellValueFactory(new PropertyValueFactory<>("sales"));
    	
        tableInventory.setItems(data);
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
    		loadInventory();
    	}
    }
}
