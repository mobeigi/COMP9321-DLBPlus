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
    <title>Bibliographic Library | Results</title>
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
	   <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
	     <ul class="right hide-on-med-and-down">
	       <li><a href="#">Advanced Search</a></li>
	       <li><a href="#">Shopping Cart</a></li>
	       <li><a href="#">Sign Up</a></li>
	       <li><a href="#">Login</a></li>
	     </ul>
	   </div>
	</nav>
	<div class="section no-pad-bot" id="index-banner">
    	<div class="container">
    		<br><br>
    		<h1 class="header center orange-text">Bibliographic Library</h1>
    		<br>
    		<h2 class="header center orange-text">Search Results</h2>
        </div>
 	</div>
 	
 	
	<div class="container">
		<div class="row valign-wrapper">
			<div class="col s10 offset-s1">
		   			
						<c:choose>
							<c:when test="${empty searchFound.results}">
								<p class="flow-text center">No results found!</p>
							</c:when>
							
							<c:otherwise>
								<p class="flow-text">There are ${fn:length(searchFound.results)} results!</p>
				
		<%-- Display 10 results from current page --%>
		<div class="card valign grey lighten-1" >
	        <table class="centered highlighted striped responsive-table">
				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>Type</th>
				</tr>
				<c:forEach begin="${(searchFound.currPage - 1)* searchFound.numItemsPerPage}" 
						   end="${(searchFound.currPage - 1)* searchFound.numItemsPerPage + searchFound.numItemsPerPage - 1}" 
						   varStatus="loop">
					<c:if test="${loop.index < fn:length(searchFound.results)}">
						<tr>
							<td><a href="setup?id=${searchFound.results[loop.index].id}">${searchFound.results[loop.index].title}</a></td>
							<td><p><i>${searchFound.results[loop.index].author}</i></p></td>
							<td>${searchFound.results[loop.index].pubType}</td>
							
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
				
		<%-- Page navigation links --%>
		<%-- Previous page link --%>
		<c:choose>
			<c:when test="${searchFound.currPage != 1}">
				<div class="col s2 offset-s3">
					<form action='setup' method='POST' style="float:left">
						<input type='hidden' name="action" value="viewPreviousSearchPage"/>
						<button type="submit" class="btn btn-link">
							<span class="glyphicon glyphicon-chevron-left"></span>
							Previous
						</button>
					</form>	
				</div>
			</c:when>
			<c:otherwise>
				<div class="col s2 offset-s3">
					<button type="button" class="btn btn-link disabled" style="float:left">
						<span class="glyphicon glyphicon-chevron-left"></span>
						Previous
					</button>
				</div>					
			</c:otherwise>
		</c:choose>
		<div class="col s2">
			<form action="setup" method="post">
				<input type="hidden" name="action" value="back"/>
				<button type="submit" value="Back" class="btn">Back</button>
			</form>
		</div>		
			<%-- Next page link --%>
			<c:choose>
				<c:when test="${searchFound.currPage != searchFound.totalPages}">
					<div class="col s2">
						<form action='setup' method='POST'>
							<input type='hidden' name="action" value="viewNextSearchPage"/>
							<button type="submit" class="btn btn-link">
								Next
								<span class="glyphicon glyphicon-chevron-right"></span>
							</button>
						</form>
					</div>	
				</c:when>
				<c:otherwise>
					<div class="col s2">
						<button type="button" class="btn btn-link disabled">
							Next
							<span class="glyphicon glyphicon-chevron-right"></span>
						</button>
					</div>					
				</c:otherwise>
			</c:choose>
			
		</c:otherwise>
	</c:choose>


   </div>
  </div>
  </div>
  	<br>
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
