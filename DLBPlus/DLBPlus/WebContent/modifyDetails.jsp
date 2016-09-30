<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bibliographic Library | Edit Details</title>
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
    		<h1 class="header center orange-text">Account Details</h1>
    		<br><br>
        </div>
 	</div>
 	
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
	                <td>User Name</td>
	                <td><input type="text" name="uname" value="" /></td>
	            </tr>
	            <tr>
	                <td>Password</td>
	                <td><input type="password" name="pass" value="" /></td>
	            </tr>
	            <tr>
	                <td><input type="submit" value="Submit" />
	                <input type="hidden" name="action" value="modified" /></td>
	                <td><input type="reset" value="Reset" /></td>
	            </tr>
	            <tr>
	                <td colspan="2">Already registered? <a href="index.jsp">Login Here</a></td>
	            </tr>
	        </tbody>
	    </table>
	    </center>
	</form>
        
        
    <jsp:include page="footer.jsp" />
</body>
</html>