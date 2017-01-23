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

    <title>Newsstand - Admin - Edit Publisher</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-8">

        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/publishers/update?id=${publisher.id}">
            <fieldset>

                <legend>Edit Publisher</legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="name">Title</label>
                    <div class="col-md-4">
                        <input id="name" name="name" class="form-control input-md" type="text"
                               value="${publisher.title}" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4"></div>
                    <div class="col-md-2">
                        <button id="updatebutton" name="addbutton" class="btn btn-primary"
                                type="submit">Update</button>
                    </div>
                    <div class="col-md-2">
                        <a class='btn btn-danger' href="${pageContext.request.contextPath}/admin/publishers/delete?id=${publisher.id}">
                            <span class="glyphicon glyphicon-remove"></span> Delete</a>
                    </div>
                </div>

            </fieldset>
        </form>

        <a class='btn btn-default' href="${pageContext.request.contextPath}/admin/publishers">
            <span class="glyphicon glyphicon-chevron-left"></span> Back</a>

    </div>

    <div class="col-md-2"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
