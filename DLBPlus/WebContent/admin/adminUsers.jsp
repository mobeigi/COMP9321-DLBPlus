<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="ShoppingCart" class="edu.unsw.comp9321.ShoppingCart"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Bibliographic Library | Manage Users</title>
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
 		
	<!-- List of Users -->
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h2 class="center orange-text">List of Users</h2>
    		<br><br>
        </div>
 	</div>

	<!-- Display number of users -->
	<div class="container">
		<div class="row valign-wrapper">
			<div class="col s10 offset-s1">
		   			
				<c:choose>
					<c:when test="${empty listOfUsers.numUsers}"> <!-- use object here -->
						<p class="flow-text center">No users!</p> <!-- Unlikely as there will be at least one Admin user to see this -->
					</c:when>
					
					<c:otherwise>
						<p class="flow-text">There are ${fn:length(listOfUsers.numUsers)} users!</p> <!-- use object here -->
					</c:otherwise>
				</c:choose>

				<%-- Display list of all users --%>
     			<div class="card valign grey lighten-1" >
			        <table class="left highlighted striped responsive-table">
			        	<thead>
							<tr>
								<th> ID </th>
								<th> Username </th>
								<th> Is Admin</th>
								<th> Status </th>
								<th> Options </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pub" items="${found}"
							           varStatus="loop"> <!-- Use data structure here -->
								<tr>
									<td><a href="setup?id=${searchFound.results[loop.index].id}">${searchFound.results[loop.index].title}</a></td> <!-- Use data structure here -->
									<td><c:out value="${pub.author}" /></td>
									<td><c:out value="${pub.pubType}" /></td>
									<td><c:out value="${pub.pubType2}" /></td>
									<td>
									    <FORM Action='adminUsers.jsp'> <!-- Link to servlet to perform operation -->
										<INPUT type='submit' value='Suspend user'>
										<input type="hidden" name="addToCart" value="no"> <!-- Use page operations -->
										</FORM>
									    <FORM Action='adminIndex.jsp'> <!-- Link to servlet to perform operation -->
										<INPUT type='submit' value='Remove user'>
										<input type="hidden" name="addToCart" value="no"> <!-- Use page operations -->
										</FORM>
									</td>
								</tr>
							</c:forEach>
						</tbody>	
		        	</table>
				</div>
  			</div>
		</div>
	</div>
    
    
	<jsp:include page="footer.jsp" />
</body>
</html>
