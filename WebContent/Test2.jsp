<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.Calendar"
	import="csci4050.JavaTest"
	import="java.util.ArrayList"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookBay | JSP Test</title>
</head>
<body>
	<h1>Current Date: <%= Calendar.getInstance().getTime() %></h1>
	
	<%
		ArrayList<String> allUserData = (ArrayList<String>) request.getAttribute("allUserData");
	
		for(int i = 0; i < allUserData.size(); i++) {
			String user = allUserData.get(i);
			
			out.println("<p>" + user + "</p>");
		}
	%>
</body>
</html>