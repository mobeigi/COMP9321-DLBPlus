<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Order History</title>
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
    <h1 class="header center orange-text">Order History</h1>
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
                    <th> Order ID </th>
                    <th> Title </th>
                    <th> Seller ID</th>
                    <th> Order Date</th>
                    <th> Price </th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="order" items="${userOrderList}">
                    <tr>
                      <td><c:out value="${order.id}"/></td>
                      <td><c:out value="${order.title}"/></td>
                      <td><c:out value="${order.sellerid}" /></td>
                      <td><c:out value="${order.order_date}" /></td>
                      <td>$<c:out value="${order.price}" /></td>

                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
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