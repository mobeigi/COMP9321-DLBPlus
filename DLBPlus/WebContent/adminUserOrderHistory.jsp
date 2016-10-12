<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | User Order History</title>
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
<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br><br>
    <h1 class="header center orange-text">User Order History</h1>
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
              <div class="row">
                <table class="centered highlighted striped responsive-table">
                  <thead>
                  <tr>
                    <th class="centered">Order ID</th>
                    <th class="centered">Title</th>
                    <th class="centered">Seller</th>
                    <th class="centered">Order Date</th>
                    <th class="centered">Price</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach begin="${(userOrderList.currPage - 1) * userOrderList.numItemsPerPage}"
                             end="${(userOrderList.currPage - 1) * userOrderList.numItemsPerPage + userOrderList.numItemsPerPage - 1}"
                             varStatus="loop">
                    <c:if test="${loop.index < fn:length(userOrderList.results)}">
                      <tr>
                        <td>${userOrderList.results[loop.index].id}</td>
                        <td>${userOrderList.results[loop.index].pubTitle}</td>
                        <td>${userOrderList.results[loop.index].sellerUsername}</td>
                        <td>${userOrderList.results[loop.index].orderDateString}</td>
                        <td>${userOrderList.results[loop.index].priceString}</td>
                      </tr>
                    </c:if>
                  </c:forEach>
                  </tbody>
                  </tbody>
                </table>

                <br />
                  <%-- Page navigation links --%>
                  <%-- Previous page link --%>
                <c:choose>
                  <c:when test="${userOrderList.currPage != 1}">
                    <div class="col s2 offset-s3">
                      <a href="${currentFullUrl}&pageNo=${userOrderList.currPage - 1}">
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
                  <c:when test="${userOrderList.currPage != userOrderList.totalPages}">
                    <div class="col s2">
                      <a href="${currentFullUrl}&pageNo=${userOrderList.currPage + 1}">
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

                <br />
                  <%-- Back button --%>
                <div class="col s2">
                  <a href="/admin?action=viewallusers">
                    <button type="submit" value="Back" class="btn">Back</button>
                  </a>
                </div>

              </div>
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