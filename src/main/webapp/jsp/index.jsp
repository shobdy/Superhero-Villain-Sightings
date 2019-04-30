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
        <title>Shawn Hobdy</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet"> 
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/png" />
    </head>
    <body>
        <div class="container">
            <div class="row">
                <span class="h1">Shawn Hobdy</span>
                <hr/>
                <p>Hello! My name as you may have guessed is Shawn Hobdy. I have been in the tech industry for more than 20 years now.
                    My career has taken me in many different directions like Telephone systems, locksmithing, and network administration.
                    I am currently working on web design using Spring and Java.</p>
                <p>This is my landing page!. I will be adding new content as soon as I can get it cleaned up. 
                    Please take a look at my current project.</p><br />
                <table class="table table-bordered col-md-9">
                    <tr>
                        <th>Project Name</th>
                        <th>Project Description</th>
                    </tr>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/home">Superhero Sightings</a></td>
                        <td>Website to track Hero/Villain sightings.</td>
                    </tr>
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/displayMenuPage">Vending Machine</a></td>
                        <td>Online implementation of a vending machine.</td>
                    </tr>
                </table>

                <p>Thank you for your time looking at my projects. 
                    If you have any questions please let me know at: <a href="mailto:ShawnHobdy@gmail.com">ShawnHobdy@gmail.com</a>.
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/SHSightingsScripts.js"></script>

    </body>
</html>

