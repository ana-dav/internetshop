<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Please provide your login details</h1>

<h4 style="color: darkred">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <ul>
        <li>Type in your name <input type="text" name="name"></li>
        <li>Type in your login <input type="text" name="login"></li>
        <li>Type in your pass <input type="password" name="pass"></li>
        <li>Confirm your pass <input type="password" name="pass-confirm"></li>
        <li><button type="submit">Register</button></li>
    </ul>
</form>
</body>
</html>
