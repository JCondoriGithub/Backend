package webhello.web;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class HelloTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {

		try {
			pageContext.getOut().print("<b>open tag hello from: " + getClass() + "</b><br>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	@Override
	public int doEndTag() throws JspException {

		try {
			pageContext.getOut().print("<b>close tag hello from: " + getClass() + "</b>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
