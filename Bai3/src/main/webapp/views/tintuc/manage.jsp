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

        input {
            text-align: center;
            border: none;
            font-size: 16px;
        }

        select {
            padding: 5px 100px;
            box-sizing: border-box;
            border: 2px solid black
        }

        h2 a {
            font-size: 20px;
        }

        button {
            padding: 3px 15px;
            border-radius: 5px;
            background-color: red;
            color: white;
            border: none;
            font-weight: bold;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Quản lý tin tức</h1>
<h2 style="margin-bottom: 30px"><a href="views/home.jsp">Trang chủ</a></h2>
<table border="1">
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
        <th>
            Lựa chọn
        </th>

    </tr>
    <c:forEach var="tinTuc" items="${tinTucs}">
        <form method="post" action="tintucs">
            <tr>
                <td>
                    <input name="id" value="${tinTuc.maTinTuc}"/>
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
                <td>
                    <button onclick="return confirm('Ban có chắc muốn xóa')"
                            type="submit" name="action" value="delete">Xóa
                    </button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>


</body>
</html>
