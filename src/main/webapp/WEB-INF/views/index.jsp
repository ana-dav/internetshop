<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello world</h1>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/users/all">All Users</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/products/all">All Products</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/registration">Registration</a>
    </li>

    <li>
        <a href="${pageContext.request.contextPath}/cart">Cart</a>
    </li>
</ul>
</body>
</html>
