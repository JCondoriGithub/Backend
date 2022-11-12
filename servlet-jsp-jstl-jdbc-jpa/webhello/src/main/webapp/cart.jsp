<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import= "webhello.web.Utils,webhello.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="e" %>

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

	<div>
		<h3>Pagina del carrello per l'utente: </h3>
		<b><c:out value="${user.name} "/>
		<c:out value="${user.surname}"/></b>
	</div>

	<e:cart cart="${cart}" add="add.jsp" var="p">
		<e:cart_product cart="${cart}" product="${p}" remove="remove?product="/>
	</e:cart>

	<br>
	<a href="home.jsp">home</a>
	<br>
	<a href="logout">logout</a>

</body>
</html>