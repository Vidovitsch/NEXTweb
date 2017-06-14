<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%! int fontSize;%> 
<%! int maxfontSize = 3;%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
    <head>
        <spring:url value="/css/index.css" var="mainCSS" />
        <link href="${mainCSS}" rel="stylesheet" />
    </head>
    <body>
        <div class="wrapper">
            
            <div class="headline">Je ultieme ervaring begint hier</div>
            <a href="events.htm">Open workshop screen</a><br>
            <br>
            <a href="schedule.htm">Open schedule</a><br>
            <br>
            <a href="group.htm">Open group</a><br>
            <br>
            <div class="button_base b05_3d_roll button_logout" id="logout-button">
                <div>Logout</div>
                <div>Logout</div>
            </div>
            <p>
                <button ng-click="htmlgeneration(0)">auth</button>
            </p>
            <p id="demo"></p>
            <input type="button" id="btnSearch" value="Copy to clipboard" onclick="GetValue();" />
            <p id="message" ></p><br>

            <script>
                function GetValue()
                {
                    document.getElementById("message").innerHTML = "m.vaneijkeren@student.fontys.nl;david.debekker@student.fontys.nl;a.dekkerslos@student.fontys.nl";
                    copyToClipboard("message");
                }

                function copyToClipboard(elementId) {


                    var aux = document.createElement("input");
                    aux.setAttribute("value", document.getElementById(elementId).innerHTML);
                    document.body.appendChild(aux);
                    aux.select();
                    document.execCommand("copy");

                    document.body.removeChild(aux);

                }
            </script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
            <span>sample@mail.com</span>
            <span>another@mail.com</span>
            <span>sample2@mail.com</span>
            <a href="mailto:your@email.address?body=One paragraph.">Contact Us</a>
            <script>
                $("span").click(function () {
                    window.location.href = "mailto:user@example.com?subject=Subject&body=message%20goes%20here";
                });
            </script>
            <script>
                var user = firebase.auth().currentUser;
                firebase.auth().onAuthStateChanged(function (user) {
                    if (user) {
                        // User is signed in.il;
                    } else {
                        // No user is signed in.
                        //post("login.htm", null, "get"); 
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
