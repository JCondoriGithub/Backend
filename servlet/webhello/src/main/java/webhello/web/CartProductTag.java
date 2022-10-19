package webhello.web;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import webhello.model.*;

public class CartProductTag extends TagSupport {

	Cart cart;
	Product product;
	String remove;
	
	public void setCart(Cart cart) {
		
		this.cart = cart;
	}
	
	public void setProduct(Product product) {
		
		this.product = product;
	}
	
	public void setRemove(String remove) {
		
		this.remove = remove;
	}
	
	public int doStartTag() throws JspException {
		
		try {
			pageContext.getOut().print("\n"
					+ "			<tr>\n"
					+ "				<td>" + product.getName() + "</td><br>\n"
					+ "				<td>" + product.getPrice() + "</td><br>\n"
					+ "				<td>" + cart.getQty(product) + "</td><br>\n"
					+ "				<td><a href=\"" + remove + product.getCodeID() + "\">remove</a></td>\n"
					+ "			</tr>\n"
					+ "		</c:forEach>");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
