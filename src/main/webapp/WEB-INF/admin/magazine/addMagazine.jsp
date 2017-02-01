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

    <title>Newsstand - <fmt:message key="admin" bundle="${bundle}"/> - <fmt:message key="addMagazine" bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-8">

        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/magazines/add">
            <fieldset>

                <legend><fmt:message key="addMagazine" bundle="${bundle}"/></legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="title">
                        <fmt:message key="title" bundle="${bundle}"/>
                    </label>
                    <div class="col-md-4">
                        <input id="title" name="title" placeholder="title" class="form-control input-md" type="text" required>

                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="quantity">
                        <fmt:message key="quantity" bundle="${bundle}"/>
                    </label>
                    <div class="col-md-4">
                        <input id="quantity" name="quantity" class="form-control input-md" type="number" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="price">
                        <fmt:message key="price" bundle="${bundle}"/>
                    </label>
                    <div class="col-md-4">
                        <input id="price" name="price" placeholder="" class="form-control input-md" type="number"
                               step="0.01" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="publisher">
                        <fmt:message key="publisher" bundle="${bundle}"/>
                    </label>
                    <div class="col-md-4">
                        <select id="publisher" name="publisher" class="form-control">
                            <c:forEach items="${publishers}" var="publisher">
                                <option value="${publisher.id}">${publisher.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="category">
                        <fmt:message key="category" bundle="${bundle}"/>
                    </label>
                    <div class="col-md-4">
                        <select id="category" name="category" class="form-control">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="description">
                        <fmt:message key="description" bundle="${bundle}"/>
                    </label>
                    <div class="col-md-4">
                        <textarea class="form-control" id="description" name="description"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label"></label>
                    <div class="col-md-4">
                        <button id="submit" name="submit" type="submit" class="btn btn-primary">
                            <fmt:message key="add" bundle="${bundle}"/>
                        </button>
                    </div>
                </div>

            </fieldset>
        </form>


        <a class='btn btn-default' href="${pageContext.request.contextPath}/admin/magazines?p=1&s=10">
            <span class="glyphicon glyphicon-chevron-left"></span> <fmt:message key="back" bundle="${bundle}"/></a>

    </div>

    <div class="col-md-2"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
