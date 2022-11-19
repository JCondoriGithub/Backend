package autogenerati;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codeID;

	private String name;

	private int price;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="code_category")
	private Category1 category;

	public Product1() {
	}

	public int getCodeID() {
		return this.codeID;
	}

	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category1 getCategory() {
		return this.category;
	}

	public void setCategory(Category1 category) {
		this.category = category;
	}

}