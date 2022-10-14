<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "webhello.web.Utils,webhello.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
1:43
<c:out value="${user.role}"/><br>	<!-- cerca negli scope l'oggetto "user" (scope: request), invoca "getRole" e stampa a video -->
<c:set var="messaggio" value="hello"/>
<c:set var="messaggio" value="valore di sessione" scope="session"/>
message= <c:out value="${messaggio}"/>	<!-- cerca negli scope la prima variabile "messaggio" e stampalo a video -->
<br>
dal page context: <%=pageContext.getAttribute("messaggio") %><br>	<!-- nella pagina è visibile un'attributo "messaggio"-->
dalla request: <%=request.getAttribute("messaggio") %><br>	<!-- nella request NON è visibile un'attributo messaggio-->
dalla sessione: <%=session.getAttribute("messaggio") %><br>	<!-- nella sessione è visibile un'attributo messaggio-->
dall'applicazione: <%=application.getAttribute("messaggio") %>
<br><br>
choose: 
<c:choose>
	<c:when test="${messaggio2!=null}">attributo presente</c:when>
	<c:when test="${false}">valore 2</c:when>
	<c:otherwise>nessuno dei precedenti</c:otherwise>
</c:choose>
<br><br>
<c:forEach begin="1" end="10" var="i">	<!-- funzionalità equivalente alla for tradizionale. "var" indice del ciclo, step="1" di default-->
	i vale: <c:out value="${i}"></c:out><br>
</c:forEach>
<br>

<%
	pageContext.setAttribute("prod", ProductManager.getInstance().getProducts());
%>
<c:forEach items="${prod}" var="j">	<!-- funzionalità equivalente alla forEach. ogni elemento di items passa alla variabile "j" -->
	<c:out value="${j}"></c:out><br>
</c:forEach>

<jsp:useBean id="newUser" class="webhello.model.User" scope="page">	<!-- imposta nello scope, un'istanza di un javaBean (User) e lo associa alla varaiabile "newUser". Se l'istanza esiste già, viene usato per impostare delle proprietà di essa -->	
</jsp:useBean>
<jsp:setProperty property="name" name="newUser" value="Egidio"/><br>	<!-- imposta il valore all'attributo dell'oggetto associato a "newUser" -->
getProperty= <jsp:getProperty property="name" name="newUser"/><br>
newUser= <c:out value="${newUser.name}"></c:out>

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