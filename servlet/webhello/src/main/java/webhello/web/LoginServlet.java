package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import webhello.model.User;
import webhello.model.UserManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		UserManager userManager = new UserManager();
		User user = userManager.getUser(username, password);
		
		Utils.setUser(request, user);
		
		if(user != null)
			response.sendRedirect("home.jsp");
		else {
			response.sendRedirect("index.jsp?error");
		}
	}

}