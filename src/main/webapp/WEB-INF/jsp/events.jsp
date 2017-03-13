<%-- 
    Document   : workshops
    Created on : 8-mrt-2017, 10:12:40
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
        <spring:url value="/css/workshops.css" var="workshopsCSS" />
        
        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${workshopsCSS}" rel="stylesheet" />
        
        <title>Workshops</title>
    </head>
    <body>
        <div id="next-header">
            <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
        </div>
        <div id="spacer"></div>
        <h1>EVENTS</h1>
        <div id="event-container">
            <table class="event-table" cellpadding="25%">
                <c:forEach var="row" items="${events}">
                    <tr>
                        <c:forEach var="ws" items="${row}">
                            <td>
                                <div class="event-single">
                                    <div class="image">
                                        <img src="${ws.imageURL}" />
                                        <span id="event-dateTime">${ws.date}</br>
                                            <span id="event-time">
                                                ${ws.startTime} - ${ws.endTime}
                                            </span>
                                        </span>
                                        <h2>${ws.eventName}</h2>
                                    </div>
                                </div>
                            </td> 
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
