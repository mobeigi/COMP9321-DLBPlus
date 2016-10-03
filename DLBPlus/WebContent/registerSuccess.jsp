<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Register Successful!</title>
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
    <h1 class="header center orange-text">Registration Success!</h1>
    <br><br>
  </div>
</div>

<div class="row">
  <div class="col s4 offset-s4">
    <div class="card white">
      <div class="card-content black-text">
        <div class="row">
          <div class="col s12 center">
            <form action="dblplus" method="post">
              <br>
              <input type="hidden" name="action" value="toAccount"/>
              <button class="btn waves-effect waves-light" type="submit" value="toAccount">To Account Page</button>
              <br><br>
            </form>
          </div>
        </div>
        <div class="center-align">
          <form action="dblplus" method="POST">
            <input type="hidden" name="action" value="home"/>
            <button class="btn waves-effect waves-light" type="submit" value="home">Home</button>
            <br>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<br>

<jsp:include page="footer.jsp" />
</body>
</html>