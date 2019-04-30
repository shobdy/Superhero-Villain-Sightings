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
        <title>Edit Heroes/Villains Page</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Person/PersonPage">Heroes/Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Locations/LocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <h2>Edit Person</h2>
            <sf:form class="form-horizontal" role="form" modelAttribute="commandModel" action="editPerson" method="POST">
                <div class="form-group">
                    <label for="edit-person-type" class="col-md-3 control-label">Type:</label>
                    <div class="col-md-9">
                        <sf:select path="personType" class="form-control" autofocus="autofocus">
                            <sf:option value="Hero" label="Hero"/>
                            <sf:option value="Villain" label="Villain"/>
                        </sf:select>
                        <sf:errors path="personType" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-person-name" class="col-md-3 control-label">Name:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-person-name" path="personName" placeholder="Name"/>
                        <sf:errors path="personName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-description" class="col-md-3 control-label">Description:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-description" path="personDescription" placeholder="Company"/>
                        <sf:errors path="personDescription" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="edit-power" class="col-md-3 control-label">Power:</label>
                    <div class="col-md-9">
                        <sf:input type="text" class="form-control" id="edit-power" path="personPower" placeholder="Phone"/>
                        <sf:errors path="personPower" cssclass="error"></sf:errors>
                        <sf:hidden path="personId"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-organization" class="col-md-3 control-label">Organization(s):</label>
                    <div class="col-md-9">
                        <sf:select path="organizationIds" class="form-control" multiple="true">
                            <sf:options items="${commandModel.organizationList}" itemValue="organizationId" itemLabel="organizationName"/>
                        </sf:select>
                        <sf:errors path="organizationIds" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-md-9">
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/Person/PersonPage">Back</a>
                        <input type="submit" class="btn btn-default pull-right" value="Update Person"/>
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
