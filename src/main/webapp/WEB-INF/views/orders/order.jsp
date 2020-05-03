<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"></jsp:include>
<html>
<head>
    <title>Order</title>
</head>
<body>
<h1>Order details</h1>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Product</td>
        <td>Price</td>
<%--        название элемента в листе--%>
        <c:forEach var="product" items="${products}">
    <tr>
        <td>
            <c:out value="${product.id}"/>
        </td>
        <td>
            <c:out value="${product.name}"/>
        </td>
        <td>
            <c:out value="${product.price}"/>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
