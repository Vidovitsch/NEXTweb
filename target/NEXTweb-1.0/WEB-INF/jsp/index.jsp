<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%! int fontSize;%> 
<%! int maxfontSize = 3;%> 
<html>
<head>
<title>Spring MVC Tutorial Series by Crunchify.com</title>
<style type="text/css">
    body {
            background-image: url('http://crunchify.com/bg.png');
    }
</style>
</head>
<body>
    <a href="workshops.htm">Open workshop screen</a><br>
	<br>
	<div style="text-align:center">
		<h2>
			Hey You..!! This is your 1st Spring MCV Tutorial..<br> <br>
		</h2>
		<h3>
			<a href="user.htm">Click here to See Welcome Message... </a>(to
			check Spring MVC Controller... @RequestMapping("/welcome"))
		</h3>
	</div>
    <head>
        <title>Spring MVC Tutorial Series by Crunchify.com</title>
        <style type="text/css">
            body {
                background-image: url('http://crunchify.com/bg.png');
            }
        </style>
    </head>
    <body>
        <br>
        <div style="text-align:center">
            <h2>
                Hey You..!! This is your 1st Spring MCV Tutorial..<br> <br>
            </h2>
            <h3>
                <a href="welcome.htm">Click here to See Welcome Message... </a>(to
                check Spring MVC Controller... @RequestMapping("/welcome"))
            </h3>
        </div>
        Hello World!<br/>
        <%
            out.println("Your IP address is " + request.getRemoteAddr());
        %>
    <%while (fontSize <= maxfontSize)
    {%>
    <font color="green" size="<%= fontSize%>">
        JSP Tutorial
    </font><br />
    <%fontSize++;%>
    <%}%>
    <h2>Auto Refresh Header Example</h2>
    <h1>Using GET Method to Read Form Data</h1>
    <ul>

        <li><p><b>First Name:</b>
                    <%= request.getParameter("first_name")%>
            </p></li>
        <li><p><b>Last  Name:</b>
                    <%= request.getParameter("last_name")%>
            </p></li>
    </ul>
    <form action="main.htm" method="POST">
        First Name: <input type="text" name="first_name">
        <br />
        Last Name: <input type="text" name="last_name" />
        <br />
        <input type="checkbox" name="maths" checked="checked" /> Maths
        <br />
        <input type="checkbox" name="physics"  /> Physics
        <br />
        <input type="checkbox" name="chemistry" checked="checked" /> 
        Chemistry
        <br />
        <input type="submit" name="myObjectId" value="${myObjectId}" />
</body>
</html>
