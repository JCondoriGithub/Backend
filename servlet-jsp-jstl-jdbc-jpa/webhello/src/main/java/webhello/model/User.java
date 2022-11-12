package webhello.model;

public class User {

	private String username, password, name, surname;
	private Role role;
	
	public User() {};
	
	public User(String username, String password, String name, String surname, Role role) {
		
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}
	
	public static enum Role {ADMIN, COSTUMER};
	
	public String getUsername() {
		return username;
	}
	
	void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Role getRole() {
		return role;
	}
}
