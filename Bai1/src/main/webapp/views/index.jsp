<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Users
</h1>
<br/>
<a href="add.jsp">Add new user</a>
<a href="${pageContext.request.contextPath}//users?action=list">List user</a>

</body>
</html>