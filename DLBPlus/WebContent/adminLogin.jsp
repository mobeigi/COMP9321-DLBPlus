<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	</script>
	<jsp:include page="adminNavbar.jsp" />
	
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h2 class="header center orange-text">Admin Login</h2>
    		<br><br>
        </div>
 	</div>
	
	<div class="row">
 		<div class="col s4 offset-s4">
 			<div class="card white">
        		<div class="card-content black-text">
        		
					<c:if test="${not empty errorMsg}">
					    <p>${errorMsg}</p>
					</c:if>

					<form action="admin" method="post">
		              	<div class="row">
		              		<div class="col s12">
			           			<input placeholder="Username" required="" aria-required="" id="textbox" name="username" type="text" />
			           		</div>
			           	</div>
			           	<div class="row">
			           		<div class="col s12">
			     		    	<input placeholder="Password" required="" aria-required="" id="textbox" name="password" type="password" />
		     		    	</div>
			     		</div>
			     		
			           	<div class="row">
				           	   				
							<div class="col s12 center">
								<br>
					            <input type="hidden" name="action" value="adminLogin"/>
					            <button class="btn waves-effect waves-light" type="submit">Login
			  						<i class="material-icons right">send</i>
			  					</button>
			  					<br><br>
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