package webhello.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import webhello.model.*;

public class CartTag extends TagSupport{

	Cart cart;
	String add, var;
	List products;
	int index;
	
	public void setCart(Cart cart) {
		
		this.cart = cart;
	}
	
	public void setAdd(String add) {
		
		this.add = add;
	}
	
	public void setVar(String var) {
		
		this.var = var;
	}
	
	private void print(String s) {
		
		try {
			pageContext.getOut().print(s);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int doStartTag() {

		print("	<table>\n"
				+ "		<tr>\n"
				+ "			<th>nome</th>\n"
				+ "			<th>prezzo</th>\n"
				+ "			<th>quantità</th>\n"
				+ "		</tr>\n");
		
		products= cart.getProducts();
		index = 0;
				
		if(index < products.size())
			pageContext.setAttribute(var, products.get(index));
		return products.size() > 0 ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
	
	@Override
	public int doAfterBody() throws JspException {

		index++;
		if(index < products.size())
			pageContext.setAttribute(var, products.get(index));
		return index < products.size() ? EVAL_BODY_AGAIN : SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {

		try {
			pageContext.getRequest().setAttribute("add", add);
			pageContext.getRequest().setAttribute("cart", cart);	// si inseriscono questi attributi di "CartTag" nell'hashmap dello scope request
			pageContext.include("cartfooter.jsp");	// renderizza nella pagina la risorsa cartfooter.jsp
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();
	}
}
