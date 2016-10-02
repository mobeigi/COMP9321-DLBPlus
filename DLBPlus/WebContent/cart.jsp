<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="edu.unsw.comp9321.*, java.util.*"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<jsp:include page="navbar.jsp" />
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
					
					<c:when test="${cartListSize eq 0}">
						<div class="card valign grey lighten-4" >
							<br>
							<p class="flow-text center">Cart is empty!</p>
							<br>
						</div>
					</c:when>
					<c:when test="${cartListSize ne 0}">
						<form action="setup" method="POST">
							<div class="card valign grey lighten-4" >
								<div class="col s10">
									<table class="centered highlighted responsive-table">							
									<thead>
										<tr>
											<th>Listing ID</th>
											<th>Title</th>
											<th>Type</th>
											<th>Seller Name</th>
											<th>Price</th>
											<th> </th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${cartList}">
											<tr>
											<td><c:out value="${item.listingid}" /></td>
											<td><c:out value="${item.publicationName}" /></td>
											<td><c:out value="${item.publicationType}" /></td>
											<td><c:out value="${item.sellerName}" /></td>
											<td><c:out value="${item.price}" /></td>
											<td><input type="checkbox" name="removeListingID" value="<c:out value="${item.listingid}" />" id="${item.listingid}">
											<label for="${item.listingid}"></label></td>
											</tr>
										</c:forEach>
									</tbody>
									</table>
								</div>
							</div>
							<div class="right-align">
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
		<div class="center-align">
			<div class="row">
				<div class="col s2 offset-s4">
					<form action="setup" method="post">
						<input type="hidden" name="action" value="checkout">
						<button class="btn waves-effect waves-light" type="submit">Checkout
						<i class="material-icons right"></i>
						</button>
					</form>
				</div>
				<div class="col s2">
					<form action="setup" method="POST">
						<input type="hidden" name="action" value="home">
						<button class="btn waves-effect waves-light" type="submit">Back
						<i class="material-icons right"></i>
						</button> 
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	<jsp:include page="footer.jsp" />
</body>
</html>