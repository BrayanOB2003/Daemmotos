package model;

import java.util.List;

public class Shop {
		
	private List<Product> products;
	
	public Shop(){
		
	}
	
	public boolean addProduct(String name, List<String> reference, double pricePurchase, int code, int quantity) {
		Product newProduct = new Product(name, reference, pricePurchase, code, quantity);
		
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
}
