<%-- 
    Document   : user
    Created on : 7-mrt-2017, 17:03:48
    Author     : mmjan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/container.css" />
        <title>Group</title>
    </head>
    <body>
        <div id="next-header">
            <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
        </div>
        <div id="spacer"></div>

        <h1>GROUP</h1>
        
        <div id="next-group">
            <table width="50%" border="1" align="left" >
                <tr bgcolor="#949494">
                    <th>Group Number: ${group.groupNumber}</th>
                </tr>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        ${user.name} 
                    </td>
                    <td>
                        <img src="${user.image}" width="64px" height="64px"/>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        
        <div id="next-msgbox">
            <ul>
                <c:forEach var="msg" items="${messages}">
                <li>
                    <p>${msg.time} - ${msg.username}: ${msg.content}</p>
                </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
