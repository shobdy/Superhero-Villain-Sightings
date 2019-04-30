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
        <title>Heroes/Villains Page</title>
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Person/PersonPage">Heroes/Villains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Locations/LocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Organizations/OrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <!-- Add col to hold the new person form - have it take up the other half of the row -->
                <div class="col-md-push-6 col-md-6">
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <h2>Add New Hero/Villain</h2>
                        <sf:form class="form-horizontal" role="form" method="POST" action="createPerson" modelAttribute="commandModel">
                            <div class="form-group">
                                <label for="add-person-type" class="col-md-3 control-label">Type:</label>
                                <div class="col-md-9">
                                    <sf:select path="personType" class="form-control" autofocus="autofocus" >
                                        <sf:option value="" label="Select Option"/>
                                        <sf:option value="Hero" label="Hero"/>
                                        <sf:option value="Villain" label="Villain"/>
                                    </sf:select>
                                    <sf:errors path="personType" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-person-name" class="col-md-3 control-label">Name:</label>
                                <div class="col-md-9">
                                    <sf:input type="text" class="form-control" id="add-person-name" path="personName" Placeholder="Name" />
                                    <sf:errors path="personName" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-description" class="col-md-3 control-label">Description:</label>
                                <div class="col-md-9">
                                    <sf:input type="text" class="form-control" id="add-description" path="personDescription" placeholder="Description"/>
                                    <sf:errors path="personDescription" cssclass="error"></sf:errors>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-power" class="col-md-3 control-label">Power:</label>
                                <div class="col-md-9">
                                    <sf:input type="text" class="form-control" id="add-power" path="personPower" placeholder="Power"/>
                                    <sf:errors path="personPower" cssClass="error"></sf:errors>
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
                                    <input type="submit" class="btn btn-default" value="Create Person"/>
                                </div>
                            </div>
                        </sf:form>
                    </sec:authorize>
                </div> <!-- End col div -->
                <div class="hidden-md hidden-lg"><hr/></div><!-- Added to break up between the 2 columns when smaller than medium -->
                <!-- Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-pull-6 col-md-6">
                    <h2>Heroes/Villains Listing</h2>
                    <table id="contactTable" class="table table-hover tableFixed">
                        <tr>
                            <th width="15%">Type</th>
                            <th width="23%">Name</th>
                            <th width="40%">Description</th>
                            <th width="22%"></th>
                        </tr>
                        <c:forEach var="currentPerson" items="${commandModel.personList}">
                            <tr>
                                <td><c:out value="${currentPerson.personType}"/></td>
                                <td><a href="personDetails?personId=${currentPerson.personId}"><c:out value="${currentPerson.personName}"/></a></td>
                                <td class="truncateText"><span><c:out value="${currentPerson.personDescription}"/></span></td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <span class="text-nowrap"><a href="displayEditPersonPage?personId=${currentPerson.personId}">EDIT</a></sec:authorize><sec:authorize access="hasRole('ROLE_ADMIN')"> | <a href="deletePerson?personId=${currentPerson.personId}">DELETE</a></span>
                                    </sec:authorize>
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
