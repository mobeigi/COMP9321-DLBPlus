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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Bibliographic Library | Home</title>

    <link rel="stylesheet" type="text/css" href="theme/styles.css" />

</head>

<body>
	<form action = 'setup' method = 'GET'>
	
	</form>
    <div id="page1">

        <center><h1>Bibliographic Library</h1></center>
        
        <form id="aSearchForm" action="setup" method="post">
    		<fieldset>
               	<input id="s" name="searchQuery" type="text" />
                <select id="dropdown" name="pubType">
                	<option>Any</option>
                    <option>Article</option>
                    <option>Inproceedings</option>
                    <option>Proceedings</option>
                    <option>Book</option>
                    <option>Incollection</option>
                    <option>Phdthesis</option>
                    <option>Masterthesis</option>
                    <option>WWW</option>
                </select>
                <input type="hidden" name="action" value="search"/>
                <input type="submit" value="Search" id="sButton" />
                <div id="searchLeftContainer">
                    <a href ="search.jsp" id="search">Advanced Search</a>
    			</div>
                
                <div id="searchRightContainer">
                    <a href ="cart.jsp">Shopping Cart</a>
                </div>

            </fieldset>
        </form>
    </div>


    <div id="page2">
        <center><h2> Bibliographies of Interest</h2></center>
        <table id = "sampleTable">
			<tr>
				<th> Title </th>
				<th> Author</th>
				<th> Publication Type</th>
			</tr>
			<c:forEach var="pub" items="${found}">
				<tr>
					<td><c:out value="${pub.title}" /></td>
					<td><c:out value="${pub.author}" /></td>
					<td><c:out value="${pub.pubType}" /></td>
				</tr>
			</c:forEach>	
        </table>
    </div>
</body>
</html>
