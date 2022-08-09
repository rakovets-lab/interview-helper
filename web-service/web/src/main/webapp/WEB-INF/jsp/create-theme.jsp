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
    <form action="${pageContext.request.contextPath}/api/themes" method="post">
        <div class="mb-3">
            <label for="label-theme" class="form-label">Theme</label>
            <select id="label-theme" class="form-select" class="form-control" type="" name="themeId">
                <c:forEach items="${requestScope.themes}" var="theme">
                    <option value="${theme.id}">${theme.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="label-question" class="form-label">Question</label>
            <input id="label-question" class="form-control" type="text" name="question"/>
        </div>
        <div class="mb-3">
            <label for="label-answer" class="form-label">Answer</label>
            <textarea id="label-answer" class="form-control" rows="5" name="answer"></textarea>
        </div>
        <button class="btn btn-secondary" disabled>Create</button>
    </form>
</div>
</body>
</html>
