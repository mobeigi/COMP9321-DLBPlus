<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Register</title>
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
    <h2 class="header center orange-text">Login</h2>
    <br><br>
  </div>
</div>

<div class="row">
  <div class="col s4 offset-s4">
    <c:if test="${not empty eMessage}">
      <p class="red-text">${eMessage}</p>
    </c:if>
    <div class="card white">
      <div class="card-content black-text">
        <form action="dblplus" method="post">
          <div class="row">
            <div class="col s12">
              <input placeholder="Username" required="" aria-required="" id="textbox" name="uname" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s12">
              <input placeholder="Password" required="" aria-required="" id="textbox" name="pass" type="password" />
            </div>
          </div>

          <div class="row">

            <div class="col s12 center">
              <br>
              <input type="hidden" name="action" value="loginProcess" />
              <button class="btn waves-effect waves-light" type="submit" value="Search">Login
                <i class="material-icons right">send</i>
              </button>
              <br><br>
            </div>
          </div>
          <div class="row">
            <div class="col s12">
              <p class="center">Not registered? <a href="?action=registerPage">Sign Up</a></p>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<br>

<jsp:include page="footer.jsp" />
</body>
</html>