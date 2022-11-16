<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "webhello.web.Utils,webhello.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anagrafica prodotti</title>
<style>
	table, th, td {
	  border:1px solid black;
	}
</style>
</head>
<body>

<% if(!Utils.checkUser(request, response, true))	// se è il ritorno del metodo è false
	return;		// esegui il return e blocca il codice
%>

<table>
		<tr>
			<th>nome</th>
			<th>categoria</th>
			<th>prezzo</th>
			<th>rimuovi</th>
		</tr>
		<%
		Cart c = Utils.getCart(request);
			for(Product p: ProductManager.getInstance().getProducts()) {
		%>
			<tr>
				<td><%=p.getName() %></td>
				<td><%=p.getCategory() == null ? "-" : p.getCategory().getName() %></td>
				<td><%=p.getPrice() %></td>
				<td><a href="remProduct?product=<%=p.getCodeID() %>">remove</a></td>
			</tr>
			<%} %>		
</table>
<br>
<form action="addProduct" method="post">
<%if(request.getParameter("error") != null) {%>
	<div>errore!: prodotto già esistente</div><br>
<%} %>
	nome prodotto<br>
	<input type="text" name="nameProduct"><br>
	prezzo prodotto<br>
	<input type="number" name="priceProduct">€<br>
	<label for="cars">Categoria</label>
	<br>
	<select name="category">
	<option value="">--nessuna categoria--</option>
<%
	for(Category cat: ProductManager.getInstance().getCategories()) { 
%>
      <option value="<%=cat.getCode() %>"><%=cat.getName() %></option>
<%} %>
  	</select>
	<br><br>
	<input type="submit" value="aggiungi" name="addButton">
</form>
<br>
<a href="home.jsp">home</a>

</body>
</html>