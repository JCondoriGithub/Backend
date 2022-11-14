package webhello.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webhello.model.ProductManager;
import webhello.model.ProductManager.DuplicatedProductException;

@WebServlet("/addProduct")
public class AddProdServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nameProduct = request.getParameter("nameProduct");
		int priceProduct = Integer.parseInt(request.getParameter("priceProduct"));
		String category = request.getParameter("category");
		
		try {
			ProductManager.getInstance().add(nameProduct, priceProduct, (category == null || category.isEmpty()) ? null : ProductManager.getInstance().getCategory(Integer.parseInt(category)));
			response.sendRedirect("products.jsp");
		} catch (DuplicatedProductException ex){
			response.sendRedirect("products.jsp?error");
		}
	}

}
