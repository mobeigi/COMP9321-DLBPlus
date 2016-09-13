<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="ShoppingCart" class="edu.unsw.comp9321.ShoppingCart"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Bibliographic Library | Search Results</title>

    <link rel="stylesheet" type="text/css" href="theme/styles.css" />

</head>

<body>
   <div id="page1">
    <center><h1>Bibliographic Library</h1></center>
   	<center><h2>Search Results</h2></center>
	<c:choose>
			<c:when test="${empty searchFound.results}">
				<center>No results found!</center>
			</c:when>
			
			<c:otherwise>
				<p>There are ${fn:length(searchFound.results)} results!</p>
				
				<%-- Display 10 results from current page --%>
	<table id="sampleTable">
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Type</th>
		</tr>
		<c:forEach begin="${(searchFound.currPage - 1)* searchFound.numItemsPerPage}" 
				   end="${(searchFound.currPage - 1)* searchFound.numItemsPerPage + searchFound.numItemsPerPage - 1}" 
				   varStatus="loop">
			<c:if test="${loop.index < fn:length(searchFound.results)}">
				<tr>
					<td><a href="setup?id=${searchFound.results[loop.index].id}">${searchFound.results[loop.index].title}</a></td>
					<td><p><i>${searchFound.results[loop.index].author}</i></p></td>
					<td>${searchFound.results[loop.index].pubType}</td>
					
				</tr>
			</c:if>
		</c:forEach>
	</table>
				
			<%-- Page navigation links --%>
			<%-- Previous page link --%>
			<c:choose>
				<c:when test="${searchFound.currPage != 1}">
					<form action='setup' method='POST' style="float:left">
						<input type='hidden' name="action" value="viewPreviousSearchPage"/>
						<button type="submit" class="btn btn-link">
							<span class="glyphicon glyphicon-chevron-left"></span>
							Previous
						</button>
					</form>	
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-link disabled" style="float:left">
						<span class="glyphicon glyphicon-chevron-left"></span>
						Previous
					</button>					
				</c:otherwise>
			</c:choose>
							
			<%-- Next page link --%>
			<c:choose>
				<c:when test="${searchFound.currPage != searchFound.totalPages}">
					<form action='setup' method='POST'>
						<input type='hidden' name="action" value="viewNextSearchPage"/>
						<button type="submit" class="btn btn-link">
							Next
							<span class="glyphicon glyphicon-chevron-right"></span>
						</button>
					</form>	
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-link disabled">
						Next
						<span class="glyphicon glyphicon-chevron-right"></span>
					</button>					
				</c:otherwise>
			</c:choose>
			
		</c:otherwise>
	</c:choose>
	<form action="setup" method="post">
		<input type="hidden" name="action" value="back"/>
		<input type="submit" value="Back" id="button"/>
	</form>
	<br>
   </div>
</body>
</html>
