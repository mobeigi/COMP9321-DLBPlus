<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="edu.unsw.comp9321.*, java.util.*"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <jsp:useBean id="ShoppingCart" class="edu.unsw.comp9321.ShoppingCart"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | Shopping Cart</title>
<link rel="stylesheet" type="text/css" href="theme/styles.css" />

</head>
<body>
<center><h1>Bibliographic Library</h1></center>
	<div id="page2">
		<c:choose>
			<c:when test="${isAlreadySelected eq 'true'}">
				<center><h2> Publication is already in your shopping cart! </h2></center>
			</c:when>
			<c:otherwise>
				<center><h2>Shopping Cart</h2></center>
			</c:otherwise>
		</c:choose>
		<c:choose>
		
		<c:when test="${cartSize eq 0}">
			<center><h2>Cart is empty!</h2></center>
		</c:when>
		<c:when test="${cartSize ne 0}">
		<form id="searchForm"action="setup" method="POST">
		<table id="sampleTable2">
				<tr>
					<th>Title</th>
					<th> </th>
				</tr>
				<c:forEach var="publication" items="${ShoppingCart.elements}">
					<tr>
					<td><c:out value="${publication.title}" /></td>
					<td><input type="checkbox" name="removeFromCart" value="<c:out value="${publication.id}" />"></td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="action" value="remove"/>
			<input id="cButton" type='submit' value='Remove from Cart'/> 
		</form>
		</c:when>
		</c:choose>
		<form action="setup" method = 'POST'>
		<input type="hidden" name="action" value="back"/>
		<input id="cButton" type='submit' value='Back to Search'/> 
		</form>
</div>
</body>
</html>