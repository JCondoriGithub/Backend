package webhello;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

	private List<User> users = new ArrayList<>();
	
	private static UserManager instance = new UserManager();
	public static UserManager getInstance() {
		return instance;
	}
	
	UserManager() {
		users.add(new User("admin", "admin", "Mario", "Rossi"));
		for(int i = 0; i < 10; i++)
			users.add(new User("admin"+i, "pass", "Luca"+i, "Verdi"));
	}
	
	public User getUser(String username, String pass) {
		for(User u: users)
			if(u.getUsername().equals(username) && u.getPassword().equals(pass)) 
				return u;
			return null;
	}
}
