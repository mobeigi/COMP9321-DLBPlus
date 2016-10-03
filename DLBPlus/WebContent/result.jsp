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
  <title>DBL+ | Results</title>
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

          <c:choose>
            <c:when test="${searchFound.totalItems eq 1}">
              <p class="flow-text">There was ${fn:length(searchFound.results)} result found.</p>
            </c:when>
            <c:otherwise>
              <p class="flow-text">There were ${fn:length(searchFound.results)} results found.</p>
            </c:otherwise>
          </c:choose>

          <%-- Display 10 results from current page --%>
          <div class="card valign grey lighten-2" >
            <table class="centered highlighted striped responsive-table">
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
              </tr>
              <c:forEach begin="${(searchFound.currPage - 1)* searchFound.numItemsPerPage}"
                         end="${(searchFound.currPage - 1)* searchFound.numItemsPerPage + searchFound.numItemsPerPage - 1}"
                         varStatus="loop">
                <c:if test="${loop.index < fn:length(searchFound.results)}">
                  <tr>
                    <td><img src="${searchFound.results[loop.index].imageOrDefault}" class="listingImageThumbnail" /></td>
                    <td><a href="/dblplus?action=viewlistingdetails&id=${searchFound.results[loop.index].id}">${searchFound.results[loop.index].title}</a></td>
                    <td><p><i>${searchFound.results[loop.index].arrayAuthors}</i></p></td>
                    <td>${searchFound.results[loop.index].year}</td>
                    <td>${searchFound.results[loop.index].typeString}</td>
                    <td>${searchFound.results[loop.index].listDateString}</td>
                    <td>${searchFound.results[loop.index].endDateString}</td>
                    <td>${searchFound.results[loop.index].quantity}</td>
                    <td>${searchFound.results[loop.index].sellpriceString}</td>
                    <td>${searchFound.results[loop.index].sellerUsername}</td>
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
                <a href="${currentFullUrl}&pageNo=${searchFound.currPage - 1}">
                  <button type="submit" class="btn btn-link">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    Previous
                  </button>
                </a>
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

          <%-- Next page link --%>
          <c:choose>
            <c:when test="${searchFound.currPage != searchFound.totalPages}">
              <div class="col s2">
                <a href="${currentFullUrl}&pageNo=${searchFound.currPage + 1}">
                  <button type="submit" class="btn btn-link">
                    Next
                    <span class="glyphicon glyphicon-chevron-right"></span>
                  </button>
                </a>
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

          <%-- Back button --%>
          <div class="col s2">
            <a href="/dblplus?action=advancedsearch">
              <button type="submit" value="Back" class="btn">Back</button>
            </a>
          </div>

        </c:otherwise>
      </c:choose>


    </div>
  </div>
</div>
<br>


<jsp:include page="footer.jsp" />
</body>
</html>