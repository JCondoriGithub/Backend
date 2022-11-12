package webhello.web;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;
import webhello.model.User;


public class HelloTag extends TagSupport {

	User user;
	int n;	// attributi della classe e del tag associato
	int i;	// attributo della classe
	
	public void setUser(User user) {
		
		this.user = user;
	}
	
	public void setn(int n) {	// nell'attributo "n" della classe del tag, verrà inserito il valore fisso definito nel tag hello 
		
		this.n = n;
	}
	
	@Override
	public int doStartTag() throws JspException {

		try {
			pageContext.getOut().print("<b>" + (user == null ? "non so chi sei" : ("buongiorno " + user.getName() + " " + user.getSurname())) + "</b>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		i = 0;
		return EVAL_BODY_INCLUDE;	// renderizza il contenuto del tag
		//return SKIP_BODY;	non renderizzare il contenuto del tag
	}
	
	@Override
	public int doAfterBody() throws JspException {
		
		i++;
		return i == n ? SKIP_BODY : EVAL_BODY_AGAIN;	// se i==n salta il contenuto del tag, altrimenti renderizzalo ancora
	}
	
	@Override
	public int doEndTag() throws JspException {

		try {
			pageContext.getOut().print("<br><b>close tag hello from: " + getClass() + "</b>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();	// il resto della pagina potrà essere renderizzato
		//return SKIP_PAGE;	non renderizzare il resto della pagina
	}
}
