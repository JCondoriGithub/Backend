package webhello.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import jakarta.servlet.http.Cookie;
import webhello.model.Cart;
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
	
	public static void setCart(HttpServletRequest req, Cart c) {
		
		req.getSession().setAttribute("cart", c);
	}
	
	public static Cart getCart(HttpServletRequest req) {
	
		return (Cart)req.getSession().getAttribute("cart");
	}
	
	public static void logout(HttpServletRequest req) {
		
		req.getSession().invalidate();
	}
	
	public static boolean checkUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		User user = getUser(req);
		if(user == null)
			res.sendRedirect("index.jsp?err");
		return user != null;
	}

}
