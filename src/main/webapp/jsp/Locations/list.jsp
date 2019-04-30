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
            <div class="row">
                <!-- Add col to hold the new contact form - have it take up the other half of the row -->
                <div class="col-md-push-6 col-md-6">
                    <h2>Add New Location</h2>
                    <sf:form class="form-horizontal" role="form" method="POST" action="createLocation" modelAttribute="commandModel">
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-3 control-label">Name:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-location-name" path="locationName" placeholder="Name of Location" autofocus="autofocus" />
                                <sf:errors path="locationName" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-description" class="col-md-3 control-label">Description:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-location-description" path="locationDescription" placeholder="Description"/>
                                <sf:errors path="locationDescription" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-addressLine1" class="col-md-3 control-label">Address Line 1:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-addressLine1" path="addressLine1" placeholder="Address Line 1"/>
                                <sf:errors path="addressLine1" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-addressLine2" class="col-md-3 control-label">Address Line 2:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-addressLine2" path="addressLine2" placeholder="Address Line 2"/>
                                <sf:errors path="addressLine2" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-addressCity" class="col-md-3 control-label">City:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-addressCity" path="addressCity" placeholder="City"/>
                                <sf:errors path="addressCity" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-addressState" class="col-md-3 control-label">State:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-addressState" path="addressState" placeholder="State"/>
                                <sf:errors path="addressState" cssclass="error"></sf:errors>
                            </div>
                        </div>    
                        <div class="form-group">
                            <label for="add-addressZip" class="col-md-3 control-label">Zipcode:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-addressZip" path="addressZip" placeholder="Zipcode"/>
                                <sf:errors path="addressZip" cssclass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-addressCountry" class="col-md-3 control-label">Country:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-addressCountry" path="addressCountry" placeholder="Country"/>
                                <sf:errors path="addressCountry" cssclass="error"></sf:errors>
                                <sf:hidden path="addressId"/>
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="add-locationLatitude" class="col-md-3 control-label">Latitude:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-locationLatitude" path="locationLatitude" placeholder="Latitude"/>
                                <sf:errors path="locationLatitude" cssclass="error"></sf:errors>
                            </div>
                        </div> 
                        <div class="form-group">
                            <label for="add-locationLongitude" class="col-md-3 control-label">Longitude:</label>
                            <div class="col-md-9">
                                <sf:input type="text" class="form-control" id="add-locationLongitude" path="locationLongitude" placeholder="Longitude"/>
                                <sf:errors path="locationLongitude" cssclass="error"></sf:errors>
                                <sf:hidden path="locationId"/>
                                <sf:hidden path="locationAddressId"/>
                            </div>
                        </div> 
                        <div class="form-group">
                            <div class="col-md-offset-3 col-md-9">
                                <input type="submit" class="btn btn-default" value="Create Location"/>
                            </div>
                        </div>
                    </sf:form>
                </div> <!-- End col div -->
                <div class="hidden-md hidden-lg"><hr/></div><!-- Added to break up between the 2 columns when smaller than medium -->
                <!-- Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-pull-6 col-md-6">
                    <h2>Locations Listing</h2>
                    <table id="contactTable" class="table table-hover tableFixed">
                        <tr>
                            <th width="25%">Location Name</th>
                            <th width="53%">Description</th>
                            <th width="22%"></th>
                        </tr>
                        <c:forEach var="currentLocation" items="${commandModel.locationList}">
                            <tr>
                                <td><a href="locationDetails?locationId=${currentLocation.locationId}"><c:out value="${currentLocation.locationName}"/></a></td>
                                <td class="truncateText"><c:out value="${currentLocation.locationDescription}"/></td>
                                <td>
                                    <span class="text-nowrap">
                                        <a href="displayEditLocationPage?locationId=${currentLocation.locationId}">EDIT</a>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')"> | <a href="deleteLocation?locationId=${currentLocation.locationId}">DELETE</a></sec:authorize>
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
