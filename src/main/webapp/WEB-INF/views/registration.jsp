<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
</head>

<body>

<form:form method="POST" modelAttribute="userForm">
    <h2>Create your account</h2>
    <spring:bind path="username">
        <div>
            <form:input type="text" path="username" placeholder="Username"
                        autofocus="true"/>
            <form:errors path="username"/>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="password" path="password" class="form-control" placeholder="Password"/>
            <form:errors path="password"/>
        </div>
    </spring:bind>

    <input type="submit" value="Submit">
</form:form>
</body>
</html>