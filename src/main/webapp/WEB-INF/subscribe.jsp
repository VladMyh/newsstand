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

    <title>Newsstand - <fmt:message key="subscribe" bundle="${bundle}"/></title>

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

            <form method="post" action="${pageContext.request.contextPath}/subscribe">

                <input type="hidden" name="id" value="${magazine.id}">

                <h1><fmt:message key="subscribe" bundle="${bundle}"/></h1>

                <div class="well">
                    <h4><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                        <fmt:message key="details" bundle="${bundle}"/></h4>

                    <p><fmt:message key="magazine" bundle="${bundle}"/>: ${magazine.title}<label></label></p>
                    <p><fmt:message key="perMonth" bundle="${bundle}"/>: <label id="basePrice"><p:price price="${magazine.price}"/></label></p>
                    <hr>
                    <p><fmt:message key="total" bundle="${bundle}"/>: <label id="price"><p:price price="${magazine.price}"/></label></p>
                </div>

                <div class="well">
                    <h4><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
                        <fmt:message key="subscriptionType" bundle="${bundle}"/></h4>

                    <select id="subscriptionType" name="type" onchange="onChange()" class="selectpicker">
                        <c:forEach items="${subscriptionTypes}" var="type">
                            <option value="${type.id}" multiplier="${type.priceMultiplier}">${type.name}</option>
                        </c:forEach>
                    </select>

                </div>

                <div class="well">
                    <h4><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                        <fmt:message key="payment" bundle="${bundle}"/></h4>

                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" checked> <fmt:message key="creditCard" bundle="${bundle}"/>
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> <fmt:message key="paypal" bundle="${bundle}"/>
                    </label>

                </div>

                <button type="submit" class="btn btn-primary"><fmt:message key="continue" bundle="${bundle}"/></button>

            </form>
        </div>

        <div class="col-md-3"></div>

    </div>

</div>
<!-- /.container -->

<script>
    function onChange() {
        var select = document.getElementById("subscriptionType");
        var label = document.getElementById("price");

        var basePrice = parseFloat(document.getElementById("basePrice").innerHTML);
        var multiplier = parseFloat(select.options[select.selectedIndex].getAttribute("multiplier"));

        label.innerHTML = (basePrice * multiplier).toString();
    }
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>