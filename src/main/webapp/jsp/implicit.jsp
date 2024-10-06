<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String username = request.getParameter("username");
		out.println("Welcome " + username);
		
		response.setContentType("text/html");
		
		session.setAttribute("userName", "JohnDoe");
		application.setAttribute("appName", "MyJSPPage");
		String initParams = config.getInitParameter("someParameter");
		
		pageContext.setAttribute("someAttr","value", PageContext.REQUEST_SCOPE);
	%>
	
	<br/>
	<%= session.getAttribute("userName") %>
	<br/>
	<%= application.getAttribute("appName") %>
	<br/>
	<%= pageContext.getAttribute("someAttr", PageContext.REQUEST_SCOPE) %>
	
	<br/>
	<%= page %>

</body>
</html>