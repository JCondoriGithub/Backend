<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>footer</title>
</head>
<body>

	</table>
	<br><br>
	
	valore totale dei prodotti: <b><c:out value="${cart.getTotPrice()}"/></b>	<!-- cerca negli scope l'oggetto "cart" (scope: request), invoca "getTotPrice" e stampa a video -->
	<br><br>
	<a href="${add}">aggiungi prodotti</a>

</body>
</html>