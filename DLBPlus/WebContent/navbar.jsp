<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Header -->
<nav class="light-blue lighten-1" role="navigation">
  <div class="nav-wrapper container">
    <a id="logo-container" href="/dblplus?action=home" class="brand-logo"><img src="images/logo_white.png" alt="logo"></a>
    <ul class="right hide-on-med-and-down">
      <li><a href="?action=visualise">Visualise</a></li>
      <c:choose>
        <c:when test="${not empty user}">
          <li><a href="/dblplus?action=myaccount">Account</a></li>
          <li><a href="/dblplus?action=viewcart">Cart</a></li>
          <li class="userNameListing"><strong>${user.username}</strong></li>
          <li><a href="/dblplus?action=logout">Logout</a>
        </c:when>

        <c:otherwise>
          <li><a href="/dblplus?action=register">Register</a></li>
          <li><a href="/dblplus?action=login">Login</a></li>
        </c:otherwise>
      </c:choose>

    </ul>
  </div>
</nav>