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
    <h1>Тема: ${requestScope.theme.name}</h1>
    <div class="accordion accordion-flush" id="accordion-flush">
        <c:set var="count" value="0" scope="page"/>
        <c:forEach items="${requestScope.theme.questions}" var="question">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <div class="accordion-item">
                <h2 class="accordion-header" id="flush-heading-${count}">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#flush-collapse-${count}" aria-expanded="false"
                            aria-controls="flush-collapse-${count}">
                        <strong>Вопрос ${count}: ${question.content}</strong>
                    </button>
                </h2>
                <div id="flush-collapse-${count}" class="accordion-collapse collapse"
                     aria-labelledby="flush-heading-${count}" data-bs-parent="#accordion-flush">
                    <div class="accordion-body">${question.answer.content}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
