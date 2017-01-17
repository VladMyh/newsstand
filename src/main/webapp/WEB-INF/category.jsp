<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Newsstand - ${category.name}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-3">
            <p class="lead">Categories</p>
            <div class="list-group">
                <c:forEach items="${categories}" var="category">
                    <a href="${pageContext.request.contextPath}/category?catId=${category.id}&p=1&s=6"
                       class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-9">

            <div class="row">

                <c:if test="${currSize == 0}">
                    <h1>Nothing here</h1>
                </c:if>

                <c:forEach items="${page}" var="magazine">
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="placehold.it/300x800" width="300" height="800"/>
                            <div class="caption">
                                <h4 class="pull-right">$${magazine.price}</h4>
                                <h4><a href="${pageContext.request.contextPath}/magazine?id=${magazine.id}">${magazine.title}</a>
                                </h4>
                                <p>${magazine.description}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="row">
                <ul class="pager">
                    <c:if test="${pageNum > 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/category?catId=${category.id}&p=${pageNum-1}&s=${pageSize}">
                            <span aria-hidden="true">&larr;</span>
                        </a>
                    </li>
                    </c:if>

                    <c:if test="${currSize == pageSize}">
                    <li>
                        <a href="${pageContext.request.contextPath}/category?catId=${category.id}&p=${pageNum+1}&s=${pageSize}">
                            <span aria-hidden="true">&rarr;</span>
                        </a>
                    </li>
                    </c:if>
                </ul>
            </div>

        </div>

    </div>

</div>
<!-- /.container -->
</body>
</html>
