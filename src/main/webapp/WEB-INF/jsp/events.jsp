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
        <spring:url value="/css/workshops.css" var="workshopsCSS" />
        
        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${workshopsCSS}" rel="stylesheet" />
        
        <title>Workshops</title>
    </head>
    <body>
        <%@ include file="master.jsp" %>
        
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
                                        <span id="event-dateTime">${ws.startTime} - ${ws.endTime}</span>
                                        <span id="event-name"><b>${ws.eventName}</b></span></br>
                                        <span id="event-type">${ws.eventType}</span>
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
