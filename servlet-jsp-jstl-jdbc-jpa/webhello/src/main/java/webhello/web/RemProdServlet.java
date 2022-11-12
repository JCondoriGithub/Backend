package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhello.model.ProductManager;
import webhello.model.ProductManagerMemory;

@WebServlet("/remProduct")
public class RemProdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String productID = request.getParameter("product");
		ProductManager.getInstance().remove(Integer.parseInt(productID));
		response.sendRedirect("products.jsp");
	}

}
