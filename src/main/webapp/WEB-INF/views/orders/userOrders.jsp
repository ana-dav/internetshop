<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../parts/header.jsp"></jsp:include>
<html>
<head>
    <title>All user orders</title>
</head>
<body>
<h1>User orders page</h1>

<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Details</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value ="${order.getId()}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/details?id=${order.getId()}">Details</a>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>
