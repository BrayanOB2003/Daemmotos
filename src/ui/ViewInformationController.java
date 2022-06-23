package ui;

import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import model.Product;
import model.Shop;

public class ViewInformationController {
	
	@FXML
    private Button buttonEdit;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPricePurchase;

    @FXML
    private TextField txtPriceSale;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtReferences;

    private int indexProduct;
    
    private Product product;
    
    private Shop shop;
    
    public ViewInformationController(Shop shop){
    	this.shop = shop;
    	indexProduct = -1; //Defect value
    	product = new Product();
    }
    
    public void initialize() {
    	if(indexProduct != -1) {
    		loadInformation(indexProduct);
    	}
    	buttonSave.setVisible(false);
    	initNumberFormatTextField();
    }
    
    private void initNumberFormatTextField() {   
    	UnaryOperator<Change> filter = change -> {
    	    String text = change.getText();

    	    if (text.matches("[0-9]*")) {
    	        return change;
    	    }
    	    
    	    return null;
    	};

    	
    	TextFormatter<String> formatter1 = new TextFormatter<>(filter);
    	TextFormatter<String> formatter2 = new TextFormatter<>(filter);
    	TextFormatter<String> formatter3 = new TextFormatter<>(filter);
    	
    	txtPricePurchase.setTextFormatter(formatter1);
    	txtPriceSale.setTextFormatter(formatter2);
    	txtQuantity.setTextFormatter(formatter3);
    }
    
    private void loadInformation(int index) {
    	product = shop.getProducts().get(index);
    	txtName.setText(product.getName());
    	txtPricePurchase.setText(String.valueOf(product.getPricePurchase()));
    	txtPriceSale.setText(String.valueOf(product.getPriceSale()));
    	txtQuantity.setText(String.valueOf(product.getQuantity()));
    	txtReferences.setText(product.getReferences());
    }
    
    @FXML
    public void SaveChanges(ActionEvent event) {
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        
    	if(!txtName.getText().isEmpty() && !txtPricePurchase.getText().isEmpty() && !txtPriceSale.getText().isEmpty()
				&& !txtQuantity.getText().isEmpty() && !txtQuantity.getText().isEmpty() && !txtReferences.getText().isEmpty()) {
    		String name = txtName.getText();
        	Double pricePurchase = Double.parseDouble(txtPricePurchase.getText());
        	Double priceSale = Double.parseDouble(txtPriceSale.getText());
        	Integer quantity = Integer.parseInt(txtQuantity.getText());
        	String references = txtReferences.getText();
        	
        	if(!product.getName().equals(name) || product.getPricePurchase() != pricePurchase || product.getPriceSale() != priceSale ||
        			product.getQuantity() != quantity || !product.getReferences().equals(references)) {
        		
        		product.setName(name);
        		product.setPricePurchase(pricePurchase);
        		product.setPriceSale(priceSale);
        		product.setQuantity(quantity);
        		product.setReferences(references);
        		
        		shop.getProducts().set(indexProduct, product);
        		
        		alert.setContentText("Se guardaron los cambios.");
    	        alert.showAndWait();
    	        
    	        buttonSave.setVisible(false);
    	        txtName.setStyle("");
    	        txtName.setEditable(false);
    	        
    	        txtPricePurchase.setStyle("");
    	        txtPricePurchase.setEditable(false);
    	        
    	        txtPriceSale.setStyle("");
    	        txtPriceSale.setEditable(false);
    	        
    	        txtQuantity.setStyle("");
    	        txtQuantity.setEditable(false);
    	        
    	        txtReferences.setStyle("");
    	        txtReferences.setEditable(false);
    	        
        	} else {
        		alert.setContentText("No hay ningún cambio.");
    	        alert.showAndWait();
        	}
    	} else {
    		alert.setContentText("Falta información.");
	        alert.showAndWait();
    	}
    }

    @FXML
    public void activeFields(ActionEvent event) {
    	txtName.setEditable(true);
    	txtName.setStyle("-fx-background-color: transparent");
    	
    	txtPricePurchase.setEditable(true);
    	txtPricePurchase.setStyle("-fx-background-color: transparent");
    	
    	txtPriceSale.setEditable(true);
    	txtPriceSale.setStyle("-fx-background-color: transparent");
    	
    	txtQuantity.setEditable(true);
    	txtQuantity.setStyle("-fx-background-color: transparent");
    	
    	txtReferences.setEditable(true);
    	txtReferences.setStyle("-fx-background-color: transparent");
    	
    	buttonSave.setVisible(true);
    }

	public int getIndexProduct() {
		return indexProduct;
	}

	public void setIndexProduct(int indexProduct) {
		this.indexProduct = indexProduct;
	}
}
