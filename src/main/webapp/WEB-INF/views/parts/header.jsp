<html>
<head>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/css/mdb.min.css" rel="stylesheet">
    <title>Internetshop</title>
</head>
<body>
<nav class="navbar fixed-top navbar-expand-lg navbar-dark primary-color scrolling-navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            aria-controls="navbarSupportedContent-555" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
                <a class="nav-link" href="${pageContext.request.contextPath}/products/all">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cart">Cart</a>
            </li>
            <li class="nav-item">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/users/all">All Users</a>
            </li>
            <li class="nav-item">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/products/admin">All Products</a>
            </li>
            <li class="nav-item">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/orders/all">All Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/injectData">Inject test data</a>
            </li>
        </ul>
    </div>
</nav>
