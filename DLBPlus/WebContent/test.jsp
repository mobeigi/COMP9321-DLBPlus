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

	// remove the listing
	db.RemoveListing(1);

%>
</body>
</html>
