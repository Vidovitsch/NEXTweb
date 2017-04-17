<%-- 
    Document   : eventMessage
    Created on : 17-apr-2017, 19:20:09
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>
        <spring:url value="/css/eventmsg.css" var="eventmsgCSS" />
        
        <link href="${eventmsgCSS}" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper">
            <h1>Hello World!</h1>
        </div>
        <script>
            // Initialize Firebase
            var config = {
                apiKey: "AIzaSyCRi0Ma5ekQxhwg-BfQCa6684hMzvR3Z1o",
                authDomain: "nextweek-b9a58.firebaseapp.com",
                databaseURL: "https://nextweek-b9a58.firebaseio.com",
                storageBucket: "nextweek-b9a58.appspot.com",
                messagingSenderId: "488624254338"
            };
            firebase.initializeApp(config); 
            var database = firebase.database();
            alert("${eventID}");
            var attendants = database.ref("Event/${eventID}/Attending");
            attendants.on("child_added", function(snapshot) {
                alert("Succes!");
            });
        </script>
    </body>
</html>
