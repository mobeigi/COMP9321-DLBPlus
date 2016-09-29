<%@ page import="edu.unsw.comp9321.DBHelper" %>
<%@ page import="edu.unsw.comp9321.Publication" %>
<%@ page import="java.util.Date" %>
<%@ page import="edu.unsw.comp9321.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="edu.unsw.comp9321.Listing" %>
<%@ page import="edu.unsw.comp9321.Admin" %>

<%--
  Created by IntelliJ IDEA.
  User: Mohammad
  Date: 27/09/2016
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Test</title>
</head>
<body>
<%
	DBHelper db = new DBHelper();	//Note this should be called once per server, not per page load or multiple times
	db.init();
	
  //Make some users
  Date date = new Date();
  User u1 = db.CreateUser("joe1", "bobby", "Joe", "Blogs", "joeblogs@gmail.com", "10 main st", date, "1000000", "my dp");
  User u2 = db.CreateUser("joe2", "teddy23", "Joe", "Blogs", "joeblogs@gmail.com", "10 main st", date, "1000000", "my dp");
  User u3 = db.CreateUser("joe3", "@@879@@KAPPA", "Joe", "Blogs", "joeblogs@gmail.com", "10 main st", date, "1000000", "my dp");

  if (u1 == null)
    u1 = db.GetUser("joe1");
  if (u2 == null)
    u2 = db.GetUser("joe2");
  if (u3 == null)
    u3 = db.GetUser("joe3");

  //Test SetUserStatus
  db.SetUserStatus(u1, true);
  System.out.println("ID: " + u1.getId() + ", Status: " + u1.getAcctstatus());
  db.SetUserStatus(u1, false);
  System.out.println("ID: " + u1.getId() + ", Status: " + u1.getAcctstatus());


%>
</body>
</html>
