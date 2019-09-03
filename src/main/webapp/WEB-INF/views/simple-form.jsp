<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>simple form</title>
</head>
<body>
<h2>SIMPLE FORM PAGE</h2>

<form:form modelAttribute="donation">

    <c:forEach items="${categories}" var="category">
        <form:checkbox path="categories" value="${category}"/>
    </c:forEach>

    <form:input type="number" path="quantity"/>

    <br>
    <input type="submit" value="Dodaj" />
</form:form>

</body>
</html>