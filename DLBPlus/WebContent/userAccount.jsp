<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | My Account</title>
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
    <h2 class="header center orange-text">My Account</h2>
    <br><br>
  </div>
</div>

<div class="row">
  <div class="col s6 offset-s3">
    <div class="card white">
      <div class="card-content black-text">
        <div class="row">
          <div class="col s6">
            <h5 class="Header center flow-text">Orders</h5>
            <br>
            <div class="container center">
              <a href="/dblplus?action=vieworderhistory">
                <button class="btn waves-effect waves-light">View Order History</button>
              </a>
            </div>
          </div>
          <div class="col s6">
            <h5 class="Header center flow-text">Sales</h5>
            <br>
            <div class="container center">
              <a href="/dblplus?action=createlisting">
                <button class="btn waves-effect waves-light">Create Listing</button>
              </a>
              <br /><br />
              <a href="/dblplus?action=viewlistings">
                <button class="btn waves-effect waves-light">View Listings</button>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<br><br>

<div class="row">
  <div class="col s6 offset-s3">
    <div class="card white">
      <div class="card-content black-text">
        <div class="row">
          <div class="col s12">
            <h5 class="Header center flow-text">Account Management</h5>
            <br>
            <strong>First Name:</strong> ${user.fname}
            <br>
            <strong>Last Name:</strong> ${user.lname}
            <br>
            <strong>Nickname:</strong> ${user.nickname}
            <br>
            <strong>Email:</strong> ${user.email}
            <br>
            <strong>Address:</strong> ${user.address}
            <br>
            <strong>Credit Card:</strong> ${user.creditcard}
            <br>
            <strong>Account Creation Date: </strong> ${user.acctcreated}
            <br />
            <br />
            <div class="container center">
              <a href="/dblplus?action=modifydetails">
                <button class="btn waves-effect waves-light">Edit Details</button>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<br><br>

<jsp:include page="footer.jsp" />
</body>
</html>