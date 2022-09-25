package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().print("<form action=\"login\" method=\"post\">\n"
									+ "		<h3>Login</h3>\n"
									+ "		<input type=\"text\" name=\"username\"><br>\n"
									+ "	 	<input type=\"password\" name=\"password\"><br><br>\n"
									+ "	 	<input type=\"submit\" value=\"submit\" name=\"login\">\n"
									+ "	</form>");
		
		if(request.getParameter("error") != null)
			response.getWriter().print("<div><b>Errore!</b></div>");
	}

}
