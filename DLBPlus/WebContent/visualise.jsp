<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Bibliographic Library | Visualise</title>
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    
    <script type="text/javascript" src="js/vis.js"></script>
  	<link href="css/vis.css" rel="stylesheet" type="text/css" />
  	
	<style type="text/css">
	    #visualisation {
	      width: 800px;
	      height: 800px;
	    }
	    p {
	      max-width: 1000px;
	   	}
  	</style>
    
</head>

<body>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<jsp:include page="navbar.jsp"/>
	
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h1 class="header center orange-text">Visualisation</h1>
    		<br><br>
        </div>
 	</div>
 	
 	<!-- Query box -->
	<center><p> Here is the query area! </p></center>
	<br>
	
	<div class="row">
		<div class="col s8 offset-s2">
			<div class="card white">
		 			
		 			<!-- Area to visualise the listings -->
		 			<div id="visualisation"></div>		 			
		 	</div>

	 	</div>
	 </div>
	 
	 <!-- Execute visualise listings -->
	 <script type="text/javascript" src="js/visualiseListings.js"></script>
	
	<jsp:include page="footer.jsp" />
</body>
</html>