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
        <title>Edit Organization Page</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <h2>Edit Organization</h2>
            <sf:form class="form-horizontal" role="form" modelAttribute="commandModel" action="editOrganization" method="POST">
                <div class="form-group">
                    <label for="edit-organization-name" class="col-md-3 control-label">Name:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-location-name" path="organizationName" placeholder="Organization Name" autofocus="autofocus" />
                        <sf:errors path="organizationName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-organization-description" class="col-md-3 control-label">Description:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-organization-description" path="organizationDescription" placeholder="Description"/>
                        <sf:errors path="organizationDescription" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-addressLine1" class="col-md-3 control-label">Address Line 1:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-addressLine1" path="addressLine1" placeholder="Address Line 1"/>
                        <sf:errors path="addressLine1" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-addressLine2" class="col-md-3 control-label">Address Line 2:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-addressLine2" path="addressLine2" placeholder="Address Line 2"/>
                        <sf:errors path="addressLine2" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-addressCity" class="col-md-3 control-label">City:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-addressCity" path="addressCity" placeholder="City"/>
                        <sf:errors path="addressCity" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-addressState" class="col-md-3 control-label">State:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-addressState" path="addressState" placeholder="State"/>
                        <sf:errors path="addressState" cssclass="error"></sf:errors>
                    </div>
                </div>    
                <div class="form-group">
                    <label for="edit-addressZip" class="col-md-3 control-label">Zipcode:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-addressZip" path="addressZip" placeholder="Zipcode"/>
                        <sf:errors path="addressZip" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-addressCountry" class="col-md-3 control-label">Country:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-addressCountry" path="addressCountry" placeholder="Country"/>
                        <sf:errors path="addressCountry" cssclass="error"></sf:errors>
                        <sf:hidden path="addressId"/>
                    </div>
                </div> 
                <div class="form-group">
                    <label for="edit-organizationContactName" class="col-md-3 control-label">Contact Name:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-organizationContactName" path="organizationContactName" placeholder="Contact Name"/>
                        <sf:errors path="organizationContactName" cssclass="error"></sf:errors>
                    </div>
                </div> 
                <div class="form-group">
                    <label for="edit-organizationContactPhone" class="col-md-3 control-label">Contact Phone:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-organizationContactPhone" path="organizationContactPhone" placeholder="Contact Phone"/>
                        <sf:errors path="organizationContactPhone" cssclass="error"></sf:errors>
                    </div>
                </div> 
                <div class="form-group">
                    <label for="edit-organizationContactEmail" class="col-md-3 control-label">Contact Email:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-organizationContactEmail" path="organizationContactEmail" placeholder="Contact Email"/>
                        <sf:errors path="organizationContactEmail" cssclass="error"></sf:errors>
                        <sf:hidden path="organizationId"/>
                        <sf:hidden path="organizationAddressId"/>
                    </div>
                </div> 
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-9">
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Back</a>
                        <input type="submit" class="btn btn-default pull-right" value="Update Organization"/>
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
