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
            <div id="message">
                <div id="cssLoader" class="loader"></div><br>
            </div>
        </div>
        <script>
            
            function startTimer(duration, display) {
                var start = Date.now(),
                    diff,
                    seconds;
                function timer() {
                    // get the number of seconds that have elapsed since 
                    // startTimer() was called
                    diff = duration - (((Date.now() - start) / 1000) | 0);

                    // does the same job as parseInt truncates the float
                    seconds = (diff % 60) | 0;

                    seconds = seconds < 10 ? "0" + seconds : seconds;
                    
                    if (seconds === '00') {
                        location.replace("/events");
                    }
                    
                    display.textContent = seconds; 

                    if (diff <= 0) {
                        // add one second so that the count down starts at the full duration
                        // example 05:00 not 04:59
                        start = Date.now() + 1000;
                    }
                };
                // we don't want to wait a full second before the timer starts
                timer();
                setInterval(timer, 1000);
            }
            
            function startCountDown() {
                var seconds = 5,
                display = document.querySelector('#time');
                startTimer(seconds, display);
            };
            
            function initRedirect(available) {
                document.getElementById("message").innerHTML += 
                        '<div id="available"><span>' + available + '</span></div>';
                document.getElementById("message").innerHTML += 
                        '<div id="cdTime">Redirecting in <span id="time"></span> seconds!</div>';
                startCountDown();
            }
            
            window.onload = function() {
                var loader = document.getElementById('cssLoader'),
                    seconds = 2,
                    second = 0,
                    interval;
                interval = setInterval(function() {
                    if (second >= seconds) {
                        loader.parentNode.removeChild(loader);
                        initRedirect("${available}");
                    }
                second++;
                }, 1000);
            }
        </script>
    </body>
</html>
