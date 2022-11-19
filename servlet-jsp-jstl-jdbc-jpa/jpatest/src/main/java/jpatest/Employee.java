package jpatest;

import jakarta.persistence.Entity;

@Entity
public class Employee extends User {

	private String idNumber;
	
	public Employee(String login, String pass, String idNumber) {
		super(null, login, pass);
		this.idNumber = idNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Override
	public String toString() {
		return "Employee [idNumber=" + idNumber + ", getId()=" + getId() + ", getLogin()=" + getLogin()
				+ ", getPassword()=" + getPassword() + "]";
	}

}
