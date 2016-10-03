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
  <title>DBL+ | Manage Listings</title>
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
<jsp:include page="adminNavbar.jsp" />

<!-- List of Users -->
<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br><br>
    <h2 class="center orange-text">List of Listings</h2>
    <br><br>
  </div>
</div>

<!-- Display number of Listings -->
<div class="container">
  <div class="row valign-wrapper">
    <div class="col s10 offset-s1">

      <c:choose>
        <c:when test="${fn:length(ListOfListings) == 0}">
          <p class="flow-text center">No Listings!</p>
        </c:when>
        <c:otherwise>
          <p class="flow-text">There are ${fn:length(ListOfListings)} listings!</p>
        </c:otherwise>
      </c:choose>

      <%-- Display list of listings --%> <%-- display all listings for now --%>
      <div class="card valign grey lighten-1" >
        <table class="left highlighted striped responsive-table">
          <thead>
          <tr>
            <th class="centered"></th>
            <th class="centered">Title</th>
            <th class="centered">Author</th>
            <th class="centered">Year</th>
            <th class="centered">Type</th>
            <th class="centered">List Date</th>
            <th class="centered">End Date</th>
            <th class="centered">Quantity</th>
            <th class="centered">Price</th>
            <th class="centered">Seller</th>
            <th class="centered">Admin</th>
          </tr>
          </thead>
          <tbody>

          <c:forEach var="listing" items="${ListOfListings}"
                     varStatus="loop">
            <tr>
              <td><img src="${listing.imageOrDefault}" class="listingImageThumbnail" /></td>
              <td><a href="/dblplus?action=viewlistingdetails&id=${listing.id}">${listing.title}</a></td>
              <td><p><i>${listing.arrayAuthors}</i></p></td>
              <td>${listing.year}</td>
              <td>${listing.typeString}</td>
              <td>${listing.listDateString}</td>
              <td>${listing.endDateString}</td>
              <td>${listing.quantity}</td>
              <td>${listing.sellpriceString}</td>
              <td>${listing.sellerUsername}</td>
              <td>
                <form action='admin'> <!-- Link to servlet to perform operation -->
                  <button type="submit" value="Remove listing" class="btn">Remove listing</button>
                  <input type="hidden" name="action" value="removeListing"> <!-- Use page operations -->
                  <input type="hidden" name="itemId" value="${listing.id}"> <!-- Use page operations -->
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>

        <%-- Back button --%>
        <div class="col s2">
          <a href="/admin?action=portal">
            <button type="submit" value="Back" class="btn">Back</button>
          </a>
        </div>

      </div>
    </div>
  </div>
</div>

<%-- Page navigation links --%>
<%-- Previous page link --%>
<%--
<c:choose>
  <c:when test="${searchFound.currPage != 1}">
    <div class="col s2 offset-s3">
      <form action='dblplus' method='POST' style="float:left">
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
--%>

<%-- Next page link --%>
<%--
<c:choose>
  <c:when test="${searchFound.currPage != searchFound.totalPages}">
    <div class="col s2">
      <form action='dblplus' method='POST'>
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
--%>


<jsp:include page="footer.jsp" />
</body>
</html>
