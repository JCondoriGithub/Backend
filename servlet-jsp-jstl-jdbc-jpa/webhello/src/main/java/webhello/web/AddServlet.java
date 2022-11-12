package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhello.model.Cart;
import webhello.model.Product;
import webhello.model.ProductManager;
import webhello.model.ProductManagerMemory;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codeID = request.getParameter("listProducts");
		String qty = request.getParameter("inputQty");
		
		Product p = ProductManager.getInstance().getProduct(Integer.parseInt(codeID));
		int q = Integer.parseInt(qty);
		
		Cart c = Utils.getCart(request);
		c.add(p, q);
		response.sendRedirect("cart.jsp");
	}

}
