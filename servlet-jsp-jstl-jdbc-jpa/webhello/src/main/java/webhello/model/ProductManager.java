package webhello.model;

import java.util.List;

public abstract class ProductManager {

	public static class NotImplemented extends RuntimeException {
	}
	
	public static ProductManager getInstance() {
		return HibernateProductManager.getInstance();
	}
	
	public abstract Product add(String name, int price) throws DuplicatedProductException;
		
	/*public Product add(String name, int price, Category cat) throws DuplicatedProductException {
		throw new NotImplemented();
	}*/
	
	public abstract Product getProduct(int id);
	
	public abstract List<Product> getProducts();
	
	public abstract void remove(int code);
	
	/*public List<Category> getCategories(){
		throw new NotImplemented();
	}

	public Category getCategory(int code){
		throw new NotImplemented();
	}
	*/
	public static class DuplicatedProductException extends Exception{
		private static final long serialVersionUID = 5205260546649719848L;
		
		public DuplicatedProductException(String msg) {
			super("duplicated code:" + msg);	
			name=msg;
		}
		private String name;
		
	}
}
