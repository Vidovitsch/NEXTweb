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
        <spring:url value="/css/messageboard.css" var="messageboardCSS" />
        
        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${messageboardCSS}" rel="stylesheet" />
        <title>MessageBoard</title>
    </head>
    <body>
        <div id="next-header">
            <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
        </div>
        <div id="spacer"></div>
        <h1>Messages Group ${group}</h1>
        <div id="Messageboard-container">  
            <c:forEach var="msg" items="${messages}">
                <div class="${user == msg.userName ? 'ownMessagecss' : 'otherMessagecss'}">
                   <c:if test="${user != msg.userName}"><span class="username-label">${msg.userName}</span><br/></c:if>
                   <span class="content-label">${msg.content} </span>
                   <span class="date-label">${msg.date} </span><br/>
                </div>           
            </c:forEach> 
        </div>
    </body>
</html>
