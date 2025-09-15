<%--
  Created by IntelliJ IDEA.
  User: Huu Thai
  Date: 9/15/2025
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Cart Page</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css"
          integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 800vh;
        }

        .table_center {
            margin-left: auto;
            margin-right: auto;
            width: 80%
        }
    </style>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="row">
    <div class="container">
        <h3 class="text-center">Shopping Carts</h3>
        <hr>
        <br>
        <c:if test="${empty sessionScope.cart}">
            <h4 class="text-center text-danger">Your cart is empty</h4>
        </c:if>
        <c:if test="${not empty sessionScope.cart}">
            <table class="table table-striped table_center">
                <thead class="alert alert-secondary">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Photo</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Sub Total</th>
                    <th>Option</th>
                </tr>
                </thead>
                <c:forEach var="item" items="${sessionScope.cart}">
                    <c:set var="total"
                           value="${total + item.product.price * item.quantity }"></c:set>
                    <form action="${pageContext.request.contextPath }/cart" method="get">
                        <input type="hidden" name="action" value="update">
                        <tr>
                            <td>${item.product.id}</td>
                            <td>${item.product.name}</td>
                            <td>
                                <img src="${pageContext.request.contextPath }/images/${item.product.image}"
                                     width="120">
                            </td>
                            <td>${item.product.price}</td>
                            <td>
                                <input type="hidden" name="id"
                                       value="${item.product.id}">
                                <input type="number" name="quantity"
                                    value="${item.quantity}" min="1"
                                    style="width:60px">
                            </td>
                            <td>${item.product.price * item.quantity}</td>
                            <td>
                                <div style="display: flex; gap: 10px">
                                    <a href="${pageContext.request.contextPath }/cart?action=remove&id=${item.product.id}"
                                       onclick="return confirm('Are you sure?')">Remove</a>
                                    <button style="border: none; text-decoration: underline; background-color: transparent"
                                            onclick="return confirm('Are you sure?')"
                                            type="submit">
                                        Update
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <tr>
                    <td colspan="6">Total</td>
                    <td>${total }</td>
                </tr>
            </table>
        </c:if>
        <br>
        <h6 class="text-center">
            <a href="${pageContext.request.contextPath }/products">Continue
                Shopping</a></h6>
    </div>
</div>

</body>
</html>
