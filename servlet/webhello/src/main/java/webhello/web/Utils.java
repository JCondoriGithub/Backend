package webhello.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import webhello.model.User;
import webhello.model.UserManager;

public class Utils {
	
	public static final String COOKIENAME = "chisono";

	public static User getUser(HttpServletRequest req) {
		
		if(req.getCookies() == null)
			return null;
		else {
			for(Cookie c: req.getCookies())
				if(c.getName().equals(COOKIENAME))		
					return  UserManager.getInstance().getUser(c.getValue());
			return null;
		}
	}
	
	public static void setUser(HttpServletResponse res, User u) {
	
		Cookie c = new Cookie(COOKIENAME, u == null ? "" : u.getUsername());
		if(u == null)
			c.setMaxAge(0);
		res.addCookie(c);
	}
}
