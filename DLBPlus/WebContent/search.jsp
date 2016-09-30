<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<jsp:include page="navbar.jsp" />
	
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h1 class="header center orange-text">Search for a Listing</h1>
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
			           			<input placeholder="Chapter"    id="textbox" name="searchChapter" type="text" />
			           		</div>
			     		</div>
			     		<div class="row">
		              		
			           		<div class="col s6">
			     		    	<input placeholder="Pages"    id="textbox" name="searchPage" type="text" />
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
			           			<input placeholder="venues"    id="textbox" name="searchVenues" type="text" />
			           		</div>
			           		<div class="col s6">
			     		    	<input placeholder="Seller Username"    id="textbox" name="searchSeller" type="text" />
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
			<button class="btn waves-effect waves-light" type="submit" value="back">Back</button>
			<br>    
	    </form>
    </div><br>
    
    <jsp:include page="footer.jsp" />
</body>
</html>


