<jsp:include page="../parts/header.jsp"></jsp:include>
<!-- Material form subscription -->
<div class="card">

    <h4 class="card-header info-color white-text text-center py-4">
        <strong>Subscribe</strong>
    </h4>

    <!--Card content-->
    <div class="card-body px-lg-5">
        <h5 style="color: darkred">${errorMsg}</h5>
        <!-- Form -->
        <form action="${pageContext.request.contextPath}/login" method="post">

            <!-- Name -->
            <div class="md-form mt-3">
                <input type="text" name="login" id="materialSubscriptionFormPasswords" class="form-control">
                <label for="materialSubscriptionFormPasswords">Name</label>
            </div>

            <!-- E-mai -->
            <div class="md-form">
                <input type="password" name="pass" id="materialSubscriptionFormEmail" class="form-control">
                <label for="materialSubscriptionFormEmail">Password</label>
            </div>

            <!-- Sign in button -->
            <button class="btn btn-outline-info btn-rounded btn-block z-depth-0 my-4 waves-effect" type="submit">Sign in</button>

        </form>
        <!-- Form -->

    </div>

</div>
<!-- Material form subscription -->