<jsp:include page="parts/header.jsp"></jsp:include>
<div class="container">
    <div class="row d-flex justify-content-center mt-5">
        <h5 class="card-header info-color white-text text-center">
            <strong>Sign up</strong>
        </h5>
        <div class="col-6 card">


    <div class="card-body pt-0">
        <h4 style="color: darkred">${message}</h4>

        <form method="post" action="${pageContext.request.contextPath}/registration">

            <div class="form-row">
                <div class="col">
                    <div class="md-form">
                        <input type="text" name="name" id="materialRegisterFormFirstName" class="form-control">
                        <label for="materialRegisterFormFirstName">First name</label>
                    </div>
                </div>
                <div class="col">
                    <div class="md-form">
                        <input type="text" name="login" id="materialRegisterFormLastName" class="form-control">
                        <label for="materialRegisterFormLastName">Login</label>
                    </div>
                </div>
            </div>
            <div class="md-form">
                <input type="password" name="pass" id="materialRegisterFormPassword" class="form-control" aria-describedby="materialRegisterFormPasswordHelpBlock">
                <label for="materialRegisterFormPassword">Password</label>
                <small id="materialRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">
                    * At least 8 characters
                </small>
            </div>
            <div class="md-form">
                <input type="password" name="pass-confirm" id="materialRegisterFormPhone" class="form-control" aria-describedby="materialRegisterFormPhoneHelpBlock">
                <label for="materialRegisterFormPhone">Confirm password</label>
                <small id="materialRegisterFormPhoneHelpBlock" class="form-text text-muted mb-4">
                    Please confirm you password
                </small>
            </div>

            <!-- Sign up button -->
            <button class="btn btn-outline-info btn-rounded btn-block my-4 waves-effect z-depth-0" type="submit">Sign in</button>

            <hr>

            <!-- Terms of service -->
            <p>By clicking
                <em>Sign up</em> you agree to our
                <a href="" target="_blank">terms of service</a>

        </form>
    </div>


        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp"></jsp:include>