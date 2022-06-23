package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class Shop {
		
	private List<Product> products;
	private final static String SEPARATOR = ",";
	
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
	
	public boolean importData(String path) {
		
		boolean imported = false;
		
		BufferedReader br = null;
		
		
	      try {
	    	 
	         br =new BufferedReader(new FileReader(path));
	         br.readLine();
	         String line = br.readLine();
	         
	         while (line != null) {
	            String [] fields = line.split(SEPARATOR);
	            Product pr = new Product();
	            List<String> references = new ArrayList<>();
	            references.add(fields[1]);
	            
	            imported = addProduct(fields[0],references, Double.parseDouble(fields[2]), pr.generatePriceSale(Double.parseDouble(fields[3])),
	            		Integer.parseInt(fields[4]), Integer.parseInt(fields[4]));
	            
	            line = br.readLine();
	            
	         }
	         
	      } catch (Exception e) {
	         
	      }
	      
	      return imported;
	}
}
