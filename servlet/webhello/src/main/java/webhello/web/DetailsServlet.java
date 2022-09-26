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

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//User user = Utils.getUser(request);
		User user = (User)request.getSession().getAttribute("user");
		
		if(user != null)
			response.getWriter().print("<html><body><h3>Pagina dei dettagli per l'utente: </h3><b>" + user.getName() + " " + user.getSurname() + "</b>"
					+ "<br><a href='./login'>Home</a>"
					+ "<br><a href='./index'>logout</a></body></html>");
		else {
			response.sendRedirect("index?error");
		}
	}

}
