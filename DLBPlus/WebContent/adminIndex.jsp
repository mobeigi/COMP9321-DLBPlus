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
    
    <center>You are logged in as: <strong>${currAdmin.username}</strong></center>
  </div>
</div>


<jsp:include page="footer.jsp" />

</body>
</html>
