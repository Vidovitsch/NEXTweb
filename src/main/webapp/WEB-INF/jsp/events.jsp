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
                                <td id="${ws.id}">
                                    <div class="event-single" onclick="showPopUp('${ws.hexColor}', '${ws.eventType}', 
                                                '${ws.eventName}', '${ws.startTime}', '${ws.endTime}', 
                                                '${ws.locationName}', '${ws.description}', '${ws.id}');">
                                        <div class="image">
                                            <img src="${ws.imageURL}" />
                                            <span style="background-color: ${ws.hexColor};" id="event-dateTime">
                                                ${ws.startTime} - ${ws.endTime}</span>
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
        </div>
        <script>
            function showPopUp(hexColor, eventType, eventName, start, end, location, description, id) {
                document.getElementById(id).innerHTML += 
                        '<div id="event" class="overlay"> \
                                <div class="popup"> \
                                    <span style="background-color: ' + hexColor + '" \
                                          id="popup-event-type"><b>' + eventType + '</b></span> \
                                        <div id="popup-wrapper"> \
                                            <span id="popup-event-name"><b>' + eventName + '</b></span></br> \
                                            <span id="popup-event-time">' + start + ' - ' + end + '</span></br> \
                                            <span id="popup-event-location">' + location + '</span></br></br> \
                                            <span class="close" onclick="removePopUp(); return false">Close</span> \
                                            <div class="content"> \
                                                ' + description + ' \
                                            </div> \
                                        </div> \
                                </div> \
                            </div>'; 

                if (eventType === "Workshop") {
                    document.getElementById("popup-wrapper").innerHTML +=
                            '<div id="popup-workshop-form"> \
                                <span id="attending-users">Attendance: ${workshop.attendingUsers} / \
                                    ${workshop.maxUsers}</span></br> \
                                <input id="attend-button" class="popup-control" type="submit" value="Attend" /> \
                            </div>';
                }     
            };
            
            function removePopUp() {
                var popup = document.getElementById("event");
                popup.parentNode.removeChild(popup);
            }
        </script>
    </body>
</html>
