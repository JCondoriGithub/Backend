package webhello;

public class User {

	private String username, password, name, surname;
	
	public User(String username, String password, String name, String surname) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
	
	String getUsername() {
		return username;
	}
	
	void setUsername(String username) {
		this.username = username;
	}
	
	String getPassword() {
		return password;
	}
	
	void setPassword(String password) {
		this.password = password;
	}
	
	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getSurname() {
		return surname;
	}
	
	void setSurname(String surname) {
		this.surname = surname;
	}
}
