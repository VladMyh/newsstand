<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="navbar"%>
<%@ taglib uri="/WEB-INF/price.tld" prefix="p"%>
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

    <title>Newsstand</title>

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

        <div class="col-md-9">

            <div class="row">

                <c:forEach items="${latestMagazines}" var="magazine">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <a href="${pageContext.request.contextPath}/magazine?id=${magazine.id}" class="thumbnail">
                                <img src="${pageContext.request.contextPath}/image?id=${magazine.id}"
                                     onerror="this.src='https://placeholdit.imgix.net/~text?txtsize=28&txt=300%C3%97400&w=300&h=400'"/>
                            </a>
                            <div class="caption">
                                <h4 class="pull-right"><p:price price="${magazine.price}"/></h4>
                                <h4>
                                    <a href="${pageContext.request.contextPath}/magazine?id=${magazine.id}">${magazine.title}</a>
                                </h4>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>

    </div>

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <a href="${pageContext.request.contextPath}/?locale=en">EN</a>
                <a href="${pageContext.request.contextPath}/?locale=ru">RU</a>
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
