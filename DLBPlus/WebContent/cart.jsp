<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Shopping Cart</title>
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
<jsp:include page="navbar.jsp" />
<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br><br>
    <h1 class="header center orange-text">Shopping Cart</h1>

    <c:choose>
      <c:when test="${isAlreadySelected eq 'true'}">
        <span class="header center">Listing is already in your shopping cart!</span>
      </c:when>
      <c:otherwise>
        <h2 class="header center orange-text">Shopping Cart</h2>
      </c:otherwise>
    </c:choose>
  </div>
</div>

<br><br>
<div class="container">
  <div class="col s10 offset-s1">


    <c:choose>

      <c:when test="${fn:length(cartListAsListings) eq 0}">
        <div class="card valign grey lighten-4" >
          <br>
          <p class="flow-text center">Cart is empty!</p>
          <br>
        </div>
      </c:when>
      <c:when test="${fn:length(cartListAsListings) ne 0}">
        <form action="dblplus" method="POST">
          <div class="card valign grey lighten-4" >
            <div class="col s10">
              <table class="centered highlighted responsive-table">
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
                  <th class="centered"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartListAsListings}">
                  <tr>
                  <tr>
                    <td><img src="${item.imageOrDefault}" class="listingImageThumbnail" /></td>
                    <td><a href="/dblplus?action=viewlistingdetails&id=${item.id}">${item.title}</a></td>
                    <td><i>${item.arrayAuthors}</i></td>
                    <td>${item.yearString}</td>
                    <td>${item.typeString}</td>
                    <td>${item.listDateString}</td>
                    <td>${item.endDateString}</td>
                    <td>${item.quantity}</td>
                    <td>${item.sellpriceString}</td>
                    <td>${item.sellerUsername}</td>
                    <td><input type="checkbox" name="removeListingID" value="${item.id}" id="${item.id}">
                      <label for="${item.id}"></label></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <div class="right-align">
            <input type="hidden" name="action" value="remove">
            <button class="btn waves-effect waves-light" type="submit" value="Remove from Cart">Remove from Cart
              <i class="material-icons right"></i>
            </button>
          </div>
        </form>
      </c:when>
    </c:choose>
  </div>
</div>
</div>

<div class="container">
  <div class="center-align">
    <div class="row">
      <div class="col s2 offset-s4">
        <c:if test="${fn:length(cartListAsListings) ne 0}">
          <form action="dblplus" method="post">
            <input type="hidden" name="action" value="checkout">
            <button class="btn waves-effect waves-light" type="submit">Checkout
              <i class="material-icons right"></i>
            </button>
          </form>
        </c:if>
      </div>
      <div class="col s2">
        <%-- Back button --%>
        <div class="col s2">
          <a href="/dblplus?action=myaccount">
            <button type="submit" value="Back" class="btn">Back</button>
          </a>
        </div>
      </div>
    </div>
  </div>
</div>
<br><br>
<jsp:include page="footer.jsp" />
</body>
</html>