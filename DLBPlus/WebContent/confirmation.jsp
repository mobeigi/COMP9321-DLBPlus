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
    		<h1 class="header center orange-text">Bibliographic Library</h1>
    		<h2 class="header center orange-text">Confirmation</h2>
    		<br><br>
        </div>
 	</div>
	
	<div class="row">
 		<div class="col s4 offset-s4">
 			<div class="card white">
        		<div class="card-content black-text">
					<form action="setup" method="post">
		              	<div class="row">
		              		<div class="col s12">
		              			<label>Please enter your confirmation code:</label>
			           			<input placeholder="Confirmation Code" id="textbox" name="code" type="text" />
			           		</div>
			           	</div>
			     		
			           	<div class="row">
				           	   				
							<div class="col s12 center">
					            <input type="hidden" name="action" value="regSuccess"/>
					            <button class="btn waves-effect waves-light" type="submit" value="regSuccess">Confirm
			  						<i class="material-icons right">send</i>
			  					</button>
			  					<br>
				  			</div>
				  			<!-- <div class="col s12 center">
					            <input type="hidden" name="action" value="reset"/>
					            <button class="btn waves-effect waves-light" type="submit" value="reset">Reset
			  						<i class="material-icons right">replay</i>
			  					</button>
			  					<br><br>
				  			</div> -->
				  		</div>
				  		<div class="row">
				  			<div class="col s12">
				  				<p class="center">Already Registered? <a href="login.jsp">Login</a></p>
				  			</div>
				  		</div>
					</form>        
	        	</div>
      		</div>
      	</div>
   	</div>
   	<div class="center-align">
	    <form action="setup" method="POST">
			<input type="hidden" name="action" value="back"/>
			<button class="btn waves-effect waves-light" type="submit" value="back">Back</button>
			<br>    
	    </form>
    </div><br>
    
     <!-- Footer -->
    <footer class="page-footer orange">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">Company Bio</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Settings</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Connect</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
      </div>
    </div>
  </footer>
</body>
</html>