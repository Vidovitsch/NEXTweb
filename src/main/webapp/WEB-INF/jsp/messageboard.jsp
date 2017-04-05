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
        <div id="next-header">
            <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
        </div>
        <div id="spacer"></div>
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
                <form id="postMessageForm" th:action="@{/mvc/chat}" data-bind="visible: true">
                    <input id="txtareamsg" name="message" type="text" data-bind="value: message" />
                    <button id="btnSend" type="submit" data-bind="click: postMessage">Post</button>
                </form>
            </div>
        </div>
        <script src="../../../javascript/jquery-1.7.2.min.js"></script>
        <script src="../../../javascript/knockout-2.0.0.js"></script>
        <script>document.getElementById("btnSend").onclick = function() {
                if (that.message().trim() != '') {
                    var form = $("#postMessageForm");
                    $.ajax({url : form.attr("action"), type : "POST",
                      data : "message=[" + that.userName() + "] " + $("#postMessageForm input[name=message]").val(),
                        error : function(xhr) {
                            console.error("Error posting chat message: status=" + xhr.status + ", statusText=" + xhr.statusText);
                        }
                    });
                    that.message('');
                }
            };
        </script>
    </body>
</html>
