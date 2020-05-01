<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
    <form  method="post" action="${pageContext.request.contextPath}/newprod">
        <p>Add new product</p>
        <label>Name:</label>
        <input name="name" type="text" placeholder="Name"
               value="${empty name?'product name':'value is NOT empty or null'}">
        <label>Price:</label>
        <input name="price" type="text" placeholder="Price"
               value="${empty price?'product price':'value is NOT empty or null'}">
        <button class="btn btn-info btn-block my-4" type="submit">Add</button>
    </form>
</div>
</body>
</html>
