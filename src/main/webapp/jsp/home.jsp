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
        <title>Superhero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet"> 
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/png" />
    </head>
    <body>
        <div class="container">
            <span class="h1">Superhero Sightings</span>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <span class="h4 pull-right"><a href="<c:url value="/login" />" >Login</a></span>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <span class="h4 pull-right">${pageContext.request.userPrincipal.name} | <a href="<c:url value="/logout" />" > Logout</a></span>
            </c:if>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Landing Page</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Person/PersonPage">Heroes/Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Locations/LocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <p>Welcome to the Superhero sightings page. We will have a list of heroes/villains, their organizations, locations, and sightings. 
                <sec:authorize access="isAuthenticated()"> Below you will see the last 10 sightings.</sec:authorize>
            </p>
            <div class="row">
                
                <div class="col-md-6">
                    <h4>Below is a list of users to test the web application with:</h4>
                    <table class="table table-bordered">
                        <tr>
                            <th width="20%">Username</th>
                            <th width="20%">Password</th>
                            <th width="60%">Description</th>
                        </tr>
                        <tr>
                            <td>user</td>
                            <td>password</td>
                            <td>Gives limited access</td>
                        </tr>
                        <tr>
                            <td>admin</td>
                            <td>password</td>
                            <td>Gives full access</td>
                        </tr>
                    </table>
                </div>
            </div>
            <sec:authorize access="isAuthenticated()">
                <div class="row">
                    <!-- Add a col to hold the summary table - have it take up half the row -->
                    <div class="col-md-6">
                        <h2>Last Ten Sightings</h2>
                        <table id="contactTable" class="table table-hover">
                            <tr>
                                <th width="25%">Date Sighted</th>
                                <th width="25%">Person Name</th>
                                <th width="28%">Location Name</th>
                            </tr>
                            <c:forEach var="currentSighting" items="${lastTen}">
                                <tr>
                                    <td><c:out value="${currentSighting.dateSighted}"/></td>
                                    <td><c:out value="${currentSighting.person.personName}"/></a></td>
                                    <td><c:out value="${currentSighting.location.locationName}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div> <!-- End col div -->
                </div>
            </sec:authorize>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>

    </body>
</html>

