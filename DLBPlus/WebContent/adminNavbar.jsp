<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Header -->
<nav class="green lighten-1" role="navigation">
  <div class="nav-wrapper container"><a id="logo-container" href="/dblplus?action=home" class="brand-logo"><img src="images/logo_white.png" alt="logo"></a>
    <ul class="right hide-on-med-and-down">
      <c:choose>
      <c:when test="${not empty currAdmin}">
      <li><a href="admin?action=portal">Portal</a></li>
      <li><a href="admin?action=viewallusers">Manage Users</a></li>
      <li><a href="admin?action=viewalllistings">Manage Listings</a></li>
      <li class="userNameListing"><strong>${currAdmin.username}</strong></li>
      <li><a href="admin?action=adminLogout">Logout</a>
        </c:when>
        </c:choose>
    </ul>
  </div>
</nav>