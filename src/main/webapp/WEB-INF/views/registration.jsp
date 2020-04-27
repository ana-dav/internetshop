<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Please provide your login details</h1>
<h4 style="color: darkred">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Type in your login <input type="text" name="login">
    Type in your pass <input type="password" name="pass">
    Confirm your pass <input type="password" name="pass-confirm">
    <button type="submit">Register</button>
</form>
</body>
</html>
