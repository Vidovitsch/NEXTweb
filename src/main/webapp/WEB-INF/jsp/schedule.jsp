<%-- 
    Document   : schedule
    Created on : 15-mrt-2017, 9:16:50
    Author     : Arno Dekkers Los
--%>

<%@page import="org.aspectj.weaver.ast.Var"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<c:set var="day" scope="session" value="Monday"/>
<c:set var="dayname" scope="session" value="Monday"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <spring:url value="/css/schedule.css" var="scheduleCSS" />

        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
        <link href="${scheduleCSS}" rel="stylesheet" />

        <title>De planning</title>
    </head>
    <body>
        <div class="wrapper2">

            <h1>De planning</h1>

            <div class="cd-schedule loading">
                <div class="timeline">
                    <ul class="schedule-box-sizing">
                        <li><span>09:00</span></li>
                        <li><span>09:30</span></li>
                        <li><span>10:00</span></li>
                        <li><span>10:30</span></li>
                        <li><span>11:00</span></li>
                        <li><span>11:30</span></li>
                        <li><span>12:00</span></li>
                        <li><span>12:30</span></li>
                        <li><span>13:00</span></li>
                        <li><span>13:30</span></li>
                        <li><span>14:00</span></li>
                        <li><span>14:30</span></li>
                        <li><span>15:00</span></li>
                        <li><span>15:30</span></li>
                        <li><span>16:00</span></li>
                        <li><span>16:30</span></li>
                        <li><span>17:00</span></li>
                        <li><span>17:30</span></li>
                        <li><span>18:00</span></li>
                    </ul>
                </div> <!-- .timeline -->

                <div class="events">

                    <c:forEach begin="0" end="4" varStatus="loop">
                        <c:choose>
                            <c:when test="${0 == loop.index}">
                                <c:set var="day" scope="session" value="${Monday}"/>
                                <c:set var="dayname" scope="session" value="Monday"/>
                            </c:when>
                            <c:when test="${1 == loop.index}">
                                <c:set var="day" scope="session" value="${Tuesday}"/>
                                <c:set var="dayname" scope="session" value="Tuesday"/>
                            </c:when>
                            <c:when test="${2 == loop.index}">
                                <c:set var="day" scope="session" value="${Wednesday}"/>
                                <c:set var="dayname" scope="session" value="Wednesday"/>
                            </c:when>
                            <c:when test="${3 == loop.index}">
                                <c:set var="day" scope="session" value="${Thursday}"/>
                                <c:set var="dayname" scope="session" value="Thursday"/>
                            </c:when>
                            <c:when test="${4 == loop.index}">
                                <c:set var="day" scope="session" value="${Friday}"/>
                                <c:set var="dayname" scope="session" value="Friday"/>
                            </c:when>
                        </c:choose>
                        
                        <li class="events-group">
                            <div class="top-info"><span><c:out value="${dayname}"/></span></div>
                            <ul>
                                <c:forEach var="row" items="${day}">
                                    <li class="single-event" data-start="${row.startTime}" data-end="${row.endTime}" data-content="${row.description}" data-event="${row.eventType}">
                                        <a href="#0">
                                            <em class="event-name">${row.eventName}</em>
                                        </a>
                                    </li>
                                </c:forEach>        
                            </ul>
                        </li>
                    </c:forEach>
                </div>

                <div class="event-modal">
                    <header class="header">
                        <div class="content">
                            <span class="event-date"></span>
                            <h3 class="event-name"></h3>
                        </div>

                        <div class="header-bg"></div>
                    </header>

                    <div class="body">
                        <div class="event-info">
                            <!--<h3 class="event-description"></h3>-->
                        </div>
                        <div class="body-bg"></div>
                    </div>

                    <a href="#0" class="close">Close</a>
                </div>

                <div class="cover-layer"></div>
            </div> <!-- .cd-schedule -->
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
        <script>
            if (!window.jQuery)
                document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
        </script>
        <script src="../../../images/javascript/schedule.js"></script> <!-- Resource jQuery -->
    </body>
</html>
