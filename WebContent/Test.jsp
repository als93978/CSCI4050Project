<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.Calendar"
	import="csci4050.JavaTest"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookBay | JSP Test</title>
</head>
<body>
	<h1>Current Date: <%= Calendar.getInstance().getTime() %></h1>
	
	<% JavaTest javaTest = new JavaTest(); %>
	
	<h5>TestString from JavaTest.java: <%= javaTest.something %></h5>
</body>
</html>