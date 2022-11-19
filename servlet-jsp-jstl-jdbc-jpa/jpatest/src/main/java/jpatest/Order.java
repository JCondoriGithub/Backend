package jpatest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	private Long id;
	
	private String title;
	
	@ManyToOne
	Customer customer;
//	User customer;	la relazione è con la superclasse "User", quindi, a livello di DB, non si riesce ad individuare la tabella specifica della relazione. Perche in User si trovano i records che non sono ne di "Customer" ne di "Employee"
	
	public Order() {
		
	}
	
	public Order(Long id, String title, Customer customer) {
		this.id = id;
		this.title = title;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
