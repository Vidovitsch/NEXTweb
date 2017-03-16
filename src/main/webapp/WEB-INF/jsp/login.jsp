<%-- 
    Document   : login.jsp
    Created on : 15-mrt-2017, 15:20:29
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <spring:url value="/css/container.css" var="containerCSS" />
        <spring:url value="/css/login.css" var="loginCSS" />

        <link href="${containerCSS}" rel="stylesheet" />
        <link href="${loginCSS}" rel="stylesheet" />

    </head>

    <body>
        <div id="next-header">
            <a href="index.htm"><img id="next-logo" src="/images/next_logo.png"/></a>
        </div>
        <div id="spacer"></div>

        <div class="login-page">
            <div id="login-form">
                <h2>Login</h2>
                <form action="requestlogin" method="POST">
                    <div class="form-section">
                        <span>E-mail:</span> </br>
                        <input class="form-control" type="text" name="email"></br>
                    </div>
                    <div class="form-section">
                        <span>Password:</span> </br>
                        <input class="form-control" type="password" name="password"></br>
                        <input id="login-button" class="form-control" type="submit" value="Login" />
                    </div>
                </form>
            </div>
            <div id="login-image">
                <img src="/images/next_wallpaper1.jpg" />
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

    </body>

</html>

