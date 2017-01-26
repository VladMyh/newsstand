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

            <h1>Subscribe</h1>

            <div class="well">
                <h4><span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                    Magazine details</h4>

                <p>Magazine name</p>
            </div>

            <div class="well">
                <h4><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>
                    Subscription type</h4>

                <select class="selectpicker">
                    <option selected>One month</option>
                    <option>Two month</option>
                    <option>Three month</option>
                    <option>Six month</option>
                    <option>One year</option>
                </select>

            </div>

            <div class="well">
                <h4><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                    Payment</h4>

                <label class="radio-inline">
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> Credit card
                </label>
                <label class="radio-inline">
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> PayPal
                </label>

            </div>

            <button type="submit" class="btn btn-primary">Continue</button>

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