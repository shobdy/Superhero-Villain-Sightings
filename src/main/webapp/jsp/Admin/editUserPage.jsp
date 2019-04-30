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
        <title>Superhero Sightings</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet"> 
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/png" />
    </head>
    <body>
        <div class="container">
            <span class="h1">Superhero Sihtings</span>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
            <h2>Edit User</h2>
            <div class="row">
                <!-- Add col to hold the new user form - have it take up the other half of the row -->
                <sf:form class="form-horizontal" role="form" method="POST" action="editUser" modelAttribute="commandModel">
                    <div class="form-group">
                        <label for="add-user-name" class="col-md-3 control-label">Username:</label>
                        <div class="col-md-9">
                            <sf:input type="text" class="form-control" id="add-user-name" path="username" placeholder="Username" autofocus="autofocus" />
                            <sf:errors path="username" cssclass="error"/>
                            <sf:hidden path="id" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <a class="btn btn-default" href="displayChangePasswordPage?id=${commandModel.id}">Change Password</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <span style="margin-right: 5em;">
                                <label for="add-user-admin" class="control-label">Admin User?</label>
                                <!-- Finally figured out for checkboxes, you have to use sf:checkbox and not type="checkbox" -->
                                <sf:checkbox class="checkbox-inline" id="add-user-admin" path="admin" value="${commandModel.admin}" />
                                <sf:errors path="admin" cssclass="error"/>
                            </span>
                            <span>
                                <label for="user-enabled" class="control-label">User Enabled?</label>
                                
                                <sf:checkbox class="checkbox-inline" id="user-enabled" path="enabled" value="${commandModel.enabled}"/>
                                <sf:errors path="enabled" cssclass="error"/>
                                <sf:hidden path="password" />
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9">
                            <a class="btn btn-default" href="${pageContext.request.contextPath}/Admin/displayUserList">Back</a>
                            <input type="submit" class="btn btn-default pull-right" value="Update User" />
                        </div>
                    </div>
                </sf:form>
            </div>
        </div>
    </body>
</html>
