<%--
  Created by IntelliJ IDEA.
  User: Huu Thai
  Date: 9/17/2025
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 20px;
            height: 100%;
            margin: 0;                 /* Xóa margin mặc định */
            display: flex;
            flex-direction: column;
        }
        .navbar {
            display: flex;
            justify-content: center;
            padding-top: 10px;
            padding-bottom: 10px;
            border: 2px solid black;
         
        }
        a {
            color: black;
            border-right: 1px solid black;
            padding: 0 30px;
        }
        a:last-child {
            border-right: none;
        }
        footer {
            border: 2px solid black;
            margin-top: auto;

        }
    </style>
</head>
<body>
<img src="../images/home.png" alt=""
     style="width: 100%; height: 150px; object-fit: cover">
     <header class="navbar" style="">
         <a href="${pageContext.request.contextPath}/tintucs?action=list&maDanhMuc=-1">Danh sách tin tức</a>
         <a href="tintuc/add.jsp">Thêm tin tức mới</a>
         <a href="${pageContext.request.contextPath}/tintucs?action=manage">Chức năng quản lý</a>
     </header>
 <footer>
     <p style="text-align: center">Lê Văn Hửu Thái - 22685411 - DHKTPM18BTT</p>
 </footer>
</body>
</html>
