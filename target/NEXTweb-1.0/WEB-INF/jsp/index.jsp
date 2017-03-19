<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%! int fontSize;%> 
<%! int maxfontSize = 3;%> 

<html>
    <head>
    </head>
    <body>
        <%@ include file="master.jsp" %>

        <a href="events.htm">Open workshop screen</a><br>
        <br>
        <a href="messageboard.htm">Open MessageBoard</a><br>
        <br>
        <a href="schedule.htm">Open schedule</a><br>
        <br>
        <a href="group.htm">Open group</a><br>
        <br>

        <p>
            <button ng-click="htmlgeneration(0)">auth</button>
        </p>
        <p id="demo"></p>
        <input id="logout-button" class="form-control" type="submit" value="Logout" />
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
                    document.getElementById("demo").innerHTML = user.email;
                } else {
                    // No user is signed in.
                    document.getElementById("demo").innerHTML = "no user logged in";

                }
            });

            document.getElementById("logout-button").onclick = function () {
                firebase.auth().signOut().then(function () {
                    post("login.htm", null, "get");
                }).catch(function (error) {
                    // An error happened.
                });
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
    </body>
</html>
