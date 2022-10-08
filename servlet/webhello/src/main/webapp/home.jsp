<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "webhello.web.Utils,webhello.model.*" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<% User user = Utils.getUser(request); %>
	<h3>Benvenuto</h3>
	<div><%= user.getName() %> <%=user.getSurname() %></div>
	<br>
	<a href="cart.jsp">vai al carrello</a>
	<br>
	<a href="logout">logout</a>

</body>
</html>