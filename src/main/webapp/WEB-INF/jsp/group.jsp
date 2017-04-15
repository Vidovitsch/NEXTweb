<%-- 
    Document   : user
    Created on : 7-mrt-2017, 17:03:48
    Author     : mmjan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-auth.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.1/firebase-database.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.7.2/firebase.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/group.css" />
        <title>Group</title>
    </head>
    <body>
        <div class="wrapper">
            <div id="next-group">
                <h2>Group Nr: ${group.groupNumber}</h2>
                <c:forEach var="user" items="${users}">
                    <div class="dropdown">
                        <div class="dropdown-div">
                            <div style="display: inline-block; float: left; width: 75px">
                                <img src="/images/default_user.jpg" width="64px" height="64px">
                            </div>
                            <div style="display: inline-block; font-size: 32px; padding-top: 16px">
                                ${user.name}
                            </div>
                        </div>
                        <div class="dropdown-content">
                        <table>
                            <tr>
                                <td><b>name: </b></td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td><b>email: </b></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td><b>class: </b></td>
                                <td>S43, Software</td>
                            </tr>
                            </table>
                        </div>
                    </div>
                    <br>        
                </c:forEach>
            </div>
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
                    <button id="btnSend" name="btnSend" type="submit" onClick="postMessage('${userUID}')">Post</button>
                </div>
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
            var database = firebase.database();

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
                database.ref('Group/' + groupID + '/Messages/' + dateTime).set({
                    Content: content,
                    UID: uid
                });
            };

            function getGroupID(uid) {
                return 0; 
            };

            var messages = database.ref("Group/0/Messages");
            messages.on("child_added", function(snapshot) {
            var message = snapshot.val();
            var userName = database.ref("User/" + message.UID + "/Name").valueOf();
                 if (${userUID == msg.uid})
                 {
                    document.getElementById("messages").innerHTML += 
                         '<div class="ownMessageWrapper"><div class="ownMessagecss"><div class="message-class"><span class ="msg-content">' + message.Content + ' &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></div><div class="info-msg"><span class="date-label">' + snapshot.val().toString() + '</span></div></div></div>';
                 }
                 else
                 {
                    document.getElementById("messages").innerHTML += 
                         '<div class="otherMessageWrapper"><div class="otherMessagecss"><div class="message-class"><span class ="msg-content">' + message.Content + ' &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></div><div class="info-msg"><span class="username-label">' + userName + '</span><br><span class="date-label">' + snapshot.toString() + '</span></div></div></div>';
                 }
            });
        </script>
    </body>
</html>
