<%-- 
    Document   : workshops
    Created on : 8-mrt-2017, 10:12:40
    Author     : David
--%>

<%@page import="Models.Workshop"%>
<%@page import="Models.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%! Event event = new Workshop("Test"); %>
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
        
        <div class="wrapper">
            <div id="event-day-bar">
                <ul id="event-day-bar-list">
                    <li class="event-day-bar-item"><a href="/events/ma">ma</a></li>
                    <li class="event-day-bar-item"><a href="/events/di">di</a></li>
                    <li class="event-day-bar-item"><a href="/events/wo">wo</a></li>
                    <li class="event-day-bar-item"><a href="/events/do">do</a></li>
                    <li class="event-day-bar-item"><a href="/events/vr">vr</a></li>
                </ul>
            </div>
            
            <br> <!-- this break is css magic! -->
            
            <div id="event-container">
                <table class="event-table" cellpadding="25%">
                    <c:forEach var="row" items="${events}">
                        <tr>
                            <c:forEach var="ws" items="${row}">
                                <td>
                                    <a href="#${ws.id}">
                                    <div class="event-single">
                                        <div class="image">
                                            <img src="${ws.imageURL}" />
                                            <span style="background-color: ${ws.hexColor};" id="event-dateTime">
                                                ${ws.startTime} - ${ws.endTime}</span>
                                            <span id="event-name"><b>${ws.eventName}</b></span></br>
                                            <span id="event-type">${ws.eventType}</span>
                                        </div>
                                    </div>
                                    </a>
                                    <div id="${ws.id}" class="overlay">
                                        <div class="popup">
                                            <span style="background-color: ${ws.hexColor};" 
                                                  id="popup-event-type"><b>${ws.eventType}</b></span>
                                                <div id="popup-wrapper">
                                                    <span id="popup-event-name"><b>${ws.eventName}</b></span></br>
                                                    <span id="popup-event-time">${ws.startTime} - ${ws.endTime}</span></br>
                                                    <span id="popup-event-location">${ws.locationName}</span></br></br>
                                                    <a class="close" href="#">&times;</a>
                                                    <div class="content">
                                                        ${ws.description}
                                                    </div>
                                                    <c:if test="${ws.eventType == 'Workshop'}">
                                                        <div id="popup-workshop-form">
                                                            <span id="attending-users">Attendance: ${ws.attendingUsers} / 
                                                                ${ws.maxUsers}</span></br>
                                                            <input id="attend-button" class="popup-control" type="submit" value="Attend" />
                                                        </div>
                                                    </c:if>
                                                </div>
                                        </div>
                                    </div>
                                </td> 
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
