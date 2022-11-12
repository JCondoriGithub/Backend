package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhello.model.Cart;
import webhello.model.Product;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String productID = request.getParameter("product");
		Cart c = Utils.getCart(request);
		for(Product p: c.getProducts())
			if(p.getCodeID() == Integer.parseInt(productID))
				c.remove(p);
		response.sendRedirect("cart.jsp");
	}

}
