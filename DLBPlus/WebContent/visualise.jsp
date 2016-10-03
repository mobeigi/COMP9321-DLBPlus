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
  <title>DBL+ | Visualise</title>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="shortcut icon" href="/images/favicon.ico">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="css/main.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

  <script type="text/javascript" src="js/vis.js"></script>
  <link href="css/vis.css" rel="stylesheet" type="text/css" />

  <style type="text/css">
    #visualisation {
      width: 800px;
      height: 800px;
      border: 1px solid lightgray;
    }
    p {
      max-width: 1000px;
    }

    input {
      display: inline;
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

<script>
  $(document).ready(function() {
    $('select').material_select();
  });
</script>

<!-- Query box -->
<div class="row">
  <div class="col s8 offset-s2">
    <div class="card white">

      <div class="row">
        <form action="dblplus" method="get">
        
          <div class="col s6">
          	<label for="queryBox">Query Data:</label>
            <textarea id="queryBox" name="queryData" class="materialize-textarea"></textarea>
          </div>
          
          <div class="col s3">
		  	<select name="queryType">
		      	<option value="title" selected>Titles</option>
		      	<option value="author">Authors</option>
		      	<option value="editor">Editors</option>
		      	<option value="venue">Venues</option>
		      	<label>Query field:</label>
		    </select>
	 	  </div>
			
		  <div>
	          <input type="hidden" name="action" value="queryVisualisation">
	          <button class="btn waves-effect waves-light" type="submit">Query</button>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
<br>

<div class="row">
  <div class="col s8 offset-s2">
    <div class="card white">

      <!-- Area to visualise the listings -->
      <center>
        <div id="visualisation"></div>
      </center>
    </div>

  </div>
</div>

<!-- Execute visualise listings -->
<script type="text/javascript">

  // create an array with nodes
  var nodes = [
    <%
      List<VisNode> visNodes = (List<VisNode>) request.getAttribute("visNodes");
      if (visNodes != null && visNodes.size() > 0) {
        String arrayVisNodes = "";
    	for (VisNode visNode : visNodes) {
    		arrayVisNodes += "{";
    		
    		// id
    		arrayVisNodes += "id: " + visNode.getID();
    		
    		// label
    		arrayVisNodes += ", label: '" + visNode.getValue() + "'";
    		
    		// image based on type
    		arrayVisNodes += ", image: '";
    		switch(visNode.getNodeType()) {
    		
	    		case TITLE:
	    			arrayVisNodes += "images/publication-icon.png";
	    			break;
	    			
	    		case AUTHOR:
	    			arrayVisNodes += "images/author-icon.png";
	    			break;
	    			
	    		case EDITOR:
	    			arrayVisNodes += "images/editor-icon.png";
	    			break;
	    			
	    		case VENUE:
	    			arrayVisNodes += "images/venue-icon.ico";
	    			break;
    		
    			default:
    				break;
    		}
    		arrayVisNodes += "'";
    		
    		// Shape
    		arrayVisNodes += ", shape: 'image'";
    		
    		// Close object
    		arrayVisNodes +=  "},";
    	}
        arrayVisNodes = arrayVisNodes.substring(0,arrayVisNodes.length()-1);	// remove trailing comma
        out.print(arrayVisNodes);
      } else {
        out.print("");
      }
    %>
  ];

  // create an array with edges
  var edges = [
    <%
      // Prepare font object
      String font = "font: {align: 'top'}";
      List<VisRelationship> visRelationships = (List<VisRelationship>) request.getAttribute("visRelationships");
      if (visRelationships != null && visRelationships.size() > 0) {
        String arrayVisRelationships = "";
        for (VisRelationship visRelationship : visRelationships) {
        	arrayVisRelationships += "{";
        	
        	// Consider from node id
        	arrayVisRelationships += "from: " + visRelationship.getFromNodeID();
        	
        	// Consider to node id
        	arrayVisRelationships += ", to: " + visRelationship.getToNodeID();
        	
        	// Consider font
        	arrayVisRelationships += ", " + font;
                       
            // Close object
            arrayVisRelationships += "},";
        }
        arrayVisRelationships = arrayVisRelationships.substring(0,arrayVisRelationships.length()-1);	// remove trailing comma
        out.print(arrayVisRelationships);
      } else {
        out.print("");
      }
    %>
  ];

  // Check if there are nodes and relationships to display
  var container = document.getElementById("visualisation");
  if (nodes.length > 0 && edges.length > 0) {
    // Display the graph-visuaslisation
    var data = {
      nodes: nodes,
      edges: edges
    };
    var options = {};
    var network = new vis.Network(container, data, options);
  }

  // Case when nothing to display
  else {
    container.innerHTML = "No listings to visualise!";
  }

</script>

<jsp:include page="footer.jsp" />
</body>
</html>