<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Bibliographic Library | Advanced Search</title>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

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
        <form action="setup" method="get">
          <div class="row">
            <div class="col s12">
              <input placeholder="Title" name="title" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <input placeholder="Seller Username" name="sellerusername" type="text" />
            </div>
            <div class="col s6">
              <input placeholder="Quantity" name="quantity" type="text" pattern="\d*" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <input placeholder="Start Date" name="startdate" type="date" />
            </div>
            <div class="col s6">
              <input placeholder="End Date" name="enddate" type="date" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <input placeholder="Minimum Price ($AUD)" name="minprice" type="text" pattern="\d*.?\d*" onkeypress='return event.charCode == 46 || (event.charCode >= 48 && event.charCode <= 57)' />
            </div>
            <div class="col s6">
              <input placeholder="Minimum Price ($AUD)" name="maxprice" type="text" pattern="\d*.?\d*" onkeypress='return event.charCode == 46 || (event.charCode >= 48 && event.charCode <= 57)' />
            </div>
          </div>

          <div class="row">
            <div class="input-field col s6">
              <textarea placeholder="Author" name="author" class="materialize-textarea"></textarea>
            </div>
            <div class="input-field col s6">
              <textarea placeholder="Editors" name="editor" class="materialize-textarea"></textarea>
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <input placeholder="Volume" name="volume" type="text" />
            </div>
            <div class="col s6">
              <input placeholder="Chapter" name="chapter" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
              <input placeholder="Number" name="number" type="text" />
            </div>
            <div class="col s6">
              <input placeholder="CDROM" name="cdrom" type="text" />
            </div>
          </div>

          <div class="row">

            <div class="col s6">
              <input placeholder="Pages" name="pages" type="text" />
            </div>
            <div class="col s6">
              <input placeholder="Publisher" name="publisher" type="text" />
            </div>
          </div>
          <div class="row">
            <div class="col s6">
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
              <input placeholder="Year" name="year" type="text" maxlength="4" pattern="\d{4}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
            </div>
          </div>
          <div class="row">
            <div class="col s6" style="margin-top: 50px;">
              <input placeholder="Address"  name="address" type="text" />
            </div>
            <div class="input-field col s6">
              <textarea placeholder="Venues" name="venues" class="materialize-textarea"></textarea>
            </div>
          </div>
          <div class="row">
            <div class="input-field col s6">
              <textarea placeholder="URLs" name="urls" class="materialize-textarea"></textarea>
            </div>
            <div class="input-field col s6">
              <textarea placeholder="EEs" name="ees" class="materialize-textarea"></textarea>
            </div>
          </div>

          <div class="row">
            <div class="input-field col s6">
              <textarea placeholder="Cites" name="cites" class="materialize-textarea"></textarea>
            </div>
            <div class="col s6" style="margin-top: 50px;">
              <input placeholder="Cross References"  name="crossref" type="text" />
            </div>
          </div>

          <div class="row">
            <div class="input-field col s6">
              <textarea placeholder="ISBNs" name="isbns" class="materialize-textarea"></textarea>
            </div>
            <div class="col s6" style="margin-top: 50px;">
              <input placeholder="Note"  name="note" type="text" />
            </div>
          </div>

          <div class="row">
            <div class="col s6">
              <input placeholder="Series"  name="series" type="text" />
            </div>
            <div class="col s6">
              <input placeholder="Ratings"  name="ratings" type="text" />
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
  <form action="setup" method="POST">
    <input type="hidden" name="action" value="home"/>
    <button class="btn waves-effect waves-light" type="submit" value="back">Back</button>
    <br>
  </form>
</div><br>

<jsp:include page="footer.jsp" />
</body>
</html>