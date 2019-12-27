<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@ include file="header-nonloged.jsp" %>
</header>

<section class="login-page">

<%--    <form:form method="POST" modelAttribute="guest">

        E-mail: <form:input path="email"/><br>
        <form:errors path="email" cssClass="error"/><br>
        Hasło: <form:password path="password"/><br>
        <form:errors path="password" cssClass="error"/><br>
        Imie: <form:input path="name"/><br>
        <form:errors path="name" cssClass="error"/><br>
        Nazwisko: <form:input path="surname"/><br>
        <form:errors path="surname" cssClass="error"/><br>
        <input type="submit" value="Zarejestruj">
    </form:form>--%>

    <h2>Załóż konto</h2>
    <form name="f" th:object="${user}" th:action="@{/register}" method="post">
        <div class="form-group">
            <input type="email" name="username" placeholder="Email" />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="matchingPassword" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form>
</section>

    <%@ include file="footer.jsp" %>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>