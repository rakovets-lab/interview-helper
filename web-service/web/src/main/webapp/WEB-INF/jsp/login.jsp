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
                <form action="${pageContext.request.contextPath}/api/login" method="post">
                    <div class="form-input">
                        <label for="label-username" class="form-label"> </label>
                        <input id="label-username" type="text" class="form-control" placeholder="Username"
                               name="username">
                        <div id="invalidCheck2Feedback" class="invalid-feedback">
                            You must agree before submitting.
                        </div>
                    </div>
                    <div class="form-input">
                        <label for="label-password" class="form-label"> </label>
                        <input id="label-password" type="password" class="form-control" placeholder="Password"
                               name="password">

                    </div>
                    <div class="text-center mt-4" style="color: red">
                    <c:if test="${error != null}">
                            <c:out value="${error}"/>
                    </c:if>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary mt-4 signup">Login</button>
                    </div>
                </form>
                <div class="text-center mt-4"><span>You aren't a member?</span> <a
                        href="<c:url value="/form/registration"/>"
                        class="text-decoration-none">Registration</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
