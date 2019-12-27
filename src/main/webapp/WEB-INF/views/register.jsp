<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<%--    <form:form method="POST" modelAttribute="user">
        E-mail: <form:input path="email"/><br>
        <form:errors path="email" cssClass="error"/><br>
        Hasło: <form:password path="password"/><br>
        <form:errors path="password" cssClass="error"/><br>
        Imie: <form:input path="firstName"/><br>
        <form:errors path="firstName" cssClass="error"/><br>
        Nazwisko: <form:input path="lastName"/><br>
        <form:errors path="lastName" cssClass="error"/><br>
        <input type="submit" value="Zarejestruj">
    </form:form>--%>

    <h2>Załóż konto</h2>
    <form:form method="POST" modelAttribute="userDTO">

        <div class="form-group">
            <form:input path="username" type="email" placeholder="Email" />
            <form:errors class="validation" path="username"/>

        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło" />
            <form:errors class="validation" path="password"/>


        </div>
        <div class="form-group">
            <form:input path="matchingPassword" type="password" placeholder="Powtórz hasło" />
            <form:errors class="validation" path="matchingPassword"/>

        </div>

        <div class="form-group form-group--buttons">
            <a href="login.html" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>

</section>

    <%@ include file="footer.jsp" %>

<script src="<c:url value="resources/js/app.js"/>"></script>
</body>
</html>