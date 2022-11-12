<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "webhello.web.Utils,webhello.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="e" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

<e:hello user="${user}" n="5"><br>contenuto/body del tag hello</e:hello>	<!-- si imposta l'attributo "n" al valore fisso 5 -->

	<hr>
	<% User user = Utils.getUser(request); %>
	<h3>Benvenuto</h3>
	<div><%= user.getName() %> <%=user.getSurname() %></div>
	<br>
	<a href="cart.jsp">vai al carrello</a>
	<br>
	<a href="logout">logout</a>
	
	<%if(user.getRole() == User.Role.ADMIN) { %>
	<br><br>
	<a href="products.jsp">prodotti</a>
	<%} %>

</body>
</html>