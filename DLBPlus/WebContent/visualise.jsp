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
	<script type="text/javascript">

	  // create an array with nodes
	  var nodes = [
	    {id: 1, label: 'Node 1'},
	    {id: 2, label: 'Node 2'},
	    {id: 3, label: 'Node 3:\nLeft-Aligned', font: {'face': 'Monospace', align: 'left'}},
	    {id: 4, label: 'Node 4'},
	    {id: 5, label: 'Node 5\nLeft-Aligned box', shape: 'box',
	     font: {'face': 'Monospace', align: 'left'}}
	  ];

	  // create an array with edges
	  var edges = [
	    {from: 1, to: 2, label: 'middle',     font: {align: 'middle'}},
	    {from: 1, to: 3, label: 'top',        font: {align: 'top'}},
	    {from: 2, to: 4, label: 'horizontal', font: {align: 'horizontal'}},
	    {from: 2, to: 5, label: 'bottom',     font: {align: 'bottom'}}
	  ];

	  // create a network
	  var container = document.getElementById("visualisation");
	  var data = {
	    nodes: nodes,
	    edges: edges
	  };
	  var options = {};
	  var network = new vis.Network(container, data, options);

	</script>
	
	<jsp:include page="footer.jsp" />
</body>
</html>