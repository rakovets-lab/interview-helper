<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="WEB-INF/jsp/common/jstl-connect.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Interview Helper</title>
    <%@ include file="WEB-INF/jsp/common/css-connect.jsp" %>
    <%@ include file="WEB-INF/jsp/common/js-connect.jsp" %>
</head>
<body>
<%@ include file="WEB-INF/jsp/common/header.jsp" %>
<main class="container">
    <div class="row">
        <h1 class="text-center">
            Opps...
        </h1>
        <div>
            <c:if test="${pageContext.errorData.statusCode == 404}">
                <p class="text-center">Page not found</p>
            </c:if>
            <c:if test="${pageContext.errorData.statusCode != 404}">
                <h3 class="text-center">
                    Error details
                </h3>
                <table class="table table-borderless">
                    <tr class="table-danger">
                        <th scope="row">Error:</th>
                        <td>${pageContext.exception}</td>
                    </tr>
                    <tr>
                        <th scope="row">URI:</th>
                        <td>${pageContext.errorData.requestURI}</td>
                    </tr>
                    <tr>
                        <th scope="row">Status code:</th>
                        <td>${pageContext.errorData.statusCode}</td>
                    </tr>
                    <tr>
                        <th scope="row">Stack trace:</th>
                        <td>
                            <c:forEach var="trace"
                                       items="${pageContext.exception.stackTrace}">
                                <code>${trace}</code>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
            </c:if>

        </div>
    </div>
</main>
</body>
</html>
