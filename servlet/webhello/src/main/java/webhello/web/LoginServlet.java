package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhello.model.User;
import webhello.model.UserManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = Utils.getUser(request);
				
		if(user != null)
			response.getWriter().print("<html><body><h4>Benvenuto " + user.getName() + " " + user.getSurname() + "</h4>"
					+ "<div><a href='./details'>dettagli</a></div>"
					+ "<div><br><a href='./index'>logout</a></div>"
					+ "</body></html>");
		else {
			response.sendRedirect("index?error");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
				
		UserManager userManager = new UserManager();

		User user = userManager.getUser(username, password);
		
		Utils.setUser(response, user);
		
		if(user != null)
			response.getWriter().print("<html><body><h4>Benvenuto " + user.getName() + " " + user.getSurname() + "</h4>"
					+ "<div><a href='./details'>dettagli</a></div>"
					+ "<div><br><a href='./index'>logout</a></div>"
					+ "</body></html>");
		else {
			response.sendRedirect("index?error");
		}
	}

}
