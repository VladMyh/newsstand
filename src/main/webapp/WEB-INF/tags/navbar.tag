<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="Page navigation bar" pageEncoding="UTF-8"%>
<%@attribute name="navbar" fragment="true" %>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Newsstand</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Search</button>
                </form>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <%--Admin--%>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/magazines">Magazines</a></li>
                            <li><a href="#">Subscriptions</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/users?p=1&s=10">Users</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/publishers">Publishers</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/categories">Categories</a></li>
                        </ul>
                    </li>
                </c:if>

                <%--User not logged in--%>
                <c:if test="${sessionScope.authenticated == null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Sign In</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/register">Sign Up</a>
                    </li>
                </c:if>

                <%--User controlls--%>
                <c:if test="${sessionScope.authenticated != null && sessionScope.authenticated == true}">
                    <li>
                        <a href=""><c:out value="${sessionScope.username}"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout">Sign out</a>
                    </li>
                </c:if>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>