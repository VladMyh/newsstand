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
            <p class="lead">Categories</p>
            <div class="list-group">
                <c:forEach items="${categories}" var="category">
                    <a href="" class="list-group-item">${category.name}</a>
                </c:forEach>
            </div>
        </div>

        <div class="col-md-6">

            <div class="thumbnail">
                <div class="caption-full">
                    <h4 class="pull-right">Price $${magazine.price}  </h4>
                    <h4><a href="#">${magazine.title}</a></h4>

                    <p><b>Publisher: </b><a href="#">${magazine.publisher.title}</a></p>
                    <p><b>Category: </b><a href="#">${magazine.category.name}</a></p>

                    <p>${magazine.description}</p>
                </div>
            </div>

        </div>

        <div class="col-md-3">
            <img src="http://placehold.it/300x500">
        </div>

    </div>

</div>
<!-- /.container -->

</body>

</html>