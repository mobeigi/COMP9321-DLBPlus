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
<<<<<<< HEAD
WE REGISTERING BOIS
        <form method="post" action="setup">
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Enter Information Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="fname" value="" />
                        </td>
                        
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lname" value="" />
                        </td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" name="email" value="" /></td>
                    </tr>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="uname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" name="address" value="" /></td>
                    </tr>
                     <tr>
                        <td>Date of Birth</td>
                        <td><input type="text" name="dob" value="" /></td>
                    </tr>
                     <tr>
                        <td>Credit Card Number</td>
                        <td><input type="text" name="ccn" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" />
                        <input type="hidden" name="action" value="register" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">Already registered? <a href="index.jsp">Login Here</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
=======
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
    		<h2 class="header center orange-text">Register</h2>
    		<br><br>
        </div>
 	</div>
	
	<div class="row">
 		<div class="col s6 offset-s3">
 			<div class="card white">
        		<div class="card-content black-text">
					<form action="setup" method="post">
		              	<div class="row">
		              		<div class="col s6">
			           			<input placeholder="Username"      id="textbox" name="newUserName" type="text" />
			           		</div>
			           		<div class="col s6">
			     		    	<input placeholder="Password"      id="textbox" name="newPassword" type="text" />
		     		    	</div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input placeholder="First Name"    id="textbox" name="newFirstName" type="text" />
			     		    </div>
			     		    <div class="col s6">
			     		    	<input placeholder="Last Name"     id="textbox" name="newLastName" type="text" />
			     		    </div>
			     		</div>
        				<div class="row">
        					<div class="col s12">
			              		<input placeholder="Email"         id="textbox" name="newEmail" type="text" />
		              		</div>
		              	</div>
        				<div class="row">
        					<div class="col s12">
			              		<input placeholder="Address"       id="textbox" name="newAddress" type="text" />
		              		</div>
		              	</div>			     		
		              	<div class="row">
			     			<div class="col s6">
			     		    	<input placeholder="Date of Birth" id="textbox" name="newDOB" type="text" />
			     		    </div>
			     		    <div class="col s6">
			     		    	<input placeholder="Credit Card Number"     id="textbox" name="newCCNo" type="text" />
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
          <h5 class="white-text">Cobacky Bio</h5>
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
>>>>>>> 0936e23e8a11ad596bcf580dc226ee7c489a308e
</body>
</html>