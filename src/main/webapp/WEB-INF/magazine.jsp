<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%--Localization--%>
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="localization" var="bundle"/>
    <%----%>

    <title>Newsstand - ${magazine.title}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>

<navbar:navbar/>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-3">
            <p class="lead"><fmt:message key="categories" bundle="${bundle}"/></p>
            <div class="list-group">
                <c:forEach items="${categories}" var="category">
                    <a href="${pageContext.request.contextPath}/category?catId=${category.id}&p=1&s=6"
                       class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-6">

            <div class="thumbnail">
                <div class="caption-full">
                    <h4 class="pull-right">${magazine.price}₴  </h4>
                    <h3>${magazine.title}</h3>

                    <p><b><fmt:message key="publisher" bundle="${bundle}"/>: </b><a href="#">${magazine.publisher.title}</a></p>
                    <p><b><fmt:message key="category" bundle="${bundle}"/>: </b><a href="#">${magazine.category.name}</a></p>

                    <%--User logged in--%>
                    <c:if test="${sessionScope.authenticated != null &&
                                  sessionScope.authenticated == true &&
                                  sessionScope.role == 'USER'}">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/subscribe?id=${magazine.id}">
                            <fmt:message key="subscribe" bundle="${bundle}"/>
                        </a>
                    </c:if>

                    <%--User not logged in--%>
                    <c:if test="${sessionScope.authenticated == null}">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/login">
                            <fmt:message key="subscribe" bundle="${bundle}"/>
                        </a>
                    </c:if>

                    <hr>
                    <h5><b><fmt:message key="description" bundle="${bundle}"/></b></h5>
                    <p>${magazine.description}</p>
                </div>
            </div>

        </div>

        <div class="col-md-3">
            <a href="#" class="thumbnail">
                <img src="placehold.it/300x800" width="300" height="800"/>
            </a>
        </div>

    </div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>