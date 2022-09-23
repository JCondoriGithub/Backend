package webhello;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().print("<form action=\"login\" method=\"post\">\n"
									+ "		<h3>Login</h3>\n"
									+ "		<input type=\"text\" name=\"username\"><br>\n"
									+ "	 	<input type=\"password\" name=\"password\"><br><br>\n"
									+ "	 	<input type=\"submit\" value=\"submit\" name=\"login\">\n"
									+ "	</form>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserManager userManager = new UserManager();

		User user = userManager.getUser(username, password);
		
		
		if(user != null)
			response.getWriter().print("<html><body><h4>Benvenuto " + user.getName() + " " + user.getSurname() + "</h4></body></html>");
		else {
			doGet(request, response);
			response.getWriter().print("<html><body><h4>Errore!</h4></body></html>");
		}
	}

}
