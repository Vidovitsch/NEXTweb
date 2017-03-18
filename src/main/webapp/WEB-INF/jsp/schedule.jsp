<%-- 
    Document   : schedule
    Created on : 15-mrt-2017, 9:16:50
    Author     : Arno Dekkers Los
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <spring:url value="/css/schedule.css" var="scheduleCSS" />
        <spring:url value="/css/schedulereset.css" var="scheduleresetCSS" />
        
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
        <link href="${scheduleCSS}" rel="stylesheet" />
        <link href="${scheduleresetCSS}" rel="stylesheet" />
        
        <title>De planning</title>
    </head>
    <body>
        <%@ include file="master.jsp" %>
        
        <h1>De planning</h1>
        
        <div class="cd-schedule loading">
        <div class="timeline">
                <ul>
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
                <ul>
                        <li class="events-group">
                                <div class="top-info"><span>Monday</span></div>

                                <ul>
                                    <li class="single-event" data-start="9:45" data-end="16:45"  data-content="nothing Jon Snow" data-event="event-3">
						<a href="#0">
							<em class="event-name">yola les</em>
						</a>
					</li>
                                    
                                    <c:forEach var="row" items="${Monday}">
                                        <li class="single-event" data-start=${row.startTime} data-end=${row.endTime} data-content=${row.description} data-event=${row.eventType}>
                                                <a href="#0">
                                                        <em class="event-name">${row.eventName}</em>
                                                </a>
                                        </li>
                                    </c:forEach>        
                                </ul>
                        </li>

                        <li class="events-group">
                                <div class="top-info"><span>Tuesday</span></div>

                                <ul>
                                        <c:forEach var="row" items="${Tuesday}">
                                        <li class="single-event" data-start=${row.startTime} data-end=${row.endTime} data-content=${row.description} data-event="None">
                                                <a href="#0">
                                                    <%-- For debug purposes the line bellow is row.eventType and not row.eventName--%>
                                                        <em class="event-name">${row.eventType}</em>
                                                </a>
                                        </li>
                                    </c:forEach>  
                                </ul>
                        </li>

                        <li class="events-group">
                                <div class="top-info"><span>Wednesday</span></div>
                                <ul>
                                        <c:forEach var="row" items="${Wednesday}">
                                            <li class="single-event" data-start=${row.startTime} data-end=${row.endTime} data-content=${row.description} data-event=${row.eventType}>
                                                    <a href="#0">
                                                            <em class="event-name">${row.eventName}</em>
                                                    </a>
                                            </li>
                                        </c:forEach>  
                                </ul>
                        </li>

                        <li class="events-group">
                                <div class="top-info"><span>Thursday</span></div>

                                <ul>
                                        <c:forEach var="row" items="${Thursday}">
                                        <li class="single-event" data-start=${row.startTime} data-end=${row.endTime} data-content=${row.description}data-event=${row.eventType}>
                                                <a href="#0">
                                                        <em class="event-name">${row.eventName}</em>
                                                </a>
                                        </li>
                                    </c:forEach>  
                                </ul>
                        </li>

                        <li class="events-group">
                                <div class="top-info"><span>Friday</span></div>

                                <ul>
                                        <c:forEach var="row" items="${Friday}">
                                        <li class="single-event" data-start=${row.startTime} data-end=${row.endTime} data-content=${row.description} data-event=${row.eventType}>
                                                <a href="#0">
                                                        <em class="event-name">${row.eventName}</em>
                                                </a>
                                        </li>
                                    </c:forEach>  
                                </ul>
                        </li>
                </ul>
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
			<div class="event-info"></div>
			<div class="body-bg"></div>
		</div>

		<a href="#0" class="close">Close</a>
	</div>

	<div class="cover-layer"></div>
</div> <!-- .cd-schedule -->
<script src="js/modernizr.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
<script src="../../../javacript/schedule.js"></script> <!-- Resource jQuery -->
</body>
</html>
