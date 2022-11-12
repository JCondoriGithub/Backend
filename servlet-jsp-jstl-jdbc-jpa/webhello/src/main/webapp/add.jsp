<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "webhello.web.Utils,webhello.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aggiungi prodotto</title>
</head>
<body>

<% Cart c = Utils.getCart(request);%>

<form action="add" method="post">
	<select name="listProducts">
	<%
	for(Product p: ProductManager.getInstance().getProducts()) {
	%>
		  <option value="<%=p.getCodeID() %>"><%=p.getName() %></option>
	<%} %>
	</select>
	quantità: <input type="number" name="inputQty">
	<br><br>
	<input name="aggiungi" type="submit" value="aggiungi">
</form>
<br><br>
<a href="cart.jsp">cancel</a>
	
</body>
</html>