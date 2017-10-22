<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Login</title>
</head>

<body>
<form method="POST" action="${contextPath}/login" class="form-signin">
    <h2>Login</h2>

    <div>
        <span>${message}</span>
        <input name="username" type="text" placeholder="Username"/> <br>
        <input name="password" type="password" placeholder="Password"/> <br>
        <span>${error}</span>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <input type="submit" value="Login">
        <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
    </div>
</form>

</body>
</html>