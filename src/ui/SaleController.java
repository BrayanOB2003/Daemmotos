package ui;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Product;
import model.Shop;

public class SaleController {
	
	@FXML
    private TableColumn<Product, String> columnCode;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtSearchProduct;

    private Shop shop;
    private Product selectedProduct;
    private int productIndex;
    
    
    public SaleController() {
    	
    }
    
    public void loadInformation(Shop shop) {
    	this.shop = shop;
    	
    	ObservableList<Product> data = FXCollections.observableArrayList(shop.getProducts());
    	 
    	columnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
        tableProducts.setItems(data);
        tableProducts.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        	if(tableProducts.getSelectionModel().getSelectedItem() != null) {
        		selectedProduct = tableProducts.getSelectionModel().getSelectedItem();
        		productIndex = shop.getProducts().indexOf(selectedProduct);
        		
        		txtCode.setText(selectedProduct.getCode());
        		txtName.setText(selectedProduct.getName());
        	}
        });
    }
    
    private void loadInventory(List<Product> list) {
    	ObservableList<Product> data = FXCollections.observableArrayList(list);
   	 
    	columnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
    	columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
        tableProducts.setItems(data);
        tableProducts.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        	if(tableProducts.getSelectionModel().getSelectedItem() != null) {
        		selectedProduct = tableProducts.getSelectionModel().getSelectedItem();
        		productIndex = shop.getProducts().indexOf(selectedProduct);
        		
        		txtCode.setText(selectedProduct.getCode());
        		txtName.setText(selectedProduct.getName());
        	}
        });
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
    		loadInformation(shop);
    	}
    }
    
    @FXML
    public void makeSale(ActionEvent event) {
    	if() {
    		
    	}
    }
}
