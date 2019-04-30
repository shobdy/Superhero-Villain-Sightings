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
        <title>Locations Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet"> 
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/png" />
    </head>
    <body>
        <div class="container">
            <span class="h1">Superhero Sightings</span>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <span class="h4 pull-right">${pageContext.request.userPrincipal.name} | <a href="<c:url value="/logout" />" > Logout</a></span>
            </c:if>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/">Landing Page</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Person/PersonPage">Heroes/Villains</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Locations/LocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
            <h2>Location Details</h2>
            <p>Name <c:out value="${location.locationName}"/></p>
            <p>Description: <c:out value="${location.locationDescription}"/></p>
            <p>Address Line 1: <c:out value="${address.addressLine1}"/></p>
            <p>Address Line 2: <c:out value="${address.addressLine2}"/></p>
            <p>City: <c:out value="${address.addressCity}"/></p>
            <p>State: <c:out value="${address.addressState}"/></p>
            <p>Zip: <c:out value="${address.addressZip}"/></p>
            <p>Country: <c:out value="${address.addressCountry}"/></p>
            <p>Latitude: <c:out value="${location.locationLatitude}"/></p>
            <p>Longitude: <c:out value="${location.locationLongitude}"/></p>
            <button class="btn btn-default" onclick="goBack()" autofocus="autofocus">Back</button>
            <!-- Main Page Content Stop -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>
    </body>
</html>