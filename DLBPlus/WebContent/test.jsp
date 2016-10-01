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

	if (u1 == null)
		u1 = db.GetUser("joe1");
	if (u2 == null)
		u2 = db.GetUser("joe2");
	if (u3 == null)
		u3 = db.GetUser("joe3");

	//Search listings
	Listing l = new Listing();
	l.setSellerid(u2.getId());
	l.setQuantity(10);
	l.setListdate(new Timestamp(1000)); //1 second
	Date now = new Date();
	l.setEnddate(new Timestamp(now.getTime())); //today

	l.setType("article");

	List<String> authors = new ArrayList<String>();
	authors.add("Bob Ian");
	authors.add("Richard Bard");
	l.setAuthors(authors);

	List<Listing> listings = db.SearchListings(l, null, null, false, false);

	for (Listing lRes : listings) {
		System.out.println("ID:" + lRes.getId() + ", Title:" + lRes.getTitle());
	}

%>
</body>
</html>
