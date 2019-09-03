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

    <c:forEach items="${categoryList}" var="categoryFromList">
        <form:checkbox path="categories" value="${categoryFromList}"/>${categoryFromList.name}
    </c:forEach>

    <br>
    ilość worków: <form:input type="number" path="quantity"/>
    <br>


     <c:forEach items="${institutionsList}" var="institutionFromList">
        <form:radiobutton path="institution" value="${institutionFromList}"/>${institutionFromList.name}
     </c:forEach>
    <br>

    kod pocztowy: <form:input path="zipCode" /><br>
    ulica: <form:input path="street" /><br>
    miejscowość: <form:input path="city"/><br>
    komentarz do dobioru rzeczy: <form:textarea path="pickUpComment"/><br>
    data: <form:input type="date" path="pickUpDate"/><br>
    godzina: <form:input type="time" path="pickUpTime" /><br>

    <br>
    <input type="submit" value="Dodaj" />
</form:form>

</body>
</html>