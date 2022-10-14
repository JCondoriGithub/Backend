<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import= "webhello.web.Utils,webhello.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrello</title>
<style>
	table, th, td {
	  border:1px solid black;
	}
</style>
</head>
<body>

	<% User user = Utils.getUser(request); %>
	<h3>Pagina del carrello per l'utente: </h3>
	<b><%=user.getName() %> <%=user.getSurname() %></b>
	<br><br>
	<table>
		<tr>
			<th>nome</th>
			<th>prezzo</th>
			<th>quantità</th>
		</tr>
		<% Cart c = Utils.getCart(request);
			for(Product p: c.getProducts()) {
		%>
			<tr>
				<td><%=p.getName() %></td>
				<td><%=p.getPrice() %>
				<td><%=c.getQty(p) %></td>
				<td><a href="remove?product=<%=p.getCodeID() %>">remove</a></td>
			</tr>
			<%} %>
		
	</table>
	<br>
	<span>valore totale dei prodotti: <b><%=c.getTotPrice() %></b>€</span>
	<br><br>
	<br>
	<a href="add.jsp">aggiungi prodotti</a>
	<br>
	<a href="home.jsp">home</a>
	<br>
	<a href="logout">logout</a>

</body>
</html>