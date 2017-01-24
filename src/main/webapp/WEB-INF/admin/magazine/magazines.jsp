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

    <title>Newsstand - Admin - Magazines</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-8">

        <h1>Magazines</h1>

        <table class="table">
            <thead>
            <tr>
                <th>title</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Publisher</th>
                <th>Category</th>
                <th>Actions</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${page}" var="magazine">
                <tr>
                    <td>${magazine.title}</td>
                    <td>${magazine.quantity}</td>
                    <td>${magazine.price}</td>
                    <td>${magazine.category.name}</td>
                    <td>${magazine.publisher.title}</td>
                    <td>
                        <a class='btn btn-info btn-xs' href="#">
                            <span class="glyphicon glyphicon-edit"></span> Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="row">
            <ul class="pager">
                <c:if test="${pageNum > 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/magazines?p=${pageNum-1}&s=${pageSize}">
                            <span aria-hidden="true">&larr;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${currSize == pageSize}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/magazines?p=${pageNum+1}&s=${pageSize}">
                            <span aria-hidden="true">&rarr;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/magazines/add" role="button">Add</a>
    </div>

    <div class="col-md-2"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
