<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
<h1>All orders page</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>User</th>
        <th>Details</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value ="${order.getId()}"/>
            </td>
            <td>
                <c:out value ="${order.getUser().getName()}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/order?id=${order.getId()}">Details</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/cart/delete?id=${order.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>