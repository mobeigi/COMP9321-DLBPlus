<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | Listing</title>

   	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<body>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
 	<script>
   	$(document).ready(function() {
	      $('select').material_select();
  	});
   	</script>
   	
    <!-- Header -->
	<jsp:include page="navbar.jsp" />
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h2 class="header center orange-text">Listing Details</h2> 		
    		<br><br>
        </div>
 	</div>
 	
 	<div class="container">
		<div class="col s10 offset-s1">
		
			<%-- Display details of listing --%>
    		<div class="card valign grey lighten-1" >
		        <table class="left highlighted striped responsive-table">
		        	<thead>
						<tr>
							<th> Key </th>
							<th> Value </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>ID</td>
							<td><c:out value="${viewListing.id}" /></td>
						</tr>
						<tr>
							<td>Seller ID</td>
							<td><c:out value="${viewListing.sellerid}" /></td>
						</tr>
						<tr>
							<td>Quantity</td>
							<td><c:out value="${viewListing.quantity}" /></td>
						</tr>
						<tr>
							<td>List Date</td>
							<td><c:out value="${viewListing.listdate}" /></td>
						</tr>
						<tr>
							<td>End Date</td>
							<td><c:out value="${viewListing.enddate}" /></td>
						</tr>
						<tr>
							<td>Sell Price</td>
							<td><c:out value="${viewListing.sellprice}" /></td>
						</tr>
						<tr>
							<td>Image</td>
							<td><c:out value="${viewListing.image}" /></td>
						</tr>
						<tr>
							<td>Is Paused</td>
							<td><c:out value="${viewListing.paused}" /></td>
						</tr>
						<tr>
							<td>Number of Views</td>
							<td><c:out value="${viewListing.numviews}" /></td>
						</tr>
						<tr>
							<td>Type</td>
							<td><c:out value="${viewListing.type}" /></td>
						</tr>

						<c:forEach var="currAuthor" items="${viewListing.authors}"
						           varStatus="loop">
							<tr>
								<td>Author</td>
								<td><c:out value="${currAuthor}" /></td>
							</tr>
						</c:forEach>
						<c:forEach var="currEditor" items="${viewListing.editors}"
						           varStatus="loop">
							<tr>
								<td>Editor</td>
								<td><c:out value="${currEditor}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td>Title</td>
							<td><c:out value="${viewListing.title}" /></td>
						</tr>
						<tr>
							<td>Number of Pages</td>
							<td><c:out value="${viewListing.pages}" /></td>
						</tr>
						<tr>
							<td>Address</td>
							<td><c:out value="${viewListing.address}" /></td>
						</tr>
						<tr>
							<td>Volume</td>
							<td><c:out value="${viewListing.volume}" /></td>
						</tr>
						<tr>
							<td>Number</td>
							<td><c:out value="${viewListing.number}" /></td>
						</tr>
						<tr>
							<td>Month</td>
							<td><c:out value="${viewListing.month}" /></td>
						</tr>
						<c:forEach var="currURL" items="${viewListing.urls}"
						           varStatus="loop">
							<tr>
								<td>URL</td>
								<td><c:out value="${currURL}" /></td>
							</tr>
						</c:forEach>
						<c:forEach var="currEE" items="${viewListing.ees}"
						           varStatus="loop">
							<tr>
								<td>EE</td>
								<td><c:out value="${currEE}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td>CD ROM</td>
							<td><c:out value="${viewListing.cdrom}" /></td>
						</tr>
						<c:forEach var="currCity" items="${viewListing.cites}"
						           varStatus="loop">
							<tr>
								<td>City</td>
								<td><c:out value="${currCity}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td>Publisher</td>
							<td><c:out value="${viewListing.publisher}" /></td>
						</tr>
						<tr>
							<td>Note</td>
							<td><c:out value="${viewListing.note}" /></td>
						</tr>
						<tr>
							<td>Crossref</td>
							<td><c:out value="${viewListing.crossref}" /></td>
						</tr>
						<c:forEach var="currISBN" items="${viewListing.isbns}"
						           varStatus="loop">
							<tr>
								<td>ISBN</td>
								<td><c:out value="${currISBN}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td>Series</td>
							<td><c:out value="${viewListing.series}" /></td>
						</tr>
						<c:forEach var="currVenue" items="${viewListing.venues}"
						           varStatus="loop">
							<tr>
								<td>Venue</td>
								<td><c:out value="${currVenue}" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td>Chapter</td>
							<td><c:out value="${viewListing.chapter}" /></td>
						</tr>
						<tr>
							<td>Rating</td>
							<td><c:out value="${viewListing.rating}" /></td>
						</tr>

					</tbody>	
	        	</table>
			</div>
	    </div>
	    <br>
	    
	    
	    
	    <div class="row">
	    	<div class="col s9">
			    <form action="admin" method="POST">
			   		<input type="hidden" name="action" value="viewAllListings"/>
					<button type="submit" value="Back To Manage Listings" class="btn waves-effect waves-light">Back</button>
					<br>
			    </form>
		    </div>
	   	</div>
    </div>
    <br><br>
    

	<jsp:include page="footer.jsp" />
</body>
</html>