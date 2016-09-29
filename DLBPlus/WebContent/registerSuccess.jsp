<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | Register Successful!</title>
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
    		<h2 class="header center orange-text">Registration Success!</h2>
    		<br><br>
        </div>
 	</div>
	
	<div class="row">
 		<div class="col s4 offset-s4">
 			<div class="card white">
        		<div class="card-content black-text">
		           	<div class="row">
						<div class="col s12 center">
							<form action="setup" method="post">        
								<br>
					            <input type="hidden" name="action" value="toAccount"/>
					            <button class="btn waves-effect waves-light" type="submit" value="toAccount">To Account Page</button>
			  					<br><br>
		  					</form> 
			  			</div>
			  		</div>
					<div class="center-align">
					    <form action="setup" method="POST">
							<input type="hidden" name="action" value="back"/>
							<button class="btn waves-effect waves-light" type="submit" value="back">Back</button>
							<br>    
					    </form>
				    </div>     
	        	</div>
      		</div>
      	</div>
   	</div>
    <br>
    
    <jsp:include page="footer.jsp" />
</body>
</html>