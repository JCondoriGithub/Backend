<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import= "webhello.web.Utils,webhello.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dettagli</title>
</head>
<body>

	<% User user = Utils.getUser(request); %>
	<h3>Pagina dei dettagli per l'utente: </h3>
	<b><%=user.getName() %> <%=user.getSurname() %></b>
	<br><br>
	<a href="home.jsp">home</a>
	<a href="logout">logout</a>

</body>
</html>