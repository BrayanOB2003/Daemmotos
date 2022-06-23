package model;

import java.util.Arrays;
import java.util.List;

public class Product {
	
	private final static String SEPARATOR = ",";
	
	private String name;
	private List<String> references;
	private String code;
	private double pricePurchase;
	private double priceSale;
	private int quantity;
	private int sales;
	
	public Product(String name, List<String> references, double pricePurchase,double priceSale, int code, int quantity) {
		this.name = name;
		this.references = references;
		this.pricePurchase = (double)Math.round(pricePurchase * 100d) / 100d;
		this.quantity = quantity;
		this.code = generateCode(code);
		this.priceSale = (double)Math.round(priceSale * 100d) / 100d;
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

	public String getReferences() {
		String temp = "";
		
		for(int i = 0; i < references.size(); i++) {
			
			if(i == references.size()-1) {
				temp += references.get(i);
			} else {
				temp += references.get(i) + SEPARATOR;
			}
		}
		
		return temp;
	}

	public void setReferences(String txt) {
		txt.replace(" ", "");
		String[] references = txt.split(SEPARATOR);
		this.references = Arrays.asList(references);
	}
	
	public double getPricePurchase() {
		return pricePurchase;
	}

	public void setPricePurchase(double pricePurchase) {
		this.pricePurchase = (double)Math.round(pricePurchase * 100d) / 100d;
	}

	public double getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(double priceSale) {
		this.priceSale = (double)Math.round(priceSale * 100d) / 100d;
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
