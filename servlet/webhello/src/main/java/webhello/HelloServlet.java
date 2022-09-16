package webhello;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter w = res.getWriter();
		w.print("<html><body><b>hai chiamato la get</b><hr/>");
		
		//posso mettere i parametri direttamente nella barra deglsi indirizzi. ese: ?param1=ciao!&param2=addio!
		for(String att: req.getParameterMap().keySet())
			w.print(att + "=" + req.getParameter(att) + "<hr/>");
		w.print("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int number1 = Integer.parseInt(req.getParameter("1op"));
		int number2 = Integer.parseInt(req.getParameter("2op"));
		String op = req.getParameter("op");
		
		int result = 0;
		
		switch(op) {
		case "somma":
			result = number1 + number2;
			break;
		case "sottrazione":
			result = number1 - number2;
			break;
		case "prodotto":
			result = number1 * number2;
			break;
		case "divisione":
			result = number1 / number2;
			break;
		}
		
		PrintWriter w = res.getWriter();
		w.print("<html><body>risultato = <b>");
		w.print(result + "</b></body></html>");
	}

}
