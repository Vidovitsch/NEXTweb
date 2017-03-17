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
                <form onsubmit="return doSomething();" method="POST">
                    <div class="form-section">
                        <span>E-mail:</span> </br>
                        <input class="form-control" type="text" name="email"></br>
                    </div>
                    <div class="form-section">
                        <span>Password:</span> </br>
                        <input class="form-control" type="password" name="password"></br>
                        <input id="login-button" class="form-control" type="submit" value="Login" /></br>
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
<<<<<<< HEAD
        <script>
            function doSomething() {
                var email = document.getElementsByName("email")[0].value;
                var password = document.getElementsByName("password")[0].value;
                firebase.auth().onAuthStateChanged(function (user) {
                    if (user) {
                        post('requestlogin', {result: (user.uid + ' is ingelogd!')});
                    } else {
                        alert("no user signed in");
                    }
                });
                firebase.auth().createUserWithEmailAndPassword(email, password).catch(function (error) {
                    // Handle Errors here.
                    var errorCode = error.code;
                    var errorMessage = error.message;
                    alert(errorMessage);
                    // ...
                });
                return false;
            }
            
            function post(path, params, method) {
                method = method || "post"; // Set method to post by default if not specified.

                // The rest of this code assumes you are not using a library.
                // It can be made less wordy if you use one.
                var form = document.createElement("form");
                form.setAttribute("method", method);
                form.setAttribute("action", path);

                for (var key in params) {
                    if (params.hasOwnProperty(key)) {
                        var hiddenField = document.createElement("input");
                        hiddenField.setAttribute("type", "hidden");
                        hiddenField.setAttribute("name", key);
                        hiddenField.setAttribute("value", params[key]);

                        form.appendChild(hiddenField);
                    }
                }

                document.body.appendChild(form);
                form.submit();
            }
        </script>
=======
>>>>>>> origin/master
    </body>
</html>

