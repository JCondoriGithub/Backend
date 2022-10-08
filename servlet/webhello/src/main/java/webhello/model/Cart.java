package webhello.model;

import java.util.HashMap;
import java.util.List;

public class Cart {

	private HashMap<Product, Integer> products =  new HashMap<Product, Integer>();
	
	public List<Product> getProducts() {
		return List.copyOf(products.keySet());	// ritorna in forma di lista i nomi-chiave di "products"
	}
	
	public int getQty(Product p) {
		Integer q = products.get(p);
		return q == null ? 0 : q.intValue();
	}
	
	public int getTotPrice() {
		int tot = 0;
		for(Product p: getProducts())
			tot += getQty(p) * p.getPrice();
		return tot;
	}
	
	public void add(Product p, int qty) {
		products.put(p, getQty(p) + qty);
	}
	
	public void remove(Product p) {
		products.remove(p);
	}
}
