<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%! int fontSize;%> 
<%! int maxfontSize = 3;%> 

<html>
<head>
</head>
<body>
    <%@ include file="master.jsp" %>
    
    <a href="events.htm">Open workshop screen</a><br>
    <br>
    <a href="messageboard.htm">Open MessageBoard</a><br>
    <br>
    <a href="schedule.htm">Open schedule</a><br>
    <br>
    <a href="group.htm">Open group</a><br>
    <br>
    
    <p>
        <button ng-click="htmlgeneration(0)">auth</button>
    </p>

</body>
</html>
