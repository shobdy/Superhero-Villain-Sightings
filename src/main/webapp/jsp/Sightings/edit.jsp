<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Added meta tag below to fix the scaling issue when looking on a small screen device -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Sighting Page</title>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Locations/LocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <h2>Edit Sighting</h2>
            <sf:form class="form-horizontal" role="form" modelAttribute="commandModel" action="editSighting" method="POST">
                <div class="form-group">
                    <label for="edit-date-sighted" class="col-md-3 control-label">Date of Sighting:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-date-sighted" path="dateSighted" placeholder="Please enter as: yyyy-mm-dd" autofocus="autofocus" />
                        <sf:errors path="dateSighted" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-person-name" class="col-md-3 control-label">Person Name:</label>
                    <div class="col-md-9">
                        <sf:select path="personId" class="form-control">
                            <sf:options items="${commandModel.personList}" itemValue="personId" itemLabel="personName"/>
                        </sf:select>
                        <sf:errors path="personId" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-location-name" class="col-md-3 control-label">Location Name:</label>
                    <div class="col-md-9">
                        <sf:select path="locationId" class="form-control">
                            <sf:options items="${commandModel.locationList}" itemValue="locationId" itemLabel="locationName"/>
                        </sf:select>
                        <sf:errors path="locationId" cssclass="error"></sf:errors>
                        <sf:hidden path="personLocationId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-9">
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/Sightings/SightingsPage">Back</a>
                        <input type="submit" class="btn btn-default pull-right" value="Update Location"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>
    </body>
</html>
