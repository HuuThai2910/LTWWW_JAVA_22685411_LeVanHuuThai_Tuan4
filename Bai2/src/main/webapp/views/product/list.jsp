<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Huu Thai
  Date: 9/15/2025
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Cards</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css"
          integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f3f4f6;
            margin: 0;

        }

        .container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 20px;
            max-width: 1000px;
            margin: auto;
        }

        .product-card {
            background: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease;
            text-align: center;
        }

        .product-card:hover {
            transform: translateY(-5px);
        }

        .product-card img {
            width: 100%;
            height: 180px;
        }

        .card-body {
            padding: 15px;
        }

        .card-body h3 {
            margin: 10px 0;
            font-size: 18px;
            color: #111;
        }

        .price {
            font-size: 16px;
            font-weight: bold;
            color: #007bff;
            margin: 8px 0;
        }

        .quantity {
            display: inline-block;
            background: #e5e7eb;
            color: #333;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 13px;
            margin: 6px 0;
        }

        .btn-cart {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 15px;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.2s;
        }

        .btn-cart:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div style="margin: auto auto 20px auto; max-width: 1000px;">
    <h1 style="text-align: center; color: #007bff; font-size: 50px">List Product</h1>
    <div style="text-align: right;">
        <a href="${pageContext.request.contextPath}/cart?action=show"
           style="color: #007bff;">View Cart</a>
    </div>
</div>
<div class="container">
    <!-- Product 1 -->
    <c:forEach var="product" items="${products}">
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/images/${product.image}"
                 alt="Nokia Lumia">
            <div class="card-body">
                <h3>${product.name}</h3>
                <p class="price">${product.price}</p>
                <span class="quantity">Quantity: 1</span>
                <br>
                <a class="btn-cart"
                   href="${pageContext.request.contextPath}/cart?action=add&id=${product.id}">Add
                    To Cart</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>





