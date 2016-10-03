<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Current Listings</title>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="shortcut icon" href="/images/favicon.ico">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="css/main.css" />
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
<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br><br>
    <h1 class="header center orange-text">Current Listings</h1>
    <br><br>
  </div>
</div>

<div class="row">
  <div class="col s10 offset-s1">
    <div class="card white">
      <div class="card-content">
        <div class="card-title">
          <c:choose>
            <c:when test="${not empty eMessage}">
              <p class="red-text center">${eMessage}</p>
            </c:when>
            <c:otherwise>

              <form method="get" action="dblplus">
                <div class="row">
                  <table class="centered highlighted striped responsive-table">
                    <thead>
                    <tr>
                      <th>Listing ID</th>
                      <th></th>
                      <th>Title</th>
                      <th>Authors</th>
                      <th>Type</th>
                      <th>Quantity</th>
                      <th>Listed Price</th>
                      <th>Number of Views</th>
                      <th>Paused </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="listing" items="${userListings}">
                      <tr>
                        <td><c:out value="${listing.id}"/></td>
                        <td><img src="${listing.imageOrDefault}" class="listingImageThumbnail" /></td>
                        <td><a href="/dblplus?action=viewlistingdetails&id=${listing.id}">${listing.title}</a></td>
                        <td><c:out value="${listing.arrayAuthors}" /></td>
                        <td><c:out value="${listing.typeString}" /></td>
                        <td><c:out value="${listing.quantity}" /></td>
                        <td>$<c:out value="${listing.sellprice}" /></td>
                        <td><c:out value="${listing.numviews}" /></td>
                        <c:choose>
                        <c:when test="${listing.paused}">
                        <td><input type="checkbox" checked="checked" name="${listing.id}" value="checked" id="${listing.id}">
                          </c:when>
                          <c:otherwise>
                        <td><input type="checkbox" name="${listing.id}" value="checked" id="${listing.id}">
                          </c:otherwise>
                          </c:choose>
                          <label for="${listing.id}"></label></td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
                <div class="row">
                  <div class="right-align">
                    <input type="hidden" name="action" value="updateListingStatus">
                    <button class="btn waves-effect waves-light" type="submit" value="updateListingStatus">Apply Changes
                      <i class="material-icons right"></i>
                    </button>
                  </div>
                </div>
              </form>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>
</div>



<jsp:include page="footer.jsp" />
</body>
</html>