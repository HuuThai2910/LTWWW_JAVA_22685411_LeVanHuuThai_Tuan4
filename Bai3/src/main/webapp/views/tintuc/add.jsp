<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Huu Thai
  Date: 9/18/2025
  Time: 2:59 PM
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
            font-family: Arial, sans-serif;
            font-size: 16px;
            background-color: #f4f6f9;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding: 40px;
        }

        .container {
            background: #fff;
            padding: 25px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 50%;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 22px;
            color: #333;
        }

        form div {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px;
        }

        label {
            margin-bottom: 6px;
            font-weight: bold;
            color: #444;
        }

        input, select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
            outline: none;
            transition: border 0.3s;
        }

        input:focus, select:focus {
            border-color: #007bff;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            background: #007bff;
            color: white;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background: #0056b3;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Thêm tin tức mới</h1>

    <form action="tintucs" method="post">
        <!-- Mã Tin Tức -->
        <div>
            <label>Mã Tin Tức</label>
            <input type="text" name="maTinTuc" value="${tinTuc.maTinTuc}"
                   placeholder="Mã Tin Tức"/>
            <c:forEach var="err" items="${errors}">
                <c:if test="${err.propertyPath == 'maTinTuc'}">
                    <span style="color:red">${err.message}</span>
                </c:if>
            </c:forEach>
        </div>

        <!-- Tiêu đề -->
        <div>
            <label>Tiêu Đề</label>
            <input type="text" name="tieuDe" value="${tinTuc.tieuDe}"
                   placeholder="Tiêu Đề"/>
            <c:forEach var="err" items="${errors}">
                <c:if test="${err.propertyPath == 'tieuDe'}">
                    <span style="color:red">${err.message}</span>
                </c:if>
            </c:forEach>
        </div>

        <!-- Liên kết -->
        <div>
            <label>Liên Kết</label>
            <input type="text" name="lienKet" value="${tinTuc.lienKet}"
                   placeholder="Liên Kết"/>
            <c:forEach var="err" items="${errors}">
                <c:if test="${err.propertyPath == 'lienKet'}">
                    <span style="color:red">${err.message}</span>
                </c:if>
            </c:forEach>
        </div>

        <!-- Nội dung -->
        <div>
            <label>Nội dung</label>
            <textarea name="noiDung" rows="5">${tinTuc.noiDungTinTuc}</textarea>
            <c:forEach var="err" items="${errors}">
                <c:if test="${err.propertyPath == 'noiDungTinTuc'}">
                    <span style="color:red">${err.message}</span>
                </c:if>
            </c:forEach>
        </div>

        <!-- Mã Danh Mục -->
        <div>
            <label>Danh Mục</label>
            <select name="maDanhMuc">
                <c:forEach var="danhMuc" items="${danhMucs}">
                    <option value="${danhMuc.maDanhMuc}">${danhMuc.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" name="action" value="add">Thêm</button>
    </form>
</div>
</body>

</html>
