<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>DBL+ | Manage Users</title>
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
    <h2 class="center orange-text">List of Users</h2>
    <br><br>
  </div>
</div>

<!-- Display number of users -->
<div class="container">
  <form action="admin" method="post">
    <div class="row valign-wrapper">
      <div class="col s10 offset-s1">


        <c:choose>
          <c:when test="${fn:length(ListOfUsers) == 0}">
            <p class="flow-text center">No users!</p> <!-- Admin user is different than user -->
          </c:when>
          <c:otherwise>
            <p class="flow-text">There are ${fn:length(ListOfUsers)} users!</p>
          </c:otherwise>
        </c:choose>

        <%-- Display list of all users --%>
        <div class="card valign grey lighten-1" >
          <table class="left highlighted striped responsive-table">
            <thead>
            <tr>
              <th class="centered">ID</th>
              <th class="centered">Username</th>
              <th class="centered">Order History</th>
              <th class="centered">Removed Listings</th>
              <th class="centered">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="currUser" items="${ListOfUsers}"
                       varStatus="loop">
              <tr>
                <td><c:out value="${currUser.id}" /></td>
                <td><a href=/admin?userId=${currUser.id}">${currUser.username}</a></td>
                <td><a href="/admin?action=vieworderhistory&userid=${currUser.id}">View</a></td>
                <td><a href="/admin?action=viewremovedlistings&userid=${currUser.id}">View</a></td>
                <td>
                  <select name="${currUser.id}">
                    <c:choose>
                      <c:when test="${currUser.acctstatus == true}">
                        <option value="true" selected>Active</option>
                        <option value="false">Suspended</option>
                      </c:when>
                      <c:otherwise>
                        <option value="true">Active</option>
                        <option value="false" selected>Suspended</option>
                      </c:otherwise>
                    </c:choose>
                  </select>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <br><br>
          <input type="hidden" name="action" value="UpdateUsersStatus">
          <button type="submit" value="Update changes" class="btn">Update changes</button>
        </div>
      </div>
    </div>
  </form>

  <%-- Back button --%>
  <div class="col s2">
    <a href="/admin?action=portal">
      <button type="submit" value="Back" class="btn">Back</button>
    </a>
  </div>

</div>

<jsp:include page="footer.jsp" />
</body>
</html>
