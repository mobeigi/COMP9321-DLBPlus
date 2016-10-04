<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>DBL+ | Admin Page</title>
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
    <h2 class="header center orange-text">Admin Portal</h2>
    <br><br>
    
<div class="row">
  <div class="col s4 offset-s4">
    <div class="card white">
      <div class="card-content black-text">
        <div class="row">
          <div class="col s12 center">
            <p class="flow-text center">You are logged in as: <strong>${currAdmin.username}</strong></p>
            <form action="dblplus" method="post">
              <br>
              <input type="hidden" name="action" value="viewallusers"/>
              <button class="btn waves-effect waves-light" type="submit" value="viewallusers">Manage Users</button>
              <br><br>
            </form>
          </div>
        </div>
        <div class="center-align">
          <form action="dblplus" method="POST">
            <input type="hidden" name="action" value="viewAllListings"/>
            <button class="btn waves-effect waves-light" type="submit" value="viewAllListings">Manage listings</button>
            <br>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<br>
  </div>
</div>


<jsp:include page="footer.jsp" />

</body>
</html>
