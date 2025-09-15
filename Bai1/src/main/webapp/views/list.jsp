<%--
  Created by IntelliJ IDEA.
  User: Huu Thai
  Date: 9/13/2025
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
     <link rel="stylesheet" type="text/css" href="styles/main.css" />
</head>
<body>
<div>
    <h1>User Management</h1>
     <h2>
         <a href="views/add.jsp">Add New User</a>
     </h2>
     <h2>List Users</h2>
    <table border="1">
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Gender</th><th>Date of Birth</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.gender}</td>
                <td>${user.date}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
