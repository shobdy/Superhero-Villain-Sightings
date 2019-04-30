<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Added meta tag below to fix the scaling issue when looking on a small screen device -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Superhero Sightings: Login</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet"> 
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/png" />
    </head>
    <body>
        <div class="container">
            <h1>Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Landing Page</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                </ul>    
            </div>
            <h2>Login Page</h2>
            <c:if test="${param.login_error == 1}">
                <h3>Wrong ID or Password!</h3>
            </c:if>
            <form class="form-horizontal" role="form" method="post" action="j_spring_security_check">
                <div class="form-group">
                    <label for="j_username" class="col-md-4 control-label">Username:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="j_username" placeholder="Username" autofocus/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="j_password" class="col-md-4 control-label">Password:</label>
                    <div class="col-md-8">
                        <input type="password" class="form-control" name="j_password" placeholder="Password"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" id="search-button" value="Sign In" />
                    </div>
                </div>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>
    </body>
</html>
