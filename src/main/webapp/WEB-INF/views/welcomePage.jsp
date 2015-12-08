<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body>
	<center>
		<h2>Welcome</h2>
		<c:out value="${sessionScope['scopedTarget.user'].password}"/>
		<h2>
		
						 <c:forEach items="${sessionScope}" var="entry">
							    ${entry.key} = ${entry.value}<br>
							</c:forEach>
		</h2>
		</center>
</body>
</html>