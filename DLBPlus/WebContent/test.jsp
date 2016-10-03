<%@ page import="edu.unsw.comp9321.DBHelper" %>
<%@ page import="edu.unsw.comp9321.Listing" %>
<%@ page import="java.util.Date" %>
<%@ page import="edu.unsw.comp9321.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="edu.unsw.comp9321.Listing" %>
<%@ page import="edu.unsw.comp9321.Admin" %>
<%@ page import="java.util.ArrayList" %>

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
  User u1 = db.CreateUser("joe1", "bobby", "Joe", "Joey","Blogs", "joeblogs@gmail.com", "10 main st", date, "1000000", "my dp");
  User u2 = db.CreateUser("joe2", "teddy23", "Joe", "Joey","Blogs", "joeblogs@gmail.com", "10 main st", date, "1000000", "my dp");
  User u3 = db.CreateUser("joe3", "@@879@@KAPPA", "Joe", "Joey","Blogs", "joeblogs@gmail.com", "10 main st", date, "1000000", "my dp");

  Admin a = db.CreateAdmin("moadmin", "123");

  if (u1 == null)
    u1 = db.GetUser("joe1");
  if (u2 == null)
    u2 = db.GetUser("joe2");
  if (u3 == null)
    u3 = db.GetUser("joe3");

  //Search listings
  Timestamp start = new Timestamp((long)1420070400*1000);
  Timestamp end = new Timestamp((long)1451606400*1000);
  List<String> authors = new ArrayList<String>();
  authors.add("Bob Dole");
  authors.add("Bard Main");

  List<String> editors = new ArrayList<String>();
  editors.add("Sir William Franklin");
  editors.add("Sir Franklin William");

  List<String> venues = new ArrayList<String>();
  venues.add("UNSW");
  venues.add("USYD");

  List<String> urls = new ArrayList<String>();
  urls.add("http://www.google.com");

  List<String> ees = new ArrayList<String>();
  ees.add("http://www.refref.com/somelink/blah");

  List<String> cites = new ArrayList<String>();
  cites.add("JENOV-1");
  cites.add("JENOV-12");
  cites.add("JENOV-13");

  List<String> isbns = new ArrayList<String>();
  isbns.add("5059494126594");

  db.CreateListing(u3, 10, start, end, 99.99, "test image", Listing.Type.ARTICLE, authors, editors, "How to be ninja", venues,
    "12p", 2014, "10 main st", "XII", "2nd", "january", urls, ees, "ROM2", cites, "Penguin", "This is a note", "CR-120-23",
    isbns, "1st series", "18", "5");

%>
</body>
</html>