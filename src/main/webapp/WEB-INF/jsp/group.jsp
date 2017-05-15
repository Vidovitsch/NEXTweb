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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/group.css" />
        <title>Group</title>
    </head>
    <body>
        <div class="wrapper">
            <div id="next-group">
                <h2>Group Nr: ${group.groupNumber}</h2>
                <h2>Table Nr: ${group.location}</h2>
                <c:forEach var="user" items="${group.users}">
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
                                <td><b>profile: semester </b></td>
                                <td>${user.course}: ${user.semester}</td>
                            </tr>
                            </table>
                        </div>
                    </div>
                    <br>        
                </c:forEach>
            </div>
            <div id="Messageboard-container">  
                <div id="messages" style="overflow-y:scroll;">
                    
                </div>
                <div class="input-container">
                    <input id="txtareamsg" name="message" type="text"/>
                    <button id="btnSend" name="btnSend" type="submit" onClick="postMessage('${userUID}')">Post</button>
                </div>
            </div>
        </div>
        <script>
            // Initialize Firebase
            var database = firebase.database();
            
            var input = document.getElementById('txtareamsg');
            input.focus();
            input.select();
            
            document.getElementById("txtareamsg")
                .addEventListener("keyup", function(event) {
                event.preventDefault();
                if (event.keyCode == 13) {
                    document.getElementById("btnSend").click();
                }
            }); 
            
            var totalMessages = 0;
            setInterval(setTime, 5000);

            function setTime()
            {
                totalMessages = 0;
            }


            var postMessage = function(uid) {
                if (totalMessages > 4)
                {
                    alert("Dude, stahp");
                    return;
                }
                if (totalMessages > 3)
                {
                    alert("Don't spam the page please");
                    totalMessages++;
                    return;
                }
                if (!document.getElementById("txtareamsg").value)
                    return;
                var content = document.getElementById("txtareamsg").value;
                var dateTime = getCurrentDateTime();

                writeToFirebase(dateTime, content, uid);
                totalMessages++;
                document.getElementById("txtareamsg").value = '';
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
            
            var id;
            function writeToFirebase(dateTime, content, uid) {
                database.ref('User/' + uid + '/GroupID/').once("value", function(data) {
                    id = data.val();
                    database.ref('Group/' + id + '/Messages/' + dateTime).set({
                        Content: content,
                        UID: uid
                    });
                });
            };

            database.ref('User/${userUID}/GroupID/').once("value", function(data) {
                id = data.val();
                bindMessages();
            });
            
            function bindMessages() {
                var path = "Group/"+id+"/Messages";
                var messages = database.ref(path);
                messages.on("child_added", function(snapshot) {
                    var message = snapshot.val();
                    var userName;
                    var date;
                    date = snapshot.key;
                    console.log(message.UID);
                    database.ref("User/" + message.UID).once("value", function(data) {
                        userName = data.val().Name;
                        var lastName = data.val().Lastname;
                        if (lastName !== undefined)
                        {
                            userName += " " + lastName;
                        }
                        postMsg(message, userName, date);
                    });
                });
            }
            
            function postMsg(message, userName, date) {
                var uid = "${userUID}";
                if (uid === message.UID)
                {
                   document.getElementById("messages").innerHTML += 
                        '<div class="ownMessageWrapper"> \
                           <div class="ownMessagecss"> \
                               <div class="message-class">\
                                   <span class ="msg-content">' + message.Content + ' &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>\
                               </div>\
                               <div class="info-msg">\
                                   <span class="date-label">' + date + '</span>\
                               </div>\
                           </div>\
                       </div>';
                }
                else
                {
                   document.getElementById("messages").innerHTML += 
                       '<div class="otherMessageWrapper">\
                           <div class="otherMessagecss">\
                               <div class="message-class">\
                                   <span class ="msg-content">' + message.Content + ' &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>\
                               </div>\
                               <div class="info-msg">\
                                   <span class="username-label">' + userName + '</span><br>\
                                   <span class="date-label">' + date + '</span>\
                               </div>\
                           </div>\
                       </div>';
                }
                var el = document.getElementById("messages");
                el.scrollTop = el.scrollHeight;
            }
        </script>
    </body>
</html>
