<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Профиль</title>
</head>
<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <h2>Добро пожаловать, ${pageContext.request.userPrincipal.name}</h2>
    <h3>Ваш баланс: ${balance}</h3>
    <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
</c:if>
</body>
</html>