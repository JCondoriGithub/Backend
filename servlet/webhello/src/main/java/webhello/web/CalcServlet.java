package webhello.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalcServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter w = res.getWriter();
		w.print("<html><body><b>Calcolatore</b><hr/>"
				+ "	<form action=\"calcolatrice\" method=\"post\">\n"
				+ "	  <input type=\"text\" id=\"1op\" name=\"1op\"><br>\n"
				+ "	  <input type=\"text\" id=\"2op\" name=\"2op\"><br><br>\n"
				+ "	  <input type=\"submit\" value=\"somma\" name=\"op\">\n"
				+ "	  <input type=\"submit\" value=\"sottrazione\" name=\"op\">\n"
				+ "	  <input type=\"submit\" value=\"prodotto\" name=\"op\">\n"
				+ "	  <input type=\"submit\" value=\"divisione\" name=\"op\">\n"
				+ "	</form><hr/>"
				+ "</body></html>");
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
		
		doGet(req, res);
		
		PrintWriter w = res.getWriter();
		w.print("<html><body>risultato = <b>");
		w.print(result + "</b></body></html>");
	}

}
