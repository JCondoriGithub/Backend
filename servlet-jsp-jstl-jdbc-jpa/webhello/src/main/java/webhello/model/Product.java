package webhello.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@NamedQueries({
	@NamedQuery(name = "Product.getByCategory", query = "select p from Product p where p.category=:c"),
	@NamedQuery(name = "Product.getByCategoryName", query = "select p from Product p where p.category.name=:n"),
	@NamedQuery(name = "Product.getAll", query = "select p from Product p"),
	@NamedQuery(name = "Product.find", query = "select p from Product p left join fetch p.category c where p.name like :txt or c.name like :txt")
})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codeID")
	private Integer codeID;
	private String name;
	private int price;
	
	@ManyToOne(fetch = FetchType.EAGER)	// verrà chiamata anche la sua "category" quando viene chiamato quest'oggetto, invece con LAZY "category" non sarà disponibile
	@JoinColumn(name = "code_category")
	private Category category = null;
	
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String toString() {
		String string = "code: " + this.codeID + ", name: " + this.name + ", price: " + this.price;
		return string;
	}
}
