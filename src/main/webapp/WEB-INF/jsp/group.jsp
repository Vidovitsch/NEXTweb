<%-- 
    Document   : user
    Created on : 7-mrt-2017, 17:03:48
    Author     : mmjan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/group.css" />
        <title>Group</title>
    </head>
    <body>

        <div class="wrapper2">

            <div id="next-group">

                <h2>Group Nr: ${group.groupNumber}</h2>

                <c:forEach var="user" items="${users}">

                    <div class="dropdown">
                        <div class="dropdown-div">
                            <div style="display: inline-block; float: left; width: 75px">
                                <img src="/images/default_user" width="64px" height="64px">
                            </div>
                            <div style="display: inline-block; font-size: 32px; padding-top: 16px">
                                ${user.name}
                            </div>
                        </div>
                        <div class="dropdown-content">
                        <table>
                            <tr>
                                <td><b>name: </b></td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td><b>uid: </b></td>
                                <td>${user.uid}</td>
                            </tr>
                            <tr>
                                <td><b>email: </b></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td><b>class: </b></td>
                                <td>S43, Software</td>
                            </tr>

                            </table>
                        </div>
                    </div>

                    <br>        

                </c:forEach>

            </div>
        </div>
            
    </body>
</html>
