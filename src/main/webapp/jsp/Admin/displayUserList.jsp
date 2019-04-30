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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/Sightings/SightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Admin/displayUserList">User Admin</a></li></sec:authorize>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
            <div class="row">
                <!-- Add col to hold the new user form - have it take up the other half of the row -->
                <div class="col-md-push-6 col-md-6">
                    <h2>Add New User</h2>
                <sf:form class="form-horizontal" role="form" method="POST" action="addUser" modelAttribute="commandModel">
                    <div class="form-group">
                        <label for="add-user-name" class="col-md-4 control-label">Username:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-user-name" path="username" placeholder="Username" autofocus="autofocus" />
                            <sf:errors path="username" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-user-password" class="col-md-4 control-label">Password:</label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="add-user-password" path="password" placeholder="Password"/>
                            <sf:errors path="password" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-user-admin" class="col-md-4 control-label">Admin User?</label>
                        <div class="col-md-8">
                            <!-- Finally figured out for checkboxes, you have to use sf:checkbox and not type="checkbox" -->
                            <sf:checkbox class="checkbox" id="add-user-admin" path="admin"/>
                            <sf:errors path="admin" cssclass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Add User"/>
                        </div>
                    </div>
                </sf:form>
                </div><!-- End Col Div -->
                <div class="hidden-md hidden-lg"><hr/></div><!-- Added to break up between the 2 columns when smaller than medium -->
                <!-- Add a col to hold the summary table - have it take up half the row -->
                <div class="col-md-pull-6 col-md-6">
                    <h2>Users Listing</h2>
                    <table id="userTable" class="table table-hover">
                        <tr>
                            <th width="45%" style="overflow: hidden">Username</th>
                            <th width="15%" style="overflow:hidden">Admin</th>
                            <th width="15%" style="overflow:hidden">Enabled</th>
                            <th width="25%" style="overflow: hidden"></th>
                        </tr>
                        <c:forEach var="currentUser" items="${commandModel.userList}">
                            <c:choose>
                                <c:when test="${currentUser.username == 'admin'}"></c:when>
                                <c:when test="${currentUser.username == 'user'}"></c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><c:out value="${currentUser.username}"/></td>
                                        <td>
                                            <c:if test="${currentUser.admin == true}">
                                                YES
                                            </c:if>
                                            <c:if test="${currentUser.admin == false}">
                                                NO
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${currentUser.enabled == true}">
                                                YES
                                            </c:if>
                                            <c:if test="${currentUser.enabled == false}">
                                                NO
                                            </c:if>
                                        </td>
                                        <td>
                                            <span class="text-nowrap">
                                                <a href="displayEditUserPage?id=${currentUser.userId}">EDIT </a> | 
                                                <a href="deleteUser?id=${currentUser.userId}" onclick="return confirm('Are you sure you want to permanently delete this user?')">DELETE</a>
                                            </span>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </table>
                </div><!-- End col div -->
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>   
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>
    </body>
</html>
