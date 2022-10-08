package webhello.model;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

	private List<Product> products = new ArrayList<>();
	
	private static ProductManager instance = new ProductManager();
	public static ProductManager getInstance() {
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
}
