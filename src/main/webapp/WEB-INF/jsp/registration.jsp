<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/jstl-connect.jsp" %>
<html>
<head>
    <title>Interview Helper</title>
    <%@ include file="common/css-connect.jsp" %>
    <%@ include file="common/js-connect.jsp" %>
</head>
<body>
<div class="container mt-5 mb-5">
    <div class="row d-flex align-items-center justify-content-center">
        <div class="col-md-6">
            <div class="card px-5 py-5"><span class="circle"><i class="fa fa-check"></i></span>
                <form action="${pageContext.request.contextPath}/api/registration" method="post">
                    <div class="form-input">
                        <label for="label-first-name" class="form-label"> </label>
                        <input id="label-first-name" type="text" class="form-control" placeholder="First Name"
                               name="first-name">
                    </div>
                    <div class="form-input">
                        <label for="label-last-name" class="form-label"> </label>
                        <input id="label-last-name" type="text" class="form-control" placeholder="Last Name"
                               name="last-name">
                    </div>
                    <div class="form-input">
                        <label for="label-email" class="form-label"> </label>
                        <input id="label-email" type="text" class="form-control" placeholder="Email address"
                               name="email">
                    </div>
                    <div class="form-input">
                        <label for="label-username" class="form-label"> </label>
                        <input id="label-username" type="text" class="form-control" placeholder="Username"
                               name="username">
                    </div>
                    <div class="form-input">
                        <label for="label-password" class="form-label"> </label>
                        <input id="label-password" type="password" class="form-control" placeholder="Password"
                               name="password">
                    </div>
                    <div class="form-check">
                        <label class="form-check-label" for="flexCheckChecked"> I agree all the statements </label>
                        <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked"
                               checked>
                    </div>
                    <div class="text-center mt-4" style="color: red">
                        <c:if test="${error != null}">
                            <c:out value="${error}"/>
                        </c:if>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary mt-4 signup">Registration</button>
                    </div>
                </form>

                <div class="text-center mt-4"><span>Already a member?</span> <a href="<c:url value="/form/login"/>"
                                                                                class="text-decoration-none">Login</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
