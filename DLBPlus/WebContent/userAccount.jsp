<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<nav class="light-blue lighten-1" role="navigation">
	   <div class="nav-wrapper container"><a id="logo-container" href="?action=home" class="brand-logo"><img src="images/logo_white.png" alt="logo"></a>
	     <ul class="right hide-on-med-and-down">
	       <li><a href="search.jsp">Advanced Search</a></li>
	       <li><a href="cart.jsp">Shopping Cart</a></li>
	       <li><a href="register.jsp">Sign Up</a></li>
	       <li><a href="login.jsp">Login</a></li>
	     </ul>
	   </div>
	</nav>
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
       						<center><a href="?action=history&method=post">
       							View Order History
       						</a></center>			
        				</div>
        				<div class="col s6">
        					<h5 class="Header center flow-text">Your Listings</h5>
        					<br>
       						<center><a href="?action=history&method=post">
       							View Listings
       						</a></center>		
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
       						<center><a href="?action=history&method=post">
       							Edit details
       						</a></center>		
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