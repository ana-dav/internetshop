<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<h4 style="color: darkred">${errorMsg}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    <ul>
        <li>Type in your login <input type="text" name="login"></li>
        <li>Type in your pass <input type="password" name="pass"></li>
        <li><button type="submit">Login</button></li>
    </ul>
</form>
</body>
</html>
