package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Shop {
		
	private List<Product> products;
	private final static String SEPARATOR = ",";
	private final static String REFERENCE_SEPARATOR = "~";
	
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
	
	public boolean addProductImport(String name, List<String> reference, double pricePurchase, double priceSale, String code, int quantity, int sales) {
		Product newProduct = new Product(name, reference, pricePurchase, priceSale, code, quantity, sales);
		
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
	         String [] fields;
	         
	         while (line != null) {
	            fields = line.split(SEPARATOR);
	            String[] references = fields[2].split(REFERENCE_SEPARATOR);
	            
	            imported = addProductImport(fields[1],Arrays.asList(references), Double.parseDouble(fields[4]), Double.parseDouble(fields[5]),
	            		fields[0], Integer.parseInt(fields[3]), Integer.parseInt(fields[6]));
	            
	            line = br.readLine();
	            
	         }
	         br.close();
	      } catch (Exception e) {
	         
	      }
	      
	      return imported;
	}
	
	public boolean exportData(String path) {
		boolean exported = false;
		
		BufferedWriter bw = null;
		File file = new File(path);
		FileWriter write = null;
		
		try {
			write = new FileWriter(file);
			bw = new BufferedWriter(write);
			bw.write("Codigo" + SEPARATOR + "Nombre" + SEPARATOR + "Referencias" + SEPARATOR + "Cantidad" + SEPARATOR + "Precio de compra" + SEPARATOR + "Precio de venta" +
			SEPARATOR + "Unidades vendidas");
			bw.newLine();
			for(Product product: products) {
				bw.write(product.getCode() + SEPARATOR + product.getName() + SEPARATOR + product.getReferences() + SEPARATOR + 
						product.getQuantity() + SEPARATOR + product.getPricePurchase() + SEPARATOR + product.getPriceSale() + SEPARATOR + product.getSales());
				bw.newLine();
			}
			
			bw.close();
			exported = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return exported;
	}
}
