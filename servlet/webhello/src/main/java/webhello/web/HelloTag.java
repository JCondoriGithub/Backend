package webhello.web;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import webhello.model.User;


public class HelloTag extends TagSupport {

	User user;	// attributo della classe
	
	public void setUser(User user) {	// nell'attributo della classe verrà inserito l'oggetto "user" trovato dal tag hello 
		
		this.user = user;
	}
	
	@Override
	public int doStartTag() throws JspException {

		try {
			pageContext.getOut().print("<b>" + (user == null ? "non so chi sei" : ("buongiorno " + user.getName() + " " + user.getSurname())) + "</b>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	@Override
	public int doEndTag() throws JspException {

		try {
			pageContext.getOut().print("<br><b>close tag hello from: " + getClass() + "</b>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
