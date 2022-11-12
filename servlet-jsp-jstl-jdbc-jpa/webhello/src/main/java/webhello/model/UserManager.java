package webhello.model;

import java.util.ArrayList;
import java.util.List;

import webhello.model.User.Role;

public class UserManager {

	private List<User> users = new ArrayList<>();
	
	private static UserManager instance = new UserManager();
	public static UserManager getInstance() {
		return instance;
	}
	
	public UserManager() {
		users.add(new User("admin", "admin", "Mario", "Rossi", Role.ADMIN));
		for(int i = 0; i < 10; i++)
			users.add(new User("user"+i, "pass", "Luca"+i, "Verdi", Role.COSTUMER));
	}
	
	public User getUser(String username, String pass) {
		for(User u: users)
			if(u.getUsername().equals(username) && u.getPassword().equals(pass)) 
				return u;
			return null;
	}
	
	public User getUser(String username) {
		for(User u: users)
			if(u.getUsername().equals(username)) 
				return u;
			return null;
	}
}
