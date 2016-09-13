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
<title>Bibliographic Library | Publication</title>

    <link rel="stylesheet" type="text/css" href="theme/styles.css" />

<body>
    <div id="page2">

        <center><h1>Bibliographic Library</h1></center>
		<form id="searchForm" action="setup" method = "POST">
			<center><h2>Publication Details</h2></center>
			<table id="sampleTable2">
				
					<c:forEach var="Field" items="${pubEntry}">
						<tr>
							<td id="tdleft"><c:out value="${Field}"/></td>
						</tr>
					</c:forEach>
				
			</table>
			
			<div align="right">
				<input type="hidden" name="action" value="add"/>
				<input type="hidden" name="publicationID" value="${publicationID}"/>
				<input type="submit" value="Add to Shopping Cart" id="button2"/> 
			</div>
		</form>
    </div>
    <form action="setup" method="POST">
   		<input type="hidden" name="action" value="back"/>
		<center><input type="submit" value="Back To Search" id="button2"/></center>
		<br>
    </form>
</body>
</html>