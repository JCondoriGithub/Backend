package webhello.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import webhello.model.User;
import webhello.model.UserManager;
import jakarta.servlet.http.HttpSession;

public class Utils {
	
	public static void setUser(HttpServletRequest req, User user) {
		
		req.getSession().setAttribute("user", user);
	}
	
	public static User getUser(HttpServletRequest req) {
	
		return (User)req.getSession().getAttribute("user");
	}
}
