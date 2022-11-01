package webhello.web;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhello.model.Cart;
import webhello.model.ProductManager;
import webhello.model.User;
import webhello.model.UserManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		User user = UserManager.getInstance().getUser(username, password);
		
		try {
			ProductManager.getInstance().getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		Cart c = new Cart();
		c.add(ProductManager.getInstance().getProduct(1), 2);
		c.add(ProductManager.getInstance().getProduct(3), 1);
		Utils.setCart(request, c);
		
		Utils.setUser(request, user);
		
		if(user != null)
			response.sendRedirect("home.jsp");
		else {
			response.sendRedirect("index.jsp?error");
		}
	}

}
