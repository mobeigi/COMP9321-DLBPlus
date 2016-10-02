<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Header -->
	<nav class="light-blue lighten-1" role="navigation">
	   	<div class="nav-wrapper container">
			<a id="logo-container" href="?action=home" class="brand-logo"><img src="images/logo_white.png" alt="logo"></a>
	   		<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li>
					<a href="?action=visualise">Visualise</a> 
				</li>
	       	</ul>
	   		
   			<ul class="right hide-on-med-and-down"> 			
				<c:choose>
					<c:when test="${not empty user}">
						<li><a href="userAccount.jsp">Account</a></li>
						<li><a href="?action=viewCart">Cart</a></li>
						<li><a></a></li>
						<li><a href="?action=logout">Logout</a>
					</c:when>
					
						<c:otherwise>
						<li><a href="?action=registerPage">Register</a></li>
						<li><a href="?action=loginPage">Login</a></li>
					</c:otherwise>
				</c:choose>
	     	</ul>
	   </div>
	</nav>