<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Listing</title>

  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="shortcut icon" href="/images/favicon.ico">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="css/main.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

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
    <h1 class="header center orange-text">Listing Details</h1>
    <br><br>
  </div>
</div>

<div class="container">
  <div class="col s10 offset-s1">

    <c:choose>
      <c:when test="${not listingFound}">
        <p class="flow-text center">No listing found. Check <strong>listing ID</strong>.</p>
      </c:when>

      <c:otherwise>
        <div class="card valign grey lighten-2" >
          <table class="centered highlighted striped responsive-table">
            <tr>
              <th class="centered">Field</th>
              <th class="centered">Value</th>
            </tr>

            <c:if test="${listings.image != null}">
              <tr>
                <td class="col offset-s1">Image</td>
                <td class="col offset-s1"><img src="${listings.image}" class="listingImage"/></td>
              </tr>
            </c:if>

            <c:if test="${ listings.id != null}">
              <tr>
                <td class="col offset-s1">ID</td>
                <td class="col offset-s1">${listings.id}</td>
              </tr>
            </c:if>

            <c:if test="${listings.title != null}">
              <tr>
                <td class="col offset-s1">Title</td>
                <td class="col offset-s1">${listings.title}</td>
              </tr>
            </c:if>

            <c:if test="${listings.type != null}">
              <tr>
                <td class="col offset-s1">Type</td>
                <td class="col offset-s1">${listings.typeString}</td>
              </tr>
            </c:if>

            <c:if test="${listings.sellerid != null}">
              <tr>
                <td class="col offset-s1">Seller</td>
                <td class="col offset-s1">${listings.sellerUsername}</td>
              </tr>
            </c:if>

            <c:if test="${listings.quantity != null}">
              <tr>
                <td class="col offset-s1">Quantity</td>
                <td class="col offset-s1">${listings.quantity}</td>
              </tr>
            </c:if>

            <c:if test="${listings.listdate != null}">
              <tr>
                <td class="col offset-s1">List Date</td>
                <td class="col offset-s1">${listings.listDateString}</td>
              </tr>
            </c:if>

            <c:if test="${listings.enddate != null}">
              <tr>
                <td class="col offset-s1">End Date</td>
                <td class="col offset-s1">${listings.endDateString}</td>
              </tr>
            </c:if>

            <c:if test="${listings.sellprice != null}">
              <tr>
                <td class="col offset-s1">Price</td>
                <td class="col offset-s1">${listings.sellpriceString}</td>
              </tr>
            </c:if>

            <c:if test="${listings.paused != null}">
              <tr>
                <td class="col offset-s1">Paused</td>
                <td class="col offset-s1">${listings.paused}</td>
              </tr>
            </c:if>

            <c:if test="${listings.numviews != null}">
              <tr>
                <td class="col offset-s1">Views</td>
                <td class="col offset-s1">${listings.numviews}</td>
              </tr>
            </c:if>

            <c:if test="${listings.authors != null && not empty listings.authors}">
              <tr>
                <td class="col offset-s1">Authors</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.authors}" var="author">
                    ${author}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.editors != null && not empty listings.editors}">
              <tr>
                <td class="col offset-s1">Editors</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.editors}" var="editor">
                    ${editor}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.volume != null}">
              <tr>
                <td class="col offset-s1">Volume</td>
                <td class="col offset-s1">${listings.volume}</td>
              </tr>
            </c:if>

            <c:if test="${listings.chapter != null}">
              <tr>
                <td class="col offset-s1">Chapter</td>
                <td class="col offset-s1">${listings.chapter}</td>
              </tr>
            </c:if>

            <c:if test="${listings.number != null}">
              <tr>
                <td class="col offset-s1">Number</td>
                <td class="col offset-s1">${listings.number}</td>
              </tr>
            </c:if>

            <c:if test="${listings.cdrom != null}">
              <tr>
                <td class="col offset-s1">CDROM</td>
                <td class="col offset-s1">${listings.cdrom}</td>
              </tr>
            </c:if>

            <c:if test="${listings.pages != null}">
              <tr>
                <td class="col offset-s1">Pages</td>
                <td class="col offset-s1">${listings.pages}</td>
              </tr>
            </c:if>

            <c:if test="${listings.publisher != null}">
              <tr>
                <td class="col offset-s1">Publisher</td>
                <td class="col offset-s1">${listings.publisher}</td>
              </tr>
            </c:if>

            <c:if test="${listings.month != null}">
              <tr>
                <td class="col offset-s1">Month</td>
                <td class="col offset-s1">${listings.month}</td>
              </tr>
            </c:if>

            <c:if test="${listings.year != null}">
              <tr>
                <td class="col offset-s1">Year</td>
                <td class="col offset-s1">${listings.year}</td>
              </tr>
            </c:if>

            <c:if test="${listings.address != null}">
              <tr>
                <td class="col offset-s1">Address</td>
                <td class="col offset-s1">${listings.address}</td>
              </tr>
            </c:if>

            <c:if test="${listings.venues != null && not empty listings.venues}">
              <tr>
                <td class="col offset-s1">Venues</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.venues}" var="venue">
                    ${venue}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.urls != null && not empty listings.urls}">
              <tr>
                <td class="col offset-s1">URLs</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.urls}" var="url">
                    ${url}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.ees != null && not empty listings.ees}">
              <tr>
                <td class="col offset-s1">EEs</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.ees}" var="ee">
                    ${ee}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.cites != null && not empty listings.cites}">
              <tr>
                <td class="col offset-s1">Cites</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.cites}" var="cite">
                    ${cite}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.crossref != null}">
              <tr>
                <td class="col offset-s1">Cross References</td>
                <td class="col offset-s1">${listings.crossref}</td>
              </tr>
            </c:if>

            <c:if test="${listings.isbns != null && not empty listings.isbns}">
              <tr>
                <td class="col offset-s1">ISBNs</td>
                <td class="col offset-s1">
                  <c:forEach items="${listings.isbns}" var="isbn">
                    ${isbn}<br />
                  </c:forEach>
                </td>
              </tr>
            </c:if>

            <c:if test="${listings.note != null}">
              <tr>
                <td class="col offset-s1">Note</td>
                <td class="col offset-s1">${listings.note}</td>
              </tr>
            </c:if>

            <c:if test="${listings.series != null}">
              <tr>
                <td class="col offset-s1">Series</td>
                <td class="col offset-s1">${listings.series}</td>
              </tr>
            </c:if>
          </table>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
  <br>
  <div class="row">
    <div class="col s9">
      <form action="dblplus" method="POST">
        <input type="hidden" name="action" value="back"/>
        <button type="submit" value="Back To Search" class="btn waves-effect waves-light">Back</button>
        <br>
      </form>
    </div>

    <c:if test="${user != null && listings.sellerid != user.id}">
    <div class="col s3">
      <form action="dblplus" method = "POST">
        <div>
          <input type="hidden" name="action" value="add"/>
          <input type="hidden" name="listingID" value="${listings.id}"/>
          <button type="submit" value="Add to Shopping Cart" class="btn waves-effect waves-light">Add to Cart
            <i class="material-icons right">add</i>
          </button>
        </div>
      </form>
    </div>
    </c:if>
  </div>
</div>
<br><br>


<jsp:include page="footer.jsp" />
</body>
</html>