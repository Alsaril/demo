<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Вход</title>
</head>

<body>
<form method="POST" action="${contextPath}/login" class="form-signin">
    <h2>Вход</h2>

    <div>
        <c:if test="${error != null}">
            <div id="error">
                <spring:message code="message.badCredentials">
                </spring:message>
            </div>
        </c:if>
        <input name="username" type="text" placeholder="Логин"/> <br>
        <input name="password" type="password" placeholder="Пароль"/> <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Войти">
        <h4 class="text-center"><a href="${contextPath}/registration">Создать аккаунт</a></h4>
    </div>
</form>

</body>
</html>