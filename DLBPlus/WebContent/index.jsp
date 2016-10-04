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
  <title>DBL+ | Home</title>
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
    <h1 class="header center orange-text">Bibliographic Library</h1>
    <br><br>
  </div>
</div>


<!-- Basic Search Form -->
<div class="row">
  <div class="col s8 offset-s2">
    <div class="card white">
      <div class="card-content black-text">
        <div class="card-title">
          <form action="dblplus" method="get">
            <div class="row">
              <div class="input-field col s6">
                <input Placeholder="Search for a listing" name="title" type="text" class="validate" />
              </div>
              <div class="input-field col s3">
                <select name="type">
                  <option value="" selected>Any</option>
                  <option value="article">Article</option>
                  <option value="inproceedings">Inproceedings</option>
                  <option value="proceedings">Proceedings</option>
                  <option value="book">Book</option>
                  <option value="incollection">Incollection</option>
                  <option value="phdthesis">PHD Thesis</option>
                  <option value="mastersthesis" >Masters Thesis</option>
                  <option value="www">Website</option>
                </select>
              </div>
              <div class="input-field col s3" style="vertical-align: middle">
                <button class="btn waves-effect waves-light" type="submit" value="Search">Search
                  <i class="material-icons right">send</i>
                </button>
                <input type="hidden" name="action" value="search"/>
              </div>
            </div>
          </form>
        </div>
        <div class="card-action">
          <form id="aSearchForm" action="dblplus" method="post">
            <div class="row">
              <div class="col s3">
                <a href ="/dblplus?action=advancedsearch" id="search">Advanced Search</a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


<!-- Random Listings -->
<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br><br>
    <h2 class="center orange-text">Featured Listings</h2>
  </div>
</div>

<div class="container">
  <div class="row valign-wrapper">
    <div class="col s10 offset-s1">
      <c:choose>
        <c:when test="${not empty eMessage}">
          <p class="red-text center">${eMessage}</p>
        </c:when>
        <c:otherwise>

          <c:choose>
            <c:when test="${empty randomListings}">
              <p class="red-text center">No featured listings are available.</p>
            </c:when>
            <c:otherwise>
              <br><br>
              <div class="card valign grey lighten-1" >
                <table class="left highlighted striped responsive-table">
                  <thead>
                  <tr>
                    <th class="centered">Title</th>
                    <th class="centered">Author</th>
                    <th class="centered">Year</th>
                    <th class="centered">Type</th>
                    <th class="centered">List Date</th>
                    <th class="centered">End Date</th>
                    <th class="centered">Quantity</th>
                    <th class="centered">Price</th>
                    <th class="centered">Seller</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="listing" items="${randomListings}">
                    <tr>
                      <td><a href="/?action=viewlistingdetails&id=${listing.id}">${listing.title}</a></td>
                      <td><i>${listing.arrayAuthors}</i></td>
                      <td>${listing.yearString}</td>
                      <td>${listing.typeString}</td>
                      <td>${listing.listDateString}</td>
                      <td>${listing.endDateString}</td>
                      <td>${listing.quantity}</td>
                      <td>${listing.sellpriceString}</td>
                      <td>${listing.sellerUsername}</td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </c:otherwise>
          </c:choose>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>
<br><br>

<jsp:include page="footer.jsp" />
</body>
</html>
