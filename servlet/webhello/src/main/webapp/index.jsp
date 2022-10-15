<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "webhello.web.Utils,webhello.model.*" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="e" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

<e:hello/>

	<form action="login" method="post">
		<h3>Login</h3><br>
		<input type="text" name="username"><br>
		<input type="text" name="password"><br><br>
		<input type="submit" value="submit" name="login">
	</form>
	<br>

	<%
	if(request.getParameter("error") != null) {%>
		<div>Login errato!</div>
	<%} %>
	
<e:hello/>
	
</body>
</html>