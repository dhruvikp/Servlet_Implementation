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
	int sum = 5 + 10;
	out.println("The sum is:" + sum);
	%>

	<%!int counter = 0;

	public int incrementCounter() {
		return ++counter;
	}%>
	<br /> The current value of the counter is :
	
	<%=incrementCounter()%>

</body>
</html>