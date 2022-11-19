package jpatest;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User {

	private String vatNumber;
	
	public Customer(String login, String pass, String vatNumber) {
		super(null, login, pass);
		this.vatNumber = vatNumber;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	@Override
	public String toString() {
		return "Costumer [vatNumber=" + vatNumber + ", getId()=" + getId() + ", getLogin()=" + getLogin()
				+ ", getPassword()=" + getPassword() + "]";
	}
	
}
