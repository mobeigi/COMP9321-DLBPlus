<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | New Listing</title>
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
    		<h1 class="header center orange-text">New Listing</h1>
    		<br><br>
        </div>
 	</div>

	<!-- Body -->
	<div class="row">
 		<div class="col s6 offset-s3">
 			<div class="card white">
        		<div class="card-content black-text">
					<form action="setup" method="post">
		              	<div class="row">
		              		<div class="col s12">
			           			<input class="validate" placeholder="Item Name" required="" aria-required="" name="itemName" type="text" />
			           		</div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input placeholder="Authors (please separate authors with '|')" required="" aria-required="" name="authors" type="text" />
		     		    	</div>
			     		    <div class="input-field col s6">
			    				<select name="pubType" required="" aria-required="">
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
			     		</div>
        				<div class="row">
        					<div class="col s6">
			              		<input class="validate" placeholder="Editors" name="editors" type="text"/>
		              		</div>
		              		<div class="col s6">
			              		<input class="validate" placeholder="Venues" name="venues" type="text"/>
		              		</div>
		              	</div>
        				<div class="row">
        					<div class="col s6">
			              		<input class="validate" placeholder="Pages" name="pages" type="text"/>
		              		</div>
		              		<div class="col s6">
			              		<input class="validate" placeholder="Volume" name="volume" type="text"/>
		              		</div>
		              	</div>			     		
		              	<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="Year" name="year" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="Month" name="month" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="Address" name="address" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="Number" name="number" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="URLs" name="urls" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="EEs" name="ees" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="cdrom" name="cdrom" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="cites" name="cites" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="publisher" name="publisher" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="isbns" name="isbns" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="Cross Reference" name="crossref" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="Series" name="series" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s6">
			     		    	<input class="validate" placeholder="Chapter" name="chapter" type="text"/>
			     		    </div>
			     		    <div class="col s6">
			     		    	<input class="validate" placeholder="Rating" name="rating" type="text" />
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s12">
			     		    	<input class="validate" placeholder="Note" name="note" type="text"/>
			     		    </div>
			     		</div>
			     		<div class="row">
			     			<div class="col s4 offset-s4">
			     				<table style="cell-spacing: 0px">
				     				<tbody>
				     					<tr>
					     					<th><p class="flow-text">Price: </p></th>
					     					<th><i style="font-weight:normal">$</i></th>
					     		    		<th style="padding-top: 40px">
					     		    		<input style="height:1.5rem;font-weight: normal" pattern="[0-9]+(\\.[0-9][0-9]?)?" name="price" aria-required="" required="" placeholder="" class="validate" type="text">
					     		    		</th>
				     		    		</tr>
				     		    	</tbody>
			     		    	</table>
			     		    </div>
			     		</div>
			           	<div class="row">
				           	   				
							<div class="col s12 center">
								<br>
					            <input type="hidden" name="action" value="registerItem"/>
					            <button class="btn waves-effect waves-light" type="submit" value="registerItem">Register Item
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
	
	<jsp:include page="footer.jsp" />
</body>
</html>