<%-- 
    Document   : messageboard
    Created on : 8-mrt-2017, 14:26:33
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/css/container.css" var="containerCSS" />
        <link href="${containerCSS}" rel="stylesheet" />
        <title>MessageBoard</title>
    </head>
    <body>
        <div id="header">
            <img id="next_logo" src="/images/next_logo.png"/>
        </div>
        <div id="mbgroup">
            <label for="Group">Groep: </label>
        </div>
    </body>
</html>
