package model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
		
	private List<Product> products;
	
	public Shop(){
		products = new ArrayList<Product>();
	}
	
	public boolean addProduct(String name, List<String> reference, double pricePurchase, double priceSale, int code, int quantity) {
		Product newProduct = new Product(name, reference, pricePurchase, priceSale, code, quantity);
		
		try {
			products.add(newProduct);
			return true;	
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public int searchProduct(String code) {
		
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getCode().equals(code)) {
				return i;
			}
		}
		return -1;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
