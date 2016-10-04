<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>DBL+ | Purchase Confirmed</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<form method="post" action="dblplus">
  <center>
    <table border="1" width="30%" cellpadding="5">
      <thead>
      <tr>
        <th colspan="2">Enter Information Here</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>User Name</td>
        <td><input type="text" name="uname" value="" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="pass" value="" /></td>
      </tr>
      <tr>
        <td><input type="submit" value="Submit" />
          <input type="hidden" name="action" value="confirmPurchase" /></td>
        <td><input type="reset" value="Reset" /></td>
      </tr>
      <tr>
        <td colspan="2">Already registered? <a href="index.jsp">Login Here</a></td>
      </tr>
      </tbody>
    </table>
  </center>
</form>


<jsp:include page="footer.jsp" />
</body>
</html>