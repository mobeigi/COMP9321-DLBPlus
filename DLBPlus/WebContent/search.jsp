<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="ShoppingCart" class="edu.unsw.comp9321.ShoppingCart"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Bibliographic Library | Advanced Search</title>
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
    		<h1 class="header center orange-text">Bibliographic Library</h1>
    		<br><br>
        </div>
 	</div>
 	
 	<!-- Body -->
 	<div class="row">
 		<div class="col s8 offset-s2">
 			<div class="card white">
        		<div class="card-content black-text">
					<form action="setup" method="post">
        				<div class="row">
        					<div class="col s12">
			              		<input placeholder="Title"     id="textbox" name="searchTitle" type="text" />
		              		</div>
		              	</div>
		              	<div class="row">
		              		<div class="col s6">
			           			<input placeholder="Author"    id="textbox" name="searchAuthor" type="text" />
			           		</div>
			           		<div class="col s6">
			     		    	<input placeholder="Editor"    id="textbox" name="searchEditor" type="text" />
		     		    	</div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input placeholder="Volume"    id="textbox" name="searchVolume" type="text" />
			     		    </div>
			     		    <div class="col s6">
			     		    	<input placeholder="Publisher" id="textbox" name="searchPubber" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input placeholder="ISBN"      id="textbox" name="searchISBN" type="text" />
			     		    </div>
			     		    <div class="col s6">
			           			<input placeholder="YYYY"      id="textbox" name="searchYear" type="text" maxlength="4" pattern="\d{4}"/>
			           		</div>
			           	</div>
			           	<div class="row">
				           	<div class="col s6">
				           		<label>Publication Type</label>
				                <select name="pubType">
							    	<option selected>Any</option>
						        	<option>Article</option>
							        <option>Inproceedings</option>
							        <option>Proceedings</option>
							        <option>Book</option>
							        <option>Incollection</option>
							        <option>Phdthesis</option>
							        <option>Masterthesis</option>
							        <option>WWW</option>
		    					</select>		    					
		    				</div>
	    				
							<div class="col s6 center">
								<br>
					            <input type="hidden" name="action" value="aSearch"/>
					            <button class="btn waves-effect waves-light" type="submit" value="Search">Search
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
   	<div class="center-align">
	    <form action="setup" method="POST">
			<input type="hidden" name="action" value="back"/>
			<button class="btn waves-effect waves-light" type="submit" value="Search">Back</button>
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


