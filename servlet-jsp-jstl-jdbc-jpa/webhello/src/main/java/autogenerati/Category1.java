package autogenerati;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int code;

	private String name;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="category")
	private List<Product1> products;

	public Category1() {
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product1> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product1> products) {
		this.products = products;
	}

	public Product1 addProduct(Product1 product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product1 removeProduct(Product1 product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

}