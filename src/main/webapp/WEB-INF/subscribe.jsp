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

    <title>Newsstand - Subscribe</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>

<body>

<navbar:navbar/>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-3"></div>

        <div class="col-md-6">

            <h1><fmt:message key="subscribe" bundle="${bundle}"/></h1>

            <div class="well">
                <h4><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                    <fmt:message key="details" bundle="${bundle}"/></h4>

                <p>Magazine name</p>
            </div>

            <div class="well">
                <h4><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
                    <fmt:message key="subscriptionType" bundle="${bundle}"/></h4>

                <select class="selectpicker">
                    <option selected><fmt:message key="oneMonth" bundle="${bundle}"/></option>
                    <option><fmt:message key="twoMonths" bundle="${bundle}"/></option>
                    <option><fmt:message key="threeMonths" bundle="${bundle}"/></option>
                    <option><fmt:message key="sixMonths" bundle="${bundle}"/></option>
                    <option><fmt:message key="oneYear" bundle="${bundle}"/></option>
                </select>

            </div>

            <div class="well">
                <h4><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                    <fmt:message key="payment" bundle="${bundle}"/></h4>

                <label class="radio-inline">
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> <fmt:message key="creditCard" bundle="${bundle}"/>
                </label>
                <label class="radio-inline">
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> <fmt:message key="paypal" bundle="${bundle}"/>
                </label>

            </div>

            <button type="submit" class="btn btn-primary"><fmt:message key="continue" bundle="${bundle}"/></button>

        </div>

        <div class="col-md-3"></div>

    </div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>