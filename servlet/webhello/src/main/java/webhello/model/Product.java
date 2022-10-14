package webhello.model;

public class Product {

	private int codeID;
	private String name;
	private int price;
	
	public Product(int codeID, String name, int price) {
		
		this.codeID = codeID;
		this.name = name;
		this.price = price;
	}
	
	public int getCodeID() {
		return codeID;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		String string = "code: " + this.codeID + ", name: " + this.name + ", price: " + this.price;
		return string;
	}
}
