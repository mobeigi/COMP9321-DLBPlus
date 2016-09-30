<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | Register</title>
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
	   
	   $('.datepicker').pickadate({
		    selectMonths: true, // Creates a dropdown to control month
		    selectYears: 15 // Creates a dropdown of 15 years to control year
	  });
	</script>
	<jsp:include page="navbar.jsp" />
	
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h1 class="header center orange-text">Bibliographic Library</h1>
    		<h2 class="header center orange-text">Register</h2>
    		<br><br>
        </div>
 	</div>
	
	<div class="row">
 		<div class="col s6 offset-s3">
 			<c:choose>
				<c:when test="${not empty eMessage}">
 					<p class="red-text">${eMessage}</p>
 				</c:when>
 			</c:choose>
 			<div class="card white">
        		<div class="card-content black-text">
					<form action="setup" method="post">
						<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="Username" required="" aria-required="" name="uname" type="text" />
			           		</div>
			           		<div class="col s6">
			     		    	<input placeholder="Nickname" required="" aria-required="" name="nickname" type="text" />
		     		    	</div>
			     		</div>
			            <div class="row">
		              		<div class="col s6">
			     		    	<input placeholder="Password" required="" aria-required="" name="pass" type="password" />
		     		    	</div>
			           		<div class="col s6">
			     		    	<input placeholder="Retype Password" required="" aria-required="" name="passConfirm" type="password" />
		     		    	</div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input placeholder="First Name" required="" aria-required="" name="fname" type="text" />
			     		    </div>
			     		    <div class="col s6">
			     		    	<input placeholder="Last Name" required="" aria-required="" name="lname" type="text" />
			     		    </div>
        				<div class="row">
        					<div class="col s12">
			              		<input placeholder="Address" name="address" type="text" />
		              		</div>
		              	</div>			     		
		              	<div class="row">
			     			<div class="col s6">
			     		    	<input class="datepicker validate" placeholder="Date of Birth" required="" aria-required="" name="dob" type="date"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="Credit Card Number" required="" aria-required="" name="ccn" type="text" />
			     		    </div>
			     		</div>
			           	<div class="row">
				           	   				
							<div class="col s12 center">
								<br>
					            <input type="hidden" name="action" value="register"/>
					            <button class="btn waves-effect waves-light" type="submit" value="register">Register
			  						<i class="material-icons right">send</i>
			  					</button>
			  					<br><br>
				  			</div>
				  		</div>
				  		<div class="row">
				  			<div class="col s12">
				  				<p class="center">Already registered? <a href="login.jsp">Login</a></p>
				  			</div>
				  		</div>
					</form>        
	        	</div>
      		</div>
      	</div>
   	</div>
	<br>
    

    <jsp:include page="footer.jsp" />

</body>
</html>