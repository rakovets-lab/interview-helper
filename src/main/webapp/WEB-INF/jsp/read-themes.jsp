<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/jstl-connect.jsp" %>
<html>
<head>
    <title>Interview Helper</title>
    <%@ include file="common/css-connect.jsp" %>
    <%@ include file="common/js-connect.jsp" %>
</head>
<body>
<%@ include file="common/header.jsp" %>
<div class="container">
    <table class="table table-borderless table-striped table-hover">
        <tr>
            <th scope="col">id</th>
            <th scope="col">Name</th>
        </tr>
        <c:forEach items="${requestScope.themes}" var="theme">
        <tr>
            <th scope="row">${theme.id}</th>
            <td><a href="${pageContext.request.contextPath}/view/themes/${theme.id}" class="link-dark">${theme.name}</a>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
