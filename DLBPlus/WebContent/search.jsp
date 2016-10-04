<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>DBL+ | Advanced Search</title>
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
    <h1 class="header center orange-text">Advanced Search</h1>
    <br><br>
  </div>
</div>

<!-- Body -->
<div class="row">
  <div class="col s8 offset-s2">
    <div class="card white">
      <div class="card-content black-text">
        <form action="dblplus" method="get">
          <div class="row">
            <div class="col s12">
              <label>Title</label>
              <input name="title" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <label>Seller User Name</label>
              <input name="sellerusername" type="text" />
            </div>
            <div class="col s6">
              <label>Quantity</label>
              <input name="quantity" type="text" pattern="\d*" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <label>Start Date</label>
              <input name="startdate" type="date" />
            </div>
            <div class="col s6">
              <label>End Date</label>
              <input name="enddate" type="date" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <label>Minimum Price ($AUD)</label>
              <input name="minprice" type="text" pattern="\d*.?\d*" onkeypress='return event.charCode == 46 || (event.charCode >= 48 && event.charCode <= 57)' />
            </div>
            <div class="col s6">
              <label>Maximum Price ($AUD)</label>
              <input name="maxprice" type="text" pattern="\d*.?\d*" onkeypress='return event.charCode == 46 || (event.charCode >= 48 && event.charCode <= 57)' />
            </div>
          </div>

          <div class="row">
            <div class="input-field col s6">
              <label>Author</label>
              <textarea name="author" class="materialize-textarea"></textarea>
            </div>
            <div class="input-field col s6">
              <label>Editor</label>
              <textarea name="editor" class="materialize-textarea"></textarea>
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <label>Volume</label>
              <input name="volume" type="text" />
            </div>
            <div class="col s6">
              <label>Chapter</label>
              <input name="chapter" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <label>Number</label>
              <input name="number" type="text" />
            </div>
            <div class="col s6">
              <label>CDrom</label>
              <input name="cdrom" type="text" />
            </div>
          </div>

          <div class="row">

            <div class="col s6">
              <label>Pages</label>
              <input name="pages" type="text" />
            </div>
            <div class="col s6">
              <label>Publisher</label>
              <input name="publisher" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <label>Month</label>
              <select name="month">
                <option value="" disabled selected>Month</option>
                <option value="January">January</option>
                <option value="February">February</option>
                <option value="March">March</option>
                <option value="April">April</option>
                <option value="May">May</option>
                <option value="June">June</option>
                <option value="July">July</option>
                <option value="August">August</option>
                <option value="September">September</option>
                <option value="October">October</option>
                <option value="November">November</option>
                <option value="December">December</option>
              </select>
            </div>
            <div class="col s6">
              <label>Year</label>
              <input name="year" type="text" maxlength="4" pattern="\d{4}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
            </div>
          </div>
          <div class="row">
            <div class="col s6" style="margin-top: 33px;">
              <label>Address</label>
              <input name="address" type="text" />
            </div>
            <div class="input-field col s6">
              <label>Venues</label>
              <textarea name="venues" class="materialize-textarea"></textarea>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s6">
              <label>URLs</label>
              <textarea name="urls" class="materialize-textarea"></textarea>
            </div>
            <div class="input-field col s6">
              <label>EEs</label>
              <textarea name="ees" class="materialize-textarea"></textarea>
            </div>
          </div>

          <div class="row">
            <div class="input-field col s6">
              <label>Cites</label>
              <textarea name="cites" class="materialize-textarea"></textarea>
            </div>
            <div class="col s6" style="margin-top: 33px;">
              <label>Cross References</label>
              <input name="crossref" type="text" />
            </div>
          </div>

          <div class="row">
            <div class="input-field col s6">
              <label>ISBNs</label>
              <textarea name="isbns" class="materialize-textarea"></textarea>
            </div>
            <div class="col s6" style="margin-top: 33px;">
              <label>Note</label>
              <input name="note" type="text" />
            </div>
          </div>

          <div class="row">
            <div class="col s6">
              <label>Series</label>
              <input name="series" type="text" />
            </div>
            <div class="col s6">
              <label>Ratings</label>
              <input name="ratings" type="text" />
            </div>
          </div>

          <div class="row">
            <div class="col s6">
              <label>Publication Type</label>
              <select name="type">
                <option value="" selected>Any</option>
                <option value="article">Article</option>
                <option value="inproceedings">Inproceedings</option>
                <option value="proceedings">Proceedings</option>
                <option value="book">Book</option>
                <option value="incollection">Incollection</option>
                <option value="phdthesis">PHD Thesis</option>
                <option value="mastersthesis" >Masters Thesis</option>
                <option value="www">Website</option>
              </select>
            </div>

            <div class="col s6 center">
              <br>
              <input type="hidden" name="action" value="search"/>
              <button class="btn waves-effect waves-light" type="submit" value="Search">Search
                <i class="material-icons right">send</i>
              </button>
              <br />
              <br />

              <input type="checkbox" class="filled-in" id="matchcase-checkbox" name="matchcase" />
              <label for="matchcase-checkbox">Match Case</label>
              &nbsp;
              <input type="checkbox" class="filled-in" id="exactmatch-checkbox" name="exactmatch" style="margin-left: 10px;" />
              <label for="exactmatch-checkbox">Exact Match</label>
              <br><br>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="center-align">
  <a href="/dblplus?action=home">
    <button class="btn waves-effect waves-light">Back</button>
  </a>
</div><br>

<jsp:include page="footer.jsp" />
</body>
</html>