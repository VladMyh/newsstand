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

    <title>Newsstand - Admin - Categories</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-8">

        <c:if test="${updateSuccess != null && updateSuccess == true}">
            <div class="alert alert-success" role="alert">Category updated successfully!</div>
        </c:if>
        <c:if test="${deletionSuccess != null && deletionSuccess == true}">
            <div class="alert alert-success" role="alert">Category deleted successfully!</div>
        </c:if>
        <c:if test="${deletionSuccess != null && deletionSuccess == false}">
            <div class="alert alert-danger" role="alert">Couldn't delete category!</div>
        </c:if>

        <h1>Categories</h1>

        <table class="table">
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td class="col-md-3">${category.name}</td>
                        <td class="col-md-1">
                            <a class='btn btn-info btn-xs' href="${pageContext.request.contextPath}/admin/categories/edit?id=${category.id}">
                                <span class="glyphicon glyphicon-edit"></span> Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/categories/add" role="button">Add</a>
    </div>

    <div class="col-md-2"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
