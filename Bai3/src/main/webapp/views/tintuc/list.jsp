<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Huu Thai
  Date: 9/17/2025
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            margin: 0 auto;
            width: 1170px;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 6px;
            border: 2px solid black;
        }

        th, td {
            padding: 15px;
            text-align: center;
            border: 2px solid black;
        }

        select {
            padding: 5px 100px;
            border: 2px solid black
        }

        h2 a {
            font-size: 20px;
        }

    </style>
</head>
<body>
<h1>Danh sách tin tức</h1>

<div style="display: flex;  justify-content: space-between;">
    <div style="display: flex; align-items: center; justify-content: center; margin-bottom: 50px; gap: 30px">
        <h2>Danh Mục:</h2>
        <form action="tintucs" method="get">
            <input type="hidden" name="action" value="list">
            <select name="maDanhMuc" onchange="this.form.submit()">
                <option value="-1" ${param.maDanhMuc == "-1" ? 'selected' : ''}>
                    Tất cả
                </option>
                <c:forEach var="danhMuc" items="${danhMucs}">
                    <option value="${danhMuc.maDanhMuc}" ${param.maDanhMuc == danhMuc.maDanhMuc ? 'selected' : ''}>${danhMuc.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </form>
    </div>
    <h2><a href="views/home.jsp">Trang chủ</a></h2>
</div>
<table border="1">
    <c:if test="${empty tinTucs}">
        <h2 style="text-align: center">Danh mục này hiện chưa có tin tức
            nào</h2>
    </c:if>
    <c:if test="${not empty tinTucs}">
        <tr>
            <th>
                Mã Tin Tức
            </th>
            <th>
                Tiêu Đề
            </th>
            <th>
                Nội Dung Tin Tức
            </th>
            <th>
                Liên Kết
            </th>
        </tr>
        <c:forEach var="tinTuc" items="${tinTucs}">
            <tr>
                <td>
                        ${tinTuc.maTinTuc}
                </td>
                <td>
                        ${tinTuc.tieuDe}
                </td>
                <td>
                        ${tinTuc.noiDungTinTuc}
                </td>
                <td>
                    <a href="${tinTuc.lienKet}"
                       target="_blank">${tinTuc.lienKet}</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>


</table>

</body>
</html>
