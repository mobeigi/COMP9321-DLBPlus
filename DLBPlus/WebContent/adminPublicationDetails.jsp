<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | Publication Details</title>

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
	<jsp:include page="adminNavbar.jsp" />
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h1 class="header center orange-text">Bibliographic Library</h1>
			<h2 class="header center orange-text">Publication Details</h2>
    		
    		<br><br>
        </div>
 	</div>
 	
 	<div class="container">
		<div class="col s10 offset-s1">
   			<div class="card valign grey lighten-2" >
	        		<table class="centered highlighted striped responsive-table">

						
							<c:forEach var="Field" items="${pubEntry}">
								<tr>
									<td class="col offset-s1"><c:out value="${Field}"/></td>
								</tr>
							</c:forEach>
						
					</table>
			</div>
	    </div>
	    <br>
	    <div class="row">
	    	<div class="col s10">
			    <form action="setup" method="POST">
			   		<input type="hidden" name="action" value="back"/>
					<button type="submit" value="Back To Search" class="btn waves-effect waves-light">Back</button>
					<br>
			    </form>
		    </div>
		    <div class="col s2">
				<form action="setup" method = "POST">
					<div>
						<input type="hidden" name="action" value="add"/>
						<input type="hidden" name="publicationID" value="${publicationID}"/>
						<button type="submit" value="Add to Shopping Cart" class="btn waves-effect waves-light">Add to Cart
						<i class="material-icons right">add</i>
						</button> 
					</div>
			    </form>
			</div>
	   	</div>
    </div>
    <br><br>


	<jsp:include page="footer.jsp" />
	
</body>
</html>