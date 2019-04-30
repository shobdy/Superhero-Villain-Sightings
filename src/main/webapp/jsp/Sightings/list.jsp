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
        <title>Sightings Page</title>
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
                    <li role="presentation" ><a href="${pageContext.request.contextPath}/Locations/LocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <!-- Add col to hold the new sighting form - have it take up the other half of the row -->
                <div class="col-md-push-6 col-md-6">
                    <h2>Add New Sighting</h2>
                    <sf:form class="form-horizontal" role="form" method="POST" action="createSighting" modelAttribute="commandModel">
                        <div class="form-group">
                            <label for="add-date-sighted" class="col-md-4 control-label">Date of Sighting:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" id="add-date-sighted" path="dateSighted" placeholder="Please enter as: yyyy-mm-dd" autofocus="autofocus" />
                                <sf:errors path="dateSighted" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-person-name" class="col-md-4 control-label">Person Name:</label>
                            <div class="col-md-8">
                                <sf:select path="personId" class="form-control">
                                    <sf:options items="${commandModel.personList}" itemValue="personId" itemLabel="personName"/>
                                </sf:select>
                                <sf:errors path="personId" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                            <div class="col-md-8">
                                <sf:select path="locationId" class="form-control">
                                    <sf:options items="${commandModel.locationList}" itemValue="locationId" itemLabel="locationName"/>
                                </sf:select>
                                <sf:errors path="locationId" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </sf:form>
                </div> <!-- End col div -->
                <div class="hidden-md hidden-lg"><hr/></div><!-- Added to break up between the 2 columns when smaller than medium -->
                <!-- Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-pull-6 col-md-6">
                    <h2>Sightings Listing</h2>
                    <table id="contactTable" class="table table-hover tableFixed">
                        <tr>
                            <th width="25%" style="overflow: hidden">Date Sighted</th>
                            <th width="25%" style="overflow: hidden">Person Name</th>
                            <th width="28%" style="overflow: hidden">Location Name</th>
                            <th width="22%" style="overflow: hidden"></th>
                        </tr>
                        <c:forEach var="currentPersonLocation" items="${commandModel.personLocationList}">
                            <tr>
                                <td><c:out value="${currentPersonLocation.dateSighted}"/></a></td>
                                <td><c:out value="${currentPersonLocation.person.personName}"/></td>
                                <td class="truncateText"><c:out value="${currentPersonLocation.location.locationName}"/></td>
                                <td>
                                    <span class="text-nowrap">
                                        <a href="displayEditSightingPage?personLocationId=${currentPersonLocation.personLocationId}">EDIT</a>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            | <a href="deleteSighting?personLocationId=${currentPersonLocation.personLocationId}">DELETE</a>
                                        </sec:authorize>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div> <!-- End col div -->
                <!-- Main Page Content Stop -->
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>
    </body>
</html>