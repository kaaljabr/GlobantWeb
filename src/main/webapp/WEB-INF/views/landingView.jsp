<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Globant Web</title>
</head>
<body>
	<center>
		<h2>Welcome ${username} :)</h2>		
		<div>
		<a href='${pageContext.request.contextPath}/login' >Go back to Login page</a>
		</div>
		<div>
		<a href='${pageContext.request.contextPath}/usersByProfession?profession=electrical engineer' >Show users filtered by PROFESSION and grouped by STATE</a>
		</div>
			
	</center>
</body>
</html>