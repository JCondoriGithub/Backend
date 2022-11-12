package webhello.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codeID")
	private Integer codeID = null;
	private String name;
	private int price;
	
	public Product() {
		
	}
	
	public Product(Integer codeID, String name, int price) {
		
		this.codeID = codeID;
		this.name = name;
		this.price = price;
	}

	public int getCodeID() {
		return codeID;
	}
	
	public void setCodeID(Integer codeID) {
		this.codeID = codeID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString() {
		String string = "code: " + this.codeID + ", name: " + this.name + ", price: " + this.price;
		return string;
	}
}
