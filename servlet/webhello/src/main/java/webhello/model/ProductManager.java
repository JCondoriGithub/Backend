package webhello.model;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

	private List<Product> products = new ArrayList<>();
	
	private static ProductManager instance = new ProductManager();	// quando si esegue il progetto, subito si genera un'istanza della classe "ProductManager"
	public static ProductManager getInstance() {	// metodo che restituisce l'istanza generata all'avvio del progetto
		return instance;
	}
	
	public ProductManager() {
		products.add(new Product(1, "scarpe", 80));
		products.add(new Product(2, "giubotto", 120));
		products.add(new Product(3, "pantaloni", 60));
	}
	
	public List<Product> getProducts() {
		return List.copyOf(products);
	}
	
	public Product getProduct(int id) {
		for(Product p: products)
			if(p.getCodeID() == id) 
				return p;
			return null;
	}
	
	public void addProduct(String name, int price) throws DuplicatedProductException {
		int lastID = 0;
		for(Product p: products) {
			if(p.getCodeID() > lastID)
				lastID = p.getCodeID();
			if(p.getName().equals(name))
				throw new DuplicatedProductException(name);
		}
		products.add(new Product(lastID+1, name, price));
	}
	
	public void remProduct(int id) {
		products.remove(getProduct(id));
	}
	
	public static class DuplicatedProductException extends Exception {
		private String name;
		
		public DuplicatedProductException(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
}
