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
	        		

				<c:choose>
					
					<c:when test="${cartSize eq 0}">
						<div class="card valign grey lighten-4" >
							<br>
							<p class="flow-text center">Cart is empty!</p>
							<br>
						</div>
					</c:when>
					<c:when test="${cartSize ne 0}">
						<form action="setup" method="POST">
							<div class="card valign grey lighten-4" >
								<div class="col s10">
									<table class="centered highlighted responsive-table">							
									<thead>
										<th>Title</th>
										<th> </th>
									</thead>
									<tbody>
										<c:forEach var="publication" items="${ShoppingCart.elements}">
											<tr>
											<td><c:out value="${publication.title}" /></td>
											<td><input type="checkbox" name="removeFromCart" value="<c:out value="${publication.id}" />" id="${publication.id}">
											<label for="${publication.id}"></label></td>
											</tr>
										</c:forEach>
									</tbody>
									</table>
								</div>
							</div>
							<div class="">
								<input type="hidden" name="action" value="remove">
								<button class="btn waves-effect waves-light" type="submit" value="Remove from Cart">Remove from Cart
								<i class="material-icons right"></i>
								</button> 
							</div>
						</form>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
		
	<div class="container">
		<div class="col s10 offset-1">
			<div class="col s6">
				<form action="setup" method="POST">
					<input type="hidden" name="action" value="back">
					<button class="btn waves-effect waves-light" type="submit" value="Back to Search">Back to Search
					<i class="material-icons right"></i>
					</button> 
				</form>
			</div>
		</div>
	</div>
</body>
</html>