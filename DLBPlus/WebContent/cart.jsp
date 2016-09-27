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
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	 <script>
	   $(document).ready(function() {
	      $('select').material_select();
	  });
	</script>
	<nav class="light-blue lighten-1" role="navigation">
	   <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
	     <ul class="right hide-on-med-and-down">
	       <li><a href="#">Advanced Search</a></li>
	       <li><a href="#">Shopping Cart</a></li>
	       <li><a href="#">Sign Up</a></li>
	       <li><a href="#">Login</a></li>
	     </ul>
	   </div>
	</nav>
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h1 class="header center orange-text">Bibliographic Library</h1>
    		
			<c:choose>
				<c:when test="${isAlreadySelected eq 'true'}">
					<h2 class="header center orange-text"> Publication is already in your shopping cart! </h2>
				</c:when>
				<c:otherwise>
					<h2 class="header center orange-text">Shopping Cart</h2>
				</c:otherwise>
			</c:choose>
        </div>
 	</div>
 	
 	<br><br>
 	<div class="container">
		<div class="col s10 offset-s1">
   			<div class="card valign grey lighten-4" >
	        		

					<c:choose>
					
						<c:when test="${cartSize eq 0}">
							<p class="flow-text">Cart is empty!</p>
						</c:when>
						<c:when test="${cartSize ne 0}">
						<form action="setup" method="POST">
							<table class="centered highlighted responsive-table">							
							<thead>
								<th>Title</th>
								<th> </th>
							</thead>
							<tbody>
								<c:forEach var="publication" items="${ShoppingCart.elements}">
									<tr>
									<td><c:out value="${publication.title}" /></td>
									<td><input type="checkbox" name="removeFromCart" value="<c:out value="${publication.id}" />"></td>
									</tr>
								</c:forEach>
							</tbody>
							</table>
							<input type="hidden" name="action" value="remove"/>
							<input id="cButton" type='submit' value='Remove from Cart'/> 
						</form>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
		<form action="setup" method = 'POST'>
		<input type="hidden" name="action" value="back"/>
		<input id="cButton" type='submit' value='Back to Search'/> 
		</form>
</div>
</body>
</html>