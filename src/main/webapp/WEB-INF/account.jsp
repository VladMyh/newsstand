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

    <title>Newsstand - <fmt:message key="account" bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<div class="container">
    <div class="row">

        <div class="row">
            <div class="col-md-1"></div>

            <div class="col-md-11">
                <h1 class="">${user.firstName} ${user.lastName}</h1>
            </div>

            <div class="col-md-1"></div>
        </div>
        <br>
        <div class="row">

            <div class="col-md-1"></div>

            <div class="col-md-3">
                <!--left col-->
                <ul class="list-group">
                    <li class="list-group-item text-muted" contenteditable="false">
                        <fmt:message key="account" bundle="${bundle}"/>
                    </li>
                    <li class="list-group-item text-right" ng-show="userInfo.studentInfo.faculty !== null">
                        <span class="pull-left"><strong class=""><fmt:message key="email" bundle="${bundle}"/></strong></span> ${user.email}
                    </li>
                </ul>

            </div>
            <!--/col-3-->
            <div class="col-md-7" style="" contenteditable="false">
                <div class="panel panel-default target">
                    <div class="panel-heading" contenteditable="false"><fmt:message key="subscriptions" bundle="${bundle}"/></div>
                    <div class="panel-body">
                        <div class="row">

                            <c:if test="${isSubscriptionsEmpty}">
                                <p><fmt:message key="noSubscriptions" bundle="${bundle}"/></p>
                            </c:if>

                            <c:if test="${!isSubscriptionsEmpty}">
                                <c:forEach items="${subscriptions}" var="sub">

                                    <div class="col-md-12">
                                        <div class="thumbnail">
                                            <div class="caption">
                                                <h3>${sub.magazine.title}</h3>
                                                <span class="pull-right"><h3>${sub.price}₴</h3></span>

                                                <p><fmt:message key="from" bundle="${bundle}"/>: ${sub.startDate}</p>
                                                <p><fmt:message key="to" bundle="${bundle}"/>: ${sub.endDate}</p>

                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>
                            </c:if>

                        </div>

                    </div>

                </div>
            </div>

            <div class="col-md-1"></div>
        </div>

    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
