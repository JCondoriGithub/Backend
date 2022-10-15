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

	<h3>Pagina del carrello per l'utente: </h3>
	<b><c:out value="${user.name} "/>
	<c:out value="${user.surname}"/></b>
	<table>
		<tr>
			<th>nome</th>
			<th>prezzo</th>
			<th>quantità</th>
		</tr>
		<c:forEach items="${cart.products}" var="p">
			<tr>
				<td><c:out value="${p.name}"/></td><br>
				<td><c:out value="${p.price}"/></td><br>
				<td><c:out value="${cart.getQty(p)}"/></td><br>
				<td><a href="remove?product=${p.codeID}">remove</a></td>
			</tr>
		</c:forEach>
		
	</table>
	<br>
	valore totale dei prodotti: <b><c:out value="${cart.getTotPrice()}"/></b>
	<br><br>
	<br>
	<a href="add.jsp">aggiungi prodotti</a>
	<br>
	<a href="home.jsp">home</a>
	<br>
	<a href="logout">logout</a>

</body>
</html>