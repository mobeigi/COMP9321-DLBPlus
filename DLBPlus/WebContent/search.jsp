<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="ShoppingCart" class="edu.unsw.comp9321.ShoppingCart"
	scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Bibliographic Library | Advanced Search</title>

    <link rel="stylesheet" type="text/css" href="theme/styles.css" />

</head>
<body>
	<form action = 'setup' method = 'GET'>
	
	</form>
    <div id="page3">

        <center><h1>Bibliographic Library</h1></center>
        
        <form id="aSearchForm" action="setup" method="post">
    		<fieldset>
               	<h2>Title:</h2><input id="textbox" name="searchTitle" type="text" /><br><br><br>
           		<h2>Author:</h2><input id="textbox" name="searchAuthor" type="text" /><br><br><br>
     		    <h2>Editor:</h2><input id="textbox" name="searchEditor" type="text" /><br><br><br>
     		    <h2>Volume:</h2><input id="textbox" name="searchVolume" type="text" /><br><br><br>
     		    <h2>Publisher:</h2><input id="textbox" name="searchPubber" type="text" /><br><br><br>
     		    <h2>ISBN:</h2><input id="textbox" name="searchISBN" type="text" /><br><br><br>
           		<h2>Year:</h2><input id="textbox" name="searchYear" type="text" maxlength="4" pattern="\d{4}"/><br><br><br>
                <h2>Publication Type: </h2><select id="dropdown" name="searchPubType" >
                	<option>Any</option>
                    <option>Article</option>
                    <option>Inproceedings</option>
                    <option>Proceedings</option>
                    <option>Book</option>
                    <option>Incollection</option>
                    <option>Phdthesis</option>
                    <option>Masterthesis</option>
                    <option>WWW</option>
                </select><br><br><br>
            </fieldset>
			<div align='right'>
	            <input type="hidden" name="action" value="aSearch"/>
				<a href ="cart.jsp">Shopping Cart</a>
	            <input type="submit" value="Search" id="button" />
  			</div>
        </form>
    </div>
    <form action="setup" method="POST">
		<input type="hidden" name="action" value="back"/>
		<center><input type="submit" value="Back" id="button"/></center>
		<br>    
    </form>
</body>
</html>


