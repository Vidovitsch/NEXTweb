<%-- 
    Document   : user
    Created on : 7-mrt-2017, 17:03:48
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Users</h1>
        
        <table width="100%" border="1" align="center">
            <tr bgcolor="#949494">
                <th>first name</th>
                <th>last name</th>
                <th>age</th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
