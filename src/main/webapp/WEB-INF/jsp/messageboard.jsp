<%-- 
    Document   : messageboard
    Created on : 8-mrt-2017, 14:26:33
    Author     : Bert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/css/container.css" var="containerCSS" />
        <spring:url value="/css/messageboard.css" var="messageboardCSS" />
        
        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${messageboardCSS}" rel="stylesheet" />
        <title>MessageBoard</title>
    </head>
    <body>
        <div class="wrapper">
            <div id="next-header">
                <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
            </div>
            <div id="spacer"></div>
            <input type="hidden" id="userpcn"></p>
            <h1>Messages Group ${group}</h1>
            <div id="Messageboard-container">  
                <div id="messages" style="overflow-y:scroll;">
                    <c:forEach var="msg" items="${messages}">
                        <div class="${user == msg.userName ? 'ownMessageWrapper' : 'otherMessageWrapper'}">
                            <div class="${user == msg.userName ? 'ownMessagecss' : 'otherMessagecss'}">
                                <div class="message-class">
                                   <span class ="msg-content">${msg.content} &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                                </div>
                                <c:choose>
                                    <c:when test="${user != msg.userName}">
                                        <div class="info-msg">
                                        <span class="username-label">${msg.userName}</span>
                                        <br>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="info-msg">
                                    </c:otherwise>  
                                </c:choose>
                                    <span class="date-label">${msg.date}</span>
                                        </div>
                            </div>
                        </div>    
                    </c:forEach> 
                </div>
                <div class="input-container">
                    <textarea id="txtareamsg" name="message"></textarea><button id="btnSend" type="button" onclick="sendMessage()">Send</button>
                </div>
            </div>
        </div>
            
            <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
            <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
            <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
            <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>
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
            </script>
            <script>
            var user = firebase.auth().currentUser;

            firebase.auth().onAuthStateChanged(function (user) {
                if (user) {
                    // User is signed in.
                    document.getElementById("userpcn").innerHTML = user.pcn;
                } else {
                    // No user is signed in.
                    document.getElementById("userpcn").innerHTML = "no user logged in";

                }
            });
            </script>
    </body>
</html>
