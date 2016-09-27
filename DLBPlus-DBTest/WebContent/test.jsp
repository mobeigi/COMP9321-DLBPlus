<%@ page import="edu.unsw.comp9321.DBHelper" %>
<%@ page import="edu.unsw.comp9321.Publication" %><%--
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
	Publication p = db.GetRandomPublication();

	if (p != null) {
		out.println("ID: " + p.getId());
		out.println("<br />");
		out.println("Type: " + p.getType());
		out.println("<br />");
		out.println("Authors: " + p.getAuthors());
		out.println("<br />");
		out.println("Editors: " + p.getEditors());
		out.println("<br />");
		out.println("Title: " + p.getTitle());
		out.println("<br />");
		out.println("Pages: " + p.getPages());
		out.println("<br />");
		out.println("Year: " + p.getYear());
		out.println("<br />");
		out.println("Address: " + p.getAddress());
		out.println("<br />");
		out.println("Volume: " + p.getVolume());
		out.println("<br />");
		out.println("Number: " + p.getNumber());
		out.println("<br />");
		out.println("Month: " + p.getMonth());
		out.println("<br />");
		out.println("URLS: " + p.getUrls());
		out.println("<br />");
		out.println("EES: " + p.getEes());
		out.println("<br />");
		out.println("Cdrom: " + p.getCdrom());
		out.println("<br />");
		out.println("Cite: " + p.getCites());
		out.println("<br />");
		out.println("Publisher: " + p.getPublisher());
		out.println("<br />");
		out.println("Note: " + p.getNote());
		out.println("<br />");
		out.println("Crossref: " + p.getCrossref());
		out.println("<br />");
		out.println("Isbn: " + p.getIsbns());
		out.println("<br />");
		out.println("Series: " + p.getSeries());
		out.println("<br />");
		out.println("Venue: " + p.getVenues());
		out.println("<br />");
		out.println("Chapter: " + p.getChapter());
		out.println("<br />");
		out.println("Recprice: " + p.getRecprice());
		out.println("<br />");
		out.println("Rating: " + p.getRating());
		out.println("<br />");
		out.println("<hr />");
	}

%>
</body>
</html>
