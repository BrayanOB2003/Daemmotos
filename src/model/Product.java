package model;

import java.util.List;

public class Product {
	private String name;
	private List<String> references;
	private String code;
	private double pricePurchase;
	private double priceSale;
	private int quantity;
	private int sales;
	
	public Product(String name, List<String> reference, double pricePurchase,double priceSale, int code, int quantity) {
		this.name = name;
		this.references = reference;
		this.pricePurchase = pricePurchase;
		this.quantity = quantity;
		this.code = generateCode(code);
		this.priceSale = priceSale;
		this.sales = 0;
	}
	
	public Product() {
		
	}
	
	private String generateCode(int code) {
		
		String characterCode = "";
		int charactersLimit = 3;
		
		for(int i = 0; i < charactersLimit; i++) {
			characterCode += String.valueOf(name.charAt(i)).toUpperCase();
		}
		
		characterCode += String.format("%03d", code);
		
		
		return characterCode;
	}
	
	public void makeSales(int sale) {
		sales += sale;
		quantity -= sale;
	}
	
	public void makePurchase(int purchase) {
		quantity += purchase;
	}
	
	public double generatePriceSale(double pricePurchase) {
		double aux = 0;
		
		aux = (pricePurchase*0.19) + (pricePurchase*0.5) + pricePurchase;
		
		return aux;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getReferences() {
		return references;
	}

	public void setReferences(List<String> references) {
		this.references = references;
	}

	public double getPricePurchase() {
		return pricePurchase;
	}

	public void setPricePurchase(double pricePurchase) {
		this.pricePurchase = pricePurchase;
	}

	public double getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(double priceSale) {
		this.priceSale = priceSale;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSales() {
		return sales;
	}
}
