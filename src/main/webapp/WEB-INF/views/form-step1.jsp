<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
  </head>
  <body>

  <%@ include file="header-form.jsp" %>

    <section class="form--steps">
      <div class="form--steps-instructions">
        <div class="form--steps-container">
          <h3>Ważne!</h3>
          <p data-step="1" class="active">
            Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
            wiedzieć komu najlepiej je przekazać.
          </p>
        </div>
      </div>

      <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form action="/donation/2" method="post">
          <!-- STEP 1: class .active is switching steps -->
          <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>
            <c:forEach var="category" items="${categories}">
            <div class="form-group form-group--checkbox">
              <label>
                  <input
                  type="checkbox"
                  name="categories"
                  value=${category.id}
                />
                <span class="checkbox"></span>
                <span class="description">${category.name}</span>
              </label>
            </div>
            </c:forEach>


            <div class="form-group form-group--buttons">
              <button type="submit" class="btn">Dalej</button>
            </div>
          </div>
        </form>

      </div>
    </section>

  <%@ include file="footer.jsp" %>

  <script src="<c:url value="../resources/js/app.js"/>"></script>
  </body>
</html>
