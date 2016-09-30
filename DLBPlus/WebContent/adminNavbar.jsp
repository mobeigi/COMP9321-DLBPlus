<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Header -->
	<nav class="light-blue lighten-1" role="navigation">
	   	<div class="nav-wrapper container"><a id="logo-container" href="?action=home" class="brand-logo"><img src="images/logo_white.png" alt="logo"></a>
   			<ul class="right hide-on-med-and-down"> 			
				<c:choose>
					<c:when test="${not empty user}">
						<li><a href="search.jsp">Advanced Search</a></li>
						<li><a href="userAccount.jsp">Account</a></li>
						<li><a href="cart.jsp">Cart</a></li>
						<li><a></a></li>
						<li><a href="?action=logout">Logout</a>
					</c:when>
						
					<c:otherwise>
						<li><a href="/DLBPlus/admin?Action=Users">Manage Users</a></li>
						<li><a href="/DLBPlus/admin?Action=Listings">Manage Publications</a></li>
						<li><a href="login.jsp">Login</a></li>
					</c:otherwise>
				</c:choose>
	     </ul>
	   </div>
	</nav>