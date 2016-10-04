<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | User Details</title>

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
<jsp:include page="adminNavbar.jsp" />
<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br><br>
    <h2 class="header center orange-text">User Details</h2>

    <br><br>
  </div>
</div>

<div class="container">
  <div class="col s10 offset-s1">

    <%-- Display details of user --%>
    <div class="card valign grey lighten-1" >
      <table class="left highlighted striped responsive-table">
        <thead>
        <tr>
          <th class="centered">Field</th>
          <th class="centered">Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>ID</td>
          <td><c:out value="${myUser.id}" /></td>
        </tr>
        <tr>
          <td>Username</td>
          <td><c:out value="${myUser.username}" /></td>
        </tr>
        <tr>
          <td>First name</td>
          <td><c:out value="${myUser.fname}" /></td>
        </tr>
        <tr>
          <td>Last name</td>
          <td><c:out value="${myUser.lname}" /></td>
        </tr>
        <tr>
          <td>Nickname</td>
          <td><c:out value="${myUser.nickname}" /></td>
        </tr>
        <tr>
          <td>Email</td>
          <td><c:out value="${myUser.email}" /></td>
        </tr>
        <tr>
          <td>Address</td>
          <td><c:out value="${myUser.address}" /></td>
        </tr>
        <tr>
          <td>DOB</td>
          <td><c:out value="${myUser.dob}" /></td>
        </tr>
        <tr>
          <td>Credit Card</td>
          <td><c:out value="${myUser.creditcard}" /></td>
        </tr>
        <tr>
          <td>Cart Id</td>
          <td><c:out value="${myUser.cartid}" /></td>
        </tr>
        <tr>
          <td>Status</td>
          <td><c:out value="${myUser.acctstatus ? 'Active' : 'Suspended'}" /></td>
        </tr>
        <tr>
          <td>Account Confirmed</td>
          <td><c:out value="${myUser.acctconfrm ? 'Yes' : 'No'}" /></td>
        </tr>
        <tr>
          <td>Account Created</td>
          <td><c:out value="${myUser.acctcreated}" /></td>
        </tr>

        </tbody>
      </table>
    </div>
  </div>

  <br />
  <%-- Back button --%>
  <div class="col s2">
    <a href="/admin?action=viewallusers">
      <button type="submit" value="Back" class="btn">Back</button>
    </a>
  </div>


</div>
<br><br>


<jsp:include page="footer.jsp" />
</body>
</html>