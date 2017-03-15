<%-- 
    Document   : login.jsp
    Created on : 15-mrt-2017, 15:20:29
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/css/container.css" var="containerCSS" />
        <spring:url value="/css/login.css" var="loginCSS" />
        
        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${loginCSS}" rel="stylesheet" />
        
    </head>
    <body>
        <div id="next-header">
            <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
        </div>
        <div id="spacer"></div>
        
        <div class="login-page">
            <div id="login-form">
                <span><b>Login</b></span></br>
                <form action="events.htm" method="POST">
                    E-mail: </br>
                    <input class="form-control" type="text" name="email"></br>
                    Password: </br>
                    <input class="form-control" type="password" name="email"></br>
                    <input type="submit" value="Login" />
                </form>
            </div>
        </div>
    </body>
</html>
