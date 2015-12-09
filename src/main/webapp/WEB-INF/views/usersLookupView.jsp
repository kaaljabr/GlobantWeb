<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Globant Web | Users List</title>
	<link href="<c:url value="/css/users-lookup-style.css" />" rel="stylesheet">
</head>
<body>
	<center>
		<h2>Users List is:</h2>
		<div>
			<a href='${pageContext.request.contextPath}/login'>Go back to
				Login page</a>
		</div>

		<div>
			<table>
				<thead>
					<tr>
						<th>Username</th>
						<th>State</th>
						<th>Profession</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="element">
						<tr>
						<td><strong>${element.username}</strong></td>
						<td>${element.state}</td>
						<td>${element.profession}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

	</center>





</body>
</html>