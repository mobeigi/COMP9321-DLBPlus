<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | User Account</title>
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
	
	<!-- Header -->
	<jsp:include page="navbar.jsp" />
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h2 class="header center orange-text">Your Account</h2>
    		<br><br>
        </div>
 	</div>
	
	<div class="row">
 		<div class="col s6 offset-s3">
 			<div class="card white">
        		<div class="card-content black-text">
        			<div class="row">
        				<div class="col s6">
        					<h5 class="Header center flow-text">Orders</h5>
        					<br>
        					<div class="container center">
	        					<form method="post" action="setup">
		        					<input type="hidden" name="action" value="viewHist"/>
		        					<button class="btn waves-effect waves-light" type="submit" value="viewHist">View Order History</button>			
	       						</form>
       						</div>
        				</div>
        				<div class="col s6">
        					<h5 class="Header center flow-text">Sales</h5>
        					<br>
        					<div class="container center">
        						<form method="post" action="setup">
        							<input type="hidden" name="action" value="createListing"/>
        							<button class="btn waves-effect waves-light" type="submit" value="createListing">Create Listing</button>
        						</form>
        						<br>
	        					<form method="post" action="setup">
		        					<input type="hidden" name="action" value="viewListings"/>
		        					<button class="btn waves-effect waves-light" type="submit" value="viewListings">View Listings</button>			
	       						</form>
       						</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>	
   	</div>
	<br><br>
	
	<div class="row">
 		<div class="col s6 offset-s3">
 			<div class="card white">
        		<div class="card-content black-text">
        			<div class="row">
        				<div class="col s12">
        					<h5 class="Header center flow-text">Account Management</h5>
        					<br>
        						First Name: ${user.fname}
        						<br>
        						Last Name: ${user.lname}
        						<br>
        						Nickname: ${user.nickname}
        						<br>
        						Email: ${user.email}
        						<br>
        						Address: ${user.address}
        						<br>
        						Credit Card: ${user.creditcard}
        						<br>
        						Account Creation Date: ${user.acctcreated}
								<br>
        					<div class="container center">
	        					<form method="post" action="setup">
		        					<input type="hidden" name="action" value="modified"/>
		        					<button class="btn waves-effect waves-light" type="submit" value="modified">Edit Details</button>			
	       						</form>
       						</div>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>	
   	</div>
    <br><br>
    
   <jsp:include page="footer.jsp" />
</body>
</html>