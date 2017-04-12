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
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>
        <h1>Messages Group ${group}</h1>
        <div id="Messageboard-container">  
            <div id="messages" style="overflow-y:scroll;">
                <c:forEach var="msg" items="${messages}">
                    <div class="${userUID == msg.uid ? 'ownMessageWrapper' : 'otherMessageWrapper'}">
                        <div class="${userUID == msg.uid ? 'ownMessagecss' : 'otherMessagecss'}">
                            <div class="message-class">
                               <span class ="msg-content">${msg.content} &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                            </div>
                            <c:choose>
                                <c:when test="${userUID != msg.uid}">
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
                <input id="txtareamsg" name="message" type="text"/>
                <button id="btnSend" name="btnSend" onClick="postMessage('${userUID}')">Send</button>
            </div>
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
            
            var postMessage = function(uid) {
                console.log("Im here!");
                var content = document.getElementById("txtareamsg").value;
                var dateTime = getCurrentDateTime();
                
                writeToFirebase(dateTime, content, uid);
                document.getElementById("txtaremsg").value = '';
            };
            
            function getCurrentDateTime() {
                var currentDate = new Date();
                var year = currentDate.getYear().toString().substr(1, 2);
                var dateTime = currentDate.getDay() + "-" +
                        currentDate.getMonth() + "-" +
                        year + ":" +
                        currentDate.getHours() + ":" +
                        currentDate.getMinutes() + ":" +
                        currentDate.getSeconds() + ":" +
                        currentDate.getMilliseconds();
                return dateTime;
            };
            
            function writeToFirebase(dateTime, content, uid) {
                var groupID = getGroupID(uid);
                firebase.database().ref('Group/' + groupID + '/Messages/' + dateTime).set({
                    Content: content,
                    UID: uid
                });
            };
            
            function getGroupID(uid) {
                return 0; 
            };
        </script>
    </body>
</html>
