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
                    <h4 class="pull-right"><p:price price="${magazine.price}"/></h4>
                    <h3>${magazine.title}</h3>

                    <p>
                        <b><fmt:message key="publisher" bundle="${bundle}"/>: </b><a>${magazine.publisher.title}</a>
                    </p>
                    <p>
                        <b><fmt:message key="category" bundle="${bundle}"/>: </b>
                        <a href="${pageContext.request.contextPath}/category?catId=${magazine.category.id}&p=1&s=6">${magazine.category.name}</a>
                    </p>

                    <div class="text-right">
                    <%--User logged in--%>
                    <c:if test="${sessionScope.authenticated != null &&
                                  sessionScope.authenticated == true &&
                                  sessionScope.role == 'USER' &&
                                  magazine.enabled == true}">
                        <c:if test="${!isSubscribed}">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/subscribe?id=${magazine.id}">
                                <fmt:message key="subscribe" bundle="${bundle}"/>
                            </a>
                        </c:if>

                        <c:if test="${isSubscribed}">
                            <button class="btn btn-success" disabled="disabled">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                <fmt:message key="subscribed" bundle="${bundle}"/>
                            </button>
                        </c:if>
                    </c:if>

                    <c:if test="${magazine.enabled == false}">
                        <button class="btn btn-danger" disabled="disabled">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            <fmt:message key="disabled" bundle="${bundle}"/>
                        </button>
                    </c:if>

                    <%--User is admin--%>
                    <c:if test="${sessionScope.authenticated != null &&
                                  sessionScope.authenticated == true &&
                                  sessionScope.role == 'ADMIN'}">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/magazines/edit?id=${magazine.id}">
                            <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                            <fmt:message key="edit" bundle="${bundle}"/>
                        </a>
                    </c:if>

                    <%--User not logged in--%>
                    <c:if test="${sessionScope.authenticated == null &&
                                  magazine.enabled == true}">
                        <a class="btn btn-primary " href="${pageContext.request.contextPath}/login">
                            <fmt:message key="subscribe" bundle="${bundle}"/>
                        </a>
                    </c:if>
                    </div>

                    <div>
                    <hr>
                    <h5><b><fmt:message key="description" bundle="${bundle}"/></b></h5>

                    <c:if test="${magazine.description != ''}">
                        <p>${magazine.description}</p>
                    </c:if>
                    <c:if test="${magazine.description == ''}">
                        <p><fmt:message key="noDescription" bundle="${bundle}"/></p>
                    </c:if>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-md-3">
            <a href="#" class="thumbnail">
                <c:if test="${magazine.imageId != 0}">
                    <img src="${pageContext.request.contextPath}/image?id=${magazine.imageId}"/>
                </c:if>
                <c:if test="${magazine.imageId == 0}">
                    <img src="https://placeholdit.imgix.net/~text?txtsize=28&txt=300%C3%97400&w=300&h=400"/>
                </c:if>
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