<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | New Listing</title>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link rel="shortcut icon" href="/images/favicon.ico">
  <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="css/main.css" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

  <script type="text/javascript">
    function imgToBase64() {
      var inputField = document.getElementById("base64Img");
      var file    = document.querySelector('input[type=file]').files[0];
      var reader  = new FileReader();

      reader.addEventListener("load", function () {
        inputField.value = reader.result;
      }, false);

      if (file) {
        reader.readAsDataURL(file);
      }
    }
  </script>
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
    <h1 class="header center orange-text">New Listing</h1>
    <br><br>
  </div>
</div>

<!-- Body -->
<div class="row">
  <div class="col s6 offset-s3">
    <div class="card white">
      <div class="card-content black-text">
        <form action="dblplus" method="post">
          <div class="row">
            <div class="col s12">
              <input placeholder="Title" name="title" type="text" aria-required="true" required="" />
            </div>
          </div>
          <div class="row">
            <div class="col s12">
              <label>Publication Type</label>
              <select required aria-required="true" name="type">
                <option value="">Select</option>
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
            <div class="col s12">
              <div class="file-field input-field">
                <div class="btn">
                  <span>Add Photo</span>
                  <input type="file" onchange="imgToBase64()">
                  <input type="hidden" name="image" id="base64Img"/>
                </div>
                <div class="file-path-wrapper">
                  <input class="file-path validate" placholder="Select a file.." type="text">
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col s12">
              <table style="cell-spacing: 0px">
                <tbody>
                <tr>
                  <th><p class="flow-text">Price: </p></th>
                  <th><i style="font-weight:normal">$</i></th>
                  <th style="padding-top: 40px">
                    <input style="height:1.5rem;font-weight: normal" pattern="(0\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\.([0-9]{1,2}))?)" name="price" aria-required="" required="" placeholder="" class="validate center" type="text">
                  </th>
                  <th><p class="flow-text">Quantity: </p></th>
                  <th style="padding-top: 40px">
                    <input style="height:1.5rem;font-weight: normal" name="quantity" aria-required="" required="" placeholder="0" class="validate center" type="text"/>
                  </th>
                  <th><p class="flow-text">Duration: </p></th>
                  <th style="padding-top: 40px">
                    <input style="height:1.5rem;font-weight: normal" name="duration" aria-required="" required="" class="validate center" type="text" pattern="^[0-9]*$"/>
                  </th>
                </tr>
                </tbody>
              </table>
            </div>
          </div>


          <div class="row">

            <div class="col s12 center">
              <br>
              <input type="hidden" name="action" value="registerItem"/>
              <button class="btn waves-effect waves-light" type="submit" value="registerItem">Register Item
                <i class="material-icons right">send</i>
              </button>
              <br><br>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>